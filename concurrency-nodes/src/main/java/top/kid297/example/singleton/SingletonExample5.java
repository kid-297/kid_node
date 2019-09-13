package top.kid297.example.singleton;

import top.kid297.annoations.ThreadSafe;

/**
 * @version V1.0
 * @Description: 单例模式 -- 懒汉模式(双重同步锁代理模式)
 * 在第一次使用的时候装载
 * @Auther: ly
 * @Date: 2019/7/3 11:36
 */
@ThreadSafe
public class SingletonExample5 {

    /**
     * 私有的构造函数
     */
    private SingletonExample5(){

    }

    /**
     * 单例对象 volatile + 双重检测机制 来禁止指令重排机制
     */
    private static volatile SingletonExample5 instance = null;

    //静态工厂方法
    public static SingletonExample5 getInstance(){
        //使用双层检测机制
        if (instance == null){
            //同步锁
            synchronized (SingletonExample5.class){
                if (instance == null){
                    instance = new SingletonExample5();
                }
            }
        }
        return instance;
    }
}
