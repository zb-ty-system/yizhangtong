/*
 * Copyright (c) 2016, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 *
 *
 */
package com.zillionfortune.cif.biz.user.card.check;

import org.springframework.stereotype.Component;

import com.zillionfortune.cif.common.enums.BankCardType;
import com.zillionfortune.cif.common.enums.PersonCertType;
import com.zillionfortune.cif.common.exception.BusinessException;
import com.zillionfortune.cif.common.util.StringUtils;
import com.zillionfortune.cif.facade.user.card.dto.BindCardQueryRequest;
import com.zillionfortune.cif.facade.user.card.dto.BindCardRequest;

/**
 * ClassName: BindCardServiceParameterChecker <br/>
 * Function: 个人会员银行卡服务参数校验. <br/>
 * Date: 2016年12月12日 下午4:01:19 <br/>
 *
 * @author pengting
 * @version 
 * @since JDK 1.7
 */
@Component
public class BindCardServiceParameterChecker {

	/**
	 * checkBindCardReq:个人会员绑定银行卡参数校验. <br/>
	 *
	 * @param req
	 * @throws BusinessException
	 */
	public void checkBindCardReq(BindCardRequest req) throws BusinessException {
		if (null == req) {
			throw new BusinessException("请求对象不能为空");
		}
		if (StringUtils.isEmpty(req.getMemberId()) || req.getCertificateType() == null || StringUtils.isEmpty(req.getCertificateNo()) ||
				StringUtils.isEmpty(req.getRealName()) || StringUtils.isEmpty(req.getMobile()) || StringUtils.isEmpty(req.getBankcardNo())) {
			throw new BusinessException("memberId,certificateType,certificateNo,realName,mobile,bankcardNo 皆不能为空");
		}
		if (null == PersonCertType.getEnumItem(req.getCertificateType())) {
			throw new BusinessException("certificateType 字段的参数值不在约定的范围");
		}
		if (StringUtils.isNotEmpty(req.getBankCardType()) && BankCardType.getEnumItem(req.getBankCardType()) == null) {
			throw new BusinessException("bankCardType 字段的参数值不在约定的范围");
		}
	}
	
	/**
	 * checkSmsVerificationReq:个人会员绑定银行卡短信验证参数校验. <br/>
	 *
	 * @param req
	 * @throws BusinessException
	 */
	public  void checkSmsVerificationReq(BindCardRequest req) throws BusinessException {
		if (null == req) {
			throw new BusinessException("请求对象不能为空");
		}
		if (StringUtils.isEmpty(req.getMemberId()) || req.getCertificateType() == null || StringUtils.isEmpty(req.getCertificateNo()) ||
				StringUtils.isEmpty(req.getRealName()) || StringUtils.isEmpty(req.getMobile()) || 
				StringUtils.isEmpty(req.getBankcardNo()) || StringUtils.isEmpty(req.getSmsCode())) {
			throw new BusinessException("memberId,certificateType,certificateNo,realName,mobile,bankcardNo,smsCode 皆不能为空");
		}
		if (null == PersonCertType.getEnumItem(req.getCertificateType())) {
			throw new BusinessException("certificateType 字段的参数值不在约定的范围");
		}
		if (StringUtils.isNotEmpty(req.getBankCardType()) && BankCardType.getEnumItem(req.getBankCardType()) == null) {
			throw new BusinessException("bankCardType 字段的参数值不在约定的范围");
		}
	}
	
	/**
	 * checkunBindCardReq:个人会员解绑银行卡参数校验. <br/>
	 *
	 * @param req
	 * @throws BusinessException
	 */
	public void checkUnBindCardReq(BindCardRequest req) throws BusinessException {
		if (null == req) {
			throw new BusinessException("请求对象不能为空");
		}
		if (StringUtils.isEmpty(req.getMemberId()) || StringUtils.isEmpty(req.getBankcardNo())) {
			throw new BusinessException("memberId,bankcardNo 皆不能为空");
		}
	}
	
	/**
	 * checkQueryBindCardReq:查询已绑定银行卡信息参数校验. <br/>
	 *
	 * @param req
	 * @throws BusinessException
	 */
	public void checkQueryBindCardReq(BindCardQueryRequest req) throws BusinessException {
		if (null == req) {
			throw new BusinessException("请求对象不能为空");
		}
		if (StringUtils.isEmpty(req.getMemberId())) {
			throw new BusinessException("memberId 不能为空");
		}
		if (null != req.getCertificateType() && PersonCertType.getEnumItem(req.getCertificateType()) == null) {
			throw new BusinessException("certificateType 字段的参数值不在约定的范围");
		}
	}
}
