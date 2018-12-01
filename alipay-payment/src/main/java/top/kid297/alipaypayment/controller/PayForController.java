package top.kid297.alipaypayment.controller;

import ch.qos.logback.core.joran.spi.XMLUtil;
import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import top.kid297.alipaypayment.config.AlipayConfig;
import top.kid297.alipaypayment.model.TsOrder;
import top.kid297.alipaypayment.service.AlipayService;
import top.kid297.alipaypayment.uitl.JsonResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

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
	private AlipayService alipayService;

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
		//调用支付宝支付接口
		payHtmlString = alipayService.alipayPayPage(order, request);
		//支付订单后，为防止支付宝会跳页面链接不到，先将订单状态置为支付失败   每晚定时核对支付失败的订单
		modelAndView.addObject("html",payHtmlString);
		modelAndView.setViewName("WEB-INF/company/pays/alipayapi");
		return modelAndView;
	}

	/**
	 * 支付宝  跳转异步通知页面路径
	 * @return
	 */
	@RequestMapping("/toNotifyUrl")
	public ModelAndView toNotifyUrl(){
		ModelAndView modelAndView = new ModelAndView("WEB-INF/company/pays/notify_url");
		return modelAndView;
	}
	
	/**
	 * 支付宝  跳转同步通知页面路径  回调商户页面
	 * @return
	 * @throws UnsupportedEncodingException 
	 * @throws AlipayApiException 
	 */
	@RequestMapping("/toReturnUrl")
	public ModelAndView toReturnUrl(HttpServletRequest request) throws UnsupportedEncodingException, AlipayApiException{
		ModelAndView modelAndView = new ModelAndView("redirect:/enterprise/user/toBackHead.do?url=/enterprise/memberPrice/toMemberPrice.do");
		String selectHtmlString = new String();
		//获取支付宝GET过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map<String,String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用
			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}
		boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名
		if(signVerified) {
			//商户订单号
			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
			//支付宝交易号
			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
			//付款金额
			String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");
			//TODO 修改订单状态
		}else {
			selectHtmlString ="验签失败";
		}
		modelAndView.addObject("selectHtmlString",selectHtmlString);
		return modelAndView;
	}
}
