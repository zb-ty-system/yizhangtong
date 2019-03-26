/*
 * Copyright (c) 2016, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 *
 *
 */
package com.zillionfortune.cif.facade.user.dto;

import com.zillionfortune.cif.facade.common.dto.BaseResponse;

/**
 * ClassName: EnterpriseStatusUpdateResponse <br/>
 * Function: 会员冻结/解冻/注销业务Response. <br/>
 * Date: 2016年12月1日 下午5:41:33 <br/>
 *
 * @author wangzinan_tech@zillionfortune.com
 * @version 
 * @since JDK 1.7
 */
public class EnterpriseStatusUpdateResponse extends BaseResponse {

	private static final long serialVersionUID = 1L;
	
	/**
	 * memberId:会员Id.
	 */
	private String memberId;
	
	/**
	 * operatorId:操作员Id.
	 */
	private Long operatorId;
	
	/**
	 * 构造方法.
	 *
	 * @param respCode
	 * @param resultCode
	 * @param resultMsg
	 */
	public EnterpriseStatusUpdateResponse(String respCode, String resultCode, String resultMsg) {
		super(respCode, resultCode, resultMsg);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 构造方法.
	 *
	 * @param respCode
	 * @param resultMsg
	 */
	public EnterpriseStatusUpdateResponse(String respCode, String resultMsg) {
		super(respCode, resultMsg);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 获取memberId的值.
	 *
	 * @return memberId
	 */
	public String getMemberId() {
		return memberId;
	}

	/**
	 * 设置memberId的值.
	 *
	 * @param  memberId
	 */
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	/**
	 * 获取operatorId的值.
	 *
	 * @return operatorId
	 */
	public Long getOperatorId() {
		return operatorId;
	}

	/**
	 * 设置operatorId的值.
	 *
	 * @param  operatorId
	 */
	public void setOperatorId(Long operatorId) {
		this.operatorId = operatorId;
	}

}
