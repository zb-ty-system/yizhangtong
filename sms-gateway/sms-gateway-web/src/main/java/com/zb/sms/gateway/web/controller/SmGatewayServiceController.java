package com.zb.sms.gateway.web.controller;

import com.zb.sms.gateway.constants.RespCode;
import com.zb.sms.gateway.constants.ResultCode;
import com.zb.sms.gateway.dto.*;
import com.zb.sms.gateway.service.SmGatewayService;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 功能: 提供RESTFUL风格短信接入网关
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2016/12/13 0013 14:26
 * 版本: V1.0
 */
@RestController
@RequestMapping("/gateway")
public class SmGatewayServiceController {

    /**
     * 日志器
     */
    private final Logger logger = LoggerFactory.getLogger(SmGatewayServiceController.class);

    /**
     * 短信发送服务
     */
    @Autowired
    private SmGatewayService smGatewayService;

    /**
     * 发送短信RESTFUL接口
     *
     * @param jsonReqMessage 请求对象JSON字符串
     * @return 响应JSON字符串
     */
    @RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
    public String sendMessage(@RequestBody String jsonReqMessage) {
        logger.info("sendMessage.req:" + jsonReqMessage);

        SendMessageResponse sendMessageResponse;
        if (StringUtils.isBlank(jsonReqMessage)) {
            //请求报文为空,返回错误
            sendMessageResponse = new SendMessageResponse(RespCode.FAIL.getValue(),
                    ResultCode.REQUEST_MESSSAGE_EMPTY_ERROR.getValue(),
                    ResultCode.REQUEST_MESSSAGE_EMPTY_ERROR.getDesc());
        } else {
            try {
                //请求报文不为空,则将JSON字符串转换成对象
                JSONObject jsonReqObject = JSONObject.fromObject(jsonReqMessage);
                SendMessageRequest sendMessageRequest = (SendMessageRequest) JSONObject.toBean(jsonReqObject, SendMessageRequest.class);
                //content字段单独取
                if (jsonReqObject.containsKey("content")) {
                    sendMessageRequest.setContent(jsonReqObject.getString("content"));
                }

                //调用短信发送服务
                sendMessageResponse = smGatewayService.sendMessage(sendMessageRequest, null);
            } catch (JSONException je) {
                logger.error("报文转换异常", je);
                //解析JSON报文异常处理
                sendMessageResponse = new SendMessageResponse(RespCode.FAIL.getValue(),
                        ResultCode.REQUEST_MESSSAGE_RESOLVE_ERROR.getValue(),
                        ResultCode.REQUEST_MESSSAGE_RESOLVE_ERROR.getDesc());
            }
        }

        //将对象转换成JSON字符串
        String jsonRespMessage = JSONObject.fromObject(sendMessageResponse).toString();
        logger.info("sendMessage.resp:" + jsonRespMessage);
        return jsonRespMessage;
    }

    /**
     * 获取短信批量发送单次最大手机号数量限制RESTFUL接口
     *
     * @param jsonReqMessage 请求对象JSON字符串
     * @return 响应JSON字符串
     */
    @RequestMapping(value = "/getMobileMaxLimit", method = RequestMethod.POST)
    public String getMobileMaxLimit(@RequestBody String jsonReqMessage) {
        logger.info("getMobileMaxLimit.req:" + jsonReqMessage);
        GetMobileMaxLimitResponse getMobileMaxLimitResponse;
        if (StringUtils.isBlank(jsonReqMessage)) {
            //请求报文为空,返回错误
            getMobileMaxLimitResponse = new GetMobileMaxLimitResponse(RespCode.FAIL.getValue(),
                    ResultCode.REQUEST_MESSSAGE_EMPTY_ERROR.getValue(),
                    ResultCode.REQUEST_MESSSAGE_EMPTY_ERROR.getDesc());
        } else {
            try {
                //请求报文不为空,则将JSON字符串转换成对象
                JSONObject jsonReqObject = JSONObject.fromObject(jsonReqMessage);
                GetMobileMaxLimitRequest getMobileMaxLimitRequest = (GetMobileMaxLimitRequest) JSONObject.toBean(
                        jsonReqObject, GetMobileMaxLimitRequest.class);

                //调用短信发送服务
                getMobileMaxLimitResponse = smGatewayService.getMobileMaxLimit(getMobileMaxLimitRequest);
            } catch (JSONException je) {
                logger.error("报文转换异常", je);
                //解析JSON报文异常处理
                getMobileMaxLimitResponse = new GetMobileMaxLimitResponse(RespCode.FAIL.getValue(),
                        ResultCode.REQUEST_MESSSAGE_RESOLVE_ERROR.getValue(),
                        ResultCode.REQUEST_MESSSAGE_RESOLVE_ERROR.getDesc());
            }
        }

        //将对象转换成JSON字符串
        String jsonRespMessage = JSONObject.fromObject(getMobileMaxLimitResponse).toString();
        logger.info("getMobileMaxLimit.resp:" + jsonRespMessage);
        return jsonRespMessage;
    }

    /**
     * 生成随机码
     *
     * @param jsonReqMessage 请求对象JSON字符串
     * @return 响应JSON字符串
     */
    //@RequestMapping(value = "/generateDynamicCode", method = RequestMethod.POST)
    public String generateDynamicCode(@RequestBody String jsonReqMessage) {
        logger.info("generateDynamicCode.req:" + jsonReqMessage);
        GenerateDynamicCodeResponse generateDynamicCodeResponse;
        if (StringUtils.isBlank(jsonReqMessage)) {
            //请求报文为空,返回错误
            generateDynamicCodeResponse = new GenerateDynamicCodeResponse(RespCode.FAIL.getValue(),
                    ResultCode.REQUEST_MESSSAGE_EMPTY_ERROR.getValue(),
                    ResultCode.REQUEST_MESSSAGE_EMPTY_ERROR.getDesc());
        } else {
            try {
                //请求报文不为空,则将JSON字符串转换成对象
                JSONObject jsonReqObject = JSONObject.fromObject(jsonReqMessage);
                GenerateDynamicCodeRequest generateDynamicCodeRequest = (GenerateDynamicCodeRequest) JSONObject.toBean(
                        jsonReqObject, GenerateDynamicCodeRequest.class);

                //调用短信发送服务
                generateDynamicCodeResponse = smGatewayService.generateDynamicCode(generateDynamicCodeRequest);
            } catch (JSONException je) {
                logger.error("报文转换异常", je);
                //解析JSON报文异常处理
                generateDynamicCodeResponse = new GenerateDynamicCodeResponse(RespCode.FAIL.getValue(),
                        ResultCode.REQUEST_MESSSAGE_RESOLVE_ERROR.getValue(),
                        ResultCode.REQUEST_MESSSAGE_RESOLVE_ERROR.getDesc());
            }
        }

        //将对象转换成JSON字符串
        String jsonRespMessage = JSONObject.fromObject(generateDynamicCodeResponse).toString();
        logger.info("generateDynamicCode.resp:" + jsonRespMessage);
        return jsonRespMessage;
    }

    /**
     * 发送动态验证码短信
     *
     * @param jsonReqMessage 请求对象JSON字符串
     * @return 响应JSON字符串
     */
    //@RequestMapping(value = "/sendDynamicCode", method = RequestMethod.POST)
    public String sendDynamicCode(@RequestBody String jsonReqMessage) {
        logger.info("sendDynamicCode.req:" + jsonReqMessage);
        SendDynamicCodeResponse sendDynamicCodeResponse;
        if (StringUtils.isBlank(jsonReqMessage)) {
            //请求报文为空,返回错误
            sendDynamicCodeResponse = new SendDynamicCodeResponse(RespCode.FAIL.getValue(),
                    ResultCode.REQUEST_MESSSAGE_EMPTY_ERROR.getValue(),
                    ResultCode.REQUEST_MESSSAGE_EMPTY_ERROR.getDesc());
        } else {
            try {
                //请求报文不为空,则将JSON字符串转换成对象
                JSONObject jsonReqObject = JSONObject.fromObject(jsonReqMessage);
                SendDynamicCodeRequest sendDynamicCodeRequest = (SendDynamicCodeRequest) JSONObject.toBean(
                        jsonReqObject, SendDynamicCodeRequest.class);
                //content字段单独取
                if (jsonReqObject.containsKey("content")) {
                    sendDynamicCodeRequest.setContent(jsonReqObject.getString("content"));
                }

                //调用短信发送服务
                sendDynamicCodeResponse = smGatewayService.sendDynamicCode(sendDynamicCodeRequest);
            } catch (JSONException je) {
                logger.error("报文转换异常", je);
                //解析JSON报文异常处理
                sendDynamicCodeResponse = new SendDynamicCodeResponse(RespCode.FAIL.getValue(),
                        ResultCode.REQUEST_MESSSAGE_RESOLVE_ERROR.getValue(),
                        ResultCode.REQUEST_MESSSAGE_RESOLVE_ERROR.getDesc());
            }
        }

        //将对象转换成JSON字符串
        String jsonRespMessage = JSONObject.fromObject(sendDynamicCodeResponse).toString();
        logger.info("sendDynamicCode.resp:" + jsonRespMessage);
        return jsonRespMessage;
    }

    /**
     * 校验动态码
     *
     * @param jsonReqMessage 请求对象JSON字符串
     * @return 响应JSON字符串
     */
    //@RequestMapping(value = "/verifyDynamicCode", method = RequestMethod.POST)
    public String verifyDynamicCode(@RequestBody String jsonReqMessage) {
        logger.info("verifyDynamicCode.req:" + jsonReqMessage);
        VerifyDynamicCodeResponse verifyDynamicCodeResponse;
        if (StringUtils.isBlank(jsonReqMessage)) {
            //请求报文为空,返回错误
            verifyDynamicCodeResponse = new VerifyDynamicCodeResponse(RespCode.FAIL.getValue(),
                    ResultCode.REQUEST_MESSSAGE_EMPTY_ERROR.getValue(),
                    ResultCode.REQUEST_MESSSAGE_EMPTY_ERROR.getDesc());
        } else {
            try {
                //请求报文不为空,则将JSON字符串转换成对象
                JSONObject jsonReqObject = JSONObject.fromObject(jsonReqMessage);
                VerifyDynamicCodeRequest verifyDynamicCodeRequest = (VerifyDynamicCodeRequest) JSONObject.toBean(
                        jsonReqObject, VerifyDynamicCodeRequest.class);

                //调用短信发送服务
                verifyDynamicCodeResponse = smGatewayService.verifyDynamicCode(verifyDynamicCodeRequest);
            } catch (JSONException je) {
                logger.error("报文转换异常", je);
                //解析JSON报文异常处理
                verifyDynamicCodeResponse = new VerifyDynamicCodeResponse(RespCode.FAIL.getValue(),
                        ResultCode.REQUEST_MESSSAGE_RESOLVE_ERROR.getValue(),
                        ResultCode.REQUEST_MESSSAGE_RESOLVE_ERROR.getDesc());
            }
        }

        //将对象转换成JSON字符串
        String jsonRespMessage = JSONObject.fromObject(verifyDynamicCodeResponse).toString();
        logger.info("verifyDynamicCode.resp:" + jsonRespMessage);
        return jsonRespMessage;
    }
}
