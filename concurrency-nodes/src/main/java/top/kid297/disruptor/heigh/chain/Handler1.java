package top.kid297.disruptor.heigh.chain;


import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;


public class Handler1 implements EventHandler<Trade>, WorkHandler<Trade> {
    @Override
    public void onEvent(Trade trade, long l, boolean b) throws Exception {
        this.onEvent(trade);
    }

    @Override
    public void onEvent(Trade trade) throws Exception {
        System.out.println("HANDLER 1 : SET NAME");
        trade.setName("H1");
        Thread.sleep(1000);
    }
}
