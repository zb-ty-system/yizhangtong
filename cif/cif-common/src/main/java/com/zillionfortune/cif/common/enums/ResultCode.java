package com.zillionfortune.cif.common.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @author zhengrunlong
 * @date 2016/11/10
 */
public enum ResultCode {
	
	/** 0000: 业务处理成功 **/
	SUCCESS("0000","业务处理成功"),
	
	/** 9999: 业务处理失败 **/
	FAIL("9999","业务处理失败"),
	
	/** CUSTOMER_NOT_FOUND: 客户信息不存在 **/
	CUSTOMER_NOT_FOUND("9000","客户信息不存在 "),
	
	/** OPERATOR_NOT_FOUND: 操作员不存在 **/
	OPERATOR_NOT_FOUND("9013","操作员不存在"),
	
	/** USER_NOT_FOUND: 会员不存在 **/
	USER_NOT_FOUND("9001","会员不存在"),
	
	/** CAN_NOT_FROZEN: 会员状态为【正常】以外的场合，不能做【冻结处理】 **/
	CAN_NOT_FROZEN("9002","会员状态为【正常】以外的场合，不能做【冻结处理】"),
	
	/** CAN_NOT_UNFREEZE: 会员状态为【冻结】以外的场合，不能做【解冻处理】 **/
	CAN_NOT_UNFREEZE("9003","会员状态为【冻结】以外的场合，不能做【解冻处理】"),
	
	/** CAN_NOT_CANCEL: 会员状态为【正常/冻结】以外的场合，不能做【注销处理】 **/
	CAN_NOT_CANCEL("9004","会员状态为【正常/冻结】以外的场合，不能做【注销处理】"),
	
	/** USERNAME_OR_PASSWORD_ERROR: 用户名或密码错误 **/
	USERNAME_OR_PASSWORD_ERROR("9005", "用户名或密码错误"),
	
    /** USER_NOT_LOGIN: 用户未登录 **/
    USER_NOT_LOGIN("9006", "用户未登录"),
    
    /** CAN_NOT_LOGIN_MEMBER: 会员状态为【正常】以外的场合，不能做【登入处理】 **/
	CAN_NOT_LOGIN_MEMBER("9007","会员状态为【正常】以外的场合，不能做【登入处理】"),
	
	/** TOKEN_NOT_FOUND: 登入令牌失效 **/
	TOKEN_NOT_FOUND("9008","登入令牌失效"),
	
	/** TOKEN_ERROR: 登入令牌错误 **/
	TOKEN_ERROR("9009","登入令牌错误"),
	
	/** TOKEN_OR_OPERATORID_ERROR: 令牌（token）或操作员主键（operatorId）错误 **/
	TOKEN_OR_OPERATORID_ERROR("9010","令牌或操作员主键错误"),
	
	/** CUSTOMERNO_OR_LOGINNAME_ERROR: 商户号（customerNo）或用户名（username）错误 **/
	CUSTOMERNO_OR_USERNAME_ERROR("9011","商户号或用户名错误"),
	
	/** MEMBERID_NO_EXISTS: 会员ID（memberId）不存在 **/
	MEMBERID_NO_EXISTS("9012","会员ID不存在"),
	
	/** MEMBERID_ERROR: 会员ID（memberId）错误 **/
	MEMBERID_ERROR("9013","会员ID错误"),
	
	/** OPERATERID_ERROR: 操作员主键（operatorID）错误 **/
	OPERATERID_ERROR("9014","操作员主键错误"),
	
	/** CAN_NOT_LOGIN_OPERATOR: 操作员状态为【正常】以外的场合，不能做【登入处理】 **/
	CAN_NOT_LOGIN_OPERATOR("9015","操作员状态为【正常】以外的场合，不能做【登入处理】"),
	
	/** CAN_NOT_AUTH_MEMBER: 会员状态为【正常】以外的场合，不能做【登入鉴权处理】 **/
	CAN_NOT_AUTH_MEMBER("9016","会员状态为【正常】以外的场合，不能做【登入鉴权处理】"),
	
	/** CAN_NOT_AUTH_OPERATOR: 操作员状态为【正常】以外的场合，不能做【登入鉴权处理】 **/
	CAN_NOT_AUTH_OPERATOR("9017","操作员状态为【正常】以外的场合，不能做【登入鉴权处理】"),
	
    /** MEMBER_HAS_EXISTS: 该会员的mobile已经注册过 **/
	MEMBER_HAS_EXISTS("9100","该会员的mobile已经注册过"),
	
	/** CUSTOMERNO_HAS_NOT_EXISTS: 商户号（customerNo）不存在 **/
	CUSTOMERNO_HAS_NOT_EXISTS("9101","商户号不存在"),
	
	/** INVALID_MEMBERID: 【请求参数operatorId对应的memberId】和【请求参数memberId】不一致 **/
	INVALID_MEMBERID("9102","【请求参数operatorId对应的memberId】和【请求参数memberId】不一致"),
	
	/** CAN_NOT_RETRIEVE_PASSWORD: 会员状态为【正常】以外的场合，不能重置密码**/
	CAN_NOT_RETRIEVE_PASSWORD("9103","会员状态为【正常】以外的场合，不能重置密码"),
	
	/** CAN_NOT_MODIFY_PASSWORD: 会员状态为【正常】以外的场合，不能更新密码**/
	CAN_NOT_MODIFY_PASSWORD("9104","会员状态为【正常】以外的场合，不能更新密码"),
	
	/** ORGIPASSWORD_ERROR: 原密码不正确**/
	ORGIPASSWORD_ERROR("9105","原密码不正确"),
	
	/** NEWPASSWORD_ORGIPASSWORD_REPETITION_ERROR: 新密码和原密码不能重复**/
	NEWPASSWORD_ORGIPASSWORD_REPETITION_ERROR("9106","新密码和原密码不能重复"),
	
	/** CUSTOMERNO_HAS_INCLUDE_USER_NAME: 同一商户号下用户名不能相同**/
	CUSTOMERNO_HAS_INCLUDE_USER_NAME("9107","同一商户号下用户名不能相同"),
	
	/** CAN_NOT_RETRIEVE_PASSWORD_OPERATOR: 操作员状态为【正常】以外的场合，不能重置密码**/
	CAN_NOT_RETRIEVE_PASSWORD_OPERATOR("9108","操作员状态为【正常】以外的场合，不能重置密码"),
	
	/** CAN_NOT_MODIFY_PASSWORD_OPERATOR: 操作员状态为【正常】以外的场合，不能更新密码**/
	CAN_NOT_MODIFY_PASSWORD_OPERATOR("9109","操作员状态为【正常】以外的场合，不能更新密码"),
	
	/** CAN_NOT_FROZEN_OPERATOR: 操作员状态为【正常】以外的场合，不能做【冻结操作员处理】 **/
	CAN_NOT_FROZEN_OPERATOR("9110","操作员状态为【正常】以外的场合，不能做【冻结操作员处理】"),
	
	/** CAN_NOT_UNFREEZE_OPERATOR: 操作员状态为【冻结】以外的场合，不能做【解冻操作员处理】 **/
	CAN_NOT_UNFREEZE_OPERATOR("9112","操作员状态为【冻结】以外的场合，不能做【解冻操作员处理】"),
	
	/** CAN_NOT_CANCEL_OPERATOR: 操作员状态为【正常/冻结】以外的场合，不能做【注销操作员处理】 **/
	CAN_NOT_CANCEL_OPERATOR("9113","操作员状态为【正常/冻结】以外的场合，不能做【注销操作员处理】"),
	
	/** TRADE_PASSWORD_ERROR: 交易密码不正确 **/
	TRADE_PASSWORD_ERROR("9114","交易密码不正确"),
	
	/** MEMBER_IS_AUTHENTICATED: 该会员已是认证会员**/
	MEMBER_IS_AUTHENTICATED("9115", "该会员已是认证会员"),
	
	/** REAL_NAME_AUTH_FAIL: 信息验证不通过，认证失败  **/
	REAL_NAME_AUTH_FAIL("9116", "信息验证不通过，认证失败"),
	
	/** CAN_NOT_REPEAT_SET_PASSWORD: 交易密码已经设置，请勿重复设置  **/
	CAN_NOT_REPEAT_SET_PASSWORD("9117", "交易密码已经设置，请勿重复设置"),
	
	/** CAN_NOT_SET_PASSWORD: 会员状态为【正常】以外的场合，不能设置密码**/
	CAN_NOT_SET_PASSWORD("9118","会员状态为【正常】以外的场合，不能设置密码"),
	
	/** EXCEPTION_STATUS_CAN_NOT_REGISGER_OPERATOR: 会员状态为【正常】以外的场合，不能注册操作员**/
	EXCEPTION_STATUS_CAN_NOT_REGISGER_OPERATOR("9119","会员状态为【正常】以外的场合，不能注册操作员"),
	
	/** EXCEPTION_STATUS_CAN_NOT_REGISGER_OPERATOR_9120: 会员状态为【正常】以外的场合，不能注册操作员**/
	EXCEPTION_STATUS_CAN_NOT_REGISGER_OPERATOR_9120("9120","会员状态为【正常】以外的场合，不能更新信息"),
	
	/** EXCEPTION_STATUS_CAN_NOT_UPDATE_OPERATOR_INFO: 操作员状态为【正常】以外的场合，不能更新信息**/
	EXCEPTION_STATUS_CAN_NOT_UPDATE_OPERATOR_INFO("9121","操作员状态为【正常】以外的场合，不能更新信息"),
	
	/** CAN_NOT_BIND_AUTH_FAIL 不能绑定他人的银行卡 **/
	CAN_NOT_BIND_AUTH_FAIL("9122","不能绑定他人的银行卡"),
	
	/** CAN_NOT_AGAIN_BIND  银行账户已经绑定，不能更新绑定 **/
	CAN_NOT_AGAIN_BIND("9123","银行账户已经绑定，不能更新绑定"),
	
	/** CAN_NOT_UNBIND 该银行账户未绑定，无法进行解绑 **/
	CAN_NOT_UNBIND("9124","该银行账户未曾绑定，无法进行解绑"),
	
	/** AUTH_INFO_EXIST 该身份信息已被绑定，不能重复绑定  **/
	AUTH_INFO_EXIST("9125","该身份信息已被绑定，不能重复绑定"),
	
	/** PAYMENT_ERROR 支付网关系統异常 **/
	PAYMENT_ERROR("3000","支付网关系統异常 "),
	
	/** PAYMENT_FAIL 支付网关系統处理失败  **/
	PAYMENT_FAIL("3001","支付网关系統处理失败  "),
	
	/** ABNOMARL_USER_STATUS: 会员状态为【正常】以外的场合，无法进行操作  **/
	ABNOMARL_USER_STATUS("9130","会员状态为【正常】以外的场合，无法进行操作"),
	
	/** BIND_CARD_EXIST: 该银行卡号已被绑定  **/
	BIND_CARD_EXIST("9131","该银行卡号已被绑定"),
	
	/** BIND_CARD_NOT_EXIST: 该卡号未绑定，无法进行解绑  **/
	BIND_CARD_NOT_EXIST("9132","该卡号未绑定，无法进行解绑"),
	
	/** CAN_NOT_VERIFY_PASSWORD: 会员状态为【正常】以外的场合，不能验证密码**/
	CAN_NOT_VERIFY_PASSWORD("9133","会员状态为【正常】以外的场合，不能验证密码"),
	
	/** EXISTS_CERTICATE_TYPE_AND_NO: 您录入的证件类型证件号码已经被占用**/
	EXISTS_CERTICATE_TYPE_AND_NO("9134","您录入的证件类型证件号码已经被占用"),
	
	/** ENTERPRISE_CAN_NOT_UNFREEZE: 企业会员状态为【注销】的场合，不能解冻企业以及企业下的所有操作员 **/
	ENTERPRISE_CAN_NOT_UNFREEZE("9135","企业会员状态为【注销】的场合，不能解冻企业以及企业下的所有操作员"),
	
	/** CUSTOMER_NO_NOT_FOUND: 企业号不存在 **/
	CUSTOMER_NO_NOT_FOUND("9136","企业号不存在");
	

	private String code;
	private String desc;

    ResultCode(String code,String desc){
        this.code=code;
        this.desc=desc;
    }

    public String code(){
        return code;
    }

    public String desc(){
        return desc;
    }
    
    public static String getDesc(String code){
    	for(ResultCode item : ResultCode.values()){
    		if(StringUtils.equals(item.code, code)){
    			return item.desc();
    		}
    	}
    	
    	return null;
    }
    
}

