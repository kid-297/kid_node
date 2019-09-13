package top.kid297.example.singleton;

/**
 * @version V1.0
 * @Description: 单例模式 -- 饿汉模式
 * 单例模式在类装载的时候创建
 * @Auther: ly
 * @Date: 2019/7/3 11:36
 */
public class SingletonExample6 {

    /**
     * 私有的构造函数
     */
    private SingletonExample6(){

    }


    /**
     * 单例对象
     */
    private static SingletonExample6 instance = null;

    static {
        instance = new SingletonExample6();
    }


    /**
     * 静态工厂方法
     * @return
     */
    public static SingletonExample6 getInstance(){
        return instance;
    }

    public static void main(String[] args) {
        System.out.println(getInstance().hashCode());
        System.out.println(getInstance().hashCode());
    }
}
