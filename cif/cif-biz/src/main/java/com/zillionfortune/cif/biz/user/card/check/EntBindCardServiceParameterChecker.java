/*
 * Copyright (c) 2016, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 *
 *
 */
package com.zillionfortune.cif.biz.user.card.check;

import org.springframework.stereotype.Component;

import com.zillionfortune.cif.common.exception.BusinessException;
import com.zillionfortune.cif.common.util.StringUtils;
import com.zillionfortune.cif.facade.user.card.dto.EnterpriseBindCardRequest;

/**
 * ClassName: BindCardServiceParameterChecker <br/>
 * Function: 企业会员银行卡服务参数校验. <br/>
 * Date: 2016年12月12日 下午4:01:19 <br/>
 *
 * @author pengting
 * @version 
 * @since JDK 1.7
 */
@Component
public class EntBindCardServiceParameterChecker {

	/**
	 * checkBindCardReq:企业会员绑定银行卡参数校验. <br/>
	 *
	 * @param req
	 * @throws BusinessException
	 */
	public void checkBindCardReq(EnterpriseBindCardRequest req) throws BusinessException {
		if (null == req) {
			throw new BusinessException("请求对象不能为空");
		}
		if (StringUtils.isEmpty(req.getMemberId()) || StringUtils.isEmpty(req.getBankAccountName())
				|| StringUtils.isEmpty(req.getBankAccountNo()) || StringUtils.isEmpty(req.getBranchBankName())
				||StringUtils.isEmpty(req.getBankAccountRegion())) {
			throw new BusinessException("memberId, bankAccountName, bankAccountNo, branchBankName,bankAccountRegion  皆不能为空");
		}
	}
	
	
	/**
	 * checkunBindCardReq:企业会员解绑银行卡参数校验. <br/>
	 *
	 * @param req
	 * @throws BusinessException
	 */
	public void checkUnBindCardReq(EnterpriseBindCardRequest req) throws BusinessException {
		if (null == req) {
			throw new BusinessException("请求对象不能为空");
		}
		if (StringUtils.isEmpty(req.getMemberId()) ||
				StringUtils.isEmpty(req.getBankAccountName()) || StringUtils.isEmpty(req.getBankAccountNo())) {
			throw new BusinessException("memberId, bankAccountName, bankAccountNo 皆不能为空");
		}
	}
	
	/**
	 * checkQueryBindCardReq:查询已绑定银行卡信息参数校验. <br/>
	 *
	 * @param req
	 * @throws BusinessException
	 */
	public void checkQueryBindCardReq(EnterpriseBindCardRequest req) throws BusinessException {
		if (null == req) {
			throw new BusinessException("请求对象不能为空");
		}
		if (StringUtils.isEmpty(req.getMemberId())) {
			throw new BusinessException("memberId 不能为空");
		}
	}
}
