package com.zb.sms.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 功能: Spring Bean工具类
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2016/12/16 0016 09:25
 * 版本: V1.0
 */
@Component
public class SpringContextUtils implements ApplicationContextAware {

    /**
     * Spring 上下文
     */
    private static ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext arg0)
            throws BeansException {
        applicationContext = arg0;
    }

    /**
     * 获取applicationContext对象
     *
     * @return
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 根据bean的id来查找对象
     *
     * @param id
     * @return
     */
    public static <T> T getBean(String id) {
        return (T) applicationContext.getBean(id);
    }

    /**
     * 根据bean的class来查找对象
     *
     * @param c
     * @return
     */
    public static <T> T getBean(Class c) {
        return (T) applicationContext.getBean(c);
    }

    /**
     * 根据bean的id和class来查找对象
     *
     * @param id
     * @return
     */
    public static <T> T getBean(String id, Class c) {
        return (T) applicationContext.getBean(id, c);
    }

    /**
     * 根据bean的class来查找所有的对象(包括子类)
     *
     * @param c
     * @return
     */
    public static Map getBeansByClass(Class c) {
        return applicationContext.getBeansOfType(c);
    }
}
