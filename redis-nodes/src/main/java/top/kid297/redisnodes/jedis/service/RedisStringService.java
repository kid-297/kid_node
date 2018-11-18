package top.kid297.redisnodes.jedis.service;


import java.util.List;

/**
 * about jedis String  demo
 */
public interface RedisStringService {

    /**
     * 添加key value
     * @param key  键
     * @param value 值
     * @return true/false 添加是否成功
     */
    boolean set(String key, String value) throws Exception;

    /**
     * 添加key value 并且设置存活时间
     * @param key  键
     * @param value 值
     * @param activeTime 秒
     * @return true/false  添加是否成功
     */
    boolean set(String key, String value, long activeTime)throws Exception;

    /**
     * 同时设置一个或多个 key-value 对。
     * @param keysvalus
     * @return
     * @throws Exception
     */
    String mset(String ...keysvalus) throws Exception;

    /**
     * 插入新字符串，获取之前的字符串
     * 当 key 没有旧值时，也即是， key 不存在时，返回 nil 。
     * @param key   键
     * @param value 值
     * @return  旧字符串
     * @throws Exception 异常
     */
    String getset(String key,String value) throws Exception;

    /**
     * 同时设置一个或多个 key-value 对，当且仅当所有给定 key 都不存在。
     * 即使只有一个给定 key 已存在， MSETNX 也会拒绝执行所有给定 key 的设置操作。
     * MSETNX 是原子性的，因此它可以用作设置多个不同 key 表示不同字段(field)的唯一性逻辑对象(unique logic object)，所有字段要么全被设置，要么全不被设置。
     * @param keysvalues
     * @return
     * @throws Exception
     */
    String msetnx(String ...keysvalues) throws Exception;

    /**
     * 用 value 参数覆写(overwrite)给定 key 所储存的字符串值，从偏移量 offset 开始。
     * @param key
     * @param values
     * @param start
     * @return
     * @throws Exception
     */
    Long setrange(String key,String values,Long start) throws Exception;
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
     * 获取字串，指定开始和结束位
     * @param key  键
     * @param start  开始位
     * @param end    结束位
     * @return   获取的值
     */
    String getrange(String key,Integer start,Integer end) throws Exception;

    /**
     * 返回所有(一个或多个)给定 key 的值。
     * 如果给定的 key 里面，有某个 key 不存在，那么这个 key 返回特殊值 nil 。因此，该命令永不失败。
     * @param key
     * @return
     * @throws Exception
     */
    List<String> mget(String ...key ) throws Exception;

    /**
     * 根据key判断，如果key存在，则在后追加，如不存在，则创建
     * @param key
     * @param value  键
     * @return true/false 追加是否成功
     * @throws Exception 异常
     */
    boolean append(String key, String value) throws Exception;

    /**
     * 对 key 所储存的字符串值，设置或清除指定偏移量上的位(bit)。
     * <b>可通过bitset和getset 统计某个商品是否被用户收藏过，其中，key表示商品的sku，offset表示用户的id，而value 则表示用户是否收藏（0 or 1）</b>
     * @param key     键
     * @param offset 偏移量 offset 参数必须大于或等于 0 ，小于 2^32 (bit 映射被限制在 512 MB 之内)。
     * @param value  true/false 对应 0/1
     * @return 指定偏移量原来储存的位。 默认为false
     * @throws Exception 异常
     */
    boolean setbit(String key, long offset, boolean value) throws Exception;

    /**
     * 对 key 所储存的字符串值，获取指定偏移量上的位(bit)。
     * @param key     键
     * @param offset 偏移量
     * @return 字符串值指定偏移量上的位(bit)。对不存在的 key 或者不存在的 offset 进行 GETBIT， 返回 0
     * @throws Exception 异常
     */
    boolean getbit(String key,long offset) throws Exception;

    /**
     * 计算给定字符串中，被设置为 1 的比特位的数量。
     * <b>通过该方法获取某个商品的收藏量，或者统计上线人数</b>
     * @param key    键
     * @return  被设置为 1 的比特位的数量
     * @throws Exception 异常
     */
    Long bitcount(String key) throws Exception;

    /**
     * 将 key 中储存的数字值增一。
     * 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 INCR 操作。
     * 如果值包含错误的类型，或字符串类型的值不能表示为数字，那么返回一个错误。
     * @param key  键
     * @return 当前参数执行后的值
     * @throws Exception 异常
     */
    Long incr(String key) throws Exception;

    /**
     * 将 key 所储存的值加上增量 increment 。
     * 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 INCRBY 命令。
     * 如果值包含错误的类型，或字符串类型的值不能表示为数字，那么返回一个错误。
     * 本操作的值限制在 64 位(bit)有符号数字表示之内。
     * @param key
     * @param
     * @returndecrement
     * @throws Exception
     */
    Long incrBy(String key,Integer decrement ) throws Exception;

    /**
     * 为 key 中所储存的值加上浮点数增量 increment 。
     * 如果 key 不存在，那么 INCRBYFLOAT 会先将 key 的值设为 0 ，再执行加法操作。
     * 如果命令执行成功，那么 key 的值会被更新为（执行加法之后的）新值，并且新值会以字符串的形式返回给调用者。
     * @param key
     * @param decrement
     * @return
     * @throws Exception
     */
    Double incrByFloat(String key,double decrement ) throws Exception;


    /**
     * 将 key 中储存的数字值减一。
     * 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 DECR 操作。
     * 如果值包含错误的类型，或字符串类型的值不能表示为数字，那么返回一个错误。
     * 本操作的值限制在 64 位(bit)有符号数字表示之内。
     * @param key  键
     * @return 当前参数执行后的值
     * @throws Exception 异常
     */
    Long decr(String key) throws Exception;

    /**
     * 将 key 所储存的值减去减量 decrement 。
     * 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 DECRBY 操作。
     * 如果值包含错误的类型，或字符串类型的值不能表示为数字，那么返回一个错误。
     * 本操作的值限制在 64 位(bit)有符号数字表示之内。
     * @param key
     * @param decrement
     * @return
     * @throws Exception
     */
    Long decrBy(String key,Integer decrement ) throws Exception;


}
