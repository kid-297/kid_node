package top.kid297.disruptor.heigh.chain;


import com.lmax.disruptor.EventHandler;

import java.util.UUID;


public class Handler2 implements EventHandler<Trade> {

    @Override
    public void onEvent(Trade trade, long l, boolean b) throws Exception {
        System.out.println("HANDLER 2 : SET ID");
        trade.setId(UUID.randomUUID().toString());
        Thread.sleep(2000);
    }

}
