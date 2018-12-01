package top.kid297.alipaypayment.service;

import com.alipay.api.AlipayApiException;
import top.kid297.alipaypayment.model.TsOrder;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * 支付宝支付封装
 */
public interface AlipayService {

    /**
     * 调用支付宝支付
     * @param order 订单实体
     * @param request
     * @return
     * @throws UnsupportedEncodingException
     * @throws AlipayApiException  支付宝异常
     */
    String alipayPayPage(TsOrder order, HttpServletRequest request) throws UnsupportedEncodingException, AlipayApiException;

    /**
     * 支付宝支付 --> 根据订单查找支付是否成功
     * @param orderno
     * @return
     * @throws AlipayApiException
     */
    boolean selectAlipayOrder(String orderno) throws AlipayApiException;

}
