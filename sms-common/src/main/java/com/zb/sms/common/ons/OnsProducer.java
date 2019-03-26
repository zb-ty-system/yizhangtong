package com.zb.sms.common.ons;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.Producer;
import com.aliyun.openservices.ons.api.SendResult;
import com.aliyun.openservices.ons.api.exception.ONSClientException;

import java.util.Properties;

/**
 * 功能: 阿里云消息队列生产者
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2016/12/16 0016 09:04
 * 版本: V1.0
 */
public class OnsProducer {

    /**
     * 生产者
     */
    private Producer producer;

    /**
     * 生产者配置
     */
    private Properties properties;

    /**
     * Message Topic
     */
    private String topic;

    /**
     * Message Tag
     */
    private String tag;

    /**
     * 发送消息
     *
     * @param message
     * @return
     */
    public SendResult send(String message) {
        Message msg = new Message(this.topic, this.tag, message.getBytes());
        return this.producer.send(msg);
    }

    /**
     * 启动生产者
     */
    public void start() {
        if (null == this.properties) {
            throw new ONSClientException("properties not set");
        } else {
            this.producer = ONSFactory.createProducer(this.properties);
            this.producer.start();
        }
    }

    /**
     * 停止生产者
     */
    public void shutdown() {
        if (null != producer) {
            producer.shutdown();
        }
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
