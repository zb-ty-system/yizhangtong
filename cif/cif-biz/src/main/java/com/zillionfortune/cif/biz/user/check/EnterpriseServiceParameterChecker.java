/*
 * Copyright (c) ${year}, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 */
package com.zillionfortune.cif.biz.user.check;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.zillionfortune.cif.common.enums.EnterpriseCertType;
import com.zillionfortune.cif.common.enums.PersonCertType;
import com.zillionfortune.cif.common.exception.BusinessException;
import com.zillionfortune.cif.common.util.CommonUtil;
import com.zillionfortune.cif.facade.user.dto.EnterpriseInfoUpdateRequest;
import com.zillionfortune.cif.facade.user.dto.EnterpriseRegisterInfoUpdateRequest;
import com.zillionfortune.cif.facade.user.dto.EnterpriseRegisterOpertorRequest;
import com.zillionfortune.cif.facade.user.dto.EnterpriseRegisterRequest;

/**
 * ClassName: EnterpriseServiceParameterChecker <br/>
 * Function: 企业注册参数校验. <br/>
 * Date: 2016年11月11日 下午2:08:57 <br/>
 *
 * @author zhengrunlong@zillionfortune.com
 * @version 
 * @since JDK 1.7
 */
@Component
public class EnterpriseServiceParameterChecker {

	/**
	 * 企业注册请求参数校验
	 * @param
	 * @return
	 */
	public void checkEnterpriseRegisterRequest(EnterpriseRegisterRequest req) throws Exception {
    
        if (req == null) {
            throw new BusinessException("请求对象EnterpriseRegisterRequest不能为空");
        }
        
        if (StringUtils.isBlank(req.getEmail()) && StringUtils.isBlank(req.getMobile())) {
        	throw new BusinessException("email和mobile字段不能同时为空");
        }
     
        if(StringUtils.isBlank(req.getPassword())){
        	throw new BusinessException("password字段不能为空");
        }
        
        if (StringUtils.isBlank(req.getCertificateType())) {
            throw new BusinessException("certificateType字段不能为空");
        }
        
        if (!CommonUtil.isNumeric(req.getCertificateType())) {
        	throw new BusinessException("certificateType字段的参数值必须是整数");
        }
        
        if (EnterpriseCertType.getEnumItem(Integer.parseInt(req.getCertificateType())) == null){
        	throw new BusinessException("certificateType字段的参数值不在约定的范围");
        }
        
        if (StringUtils.isBlank(req.getCertificateNo())) {
            throw new BusinessException("certificateNo字段不能为空");
        }
        
        if (StringUtils.isBlank(req.getCertExpDate())){
        	throw new BusinessException("certExpDate字段不能为空");
        }
        
        if (!CommonUtil.isDate(req.getCertExpDate())){
        	throw new BusinessException("certExpDate字段参数值不是日期类型");
        }
    
        if (StringUtils.isBlank(req.getLegalPersonName())){
        	throw new BusinessException("legalPersonName字段不能为空");
        }
        
        if (StringUtils.isBlank(req.getLegalPersonCertificateType())){
        	throw new BusinessException("legalPersonCertificateType字段不能为空");
        }
        
        if (!CommonUtil.isNumeric(req.getLegalPersonCertificateType())) {
        	throw new BusinessException("legalPersonCertificateType字段的参数值必须是整数");
        }
        
        if (PersonCertType.getEnumItem(Integer.parseInt(req.getLegalPersonCertificateType())) == null){
        	throw new BusinessException("legalPersonCertificateType字段的参数值不在约定的范围");
        }
        
        if (StringUtils.isBlank(req.getLegalPersonCertificateNo())){
        	throw new BusinessException("legalPersonCertificateNo字段不能为空");
        }
       
        if (StringUtils.isBlank(req.getLegalPersonCertExpDate())){
        	throw new BusinessException("legalPersonCertExpDate字段不能为空");
        }
       
        if (!CommonUtil.isDate(req.getLegalPersonCertExpDate())){
        	throw new BusinessException("legalPersonCertExpDate字段参数值不是日期类型");
        }
        
        if (StringUtils.isBlank(req.getEnterpriseName())){
        	throw new BusinessException("enterpriseName字段不能为空");
        }
        
        if (StringUtils.isBlank(req.getRegisterAddress())){
        	throw new BusinessException("registerAddress字段不能为空");
        }
        
        if (StringUtils.isBlank(req.getPhone())){
        	throw new BusinessException("phone字段不能为空");
        }

    }
	
	/**
	 * 企业注册操作员
	 * @param
	 * @return
	 */
	public void checkEnterpriseRegisterOpertorRequest(EnterpriseRegisterOpertorRequest req) throws Exception {
		
		if (req == null) {
            throw new BusinessException("请求对象EnterpriseRegisterOpertorRequest不能为空");
        }
		  
        if (StringUtils.isBlank(req.getEmail()) && StringUtils.isBlank(req.getMobile())) {
        	throw new BusinessException("email和mobile字段不能同时为空");
        }
        
        if(StringUtils.isBlank(req.getPassword())){
        	throw new BusinessException("password字段不能为空");
        }
        
        if (StringUtils.isBlank(req.getCustomerNo())) {
            throw new BusinessException("customerNo字段不能为空");
        }
		
	}
	
	/**
	 * 企业会员注册信息更新
	 * @param
	 * @return
	 */
	public void checkEnterpriseRegisterInfoUpdateRequest(EnterpriseRegisterInfoUpdateRequest req) throws Exception{
		
		if (req == null) {
            throw new BusinessException("请求对象EnterpriseRegisterInfoUpdateRequest不能为空");
        }
		
		if(StringUtils.isBlank(req.getMemberId())){
			throw new BusinessException("memberId字段不能为空");
		}
		
		if(req.getOperatorId() == null){
			throw new BusinessException("operatorId字段不能为空");
		}
		
		if(!com.zillionfortune.cif.common.util.StringUtils.isNumber(req.getOperatorId())){
			throw new BusinessException("operatorId字段参数值必须是整数");
		}
		
		if(req.getEmail() == null && req.getMobile() == null){
			throw new BusinessException("mobile和email同时为空，无需更新");
		}
		
	}
	
	/**
	 * 企业会员扩展信息更新
	 * @param
	 * @return
	 */
	public void checkEnterpriseInfoUpdateRequest(EnterpriseInfoUpdateRequest req) throws Exception {
       
        if (req == null) {
            throw new BusinessException("请求对象EnterpriseInfoUpdateRequest不能为空");
        }
        
        if(StringUtils.isBlank(req.getMemberId())){
        	throw new BusinessException("memberId字段不能为空");
        }
        
        if(StringUtils.isBlank(req.getLegalPersonName())
        		&& req.getLegalPersonCertificateType()== null 
        		&& StringUtils.isBlank(req.getLegalPersonCertificateNo()) 
        		&& StringUtils.isBlank(req.getEnterpriseName())
        		&& StringUtils.isBlank(req.getRegisterAddress())
        		&& StringUtils.isBlank(req.getPostCode())
        		&& StringUtils.isBlank(req.getPhone())
        		&& StringUtils.isBlank(req.getCertExpDate())
        		&& StringUtils.isBlank(req.getLegalPersonCertExpDate())
        		&& null == req.getEnterpriseType()
        		&& null == req.getIndustry()
        		&& req.getCertificateType() == null
        		&& StringUtils.isBlank(req.getCertificateNo())){
        	
        	throw new BusinessException("所有要更新的请求字段不能同时为空");
        	
        }
        
    }
	
}
