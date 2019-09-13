package top.kid297.disruptor.heigh.multi;

import com.lmax.disruptor.WorkHandler;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @version V1.0
 * @Description:
 * @Auther: ly
 * @Date: 2019/8/23 10:38
 */
public class Consumer implements WorkHandler<Order> {
    private String comsumerId;

    private static AtomicInteger count = new AtomicInteger(0);

    private Random random = new Random();

    public Consumer(String comsumerId) {
        this.comsumerId = comsumerId;
    }

    public int getCount() {
        return count.get();
    }

    @Override
    public void onEvent(Order event) throws Exception {
        Thread.sleep(1 * random.nextInt(5));
        System.out.println("当前消费者："+this.comsumerId + ",消费信息ID:"+event.getId());
        count.incrementAndGet();
    }
}
