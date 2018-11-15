package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration
public class DemoApplicationTests {

    @Test
    public void contextLoads() {
    }

    /**
     * redis 释放连接测试
     * @param args
     */
    public static void main(String[] args) {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(8);
        config.setMaxIdle(10);
        config.setMaxWaitMillis(10000);
        JedisPool jedisPool = new JedisPool(config, "127.0.0.1", 6379);
        for(int i = 2000; i<3000; i++) {
            try( Jedis jedis = jedisPool.getResource()) {
                jedis.set("user:" + i, jedis.toString());
                System.out.println(i+"===="+jedis);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

}
