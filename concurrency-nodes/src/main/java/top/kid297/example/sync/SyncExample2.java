package top.kid297.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @version V1.0
 * @Description: 修饰一个类和一个静态方法
 * @Auther: ly
 * @Date: 2019/7/2 17:07
 */
@Slf4j
public class SyncExample2 {

    /**
     * 修饰一个类
     */
    public static void test1(int j){
        synchronized (SyncExample2.class){
            for (int i = 0; i < 10; i++) {
                log.info("test {} - {}",j,i);
            }
        }
    }

    /**
     * 修饰一个静态方法
     */
    public static synchronized void test2(int j){
        for (int i = 0; i < 10; i++) {
            log.info("test {} - {}",j,i);
        }
    }

    public static void main(String[] args) {
        SyncExample2 syncExample1 = new SyncExample2();
        SyncExample2 syncExample2 = new SyncExample2();

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            test1(1);
        });

        executorService.execute(() -> {
            test1(2);
        });
    }
}
