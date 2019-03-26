/*
 * Copyright (c) 2016, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 *
 *
 */
package com.zillionfortune.cif.facade.user.dto;


/**
 * ClassName: EntUserAuthInfoByPageQueryDto <br/>
 * Function: 企业会员认证信息. <br/>
 * Date: 2016年11月21日 上午10:07:46 <br/>
 *
 * @author pengting
 * @version
 * @since JDK 1.7
 */
public class EntUserAuthInfoByPageQueryDto {
	/**
	 * memberId:	memberId
	 */
	private String memberId;
	/**
	 * customerNo:	企业商户号
	 */
	private String customerNo;
	/**
	 * enterpriseName:	企业名称
	 */
	private String enterpriseName;
	/**
	 * certificateNo:	营业执照号/统一社会信用代码号
	 */
	private String certificateNo;
	/**
	 * certificateType:	证件类型
	 */
	private int certificateType;
	/**
	 * certExpDate:证件有效期
	 */
	private String certExpDate;
	/**
	 * legalPersonName:	法人姓名
	 */
	private String legalPersonName;
	/**
	 * legalPersonCertificateType:	法人证件类型
	 */
	private int legalPersonCertificateType;
	/**
	 * legalPersonCertificateNo:	法人证件号码
	 */
	private String legalPersonCertificateNo;
	/**
	 * legalPersonCertExpDate:	法人证件有效期
	 */
	private String legalPersonCertExpDate;
	/**
	 * enterpriseAuditComment:企业审核意见
	 */
	private String enterpriseAuditComment;
	/**
	 * legalPersonAuditComment:法人审核意见
	 */
	private String legalPersonAuditComment;
	
	private String authGrade;//	认证等级
	private String enterpriseAuditStatus;//	企业审核状态
	private String legalPersonAuditStatus;//	法人审核状态
	private String registerAddress;//	企业注册地址
	private String phoneNo;//	联系电话

	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getCustomerNo() {
		return customerNo;
	}
	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}
	public String getEnterpriseName() {
		return enterpriseName;
	}
	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}
	public String getCertificateNo() {
		return certificateNo;
	}
	public void setCertificateNo(String certificateNo) {
		this.certificateNo = certificateNo;
	}
	public int getCertificateType() {
		return certificateType;
	}
	public void setCertificateType(int certificateType) {
		this.certificateType = certificateType;
	}
	public String getCertExpDate() {
		return certExpDate;
	}
	public void setCertExpDate(String certExpDate) {
		this.certExpDate = certExpDate;
	}
	public String getLegalPersonName() {
		return legalPersonName;
	}
	public void setLegalPersonName(String legalPersonName) {
		this.legalPersonName = legalPersonName;
	}
	public int getLegalPersonCertificateType() {
		return legalPersonCertificateType;
	}
	public void setLegalPersonCertificateType(int legalPersonCertificateType) {
		this.legalPersonCertificateType = legalPersonCertificateType;
	}
	public String getLegalPersonCertificateNo() {
		return legalPersonCertificateNo;
	}
	public void setLegalPersonCertificateNo(String legalPersonCertificateNo) {
		this.legalPersonCertificateNo = legalPersonCertificateNo;
	}
	public String getLegalPersonCertExpDate() {
		return legalPersonCertExpDate;
	}
	public void setLegalPersonCertExpDate(String legalPersonCertExpDate) {
		this.legalPersonCertExpDate = legalPersonCertExpDate;
	}
	public String getEnterpriseAuditComment() {
		return enterpriseAuditComment;
	}
	public void setEnterpriseAuditComment(String enterpriseAuditComment) {
		this.enterpriseAuditComment = enterpriseAuditComment;
	}
	public String getLegalPersonAuditComment() {
		return legalPersonAuditComment;
	}
	public void setLegalPersonAuditComment(String legalPersonAuditComment) {
		this.legalPersonAuditComment = legalPersonAuditComment;
	}
	public String getAuthGrade() {
		return authGrade;
	}
	public void setAuthGrade(String authGrade) {
		this.authGrade = authGrade;
	}
	public String getEnterpriseAuditStatus() {
		return enterpriseAuditStatus;
	}
	public void setEnterpriseAuditStatus(String enterpriseAuditStatus) {
		this.enterpriseAuditStatus = enterpriseAuditStatus;
	}
	public String getLegalPersonAuditStatus() {
		return legalPersonAuditStatus;
	}
	public void setLegalPersonAuditStatus(String legalPersonAuditStatus) {
		this.legalPersonAuditStatus = legalPersonAuditStatus;
	}
	public String getRegisterAddress() {
		return registerAddress;
	}
	public void setRegisterAddress(String registerAddress) {
		this.registerAddress = registerAddress;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
}
