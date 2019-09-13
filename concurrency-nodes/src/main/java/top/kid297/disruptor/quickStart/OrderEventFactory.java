package top.kid297.disruptor.quickStart;

import com.lmax.disruptor.EventFactory;

/**
 * @version V1.0
 * @Description:
 * @Auther: ly
 * @Date: 2019/8/16 16:40
 */
public class OrderEventFactory implements EventFactory<EventOrder> {
    @Override
    public EventOrder newInstance() {
        return new EventOrder();
    }
}
