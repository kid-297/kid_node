package top.kid297.redisnodes.jedis.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import top.kid297.redisnodes.jedis.service.RedisListService;

import java.util.List;

@Slf4j
@Service
public class RedisListServiceImpl implements RedisListService {
    @Autowired
    private JedisPool jedisPool;

    @Override
    public Long lpush(String key, String ...value) {
        try(Jedis jedis = jedisPool.getResource()) {
            return jedis.lpush(key,value);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Long rpush(String key, String ...value) {
        try(Jedis jedis = jedisPool.getResource()) {
            return jedis.rpush(key,value);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String lset(String key, Long offset, String value) {
        try(Jedis jedis = jedisPool.getResource()) {
            return jedis.lset(key,offset,value);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String lpop(String key) {
        try(Jedis jedis = jedisPool.getResource()) {
            return jedis.lpop(key);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String rpop(String key) {
        try(Jedis jedis = jedisPool.getResource()) {
            return jedis.rpop(key);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String lindex(String key, Long offset) {
        try(Jedis jedis = jedisPool.getResource()) {
            return jedis.lindex(key,offset);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<String> lrange(String key, Long start, Long end) {
        try(Jedis jedis = jedisPool.getResource()) {
            return jedis.lrange(key,start,end);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Long lrem(String key, Long count, String value) {
        try(Jedis jedis = jedisPool.getResource()) {
            return jedis.lrem(key,count,value);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Long llen(String key) {
        try(Jedis jedis = jedisPool.getResource()) {
            return jedis.llen(key);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String ltrim(String key, Long start, Long end) {
        try(Jedis jedis = jedisPool.getResource()) {
            return jedis.ltrim(key,start,end);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
