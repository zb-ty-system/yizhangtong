package com.zillionfortune.cif.biz.user.check;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.zillionfortune.cif.common.exception.BusinessException;
import com.zillionfortune.cif.facade.user.dto.EnterpriseLoginPasswordModifyRequest;
import com.zillionfortune.cif.facade.user.dto.EnterpriseLoginPasswordRetrieveRequest;

/**
 * 企业会员 重置登录密码/登录密码更新业务 请求参数校验
 * 
 * @author wangzinan
 *
 */

@Component
public class EnterprisePasswordServiceParameterChecker {

	/**
	 * 企业会员重置登录密码业务 请求参数校验
	 * 
	 * @param req
	 * @throws Exception
	 */
	public void checkEnterpriseLoginPasswordRetrieveRequest(EnterpriseLoginPasswordRetrieveRequest req)
			throws Exception {
		// 校验请求参数
		if (req == null) {
			throw new BusinessException("请求对象不能为空");
		}

		if (StringUtils.isBlank(req.getCustomerNo())
			|| StringUtils.isBlank(req.getUserName())
			|| StringUtils.isBlank(req.getNewPassword())) {
			throw new BusinessException("customerNo,userName,newPassword皆不能为空");
		}
	}

	/**
	 * 企业会员更新登录密码业务 请求参数校验
	 * 
	 * @param req
	 * @throws Exception
	 */
	public void checkEnterpriseLoginPasswordModifyRequest(EnterpriseLoginPasswordModifyRequest req) throws Exception {
		// 校验请求参数
		if (req == null) {
			throw new BusinessException("请求对象不能为空");
		}

		if (StringUtils.isBlank(req.getMemberId())
			|| StringUtils.isBlank(req.getOperatorId())
			|| StringUtils.isBlank(req.getOrgiPassword())
			|| StringUtils.isBlank(req.getNewPassword())) {
			throw new BusinessException("memberId,operatorId,orgiPassword,newPassword字段皆不能为空");
		}
		
		if (StringUtils.isNotBlank(req.getOperatorId()) 
				&& !StringUtils.isNumeric(req.getOperatorId())) {
			throw new BusinessException("operatorId字段的参数值必须是整数");
		}
	}

}
