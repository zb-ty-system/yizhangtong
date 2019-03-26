package com.zb.sms.common.mns;

import com.aliyun.mns.client.CloudQueue;
import com.aliyun.mns.model.Message;

/**
 * 功能: MNS队列消息处理器
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2016/12/19 0019 10:45
 * 版本: V1.0
 */
public interface MnsConsumerHandler extends Runnable {

    /**
     * 处理MNS队列消息
     *
     * @param queue
     * @param message
     */
    void handle(CloudQueue queue, Message message);
}