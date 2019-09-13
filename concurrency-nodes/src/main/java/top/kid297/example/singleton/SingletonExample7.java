package top.kid297.example.singleton;

import top.kid297.annoations.Recommend;
import top.kid297.annoations.ThreadSafe;

/**
 * @version V1.0
 * @Description: 单例模式 -- 枚举(线程安全，推荐使用)
 * @Auther: ly
 * @Date: 2019/7/3 11:36
 */
@ThreadSafe
@Recommend
public class SingletonExample7 {

    /**
     * 私有的构造函数
     */
    private SingletonExample7(){

    }


    /**
     * 单例对象
     */
    private static SingletonExample7 instance = null;

    static {
        instance = new SingletonExample7();
    }


    /**
     * 静态工厂方法
     * @return
     */
    public static SingletonExample7 getInstance(){
        return Singleten.INSTANCE.getInstance();
    }

    private enum Singleten{
        INSTANCE;

        private SingletonExample7 singleton;

        /**
         * jvm保证这个方法觉得只调用一次
         */
        Singleten(){
            singleton = new SingletonExample7();
        }

        public SingletonExample7 getInstance(){
            return singleton;
        }
    }
}
