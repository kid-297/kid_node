package top.kid297.wechatpayment.controller;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import lombok.extern.slf4j.Slf4j;
import org.jdom.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import top.kid297.wechatpayment.config.WeChatConfig;
import top.kid297.wechatpayment.model.TsOrder;
import top.kid297.wechatpayment.service.WechatService;
import top.kid297.wechatpayment.util.*;

/**
 * 支付专用controller
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/payfor")
@Slf4j
public class PayForController {
	@Autowired
	private WechatService wechatService;

	/**
	 * 购买服务支付功能
	 * 先生成未支付订单    之后调用支付接口     
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/purchaseService")
	public ModelAndView purchaseService(HttpServletRequest request) throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		String payHtmlString = new String();
		//点击购买并支付按钮  先生成一个未付款的订单  之后调用支付接口
		TsOrder order = new TsOrder();
		//调用微信支付接口
		String urlString = wechatService.weChatPayPage(order);
		modelAndView.setViewName("WEB-INF/company/pays/wechat_api");
		return modelAndView;
	}

	/**
	 * 生成二维码
	 * Google插件
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/qrcode.do")
	public void qrcode(HttpServletRequest request, HttpServletResponse response,
	    ModelMap modelMap) {
	    try {
	       String text = request.getParameter("urlString");
	       //根据url来生成生成二维码
	       int width = 300; 
	       int height = 300; 
	       //二维码的图片格式 
	       String format = "gif"; 
	       Hashtable hints = new Hashtable(); 
	       //内容所使用编码 
	       hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
	       BitMatrix bitMatrix;
	        try {
	        bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);
	        QRUtil.writeToStream(bitMatrix, format, response.getOutputStream());
	        } catch (WriterException e) {
	        e.printStackTrace();
	        }
	    } catch (Exception e) {
	    	
	    }
	}
	
	
	/**
	 * 微信支付成功后，跳转到指定页面
	 * @param request
	 * @return
	 * @throws
	 * @throws IOException 
	 * @throws JDOMException 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/toReturnUrlWX")
	@ResponseBody
	public JsonResult<Object> toReturnUrlWX(HttpServletRequest request,HttpServletResponse response) throws IOException, JDOMException{
		JsonResult<Object> jsonResult = new JsonResult<Object>();
		//读取参数
        InputStream inputStream ;  
        StringBuffer sb = new StringBuffer();  
        inputStream = request.getInputStream();  
        String s ;  
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));  
        while ((s = in.readLine()) != null){  
            sb.append(s);  
        }  
        in.close();  
        inputStream.close(); 
        //解析xml成map  
        Map<String, String> m = new HashMap<String, String>();  
        m = XMLUtil.doXMLParse(sb.toString());
        //过滤空 设置 TreeMap  
        SortedMap<Object,Object> packageParams = new TreeMap<Object,Object>();        
        Iterator it = m.keySet().iterator();  
        while (it.hasNext()) {  
            String parameter = (String) it.next();  
            String parameterValue = m.get(parameter);  
            String v = "";  
            if(null != parameterValue) {  
                v = parameterValue.trim();  
            }  
            packageParams.put(parameter, v);  
        }  
        // 账号信息  
        String key = WeChatConfig.API_KEY; // key  
        //判断签名是否正确 
        if(PayCommonUtil.isTenpaySign("UTF-8", packageParams,key)) {  
        	//------------------------------  
            //处理业务开始  
            //------------------------------  
            String resXml = "";  
            if("SUCCESS".equals((String)packageParams.get("result_code"))){  
                // 这里是支付成功  
                //////////执行自己的业务逻辑////////////////  
                String mch_id = (String)packageParams.get("mch_id");  
                String openid = (String)packageParams.get("openid");  
                String is_subscribe = (String)packageParams.get("is_subscribe");  
                String out_trade_no = (String)packageParams.get("out_trade_no");  
                String transaction_id = (String)packageParams.get("transaction_id");  
                String total_fee = (String)packageParams.get("total_fee");  
                log.info("mch_id:"+mch_id);
                log.info("openid:"+openid);
                log.info("is_subscribe:"+is_subscribe);
                log.info("out_trade_no:"+out_trade_no);
                log.info("total_fee:"+total_fee);
                //////////执行自己的业务逻辑////////////////
                  
                log.info("支付成功");
                //通知微信.异步确认成功.必写.不然会一直通知后台.八次之后就认为交易失败了.  
                resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"  
                        + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";  
                  jsonResult.setData("success");
            } else {  
                log.info("支付失败,错误信息：" + packageParams.get("err_code"));
                resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"  
                        + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> "; 
                  jsonResult.setData("false");
            }  
            //------------------------------  
            //处理业务完毕  
            //------------------------------  
            BufferedOutputStream out = new BufferedOutputStream(  
                    response.getOutputStream());  
            out.write(resXml.getBytes());  
            out.flush();  
            out.close();  
        } else{  
            log.info("通知签名验证失败");
        }  
        return jsonResult;
	}
	
	/**
	 * 循环查询订单是否支付成功
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/hadPay.do")
	public JsonResult<Object> hadPay(HttpServletRequest request, HttpServletResponse response,
									 ModelMap modelMap) {
		JsonResult<Object> jsonResult = new JsonResult<Object>();
		String out_trade_no = request.getParameter("out_trade_no");
    	boolean rs = wechatService.selectWXOrder(out_trade_no);
    	if(rs){
    	    jsonResult.setData("success");
    	}
		return jsonResult;
	}
}
