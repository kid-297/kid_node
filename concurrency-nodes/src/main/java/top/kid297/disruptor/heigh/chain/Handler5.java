package top.kid297.disruptor.heigh.chain;


import com.lmax.disruptor.EventHandler;

/**
 * @version V1.0
 * @Description:
 * @Auther: ly
 * @Date: 2019/8/22 16:55
 */
public class Handler5 implements EventHandler<Trade> {

    @Override
    public void onEvent(Trade trade, long l, boolean b) throws Exception {
        System.out.println("handler 5: GET PRICE :"+ trade.getPrice());
        trade.setPrice(trade.getPrice()+3.00);
    }

}
