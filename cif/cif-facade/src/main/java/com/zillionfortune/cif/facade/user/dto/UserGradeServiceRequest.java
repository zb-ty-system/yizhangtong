package com.zillionfortune.cif.facade.user.dto;

import com.zillionfortune.cif.facade.common.dto.BaseRequest;

/**
 * ClassName: UserGradeServiceRequest <br/>
 * Function: 会员等级服务请求对象. <br/>
 * Date: 2016年11月22日 上午11:07:01 <br/>
 *
 * @author pengting
 * @version
 * @since JDK 1.7
 */
public class UserGradeServiceRequest extends BaseRequest {

	private static final long serialVersionUID = 1L;

	private String memberId;
	private String grade;
	private String gradeType; //查看枚举GradeType

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

	public String getGradeType() {
		return gradeType;
	}

	public void setGradeType(String gradeType) {
		this.gradeType = gradeType;
	}
}
