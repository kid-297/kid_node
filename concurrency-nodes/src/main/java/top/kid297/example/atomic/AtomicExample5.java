package top.kid297.example.atomic;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import top.kid297.annoations.ThreadSafe;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @version V1.0
 * @Description: AtomicReferenceFieldUpdater 使用 (原子性的修改)  必须有volatile 且非static修饰
 * @Auther: ly
 * @Date: 2019/7/2 16:04
 */
@ThreadSafe
@Slf4j
public class AtomicExample5 {

    private static AtomicIntegerFieldUpdater<AtomicExample5> updater = AtomicIntegerFieldUpdater.newUpdater(AtomicExample5.class,"count");

    @Getter
    public volatile int count = 100;

    public static void main(String[] args) {
        AtomicExample5 example5 = new AtomicExample5();

        if (updater.compareAndSet(example5,100,120)){
            log.info("update success 1,{}",example5.getCount());
        }

        if (updater.compareAndSet(example5,100,120)){
            log.info("update success 2,{}",example5.getCount());
        }else {
            log.error("update success 2,{}",example5.getCount());
        }
    }
}
