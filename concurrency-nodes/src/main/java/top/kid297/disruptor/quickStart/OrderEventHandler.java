package top.kid297.disruptor.quickStart;


import com.lmax.disruptor.EventHandler;

/**
 * @version V1.0
 * @Description:
 * @Auther: ly
 * @Date: 2019/8/16 16:58
 */
public class OrderEventHandler implements EventHandler<EventOrder> {

    @Override
    public void onEvent(EventOrder eventOrder, long l, boolean b) throws Exception {
        System.out.println("消费者："+ eventOrder.getValue());
    }
}
