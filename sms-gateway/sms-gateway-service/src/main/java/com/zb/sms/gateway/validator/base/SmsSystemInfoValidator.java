package com.zb.sms.gateway.validator.base;

import com.zb.sms.common.cache.SmsSystemInfoCacheManager;
import com.zb.sms.common.model.SmsSystemInfoDo;
import com.zb.sms.gateway.constants.ResultCode;
import com.zb.sms.gateway.context.SmsMessageContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 功能: 内部接入系统验证器
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2016/12/13 0013 18:42
 * 版本: V1.0
 */
@Component
public class SmsSystemInfoValidator {

    /**
     * 缓存管理器
     */
    @Autowired
    private SmsSystemInfoCacheManager cacheManager;

    /**
     * 上下文校验
     *
     * @param context
     * @return
     */
    public SmsMessageContext validate(SmsMessageContext context) {
        cacheManager.loadMemoryCache();
        SmsSystemInfoDo systemInfoDo = cacheManager.getMemoryCacheObject(context.getSystemMessageDo().getSysCode());
        if (null == systemInfoDo) {
            context.setResultCode(ResultCode.INVALID_SYSTEM_CODE_ERROR);
        } else {
            context.setSystemInfoDo(systemInfoDo);
        }
        return context;
    }
}
