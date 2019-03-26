package com.zb.sms.common.model;

import java.io.Serializable;

/**
 * 功能: 短信渠道数据持久类
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2016/12/14 0014 14:05
 * 版本: V1.0
 */
public class SmsChannelInfoDo extends BaseDo implements Serializable {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 4217114220161467609L;

    /**
     * 短信渠道编码
     */
    private String channelCode;

    /**
     * 短信渠道名称
     */
    private String channelName;

    /**
     * 短信渠道状态
     */
    private Integer status;

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
