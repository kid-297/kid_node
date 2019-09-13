package top.kid297.example.singleton;

import top.kid297.annoations.NotRecommend;

/**
 * @version V1.0
 * @Description: 单例模式 -- 懒汉模式(线程安全，消耗性能，不推荐使用)
 * 在第一次使用的时候装载
 * @Auther: ly
 * @Date: 2019/7/3 11:36
 */
@NotRecommend
public class SingletonExample3 {

    //私有的构造函数
    private SingletonExample3(){

    }

    //单例对象
    private static SingletonExample3 instance = null;

    //静态工厂方法
    public static synchronized SingletonExample3 getInstance(){
        if (instance == null){
            instance = new SingletonExample3();
        }
        return instance;
    }
}
