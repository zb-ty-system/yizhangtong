package com.zillionfortune.cif.biz.user.check;

import org.springframework.stereotype.Component;

import com.zillionfortune.cif.common.exception.BusinessException;
import com.zillionfortune.cif.common.util.StringUtils;
import com.zillionfortune.cif.facade.user.dto.IndividualInfoQueryRequest;

/**
 * ClassName: UserInfoQueryServiceParamentChecker <br/>
 * Function: 个人会员信息查询服务 参数校验. <br/>
 * Date: 2016年11月23日 上午9:19:18 <br/>
 *
 * @author pengting
 * @version 
 * @since JDK 1.7
 */
@Component
public class UserInfoQueryServiceParameterChecker {
	
	/**
	 * checkIndividualBasicInfoQuery:查询个人会员基础信息参数校验. <br/>
	 *
	 * @param request
	 */
	public void checkQueryUserInfoRequest(IndividualInfoQueryRequest request) throws Exception {
		if (null == request) {
			throw new BusinessException("请求对象不能为空");
		}
		if (StringUtils.isEmpty(request.getMemberId())) {
			throw new BusinessException("memberId不能为空");
		}
	}
	
	/**
	 * checkQueryIndividualInfoRequest:查询个人会员扩展信息参数校验. <br/>
	 *
	 * @param request
	 * @throws Exception
	 */
	public void checkQueryIndividualInfoRequest(IndividualInfoQueryRequest request) throws Exception {
		if (null == request) {
			throw new BusinessException("请求对象不能为空");
		}
		if (StringUtils.isEmpty(request.getMemberId())
				&& StringUtils.isEmpty(request.getCustomerId())) {
			throw new BusinessException("memberId和customerId不能同时为空");
		}
	}
	
	/**
	 * checkQueryUserAuthInfoRequest:查询用户认证信息参数校验. <br/>
	 *
	 * @param request
	 * @throws Exception
	 */
	public void checkQueryUserAuthInfoRequest(IndividualInfoQueryRequest request) throws Exception {
		if (null == request) {
			throw new BusinessException("请求对象不能为空");
		}
		if (StringUtils.isEmpty(request.getMemberId())
				&& StringUtils.isEmpty(request.getCustomerId())) {
			throw new BusinessException("memberId和customerId不能同时为空");
		}
	}
}
