package com.zillionfortune.cif.facade.user;

import com.zillionfortune.cif.facade.user.dto.IndividualBasicInfoQueryResponse;
import com.zillionfortune.cif.facade.user.dto.IndividualExtInfoQueryResponse;
import com.zillionfortune.cif.facade.user.dto.IndividualInfoQueryRequest;
import com.zillionfortune.cif.facade.user.dto.UserAuthInfoQueryResponse;

/**
 * @desc 个人会员信息查询服务
 * @author pengting
 * @date 2016年11月10日 下午6:06:48
 * @version 1.0.0
 */
public interface UserInfoQueryServiceFacade {
	/**
	 * queryUserInfo: 查询个人会员基础信息. <br/>
	 *
	 * @param request
	 * @return
	 */
	IndividualBasicInfoQueryResponse queryUserInfo(
			IndividualInfoQueryRequest request);

	/**
	 * queryUserExtInfo:查询个人会员扩展信息. <br/>
	 *
	 * @param request
	 * @return
	 */
	IndividualExtInfoQueryResponse queryIndividualInfo(
			IndividualInfoQueryRequest request);

	/**
	 * queryUserAuthInfo:查询用户认证信息. <br/>
	 *
	 * @param request
	 * @return
	 */
	UserAuthInfoQueryResponse queryUserAuthInfo(
			IndividualInfoQueryRequest request);

}
