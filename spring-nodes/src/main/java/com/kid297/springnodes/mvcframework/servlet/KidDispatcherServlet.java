package com.kid297.springnodes.mvcframework.servlet;

import com.kid297.springnodes.mvcframework.annotation.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;


/**
 * 启动入口
 */
public class KidDispatcherServlet extends HttpServlet {
    public KidDispatcherServlet() { super(); }
    /**
     * 跟web.xml中param-name的值一致
     */
    private static final String LOCATION = "contextConfigLocation";

    /**
     * 保存所有配置文件
     */
    private Properties p = new Properties();

    /**
     * 保存所有扫描到的相关的类名
     */
    private List<String> classNames = new ArrayList<String>();

    /**
     * 核心IOC容器，保存所有初始化的bean
     */
    private Map<String,Object> ioc = new HashMap<String, Object>();

    /**
     * 保存所有Url和方法的映射关系
     */
    private Map<String,Method> handlerMapping = new HashMap<>();



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    /**
     * 执行业务处理
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            doDispath(req,resp); //开始匹配到对应方法
        }catch (Exception e){
            //如果匹配的过程中出现异常，将异常信息打印出去
            resp.getWriter().write(String.format("500 Exception,Details :\r\n%s", Arrays.toString(e.getStackTrace())
                    .replaceAll("\\[|\\]", "").replaceAll(",\\s", "\r\n")));
        }
    }

    /**
     * 开始匹配到对应方法
     * @param req
     * @param resp
     */
    private void doDispath(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if(this.handlerMapping.isEmpty()) { return;}
        String url = req.getRequestURI();
        String contextPath = req.getContextPath();

        url = url.replace(contextPath,"").replaceAll("//","/");

        if (!this.handlerMapping.containsKey(url)){
            resp.getWriter().write("404 Not Found !");
            return;
        }

        Map<String,String[]> params = req.getParameterMap();
        Method method = this.handlerMapping.get(url);
        //获取方法的参数列表
        Class<?> [] parameterTypes = method.getParameterTypes();
        //获取请求参数
        Map<String,String[]> parameterMap = req.getParameterMap();
        //保存参数值
        Object[] paramValues = new Object[parameterTypes.length];
        //方法参数列表
        for (int i = 0; i < parameterTypes.length; i++) {
            //根据参数名称，做某些处理
            Class parameterType = parameterTypes[i];
            if (parameterType == HttpServletRequest.class){
                //参数类型已明确，这边强转类型
                paramValues[i] = req;
                continue;
            }else if(parameterType == HttpServletResponse.class){
                paramValues[i] = resp;
                continue;
            }else if(parameterType == String.class){
                for(Map.Entry<String,String[]> parm : parameterMap.entrySet()){
                    String value = Arrays.toString(parm.getValue())
                            .replaceAll("\\[|\\]","")
                            .replaceAll(",\\s",",");
                    paramValues[i] = value;
                }
            }
        }
        try {
            String beanName = lowerFirstCase(method.getDeclaringClass().getSimpleName());
            //利用反射机制来调用
            method.invoke(this.ioc.get(beanName),paramValues);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * 初始化，加载配置文件
     * @param config
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        //1,加载配置
        doLocalConfig(config.getInitParameter(LOCATION));

        //2.扫描所有相关的类
        doScanner(p.getProperty("scanPackage"));
        //doScanner("com.kid297.springnodes.demo");

        //3.初始化所有相关类的实例，并保存到IOC容器中去
        doInstance();

        //4.依赖注入
        doAutowired();

        //5.构造HandlerMapping
        initHandlerMapping();

        //6.等待请求，调用doget、dopost方法

        //提示信息
        System.out.println("my springmvc is init");
    }

    /**
     * 构造HandlerMapping
     */
    private void initHandlerMapping() {
        if (ioc.isEmpty()){ return;}
        for (Map.Entry<String,Object> entry : ioc.entrySet() ){
            Class<?> clazz = entry.getValue().getClass();
            if(!clazz.isAnnotationPresent(KidController.class)){ continue;}

            String baseUrl = "";
            //获取Controller的url配置
            if(clazz.isAnnotationPresent(KidRequestMapping.class)){
                KidRequestMapping requestMapping = clazz.getAnnotation(KidRequestMapping.class);
                baseUrl = requestMapping.value();
            }

            //获取Method的url配置
            Method[] methods = clazz.getMethods();
            for (Method method : methods){
                //没有加RequestMapping注解直接忽略
                if(!method.isAnnotationPresent(KidRequestMapping.class)){ continue;}

                //映射Method 的url 配置
                KidRequestMapping requestMapping = method.getAnnotation(KidRequestMapping.class);
                String url = ("/"+baseUrl+"/"+requestMapping.value()).replace("//","/");
                handlerMapping.put(url,method);
                System.out.println("mapped"+url+","+method);
            }
        }

        }

    /**
     * 依赖注入
     */
    private void doAutowired() {
        if(ioc.isEmpty()){ return; }
        for (Map.Entry<String,Object> entry : ioc.entrySet() ){
            //拿到实例对象中所有属性
            Field[] fields = entry.getValue().getClass().getDeclaredFields();
            for (Field field : fields) {
                if(!field.isAnnotationPresent(KidAutowired.class)){ continue;}
                KidAutowired autowired = field.getAnnotation(KidAutowired.class);
                String beanName = autowired.value().trim();
                if("".equals(beanName)){
                    beanName = field.getType().getName();
                }
                field.setAccessible(true);   //设置私有属性访问权限
                try {
                    field.set(entry.getValue(),ioc.get(beanName));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    continue;
                }
            }
         }

    }

    /**
     * 初始化所有相关类的实例
     */
    private void doInstance() {
        if(classNames.size() == 0){ return; }
        try {
            for (String className :classNames){
                Class<?> clazz = Class.forName(className);
                if(clazz.isAnnotationPresent(KidController.class)){
                    //默认将首字母小写作为beanName
                    String beanName = lowerFirstCase(clazz.getSimpleName());
                    ioc.put(beanName,clazz.newInstance());   //newInstance()也是用来创建新的对象,其与new()的区别是:newInstance():弱类型,效率低,只能调用无参构造 new():强类型,高效率,能调用任何public构造器
                }else if (clazz.isAnnotationPresent(KidService.class)){
                    KidService service = clazz.getAnnotation(KidService.class);
                    String beanName = service.value();
                    //如果用户自己设置了名字，就使用用户设置的
                    if(!"".equals(beanName.trim())){
                        ioc.put(beanName,clazz.newInstance());
                        continue;
                    }
                    //如果没有设置，则按照接口类型创建一个实例
                    Class<?>[] interfaces = clazz.getInterfaces();
                    for (Class<?> i :interfaces){
                        ioc.put(i.getName(),clazz.newInstance());
                    }
                }else {
                    continue;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 扫描所有相关类
     */
    private void doScanner(String packageName) {
        //将所有的包路径转换成文件路径
        URL url = this.getClass().getClassLoader().getResource("/"+packageName.replaceAll("\\.","/"));
        File dir = new File(url.getFile());
        for (File file: dir.listFiles()){
            //如果是文件夹，则继续递归
            if(file.isDirectory()){
                doScanner(packageName+"."+file.getName());
            }else{
                classNames.add(packageName+"."+file.getName().replace(".class","").trim());
            }
        }
    }

    /**
     * 加载配置文件
     */
    private void doLocalConfig(String location){
        InputStream inputStream = null;
        try {
            inputStream = KidDispatcherServlet.class.getClassLoader().getResourceAsStream(location);
            //读取配置文件
            p.load(inputStream);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 首字母小写
     * @param str
     * @return
     */
    private String lowerFirstCase(String str){
        char [] chars = str.toCharArray();
        chars[0] +=32;
        return String.valueOf(chars);
    }


}
