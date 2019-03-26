package com.zb.sms.gateway.validator.base;

import com.zb.sms.common.cache.SmsMessageConfigCacheManager;
import com.zb.sms.common.constants.TrueFalse;
import com.zb.sms.common.model.SmsMessageConfigDo;
import com.zb.sms.common.model.SmsSystemMessageDo;
import com.zb.sms.gateway.constants.ResultCode;
import com.zb.sms.gateway.context.SmsMessageContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 功能: 短信路由验证器
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2016/12/14 0014 17:03
 * 版本: V1.0
 */
@Component
public class SmsMessageConfigValidator {

    /**
     * 缓存管理器
     */
    @Autowired
    private SmsMessageConfigCacheManager cacheManager;

    /**
     * 上下文校验
     *
     * @param context
     * @return
     */
    public SmsMessageContext validateSendMessage(SmsMessageContext context) {
        cacheManager.loadMemoryCache();
        SmsMessageConfigDo messageConfigDo = cacheManager.getMemoryCacheObject(context.getSystemInfoDo().getId(),
                context.getSystemMessageDo().getSmsCode());
        if (null == messageConfigDo) {
            return context.setResultCode(ResultCode.INVALID_SMS_CODE_ERROR);
        }
        //如果使用本地模板
        if (TrueFalse.TRUE.getValue() == messageConfigDo.getIsLocalTemplate()) {
            if (null == messageConfigDo.getLocalTemplateId()) {
                return context.setResultCode(ResultCode.INVALID_LOCAL_TEMPLATE_ERROR);
            }
        }

        //如果使用远程模板
        if (TrueFalse.TRUE.getValue() == messageConfigDo.getIsRemoteTemplate()) {
            if (StringUtils.isBlank(messageConfigDo.getRemoteTemplateId())) {
                return context.setResultCode(ResultCode.INVALID_REMOTE_TEMPLATE_ERROR);
            }
        }

        //检查短信渠道
        if (null == messageConfigDo.getChannelId()) {
            return context.setResultCode(ResultCode.INVALID_TARGET_CHANNEL_ERROR);
        }


        //检测短信手机号码最大限制
        SmsSystemMessageDo systemMessageDo = context.getSystemMessageDo();
        if (StringUtils.isNotBlank(systemMessageDo.getMobile())) {
            String[] mobiles = systemMessageDo.getMobile().split(",");
            systemMessageDo.setMobileCount(mobiles.length);
            if (null != messageConfigDo.getMobileMaxLimit()) {
                if (messageConfigDo.getMobileMaxLimit() > 0) {
                    //空或0为不限制,否则开始检测
                    if (mobiles.length > messageConfigDo.getMobileMaxLimit()) {
                        return context.setResultCode(ResultCode.MOBILE_COUNT_OVER_LIMIT_ERROR);
                    }
                }
            }
        }

        context.setMessageConfigDo(messageConfigDo);
        return context;
    }

    /**
     * 上下文校验
     *
     * @param context
     * @return
     */
    public SmsMessageContext validateMobileMaxLimit(SmsMessageContext context) {
        cacheManager.loadMemoryCache();
        SmsMessageConfigDo messageConfigDo = cacheManager.getMemoryCacheObject(context.getSystemInfoDo().getId(),
                context.getSystemMessageDo().getSmsCode());
        if (null == messageConfigDo) {
            return context.setResultCode(ResultCode.INVALID_SMS_CODE_ERROR);
        }

        context.setMessageConfigDo(messageConfigDo);
        return context;
    }
}
