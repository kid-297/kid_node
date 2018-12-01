package top.kid297.wechatpayment.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.jdom.JDOMException;
import org.springframework.stereotype.Service;
import top.kid297.wechatpayment.config.WeChatConfig;
import top.kid297.wechatpayment.model.TsOrder;
import top.kid297.wechatpayment.service.WechatService;
import top.kid297.wechatpayment.util.HttpUtil;
import top.kid297.wechatpayment.util.PayCommonUtil;
import top.kid297.wechatpayment.util.XMLUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

@Service
@Slf4j
public class wechatServiceImpl implements WechatService {
    @Override
    public String weChatPayPage(TsOrder order) {
        int price = (int) (order.getPayPrice()*100);
        String order_price = String.valueOf(price); // 价格   注意：价格的单位是分
        String body = order.getMemberType();   // 商品名称
        String out_trade_no = order.getOrderno(); // 订单号

        SortedMap<Object,Object> packageParams = new TreeMap<Object,Object>();
        packageParams.put("appid", WeChatConfig.APP_ID);
        packageParams.put("mch_id", WeChatConfig.MCH_ID);
        packageParams.put("nonce_str", WeChatConfig.nonce_str);     //随机字符串，长度要求在32位以内。推荐随机数生成算法
        packageParams.put("body", body);
        packageParams.put("out_trade_no", out_trade_no);
        packageParams.put("total_fee", order_price);  //价格的单位为分
        packageParams.put("spbill_create_ip", WeChatConfig.CREATE_IP);
        packageParams.put("notify_url", WeChatConfig.NOTIFY_URL);
        packageParams.put("trade_type", WeChatConfig.trade_type);
        //获取签名
        String sign = PayCommonUtil.createSign("UTF-8", packageParams,WeChatConfig.API_KEY);
        packageParams.put("sign", sign);   //签名
        //拼接成xml文件
        String requestXML = PayCommonUtil.getRequestXml(packageParams);
        //调用接口  传递参数
        String resXml = HttpUtil.postData(WeChatConfig.UFDODER_URL, requestXML);
        //解析xml  获取二维码地址
        Map map = new HashMap();
        try {
            map = XMLUtil.doXMLParse(resXml);
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String return_code = (String) map.get("return_code");
        String result_code = (String) map.get("result_code");
        String urlCode = null;
        if(!return_code.isEmpty() && !result_code.isEmpty() && ("SUCCESS").equals(return_code) && ("SUCCESS").equals(result_code)){
            urlCode = (String) map.get("code_url");   //二维码链接地址
        }else{
            log.info("获取二维码失败！");
        }
        return urlCode;
    }

    @Override
    public boolean selectWXOrder(String out_trade_no) {
        boolean rs = false;
        SortedMap<Object,Object> setOrderInfo = new TreeMap<Object,Object>();
        setOrderInfo.put("appid", WeChatConfig.APP_ID);
        setOrderInfo.put("mch_id", WeChatConfig.MCH_ID);
        setOrderInfo.put("out_trade_no", out_trade_no);
        setOrderInfo.put("nonce_str", WeChatConfig.nonce_str);     //随机字符串，长度要求在32位以内。推荐随机数生成算法
        //获取签名
        String sign = PayCommonUtil.createSign("UTF-8", setOrderInfo,WeChatConfig.API_KEY);
        setOrderInfo.put("sign", sign);   //签名
        //拼接成xml文件
        String requestXML = PayCommonUtil.getRequestXml(setOrderInfo);
        //调用接口  传递参数
        String resXml = HttpUtil.postData("https://api.mch.weixin.qq.com/pay/orderquery", requestXML);
        //解析xml  获取二维码地址
        Map map = new HashMap();
        try {
            map = XMLUtil.doXMLParse(resXml);
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String return_code1 = (String) map.get("return_code");
        String trade_state = (String) map.get("trade_state");
        if(("SUCCESS").equals(return_code1) && ("SUCCESS").equals(trade_state)){
            log.info("交易成功");
            rs = true;
        }
        return rs;
    }
}
