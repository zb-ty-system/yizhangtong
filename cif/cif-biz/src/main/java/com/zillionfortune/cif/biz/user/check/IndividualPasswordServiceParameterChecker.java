package com.zillionfortune.cif.biz.user.check;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.zillionfortune.cif.common.exception.BusinessException;
import com.zillionfortune.cif.facade.user.dto.IndividualLoginPasswordModifyRequest;
import com.zillionfortune.cif.facade.user.dto.IndividualLoginPasswordRetrieveRequest;

/**
 * 个人会员 重置登录密码/登录密码更新业务 请求参数校验
 * 
 * @author wangzinan
 *
 */

@Component
public class IndividualPasswordServiceParameterChecker {

	/**
	 * 个人会员重置登录密码业务 请求参数校验
	 * 
	 * @param req
	 * @throws Exception
	 */
	public void checkIndividualLoginPasswordRetrieveRequest(IndividualLoginPasswordRetrieveRequest req)
			throws Exception {
		// 校验请求参数
		if (req == null) {
			throw new BusinessException("请求对象不能为空");
		}

		if (StringUtils.isBlank(req.getMemberId())
			|| StringUtils.isBlank(req.getNewPassword())) {
			throw new BusinessException("memberId,newPassword皆不能为空");
		}
	}

	/**
	 * 个人会员更新登录密码业务 请求参数校验
	 * 
	 * @param req
	 * @throws Exception
	 */
	public void checkIndividualLoginPasswordModifyRequest(IndividualLoginPasswordModifyRequest req) throws Exception {
		// 校验请求参数
		if (req == null) {
			throw new BusinessException("请求对象不能为空");
		}

		if (StringUtils.isBlank(req.getMemberId())
			|| StringUtils.isBlank(req.getOrgiPassword())
			|| StringUtils.isBlank(req.getNewPassword())) {
			throw new BusinessException("memberId,orgiPassword,newPassword字段皆不能为空");
		}
	}

}
