package top.kid297.redisnodes.demo.service;

/**
 * 该service模拟从数据库获取值
 */
public interface DatebaseService {

    /**
     * 模拟数据库获取商品库存量
     * @param goodsSku
     * @return
     */
    String queryGoodsNum(String goodsSku);
}
