package com.zillionfortune.cif.support.util;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.zillionfortune.cif.support.common.Constants;

/**
 * SystemUtil
 * 
 * @author litaiping
 * @version  SystemUtil.java, 2016-6-29 上午9:54:31 litaiping
 */
public class SystemUtil {
    private static Logger LOGGER=(Logger) LoggerFactory.getLogger(SystemUtil.class);
	private static String ip = null;
	private static String machine = null;

	private static void init(){
		InetAddress addr = null;
	    try {
			addr = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		 
		ip=addr.getHostAddress().toString();//获得本机IP 
		machine=addr.getHostName().toString();//获得本机名称 
	}
	/**
	 * 
	 * @return 机器IP
	 */
	public static String getIp(){
		if(ip == null){
			init();
		}
		return ip;
	}
	/**
	 * 
	 * @return 机器编号
	 */
	public static String getMachine(){
		if(machine == null){
			init();
		}
		return machine;
	}
	
	/**
	 * 
	 * @return 当前系统时间 yyyy-MM-dd HH24:MI:SS
	 */
	public static String getSysCurrentDateTime(){
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
		String time = sdf.format(new java.util.Date());  
		return time;
	}
	
	/**
	 * 
	 * @return 当前系统时间 yyyy-MM-dd
	 */
	public static String getSysCurrentDate(){
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);  
		String time = sdf.format(new java.util.Date());  
		return time;
	}
	
	/**
	 * 
	 * @return 当前系统时间 HH24:MI:SS
	 */
	public static String getSysCurrentTime(){
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.TIME_FORMAT);  
		String time = sdf.format(new java.util.Date());  
		return time;
	}
	/**
	 * 获取资源文件对于的属性值
	 * 
	 * @param propertyFileName
	 * @param propertyName
	 * @return
	 */
	public static String loadPropertyValue(String propertyFileName,String propertyName) {
	    if(StringUtils.isEmpty(propertyFileName)||StringUtils.isEmpty(propertyName)){
	        return null;
	    }
        Resource resource = new ClassPathResource(propertyFileName);
        String propertyValue = null;
        try {
            Properties props = PropertiesLoaderUtils.loadProperties(resource);
            propertyValue = props.getProperty(propertyName);
        } catch (IOException e) {
            LOGGER.error("can't load "+propertyName ,e);
        }
        if(StringUtils.isNotEmpty(propertyValue)){
            propertyValue=propertyValue.trim();
        }
        return propertyValue;
    } 
}
