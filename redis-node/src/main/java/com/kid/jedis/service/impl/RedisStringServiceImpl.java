package com.kid.jedis.service.impl;

import com.kid.jedis.service.RedisStringService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


/**
 * <b>关于Redis String 类型的一些简单的增删改查</b>
 * <p>该类里尝试两种jedis返回连接池方法：</p>
 * <p>1. jedis.close(); 详细使用方法看set()方法</p>
 * <p>2. 在try()，括号里添加<br>try (Jedis jedis = jedisPool.getResource())，<br>详细使用方法看del()方法。需要jdk1.7+支持</p>
 */
@Service
@Slf4j
public class RedisStringServiceImpl implements RedisStringService {

    @Autowired
    private JedisPool jedisPool;

    @Override
    public boolean set(String key, String value) throws Exception{
        boolean flag = false;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            flag = jedis.set(key, value).equals("OK")?true:false;
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            if (jedis != null) jedis.close();
        }
        log.info("【redis{}数据】类型：{}；key:{}；value:{}","插入","String",key,value);
        return flag;
    }

    @Override
    public boolean set(String key, String value, long activeTime) throws Exception{
        boolean flag = false;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            if (activeTime>0){
                jedis.expire(key, (int) activeTime);
            }
            flag = jedis.set(key, value).equals("OK")?true:false;
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            if (jedis != null) jedis.close();
        }
        log.info("【redis{}数据】类型：{}；key:{}；value:{}","插入","String",key,value);
        return flag;
    }

    @Override
    public boolean del(String key) throws Exception {
        boolean flag = false;
        try (Jedis jedis = jedisPool.getResource()){
            flag = jedis.del(key).equals("OK")?true:false;
        }catch (Exception e){
            e.printStackTrace();
        }
        log.info("【redis{}数据】类型：{}；key:{}","删除","String",key);
        return flag;
    }

    @Override
    public String get(String key) throws Exception {
        String value = null;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            value = jedis.get(key);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(jedis != null) jedis.close();
        }
        return value;
    }

    @Override
    public boolean append(String key, String value) throws Exception {
        try (Jedis jedis = jedisPool.getResource()){
            jedis.append(key,value);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean setbit(String key, long offset, boolean value) {
        try(Jedis jedis = jedisPool.getResource()){
            return jedis.setbit(key,offset,value);
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Long bitcount(String key) throws Exception {
        try(Jedis jedis = jedisPool.getResource()){
            return jedis.bitcount(key);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
