package com.zb.sms.common.model;

import java.io.Serializable;

/**
 * 功能: 短信路由配置数据持久类
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2016/12/12 0012 15:08
 * 版本: V1.0
 */
public class SmsMessageConfigDo extends BaseDo implements Serializable {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = -4213997668615582536L;

    /**
     * 接入系统主键
     */
    private Integer sysId;

    /**
     * 短信编码
     */
    private String smsCode;

    /**
     * 接入系统状态
     */
    private Integer status;

    /**
     * 短信类型
     */
    private Integer smsType;

    /**
     * 是否使用本地模板
     */
    private Integer isLocalTemplate;

    /**
     * 本地模板ID
     */
    private Integer localTemplateId;

    /**
     * 是否使用远程模板
     */
    private Integer isRemoteTemplate;

    /**
     * 远程模板ID
     */
    private String remoteTemplateId;

    /**
     * 是否根据系统配置使用默认渠道
     */
    private Integer isDefaultChannel;

    /**
     * 短信渠道主键,is_default_channel=0时有效
     */
    private Integer channelId;

    /**
     * 短信接收号码最大限制
     */
    private Integer mobileMaxLimit;

    public Integer getSysId() {
        return sysId;
    }

    public void setSysId(Integer sysId) {
        this.sysId = sysId;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSmsType() {
        return smsType;
    }

    public void setSmsType(Integer smsType) {
        this.smsType = smsType;
    }

    public Integer getIsLocalTemplate() {
        return isLocalTemplate;
    }

    public void setIsLocalTemplate(Integer isLocalTemplate) {
        this.isLocalTemplate = isLocalTemplate;
    }

    public Integer getLocalTemplateId() {
        return localTemplateId;
    }

    public void setLocalTemplateId(Integer localTemplateId) {
        this.localTemplateId = localTemplateId;
    }

    public Integer getIsRemoteTemplate() {
        return isRemoteTemplate;
    }

    public void setIsRemoteTemplate(Integer isRemoteTemplate) {
        this.isRemoteTemplate = isRemoteTemplate;
    }

    public String getRemoteTemplateId() {
        return remoteTemplateId;
    }

    public void setRemoteTemplateId(String remoteTemplateId) {
        this.remoteTemplateId = remoteTemplateId;
    }

    public Integer getIsDefaultChannel() {
        return isDefaultChannel;
    }

    public void setIsDefaultChannel(Integer isDefaultChannel) {
        this.isDefaultChannel = isDefaultChannel;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public Integer getMobileMaxLimit() {
        return mobileMaxLimit;
    }

    public void setMobileMaxLimit(Integer mobileMaxLimit) {
        this.mobileMaxLimit = mobileMaxLimit;
    }
}
