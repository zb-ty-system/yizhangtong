package com.zillionfortune.cif.facade.user;

import com.zillionfortune.cif.facade.common.dto.BasePageResponse;
import com.zillionfortune.cif.facade.user.dto.EntUserAuthInfoByPageQueryRequest;
import com.zillionfortune.cif.facade.user.dto.EntUserAuthInfoQueryResponse;
import com.zillionfortune.cif.facade.user.dto.EntUserQueryInfoByPageQueryRequest;
import com.zillionfortune.cif.facade.user.dto.EnterpriseExtInfoQueryResponse;
import com.zillionfortune.cif.facade.user.dto.EnterpriseInfoQueryByCertTypeNoRequest;
import com.zillionfortune.cif.facade.user.dto.EnterpriseInfoResponse;
import com.zillionfortune.cif.facade.user.dto.EnterpriseUserInfoQueryRequest;
import com.zillionfortune.cif.facade.user.dto.EnterpriseUserNameFindResponse;
import com.zillionfortune.cif.facade.user.dto.OperatorInfoQueryResponse;

/**
 * ClassName: EnterpriseUserInfoQueryServiceFacade <br/>
 * Function: 企业会员查询服务. <br/>
 * Date: 2016年11月21日 上午10:17:30 <br/>
 *
 * @author pengting
 * @version
 * @since JDK 1.7
 */
public interface EnterpriseUserInfoQueryServiceFacade {

	/**
	 * queryUserInfo:查询企业会员基础信息（操作员）. <br/>
	 *
	 * @param request
	 * @return
	 */
	OperatorInfoQueryResponse queryUserInfo(
			EnterpriseUserInfoQueryRequest request);

	/**
	 * queryInfoByCertTypeAndNo:根据证件类型证件号码查询企业信息. <br/>
	 *
	 * @param request
	 * @return
	 */
	EnterpriseInfoResponse queryInfoByCertTypeAndNo(EnterpriseInfoQueryByCertTypeNoRequest request);
	
	/**
	 * queryInfo:查询企业扩展信息. <br/>
	 *
	 * @param request
	 * @return
	 */
	EnterpriseExtInfoQueryResponse queryInfo(
			EnterpriseUserInfoQueryRequest request);
	
	/**
	 * queryUserAuthInfo:查询企业用户认证信息. <br/>
	 *
	 * @param request
	 * @return
	 */
	EntUserAuthInfoQueryResponse queryUserAuthInfo(
			EnterpriseUserInfoQueryRequest request);
	
	/**
	 * queryUserAuthInfoByPage:企业会员认证信息分页查询. <br/>
	 *
	 * @param req
	 * @return
	 */
	BasePageResponse queryUserAuthInfoByPage(EntUserAuthInfoByPageQueryRequest req);
	
	/**
	 * queryInfoByPage:企业会员信息列表分页查询. <br/>
	 *
	 * @param req
	 * @return
	 */
	BasePageResponse queryInfoByPage(EntUserQueryInfoByPageQueryRequest req);
	
	/**
	 * findUserNameIsExist: 用户登录名是否已存在. <br/>
	 *
	 * @param userName
	 * @return
	 */
	public EnterpriseUserNameFindResponse findUserNameIsExist(String userName);
}
