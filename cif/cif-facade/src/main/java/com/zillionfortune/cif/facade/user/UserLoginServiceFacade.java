/*
 * Copyright (c) 2016, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 *
 *
 */
package com.zillionfortune.cif.facade.user;

import com.zillionfortune.cif.facade.user.dto.IndividualLoginAuthRequest;
import com.zillionfortune.cif.facade.user.dto.IndividualLoginAuthResponse;
import com.zillionfortune.cif.facade.user.dto.IndividualLoginOutRequest;
import com.zillionfortune.cif.facade.user.dto.IndividualLoginOutResponse;
import com.zillionfortune.cif.facade.user.dto.IndividualLoginRequest;
import com.zillionfortune.cif.facade.user.dto.IndividualLoginResponse;

/**
 * ClassName: UserLoginServiceFacade <br/>
 * Function: 个人_会员登录_接口. <br/>
 * Date: 2016年11月15日 下午4:30:53 <br/>
 *
 * @author kaiyun
 * @version 
 * @since JDK 1.7
 */
public interface UserLoginServiceFacade {
	
	/**
	 * login:个人会员登录. <br/>
	 *
	 * @param req
	 * @return
	 */
	public IndividualLoginResponse login(IndividualLoginRequest req);
	
	/**
	 * auth:个人会员登录身份鉴权. <br/>
	 *
	 * @param req
	 * @return
	 */
	public IndividualLoginAuthResponse auth(IndividualLoginAuthRequest req);
	
	/**
	 * loginout:个人会员登出. <br/>
	 *
	 * @param req
	 * @return
	 */
	public IndividualLoginOutResponse loginout(IndividualLoginOutRequest req);

}
