package top.kid297.alipaypayment.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import org.springframework.stereotype.Service;
import top.kid297.alipaypayment.config.AlipayConfig;
import top.kid297.alipaypayment.model.TsOrder;
import top.kid297.alipaypayment.service.AlipayService;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@Service
public class AlipayServiceImpl implements AlipayService {
    @Override
    public String alipayPayPage(TsOrder order, HttpServletRequest request) throws UnsupportedEncodingException, AlipayApiException {
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = new String(order.getOrderno());
        //付款金额，必填
        String total_amount = new String(order.getPayPrice().toString());
        //订单名称，必填
        String subject = new String(order.getMemberType());
        //商品描述，可空
        String body = new String("");
        //拼接传递参数
        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //请求
        String result = alipayClient.pageExecute(alipayRequest).getBody();
        //输出
        return result;
    }

    @Override
    public boolean selectAlipayOrder(String orderno) throws AlipayApiException {
        boolean rs = false;
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
        //设置请求参数
        AlipayTradeQueryRequest alipayRequest = new AlipayTradeQueryRequest();
        //商户订单号，商户网站订单系统中唯一订单号
        String out_trade_no = new String(orderno);
        //支付宝交易号
        String trade_no = new String("");
        //请二选一设置
        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","+"\"trade_no\":\""+ trade_no +"\"}");
        //请求
        String result = alipayClient.execute(alipayRequest).getBody();

        //获取到支付宝返回的查询信息  解析
        JSONObject jsStrresult = JSONObject.parseObject(result);
        String alipay_trade_query_response= jsStrresult.getString("alipay_trade_query_response");
        //二次解析  解析嵌套的json串
        JSONObject jsStr = JSONObject.parseObject(alipay_trade_query_response);
        String codeString = jsStr.getString("code");
        String msgsString = jsStr.getString("msg");
        if(codeString.equals("10000") && msgsString.equals("Success")){
            rs = true;
        }
        //输出
        return rs;
    }
}
