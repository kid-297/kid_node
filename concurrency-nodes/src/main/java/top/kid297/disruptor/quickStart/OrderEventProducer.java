package top.kid297.disruptor.quickStart;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * @version V1.0
 * @Description:
 * @Auther: ly
 * @Date: 2019/8/16 18:04
 */
public class OrderEventProducer {

    private RingBuffer<EventOrder> ringBuffer;

    public OrderEventProducer(RingBuffer<EventOrder> ringBuffer){
        this.ringBuffer = ringBuffer;
    }

    public void sendDate(ByteBuffer bb ){
        // 1.在生产者发送消息的时候，首先 需要从我们的ringBuffer里面获取一个可用的序号
        long sequence = ringBuffer.next();
        try {
            // 2.根据这个序号，找到具体的“EventOrder”元素
            EventOrder eventOrder = ringBuffer.get(sequence);
            //3.进行实际的赋值处理
            eventOrder.setValue(bb.getLong(0));
        }finally {
            //4.提交发布操作
            ringBuffer.publish(sequence);
        }

    }
}
