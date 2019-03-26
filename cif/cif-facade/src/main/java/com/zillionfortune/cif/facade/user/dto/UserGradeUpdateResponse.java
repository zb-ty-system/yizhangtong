package com.zillionfortune.cif.facade.user.dto;

import com.zillionfortune.cif.facade.common.dto.BaseResponse;

/**
 * ClassName: UserGradeUpdateResponse <br/>
 * Function: 会员等级更新响应. <br/>
 * Date: 2016年11月29日 下午4:36:40 <br/>
 *
 * @author pengting
 * @version 
 * @since JDK 1.7
 */
public class UserGradeUpdateResponse extends BaseResponse {

	private static final long serialVersionUID = 1L;
	
	// 会员Id
	private String memberId;
	
	public UserGradeUpdateResponse(String respCode, String resultCode, String resultMsg) {
		super(respCode, resultCode, resultMsg);
	}

	public UserGradeUpdateResponse(String respCode, String resultMsg) {
		super(respCode, resultMsg);
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	

}
