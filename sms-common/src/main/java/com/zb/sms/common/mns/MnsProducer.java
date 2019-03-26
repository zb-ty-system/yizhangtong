package com.zb.sms.common.mns;

import com.aliyun.mns.client.CloudAccount;
import com.aliyun.mns.client.CloudQueue;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.common.ClientException;
import com.aliyun.mns.common.ServiceException;
import com.aliyun.mns.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 功能: 阿里云消息队列生产者
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2016/12/16 0016 12:08
 * 版本: V1.0
 */
public class MnsProducer {

    /**
     * 日志器
     */
    private static Logger logger = LoggerFactory.getLogger(MnsProducer.class);

    /**
     * AccessKey
     */
    private String accessKey;

    /**
     * SecretKey
     */
    private String secretKey;

    /**
     * MNSEndpoint
     */
    private String endpoint;

    /**
     * 队列名称
     */
    private String queueName;

    /**
     * 同步发送MNS队列消息
     *
     * @param reqMessage
     * @return
     */
    public boolean send(String reqMessage) {
        boolean success = false;
        CloudAccount account = new CloudAccount(this.accessKey, this.secretKey, this.endpoint);
        MNSClient client = account.getMNSClient(); // 在程序中，CloudAccount以及MNSClient单例实现即可，多线程安全
        try {
            CloudQueue queue = client.getQueueRef(this.queueName);
            Message message = new Message();
            message.setMessageBody(reqMessage);
            Message putMsg = queue.putMessage(message);
            logger.debug("Send MNS Message Id:" + putMsg.getMessageId());
            success = true;
        } catch (ClientException ce) {
            logger.error("Something wrong with the network connection between client and MNS service."
                    + "Please check your network and DNS availablity.", ce);
        } catch (ServiceException se) {
            logger.error("MNS exception requestId:" + se.getRequestId(), se);
            if (se.getErrorCode() != null) {
                if (se.getErrorCode().equals("QueueNotExist")) {
                    logger.error("Queue is not exist.Please create before use");
                } else if (se.getErrorCode().equals("TimeExpired")) {
                    logger.error("The request is time expired. Please check your local machine timeclock");
                }
            }
        } catch (Exception e) {
            logger.error("Unknown exception happened!", e);
        } finally {
            client.close();  // 程序退出时，需主动调用client的close方法进行资源释放
        }
        return success;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }
}
