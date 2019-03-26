package com.zb.sms.gateway.validator;

import com.zb.sms.common.model.SmsSystemMessageDo;
import com.zb.sms.gateway.constants.ResultCode;
import com.zb.sms.gateway.context.SmsMessageContext;
import com.zb.sms.gateway.dto.GenerateDynamicCodeRequest;
import com.zb.sms.gateway.dto.VerifyDynamicCodeRequest;
import com.zb.sms.gateway.validator.base.SmsMessageConfigValidator;
import com.zb.sms.gateway.validator.base.SmsSystemInfoValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 功能: 动态码验证器
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2016/12/16 0016 16:14
 * 版本: V1.0
 */
@Component
public class DynamicCodeValidator {

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
    public SmsMessageContext validateGenDynamicCode(SmsMessageContext context, GenerateDynamicCodeRequest dynamicCodeRequest) {
        if (context.hasError()) return context;

        //校验报文格式
        if (StringUtils.isBlank(dynamicCodeRequest.getSysCode())) {
            return context.setResultCode(ResultCode.INVALID_SYSTEM_CODE_ERROR);
        }
        if (StringUtils.isBlank(dynamicCodeRequest.getSequence())) {
            return context.setResultCode(ResultCode.SEQUENCE_EMPTY_ERROR);
        }
        if (dynamicCodeRequest.getCodeLength() <= 0) {
            return context.setResultCode(ResultCode.INVALID_CODE_LENGTH_ERROR);
        }

        SmsSystemMessageDo systemMessageDo = new SmsSystemMessageDo();
        systemMessageDo.setSysCode(dynamicCodeRequest.getSysCode());
        systemMessageDo.setSmsCode(dynamicCodeRequest.getSmsCode());
        context.setSystemMessageDo(systemMessageDo);

        //校验接入系统
        context = systemInfoValidator.validate(context);
        if (context.hasError()) return context;

        //校验短信编码
        context = messageConfigValidator.validateSendMessage(context);
        if (context.hasError()) return context;

        return context;
    }

    /**
     * 校验上下文
     *
     * @param context 上下文
     * @return
     */
    public SmsMessageContext validateVerifyDynamicCode(SmsMessageContext context, VerifyDynamicCodeRequest dynamicCodeRequest) {
        if (context.hasError()) return context;

        //校验报文格式
        if (StringUtils.isBlank(dynamicCodeRequest.getSysCode())) {
            return context.setResultCode(ResultCode.INVALID_SYSTEM_CODE_ERROR);
        }
        if (StringUtils.isBlank(dynamicCodeRequest.getSequence())) {
            return context.setResultCode(ResultCode.SEQUENCE_EMPTY_ERROR);
        }
        if (StringUtils.isBlank(dynamicCodeRequest.getDynamicCode())) {
            return context.setResultCode(ResultCode.INVALID_DYNAMIC_CODE_ERROR);
        }
        return context;
    }
}
