/*
 * Copyright (c) ${year}, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 */
package com.zillionfortune.cif.biz.user.check;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.zillionfortune.cif.common.constants.CommonConstants;
import com.zillionfortune.cif.common.enums.RegisterSource;
import com.zillionfortune.cif.common.exception.BusinessException;
import com.zillionfortune.cif.common.util.CommonUtil;
import com.zillionfortune.cif.facade.user.dto.IndividualInfoUpdateRequest;
import com.zillionfortune.cif.facade.user.dto.IndividualRegisterRequest;
import com.zillionfortune.cif.facade.user.dto.IndividualRegisterInfoUpdateRequest;

/**
 * ClassName: IndividualServiceParameterChecker <br/>
 * Function: 个人注册相关参数校验. <br/>
 * Date: 2016年11月11日 下午2:05:34 <br/>
 *
 * @author zhengrunlong@zillionfortune.com
 * @version 
 * @since JDK 1.7
 */
@Component
public class IndividualServiceParameterChecker {

	/**
	 * 个人注册请求参数校验
	 * @param
	 * @return
	 */
	public void checkIndividualRegisterRequest(IndividualRegisterRequest req) throws Exception {
		
        if (req == null) {
            throw new BusinessException("请求对象IndividualRegisterRequest不能为空");
        }
        
        if (StringUtils.isBlank(req.getMobile())) {
            throw new BusinessException("mobile字段不能为空");
        }
        
        if (StringUtils.isBlank(req.getRegisterSource())) {
            throw new BusinessException("registerSource字段不能为空");
        }

        if (!CommonUtil.isNumeric(req.getRegisterSource())){
        	throw new BusinessException("registerSource字段的参数值必须是整数");
        }
        
        if (RegisterSource.getEnumItem(Integer.parseInt(req.getRegisterSource())) == null){
        	throw new BusinessException("registerSource字段的参数值不在约定的范围");
        }
        
    }
	
	/**
	 * 更新个人会员注册信息请求参数校验
	 * @param
	 * @return
	 */
	public void checkRegisterInfoUpdateRequest(IndividualRegisterInfoUpdateRequest req) throws Exception {
       
        if (req == null) {
            throw new BusinessException("请求对象IndividualRegisterInfoUpdateRequest不能为空");
        }
        
        if (StringUtils.isBlank(req.getMemberId())) {
            throw new BusinessException("memberId字段不能为空");
        }
        
    }
	
	/**
	 * 更新用户扩展信息请求参数校验
	 * @param
	 * @return
	 */
	public void checkIndividualInfoUpdateRequest(IndividualInfoUpdateRequest req) throws Exception {
       
        if (req == null) {
            throw new BusinessException("请求对象IndividualInfoUpdateRequest不能为空");
        }
        
        if(StringUtils.isBlank(req.getMemberId()) && StringUtils.isBlank(req.getCustomerId())){
        	throw new BusinessException("memberId字段和customerId字段不能同时为空");
        }
        
        if(StringUtils.isNotBlank(req.getAge())){
        	if(!StringUtils.isNumeric(req.getAge())){
        		throw new BusinessException("age字段的参数值必须是整数");
        	}
        }
        
        if(StringUtils.isNotBlank(req.getGender())){
        	
        	if(!StringUtils.isNumeric(req.getGender())){
        		throw new BusinessException("gender字段的参数值必须是整数");
        	}
        	
        	if(CommonConstants.MALE.intValue() != Integer.parseInt(req.getGender()) 
        			&& CommonConstants.FEMALE.intValue() != Integer.parseInt(req.getGender())){
        		throw new BusinessException("gender字段的参数值不在约定的范围");
        	}
        }
        
        if(StringUtils.isNotBlank(req.getWorkYears())){
        	if(!StringUtils.isNumeric(req.getWorkYears())){
        		throw new BusinessException("workYears字段的参数值必须是整数");
        	}
        }
        
        if(StringUtils.isNotBlank(req.getMarriageStatus())){
        	
        	if(!StringUtils.isNumeric(req.getMarriageStatus())){
        		throw new BusinessException("marriageStatus字段的参数值必须是整数");
        	}
        	
        	if(CommonConstants.MARRIED.intValue() != Integer.parseInt(req.getMarriageStatus()) 
        			&& CommonConstants.UNMARRIED.intValue() != Integer.parseInt(req.getMarriageStatus())){
        		throw new BusinessException("marriageStatus字段的参数值不在约定的范围");
        	}	
        }
   
        if(StringUtils.isNotBlank(req.getAnnualSalary())){
        	
        	if(!NumberUtils.isNumber(req.getAnnualSalary())){
        		throw new BusinessException("annualSalary字段的参数值必须是数字");
        	}
   
        }
        
    }
	
}
