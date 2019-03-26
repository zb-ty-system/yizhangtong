package com.zb.sms.gateway.validator;

import com.zb.sms.gateway.context.SmsMessageContext;
import com.zb.sms.gateway.validator.base.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 功能: 发送短信验证器
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2016/12/16 0016 16:09
 * 版本: V1.0
 */
@Component
public class SendMessageValidator {

    /**
     * 报文格式验证器
     */
    @Autowired
    private SmsSystemMessageValidator systemMessageValidator;

    /**
     * 接入系统验证器
     */
    @Autowired
    private SmsSystemInfoValidator systemInfoValidator;

    /**
     * 短信编码验证器
     */
    @Autowired
    private SmsMessageConfigValidator messageConfigValidator;

    /**
     * 短信模板校验器
     */
    @Autowired
    private SmsMessageTemplateValidator messageTemplateValidator;

    /**
     * 目标短信渠道验证器
     */
    @Autowired
    private SmsChannelInfoValidator channelInfoValidator;

    /**
     * 校验上下文
     *
     * @param context 上下文
     * @return
     */
    public SmsMessageContext validate(SmsMessageContext context) {
        if (context.hasError()) return context;

        //校验报文格式
        context = systemMessageValidator.validateSendMessage(context);
        if (context.hasError()) return context;

        //校验接入系统
        context = systemInfoValidator.validate(context);
        if (context.hasError()) return context;

        //校验短信编码
        context = messageConfigValidator.validateSendMessage(context);
        if (context.hasError()) return context;

        //校验短信模板
        context = messageTemplateValidator.validateSendMessage(context);
        if (context.hasError()) return context;

        //校验短信渠道
        context = channelInfoValidator.validateSendMessage(context);
        if (context.hasError()) return context;

        return context;
    }
}
