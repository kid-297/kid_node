# Spring三个阶段
- 配置阶段
    - web.xml   --> DispatcherServlet
    - init-param --> contexiConfigLocation classpath:application.xml
    - url-pattern --> /*
- 初始化阶段
    - init 加载配置文件
    - IOC容器初始化 --> Map<String ,Object>
    - 扫描相关的类 --> scan-package="com.kid"
    - 实例化  --> 通过反射机制将类实例化放入IOC容器中
    - 进行DI操作 -->  在IOC容器中的实例，有很多属性没有赋值，自动把需要赋值的属性进行赋值。
    - HandlerMapping -->  能够将一个URL和一个Method进行一个关联映射
- 运行阶段
    - doPost/doGet  --> request/response
    - 从HandlerMapping去匹配 -->  找得Method、通过反射invoker,再次返回的结果交给
    - invoker 
    - response.getWrite()
    
# 项目介绍
- 该项目搭建为maven项目，整合tomcat，用为项目启动。
- 其中demo包下为普通service层和Controller层代码
- mvcframework 为自己配置的注解类和启动servlet类。
- 项目采用annotation+web.xml+servlet方式，，用不到400行代码来描述SpringIOC、DI、MVC的精华设计思想，并保证基本功能完整。
