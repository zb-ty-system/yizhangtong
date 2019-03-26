/*
 * Copyright (c) ${year}, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 */
package com.zillionfortune.cif.biz.user.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import java.math.BigDecimal;

import com.zillionfortune.cif.biz.user.check.IndividualServiceParameterChecker;
import com.zillionfortune.cif.common.constants.CommonConstants;
import com.zillionfortune.cif.common.enums.RespCode;
import com.zillionfortune.cif.common.enums.ResultCode;
import com.zillionfortune.cif.common.enums.UserStatusCodeEnum;
import com.zillionfortune.cif.common.exception.BusinessException;
import com.zillionfortune.cif.common.util.BusinessFlowUtils;
import com.zillionfortune.cif.dal.entity.PersonInfo;
import com.zillionfortune.cif.dal.entity.PersonMember;
import com.zillionfortune.cif.facade.user.IndividualServiceFacade;
import com.zillionfortune.cif.facade.user.dto.EnterpriseRegisterOpertorResponse;
import com.zillionfortune.cif.facade.user.dto.IndividualInfoUpdateRequest;
import com.zillionfortune.cif.facade.user.dto.IndividualInfoUpdateResponse;
import com.zillionfortune.cif.facade.user.dto.IndividualRegisterRequest;
import com.zillionfortune.cif.facade.user.dto.IndividualRegisterResponse;
import com.zillionfortune.cif.facade.user.dto.IndividualRegisterInfoUpdateRequest;
import com.zillionfortune.cif.facade.user.dto.IndividualRegisterInfoUpdateResponse;
import com.zillionfortune.cif.service.user.PersonInfoService;
import com.zillionfortune.cif.service.user.PersonMemberService;

/**
 * ClassName: IndividualServiceFacadeImpl <br/>
 * Function: 个人客户注册修改对外接口实现. <br/>
 * Date: 2016-11-16 下午1:59:13 <br/>
 *
 * @author zhengrunlong@zillionfortune.com
 * @version 
 * @since JDK 1.7
 */
@Component
public class IndividualServiceFacadeImpl implements IndividualServiceFacade {

	private static Logger log = LoggerFactory.getLogger(IndividualServiceFacadeImpl.class);
	
	@Autowired
	private PersonMemberService personMemberService;
	
	@Autowired
	private PersonInfoService personInfoService;
	
	@Autowired
	IndividualServiceParameterChecker paraChecker;
	
	/**
	 * 个人客户注册.
	 * @see com.zillionfortune.cif.facade.user.IndividualServiceFacade#register(com.zillionfortune.cif.facade.user.dto.IndividualRegisterRequest)
	 */
	@Override
	public IndividualRegisterResponse register(IndividualRegisterRequest req) {
		log.info("IndividualServiceFacadeImpl.register.req:" + JSON.toJSONString(req));

		IndividualRegisterResponse resp;
		
		try{
			//======1.请求参数校验
			paraChecker.checkIndividualRegisterRequest(req);
			
			//======2.通过登录名来校验是否注册过
			List<Integer> statusList = new ArrayList<Integer>();//会员状态
			statusList.add(UserStatusCodeEnum.NORMAL.getCode());
			statusList.add(UserStatusCodeEnum.FROZEN.getCode());
			
			Map<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("userName", req.getMobile());
			paramMap.put("statusList", statusList);
			
			int count = personMemberService.getPersonMemberCount(paramMap);
			if(count > 0) {
				
				resp = new IndividualRegisterResponse(RespCode.SUCCESS.code(),ResultCode.MEMBER_HAS_EXISTS.code(),
						ResultCode.MEMBER_HAS_EXISTS.desc());
				
				log.info("IndividualServiceFacadeImpl.register.resp:" + JSON.toJSONString(resp));
				return resp;
			}
			
			//======3.落地会员信息
			PersonMember personMember = new PersonMember();
			
	        String memberId = BusinessFlowUtils.generate(CommonConstants.PERSONAL_MEMBER_PREFIX);//个人会员编号生成规则
	        personMember.setMemberId(memberId);
	        personMember.setPassword(StringUtils.upperCase(req.getPassword()));
	        personMember.setRegisterSource(Integer.parseInt(req.getRegisterSource()));
	        personMember.setPhoneNo(req.getMobile());
	        personMember.setUserName(req.getMobile());
	        personMember.setStatus(UserStatusCodeEnum.NORMAL.getCode());
	        personMember.setGrade(CommonConstants.PERSON_MEMBER_GRADE);
	        personMember.setCreateTime(new Date());
	        
	        personMemberService.insertSelective(personMember);
			
			resp = new IndividualRegisterResponse(RespCode.SUCCESS.code(),ResultCode.SUCCESS.code(),
					ResultCode.SUCCESS.desc());
			resp.setMemberId(memberId);
			
		}catch(Exception e){
			log.error(e.getMessage(), e);
			 
			if (e instanceof BusinessException) {
               
                resp = new IndividualRegisterResponse(RespCode.SUCCESS.code(),ResultCode.FAIL.code(),
                		e.getMessage());
            } else {
                
                resp = new IndividualRegisterResponse(RespCode.FAIL.code(),RespCode.FAIL.desc());
            }
		}
		
		log.info("IndividualServiceFacadeImpl.register.resp:" + JSON.toJSONString(resp));
		
		return resp;
	}

	/**
	 * 个人客户注册信息修改.
	 * @see com.zillionfortune.cif.facade.user.IndividualServiceFacade#registerInfoUpdate(com.zillionfortune.cif.facade.user.dto.IndividualRegisterInfoUpdateRequest)
	 */
	@Override
	public IndividualRegisterInfoUpdateResponse registerInfoUpdate(IndividualRegisterInfoUpdateRequest req) {
		
		log.info("IndividualServiceFacadeImpl.registerInfoUpdate.req:" + JSON.toJSONString(req));

		IndividualRegisterInfoUpdateResponse resp;
		
		try{
			//======1.请求参数校验
			paraChecker.checkRegisterInfoUpdateRequest(req);
			
			//======2.校验要更新的memberId是否存在
			PersonMember pMember = personMemberService.queryByMemberId(req.getMemberId());
			if(pMember == null){
				throw new BusinessException("memberId="+req.getMemberId()+"不正确,无法更新");
			}
			
			//======3.校验会员状态是否正常
			if (pMember.getStatus() == null || UserStatusCodeEnum.NORMAL.getCode() != pMember.getStatus() ) {
				
				resp = new IndividualRegisterInfoUpdateResponse(RespCode.SUCCESS.code(),ResultCode.EXCEPTION_STATUS_CAN_NOT_REGISGER_OPERATOR_9120.code(), 
						ResultCode.EXCEPTION_STATUS_CAN_NOT_REGISGER_OPERATOR_9120.desc());

				log.info("IndividualServiceFacadeImpl.registerInfoUpdate.resp:" + JSON.toJSONString(resp));

				return resp;
			}
			
			//======4.更新会员注册信息
			PersonMember personMember = new PersonMember();
	        PropertyUtils.copyProperties(personMember, req);
	        personMember.setPhoneNo(req.getMobile());
	        personMember.setModifyTime(new Date());
	        
	        personMemberService.updateByMemberIdSelective(personMember);
			
			resp = new IndividualRegisterInfoUpdateResponse(RespCode.SUCCESS.code(),ResultCode.SUCCESS.code(),
					ResultCode.SUCCESS.desc());
			resp.setMemberId(req.getMemberId());
			
		}catch(Exception e){
			log.error(e.getMessage(), e);
			 
			if (e instanceof BusinessException) {
                
                resp = new IndividualRegisterInfoUpdateResponse(RespCode.SUCCESS.code(),ResultCode.FAIL.code(),
                		e.getMessage());
            } else {
                
                resp = new IndividualRegisterInfoUpdateResponse(RespCode.FAIL.code(),RespCode.FAIL.desc());
            }
		}
		log.info("IndividualServiceFacadeImpl.registerInfoUpdate.resp:" + JSON.toJSONString(resp));
		
		return resp;
	
	}

	/**
	 * 个人客户扩展信息修改.
	 * @see com.zillionfortune.cif.facade.user.IndividualServiceFacade#individualInfoUpdate(com.zillionfortune.cif.facade.user.dto.IndividualInfoUpdateRequest)
	 */
	@Override
	public IndividualInfoUpdateResponse individualInfoUpdate(IndividualInfoUpdateRequest req) {

		log.info("IndividualServiceFacadeImpl.individualInfoUpdate.req:" + JSON.toJSONString(req)); 

		IndividualInfoUpdateResponse resp;
		
		try{
			//======1.请求参数校验
			paraChecker.checkIndividualInfoUpdateRequest(req);
			
			//======2.更新用户扩展信息
			PersonInfo personInfo = new PersonInfo();
			personInfo.setCustomerId(req.getCustomerId());
			personInfo.setWork(req.getWork());
			personInfo.setCommonAddress(req.getCommonAddress());
			if(StringUtils.isNotBlank(req.getAge())) personInfo.setAge(Integer.parseInt(req.getAge()));
			if(StringUtils.isNotBlank(req.getGender())) personInfo.setGender(Integer.parseInt(req.getGender()));
			if(StringUtils.isNotBlank(req.getWorkYears())) personInfo.setWorkYears(Integer.parseInt(req.getWorkYears()));
			if(StringUtils.isNotBlank(req.getMarriageStatus())) personInfo.setMarriagerStatus(Integer.parseInt(req.getMarriageStatus()));
			if(StringUtils.isNotBlank(req.getAnnualSalary())) personInfo.setAnnualSalary(new BigDecimal(req.getAnnualSalary()));
			personInfo.setModifyTime(new Date());
	        
	        if(StringUtils.isBlank(req.getCustomerId())){//根据MemberId号获取CustomerId
	        	PersonMember pMember = personMemberService.selectByMemberId(req.getMemberId());
	        	if(pMember == null){
	        		 throw new BusinessException("根据memberId="+req.getMemberId()+"查询不到对应的客户信息,无法更新");
	        	}else if(StringUtils.isBlank(pMember.getCustomerId())){
	        		 throw new BusinessException("根据memberId="+req.getMemberId()+"查询不到对应的客户信息,无法更新");
	        	}else{
	        		personInfo.setCustomerId(pMember.getCustomerId());
	        	}
	        }else{
	        	PersonInfo pInfo = personInfoService.queryByCustomerId(req.getCustomerId());
	        	if(pInfo == null){
					throw new BusinessException("customerId="+req.getCustomerId()+"不正确,无法更新");
				}
	        }
	        
	        personInfoService.updateByCustomerIdSelective(personInfo);
			
			resp = new IndividualInfoUpdateResponse(RespCode.SUCCESS.code(),ResultCode.SUCCESS.code(),
					ResultCode.SUCCESS.desc());
			
			resp.setMemberId(req.getMemberId());
			resp.setCustomerId(personInfo.getCustomerId());
			
		}catch(Exception e){
			log.error(e.getMessage(), e);
			 
			if (e instanceof BusinessException) {
               
                resp = new IndividualInfoUpdateResponse(RespCode.SUCCESS.code(),ResultCode.FAIL.code(),
                		e.getMessage());
            } else {
                
                resp = new IndividualInfoUpdateResponse(RespCode.FAIL.code(),RespCode.FAIL.desc());
            }
		}
		
		log.info("IndividualServiceFacadeImpl.individualInfoUpdate.resp:" + JSON.toJSONString(resp));
		
		return resp;
	}

}

