package com.kid.jedis.service;


public interface RedisStringService {

    /**
     * 添加key value
     * @param key  键
     * @param value 值
     * @return true/false 添加是否成功
     */
    boolean set(String key,String value) throws Exception;

    /**
     * 添加key value 并且设置存活时间
     * @param key  键
     * @param value 值
     * @param activeTime 秒
     * @return true/false  添加是否成功
     */
    boolean set(String key,String value,long activeTime)throws Exception;

    /**
     * 删除
     * @param key 键
     * @return  删除是否成功
     */
    boolean del(String key) throws Exception;

    /**
     * 获取参数
     * @param key 键
     * @return  获取的参数
     * @throws Exception  异常
     */
    String get(String key) throws Exception;

    /**
     * 根据key判断，如果key存在，则在后追加，如不存在，则创建
     * @param key
     * @param value  键
     * @return true/false 追加是否成功
     * @throws Exception 异常
     */
    boolean append(String key,String value) throws Exception;

    /**
     * 对 key 所储存的字符串值，设置或清除指定偏移量上的位(bit)。
     * @param key     键
     * @param offset offset 参数必须大于或等于 0 ，小于 2^32 (bit 映射被限制在 512 MB 之内)。
     * @param value  true/false 对应 0/1
     * @return 指定偏移量原来储存的位。 默认为false
     * @throws Exception 异常
     */
    boolean setbit(String key ,long offset,boolean value) throws Exception;

    /**
     * 计算给定字符串中，被设置为 1 的比特位的数量。
     * @param key    键
     * @return  被设置为 1 的比特位的数量
     * @throws Exception 异常
     */
    Long bitcount(String key) throws Exception;
}
