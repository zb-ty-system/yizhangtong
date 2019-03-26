package com.zillionfortune.cif.facade.user.dto;

import com.zillionfortune.cif.facade.common.dto.BaseResponse;

public class EnterpriseRegisterInfoUpdateResponse  extends BaseResponse {
	
	private static final long serialVersionUID = 1L;

	/**
	 * 会员ID
	 * 必输
	 */
	private String memberId;
	
	/**
	 * 操作员ID 操作员表主键
	 * 必输
	 */
	private Long operatorId;

	public EnterpriseRegisterInfoUpdateResponse() {
		super();
	}

	public EnterpriseRegisterInfoUpdateResponse(String respCode) {
		super(respCode);
	}
	
	public EnterpriseRegisterInfoUpdateResponse(String respCode,
			String resultMsg) {
		super(respCode, resultMsg);
	}

	public EnterpriseRegisterInfoUpdateResponse(String respCode, String resultCode, String resultMsg) {
		super(respCode, resultCode, resultMsg);
	}

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
	
}