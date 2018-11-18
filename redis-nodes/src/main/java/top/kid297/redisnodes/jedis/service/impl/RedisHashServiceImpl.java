package top.kid297.redisnodes.jedis.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import top.kid297.redisnodes.jedis.service.RedisHashService;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@Slf4j
public class RedisHashServiceImpl implements RedisHashService {

    @Autowired
    private JedisPool jedisPool;

    @Override
    public Long hset(String key, String field, String value) {
        try(Jedis jedis = jedisPool.getResource()) {
            return jedis.hset(key,field,value);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Long hsetnx(String key, String field, String value) {
        try(Jedis jedis = jedisPool.getResource()) {
            return jedis.hsetnx(key,field,value);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String hget(String key, String field) {
        try(Jedis jedis = jedisPool.getResource()) {
            return jedis.hget(key,field);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Map<String, String> hgetAll(String key) {
        try(Jedis jedis = jedisPool.getResource()) {
            return jedis.hgetAll(key);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Set<String> hkeys(String key) {
        try(Jedis jedis = jedisPool.getResource()) {
            return jedis.hkeys(key);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<String> hvals(String key) {
        try(Jedis jedis = jedisPool.getResource()) {
            return jedis.hvals(key);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean hexists(String key, String field) {
        try(Jedis jedis = jedisPool.getResource()) {
            return jedis.hexists(key,field);
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Long hdel(String keys, String... field) {
        try(Jedis jedis = jedisPool.getResource()) {
            return jedis.hdel(keys,field);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
