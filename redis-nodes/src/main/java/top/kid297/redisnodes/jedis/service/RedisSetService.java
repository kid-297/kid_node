package top.kid297.redisnodes.jedis.service;

import java.util.List;
import java.util.Set;

/**
 * about redis set
 */
public interface RedisSetService {

    /**
     * 将一个或多个 member 元素加入到集合 key 当中，已经存在于集合的 member 元素将被忽略。
     * @param key
     * @param values
     * @return
     */
    Long sadd(String key,String ...values);

    /**
     * 返回集合 key 的基数(集合中元素的数量)。
     * @param key
     * @return
     */
    Long scard(String key);

    /**
     * 返回一个集合的全部成员，该集合是所有给定集合之间的差集。
     * 不存在的 key 被视为空集。
     * @param keys
     * @return
     */
    Set<String> sdiff(String ...keys);

    /**
     * 判断 member 元素是否集合 key 的成员。
     * @param key
     * @param member
     * @return
     */
    boolean sismember(String key,String member);

    /**
     * 返回集合 key 中的所有成员。
     * @param key
     * @return
     */
    Set<String> smembers(String key);
}
