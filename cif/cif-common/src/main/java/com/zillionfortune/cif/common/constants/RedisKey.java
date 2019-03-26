package com.zillionfortune.cif.common.constants;
/**
 * redis key
 * 
 * @author litaiping
 * @version  RedisKey.java, v 0.1 2016-7-29 下午2:44:13
 */
public class RedisKey {
    /** 业务参数配置 */
    public final static String BUSINESS_PARAMETER_CONFIG="business_parameter_config";
    /** 业务参数配置 */
    public final static String SYSTEM_PARAMETER_CONFIG="system_parameter_config";
    /** 充值提现开关 */
    public final static String RECHARGE_WITHDRAWSWITCH="recharge_withdraw_switch";
    /** 充值渠道手续费配置 */
    public final static String RECHARGE_CHANNEL_FEE_CONFIG="recharge_channel_fee_config";
    /** 充值渠道银行当天已充值金额 */
    public final static String CHANNEL_BANK_RECHARGED_AMOUNT="channel_bank_recharged_amount";
    
    /**
     * getIndividualLoginTokenKey:获取个人Token前缀. <br/>
     *
     * @param memberId 会员ID
     * @return
     */
    public final static String getIndividualLoginTokenKey(String memberId){
    	return "individual_token_" + memberId;
    }
    
    /**
     * getIndividualLoginInfoKey:获取个人用户信息前缀. <br/>
     *
     * @param memberId 会员ID
     * @return
     */
    public final static String getIndividualLoginInfoKey(String memberId){
    	return "individual_login_info_" + memberId;
    }
    
    /**
     * getEnterpriseLoginTokenKey:获取企业Token前缀. <br/>
     *
     * @param operatorId 操作员主键ID
     * @return
     */
    public final static String getEnterpriseLoginTokenKey(String operatorId){
    	return "enterprise_token_" + operatorId;
    }
    
    /**
     * getEnterpriseLoginInfoKey:获取企业用户信息前缀. <br/>
     *
     * @param operatorId 操作员主键ID
     * @return
     */
    public final static String getEnterpriseLoginInfoKey(String operatorId){
    	return "enterprise_login_info_" + operatorId;
    }
}
