/*
 * Copyright (c) 2016, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 *
 *
 */
package com.zillionfortune.cif.biz.user.impl;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.zillionfortune.cif.biz.user.check.EnterpriseStatusServiceParameterChecker;
import com.zillionfortune.cif.common.constants.CommonConstants;
import com.zillionfortune.cif.common.enums.RespCode;
import com.zillionfortune.cif.common.enums.ResultCode;
import com.zillionfortune.cif.common.enums.UserStatusCodeEnum;
import com.zillionfortune.cif.common.exception.BusinessException;
import com.zillionfortune.cif.dal.entity.EnterpriseMember;
import com.zillionfortune.cif.dal.entity.EnterpriseOperator;
import com.zillionfortune.cif.facade.user.EnterpriseUserStatusServiceFacade;
import com.zillionfortune.cif.facade.user.dto.EnterpriseStatusUpdateRequest;
import com.zillionfortune.cif.facade.user.dto.EnterpriseStatusUpdateResponse;
import com.zillionfortune.cif.service.user.EnterpriseMemberService;
import com.zillionfortune.cif.service.user.EnterpriseOperatorService;
import com.zillionfortune.cif.service.user.EnterpriseStatusService;

/**
 * ClassName: EnterpriseStatusServiceFacadeImpl <br/>
 * Function: 企业会员冻结/解冻/注销业务 对外接口实现. <br/>
 * Date: 2016年12月1日 下午5:47:03 <br/>
 *
 * @author wangzinan_tech@zillionfortune.com
 * @version 
 * @since JDK 1.7
 */
@Component
public class EnterpriseUserStatusServiceFacadeImpl implements EnterpriseUserStatusServiceFacade {

	private static Logger log = LoggerFactory.getLogger(EnterpriseUserStatusServiceFacadeImpl.class);

	@Autowired
	private EnterpriseMemberService enterpriseMemberService;
	
	@Autowired
	private EnterpriseOperatorService enterpriseOperatorService;
	
	@Autowired
	private EnterpriseStatusService enterpriseStatusService;

	@Autowired
	private EnterpriseStatusServiceParameterChecker parameterChecker;
	
	/**
	 * 企业会员冻结.
	 * 1、operatorId为空时，冻结企业，并冻结企业下的所有操作员.
	 * 2、operatorId均不为空时，冻结企业下的操作员.
	 * @see com.zillionfortune.cif.facade.user.EnterpriseStatusServiceFacade#enterpriseFrozen(com.zillionfortune.cif.facade.user.dto.EnterpriseStatusUpdateRequest)
	 */
	@Override
	public EnterpriseStatusUpdateResponse enterpriseFrozen(EnterpriseStatusUpdateRequest req) {
		log.info("EnterpriseStatusServiceFacadeImpl.enterpriseFrozen.req:" + JSON.toJSONString(req));
		// 响应结果定义
		EnterpriseStatusUpdateResponse resp;
		
		try {
			// 请求参数校验
			parameterChecker.checkEnterpriseStatusUpdateRequest(req);

			// 验证会员是否存在
			EnterpriseMember currentEnterpriseMember = enterpriseMemberService.queryByMemberId(req.getMemberId());
			if (currentEnterpriseMember == null) {
				// 会员不存在的场合，返回会员不存在
				resp = new EnterpriseStatusUpdateResponse(RespCode.SUCCESS.code(), ResultCode.USER_NOT_FOUND.code(),
						ResultCode.USER_NOT_FOUND.desc());

				resp.setMemberId(req.getMemberId());
				if (StringUtils.isNotBlank(req.getOperatorId())) {
					resp.setOperatorId(Long.valueOf(req.getOperatorId()));
				}
				

				log.info("EnterpriseStatusServiceFacadeImpl.enterpriseFrozen.resp:" + JSON.toJSONString(resp));

				return resp;
			}
			
			// 请求参数操作员Id（operatorId）不为空时，只冻结操作员
			if (StringUtils.isNotBlank(req.getOperatorId())) {
				Long operatorId = Long.valueOf(req.getOperatorId());
				// 验证操作员是否存在
				EnterpriseOperator currentEnterpriseOperator = enterpriseOperatorService.queryByOperatorId(operatorId);
				if (currentEnterpriseOperator == null) {
					// 操作员不存在的场合，返回操作员不存在
					resp = new EnterpriseStatusUpdateResponse(RespCode.SUCCESS.code(),
							ResultCode.OPERATOR_NOT_FOUND.code(), ResultCode.OPERATOR_NOT_FOUND.desc());

					resp.setMemberId(req.getMemberId());
					resp.setOperatorId(operatorId);

					log.info("EnterpriseStatusServiceFacadeImpl.enterpriseFrozen.resp:" + JSON.toJSONString(resp));

					return resp;
				}
				
				// 校验请求参数memberId是否有效
				if (!req.getMemberId().equals(currentEnterpriseOperator.getMemberId())) {
					// 【请求参数operatorId对应的memberId】和【请求参数memberId】不一致的场合
					resp = new EnterpriseStatusUpdateResponse(RespCode.SUCCESS.code(),
							ResultCode.INVALID_MEMBERID.code(), ResultCode.INVALID_MEMBERID.desc());

					resp.setMemberId(req.getMemberId());
					resp.setOperatorId(operatorId);

					log.info("EnterpriseStatusServiceFacadeImpl.enterpriseFrozen.resp:" + JSON.toJSONString(resp));

					return resp;
				}
				
				// 验证操作员状态是否为正常
				if (currentEnterpriseOperator.getStatus() == null
						|| currentEnterpriseOperator.getStatus() != UserStatusCodeEnum.NORMAL.getCode()) {
					resp = new EnterpriseStatusUpdateResponse(RespCode.SUCCESS.code(), ResultCode.CAN_NOT_FROZEN_OPERATOR.code(),
							ResultCode.CAN_NOT_FROZEN_OPERATOR.desc());

					resp.setMemberId(req.getMemberId());
					resp.setOperatorId(operatorId);

					log.info("EnterpriseStatusServiceFacadeImpl.enterpriseFrozen.resp:" + JSON.toJSONString(resp));

					return resp;
				}
				
				// 冻结操作员用参数设定
				EnterpriseOperator enterpriseOperator = new EnterpriseOperator();
				enterpriseOperator.setId(operatorId);
				// 更新操作员状态（正常→冻结），进行冻结操作
				enterpriseOperator.setStatus(UserStatusCodeEnum.FROZEN.getCode());
				enterpriseOperator.setModifyTime(new Date());
				enterpriseOperatorService.updateByPrimaryKeySelective(enterpriseOperator);
				
				resp = new EnterpriseStatusUpdateResponse(RespCode.SUCCESS.code(), ResultCode.SUCCESS.code(),
						ResultCode.SUCCESS.desc());

				resp.setMemberId(req.getMemberId());
				resp.setOperatorId(operatorId);

			// 请求参数操作员Id（operatorId）为空时，冻结企业，并冻结企业下的所有操作员
			} else {
				// 验证会员状态是否为正常
				if (currentEnterpriseMember.getStatus() == null
						|| currentEnterpriseMember.getStatus() != UserStatusCodeEnum.NORMAL.getCode()) {
					resp = new EnterpriseStatusUpdateResponse(RespCode.SUCCESS.code(), ResultCode.CAN_NOT_FROZEN.code(),
							ResultCode.CAN_NOT_FROZEN.desc());

					resp.setMemberId(req.getMemberId());

					log.info("EnterpriseStatusServiceFacadeImpl.enterpriseFrozen.resp:" + JSON.toJSONString(resp));

					return resp;
				}
				
				// 冻结企业用参数设定
				EnterpriseMember enterpriseMember = new EnterpriseMember();
				enterpriseMember.setMemberId(req.getMemberId());
				// 更新会员状态（正常→冻结），进行冻结操作
				enterpriseMember.setStatus(UserStatusCodeEnum.FROZEN.getCode());
				enterpriseMember.setModifyTime(new Date());
				
				// 调用【冻结企业及所有操作员】处理
				enterpriseStatusService.frozenEnterpriseAndAllOperator(enterpriseMember);
				
				resp = new EnterpriseStatusUpdateResponse(RespCode.SUCCESS.code(), ResultCode.SUCCESS.code(),
						ResultCode.SUCCESS.desc());

				resp.setMemberId(req.getMemberId());
			}
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			if (e instanceof BusinessException) {
				resp = new EnterpriseStatusUpdateResponse(RespCode.SUCCESS.code(), ResultCode.FAIL.code(), e.getMessage());
			} else {
				resp = new EnterpriseStatusUpdateResponse(RespCode.FAIL.code(), RespCode.FAIL.desc());
			}
		}
		
		log.info("EnterpriseStatusServiceFacadeImpl.enterpriseFrozen.resp:" + JSON.toJSONString(resp));

		return resp;
	}

	/**
	 * 企业会员解冻.
	 * 1、operatorId为空时，解冻企业，并解冻企业下的所有操作员.
	 * 2、operatorId均不为空时，解冻企业下的操作员.
	 * @see com.zillionfortune.cif.facade.user.EnterpriseStatusServiceFacade#enterpriseUnfreeze(com.zillionfortune.cif.facade.user.dto.EnterpriseStatusUpdateRequest)
	 */
	@Override
	public EnterpriseStatusUpdateResponse enterpriseUnfreeze(EnterpriseStatusUpdateRequest req) {
		log.info("EnterpriseStatusServiceFacadeImpl.enterpriseUnfreeze.req:" + JSON.toJSONString(req));
		// 响应结果定义
		EnterpriseStatusUpdateResponse resp;
		
		try {
			// 请求参数校验
			parameterChecker.checkEnterpriseStatusUpdateRequest(req);

			// 验证会员是否存在
			EnterpriseMember currentEnterpriseMember = enterpriseMemberService.queryByMemberId(req.getMemberId());
			if (currentEnterpriseMember == null) {
				// 会员不存在的场合，返回会员不存在
				resp = new EnterpriseStatusUpdateResponse(RespCode.SUCCESS.code(), ResultCode.USER_NOT_FOUND.code(),
						ResultCode.USER_NOT_FOUND.desc());

				resp.setMemberId(req.getMemberId());
				if (StringUtils.isNotBlank(req.getOperatorId())) {
					resp.setOperatorId(Long.valueOf(req.getOperatorId()));
				}

				log.info("EnterpriseStatusServiceFacadeImpl.enterpriseUnfreeze.resp:" + JSON.toJSONString(resp));

				return resp;
			}
			
			// 请求参数操作员Id（operatorId）不为空时，只解冻操作员
			if (StringUtils.isNotBlank(req.getOperatorId())) {
				Long operatorId = Long.valueOf(req.getOperatorId());
				// 验证操作员是否存在
				EnterpriseOperator currentEnterpriseOperator = enterpriseOperatorService.queryByOperatorId(operatorId);
				if (currentEnterpriseOperator == null) {
					// 操作员不存在的场合，返回操作员不存在
					resp = new EnterpriseStatusUpdateResponse(RespCode.SUCCESS.code(),
							ResultCode.OPERATOR_NOT_FOUND.code(), ResultCode.OPERATOR_NOT_FOUND.desc());

					resp.setMemberId(req.getMemberId());
					resp.setOperatorId(operatorId);

					log.info("EnterpriseStatusServiceFacadeImpl.enterpriseUnfreeze.resp:" + JSON.toJSONString(resp));

					return resp;
				}
				
				// 校验请求参数memberId是否有效
				if (!req.getMemberId().equals(currentEnterpriseOperator.getMemberId())) {
					// 【请求参数operatorId对应的memberId】和【请求参数memberId】不一致的场合
					resp = new EnterpriseStatusUpdateResponse(RespCode.SUCCESS.code(),
							ResultCode.INVALID_MEMBERID.code(), ResultCode.INVALID_MEMBERID.desc());

					resp.setMemberId(req.getMemberId());
					resp.setOperatorId(operatorId);

					log.info("EnterpriseStatusServiceFacadeImpl.enterpriseUnfreeze.resp:" + JSON.toJSONString(resp));

					return resp;
				}
				
				// 验证操作员状态是否为冻结
				if (currentEnterpriseOperator.getStatus() == null
						|| currentEnterpriseOperator.getStatus() != UserStatusCodeEnum.FROZEN.getCode()) {
					resp = new EnterpriseStatusUpdateResponse(RespCode.SUCCESS.code(), ResultCode.CAN_NOT_UNFREEZE_OPERATOR.code(),
							ResultCode.CAN_NOT_UNFREEZE_OPERATOR.desc());

					resp.setMemberId(req.getMemberId());
					resp.setOperatorId(operatorId);

					log.info("EnterpriseStatusServiceFacadeImpl.enterpriseUnfreeze.resp:" + JSON.toJSONString(resp));

					return resp;
				}
				
				// 解冻操作员用参数设定
				EnterpriseOperator enterpriseOperator = new EnterpriseOperator();
				enterpriseOperator.setId(operatorId);
				// 更新操作员状态（冻结→正常），进行解冻操作
				enterpriseOperator.setStatus(UserStatusCodeEnum.NORMAL.getCode());
				enterpriseOperator.setModifyTime(new Date());
				
				// 判断会员状态是否为冻结，如果是冻结状态，需要先解冻企业，然后解冻单个操作员
				if (currentEnterpriseMember.getStatus() != null
						&& currentEnterpriseMember.getStatus() == UserStatusCodeEnum.FROZEN.getCode()) {
					// 解冻企业用参数设定
					EnterpriseMember enterpriseMember = new EnterpriseMember();
					enterpriseMember.setMemberId(req.getMemberId());
					// 更新会员状态（冻结→正常），进行解冻操作
					enterpriseMember.setStatus(UserStatusCodeEnum.NORMAL.getCode());
					enterpriseMember.setModifyTime(new Date());
					
					// 调用【解冻企业及单个操作员】处理
					enterpriseStatusService.unfreezeEnterpriseAndOneOperator(enterpriseMember, enterpriseOperator);

				} else {
					// 以上情况以外的场合，只解冻操作员
					enterpriseOperatorService.updateByPrimaryKeySelective(enterpriseOperator);
				}
				
				resp = new EnterpriseStatusUpdateResponse(RespCode.SUCCESS.code(), ResultCode.SUCCESS.code(),
						ResultCode.SUCCESS.desc());

				resp.setMemberId(req.getMemberId());
				resp.setOperatorId(operatorId);
			
			// 请求参数操作员Id（operatorId）为空时，解冻企业，并解冻企业下的所有操作员
			} else {
				// 验证会员状态是否为正常/冻结
				// 1、会员状态为正常时，不用解冻企业，只用解冻企业下的所有操作员
				// 2、会员状态为冻结时，解冻企业，并解冻企业下的所有操作员
				// 3、会员状态为注销时，不能做解冻处理
				if (currentEnterpriseMember.getStatus() == null
						|| currentEnterpriseMember.getStatus() == UserStatusCodeEnum.CANCEL.getCode()) {
					resp = new EnterpriseStatusUpdateResponse(RespCode.SUCCESS.code(), ResultCode.ENTERPRISE_CAN_NOT_UNFREEZE.code(),
							ResultCode.ENTERPRISE_CAN_NOT_UNFREEZE.desc());

					resp.setMemberId(req.getMemberId());

					log.info("EnterpriseStatusServiceFacadeImpl.enterpriseUnfreeze.resp:" + JSON.toJSONString(resp));

					return resp;
				}
				
				// 解冻企业用参数设定
				EnterpriseMember enterpriseMember = new EnterpriseMember();
				enterpriseMember.setMemberId(req.getMemberId());
				enterpriseMember.setStatus(currentEnterpriseMember.getStatus());
				
				// 调用【解冻企业及所有操作员】处理
				enterpriseStatusService.unfreezeEnterpriseAndAllOperator(enterpriseMember);
				
				resp = new EnterpriseStatusUpdateResponse(RespCode.SUCCESS.code(), ResultCode.SUCCESS.code(),
						ResultCode.SUCCESS.desc());

				resp.setMemberId(req.getMemberId());
			}
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			if (e instanceof BusinessException) {
				resp = new EnterpriseStatusUpdateResponse(RespCode.SUCCESS.code(), ResultCode.FAIL.code(), e.getMessage());
			} else {
				resp = new EnterpriseStatusUpdateResponse(RespCode.FAIL.code(), RespCode.FAIL.desc());
			}
		}
		
		log.info("EnterpriseStatusServiceFacadeImpl.enterpriseUnfreeze.resp:" + JSON.toJSONString(resp));
		
		return resp;
	}

	/**
	 * 企业会员注销.
	 * 1、operatorId为空时，注销企业，并注销企业下的所有操作员.
	 * 2、operatorId均不为空时，注销企业下的操作员.
	 * @see com.zillionfortune.cif.facade.user.EnterpriseStatusServiceFacade#enterpriseCancel(com.zillionfortune.cif.facade.user.dto.EnterpriseStatusUpdateRequest)
	 */
	@Override
	public EnterpriseStatusUpdateResponse enterpriseCancel(EnterpriseStatusUpdateRequest req) {
		log.info("EnterpriseStatusServiceFacadeImpl.enterpriseCancel.req:" + JSON.toJSONString(req));
		// 响应结果定义
		EnterpriseStatusUpdateResponse resp;
		
		try {
			// 请求参数校验
			parameterChecker.checkEnterpriseStatusUpdateRequest(req);

			// 验证会员是否存在
			EnterpriseMember currentEnterpriseMember = enterpriseMemberService.queryByMemberId(req.getMemberId());
			if (currentEnterpriseMember == null) {
				// 会员不存在的场合，返回会员不存在
				resp = new EnterpriseStatusUpdateResponse(RespCode.SUCCESS.code(), ResultCode.USER_NOT_FOUND.code(),
						ResultCode.USER_NOT_FOUND.desc());

				resp.setMemberId(req.getMemberId());
				if (StringUtils.isNotBlank(req.getOperatorId())) {
					resp.setOperatorId(Long.valueOf(req.getOperatorId()));
				}

				log.info("EnterpriseStatusServiceFacadeImpl.enterpriseCancel.resp:" + JSON.toJSONString(resp));

				return resp;
			}
			
			// 请求参数操作员Id（operatorId）不为空时，只注销操作员
			if (StringUtils.isNotBlank(req.getOperatorId())) {
				Long operatorId = Long.valueOf(req.getOperatorId());
				// 验证操作员是否存在
				EnterpriseOperator currentEnterpriseOperator = enterpriseOperatorService.queryByOperatorId(operatorId);
				if (currentEnterpriseOperator == null) {
					// 操作员不存在的场合，返回操作员不存在
					resp = new EnterpriseStatusUpdateResponse(RespCode.SUCCESS.code(),
							ResultCode.OPERATOR_NOT_FOUND.code(), ResultCode.OPERATOR_NOT_FOUND.desc());

					resp.setMemberId(req.getMemberId());
					resp.setOperatorId(operatorId);

					log.info("EnterpriseStatusServiceFacadeImpl.enterpriseCancel.resp:" + JSON.toJSONString(resp));

					return resp;
				}
				
				// 校验请求参数memberId是否有效
				if (!req.getMemberId().equals(currentEnterpriseOperator.getMemberId())) {
					// 【请求参数operatorId对应的memberId】和【请求参数memberId】不一致的场合
					resp = new EnterpriseStatusUpdateResponse(RespCode.SUCCESS.code(),
							ResultCode.INVALID_MEMBERID.code(), ResultCode.INVALID_MEMBERID.desc());

					resp.setMemberId(req.getMemberId());
					resp.setOperatorId(operatorId);

					log.info("EnterpriseStatusServiceFacadeImpl.enterpriseCancel.resp:" + JSON.toJSONString(resp));

					return resp;
				}
				
				// 验证操作员状态是否为正常/冻结
				if (currentEnterpriseOperator.getStatus() == null
						|| currentEnterpriseOperator.getStatus() == UserStatusCodeEnum.CANCEL.getCode()) {
					resp = new EnterpriseStatusUpdateResponse(RespCode.SUCCESS.code(), ResultCode.CAN_NOT_CANCEL_OPERATOR.code(),
							ResultCode.CAN_NOT_CANCEL_OPERATOR.desc());

					resp.setMemberId(req.getMemberId());
					resp.setOperatorId(operatorId);

					log.info("EnterpriseStatusServiceFacadeImpl.enterpriseCancel.resp:" + JSON.toJSONString(resp));

					return resp;
				}
				
				// 注销操作员用参数设定
				EnterpriseOperator enterpriseOperator = new EnterpriseOperator();
				enterpriseOperator.setId(operatorId);
				// 更新操作员状态（正常/冻结→注销），进行注销操作
				enterpriseOperator.setStatus(UserStatusCodeEnum.CANCEL.getCode());
				// 更改别名
				enterpriseOperator.setUserName(currentEnterpriseOperator.getUserName() + CommonConstants.SUFFIX_FOR_CANCEL);
				enterpriseOperator.setModifyTime(new Date());
				enterpriseOperatorService.updateByPrimaryKeySelective(enterpriseOperator);
				
				resp = new EnterpriseStatusUpdateResponse(RespCode.SUCCESS.code(), ResultCode.SUCCESS.code(),
						ResultCode.SUCCESS.desc());

				resp.setMemberId(req.getMemberId());
				resp.setOperatorId(operatorId);
			
			// 请求参数操作员Id（operatorId）为空时，注销企业，并注销企业下的所有操作员
			} else {
				// 验证会员状态是否为正常/冻结
				if (currentEnterpriseMember.getStatus() == null
						|| currentEnterpriseMember.getStatus() == UserStatusCodeEnum.CANCEL.getCode()) {
					resp = new EnterpriseStatusUpdateResponse(RespCode.SUCCESS.code(), ResultCode.CAN_NOT_CANCEL.code(),
							ResultCode.CAN_NOT_CANCEL.desc());

					resp.setMemberId(req.getMemberId());

					log.info("EnterpriseStatusServiceFacadeImpl.enterpriseCancel.resp:" + JSON.toJSONString(resp));

					return resp;
				}
				
				// 注销企业用参数设定
				EnterpriseMember enterpriseMember = new EnterpriseMember();
				enterpriseMember.setMemberId(req.getMemberId());
				// 更新会员状态（正常/冻结→注销），进行注销操作
				enterpriseMember.setStatus(UserStatusCodeEnum.CANCEL.getCode());
				enterpriseMember.setModifyTime(new Date());
				
				// 调用【注销企业及所有操作员】处理
				enterpriseStatusService.cancelEnterpriseAndAllOperator(enterpriseMember);
				
				resp = new EnterpriseStatusUpdateResponse(RespCode.SUCCESS.code(), ResultCode.SUCCESS.code(),
						ResultCode.SUCCESS.desc());

				resp.setMemberId(req.getMemberId());
			}
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			if (e instanceof BusinessException) {
				resp = new EnterpriseStatusUpdateResponse(RespCode.SUCCESS.code(), ResultCode.FAIL.code(), e.getMessage());
			} else {
				resp = new EnterpriseStatusUpdateResponse(RespCode.FAIL.code(), RespCode.FAIL.desc());
			}
		}
		
		log.info("EnterpriseStatusServiceFacadeImpl.enterpriseCancel.resp:" + JSON.toJSONString(resp));

		return resp;
	}
	
}
