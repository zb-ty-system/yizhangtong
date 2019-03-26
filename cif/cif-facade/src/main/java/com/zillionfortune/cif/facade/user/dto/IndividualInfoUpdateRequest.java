package com.zillionfortune.cif.facade.user.dto;

import com.zillionfortune.cif.facade.common.dto.BaseRequest;

public class IndividualInfoUpdateRequest extends BaseRequest {

	private static final long serialVersionUID = 1L;

	/** 客户编号 */
	private String customerId;
	
	/** 会员编号 */
	private String memberId;
	
	/** 年龄 */
    private String age;

    /**
     * 性别
     * 1：男性；2：女性
     */
    private String gender;

    /** 工作 */
    private String work;

    /** 工作年限 */
    private String workYears;

    /** 常住地址 */
    private String commonAddress;

    /**
     * 婚姻状态
     * 1：已婚；0：未婚
     */
    private String marriageStatus;

    /** 年收入 */
    private String annualSalary;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
	public String getWork() {
		return work;
	}

	public void setWork(String work) {
		this.work = work;
	}

	public String getCommonAddress() {
		return commonAddress;
	}

	public void setCommonAddress(String commonAddress) {
		this.commonAddress = commonAddress;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getWorkYears() {
		return workYears;
	}

	public void setWorkYears(String workYears) {
		this.workYears = workYears;
	}

	public String getMarriageStatus() {
		return marriageStatus;
	}

	public void setMarriageStatus(String marriageStatus) {
		this.marriageStatus = marriageStatus;
	}

	public String getAnnualSalary() {
		return annualSalary;
	}

	public void setAnnualSalary(String annualSalary) {
		this.annualSalary = annualSalary;
	}

}
