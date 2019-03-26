package com.zillionfortune.cif.facade.user.dto;

import com.zillionfortune.cif.facade.common.dto.BaseRequest;

/**
 * ClassName: UserInfoQueryRequest <br/>
 * Function: 企业会员查询请求数据对象. <br/>
 * Date: 2016年11月16日 上午10:19:21 <br/>
 *
 * @author pengting
 * @version
 * @since JDK 1.7
 */
public class EnterpriseUserInfoQueryRequest extends BaseRequest {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1744362333610628183L;
	private String memberId;

	/**
	 * operatorId:操作员Id
	 */
	private Long operatorId;

	private String customerId;

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public Long getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Long operatorId) {
		this.operatorId = operatorId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
}
