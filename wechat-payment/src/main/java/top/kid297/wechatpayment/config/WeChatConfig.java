package top.kid297.wechatpayment.config;



import top.kid297.wechatpayment.util.ConfigUtil;
import top.kid297.wechatpayment.util.PayCommonUtil;

public class WeChatConfig {
	/**
	 * 说明：
	 * 其中APP_ID和APP_SECRET可以在公众平台找着，MCH_ID和API_KEY则在商户平台找到，
	 * 特别是API_KEY要在商户平台设置好，对于“微信扫码支付模式二”（支付与回调）实际只会用到APP_ID、
	 * MCH_ID和API_KEY，其他的都不用。
	 */
	
	//初始化
	public static String APP_ID = ConfigUtil.configContent("009");	//微信公众平台APPID信息
	public static String APP_SECRET = ""; 				//appsecret  开发者密码
	public static String MCH_ID = ConfigUtil.configContent("010"); 					//商户号   微信支付分配的商户号
	public static String API_KEY = ConfigUtil.configContent("011"); 					//key  
	
	//有关url
	public static String UFDODER_URL=ConfigUtil.configContent("012");				//微信开发平台接口地址
	public static String NOTIFY_URL="http://"+ConfigUtil.configContent("004")+"/tservice/enterprise/payfor/toReturnUrlWX.do"; 				//微信支付回调接口    异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数。
	public static String CREATE_IP=ConfigUtil.configContent("013");  				//发起支付IP
	
	//随机数
	public static String currTime = PayCommonUtil.getCurrTime();
	public static String strTime = currTime.substring(8, currTime.length());  
	public static String strRandom = PayCommonUtil.buildRandom(4) + "";  
	public static String nonce_str = strTime + strRandom;     //随机字符串，长度要求在32位以内。推荐随机数生成算法

	/**
	 * 交易类型  JSAPI--公众号支付、NATIVE--原生扫码支付、APP--app支付，统一下单接口trade_type的传参可参考这里  MICROPAY--刷卡支付，刷卡支付有单独的支付接口，不调用统一下单接口
	 */
	public static String trade_type = ConfigUtil.configContent("014");     //交易类型   取值如下：JSAPI，NATIVE，APP等，说明详见参数规定
}
