/*
 * Copyright (c) 2016, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 *
 *
 */
package com.zillionfortune.cif.biz.user.card.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.zillionfortune.cif.biz.user.card.check.EntBindCardServiceParameterChecker;
import com.zillionfortune.cif.common.enums.EnterpriseAuditStatusEnum;
import com.zillionfortune.cif.common.enums.LegalPersonAuditStatusEnum;
import com.zillionfortune.cif.common.enums.RespCode;
import com.zillionfortune.cif.common.enums.ResultCode;
import com.zillionfortune.cif.common.enums.UserStatusCodeEnum;
import com.zillionfortune.cif.common.exception.BusinessException;
import com.zillionfortune.cif.common.util.StringUtils;
import com.zillionfortune.cif.dal.entity.EnterpriseInfo;
import com.zillionfortune.cif.dal.entity.EnterpriseMember;
import com.zillionfortune.cif.facade.user.card.EnterpriseBindCardServiceFacade;
import com.zillionfortune.cif.facade.user.card.dto.EnterpriseBankNoQueryResponse;
import com.zillionfortune.cif.facade.user.card.dto.EnterpriseBindCardQueryResponse;
import com.zillionfortune.cif.facade.user.card.dto.EnterpriseBindCardRequest;
import com.zillionfortune.cif.facade.user.card.dto.EnterpriseBindCardResponse;
import com.zillionfortune.cif.service.user.EnterpriseInfoService;
import com.zillionfortune.cif.service.user.EnterpriseMemberService;
import com.zillionfortune.cif.support.common.Constants;

/**
 * ClassName: EnterpriseBindCardServiceFacadeImpl <br/>
 * Function: 企业会员绑定银行账户接口实现. <br/>
 * Date: 2016年12月13日 下午6:46:43 <br/>
 *
 * @author pengting
 * @version 
 * @since JDK 1.7
 */
@Component
public class EnterpriseBindCardServiceFacadeImpl implements EnterpriseBindCardServiceFacade {
	
	private static Logger log = LoggerFactory.getLogger(EnterpriseBindCardServiceFacadeImpl.class);
	
	@Autowired
	private EntBindCardServiceParameterChecker parameterChecker;
	@Autowired
	private EnterpriseInfoService enterpriseInfoService;
	@Autowired
	private EnterpriseMemberService enterpriseMemberService;

	/**
	 * @see com.zillionfortune.cif.facade.user.card.EnterpriseBindCardServiceFacade#bindCard(com.zillionfortune.cif.facade.user.card.dto.EnterpriseBindCardRequest)
	 */
	@Override
	public EnterpriseBindCardResponse bindCard(EnterpriseBindCardRequest req) {
		log.info("bindCard.req:" + JSONObject.toJSONString(req));
		EnterpriseBindCardResponse resp = null;
		try {
			// 参数校验
			parameterChecker.checkBindCardReq(req);
			
			EnterpriseMember member = enterpriseMemberService.queryByMemberId(req.getMemberId());
			// 会员不存在
			if (member == null) {
				resp = new EnterpriseBindCardResponse(RespCode.SUCCESS.code(), ResultCode.USER_NOT_FOUND.code(),
						ResultCode.USER_NOT_FOUND.desc());
				resp.setMemberId(req.getMemberId());
				return resp;
			}
			
			// 会员状态非正常
			if (member.getStatus() != UserStatusCodeEnum.NORMAL.getCode()) {
				resp = new EnterpriseBindCardResponse(RespCode.SUCCESS.code(), ResultCode.ABNOMARL_USER_STATUS.code(),
						ResultCode.ABNOMARL_USER_STATUS.desc());
				resp.setMemberId(req.getMemberId());
				return resp;
			}
			
			/*EnterpriseInfo  enterpriseInfo = enterpriseInfoService.queryByCustomerId(member.getCustomerId());
			// 银行账户已绑定，不能再次绑定
			if (StringUtils.isNotEmpty(enterpriseInfo.getBankAccountNo())) {
				resp = new EnterpriseBindCardResponse(RespCode.SUCCESS.code(), ResultCode.CAN_NOT_AGAIN_BIND.code(),
						ResultCode.CAN_NOT_AGAIN_BIND.desc());
				resp.setMemberId(req.getMemberId());
				return resp;
			}*/
			
			// 更新
			EnterpriseInfo  upEnterpriseInfo = new EnterpriseInfo();
			upEnterpriseInfo.setCustomerId(member.getCustomerId());
			upEnterpriseInfo.setBankAccountName(req.getBankAccountName());
			upEnterpriseInfo.setBankAccountNo(req.getBankAccountNo());
			upEnterpriseInfo.setBranchBankName(req.getBranchBankName());
			upEnterpriseInfo.setBankAccountRegion(req.getBankAccountRegion());
			upEnterpriseInfo.setModifyTime(new Date());
			enterpriseInfoService.updateByCustomerIdSelective(upEnterpriseInfo);
			resp = new EnterpriseBindCardResponse(RespCode.SUCCESS.code(), ResultCode.SUCCESS.code(), ResultCode.SUCCESS.desc());
			resp.setMemberId(req.getMemberId());
		} catch (BusinessException e) {
			log.error(e.getMessage(), e);
			resp = new EnterpriseBindCardResponse(RespCode.SUCCESS.code(), ResultCode.FAIL.code(), e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			resp = new EnterpriseBindCardResponse(RespCode.FAIL.code(), RespCode.FAIL.desc());
		} finally {
			log.info("bindCard.resp:" + JSONObject.toJSONString(resp));
		}
		return resp;
	}

	/**
	 * @see com.zillionfortune.cif.facade.user.card.EnterpriseBindCardServiceFacade#unBindCard(com.zillionfortune.cif.facade.user.card.dto.EnterpriseBindCardRequest)
	 */
	@Override
	public EnterpriseBindCardResponse unBindCard(EnterpriseBindCardRequest req) {
		log.info("unBindCard.req:" + JSONObject.toJSONString(req));
		EnterpriseBindCardResponse resp = null;
		try {
			// 参数校验
			parameterChecker.checkUnBindCardReq(req);
			
			EnterpriseMember member = enterpriseMemberService.queryByMemberId(req.getMemberId());
			// 会员不存在
			if (member == null) {
				resp = new EnterpriseBindCardResponse(RespCode.SUCCESS.code(), ResultCode.USER_NOT_FOUND.code(),
						ResultCode.USER_NOT_FOUND.desc());
				resp.setMemberId(req.getMemberId());
				return resp;
			}
			
			// 会员状态非正常
			if (member.getStatus() != UserStatusCodeEnum.NORMAL.getCode()) {
				resp = new EnterpriseBindCardResponse(RespCode.SUCCESS.code(), ResultCode.ABNOMARL_USER_STATUS.code(),
						ResultCode.ABNOMARL_USER_STATUS.desc());
				resp.setMemberId(req.getMemberId());
				return resp;
			}
			
			EnterpriseInfo  enterpriseInfo = enterpriseInfoService.queryByCustomerId(member.getCustomerId());
			// 账户未绑定,无法解绑
			if (null == enterpriseInfo || !req.getBankAccountNo().equals(enterpriseInfo.getBankAccountNo()) ||
					!req.getBankAccountName().equals(enterpriseInfo.getBankAccountName())) {
				resp = new EnterpriseBindCardResponse(RespCode.SUCCESS.code(), ResultCode.CAN_NOT_UNBIND.code(),
						ResultCode.CAN_NOT_UNBIND.desc());
				resp.setMemberId(req.getMemberId());
				return resp;
			}
			
			// 解绑  清空账户信息
			EnterpriseInfo upEnterpriseInfo = new EnterpriseInfo();
			upEnterpriseInfo.setCustomerId(enterpriseInfo.getCustomerId());
			upEnterpriseInfo.setBankAccountNo(Constants.EMPTY_STRING);
			upEnterpriseInfo.setBranchBankName(Constants.EMPTY_STRING);
			upEnterpriseInfo.setBankAccountName(Constants.EMPTY_STRING);
			upEnterpriseInfo.setBankAccountRegion(Constants.EMPTY_STRING);
			upEnterpriseInfo.setModifyTime(new Date());
			enterpriseInfoService.updateByCustomerIdSelective(upEnterpriseInfo);
			resp = new EnterpriseBindCardResponse(RespCode.SUCCESS.code(), ResultCode.SUCCESS.code(), ResultCode.SUCCESS.desc());
			resp.setMemberId(req.getMemberId());
		} catch (BusinessException e) {
			log.error(e.getMessage(), e);
			resp = new EnterpriseBindCardResponse(RespCode.SUCCESS.code(), ResultCode.FAIL.code(), e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			resp = new EnterpriseBindCardResponse(RespCode.FAIL.code(), RespCode.FAIL.desc());
		} finally {
			log.info("unBindCard.resp:" + JSONObject.toJSONString(resp));
		}
		return resp;
	}

	/**
	 * @see com.zillionfortune.cif.facade.user.card.EnterpriseBindCardServiceFacade#queryEnterpriseBindCard(com.zillionfortune.cif.facade.user.card.dto.EnterpriseBindCardRequest)
	 */
	@Override
	public EnterpriseBindCardQueryResponse queryEnterpriseBindCard(
			EnterpriseBindCardRequest req) {
		log.info("queryEnterpriseBindCard.req:" + JSONObject.toJSONString(req));
		EnterpriseBindCardQueryResponse resp = null;
		try {
			// 参数校验
			parameterChecker.checkQueryBindCardReq(req);
			
			EnterpriseInfo  enterpriseInfo = enterpriseInfoService.queryByMemberId(req.getMemberId());
			// 会员不存在
			if (enterpriseInfo == null) {
				resp = new EnterpriseBindCardQueryResponse(RespCode.SUCCESS.code(), ResultCode.USER_NOT_FOUND.code(),
						ResultCode.USER_NOT_FOUND.desc());
				resp.setMemberId(req.getMemberId());
				return resp;
			}
			
			// 封装结果对象
			resp = new EnterpriseBindCardQueryResponse(RespCode.SUCCESS.code(), ResultCode.SUCCESS.code(), ResultCode.SUCCESS.desc());
			resp.setMemberId(req.getMemberId());
			resp.setBankAccountName(enterpriseInfo.getBankAccountName());
			resp.setBankAccountNo(enterpriseInfo.getBankAccountNo());
			resp.setBranchBankName(enterpriseInfo.getBranchBankName());
			resp.setBankAccountRegion(enterpriseInfo.getBankAccountRegion());
		} catch (BusinessException e) {
			log.error(e.getMessage(), e);
			resp = new EnterpriseBindCardQueryResponse(RespCode.SUCCESS.code(), ResultCode.FAIL.code(), e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			resp = new EnterpriseBindCardQueryResponse(RespCode.FAIL.code(), RespCode.FAIL.desc());
		} finally {
			log.info("queryEnterpriseBindCard.resp:" + JSONObject.toJSONString(resp));
		}
		return resp;
	}
	
	@Override
	public EnterpriseBankNoQueryResponse findBankAccountNo(String bankAccountNo) {
		log.info("findBankAccountNo.req: bankAccountNo=" + bankAccountNo);
		EnterpriseBankNoQueryResponse resp = null;
		boolean existFlg = false;
		try {
			// 参数校验
			if (StringUtils.isEmpty(bankAccountNo)) {
				throw new BusinessException("bankAccountNo 不能为空");
			}
			
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("bankAccountNo", bankAccountNo);
			List<EnterpriseInfo > rsList = enterpriseInfoService.selectListByCriteria(paramMap);
			if (rsList != null && !rsList.isEmpty()) {
				for (EnterpriseInfo enterpriseInfo : rsList) {
					// 审核状态为待审核 或者 审核通过则表示改卡号已被占用
					if (enterpriseInfo.getEnterpriseAuditStatus() != null 
							&& enterpriseInfo.getEnterpriseAuditStatus() != EnterpriseAuditStatusEnum.CHECK_NOT_PASS.code()
							&& enterpriseInfo.getLegalPersonAuditStatus() != null
							&& enterpriseInfo.getLegalPersonAuditStatus() != LegalPersonAuditStatusEnum.CHECK_NOT_PASS.code()) {
						existFlg = true;
						break;
					}
				}
			}
			// 封装结果对象
			resp = new EnterpriseBankNoQueryResponse(RespCode.SUCCESS.code(), ResultCode.SUCCESS.code(), ResultCode.SUCCESS.desc());
			resp.setBankAccountNo(bankAccountNo);
			resp.setExistFlg(existFlg);
		} catch (BusinessException e) {
			log.error(e.getMessage(), e);
			resp = new EnterpriseBankNoQueryResponse(RespCode.SUCCESS.code(), ResultCode.FAIL.code(), e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			resp = new EnterpriseBankNoQueryResponse(RespCode.FAIL.code(), RespCode.FAIL.desc());
		} finally {
			log.info("findBankAccountNo.resp:" + JSONObject.toJSONString(resp));
		}
		return resp;
	}

}