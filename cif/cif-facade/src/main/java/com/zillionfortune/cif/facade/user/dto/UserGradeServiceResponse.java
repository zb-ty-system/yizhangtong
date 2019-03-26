package com.zillionfortune.cif.facade.user.dto;

import com.zillionfortune.cif.facade.common.dto.BaseResponse;

/**
 * ClassName: UserGradeServiceResponse <br/>
 * Function: 会员等级服务响应. <br/>
 * Date: 2016年11月22日 上午11:13:23 <br/>
 *
 * @author pengting
 * @version
 * @since JDK 1.7
 */
public class UserGradeServiceResponse extends BaseResponse {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private String memberId;
	private String grade;
	private Integer gradeType;

	public UserGradeServiceResponse() {
		super();
	}

	public UserGradeServiceResponse(String respCode, String resultMsg) {
		super(respCode, resultMsg);
	}

	public UserGradeServiceResponse(String respCode, String resultCode, String resultMsg) {
		super(respCode, resultCode, resultMsg);
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public Integer getGradeType() {
		return gradeType;
	}

	public void setGradeType(Integer gradeType) {
		this.gradeType = gradeType;
	}

}