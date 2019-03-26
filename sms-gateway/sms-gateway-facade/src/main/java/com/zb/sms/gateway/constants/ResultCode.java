package com.zb.sms.gateway.constants;

/**
 * 功能: 业务响应码
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2016/12/12 0012 09:37
 * 版本: V1.0
 */
public enum ResultCode {

    SUCCESS(0000, "处理成功"),

    REQUEST_MESSSAGE_EMPTY_ERROR(9000, "请求报文为空"),

    REQUEST_MESSSAGE_RESOLVE_ERROR(9001, "请求报文解析错误"),

    INVALID_SYSTEM_CODE_ERROR(9002, "接入系统编码错误"),

    INVALID_SMS_CODE_ERROR(9003, "短信编码错误"),

    INVALID_MOBILE_ERROR(9004, "短信接收方手机号错误"),

    INVALID_MESSAGE_CONTENT_ERROR(9005, "短信内容错误"),

    //INVALID_MOBILE_COUNT_ERROR(9006, "短信接收方手机号数量错误"),

    MOBILE_COUNT_OVER_LIMIT_ERROR(9007, "短信接收方手机号数量超出最大设置"),

    INVALID_LOCAL_TEMPLATE_ERROR(9008, "本地短信模板配置错误"),

    INVALID_REMOTE_TEMPLATE_ERROR(9009, "远程短信模板配置错误"),

    INVALID_TARGET_CHANNEL_ERROR(9010, "目标短信渠道配置错误"),

    RESOLVE_TEMPLATE_ERROR(9011, "组装模板报文错误"),

    SAVE_CHANNEL_MESSAGE_ERROR(9012, "保存短信消息体异常"),

    INVALID_MNS_QUEUE_PRODUCER_ERROR(9013, "目标渠道队列生产者配置错误"),

    SEQUENCE_EMPTY_ERROR(9014, "空流水号错误"),

    INVALID_CODE_LENGTH_ERROR(9015, "验证码码长度错误"),

    GENERATE_DYNAMIC_CODE_ERROR(9016, "生成动态码错误"),

    INVALID_DYNAMIC_CODE_ERROR(9017, "验证码错误");

    /**
     * 键值
     */
    private int value;

    /**
     * 描述
     */
    private String desc;

    /**
     * 构造函数
     *
     * @param value 键值
     * @param desc  描述
     */
    ResultCode(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    /**
     * 是否有错误异常
     *
     * @return
     */
    public boolean hasError() {
        return (this.value != 200 && this.value != 0);
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
