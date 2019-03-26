package com.zillionfortune.cif.facade.user.dto;

import com.zillionfortune.cif.facade.common.dto.BaseResponse;

/**
 * ClassName: CurrentEnterpriseUserQueryResponse <br/>
 * Function: 企业会员登录信息查询响应对象. <br/>
 * Date: 2016年11月22日 下午3:42:34 <br/>
 *
 * @author pengting
 * @version 
 * @since JDK 1.7
 */
public class CurrentEnterpriseUserQueryResponse extends BaseResponse {

	private static final long serialVersionUID = 1L;
	
	private String memberId;
	private Long operatorId;
	private String mobile;
	private String userName;
	private String email;
	
	public CurrentEnterpriseUserQueryResponse() {
		super();
	}
	public CurrentEnterpriseUserQueryResponse(String respCode, String resultCode,String resultMsg) {
		super(respCode, resultCode, resultMsg);
	}
	public CurrentEnterpriseUserQueryResponse(String respCode, String resultMsg) {
		super(respCode, resultMsg);
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
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
