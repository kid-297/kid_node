package top.kid297.disruptor.quickStart;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @version V1.0
 * @Description:
 * @Auther: ly
 * @Date: 2019/8/16 16:39
 */
public class Main {

    /*
    * 1.建立一个工厂Event类，用于创建Event类实例对象
    * 2.需要有一个监听事件类，用于处理数据（Event类）
    * 3.实例化Disruptor实例，配置一系列参数，编写Disruptor核心组件
    * 4.编写生产者组件，向Disruptor容器中去投递数据
    */

    public static void main(String[] args) {
        //1.建立一个工厂Event类，用于创建Event类实例对象
        EventFactory<EventOrder> eventFactory = new OrderEventFactory();

        int ringBufferSize = 1024*1024;
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        Disruptor<EventOrder> disruptor = new Disruptor<EventOrder>(eventFactory,
                ringBufferSize,
                executor,
                ProducerType.SINGLE,
                new BlockingWaitStrategy()) ;

        //2.需要有一个监听事件类，用于处理数据（Event类）
        disruptor.handleEventsWith(new OrderEventHandler());

        //3.实例化Disruptor实例，配置一系列参数，编写Disruptor核心组件
        disruptor.start();

        //4.获取实际存储数据的容器：RingBuffer
        RingBuffer<EventOrder> ringBuffer = disruptor.getRingBuffer();

        OrderEventProducer producer = new OrderEventProducer(ringBuffer);

        ByteBuffer bb = ByteBuffer.allocate(8);
        for (int i = 0; i < 100; i++) {
            bb.putLong(0,i);
            producer.sendDate(bb);
        }

        disruptor.shutdown();
        executor.shutdown();
    }
}
