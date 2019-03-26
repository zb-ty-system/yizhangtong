package com.zb.sms.gateway.facade.impl;

import com.zb.sms.gateway.dto.*;
import com.zb.sms.gateway.facade.SmGatewayServiceFacade;
import com.zb.sms.gateway.service.SmGatewayService;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 功能: 短信服务接口实现类
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2016/12/12 0012 13:47
 * 版本: V1.0
 */
@Service
public class SmGatewayServiceFacadeImpl implements SmGatewayServiceFacade {

    /**
     * 日志器
     */
    private final Logger logger = LoggerFactory.getLogger(SmGatewayServiceFacadeImpl.class);

    /**
     * 短信发送服务
     */
    @Autowired
    private SmGatewayService smGatewayService;

    /**
     * 发送短信
     *
     * @param sendMessageRequest 请求对象
     * @return
     */
    public SendMessageResponse sendMessage(SendMessageRequest sendMessageRequest) {
        logger.info("sendMessage.req:" + JSONObject.fromObject(sendMessageRequest));
        SendMessageResponse sendMessageResponse = smGatewayService.sendMessage(sendMessageRequest, null);
        logger.info("sendMessage.resp:" + JSONObject.fromObject(sendMessageResponse));
        return sendMessageResponse;
    }

    /**
     * 发送动态验证码短信
     *
     * @param dynamicCodeRequest 请求对象
     * @return
     */
    public SendDynamicCodeResponse sendDynamicCode(SendDynamicCodeRequest dynamicCodeRequest) {
        logger.info("sendDynamicCode.req:" + JSONObject.fromObject(dynamicCodeRequest));
        SendDynamicCodeResponse dynamicCodeResponse = smGatewayService.sendDynamicCode(dynamicCodeRequest);
        logger.info("sendDynamicCode.resp:" + JSONObject.fromObject(dynamicCodeResponse));
        return dynamicCodeResponse;
    }

    /**
     * 获取短信批量发送单次最大手机号数量限制
     *
     * @param getMobileMaxLimitRequest 请求对象
     * @return
     */
    public GetMobileMaxLimitResponse getMobileMaxLimit(GetMobileMaxLimitRequest getMobileMaxLimitRequest) {
        logger.info("getMobileMaxLimit.req:" + JSONObject.fromObject(getMobileMaxLimitRequest));
        GetMobileMaxLimitResponse getMobileMaxLimitResponse = smGatewayService.getMobileMaxLimit(getMobileMaxLimitRequest);
        logger.info("getMobileMaxLimit.resp:" + JSONObject.fromObject(getMobileMaxLimitResponse));
        return getMobileMaxLimitResponse;
    }

    /**
     * 生成随机码
     *
     * @param dynamicCodeRequest 请求对象
     * @return
     */
    public GenerateDynamicCodeResponse generateDynamicCode(GenerateDynamicCodeRequest dynamicCodeRequest) {
        logger.info("generateDynamicCode.req:" + JSONObject.fromObject(dynamicCodeRequest));
        GenerateDynamicCodeResponse generateDynamicCodeResponse = smGatewayService.generateDynamicCode(dynamicCodeRequest);
        logger.info("generateDynamicCode.resp:" + JSONObject.fromObject(generateDynamicCodeResponse));
        return generateDynamicCodeResponse;
    }

    /**
     * 校验动态码
     *
     * @param verifyDynamicCodeRequest 请求对象
     * @return
     */
    public VerifyDynamicCodeResponse verifyDynamicCode(VerifyDynamicCodeRequest verifyDynamicCodeRequest) {
        logger.info("verifyDynamicCode.req:" + JSONObject.fromObject(verifyDynamicCodeRequest));
        VerifyDynamicCodeResponse verifyDynamicCodeResponse = smGatewayService.verifyDynamicCode(verifyDynamicCodeRequest);
        logger.info("verifyDynamicCode.resp:" + JSONObject.fromObject(verifyDynamicCodeResponse));
        return verifyDynamicCodeResponse;
    }
}