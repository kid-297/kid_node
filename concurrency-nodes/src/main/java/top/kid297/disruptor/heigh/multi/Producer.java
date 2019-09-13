package top.kid297.disruptor.heigh.multi;

import com.lmax.disruptor.RingBuffer;

/**
 * @version V1.0
 * @Description:
 * @Auther: ly
 * @Date: 2019/8/23 13:45
 */
public class Producer {

    private RingBuffer<Order> ringBuffer;

    public Producer(RingBuffer<Order> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }


    public void sendData(String data) {
        long sequence = ringBuffer.next();
        try {
            Order order = ringBuffer.get(sequence);
            order.setId(data);
        }finally {
            ringBuffer.publish(sequence);
        }
    }
}
