package com.zillionfortune.cif.web.util;


import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.zillionfortune.cif.common.exception.PaycoreException;

/**
 * Created by IntelliJ IDEA.
 * User: zhongjiaren
 * Date: 2013-5-17
 * Time: 20:46:20
 * To change this template use File | Settings | File Templates.
 */
public class IPUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(IPUtil.class);
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        	ip = request.getHeader("x-forwarded-for");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        	ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        	ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        //防止多级代理时返回过个ip。
        if(ip != null && ip.indexOf(",") != -1){
            ip= ip.substring(0,ip.indexOf(","));
        }
        return ip;
    }

    public static String getAllIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("x-forwarded-for");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static void checkRequestIp(String requestIpAddr) throws PaycoreException {

        Properties loaderUtils = new Properties();
        try {
            loaderUtils = PropertiesLoaderUtils.loadProperties(new ClassPathResource("taskip.properties"));
            LOGGER.info("load taskip.properties success");
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        String payServerIp = loaderUtils.getProperty("task.ip");
        LOGGER.info("请求的IP地址为：" + requestIpAddr);
        LOGGER.info("配置文件中的服务器ip地址为:" + payServerIp);
        if (!StringUtils.isEmpty(payServerIp)) {
            if (!requestIpAddr.matches(payServerIp)) {
                throw new PaycoreException("IP不在白名单之内");
            }
            /*if (payServerIp.indexOf(",") < 0) {
                if (!(payServerIp.equals(requestIpAddr))) {
                    LOGGER.error("调用接口的ip地址与配置文件中的服务器ip地址不匹配");
                    throw new DataValidateException("IP不在白名单之内");
                }
            } else {
                String ips[] = payServerIp.split(",");
                boolean flag = false;
                for (int i = 0; i < ips.length; i++) {
                    if (ips[i].equals(requestIpAddr)) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    LOGGER.error("调用接口的ip地址与配置文件中的服务器ip地址不匹配");
                    throw new DataValidateException("IP不在白名单之内");
                }
            }*/
        }
    }
}
