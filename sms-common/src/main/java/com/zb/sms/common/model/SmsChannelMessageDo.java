package com.zb.sms.common.model;

/**
 * 功能: 短信渠道消息数据持久类
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2016/12/12 0012 15:23
 * 版本: V1.0
 */
public class SmsChannelMessageDo extends BaseDo {

    /**
     * 接入系统主键
     */
    private Integer sysId;

    /**
     * 接入系统编码
     */
    private String sysCode;

    /**
     * 短信编码
     */
    private String smsCode;

    /**
     * 短信接收方手机号码
     */
    private String mobile;

    /**
     * 短信接收方手机号数量
     */
    private int mobileCount;

    /**
     * 短信内容
     */
    private String content;

    /**
     * 短信发送内容
     */
    private String sendContent;

    /**
     * 短信发送状态
     */
    private Integer status;

    /**
     * 短信路由配置主键
     */
    private Integer smsConfigId;

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
     * 短信渠道主键
     */
    private Integer channelId;

    public Integer getSysId() {
        return sysId;
    }

    public void setSysId(Integer sysId) {
        this.sysId = sysId;
    }

    public String getSysCode() {
        return sysCode;
    }

    public void setSysCode(String sysCode) {
        this.sysCode = sysCode;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getMobileCount() {
        return mobileCount;
    }

    public void setMobileCount(int mobileCount) {
        this.mobileCount = mobileCount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSendContent() {
        return sendContent;
    }

    public void setSendContent(String sendContent) {
        this.sendContent = sendContent;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSmsConfigId() {
        return smsConfigId;
    }

    public void setSmsConfigId(Integer smsConfigId) {
        this.smsConfigId = smsConfigId;
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

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }
}
