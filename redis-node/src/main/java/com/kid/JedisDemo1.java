package com.kid;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisDemo1 {

    @Test
    public void demo1(){
        Jedis jedis = new Jedis("127.0.0.1",6379);
        jedis.set("name","kid");
        String name = jedis.get("name");
        System.out.println(name);
        jedis.close();
    }
    @Test
    public void demo2(){
        //获得连接池的配置对象
        JedisPoolConfig config = new JedisPoolConfig();
        //设置最大连接数
        config.setMaxTotal(30);
        //设置最大空闲数
        config.setMaxIdle(10);

        JedisPool jedisPool = new JedisPool(config,"127.0.0.1",6379);

        Jedis jedis = null;

        try{
            //通过连接池获得连接
            jedis = jedisPool.getResource();
            jedis.set("name","66");
            String name = jedis.get("name");
            System.out.println(name);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(jedis != null){
                jedis.close();
            }
            if (jedisPool != null){
                jedisPool.close();
            }
        }
    }
}

