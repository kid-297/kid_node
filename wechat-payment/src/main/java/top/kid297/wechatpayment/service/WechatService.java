package top.kid297.wechatpayment.service;

import top.kid297.wechatpayment.model.TsOrder;

/**
 * 微信支付
 */
public interface WechatService {
    /**
     * 调用微信支付接口
     * @param order
     * @return
     */
    String weChatPayPage(TsOrder order);

    /**
     * 根据微信交易单 查询微信订单是否交易成功
     * @param out_trade_no
     * @return
     */
    boolean selectWXOrder(String out_trade_no);
}
