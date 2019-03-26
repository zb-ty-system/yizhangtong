/*
 * Copyright (c) 2016, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 *
 *
 */
package com.zillionfortune.cif.facade.user.dto;

import com.zillionfortune.cif.facade.common.dto.BaseResponse;

/**
 * ClassName: EnterpriseQualificationUpdateDto <br/>
 * Function: 企业资质更新Response. <br/>
 * Date: 2016年12月20日 上午11:34:07 <br/>
 *
 * @author pengting
 * @version 
 * @since JDK 1.7
 */
public class EnterpriseQualificationUpdateResponse extends BaseResponse {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private String memberId;
	
	public EnterpriseQualificationUpdateResponse(String respCode) {
		super(respCode);
	}
	
	public EnterpriseQualificationUpdateResponse(String respCode, String resultCode, String resultMsg) {
		super(respCode, resultCode, resultMsg);
	}

	public EnterpriseQualificationUpdateResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EnterpriseQualificationUpdateResponse(String respCode, String resultMsg) {
		super(respCode, resultMsg);
		// TODO Auto-generated constructor stub
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	
}
