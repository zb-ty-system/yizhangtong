package com.zb.sms.gateway.validator;

import com.zb.sms.gateway.context.SmsMessageContext;
import com.zb.sms.gateway.validator.base.SmsMessageConfigValidator;
import com.zb.sms.gateway.validator.base.SmsSystemInfoValidator;
import com.zb.sms.gateway.validator.base.SmsSystemMessageValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 功能: 获取短信批量发送单次最大手机号数量限制验证器
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2016/12/16 0016 16:12
 * 版本: V1.0
 */
@Component
public class MobileMaxLimitValidator {

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
     * 校验上下文
     *
     * @param context 上下文
     * @return
     */
    public SmsMessageContext validate(SmsMessageContext context) {
        if (context.hasError()) return context;

        //校验报文格式
        context = systemMessageValidator.validateMobileMaxLimit(context);
        if (context.hasError()) return context;

        //校验接入系统
        context = systemInfoValidator.validate(context);
        if (context.hasError()) return context;

        //校验短信编码
        context = messageConfigValidator.validateMobileMaxLimit(context);
        if (context.hasError()) return context;

        return context;
    }
}
