/*
 * Copyright (c) 2016, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 *
 *
 */
package com.zillionfortune.cif.biz.user.check;

import org.springframework.stereotype.Component;

import com.zillionfortune.cif.common.enums.EnterpriseCertType;
import com.zillionfortune.cif.common.exception.BusinessException;
import com.zillionfortune.cif.common.util.StringUtils;
import com.zillionfortune.cif.facade.user.dto.EnterpriseUserAuthServiceRequest;

/**
 * ClassName: EnterpriseUserAuthParameterChecker <br/>
 * Function: 企业会员认证服 参数校验. <br/>
 * Date: 2016年12月7日 上午9:55:12 <br/>
 *
 * @author pengting
 * @version 
 * @since JDK 1.7
 */
@Component
public class EnterpriseUserAuthParameterChecker {
	
	/**
	 * checkRealNameAuthReq:企业会员认证参数校验. <br/>
	 *
	 * @param req
	 * @throws BusinessException 
	 */
	public void checkRealNameAuthReq(EnterpriseUserAuthServiceRequest req) throws BusinessException {
		if (null == req) {
			throw new BusinessException("请求对象不能为空");
		}
		if (StringUtils.isEmpty(req.getMemberId()) || StringUtils.isEmpty(req.getCertificateType()) || 
				StringUtils.isEmpty(req.getCertificateNo()) || StringUtils.isEmpty(req.getEnterpriseName())) {
			throw new BusinessException("memberId,certificateType,certificateNo,enterpriseName 皆不能为空");
		}
		if (!StringUtils.isNumber(req.getCertificateType())) {
			throw new BusinessException("certificateType 必须为整数型");
		}
		if (null == EnterpriseCertType.getEnumItem(Integer.valueOf(req.getCertificateType()))) {
			throw new BusinessException("certificateType 字段的参数值不在约定的范围");
		}
	}

}
