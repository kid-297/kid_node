package top.kid297.redisnodes.jedis.service;

import java.util.List;

/**
 * about redis list
 */
public interface RedisListService {

    /**
     * 左边插入
     * @param key
     * @param value
     * @return
     */
    Long lpush(String key, String ...value);

    /**
     * 右边插入
     * @param key
     * @param value
     * @return
     */
    Long rpush(String key, String ...value);

    /**
     * 指定偏移量插入
     * @param key
     * @param offset
     * @param value
     * @return
     */
    String lset(String key, Long offset, String value);

    /**
     * 左边弹出
     * @param key
     * @return
     */
    String lpop(String key);

    /**
     * 右边弹出
     * @param key
     * @return
     */
    String rpop(String key);

    /**
     * 返回列表 key 中，下标为 index 的元素。
     * @param key
     * @param offset
     * @return
     */
    String lindex(String key,Long offset);

    /**
     * 获取偏移量之间的值
     * @param key
     * @param start
     * @param end
     * @return
     */
    List<String> lrange(String key,Long start,Long end);

    /**
     * 根据参数 count 的值，移除列表中与参数 value 相等的元素。
     * count 的值可以是以下几种：
     * count > 0 : 从表头开始向表尾搜索，移除与 value 相等的元素，数量为 count 。
     * count < 0 : 从表尾开始向表头搜索，移除与 value 相等的元素，数量为 count 的绝对值。
     * count = 0 : 移除表中所有与 value 相等的值。
     * @param key
     * @param count
     * @param value
     * @return
     */
    Long lrem(String key,Long count,String value);

    /**
     * 获取列表长度
     * @param key 如果 key 不是列表类型，返回一个错误。
     * @return
     */
    Long llen(String key);

    /**
     * 对一个列表进行修剪(trim)，就是说，让列表只保留指定区间内的元素，不在指定区间之内的元素都将被删除。
     * @param key
     * @param start
     * @param end
     * @return
     */
    String ltrim(String key, Long start, Long end);

}
