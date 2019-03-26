package com.zillionfortune.cif.facade.user.dto;

import java.math.BigDecimal;

import com.zillionfortune.cif.facade.common.dto.BaseResponse;

/**
 * @desc 个人会员扩展信息
 * @author pengting
 * @date 2016年11月11日 下午2:19:59
 * @version 1.0.0
 */
public class IndividualExtInfoQueryResponse extends BaseResponse {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8552690975021875174L;
	private String memberId;
	private Integer age;
	private String gender;
	private String work;
	private Integer workYears;
	private String commonAddress;
	private Integer marriageStatus;
	private BigDecimal annualSalary;

	public IndividualExtInfoQueryResponse() {
		super();
	}

	public IndividualExtInfoQueryResponse(String respCode, String resultMsg) {
		super(respCode, resultMsg);
	}

	public IndividualExtInfoQueryResponse(String respCode, String resultCode, String resultMsg) {
		super(respCode, resultCode, resultMsg);
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getWork() {
		return work;
	}

	public void setWork(String work) {
		this.work = work;
	}

	public Integer getWorkYears() {
		return workYears;
	}

	public void setWorkYears(Integer workYears) {
		this.workYears = workYears;
	}

	public String getCommonAddress() {
		return commonAddress;
	}

	public void setCommonAddress(String commonAddress) {
		this.commonAddress = commonAddress;
	}

	public Integer getMarriageStatus() {
		return marriageStatus;
	}

	public void setMarriageStatus(Integer marriageStatus) {
		this.marriageStatus = marriageStatus;
	}

	public BigDecimal getAnnualSalary() {
		return annualSalary;
	}

	public void setAnnualSalary(BigDecimal annualSalary) {
		this.annualSalary = annualSalary;
	}

}
