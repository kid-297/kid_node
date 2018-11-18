package top.kid297.redisnodes.jedis.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import top.kid297.redisnodes.jedis.service.RedisStringService;

import java.util.List;


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
       return this.set(key,value,0);
    }

    @Override
    public boolean set(String key, String value, long activeTime) throws Exception{
        boolean flag = false;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set(key, value);
            if (activeTime>0){
                jedis.expire(key, (int) activeTime);
            }
            flag = true;
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            if (jedis != null) jedis.close();
        }
        log.info("【redis{}数据】类型：{}；key:{}；value:{}","插入","String",key,value);
        return flag;
    }

    @Override
    public String mset(String... keysvalus) throws Exception {
        try (Jedis jedis = jedisPool.getResource()){
            return jedis.mset(keysvalus);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean del(String key) throws Exception {
        boolean flag = false;
        try (Jedis jedis = jedisPool.getResource()){
            flag = jedis.del(key)>0?true:false;
        }catch (Exception e){
            e.printStackTrace();
        }
        log.info("【redis{}数据】类型：{}；key:{}","删除","String",key);
        return flag;
    }

    @Override
    public String get(String key) throws Exception {
        String value = null;
        try(Jedis jedis = jedisPool.getResource()) {
            value = jedis.get(key);
        }catch (Exception e){
            e.printStackTrace();
        }
        return value;
    }

    @Override
    public String getrange(String key, Integer start, Integer end) throws Exception {
        String value = null;
        try(Jedis jedis = jedisPool.getResource()) {
            value = jedis.getrange(key,start,end);
        }catch (Exception e){
            e.printStackTrace();
        }
        return value;
    }

    @Override
    public String getset(String key, String value) throws Exception {
        try(Jedis jedis = jedisPool.getResource()) {
            return jedis.getSet(key,value);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String msetnx(String... keysvalues) throws Exception {
        try(Jedis jedis = jedisPool.getResource()) {
            return jedis.mset(keysvalues);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public Long setrange(String key, String values, Long start) throws Exception {
        try(Jedis jedis = jedisPool.getResource()) {
            return jedis.setrange(key,start,values);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
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
            return jedis.setbit(key, offset, value);
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean getbit(String key, long offset) throws Exception {
        try(Jedis jedis = jedisPool.getResource()){
            return jedis.getbit(key,offset);
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

    @Override
    public Long incr(String key) throws Exception {
        try(Jedis jedis = jedisPool.getResource()) {
            return incr(key);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Long incrBy(String key, Integer decrement) throws Exception {
        try(Jedis jedis = jedisPool.getResource()) {
            return jedis.incrBy(key,decrement);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Double incrByFloat(String key, double decrement) throws Exception {
        try(Jedis jedis = jedisPool.getResource()){
            return jedis.incrByFloat(key,decrement);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Long decr(String key) throws Exception {
        try(Jedis jedis = jedisPool.getResource()){
            return jedis.decr(key);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Long decrBy(String key, Integer decrement) throws Exception {
        try(Jedis jedis = jedisPool.getResource()) {
            return jedis.decrBy(key,decrement);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<String> mget(String... key) throws Exception {
        try(Jedis jedis = jedisPool.getResource()) {
            return jedis.mget(key);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
