package com.zb.sms.common.model;

import java.io.Serializable;

/**
 * 功能: 接入系统数据持久类
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2016/12/12 0012 14:13
 * 版本: V1.0
 */
public class SmsSystemInfoDo extends BaseDo implements Serializable {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 2223280214381014789L;

    /**
     * 接入系统编码
     */
    private String sysCode;

    /**
     * 接入系统名称
     */
    private String sysName;

    /**
     * 接入系统状态
     */
    private Integer status;

    public String getSysCode() {
        return sysCode;
    }

    public void setSysCode(String sysCode) {
        this.sysCode = sysCode;
    }

    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}