package com.zb.sms.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 功能: 日期工具类
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2016/12/14 0014 18:06
 * 版本: V1.0
 */
public class DateUtils {

    public static final String DEFAULT_DATA_FORMAT = "yyyy-MM-dd";
    public static final String DEFAULT_TIME_FORMAT = "yyyy-MM-dd hh:mm:ss";
    public static final String DEFAULT_TIMESTAMP_FORMAT = "yyyy-MM-dd hh:mm:ss.SS";
    public static final String DEFAULT_FULL_TIMESTAMP_FORMAT = "yyyyMMddHHmmssSSS";
    private static Logger logger = LoggerFactory.getLogger(DateUtils.class);

    /**
     * 日期格式化
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String format(Date date, String pattern) {
        String s = "";
        try {
            DateFormat sdf = new SimpleDateFormat(pattern);
            s = sdf.format(date);
        } catch (Exception e) {
            logger.error("格式化时间错误", e);
        }
        return s;
    }

    /**
     * 获取当前系统时间
     *
     * @return
     */
    public static Date now() {
        return new Date();
    }
}
