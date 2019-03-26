package com.zillionfortune.cif.facade.user.dto;

import com.zillionfortune.cif.facade.common.dto.BaseRequest;


/**
 * ClassName: CurrentEnterpriseUserQueryRequest <br/>
 * Function: 企业会员登录信息查询请求对象. <br/>
 * Date: 2016年11月22日 下午3:43:31 <br/>
 *
 * @author pengting
 * @version 
 * @since JDK 1.7
 */
public class CurrentEnterpriseUserQueryRequest extends BaseRequest {
	
	private static final long serialVersionUID = 1L;
	
	private String memberId;
	private String operatorId;
	private String accessToken;

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}
}
