package com.zb.sms.common.mns;

import com.aliyun.mns.client.CloudAccount;
import com.aliyun.mns.client.CloudQueue;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.common.ClientException;
import com.aliyun.mns.common.ServiceException;
import com.aliyun.mns.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Properties;

/**
 * 功能: 阿里云消息队列消费者
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2016/12/19 0019 09:19
 * 版本: V1.0
 */
public class MnsConsumer extends Thread {

    /**
     * 日志器
     */
    private static Logger logger = LoggerFactory.getLogger(MnsConsumer.class);

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
     * 是否运行
     */
    private boolean runnable = true;

    /**
     * 无消息时等待时间,默认5秒
     */
    private int waitSeconds = 5;

    /**
     * 消息处理器类
     */
    private String handlerClass;

    /**
     * 消息处理线程池
     */
    private ThreadPoolTaskExecutor taskExecutor;

    /**
     * 消息处理器类属性
     */
    private Properties properties;

    @Override
    public void run() {
        MNSClient client = null;
        try {
            CloudAccount account = new CloudAccount(this.accessKey, this.secretKey, this.endpoint);
            client = account.getMNSClient();
            CloudQueue queue = client.getQueueRef(this.queueName);
            while (this.runnable) {
                try {
                    Message popMsg = queue.popMessage(this.waitSeconds);
                    if (popMsg != null) {
                        //反射获取消息处理器
                        MnsConsumerHandler handler = (MnsConsumerHandler) Class.forName(this.handlerClass).
                                getConstructors()[0].newInstance(queue, popMsg, properties);
                        taskExecutor.execute(handler);
                    } else {
                        logger.debug("正在监听:" + this.queueName);
                    }
                } catch (ClientException ce) {
                    logger.error("客户端异常,请检查网络设置和DNS有效性", ce);
                } catch (ServiceException se) {
                    logger.error("服务端异常,requestId:" + se.getRequestId(), se);
                    if (se.getErrorCode() != null) {
                        if (se.getErrorCode().equals("QueueNotExist")) {
                            logger.error(this.queueName + "队列不存在");
                        } else if (se.getErrorCode().equals("TimeExpired")) {
                            logger.error("请求过期,请检测本机服务器时间设置");
                        }
                    }
                } catch (Exception ne) {
                    logger.error("阿里云队列消费者出现未知异常", ne);
                }
            }
            logger.info("已停止:" + this.queueName);
        } catch (Exception e) {
            logger.error("连接阿里云MNS服务异常", e);
        } finally {
            if (client != null) {
                client.close();
            }
        }
    }

    /**
     * 启动消费者
     */
    public void startup() {
        this.start();
    }

    /**
     * 停止消费者
     */
    public void shutdown() {
        logger.info("尝试停止" + this.queueName + "队列消费者");
        this.runnable = false;
        //防止出现memory leak
        try {
            Thread.sleep(this.waitSeconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
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

    public int getWaitSeconds() {
        return waitSeconds;
    }

    public void setWaitSeconds(int waitSeconds) {
        this.waitSeconds = waitSeconds;
    }

    public String getHandlerClass() {
        return handlerClass;
    }

    public void setHandlerClass(String handlerClass) {
        this.handlerClass = handlerClass;
    }

    public boolean isRunnable() {
        return runnable;
    }

    public void setRunnable(boolean runnable) {
        this.runnable = runnable;
    }

    public ThreadPoolTaskExecutor getTaskExecutor() {
        return taskExecutor;
    }

    public void setTaskExecutor(ThreadPoolTaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}