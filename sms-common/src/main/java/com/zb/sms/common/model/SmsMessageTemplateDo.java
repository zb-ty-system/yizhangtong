package com.zb.sms.common.model;

import java.io.Serializable;

/**
 * 功能: 短信模板数据持久类
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2016/12/12 0012 15:19
 * 版本: V1.0
 */
public class SmsMessageTemplateDo extends BaseDo implements Serializable {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = -7793493483562872304L;

    /**
     * 短信模板编码
     */
    private String templateCode;

    /**
     * 短信模板名称
     */
    private String templateName;

    /**
     * 短信模板内容
     */
    private String templateContent;

    /**
     * 短信模板状态
     */
    private Integer status;

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplateContent() {
        return templateContent;
    }

    public void setTemplateContent(String templateContent) {
        this.templateContent = templateContent;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
