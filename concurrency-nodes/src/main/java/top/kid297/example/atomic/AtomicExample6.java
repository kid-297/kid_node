package top.kid297.example.atomic;

import lombok.extern.slf4j.Slf4j;
import top.kid297.annoations.ThreadSafe;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @version V1.0
 * @Description: AtomicBoolean
 * @Auther: ly
 * @Date: 2019/7/2 16:04
 */
@ThreadSafe
@Slf4j
public class AtomicExample6 {

    private static AtomicBoolean isHappened = new AtomicBoolean(false);

    public static AtomicInteger count = new AtomicInteger(0);

    //请求总数
    public static int clientTotal = 5000;

    //同时并发执行的线程数
    public static int threadTotal = 200;


    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    test();
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("exception", e);
                }
                countDownLatch.countDown();
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            log.error("exception",e);
        }
        log.info("isHappened:{}",isHappened.get());
    }


    public static void test(){
        if (isHappened.compareAndSet(false,true)){
            log.info("excute");
        }
    }
}
