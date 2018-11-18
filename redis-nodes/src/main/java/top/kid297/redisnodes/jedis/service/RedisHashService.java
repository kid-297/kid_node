package top.kid297.redisnodes.jedis.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * about jedis hash demo
 */
public interface RedisHashService {

    /**
     * 插入hash值（域存在则被覆盖）
     * @param key    键
     * @param field  域
     * @param value  值
     * @return  是否插入 true or false
     */
    Long hset(String key,String field,String value);


    /**
     * 插入hash值（域存在则插入不成功）
     * @param key
     * @param field
     * @param value
     * @return
     */
    Long hsetnx(String key,String field,String value);

    /**
     * 获取hash值
     * @param key  键
     * @param field  域
     * @return 值
     */
    String hget(String key,String field);

    /**
     * 获取所有的域和值
     * @param key  键
     * @return 返回所有的域和值
     */
    Map<String, String> hgetAll(String key);

    /**
     * 返回哈希表 key 中的所有域。
     * @param key
     * @return
     */
    Set<String> hkeys(String key);

    /**
     * 返回哈希表 key 中的所有值。
     * @param key
     * @return
     */
    List<String> hvals(String key);

    /**
     * 查看哈希表 key 中，给定域 field 是否存在。
     * @param key
     * @param field
     * @return
     */
    Boolean hexists (String key, String field);

    /**
     * 删除哈希表 key 中的一个或多个指定域，不存在的域将被忽略。
     * @param keys
     * @param field
     * @return
     */
    Long hdel(String keys, String ...field);

}
