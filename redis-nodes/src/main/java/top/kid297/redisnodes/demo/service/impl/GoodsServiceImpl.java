package top.kid297.redisnodes.demo.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import top.kid297.redisnodes.demo.service.DatebaseService;
import top.kid297.redisnodes.demo.service.GoodsService;
import top.kid297.redisnodes.jedis.service.RedisStringService;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
@Slf4j
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private RedisStringService redisStringService;

    @Autowired
    private DatebaseService datebaseService;

    Lock lock = new ReentrantLock();

    //重建缓存标记
    ConcurrentHashMap<String,String> mapLock   = new ConcurrentHashMap<>();

    @Override
    public Object quertGoodsNum(final String goodsSku) throws Exception {
        // 1. 从redis获取余票数量
        String value = redisStringService.get(goodsSku);
        if (value != null){
            log.warn("{}缓存中取到数据=========={}",Thread.currentThread().getName(),value);
            return value;
        }
        //2.缓存中没有，取数据库
        value = datebaseService.queryGoodsNum(goodsSku);
        log.warn("{}数据库中取到数据=========={}",Thread.currentThread().getName(),value);
        //3.将数据同步到缓存 并设置120秒过期时间
        final  String v = value;
        redisStringService.set(goodsSku,v,120);
        return value;
    }

    @Override
    public Object quertGoodsNum1(String goodsSku) throws Exception {
        // 1. 从redis获取余票数量
        String value = redisStringService.get(goodsSku);
        if (value != null){
            log.warn("{}缓存中取到数据=========={}",Thread.currentThread().getName(),value);
            return value;
        }
        lock.lock(); // 争夺一把锁  1000线程中  1个线程抢到锁  999 线程等待
        try {
            //再次查询缓存
            value = redisStringService.get(goodsSku);
            if (value != null){
                log.warn("{}缓存中取到数据=========={}",Thread.currentThread().getName(),value);
                return value;
            }
            //拿到锁的线程，去重建缓存
            //2.缓存中没有，取数据库
            value = datebaseService.queryGoodsNum(goodsSku);
            log.warn("{}数据库中取到数据=========={}",Thread.currentThread().getName(),value);
            //3.将数据同步到缓存 并设置120秒过期时间
            final  String v = value;
            redisStringService.set(goodsSku,v,120);
        }finally {
            lock.unlock();
        }
        return value;
    }

    @Override
    public Object quertGoodsNum2(String goodsSku) throws Exception {
        // 1. 从redis获取余票数量
        String value = redisStringService.get(goodsSku);
        if (value != null){
            log.warn("{}缓存中取到数据=========={}",Thread.currentThread().getName(),value);
            return value;
        }
        boolean lock = false;
        try {
            lock = mapLock.putIfAbsent(goodsSku,"true") == null; // 如果存在数据，则返回，如果不存在，则插入
            if(lock){
                //拿到锁，操作缓存重建
                //2.缓存中没有，取数据库
                value = datebaseService.queryGoodsNum(goodsSku);
                log.warn("{}数据库中取到数据=========={}",Thread.currentThread().getName(),value);
                //3.将数据同步到缓存 并设置120秒过期时间
                final  String v = value;
                redisStringService.set(goodsSku,v,120);
                //4.备份缓存存放数据  备份缓存不设置过期时间

            }else{
                //没拿到锁，缓存降级
                //方案2：查询备份缓存
                value = "";  //该处从备份缓存获取数据
                if(value != null){
                    log.warn("{}降级，备份缓存中取到数据=========={}",Thread.currentThread().getName(),value);
                }else{
                    //方案1 ： 返回固定值
                    value = "0";
                    log.warn("{}降级，返回固定值=========={}",Thread.currentThread().getName(),value);
                }
                return value;
            }
        }finally {
            mapLock.remove(goodsSku);
        }
        return value;
    }
}
