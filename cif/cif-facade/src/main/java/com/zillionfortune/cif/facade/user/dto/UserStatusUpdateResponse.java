/*
 * Copyright (c) 2016, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 *
 *
 */
package com.zillionfortune.cif.facade.user.dto;

import com.zillionfortune.cif.facade.common.dto.BaseResponse;

/**
 * ClassName: UserStatusUpdateResponse <br/>
 * Function: 会员冻结/解冻/注销业务Response. <br/>
 * Date: 2016年12月9日 下午2:12:40 <br/>
 *
 * @author wangzinan_tech@zillionfortune.com
 * @version 
 * @since JDK 1.7
 */
public class UserStatusUpdateResponse extends BaseResponse {

	private static final long serialVersionUID = 1L;
	
	/**
	 * memberId:会员Id.
	 */
	private String memberId;
	
	/**
	 * 构造方法.
	 *
	 * @param respCode
	 * @param resultCode
	 * @param resultMsg
	 */
	public UserStatusUpdateResponse(String respCode, String resultCode, String resultMsg) {
		super(respCode, resultCode, resultMsg);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 构造方法.
	 *
	 * @param respCode
	 * @param resultMsg
	 */
	public UserStatusUpdateResponse(String respCode, String resultMsg) {
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

}
