package top.kid297.disruptor.heigh.chain;

import com.lmax.disruptor.EventTranslator;
import com.lmax.disruptor.dsl.Disruptor;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @version V1.0
 * @Description:
 * @Auther: ly
 * @Date: 2019/8/22 16:34
 */
public class ThadePushlisher implements Runnable {
    private Disruptor<Trade> disruptor;
    private CountDownLatch latch;

    private static int num = 1;
    public ThadePushlisher(CountDownLatch latch, Disruptor disruptor) {
        this.disruptor = disruptor;
        this.latch = latch;
    }

    @Override
    public void run() {
        TradeEventTranslator tradeEventTranslator = new TradeEventTranslator();
        for (int i = 0; i < num; i++) {
            disruptor.publishEvent(tradeEventTranslator);
        }
        latch.countDown();
    }
}

class TradeEventTranslator implements EventTranslator<Trade>{

    private Random random = new Random();

    @Override
    public void translateTo(Trade trade, long l) {
        this.generateTrade(trade);
    }

    private void generateTrade(Trade trade) {
        trade.setPrice(random.nextDouble() * 99999);
    }
}