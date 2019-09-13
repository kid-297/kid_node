package top.kid297.example.atomic;

import lombok.extern.slf4j.Slf4j;
import top.kid297.annoations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @version V1.0
 * @Description: AtomicReference 使用
 * @Auther: ly
 * @Date: 2019/7/2 16:04
 */
@ThreadSafe
@Slf4j
public class AtomicExample4 {

    private static AtomicReference<Integer> count = new AtomicReference<>(0);

    public static void main(String[] args) {
        count.compareAndSet(0,2);
        count.compareAndSet(0,1);
        count.compareAndSet(1,3);
        count.compareAndSet(2,4);
        count.compareAndSet(3,5);
        log.info("count：{}",count);
    }
}
