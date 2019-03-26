/*
 * Copyright (c) 2016, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 *
 *
 */
package com.zillionfortune.cif.facade.user;

import com.zillionfortune.cif.facade.user.dto.UserAuthServiceRequest;
import com.zillionfortune.cif.facade.user.dto.UserAuthServiceResponse;

/**
 * ClassName: UserAuthServiceFacade <br/>
 * Function:  个人会员实名认证. <br/>
 * Date: 2016年12月2日 上午10:47:59 <br/>
 *
 * @author pengting
 * @version 
 * @since JDK 1.7
 */
public interface UserAuthServiceFacade {

	/**
	 * realNameAuth:实名认证. <br/>
	 *
	 * @param req
	 * @return
	 */
	public UserAuthServiceResponse realNameAuth(UserAuthServiceRequest req);
}
