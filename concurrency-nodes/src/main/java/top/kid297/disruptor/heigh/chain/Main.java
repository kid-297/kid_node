package top.kid297.disruptor.heigh.chain;

import com.lmax.disruptor.BusySpinWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @version V1.0
 * @Description:
 * @Auther: ly
 * @Date: 2019/8/22 16:25
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService1 = Executors.newFixedThreadPool(4);
        ExecutorService executorService2 = Executors.newFixedThreadPool(5);

        //1.构建disruptor
        Disruptor disruptor = new Disruptor<Trade>(
        new EventFactory<Trade>() {
            @Override
            public Trade newInstance() {
                return new Trade();
            }
        },
        1024*1024,
                executorService2,
        ProducerType.SINGLE,
        new BusySpinWaitStrategy());
        //2.把消费者设置到Disruptor 中 handleEventWith\
        //2.1 串行操作
        /*disruptor
                .handleEventsWith(new Handler1())
                .handleEventsWith(new Handler2())
                .handleEventsWith(new Handler3());*/

        //2.2 并行操作
        /*disruptor.handleEventsWith(new Handler1(),new Handler2(),new Handler3(),new Handler4(),new Handler5());*/

        //2.3 菱形操作 （一）
        /*disruptor.handleEventsWith(new Handler1(),new Handler2()).handleEventsWith(new Handler3());*/

        //2.3 菱形操作 （二）
       /* EventHandlerGroup ehGroup = disruptor.handleEventsWith(new Handler1(), new Handler2());
        ehGroup.then(new Handler3());*/

        //2.4 多边形操作（六边形）
        Handler1 handler1 = new Handler1();
        Handler2 handler2 = new Handler2();
        Handler3 handler3 = new Handler3();
        Handler4 handler4 = new Handler4();
        Handler5 handler5 = new Handler5();
        disruptor.handleEventsWith(handler1,handler4);
        disruptor.after(handler1).handleEventsWith(handler2);
        disruptor.after(handler4).handleEventsWith(handler5);
        disruptor.after(handler2,handler5).handleEventsWith(handler3);


        //3.启动
        RingBuffer<Trade> start = disruptor.start();
        CountDownLatch latch = new CountDownLatch(1);

        long begin = System.currentTimeMillis();

        executorService1.submit(new ThadePushlisher(latch,disruptor));

        latch.await();
        disruptor.shutdown();
        executorService1.shutdown();
        executorService2.shutdown();
        System.out.println("总耗时："+(System.currentTimeMillis() - begin));
    }
}
