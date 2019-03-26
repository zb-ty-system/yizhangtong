package com.zillionfortune.cif.biz.user.check;

import org.springframework.stereotype.Component;

import com.zillionfortune.cif.common.enums.EnterpriseCertType;
import com.zillionfortune.cif.common.exception.BusinessException;
import com.zillionfortune.cif.common.util.StringUtils;
import com.zillionfortune.cif.facade.user.dto.EnterpriseInfoQueryByCertTypeNoRequest;
import com.zillionfortune.cif.facade.user.dto.EnterpriseUserInfoQueryRequest;

/**
 * ClassName: EnterpriseUserInfoQueryServiceParameterChecker <br/>
 * Function: 企业会员查询服务参数校验. <br/>
 * Date: 2016年11月23日 上午9:34:03 <br/>
 *
 * @author pengting
 * @version 
 * @since JDK 1.7
 */
@Component
public class EnterpriseUserInfoQueryServiceParameterChecker {
	
	/**
	 * checkQueryUserInfoRequest:查询企业会员基础信息（操作员）参数校验. <br/>
	 *
	 */
	public void checkQueryUserInfoRequest(EnterpriseUserInfoQueryRequest request) throws Exception {
		if (null == request) {
			throw new BusinessException("请求对象不能为空");
		}
		if (StringUtils.isEmpty(request.getMemberId()) && null == request.getOperatorId()) {
			throw new BusinessException("memberId、 operatorId不能同时为空");
		}
	}
	
	/**
	 * checkQueryInfoRequest:查询企业扩展信息参数校验. <br/>
	 *
	 * @param request
	 * @throws Exception
	 */
	public void checkQueryInfoRequest(EnterpriseUserInfoQueryRequest request) throws Exception {
		if (null == request) {
			throw new BusinessException("请求对象不能为空");
		}
		if (StringUtils.isEmpty(request.getMemberId())
				&& StringUtils.isEmpty(request.getCustomerId())) {
			throw new BusinessException("memberId和customerId不能同时为空");
		}
	}
	
	/**
	 * checkQueryInfoByCertTypeAndNoRequest:查询企业基本信息参数校验. <br/>
	 *
	 * @param request
	 * @throws Exception
	 */
	public void checkQueryInfoByCertTypeAndNoRequest(EnterpriseInfoQueryByCertTypeNoRequest request) throws Exception {
		
		if (null == request) {
			throw new BusinessException("请求对象不能为空");
		}
		
		if (request.getCertificateType() == null) {
			throw new BusinessException("certificateType不能为空");
		}
		
		if (EnterpriseCertType.getEnumItem(request.getCertificateType()) == null){
        	throw new BusinessException("certificateType字段的参数值不在约定的范围");
        }
		 
		if (StringUtils.isEmpty(request.getCertificateNo())) {
			throw new BusinessException("certificateNo不能为空");
		}
		
	}
	
	
	/**
	 * checkQueryUserAuthInfoRequest:查询企业认证信息参数校验. <br/>
	 *
	 * @param request
	 * @throws BusinessException
	 */
	public void checkQueryUserAuthInfoRequest(EnterpriseUserInfoQueryRequest request) throws BusinessException {
		if (null == request) {
			throw new BusinessException("请求对象不能为空");
		}
		if (StringUtils.isEmpty(request.getMemberId())) {
			throw new BusinessException("memberId不能为空");
		}
	}

}
