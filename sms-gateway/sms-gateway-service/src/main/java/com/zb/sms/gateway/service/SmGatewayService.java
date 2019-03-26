package com.zb.sms.gateway.service;

import com.zb.sms.gateway.dto.*;

/**
 * 功能: 短信服务类
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2016/12/12 0012 15:39
 * 版本: V1.0
 */
public interface SmGatewayService {

    /**
     * 发送短信
     *
     * @param sendMessageRequest 请求对象
     * @return
     */
    SendMessageResponse sendMessage(SendMessageRequest sendMessageRequest, String dynamicCode);

    /**
     * 发送动态验证码短信
     *
     * @param dynamicCodeRequest 请求对象
     * @return
     */
    SendDynamicCodeResponse sendDynamicCode(SendDynamicCodeRequest dynamicCodeRequest);

    /**
     * 获取短信批量发送单次最大手机号数量限制
     *
     * @param getMobileMaxLimitRequest 请求对象
     * @return
     */
    GetMobileMaxLimitResponse getMobileMaxLimit(GetMobileMaxLimitRequest getMobileMaxLimitRequest);

    /**
     * 生成随机码
     *
     * @param dynamicCodeRequest 请求对象
     * @return
     */
    GenerateDynamicCodeResponse generateDynamicCode(GenerateDynamicCodeRequest dynamicCodeRequest);

    /**
     * 校验动态码
     *
     * @param verifyDynamicCodeRequest 请求对象
     * @return
     */
    VerifyDynamicCodeResponse verifyDynamicCode(VerifyDynamicCodeRequest verifyDynamicCodeRequest);
}
