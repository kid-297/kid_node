package com.kid.jedis.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Component
@Slf4j
public class RedisConfiguration  {

    @Bean(name= "jedis.pool")
    @Autowired
    public JedisPool jedisPool(@Qualifier("jedis.pool.config") JedisPoolConfig config,
                               @Value("${jedis.pool.host}")String host,
                               @Value("${jedis.pool.port}")int port) {
        log.info("====================redis初始化加载=======================");
        return new JedisPool(config, host, port);
    }


    @Bean(name= "jedis.pool.config")
    public JedisPoolConfig jedisPoolConfig (@Value("${jedis.pool.config.maxTotal}")int maxTotal,
                                            @Value("${jedis.pool.config.maxIdle}")int maxIdle,
                                            @Value("${jedis.pool.config.maxWaitMillis}")int maxWaitMillis) {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxIdle);
        config.setMaxWaitMillis(maxWaitMillis);
        return config;
    }

    /**
     *
     * <b>redisPool资源释放</b>
     * <p color='red'>经测试，不论之后是否还会有jedis连接，会彻底关闭掉连接</p>
     * <p>socket关闭有2个close，shutdown。他们之间的区别:</p>
     * <p>close-----关闭本进程的socket id，但链接还是开着的，用这个socket id的其它进程还能用这个链接，能读或写这个socket id</p>
     * <p>shutdown--则破坏了socket 链接，读的时候可能侦探到EOF结束符，写的时候可能会收到一个SIGPIPE信号，这个信号可能直到</p>
     * <p>socket buffer被填充了才收到，shutdown还有一个关闭方式的参数，0 不能再读，1不能再写，2 读写都不能。</p>
     * @param jedis
     */
    public static void closeJedisPool(Jedis jedis) {
        if(jedis != null){
            jedis.close();
            if (jedis.isConnected()) {
                try {
                    log.info("退出{}:{}",jedis.toString(),jedis.quit());
                    jedis.disconnect();
                } catch (Exception e) {
                    log.error("退出失败");
                    e.printStackTrace();
                }
            }
            jedis.close();
        }
    }
}
