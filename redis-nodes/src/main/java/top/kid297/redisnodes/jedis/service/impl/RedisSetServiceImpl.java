package top.kid297.redisnodes.jedis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import top.kid297.redisnodes.jedis.service.RedisSetService;

import java.util.Set;

@Service
public class RedisSetServiceImpl implements RedisSetService {
    @Autowired
    private JedisPool jedisPool;

    @Override
    public Long sadd(String key, String... values) {
        try(Jedis jedis = jedisPool.getResource()) {
            return jedis.sadd(key,values);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Long scard(String key) {
        try(Jedis jedis = jedisPool.getResource()) {
            return jedis.scard(key);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Set<String> sdiff(String... keys) {
        try(Jedis jedis = jedisPool.getResource()) {
            return jedis.sdiff(keys);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean sismember(String key, String member) {
        try(Jedis jedis = jedisPool.getResource()) {
            return jedis.sismember(key, member);
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Set<String> smembers(String key) {
        try(Jedis jedis = jedisPool.getResource()) {
            return jedis.smembers(key);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
