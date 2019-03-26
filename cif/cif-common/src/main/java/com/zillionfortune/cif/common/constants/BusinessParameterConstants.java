package com.zillionfortune.cif.common.constants;
/**
 * 业务参数常亮
 * 
 * @author litaiping
 * @version  BusinessParameterConstants.java, v 0.1 2016-7-29 上午11:45:48
 */
public class BusinessParameterConstants {
    /** 信用卡防套现开关 */
    public final static String CREDIT_CARD_RECHARGE_LIMIT_SWITCH="credit_card_recharge_limit_switch";
    /** web端充值冻结金额下限 */
    public final static String WEB_RECHARGE_FROZEN_AMOUNT_LIMIT="web_recharge_frozen_amount_limit";
    /** 手机端充值冻结金额下限 */
    public final static String CLIENT_RECHARGE_FROZEN_AMOUNT_LIMIT="client_recharge_frozen_amount_limit";
    /** web端充值冻结金额期限 */
    public final static String WEB_RECHARGE_FROZEN_DAYS="web_recharge_frozen_days";
    /** 移到端充值冻结金额期限 */
    public final static String CLIENT_RECHARGE_FROZEN_DAYS="client_recharge_frozen_days";
    /** 充值订单超时天数 */
    public final static String RECHARGE_OVERTIME_DAYS="recharge_overtime_days";
    /** 充值渠道路由 */
    public final static String RECHARGE_CHANNEL_ROUTING_STRATEGY="recharge_channel_routing_strategy";
    /**
     * 业务参数Redis Key前缀
     */
    public static final String BIZ_PARAM_REDIS_KEY_PREFIX = "bizParam:";
}
