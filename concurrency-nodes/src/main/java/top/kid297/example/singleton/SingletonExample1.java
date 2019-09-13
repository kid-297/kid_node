package top.kid297.example.singleton;

/**
 * @version V1.0
 * @Description: 单例模式 -- 懒汉模式
 * 在第一次使用的时候装载
 * @Auther: ly
 * @Date: 2019/7/3 11:36
 */
public class SingletonExample1 {

    //私有的构造函数
    private SingletonExample1(){

    }

    //单例对象
    private static SingletonExample1 instance = null;

    //静态工厂方法
    public static SingletonExample1 getInstance(){
        if (instance == null){
            instance = new SingletonExample1();
        }
        return instance;
    }
}
