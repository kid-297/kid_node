package top.kid297.disruptor;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @version V1.0
 * @Description: 并发容器类
 * @Auther: ly
 * @Date: 2019/8/26 15:19
 */
public class Review {

    public static void main(String[] args) throws InterruptedException {
        /*
           重入锁
         */
        //ConcurrentMap map = new ConcurrentHashMap(1);

        /*
            读多写少适用
            全量拷贝
         */
       /* CopyOnWriteArrayList arrayList = new CopyOnWriteArrayList();
        arrayList.add("aa");

        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(1);

        SynchronousQueue synchronousQueue = new SynchronousQueue(false);*/

        //Volatile 指令重排序  一般被Volatile修饰  1.可以立即感知到外部变化  2.禁止了指令重排序

       /* AtomicInteger atomicInteger = new AtomicInteger();

        AtomicDouble atomicDouble = new AtomicDouble();*/

        //UnSafe 类 的四大作用
        /*
            内存操作
            字段的定位与修改S
            挂起与恢复
            CAS操作（乐观锁） 比较并交换
         */

        /*
            J.U.C 工具类  AQS
            CountDownLatch & CyclicBarrier
            Future模式与Caller接口  异步提交模式
            Exchanger 线程数据交换器 （线程之间相互交换）
            ForkJoin 并行计算框架  递归算法
            Semaphore 信号量  在并发访问使用Semaphore控制线程

         */

        /*
            AQS锁
            ReentrantLock 重入锁
            ReentrantReadWriteLock 读写锁   读读共享 读写互斥
            Condition条件判断
            LockSupport基于线程的锁  unSafe 对于操作系统底层操作
         */

        /*
           正常Object使用lock
         */
        /*Object lock = new Object();
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                int sum = 0;
                for (int i = 0; i < 10; i++) {
                    sum+=i;
                }
                synchronized (lock){
                    try {
                        //wait 释放锁  notify 不释放锁
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("sum："+sum);
                }
            }
        });
        a.start();

        Thread.sleep(2000);

        synchronized (lock){
            lock.notify();
        }*/

        /*
            LockSupport使用lock
         */
       /* Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                int sum = 0;
                for (int i = 0; i < 10; i++) {
                    sum+=i;
                }
                LockSupport.park();
                System.out.println("sum："+sum);
            }
        });
        a.start();
        Thread.sleep(2000);
        LockSupport.unpark(a);*/

       /*
            线程池的创建
        */
      /*  ThreadPoolExecutor pool = new ThreadPoolExecutor(5,
                Runtime.getRuntime().availableProcessors() * 2,
                60,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(200),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread t = new Thread(r);
                        t.setName("order-thread");
                        if (t.isDaemon()) {
                            t.setDaemon(false);
                        }
                        if (Thread.NORM_PRIORITY != t.getPriority()) {
                            t.setPriority(Thread.NORM_PRIORITY);
                        }
                        return t;
                    }
                },
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        System.out.println("拒绝策略" + r);
                    }
                });

        pool.shutdown();*/

        ReentrantLock reentrantLock = new ReentrantLock();

        CountDownLatch countDownLatch = new CountDownLatch(1);


    }


}
