/*
 * Copyright (c) 2016, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 *
 *
 */
package com.zillionfortune.cif.biz.user.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.zillionfortune.cif.biz.user.check.EnterpriseUserInfoQueryServiceParameterChecker;
import com.zillionfortune.cif.common.enums.AuthGrade;
import com.zillionfortune.cif.common.enums.RespCode;
import com.zillionfortune.cif.common.enums.ResultCode;
import com.zillionfortune.cif.common.enums.UserStatusCodeEnum;
import com.zillionfortune.cif.common.exception.BusinessException;
import com.zillionfortune.cif.common.util.BeanConvertUtil;
import com.zillionfortune.cif.common.util.StringUtils;
import com.zillionfortune.cif.dal.entity.EnterpriseInfo;
import com.zillionfortune.cif.dal.entity.EnterpriseInfoMember;
import com.zillionfortune.cif.dal.entity.EnterpriseMember;
import com.zillionfortune.cif.dal.entity.EnterpriseOperator;
import com.zillionfortune.cif.dal.entity.EnterpriseQualification;
import com.zillionfortune.cif.facade.common.dto.BasePageResponse;
import com.zillionfortune.cif.facade.user.EnterpriseUserInfoQueryServiceFacade;
import com.zillionfortune.cif.facade.user.dto.EntUserAuthInfoByPageQueryDto;
import com.zillionfortune.cif.facade.user.dto.EntUserAuthInfoByPageQueryRequest;
import com.zillionfortune.cif.facade.user.dto.EntUserAuthInfoQueryResponse;
import com.zillionfortune.cif.facade.user.dto.EntUserQueryInfoByPageQueryDto;
import com.zillionfortune.cif.facade.user.dto.EntUserQueryInfoByPageQueryRequest;
import com.zillionfortune.cif.facade.user.dto.EnterpriseExtInfoQueryResponse;
import com.zillionfortune.cif.facade.user.dto.EnterpriseInfoQueryByCertTypeNoRequest;
import com.zillionfortune.cif.facade.user.dto.EnterpriseInfoResponse;
import com.zillionfortune.cif.facade.user.dto.EnterpriseUserInfoQueryRequest;
import com.zillionfortune.cif.facade.user.dto.EnterpriseUserNameFindResponse;
import com.zillionfortune.cif.facade.user.dto.OperatorInfoQueryDto;
import com.zillionfortune.cif.facade.user.dto.OperatorInfoQueryResponse;
import com.zillionfortune.cif.service.user.EnterpriseInfoService;
import com.zillionfortune.cif.service.user.EnterpriseMemberService;
import com.zillionfortune.cif.service.user.EnterpriseOperatorService;
import com.zillionfortune.cif.service.user.EnterpriseQualificationService;
import com.zillionfortune.cif.support.common.BeanUtilsWrapper;

/**
 * ClassName: EnterpriseUserInfoQueryServiceFacade <br/>
 * Function: 企业会员查询服务实现. <br/>
 * Date: 2016年11月21日 上午10:17:30 <br/>
 *
 * @author pengting
 * @version
 * @since JDK 1.7
 */
@Component
public class EnterpriseUserInfoQueryServiceFacadeImpl implements EnterpriseUserInfoQueryServiceFacade {

	private static Logger log = LoggerFactory.getLogger(EnterpriseUserInfoQueryServiceFacadeImpl.class);
	
	@Autowired
	private EnterpriseInfoService enterpriseInfoService;
	
	@Autowired
	private EnterpriseMemberService enterpriseMemberService;
	
	@Autowired
	private EnterpriseOperatorService enterpriseOperatorService;
	
	@Autowired
	private EnterpriseQualificationService enterpriseQualificationService;
	
	@Autowired
	private EnterpriseUserInfoQueryServiceParameterChecker enterpriseUserInfoQueryServiceParameterChecker;

	@Override
	public OperatorInfoQueryResponse queryUserInfo(
			EnterpriseUserInfoQueryRequest request) {
		log.info("queryUserInfo.req:" + JSONObject.toJSONString(request));
		OperatorInfoQueryResponse resp = null;
		try {
			// 参数校验
			enterpriseUserInfoQueryServiceParameterChecker.checkQueryUserInfoRequest(request);

			Map map = BeanConvertUtil.beanToMapWithoutNullValueMap(request);
			List<EnterpriseOperator> rsList = enterpriseOperatorService.queryEnterpriseOperatorByCriteria(map);
			
			resp = new OperatorInfoQueryResponse(RespCode.SUCCESS.code(),ResultCode.SUCCESS.code(), ResultCode.SUCCESS.desc());
			BeanUtilsWrapper.copyProperties(resp, request);
			if (rsList != null && !rsList.isEmpty()) {
				for (EnterpriseOperator enterpriseOperator : rsList) {
					OperatorInfoQueryDto operatorInfoQueryDto = new OperatorInfoQueryDto();
					BeanUtilsWrapper.copyProperties(operatorInfoQueryDto, enterpriseOperator);
					operatorInfoQueryDto.setOperatorId(enterpriseOperator.getId());
					resp.getData().add(operatorInfoQueryDto);
				}
			}
		} catch (BusinessException e) {
			log.error(e.getMessage(), e);
			resp = new OperatorInfoQueryResponse(RespCode.SUCCESS.code(), ResultCode.FAIL.code(), e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			resp = new OperatorInfoQueryResponse(RespCode.FAIL.code(), RespCode.FAIL.desc());
		}
		log.info("queryUserInfo.resp:" + JSONObject.toJSONString(resp));
		return resp;
	}

	@Override
	public EnterpriseExtInfoQueryResponse queryInfo(
			EnterpriseUserInfoQueryRequest request) {
		log.info("queryInfo.req:" + JSONObject.toJSONString(request));
		EnterpriseExtInfoQueryResponse resp = null;
		try {
			// 参数校验
			enterpriseUserInfoQueryServiceParameterChecker.checkQueryInfoRequest(request);

			EnterpriseInfo enterpriseInfo = null;
			if (StringUtils.isNotEmpty(request.getMemberId())) {
				enterpriseInfo = enterpriseInfoService.queryByMemberId(request.getMemberId());
				// 继续比对CustomerID是否一致
				if (enterpriseInfo != null && StringUtils.isNotEmpty(request.getCustomerId()) && 
						!enterpriseInfo.getCustomerId().equals(request.getCustomerId())) {
					enterpriseInfo = null;
				}
			} else {
				enterpriseInfo = enterpriseInfoService.queryByCustomerId(request.getCustomerId());
			}
			// 客户信息对象不存在
			if (enterpriseInfo == null) {
				resp = new EnterpriseExtInfoQueryResponse(RespCode.SUCCESS.code(), ResultCode.CUSTOMER_NOT_FOUND.code(),
						ResultCode.CUSTOMER_NOT_FOUND.desc());
				BeanUtilsWrapper.copyProperties(resp, request);
			} else {
				resp = new EnterpriseExtInfoQueryResponse(RespCode.SUCCESS.code(), ResultCode.SUCCESS.code(),
						ResultCode.SUCCESS.desc());
				BeanUtilsWrapper.copyProperties(resp, enterpriseInfo);
				// 如果是通过memberID来查询 则可以查询CustomerNo
				if (StringUtils.isNotEmpty(request.getMemberId())) {
					EnterpriseMember member = enterpriseMemberService.queryByMemberId(request.getMemberId());
					resp.setCustomerNo(member.getCustomerNo());
				}
				resp.setMemberId(request.getMemberId());
			}
		} catch (BusinessException e) {
			log.error(e.getMessage(), e);
			resp = new EnterpriseExtInfoQueryResponse(RespCode.SUCCESS.code(), ResultCode.FAIL.code(), e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			resp = new EnterpriseExtInfoQueryResponse(RespCode.FAIL.code(), RespCode.FAIL.desc());
		}
		log.info("queryInfo.resp:" + JSONObject.toJSONString(resp));
		return resp;
	}

	@Override
	public EntUserAuthInfoQueryResponse queryUserAuthInfo(
			EnterpriseUserInfoQueryRequest request) {
		log.info("queryUserAuthInfo.req:" + JSONObject.toJSONString(request));
		EntUserAuthInfoQueryResponse resp = null;
		try {
			// 参数校验
			enterpriseUserInfoQueryServiceParameterChecker.checkQueryUserAuthInfoRequest(request);

			EnterpriseMember enterpriseMember = enterpriseMemberService.queryByMemberId(request.getMemberId());
			// 会员不存在
			if (enterpriseMember == null) {
				resp = new EntUserAuthInfoQueryResponse(RespCode.SUCCESS.code(),
						ResultCode.USER_NOT_FOUND.code(), ResultCode.USER_NOT_FOUND.desc());
				BeanUtilsWrapper.copyProperties(resp, request);
			} else {
				resp = new EntUserAuthInfoQueryResponse(RespCode.SUCCESS.code(), ResultCode.SUCCESS.code(),
						ResultCode.SUCCESS.desc());
				BeanUtilsWrapper.copyProperties(resp, enterpriseMember);
				
				// 认证等级取用户等级串的第一位
				resp.setAuthGrade(Integer.parseInt(enterpriseMember.getGrade()
						.substring(0, 1)));
				// 如果已认证
				if (resp.getAuthGrade() != Integer.valueOf((AuthGrade.UNAUTHORIZED.getCode()))) {
					EnterpriseInfo enterpriseInfo = enterpriseInfoService.queryByMemberId(request.getMemberId());
					BeanUtilsWrapper.copyProperties(resp, enterpriseInfo);
					
					EnterpriseQualification enterpriseQualification = 
							enterpriseQualificationService.selectByCustormerId(enterpriseInfo.getCustomerId());
					BeanUtilsWrapper.copyProperties(resp, enterpriseQualification);
				}
			}
		} catch (BusinessException e) {
			log.error(e.getMessage(), e);
			resp = new EntUserAuthInfoQueryResponse(RespCode.SUCCESS.code(), ResultCode.FAIL.code(), e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			resp = new EntUserAuthInfoQueryResponse(RespCode.FAIL.code(), RespCode.FAIL.desc());
		}
		log.info("queryUserAuthInfo.resp:" + JSONObject.toJSONString(resp));
		return resp;
	}
	
	/**
	 * @see com.zillionfortune.cif.facade.user.EnterpriseUserInfoQueryServiceFacade#queryUserAuthInfoByPage(com.zillionfortune.cif.facade.user.dto.EntUserAuthInfoByPageQueryRequest)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public BasePageResponse queryUserAuthInfoByPage(EntUserAuthInfoByPageQueryRequest req) {
		log.info("queryUserAuthInfoByPage.req:" + JSONObject.toJSONString(req));
		BasePageResponse resp = null;
		try {
			if (req == null) {
				throw new BusinessException("请求对象不能为空");
			}
			
			int pageSize = req.getPageSize();
			int currentPage = req.getCurrentPage();
			
			// 封装查询参数
			Map paramMap = BeanConvertUtil.beanToMapWithoutNullValueMap(req);
			
			resp = new BasePageResponse(RespCode.SUCCESS.code(), ResultCode.SUCCESS.code(), ResultCode.SUCCESS.desc());
			// 查询
			long total = enterpriseMemberService.queryInfoByPageCount(paramMap);
			if (total > 0) {
				List<EnterpriseInfoMember> rsList = enterpriseMemberService.queryInfoByPage(paramMap);
				// 转换结果集
				for (EnterpriseInfoMember enterpriseInfoMember : rsList) {
					EntUserAuthInfoByPageQueryDto entUserAuthInfoByPageQueryDto = new EntUserAuthInfoByPageQueryDto();
					BeanUtilsWrapper.copyProperties(entUserAuthInfoByPageQueryDto, enterpriseInfoMember);
					entUserAuthInfoByPageQueryDto.setCertExpDate(enterpriseInfoMember.getCertificateExpireDate());
					resp.getData().add(entUserAuthInfoByPageQueryDto);
				}
				
			}
			resp.setTotal(total);
			resp.setPageSize(pageSize);
			resp.setCurrentPage(currentPage);
			
		} catch (BusinessException e) {
			log.error(e.getMessage(), e);
			resp = new BasePageResponse(RespCode.SUCCESS.code(), ResultCode.FAIL.code(), e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			resp = new BasePageResponse(RespCode.FAIL.code(), RespCode.FAIL.desc());
		}
		log.info("queryUserAuthInfoByPage.resp:" + JSONObject.toJSONString(resp));
		return resp;
	}
	
	/**
	 * @see com.zillionfortune.cif.facade.user.EnterpriseUserInfoQueryServiceFacade#queryInfoByPage(com.zillionfortune.cif.facade.user.dto.EntUserQueryInfoByPageQueryRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public BasePageResponse queryInfoByPage(EntUserQueryInfoByPageQueryRequest req) {
		log.info("queryInfoByPage.req:" + JSONObject.toJSONString(req));
		BasePageResponse resp = null;
		try {
			if (req == null) {
				throw new BusinessException("请求对象不能为空");
			}
			
			int pageSize = req.getPageSize();
			int currentPage = req.getCurrentPage();
			
			// 封装查询参数
			Map paramMap = BeanConvertUtil.beanToMapWithoutNullValueMap(req);
			
			resp = new BasePageResponse(RespCode.SUCCESS.code(), ResultCode.SUCCESS.code(), ResultCode.SUCCESS.desc());
			// 查询
			long total = enterpriseMemberService.queryInfoByPageCount(paramMap);
			if (total > 0) {
				List<EnterpriseInfoMember> rsList = enterpriseMemberService.queryInfoByPage(paramMap);
				// 转换结果集
				for (EnterpriseInfoMember enterpriseInfoMember : rsList) {
					EntUserQueryInfoByPageQueryDto entUserQueryInfoByPageQueryDto = new EntUserQueryInfoByPageQueryDto();
					BeanUtilsWrapper.copyProperties(entUserQueryInfoByPageQueryDto, enterpriseInfoMember);
					entUserQueryInfoByPageQueryDto.setCertExpDate(enterpriseInfoMember.getCertificateExpireDate());
					resp.getData().add(entUserQueryInfoByPageQueryDto);
				}
			}
			resp.setTotal(total);
			resp.setPageSize(pageSize);
			resp.setCurrentPage(currentPage);
			
		} catch (BusinessException e) {
			log.error(e.getMessage(), e);
			resp = new BasePageResponse(RespCode.SUCCESS.code(), ResultCode.FAIL.code(), e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			resp = new BasePageResponse(RespCode.FAIL.code(), RespCode.FAIL.desc());
		}
		log.info("queryInfoByPage.resp:" + JSONObject.toJSONString(resp));
		return resp;
	}
	
	@Override
	public EnterpriseUserNameFindResponse findUserNameIsExist(String userName) {
		log.info("findUserNameIsExist.req: userName=" + userName);
		EnterpriseUserNameFindResponse resp = null;
		try {
			if (StringUtils.isEmpty(userName)) {
				throw new BusinessException("userName 不能为空");
			}
			
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("userName", userName);
			boolean existFlag = false;
			List<EnterpriseOperator> rsList = enterpriseOperatorService.queryEnterpriseOperatorByCriteria(paramMap);
			// 如果能查到记录 并且状态不为注销 即视为存在
			if (rsList != null && rsList.size() > 0) {
				for (EnterpriseOperator enterpriseOperator : rsList) {
					if (enterpriseOperator.getStatus() != UserStatusCodeEnum.CANCEL.getCode()) {
						existFlag = true;
						break;
					}
				}
			}
			
			resp = new EnterpriseUserNameFindResponse(RespCode.SUCCESS.code(), ResultCode.SUCCESS.code(), ResultCode.SUCCESS.desc());
			resp.setExistFlag(existFlag);
		} catch (BusinessException e) {
			log.error(e.getMessage(), e);
			resp = new EnterpriseUserNameFindResponse(RespCode.SUCCESS.code(), ResultCode.FAIL.code(), e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			resp = new EnterpriseUserNameFindResponse(RespCode.FAIL.code(), RespCode.FAIL.desc());
		} finally {
			log.info("findUserNameIsExist.resp:" + JSONObject.toJSONString(resp));
		}
		return resp;
	}

	/**
	 * 根据证件类型证件号码查询企业信息.
	 * @see com.zillionfortune.cif.facade.user.EnterpriseUserInfoQueryServiceFacade#queryInfoByCertTypeAndNo(com.zillionfortune.cif.facade.user.dto.EnterpriseInfoQueryByCertTypeNoRequest)
	 */
	@Override
	public EnterpriseInfoResponse queryInfoByCertTypeAndNo(EnterpriseInfoQueryByCertTypeNoRequest request) {

		log.info("queryInfoByCertTypeAndNo.req:" + JSONObject.toJSONString(request));
		EnterpriseInfoResponse resp = null;
		
		try {
			
			//1.======请求参数校验 
			enterpriseUserInfoQueryServiceParameterChecker.checkQueryInfoByCertTypeAndNoRequest(request);

			//2.======通过证件类型和证件号码查询客户信息
			EnterpriseInfo enterpriseInfo = new EnterpriseInfo();
			enterpriseInfo.setCertificateType(request.getCertificateType());
			enterpriseInfo.setCertificateNo(request.getCertificateNo());
			EnterpriseInfo returnEnterpriseInfo = enterpriseInfoService.queryEnterpriseInfo(enterpriseInfo);
			
			//3.======处理反馈结果
			if (returnEnterpriseInfo == null) {
				
				resp = new EnterpriseInfoResponse(RespCode.SUCCESS.code(), ResultCode.CUSTOMER_NOT_FOUND.code(),
						ResultCode.CUSTOMER_NOT_FOUND.desc());
				BeanUtilsWrapper.copyProperties(resp, request);
				
			} else {
				
				resp = new EnterpriseInfoResponse(RespCode.SUCCESS.code(), ResultCode.SUCCESS.code(),
						ResultCode.SUCCESS.desc());
				BeanUtilsWrapper.copyProperties(resp, request);
			}
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			
			if (e instanceof BusinessException) {
				
                resp = new EnterpriseInfoResponse(RespCode.SUCCESS.code(),ResultCode.FAIL.code(),
                		e.getMessage());
                
            } else {
            	
                resp = new EnterpriseInfoResponse(RespCode.FAIL.code(),RespCode.FAIL.desc());
            }
		} finally {
			
			log.info("queryInfoByCertTypeAndNo.resp:" + JSONObject.toJSONString(resp));
		}
		
		return resp;
	
	}
}
