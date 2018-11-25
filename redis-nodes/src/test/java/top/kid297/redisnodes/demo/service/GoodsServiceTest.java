package top.kid297.redisnodes.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsServiceTest {

    @Autowired
    private GoodsService goodsService;

    //商品sku
    private static final String GOODS_SKU = "10001";

    //模拟访问次数
    private static final int THREAD_NUM = 1000;

    //倒计数器
    private CountDownLatch countDownLatch = new CountDownLatch(THREAD_NUM);

    @Test
    public void quertGoodsNum() throws InterruptedException {
        //创建，并不是马上发起请求
        Thread[] threads = new Thread[THREAD_NUM];
        for (int i = 0; i < threads.length; i++) {
            //多线程模拟用户查询请求
            Thread thread = new Thread(()->{
                try {
                    //代码在这里等待，等待countDownLatch为0 ，代表所有线程都start，再运行后续代码
                    countDownLatch.await();
                    //调用查询方法
                    goodsService.quertGoodsNum2(GOODS_SKU);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }catch (Exception e){
                    e.printStackTrace();
                }
            });
            threads[i] = thread;
            thread.start();
            //倒计时减一，代表以为计数器就位
            countDownLatch.countDown();
        }
        //等待上面所有线程执行完毕之后，结束测试
        for (Thread thread : threads){
            thread.join();
        }
    }
}