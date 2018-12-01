package top.kid297.wechatpayment.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;


public class ConfigUtil {
	
	public static String configContent(String code){
		ApplicationContext context = new FileSystemXmlApplicationContext("classpath:config/applicationContext.xml");
		//TsConfigService configService = (TsConfigService) context.getBean("tsConfigService");
		//String contentString = configService.getConfigContentByCode(code);
		return null;
	}
	
}
