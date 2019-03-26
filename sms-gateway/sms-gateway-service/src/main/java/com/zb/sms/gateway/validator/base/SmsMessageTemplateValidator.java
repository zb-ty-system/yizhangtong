package com.zb.sms.gateway.validator.base;

import com.zb.sms.common.cache.SmsMessageTemplateCacheManager;
import com.zb.sms.common.constants.TrueFalse;
import com.zb.sms.common.model.SmsMessageConfigDo;
import com.zb.sms.common.model.SmsMessageTemplateDo;
import com.zb.sms.gateway.constants.ResultCode;
import com.zb.sms.gateway.context.SmsMessageContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 功能: 短信模板验证器
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2016/12/15 0015 12:05
 * 版本: V1.0
 */
@Component
public class SmsMessageTemplateValidator {

    /**
     * 缓存管理器
     */
    @Autowired
    private SmsMessageTemplateCacheManager cacheManager;

    /**
     * 上下文校验
     *
     * @param context
     * @return
     */
    public SmsMessageContext validateSendMessage(SmsMessageContext context) {
        //检测短信路由配置
        SmsMessageConfigDo messageConfigDo = context.getMessageConfigDo();
        if (null == messageConfigDo) {
            return context.setResultCode(ResultCode.INVALID_SMS_CODE_ERROR);
        }
        //如果使用本地模板才检测,否则跳过
        if (TrueFalse.TRUE.getValue() == messageConfigDo.getIsLocalTemplate()) {
            cacheManager.loadMemoryCache();
            SmsMessageTemplateDo messageTemplateDo = cacheManager.getMemoryCacheObject(messageConfigDo.getLocalTemplateId());
            if (null == messageTemplateDo) {
                context.setResultCode(ResultCode.INVALID_LOCAL_TEMPLATE_ERROR);
            } else {
                context.setMessageTemplateDo(messageTemplateDo);
            }
        }
        return context;
    }
}
