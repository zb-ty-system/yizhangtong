package com.zb.sms.channel.handler.impl;

import com.aliyun.mns.client.CloudQueue;
import com.aliyun.mns.model.Message;
import com.zb.sms.channel.sender.YiMeiSender;
import com.zb.sms.common.constants.SendStatus;
import com.zb.sms.common.dao.mapper.SmsChannelMessageMapper;
import com.zb.sms.common.mns.MnsConsumerHandler;
import com.zb.sms.common.model.SmsChannelMessageDo;
import com.zb.sms.common.utils.SpringContextUtils;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * 功能: 亿美短信通道消息处理器
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2016/12/19 0019 11:31
 * 版本: V1.0
 */
public class YiMeiMessageHandlerImpl implements MnsConsumerHandler {

    /**
     * 日志器
     */
    private static Logger logger = LoggerFactory.getLogger(YiMeiMessageHandlerImpl.class);

    /**
     * MNS云队列
     */
    private CloudQueue queue;

    /**
     * MNS云队列消息
     */
    private Message message;

    /**
     * 发送链接地址
     */
    private String url;

    /**
     * cdkey
     */
    private String cdkey;

    /**
     * 密码
     */
    private String password;

    /**
     * 重试次数.默认3次
     */
    private int retry = 3;

    /**
     * 重试时间间隔,单位:秒
     */
    private int retrySeconds = 3;

    /**
     * 渠道消息数据访问接口
     */
    private SmsChannelMessageMapper channelMessageMapper;

    /**
     * 构造函数
     *
     * @param queue
     * @param message
     */
    public YiMeiMessageHandlerImpl(CloudQueue queue, Message message, Properties properties) {
        this.queue = queue;
        this.message = message;
        this.url = properties.getProperty("url", "");
        this.cdkey = properties.getProperty("cdkey", "");
        this.password = properties.getProperty("password", "");
        this.retry = Integer.parseInt(properties.getProperty("retry", "3"));
        this.retrySeconds = Integer.parseInt(properties.getProperty("retrySeconds", "3"));
        this.channelMessageMapper = SpringContextUtils.getBean(SmsChannelMessageMapper.class);
    }

    /**
     * 消息处理
     *
     * @param queue
     * @param message
     */
    public void handle(CloudQueue queue, Message message) {
        try {
            logger.info("接收消息:" + message.toString());
            String jsonReqMessage = message.getMessageBodyAsString();
            logger.info("处理消息:" + jsonReqMessage);
            JSONObject jsonReqObject = JSONObject.fromObject(jsonReqMessage);
            SmsChannelMessageDo channelMessageDo = (SmsChannelMessageDo) JSONObject.toBean(jsonReqObject, SmsChannelMessageDo.class);
            //content字段单独取
            if (jsonReqObject.containsKey("content")) {
                channelMessageDo.setContent(jsonReqObject.getString("content"));
            }
            YiMeiSender sender = new YiMeiSender();
            //发送短信息
            if (sender.send(this.url, this.cdkey, this.password, channelMessageDo.getId(),
                    channelMessageDo.getMobile(), channelMessageDo.getSendContent())) {
                channelMessageDo.setStatus(SendStatus.SEND_SUCCESS.getValue());
                safeDelete(message);
            } else {
                channelMessageDo.setStatus(SendStatus.SEND_FAIL.getValue());
                errorDelete(queue, message);
            }
            channelMessageMapper.update(channelMessageDo);
        } catch (Exception e) {
            logger.error("消息处理错误,次数:" + message.getDequeueCount(), e);
            errorDelete(queue, message);
        }
    }

    /**
     * 安全删除队列消息
     *
     * @param message
     */
    private void safeDelete(Message message) {
        try {
            queue.deleteMessage(message.getReceiptHandle());
        } catch (Exception e) {
            logger.error("删除消息异常", e);
        }
    }

    /**
     * 安全删除错误队列消息
     *
     * @param message
     */
    private void errorDelete(CloudQueue queue, Message message) {
        if (message.getDequeueCount() >= this.retry) {
            //超过最大尝试次数则丢弃消息
            logger.error("删除错误" + this.retry + "次消息,MessageId:" + message.getMessageId());
            safeDelete(message);
        } else {
            //设置消息下次被消费时间
            queue.changeMessageVisibility(message.getReceiptHandle(), this.retrySeconds);
        }
    }

    /**
     * 线程启动
     */
    public void run() {
        this.handle(queue, message);
    }

    public CloudQueue getQueue() {
        return queue;
    }

    public void setQueue(CloudQueue queue) {
        this.queue = queue;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCdkey() {
        return cdkey;
    }

    public void setCdkey(String cdkey) {
        this.cdkey = cdkey;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRetry() {
        return retry;
    }

    public void setRetry(int retry) {
        this.retry = retry;
    }

    public int getRetrySeconds() {
        return retrySeconds;
    }

    public void setRetrySeconds(int retrySeconds) {
        this.retrySeconds = retrySeconds;
    }
}
