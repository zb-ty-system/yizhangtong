package com.zillionfortune.cif.facade.user.dto;

import com.zillionfortune.cif.facade.common.dto.BaseRequest;

/**
 * ClassName: CurrentUserQueryServiceRequest <br/>
 * Function: 会员登录信息查询_请求参数对象. <br/>
 * Date: 2016年11月15日 下午4:45:26 <br/>
 *
 * @author Administrator
 * @version 
 * @since JDK 1.7
 */
public class CurrentUserQueryServiceRequest extends BaseRequest {
	
	private static final long serialVersionUID = 1L;
	
	private String memberId;
	private String accessToken;
	
	public CurrentUserQueryServiceRequest() {
		super();
	}

	public CurrentUserQueryServiceRequest(Integer sysCode, String memberId,
			String accessToken) {
		super();
		this.memberId = memberId;
		this.accessToken = accessToken;
	}

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

}
