package com.zb.sms.gateway.context;

import com.zb.sms.common.mns.MnsProducer;
import com.zb.sms.common.model.*;
import com.zb.sms.gateway.constants.ResultCode;

import java.io.Serializable;

/**
 * 功能: 发送短信上下文
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2016/12/14 0014 15:29
 * 版本: V1.0
 */
public class SmsMessageContext implements Serializable {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 8649511770434833727L;

    /**
     * 业务处理结果
     */
    private ResultCode resultCode;

    /**
     * 内部系统消息
     */
    private SmsSystemMessageDo systemMessageDo;

    /**
     * 内部系统信息
     */
    private SmsSystemInfoDo systemInfoDo;

    /**
     * 消息路由信息
     */
    private SmsMessageConfigDo messageConfigDo;

    /**
     * 短信模板信息
     */
    private SmsMessageTemplateDo messageTemplateDo;

    /**
     * 目标短信渠道信息
     */
    private SmsChannelInfoDo channelInfoDo;

    /**
     * 渠道消息信息
     */
    private SmsChannelMessageDo channelMessageDo;

    /**
     * MNS队列消息生产者
     */
    private MnsProducer producer;

    /**
     * 判断是否有异常
     *
     * @return
     */
    public boolean hasError() {
        if (this.resultCode != null) {
            return this.resultCode.hasError();
        }
        return false;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }

    public SmsMessageContext setResultCode(ResultCode resultCode) {
        this.resultCode = resultCode;
        return this;
    }

    public SmsSystemMessageDo getSystemMessageDo() {
        return systemMessageDo;
    }

    public void setSystemMessageDo(SmsSystemMessageDo systemMessageDo) {
        this.systemMessageDo = systemMessageDo;
    }

    public SmsSystemInfoDo getSystemInfoDo() {
        return systemInfoDo;
    }

    public void setSystemInfoDo(SmsSystemInfoDo systemInfoDo) {
        this.systemInfoDo = systemInfoDo;
    }

    public SmsMessageConfigDo getMessageConfigDo() {
        return messageConfigDo;
    }

    public void setMessageConfigDo(SmsMessageConfigDo messageConfigDo) {
        this.messageConfigDo = messageConfigDo;
    }

    public SmsMessageTemplateDo getMessageTemplateDo() {
        return messageTemplateDo;
    }

    public void setMessageTemplateDo(SmsMessageTemplateDo messageTemplateDo) {
        this.messageTemplateDo = messageTemplateDo;
    }

    public SmsChannelInfoDo getChannelInfoDo() {
        return channelInfoDo;
    }

    public void setChannelInfoDo(SmsChannelInfoDo channelInfoDo) {
        this.channelInfoDo = channelInfoDo;
    }

    public SmsChannelMessageDo getChannelMessageDo() {
        return channelMessageDo;
    }

    public void setChannelMessageDo(SmsChannelMessageDo channelMessageDo) {
        this.channelMessageDo = channelMessageDo;
    }

    public MnsProducer getProducer() {
        return producer;
    }

    public void setProducer(MnsProducer producer) {
        this.producer = producer;
    }
}
