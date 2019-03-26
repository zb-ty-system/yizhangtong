package com.zb.sms.gateway.validator.base;

import com.zb.sms.common.model.SmsSystemMessageDo;
import com.zb.sms.gateway.constants.ResultCode;
import com.zb.sms.gateway.context.SmsMessageContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * 功能: 内部系统报文格式验证器
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2016/12/15 0015 13:12
 * 版本: V1.0
 */
@Component
public class SmsSystemMessageValidator {

    /**
     * 上下文校验
     *
     * @param context
     * @return
     */
    public SmsMessageContext validateSendMessage(SmsMessageContext context) {
        SmsSystemMessageDo systemMessageDo = context.getSystemMessageDo();
        if (null == systemMessageDo) {
            return context.setResultCode(ResultCode.REQUEST_MESSSAGE_RESOLVE_ERROR);
        }
        if (StringUtils.isBlank(systemMessageDo.getSysCode())) {
            return context.setResultCode(ResultCode.INVALID_SYSTEM_CODE_ERROR);
        }
        if (StringUtils.isBlank(systemMessageDo.getSmsCode())) {
            return context.setResultCode(ResultCode.INVALID_SMS_CODE_ERROR);
        }
        if (StringUtils.isBlank(systemMessageDo.getMobile())) {
            return context.setResultCode(ResultCode.INVALID_MOBILE_ERROR);
        }
        return context;
    }

    /**
     * 上下文校验
     *
     * @param context
     * @return
     */
    public SmsMessageContext validateMobileMaxLimit(SmsMessageContext context) {
        SmsSystemMessageDo systemMessageDo = context.getSystemMessageDo();
        if (null == systemMessageDo) {
            return context.setResultCode(ResultCode.REQUEST_MESSSAGE_RESOLVE_ERROR);
        }
        if (StringUtils.isBlank(systemMessageDo.getSysCode())) {
            return context.setResultCode(ResultCode.INVALID_SYSTEM_CODE_ERROR);
        }
        if (StringUtils.isBlank(systemMessageDo.getSmsCode())) {
            return context.setResultCode(ResultCode.INVALID_SMS_CODE_ERROR);
        }
        return context;
    }
}
