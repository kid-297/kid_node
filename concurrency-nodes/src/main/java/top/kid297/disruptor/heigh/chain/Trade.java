package top.kid297.disruptor.heigh.chain;

import com.google.common.util.concurrent.AtomicDouble;
import lombok.Data;

/**
 * @version V1.0
 * @Description:
 * @Auther: ly
 * @Date: 2019/8/22 16:27
 */
@Data
public class Trade {

    String id;
    String name;
    double price;
    AtomicDouble count = new AtomicDouble();
}
