/*
 * Copyright (c) 2016, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 *
 *
 */
package com.zillionfortune.cif.facade.user.dto;

import com.zillionfortune.cif.facade.common.dto.BaseResponse;

/**
 * ClassName: EnterpriseUserNameFindResponse <br/>
 * Function: 用户名是否存在. <br/>
 * Date: 2017年1月5日 上午9:28:04 <br/>
 *
 * @author pengting
 * @version 
 * @since JDK 1.7
 */
public class EnterpriseUserNameFindResponse extends BaseResponse{

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	/** 登录用户名  */
	private String userName;
	
	/**
	 * existFlag:是否存在 true：存在，false ：不存在
	 */
	private boolean existFlag;
	
	public EnterpriseUserNameFindResponse() {
		super();
	}
	public EnterpriseUserNameFindResponse(String respCode, String resultCode,String resultMsg) {
		super(respCode, resultCode, resultMsg);
	}
	public EnterpriseUserNameFindResponse(String respCode, String resultMsg) {
		super(respCode, resultMsg);
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public boolean isExistFlag() {
		return existFlag;
	}
	public void setExistFlag(boolean existFlag) {
		this.existFlag = existFlag;
	}
}
