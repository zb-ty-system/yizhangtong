package com.zb.sms.gateway.validator.base;

import com.zb.sms.common.cache.SmsChannelInfoCacheManager;
import com.zb.sms.common.mns.MnsProducer;
import com.zb.sms.common.model.SmsChannelInfoDo;
import com.zb.sms.common.model.SmsMessageConfigDo;
import com.zb.sms.common.utils.SpringContextUtils;
import com.zb.sms.gateway.constants.ResultCode;
import com.zb.sms.gateway.context.SmsMessageContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 功能: 外部短信渠道验证器
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2016/12/16 0016 13:13
 * 版本: V1.0
 */
@Component
public class SmsChannelInfoValidator {

    /**
     * 缓存管理器
     */
    @Autowired
    private SmsChannelInfoCacheManager cacheManager;

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
        cacheManager.loadMemoryCache();
        SmsChannelInfoDo channelInfoDo = cacheManager.getMemoryCacheObject(messageConfigDo.getChannelId());
        if (null == channelInfoDo) {
            return context.setResultCode(ResultCode.INVALID_TARGET_CHANNEL_ERROR);
        } else {
            if (StringUtils.isBlank(channelInfoDo.getChannelCode())) {
                return context.setResultCode(ResultCode.INVALID_TARGET_CHANNEL_ERROR);
            }
            MnsProducer producer = SpringContextUtils.getBean(channelInfoDo.getChannelCode() + "Producer");
            if (null == producer) {
                return context.setResultCode(ResultCode.INVALID_MNS_QUEUE_PRODUCER_ERROR);
            }
            context.setProducer(producer);
            context.setChannelInfoDo(channelInfoDo);
        }
        return context;
    }
}
