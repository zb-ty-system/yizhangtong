package com.zb.sms.common.model;

import java.io.Serializable;

/**
 * 功能: 全局配置数据持久类
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2016/12/14 0014 11:25
 * 版本: V1.0
 */
public class SmsGlobalConfigDo extends BaseDo implements Serializable {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 941003947535413503L;

    /**
     * 参数分组
     */
    private String paramGroup;

    /**
     * 参数键
     */
    private String paramKey;

    /**
     * 参数值
     */
    private String paramValue;

    /**
     * 参数描述
     */
    private String paramDesc;

    public String getParamGroup() {
        return paramGroup;
    }

    public void setParamGroup(String paramGroup) {
        this.paramGroup = paramGroup;
    }

    public String getParamKey() {
        return paramKey;
    }

    public void setParamKey(String paramKey) {
        this.paramKey = paramKey;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public String getParamDesc() {
        return paramDesc;
    }

    public void setParamDesc(String paramDesc) {
        this.paramDesc = paramDesc;
    }
}
