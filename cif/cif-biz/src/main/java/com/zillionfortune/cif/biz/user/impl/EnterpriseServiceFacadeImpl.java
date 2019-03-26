/*
 * Copyright (c) ${year}, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 */
package com.zillionfortune.cif.biz.user.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.zillionfortune.cif.biz.common.util.BusinessFlowNoUtils;
import com.zillionfortune.cif.biz.user.check.EnterpriseServiceParameterChecker;
import com.zillionfortune.cif.common.constants.CommonConstants;
import com.zillionfortune.cif.common.enums.RegisterSource;
import com.zillionfortune.cif.common.enums.RespCode;
import com.zillionfortune.cif.common.enums.ResultCode;
import com.zillionfortune.cif.common.enums.UserStatusCodeEnum;
import com.zillionfortune.cif.common.exception.BusinessException;
import com.zillionfortune.cif.common.util.BusinessFlowUtils;
import com.zillionfortune.cif.common.util.DateUtil;
import com.zillionfortune.cif.dal.entity.EnterpriseInfo;
import com.zillionfortune.cif.dal.entity.EnterpriseMember;
import com.zillionfortune.cif.dal.entity.EnterpriseOperator;
import com.zillionfortune.cif.facade.user.EnterpriseServiceFacade;
import com.zillionfortune.cif.facade.user.dto.EnterpriseInfoUpdateRequest;
import com.zillionfortune.cif.facade.user.dto.EnterpriseInfoUpdateResponse;
import com.zillionfortune.cif.facade.user.dto.EnterpriseRegisterInfoUpdateRequest;
import com.zillionfortune.cif.facade.user.dto.EnterpriseRegisterInfoUpdateResponse;
import com.zillionfortune.cif.facade.user.dto.EnterpriseRegisterOpertorRequest;
import com.zillionfortune.cif.facade.user.dto.EnterpriseRegisterOpertorResponse;
import com.zillionfortune.cif.facade.user.dto.EnterpriseRegisterRequest;
import com.zillionfortune.cif.facade.user.dto.EnterpriseRegisterResponse;
import com.zillionfortune.cif.service.user.EnterpriseInfoService;
import com.zillionfortune.cif.service.user.EnterpriseMemberService;
import com.zillionfortune.cif.service.user.EnterpriseOperatorService;

/**
 * ClassName: EnterpriseServiceFacadeImpl <br/>
 * Function: 企业客户注册修改对外接口实现. <br/>
 * Date: 2016年11月16日 下午7:34:07 <br/>
 *
 * @author zhengrunlong@zillionfortune.com
 * @version 
 * @since JDK 1.7
 */
@Component
public class EnterpriseServiceFacadeImpl implements EnterpriseServiceFacade {

	private static Logger log = LoggerFactory.getLogger(EnterpriseServiceFacadeImpl.class);
	
	@Autowired
	private EnterpriseInfoService enterpriseInfoService;
	
	@Autowired
	private EnterpriseMemberService enterpriseMemberService;
	
	@Autowired
	private EnterpriseOperatorService enterpriseOperatorService;
	
	@Autowired
	EnterpriseServiceParameterChecker paraChecker;
	
	@Resource
	BusinessFlowNoUtils businessFlowNoUtils;
	
	/**
	 * 企业客户注册.
	 * @see com.zillionfortune.cif.facade.user.EnterpriseServiceFacade#register(com.zillionfortune.cif.facade.user.dto.EnterpriseRegisterRequest)
	 */
	@Override
	public EnterpriseRegisterResponse register(EnterpriseRegisterRequest req) {

		log.info("EnterpriseServiceFacadeImpl.register.req:" + JSON.toJSONString(req));

		EnterpriseRegisterResponse resp;
		
		try{
			
			//1.======请求参数校验 
			paraChecker.checkEnterpriseRegisterRequest(req);
			
			//2.======通过证件类型和证件号码来校验该客户是否落地过客户信息
			EnterpriseInfo enterpriseInfo = new EnterpriseInfo();
			
			enterpriseInfo.setCertificateType(Integer.parseInt(req.getCertificateType()));
			enterpriseInfo.setCertificateNo(req.getCertificateNo());
			EnterpriseInfo returnEnterpriseInfo = enterpriseInfoService.queryEnterpriseInfo(enterpriseInfo);
			
			//3.======落地信息
			String sCustomerId = "";
			String sMemberId = "";
			String sCustomerNo = "";
			Long operatorId;
			
			if(returnEnterpriseInfo == null){//从未注册过
				
				sCustomerId = BusinessFlowUtils.generate(CommonConstants.ENTERPRISE_INFO_PREFIX);//企业客户编号生成规则
				enterpriseInfo.setCustomerId(sCustomerId);
				enterpriseInfo.setCertificateType(Integer.parseInt(req.getCertificateType()));
				enterpriseInfo.setCertificateNo(req.getCertificateNo());
				enterpriseInfo.setCertificateExpireDate(DateUtil.getDate(req.getCertExpDate(), DateUtil.DATAFORMAT_STR));
				enterpriseInfo.setPhoneNo(req.getPhone());
				enterpriseInfo.setLegalPersonCertificateType(Integer.parseInt(req.getLegalPersonCertificateType()));
				enterpriseInfo.setLegalPersonCertificateNo(req.getLegalPersonCertificateNo());
				enterpriseInfo.setLegalPersonCertificateExpireDate(DateUtil.getDate(req.getLegalPersonCertExpDate(), 
						DateUtil.DATAFORMAT_STR));
				enterpriseInfo.setLegalPersonName(req.getLegalPersonName());
				enterpriseInfo.setEnterpriseName(req.getEnterpriseName());
				enterpriseInfo.setEnterpriseRegisterAddr(req.getRegisterAddress());
				enterpriseInfo.setPostCode(req.getPostCode());
				enterpriseInfo.setCreateTime(new Date());
				
				EnterpriseMember enterpriseMember = new EnterpriseMember();
				sMemberId = BusinessFlowUtils.generate(CommonConstants.ENTERPRISE_MEMBER_PREFIX);//企业会员编号生成规则
				enterpriseMember.setRegisterSource(RegisterSource.PC.code());//默认PC端  1：pc端；2：Android客户端；3：IOS客户端
				enterpriseMember.setCustomerId(sCustomerId);
				enterpriseMember.setMemberId(sMemberId);
				sCustomerNo = getCustomerNo();
				enterpriseMember.setCustomerNo(sCustomerNo);
				enterpriseMember.setStatus(UserStatusCodeEnum.NORMAL.getCode());//会员状态默认为正常状态
				enterpriseMember.setCreateTime(new Date());
				enterpriseMember.setGrade(CommonConstants.ENTERPRISE_MEMBER_GRADE);
				
				EnterpriseOperator enterpriseOperator = new EnterpriseOperator();
				PropertyUtils.copyProperties(enterpriseOperator, req);
				enterpriseOperator.setPassword(StringUtils.upperCase(req.getPassword()));
				enterpriseOperator.setMemberId(sMemberId);
				
				if(StringUtils.isNotBlank(req.getMobile())){
					enterpriseOperator.setUserName(req.getMobile());
				}else{
					enterpriseOperator.setUserName(req.getEmail());
				}
				
				enterpriseOperator.setStatus(UserStatusCodeEnum.NORMAL.getCode());//默认操作员是正常状态
				enterpriseOperator.setCreateTime(new Date());
			
				enterpriseInfoService.register(enterpriseInfo, enterpriseMember, enterpriseOperator);
				
				operatorId = enterpriseOperator.getId();
				
			}else{//已经注册过客户信息相当于新开一个会员
				sCustomerId = returnEnterpriseInfo.getCustomerId();
				
				EnterpriseMember enterpriseMember = new EnterpriseMember();
				sMemberId = BusinessFlowUtils.generate(CommonConstants.ENTERPRISE_MEMBER_PREFIX);//企业会员编号生成规则
				enterpriseMember.setRegisterSource(1);//默认PC端  1：pc端；2：Android客户端；3：IOS客户端
				enterpriseMember.setCustomerId(returnEnterpriseInfo.getCustomerId());
				enterpriseMember.setMemberId(sMemberId);
				sCustomerNo = getCustomerNo();
				enterpriseMember.setCustomerNo(sCustomerNo);
				enterpriseMember.setStatus(UserStatusCodeEnum.NORMAL.getCode());//会员状态默认为正常状态
				enterpriseMember.setCreateTime(new Date());
				enterpriseMember.setGrade(CommonConstants.ENTERPRISE_MEMBER_GRADE);
				
				EnterpriseOperator enterpriseOperator = new EnterpriseOperator();
				PropertyUtils.copyProperties(enterpriseOperator, req);
				enterpriseOperator.setPassword(StringUtils.upperCase(req.getPassword()));
				enterpriseOperator.setMemberId(sMemberId);
				if(StringUtils.isNotBlank(req.getMobile())){
					enterpriseOperator.setUserName(req.getMobile());
				}else{
					enterpriseOperator.setUserName(req.getEmail());
				}
				
				enterpriseOperator.setStatus(UserStatusCodeEnum.NORMAL.getCode());//默认操作员是正常状态
				enterpriseOperator.setCreateTime(new Date());
				
				enterpriseInfoService.register(enterpriseMember, enterpriseOperator);
				
				operatorId = enterpriseOperator.getId();
				
			}
			
			resp = new EnterpriseRegisterResponse(RespCode.SUCCESS.code(),ResultCode.SUCCESS.code(),ResultCode.SUCCESS.desc());
			resp.setCustomerId(sCustomerId);
			resp.setMemberId(sMemberId);
			resp.setCustomerNo(sCustomerNo);
			resp.setOperatorId(operatorId);
			
		}catch(Exception e){
			log.error(e.getMessage(), e);
			
			if (e instanceof BusinessException) {
				
                resp = new EnterpriseRegisterResponse(RespCode.SUCCESS.code(),ResultCode.FAIL.code(),
                		e.getMessage());
                
            } else {
            	
                resp = new EnterpriseRegisterResponse(RespCode.FAIL.code(),RespCode.FAIL.desc());
            }
		}
		log.info("EnterpriseServiceFacadeImpl.register.resp:" + JSON.toJSONString(resp));
		
		return resp;
	
	}

	/**
	 * 获取商户编号
	 * @throws Exception 
	 */
	
	public String getCustomerNo() throws Exception{
		
		String sCustomerNo = businessFlowNoUtils.generateSerialNo("", CommonConstants.ENTERPRISE_MEMBER,
				                                                      CommonConstants.ENTERPRISE_MEMBER_CUSTOMER_NO, 
				                                                      CommonConstants.ENTERPRISE_MEMBER_CUSTOMER_NO_NO_FORMAT,
				                                                      CommonConstants.ENTERPRISE_MEMBER_CUSTOMER_NO_START_ID);
		
		return sCustomerNo;
	}
	
	/**
	 * 企业会员添加操作员.
	 * @see com.zillionfortune.cif.facade.user.EnterpriseServiceFacade#registerOpertor(com.zillionfortune.cif.facade.user.dto.EnterpriseRegisterOpertorRequest)
	 */
	@Override
	public EnterpriseRegisterOpertorResponse registerOpertor(EnterpriseRegisterOpertorRequest req) {
		log.info("EnterpriseServiceFacadeImpl.registerOpertor.req:" + JSON.toJSONString(req));

		EnterpriseRegisterOpertorResponse resp;
		
		try{
			
			//======1.请求参数校验
			paraChecker.checkEnterpriseRegisterOpertorRequest(req);
			
			//======2.校验商户号是否存在
			EnterpriseMember enterpriseMember = new EnterpriseMember();
			enterpriseMember.setCustomerNo(req.getCustomerNo());
			List<EnterpriseMember> enterpriseMemberList= enterpriseMemberService.queryList(enterpriseMember);
			if(CollectionUtils.isEmpty(enterpriseMemberList)){//商户号不存在
				
				resp = new EnterpriseRegisterOpertorResponse(RespCode.SUCCESS.code(),ResultCode.CUSTOMERNO_HAS_NOT_EXISTS.code(),
						ResultCode.CUSTOMERNO_HAS_NOT_EXISTS.desc());
				
				log.info("EnterpriseServiceFacadeImpl.registerOpertor.resp:" + JSON.toJSONString(resp));
				return resp;
				
			}
			
			//======3.校验会员状态是否正常
			enterpriseMember = enterpriseMemberList.get(0);
			if (enterpriseMember.getStatus() == null || UserStatusCodeEnum.NORMAL.getCode() != enterpriseMember.getStatus() ) {
				
				resp = new EnterpriseRegisterOpertorResponse(RespCode.SUCCESS.code(),ResultCode.EXCEPTION_STATUS_CAN_NOT_REGISGER_OPERATOR.code(), 
						ResultCode.EXCEPTION_STATUS_CAN_NOT_REGISGER_OPERATOR.desc());

				log.info("EnterpriseServiceFacadeImpl.registerOpertor.resp:" + JSON.toJSONString(resp));

				return resp;
			}
			
			//======4.校验操作员的唯一性
			
			String userName = "";
			if(StringUtils.isNotBlank(req.getMobile()) && StringUtils.isNotBlank(req.getEmail())){//两者都有值得时候默认邮箱是登录名
				userName = req.getEmail();
			}else if(StringUtils.isNotBlank(req.getEmail())){
				userName = req.getEmail();
			}else{
				userName = req.getMobile();
			}
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("memberId", enterpriseMember.getMemberId());
			map.put("userName", userName);
			List<EnterpriseOperator> listEnterpriseOperator = enterpriseOperatorService.queryEnterpriseOperatorByCriteria(map);
			if(CollectionUtils.isNotEmpty(listEnterpriseOperator)){
				resp = new EnterpriseRegisterOpertorResponse(RespCode.SUCCESS.code(),ResultCode.CUSTOMERNO_HAS_INCLUDE_USER_NAME.code(),
						ResultCode.CUSTOMERNO_HAS_INCLUDE_USER_NAME.desc());
				
				log.info("EnterpriseServiceFacadeImpl.registerOpertor.resp:" + JSON.toJSONString(resp));
				return resp;
			}
			//======4.落地操作员信息
			EnterpriseOperator enterpriseOperator = new EnterpriseOperator();
			PropertyUtils.copyProperties(enterpriseOperator, req);
			enterpriseOperator.setUserName(userName);
			enterpriseOperator.setPassword(StringUtils.upperCase(req.getPassword()));
			enterpriseOperator.setMemberId(enterpriseMember.getMemberId());
			enterpriseOperator.setStatus(UserStatusCodeEnum.NORMAL.getCode());//默认操作员是正常状态
			enterpriseOperator.setCreateTime(new Date());
			
			enterpriseOperatorService.insertSelective(enterpriseOperator); //插入操作员信息
			
			resp = new EnterpriseRegisterOpertorResponse(RespCode.SUCCESS.code(),ResultCode.SUCCESS.code(),ResultCode.SUCCESS.desc());
			resp.setCustomerId(enterpriseMember.getCustomerId());
			resp.setMemberId(enterpriseMember.getMemberId());
			resp.setCustomerNo(req.getCustomerNo());
			resp.setOperatorId(enterpriseOperator.getId());
			
		}catch(Exception e){
			log.error(e.getMessage(), e);
			
			if (e instanceof BusinessException) {
                
                resp = new EnterpriseRegisterOpertorResponse(RespCode.SUCCESS.code(),ResultCode.FAIL.code(),e.getMessage());
            } else {
                
                resp = new EnterpriseRegisterOpertorResponse(RespCode.FAIL.code(),RespCode.FAIL.desc());
            }
		}
		log.info("EnterpriseServiceFacadeImpl.registerOpertor.resp:" + JSON.toJSONString(resp));
		
		return resp;
	}
	
	/**
	 * 企业客户注册信息修改.
	 * @see com.zillionfortune.cif.facade.user.EnterpriseServiceFacade#registerInfoUpdate(com.zillionfortune.cif.facade.user.dto.EnterpriseRegisterInfoUpdateRequest)
	 */
	@Override
	public EnterpriseRegisterInfoUpdateResponse registerInfoUpdate(EnterpriseRegisterInfoUpdateRequest req) {

		log.info("EnterpriseServiceFacadeImpl.registerInfoUpdate.req:" + JSON.toJSONString(req));

		EnterpriseRegisterInfoUpdateResponse resp;
		
		try{
			
			//1.======请求参数校验
			paraChecker.checkEnterpriseRegisterInfoUpdateRequest(req);
			Long operatorId = Long.valueOf(req.getOperatorId());
			
			//2.======校验operatorId是否正确
			EnterpriseOperator eOperator = enterpriseOperatorService.queryByOperatorId(operatorId);
			if(eOperator == null){
				throw new BusinessException("operatorId="+req.getOperatorId()+"不正确,无法更新");
			}
			
			//======3.校验操作员状态是否正常
			if (eOperator.getStatus() == null || UserStatusCodeEnum.NORMAL.getCode() != eOperator.getStatus() ) {
				
				resp = new EnterpriseRegisterInfoUpdateResponse(RespCode.SUCCESS.code(),ResultCode.EXCEPTION_STATUS_CAN_NOT_UPDATE_OPERATOR_INFO.code(), 
						ResultCode.EXCEPTION_STATUS_CAN_NOT_UPDATE_OPERATOR_INFO.desc());

				log.info("EnterpriseServiceFacadeImpl.registerInfoUpdate.resp:" + JSON.toJSONString(resp));

				return resp;
			}
			
			//4.======更新操作员信息
			EnterpriseOperator enterpriseOperator = new EnterpriseOperator();
			PropertyUtils.copyProperties(enterpriseOperator, req);
			enterpriseOperator.setId(operatorId);
			
			enterpriseOperatorService.updateByPrimaryKeySelective(enterpriseOperator);
			
			resp = new EnterpriseRegisterInfoUpdateResponse(RespCode.SUCCESS.code(),ResultCode.SUCCESS.code(),ResultCode.SUCCESS.desc());
			resp.setMemberId(req.getMemberId());
			resp.setOperatorId(operatorId);
			
		}catch(Exception e){
			 log.error(e.getMessage(), e);
			 
			if (e instanceof BusinessException) {
               
                resp = new EnterpriseRegisterInfoUpdateResponse(RespCode.SUCCESS.code(),ResultCode.FAIL.code(),e.getMessage());
            } else {
                
                resp = new EnterpriseRegisterInfoUpdateResponse(RespCode.FAIL.code(),RespCode.FAIL.desc());
            }
		}
		log.info("EnterpriseServiceFacadeImpl.registerInfoUpdate.resp:" + JSON.toJSONString(resp));
		
		return resp;
	
	}
	
	/**
	 * 企业客户扩展信息修改.
	 * @see com.zillionfortune.cif.facade.user.EnterpriseServiceFacade#enterpriseInfoUpdate(com.zillionfortune.cif.facade.user.dto.EnterpriseInfoUpdateRequest)
	 */
	@Override
	public EnterpriseInfoUpdateResponse enterpriseInfoUpdate(EnterpriseInfoUpdateRequest req) {
		log.info("EnterpriseServiceFacadeImpl.enterpriseInfoUpdate.req:" + JSON.toJSONString(req));

		EnterpriseInfoUpdateResponse resp;
		
		try{
			//1.======请求参数校验
			paraChecker.checkEnterpriseInfoUpdateRequest(req);
			
			//2.======更新企业扩展信息
			EnterpriseInfo eInfo = enterpriseInfoService.queryByMemberId(req.getMemberId());
			if(eInfo == null){
				throw new BusinessException("memberId="+req.getMemberId()+"不正确,无法更新");
			}
			
			//3.======通过证件类型和证件号码来校验该客户是否落地过客户信息
			if(req.getCertificateType() != null && StringUtils.isNotBlank(req.getCertificateNo())){
				EnterpriseInfo entInfo = new EnterpriseInfo();
				
				entInfo.setCertificateType(req.getCertificateType());
				entInfo.setCertificateNo(req.getCertificateNo());
				EnterpriseInfo returnEnterpriseInfo = enterpriseInfoService.queryEnterpriseInfo(entInfo);
				if(returnEnterpriseInfo != null && 
						!returnEnterpriseInfo.getCustomerId().equals(eInfo.getCustomerId())){//已存在证件类型证件号码不是当前被更新企业
					
					resp = new EnterpriseInfoUpdateResponse(RespCode.SUCCESS.code(),ResultCode.EXISTS_CERTICATE_TYPE_AND_NO.code(), 
							ResultCode.EXISTS_CERTICATE_TYPE_AND_NO.desc());

					log.info("EnterpriseServiceFacadeImpl.enterpriseInfoUpdate.resp:" + JSON.toJSONString(resp));

					return resp;
				}
			}
			
			//4.======更新企业扩展信息
			EnterpriseInfo enterpriseInfo = new EnterpriseInfo();
			enterpriseInfo.setCertificateType(req.getCertificateType());
			enterpriseInfo.setCertificateNo(req.getCertificateNo());
			enterpriseInfo.setCustomerId(eInfo.getCustomerId());
			enterpriseInfo.setEnterpriseName(req.getEnterpriseName());
			enterpriseInfo.setLegalPersonName(req.getLegalPersonName());
			enterpriseInfo.setLegalPersonCertificateType(req.getLegalPersonCertificateType());
			enterpriseInfo.setLegalPersonCertificateNo(req.getLegalPersonCertificateNo());
			enterpriseInfo.setLegalPersonCertificateExpireDate(DateUtil.getDate(req.getLegalPersonCertExpDate(),
					DateUtil.DATAFORMAT_STR));
			enterpriseInfo.setCertificateExpireDate(DateUtil.getDate(req.getCertExpDate(),DateUtil.DATAFORMAT_STR));
	        enterpriseInfo.setEnterpriseRegisterAddr(req.getRegisterAddress());
	        enterpriseInfo.setPostCode(req.getPostCode());
	        enterpriseInfo.setModifyTime(new Date());
	        enterpriseInfo.setPhoneNo(req.getPhone());
	        enterpriseInfo.setEnterpriseType(req.getEnterpriseType());
	        enterpriseInfo.setIndustry(req.getIndustry());
	        
	        enterpriseInfoService.updateByCustomerIdSelective(enterpriseInfo);
			
			resp = new EnterpriseInfoUpdateResponse(RespCode.SUCCESS.code(),ResultCode.SUCCESS.code(),ResultCode.SUCCESS.desc());
			resp.setMemberId(req.getMemberId());
			
		}catch(Exception e){
			 log.error(e.getMessage(), e);
			 
			if (e instanceof BusinessException) {
               
                resp = new EnterpriseInfoUpdateResponse(RespCode.SUCCESS.code(),ResultCode.FAIL.code(),e.getMessage());
            } else {
                
                resp = new EnterpriseInfoUpdateResponse(RespCode.FAIL.code(),RespCode.FAIL.desc());
            }
		}
		log.info("EnterpriseServiceFacadeImpl.enterpriseInfoUpdate.resp:" + JSON.toJSONString(resp));
		
		return resp;
	}

}
