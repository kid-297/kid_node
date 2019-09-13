package top.kid297.example.singleton;

import top.kid297.annoations.NotThreadSafe;

/**
 * @version V1.0
 * @Description: 单例模式 -- 懒汉模式(双重同步锁代理模式) 可能会发生指令重排
 * 在第一次使用的时候装载
 * @Auther: ly
 * @Date: 2019/7/3 11:36
 */
@NotThreadSafe
public class SingletonExample4 {

    //私有的构造函数
    private SingletonExample4(){

    }

    //单例对象
    private static SingletonExample4 instance = null;

    //静态工厂方法
    public static SingletonExample4 getInstance(){
        //使用双层检测机制
        if (instance == null){
            //同步锁
            synchronized (SingletonExample4.class){
                if (instance == null){
                    instance = new SingletonExample4();
                }
            }
        }
        return instance;
    }
}
