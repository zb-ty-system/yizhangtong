package com.zillionfortune.cif.support.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SmsUtil {

	private static Logger logger = LoggerFactory.getLogger(SmsUtil.class);
	public static final int PAY_WARNING = 25;	//支付预警
	
	/**
	 * hexizheng
	 * @param busiId  业务id
	 * @param telephone  多个手机号逗号分隔
	 * @param content
	 */
	public static void sendSMS(final int busiId,final String mobiles,final String content){
        new Thread(new Runnable() {  
            public void run() {  
            		if(mobiles==null||mobiles.equals("")){
            			logger.error("手机列表为空");
        				return;
        			}
        			String[] mobile = mobiles.split(",");
        			for(String tel:mobile){
        				send(busiId,tel, content);
        			}
            }  
        }).start();  
    } 
	
	private static void send(int busiId,String telephone,String content){
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String format = df.format(new Date());
		try {
			//String ret = SMSSendApi.sendSmsNotify(busiId,telephone,content,format,2);
			logger.info(telephone+"短信发送成功:"+content);
			//logger.info("发送短信结果:"+ret);
		} catch (Exception e) {
			logger.error(telephone+"短信发送失败:"+content, e);
		}
	}
}
