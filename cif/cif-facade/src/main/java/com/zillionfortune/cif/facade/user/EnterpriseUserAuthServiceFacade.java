/*
 * Copyright (c) 2016, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 *
 *
 */
package com.zillionfortune.cif.facade.user;

import com.zillionfortune.cif.facade.user.dto.EnterpriseUserAuthServiceRequest;
import com.zillionfortune.cif.facade.user.dto.EnterpriseUserAuthServiceResponse;

/**
 * ClassName: EnterpriseUserAuthServiceFacade <br/>
 * Function: 企业会员认证. <br/>
 * Date: 2016年12月2日 下午5:11:59 <br/>
 *
 * @author pengting
 * @version 
 * @since JDK 1.7
 */
public interface EnterpriseUserAuthServiceFacade {

	/**
	 * realNameAuth:企业会员认证. <br/>
	 *
	 * @param req
	 * @return
	 */
	public EnterpriseUserAuthServiceResponse realNameAuth(EnterpriseUserAuthServiceRequest req);
}
