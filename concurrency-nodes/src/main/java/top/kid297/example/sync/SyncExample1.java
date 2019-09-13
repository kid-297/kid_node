package top.kid297.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @version V1.0
 * @Description:
 * @Auther: ly
 * @Date: 2019/7/2 17:07
 */
@Slf4j
public class SyncExample1 {

    /**
     * 修饰一个代码块
     */
    public void test1(int j){
        synchronized (this){
            for (int i = 0; i < 10; i++) {
                log.info("test {} - {}",j,i);
            }
        }
    }

    /**
     * 修饰一个方法
     */
    public synchronized void test2(int j){
        for (int i = 0; i < 10; i++) {
            log.info("test {} - {}",j,i);
        }
    }

    public static void main(String[] args) {
        SyncExample1 syncExample1 = new SyncExample1();
        SyncExample1 syncExample2 = new SyncExample1();

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            syncExample1.test2(1);
        });

        executorService.execute(() -> {
            syncExample2.test2(2);
        });
    }
}
