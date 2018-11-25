package top.kid297.redisnodes.demo.service;

/**
 * 该service模拟高并发查看商品详情时，获取商品库存量
 * 该查询整合缓存进行查询，以缓解数据库查询压力。
 * 同时，该例子在一定程度上解决缓存雪崩和缓存穿透
 * 该方法验证在对应的test单元测试里查找
 */
public interface GoodsService {

    /**
     * 根据商品SKU获取库存量 --》 普通写法
     * 缺点： 如果缓存失效，则其他请求都会去直接请求数据库，大量高并发查询数据库，又可能会使数据库宕掉，即缓存穿透
     * @param goodsSku
     * @return
     * @throws Exception
     */
    Object quertGoodsNum(final String goodsSku) throws Exception;

    /**
     * 根据商品SKU获取库存量 --》 加锁（lock）
     * 优点：可以解决缓存穿透的问题
     * 缺点：
     * 1. 会导致线程阻塞。如果大量并发查询数据，一个人拿到锁，其他人都排队等候，会造成用户体验不好。
     * 2. 锁的颗粒度太粗，若不同的人查询的商品为不同，则会造成查询其他商品的用户也在等待。
     * @param goodsSku
     * @return
     * @throws Exception
     */
    Object quertGoodsNum1(final String goodsSku) throws Exception;

    /**
     * 根据商品SKU获取库存量 --》 缓存降级方案(ConcurrentHashMap)
     * 优点：
     * 1.解决缓存穿透问题
     * 2.细化锁的颗粒度
     * 3.增加缓存降级方案，增加备份缓存，提高用户体验度
     * @param goodsSku
     * @return
     * @throws Exception
     */
    Object quertGoodsNum2(final String goodsSku) throws Exception;
}
