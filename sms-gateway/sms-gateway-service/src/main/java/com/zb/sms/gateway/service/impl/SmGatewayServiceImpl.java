package com.zb.sms.gateway.service.impl;

import com.zb.sms.common.dao.mapper.SmsChannelMessageMapper;
import com.zb.sms.common.mns.MnsProducer;
import com.zb.sms.common.model.SmsSystemMessageDo;
import com.zb.sms.common.redis.StringRedisComponent;
import com.zb.sms.common.utils.DynamicCodeUtils;
import com.zb.sms.gateway.constants.RespCode;
import com.zb.sms.gateway.constants.ResultCode;
import com.zb.sms.gateway.context.SmsMessageContext;
import com.zb.sms.gateway.dto.*;
import com.zb.sms.gateway.resolver.SmsMessageTemplateResolver;
import com.zb.sms.gateway.service.SmGatewayService;
import com.zb.sms.gateway.validator.DynamicCodeValidator;
import com.zb.sms.gateway.validator.MobileMaxLimitValidator;
import com.zb.sms.gateway.validator.SendMessageValidator;
import com.zb.sms.gateway.validator.base.*;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 功能: 短信服务实现类
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2016/12/13 0013 16:37
 * 版本: V1.0
 */
@Service
public class SmGatewayServiceImpl implements SmGatewayService {

    /**
     * 日志器
     */
    private final Logger logger = LoggerFactory.getLogger(SmGatewayServiceImpl.class);

    /**
     * 发送短信验证器
     */
    @Autowired
    private SendMessageValidator sendMessageValidator;

    /**
     * 短信最大数量限制验证器
     */
    @Autowired
    private MobileMaxLimitValidator mobileMaxLimitValidator;

    /**
     * 动态码验证器
     */
    @Autowired
    private DynamicCodeValidator dynamicCodeValidator;

    /**
     * 报文模板适配器
     */
    @Autowired
    private SmsMessageTemplateResolver templateResolver;

    /**
     * 渠道消息Mapper
     */
    @Autowired
    private SmsChannelMessageMapper channelMessageMapper;

    /**
     * 字符串缓存访问器
     */
    @Autowired
    private StringRedisComponent stringRedisComponent;

    /**
     * 发送短信
     *
     * @param sendMessageRequest 请求对象
     * @return
     */
    public SendMessageResponse sendMessage(SendMessageRequest sendMessageRequest, String dynamicCode) {
        SmsMessageContext context = new SmsMessageContext();
        try {
            //将请求DTO转换为DataObject
            SmsSystemMessageDo systemMessageDo = new SmsSystemMessageDo();
            PropertyUtils.copyProperties(systemMessageDo, sendMessageRequest);
            //将DataObject放入上下文
            context.setSystemMessageDo(systemMessageDo);
        } catch (Exception e) {
            context.setResultCode(ResultCode.REQUEST_MESSSAGE_RESOLVE_ERROR);
            logger.error("转换SmsSystemMessageDo对象异常", e);
        }

        //校验上下文
        context = sendMessageValidator.validate(context);
        if (context.hasError()) {
            return new SendMessageResponse(RespCode.SUCCESS.getValue(),
                    context.getResultCode().getValue(), context.getResultCode().getDesc());
        }

        //模板适配
        context = templateResolver.resolve(context);
        if (context.hasError()) {
            return new SendMessageResponse(RespCode.SUCCESS.getValue(),
                    context.getResultCode().getValue(), context.getResultCode().getDesc());
        }

        try {
            //保存渠道消息
            channelMessageMapper.insert(context.getChannelMessageDo());
        } catch (Exception e) {
            context.setResultCode(ResultCode.SAVE_CHANNEL_MESSAGE_ERROR);
            logger.error("保存渠道消息至数据库异常", e);
        }
        if (context.hasError()) {
            return new SendMessageResponse(RespCode.SUCCESS.getValue(),
                    context.getResultCode().getValue(), context.getResultCode().getDesc());
        }

        //发送短信至队列
        MnsProducer producer = context.getProducer();
        boolean success = producer.send(JSONObject.fromObject(context.getChannelMessageDo()).toString());
        logger.info(String.format("发送短信%d至队列[%s],结果:%b",
                context.getChannelMessageDo().getId(), producer.getQueueName(), success));

        return new SendMessageResponse(RespCode.SUCCESS.getValue(),
                ResultCode.SUCCESS.getValue(), ResultCode.SUCCESS.getDesc());
    }

    /**
     * 发送动态验证码短信
     *
     * @param dynamicCodeRequest 请求对象
     * @return
     */
    public SendDynamicCodeResponse sendDynamicCode(SendDynamicCodeRequest dynamicCodeRequest) {
        GenerateDynamicCodeRequest generateCodeRequest = new GenerateDynamicCodeRequest();
        try {
            PropertyUtils.copyProperties(generateCodeRequest, dynamicCodeRequest);
        } catch (Exception e) {
            logger.error("报文转换异常", e);
            return new SendDynamicCodeResponse(RespCode.SUCCESS.getValue(),
                    ResultCode.REQUEST_MESSSAGE_RESOLVE_ERROR.getValue(),
                    ResultCode.REQUEST_MESSSAGE_RESOLVE_ERROR.getDesc());
        }
        //生成验证码
        GenerateDynamicCodeResponse generateCodeResponse = this.generateDynamicCode(generateCodeRequest);
        if (ResultCode.SUCCESS.getValue() != generateCodeResponse.getResultCode()) {
            return new SendDynamicCodeResponse(RespCode.SUCCESS.getValue(),
                    generateCodeResponse.getResultCode(), generateCodeResponse.getResultDesc());
        }
        SendMessageRequest sendMessageRequest = new SendMessageRequest();
        try {
            PropertyUtils.copyProperties(sendMessageRequest, dynamicCodeRequest);
        } catch (Exception e) {
            logger.error("报文转换异常", e);
            return new SendDynamicCodeResponse(RespCode.SUCCESS.getValue(),
                    ResultCode.REQUEST_MESSSAGE_RESOLVE_ERROR.getValue(),
                    ResultCode.REQUEST_MESSSAGE_RESOLVE_ERROR.getDesc());
        }
        //发送短信息
        SendMessageResponse sendMessageResponse = this.sendMessage(sendMessageRequest, generateCodeResponse.getDynamicCode());
        if (ResultCode.SUCCESS.getValue() != sendMessageResponse.getResultCode()) {
            return new SendDynamicCodeResponse(RespCode.SUCCESS.getValue(),
                    sendMessageResponse.getResultCode(), sendMessageResponse.getResultDesc());
        } else {
            SendDynamicCodeResponse response = new SendDynamicCodeResponse(RespCode.SUCCESS.getValue(),
                    ResultCode.SUCCESS.getValue(), ResultCode.SUCCESS.getDesc());
            response.setDynamicCode(generateCodeResponse.getDynamicCode());
            return response;
        }
    }

    /**
     * 获取短信批量发送单次最大手机号数量限制
     *
     * @param getMobileMaxLimitRequest 请求对象
     * @return
     */
    public GetMobileMaxLimitResponse getMobileMaxLimit(GetMobileMaxLimitRequest getMobileMaxLimitRequest) {
        SmsMessageContext context = new SmsMessageContext();
        try {
            //将请求DTO转换为DataObject
            SmsSystemMessageDo systemMessageDo = new SmsSystemMessageDo();
            PropertyUtils.copyProperties(systemMessageDo, getMobileMaxLimitRequest);
            //将DataObject放入上下文
            context.setSystemMessageDo(systemMessageDo);
        } catch (Exception e) {
            context.setResultCode(ResultCode.REQUEST_MESSSAGE_RESOLVE_ERROR);
            logger.error("转换SmsSystemMessageDo对象异常", e);
        }

        //校验上下文
        context = mobileMaxLimitValidator.validate(context);
        if (context.hasError()) {
            return new GetMobileMaxLimitResponse(RespCode.SUCCESS.getValue(),
                    context.getResultCode().getValue(), context.getResultCode().getDesc());
        }

        GetMobileMaxLimitResponse response = new GetMobileMaxLimitResponse(RespCode.SUCCESS.getValue(),
                ResultCode.SUCCESS.getValue(), ResultCode.SUCCESS.getDesc());
        response.setMobileMaxLimit(context.getMessageConfigDo().getMobileMaxLimit());
        return response;
    }

    /**
     * 生成随机码
     *
     * @param dynamicCodeRequest 请求对象
     * @return
     */
    public GenerateDynamicCodeResponse generateDynamicCode(GenerateDynamicCodeRequest dynamicCodeRequest) {
        SmsMessageContext context = new SmsMessageContext();

        //校验上下文
        context = dynamicCodeValidator.validateGenDynamicCode(context, dynamicCodeRequest);
        if (context.hasError()) {
            return new GenerateDynamicCodeResponse(RespCode.SUCCESS.getValue(),
                    context.getResultCode().getValue(), context.getResultCode().getDesc());
        }

        //生成动态码
        String dynamicCode = DynamicCodeUtils.generate(dynamicCodeRequest.getCodeLength(), dynamicCodeRequest.isContainsChar());
        if (StringUtils.isBlank(dynamicCode)) {
            return new GenerateDynamicCodeResponse(RespCode.SUCCESS.getValue(),
                    ResultCode.GENERATE_DYNAMIC_CODE_ERROR.getValue(),
                    ResultCode.GENERATE_DYNAMIC_CODE_ERROR.getDesc());
        }

        //放入缓存
        try {
            //stringRedisComponent.setEx(
            //        getDynamicCodeRedisKey(dynamicCodeRequest.getSysCode(), dynamicCodeRequest.getSequence()),
            //        dynamicCode, context.getMessageConfigDo().getDynCodeTimeout());
        } catch (Exception e) {
            logger.error("动态码缓存操作失败", e);
            return new GenerateDynamicCodeResponse(RespCode.SUCCESS.getValue(),
                    ResultCode.GENERATE_DYNAMIC_CODE_ERROR.getValue(),
                    ResultCode.GENERATE_DYNAMIC_CODE_ERROR.getDesc());
        }

        GenerateDynamicCodeResponse dynamicCodeResponse = new GenerateDynamicCodeResponse(RespCode.SUCCESS.getValue(),
                ResultCode.SUCCESS.getValue(), ResultCode.SUCCESS.getDesc());
        dynamicCodeResponse.setDynamicCode(dynamicCode);
        return dynamicCodeResponse;
    }

    /**
     * 获得动态码在redis中保存的key
     *
     * @param sysCode
     * @param sequence
     * @return
     */
    private String getDynamicCodeRedisKey(String sysCode, String sequence) {
        return "DYNAMIC_CODE:" + sysCode + ":" + sysCode + "_" + sequence;
    }

    /**
     * 校验动态码
     *
     * @param verifyDynamicCodeRequest 请求对象
     * @return
     */
    public VerifyDynamicCodeResponse verifyDynamicCode(VerifyDynamicCodeRequest verifyDynamicCodeRequest) {
        SmsMessageContext context = new SmsMessageContext();

        //校验上下文
        context = dynamicCodeValidator.validateVerifyDynamicCode(context, verifyDynamicCodeRequest);
        if (context.hasError()) {
            return new VerifyDynamicCodeResponse(RespCode.SUCCESS.getValue(),
                    context.getResultCode().getValue(), context.getResultCode().getDesc());
        }

        //获取redis中保存的key
        String redisKey = this.getDynamicCodeRedisKey(verifyDynamicCodeRequest.getSysCode(),
                verifyDynamicCodeRequest.getSequence());
        String dynamicCode = stringRedisComponent.get(redisKey);

        //比较结果
        boolean isVerifyed = false;
        if (StringUtils.isNotBlank(dynamicCode) && StringUtils.isNotBlank(verifyDynamicCodeRequest.getDynamicCode())) {
            if (dynamicCode.toLowerCase().trim().equals(verifyDynamicCodeRequest.getDynamicCode().toLowerCase().trim())) {
                isVerifyed = true;
            }
        }

        //删除缓存
        try {
            if (isVerifyed) {
                stringRedisComponent.delete(redisKey);
            }
        } catch (Exception e) {
            logger.error("删除Redis缓存失败", e);
        }

        VerifyDynamicCodeResponse dynamicCodeResponse = new VerifyDynamicCodeResponse(RespCode.SUCCESS.getValue(),
                ResultCode.SUCCESS.getValue(), ResultCode.SUCCESS.getDesc());
        dynamicCodeResponse.setIsVerifyed(isVerifyed);
        return dynamicCodeResponse;
    }
}
