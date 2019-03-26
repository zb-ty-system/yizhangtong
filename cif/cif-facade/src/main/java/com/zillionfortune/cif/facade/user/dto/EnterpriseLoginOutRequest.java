/*
 * Copyright (c) 2016, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 *
 *
 */
package com.zillionfortune.cif.facade.user.dto;

import com.zillionfortune.cif.facade.common.dto.BaseRequest;

/**
 * ClassName: EnterpriseLoginOutRequest <br/>
 * Function: 企业_会员登出_请求参数对象. <br/>
 * Date: 2016年11月21日 下午7:01:06 <br/>
 *
 * @author kaiyun
 * @version 
 * @since JDK 1.7
 */
public class EnterpriseLoginOutRequest extends BaseRequest {

	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 会员Id
	 * 必输
	 */
	private String memberId;
	
	/**
	 * 操作员id
	 * 必输
	 */
	private String operatorId;
	
	/**
	 * 访问token
	 * 必输
	 */
	private String accessToken;

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	
	
	

}
