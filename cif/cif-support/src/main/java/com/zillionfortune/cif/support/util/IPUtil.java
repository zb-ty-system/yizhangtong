package com.zillionfortune.cif.support.util;

import javax.servlet.http.HttpServletRequest;

/**
 * IP工具类
 * 
 * @author litaiping
 * @version  IPUtil.java, v 0.1 2016-7-23 下午3:55:39
 */
public class IPUtil {
    public static String getIpAddr(HttpServletRequest request) {

        String ip = request.getHeader("x-forwarded-for");
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
}
