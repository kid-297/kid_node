package top.kid297.example.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @version V1.0
 * @Description:
 * @Auther: ly
 * @Date: 2019/7/29 15:47
 */
@Slf4j
public class ThreadPoolExample1 {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            final int threadNum = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    log.info("tesk:{}",threadNum);
                }
            });
        }

        executorService.shutdown();
    }
}
