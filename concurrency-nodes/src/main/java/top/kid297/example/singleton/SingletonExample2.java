package top.kid297.example.singleton;

/**
 * @version V1.0
 * @Description: 单例模式 -- 饿汉模式
 * 单例模式在类装载的时候创建
 * @Auther: ly
 * @Date: 2019/7/3 11:36
 */
public class SingletonExample2 {

    //私有的构造函数
    private SingletonExample2(){

    }

    //单例对象
    private static SingletonExample2 instance = new SingletonExample2();

    //静态工厂方法
    public static SingletonExample2 getInstance(){
        return instance;
    }
}
