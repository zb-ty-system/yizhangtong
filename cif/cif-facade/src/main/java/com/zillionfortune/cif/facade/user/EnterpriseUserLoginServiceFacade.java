/*
 * Copyright (c) 2016, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 *
 *
 */
package com.zillionfortune.cif.facade.user;

import com.zillionfortune.cif.facade.user.dto.EnterpriseLoginAuthRequest;
import com.zillionfortune.cif.facade.user.dto.EnterpriseLoginAuthResponse;
import com.zillionfortune.cif.facade.user.dto.EnterpriseLoginOutRequest;
import com.zillionfortune.cif.facade.user.dto.EnterpriseLoginOutResponse;
import com.zillionfortune.cif.facade.user.dto.EnterpriseLoginRequest;
import com.zillionfortune.cif.facade.user.dto.EnterpriseLoginResponse;

/**
 * ClassName: UserLoginServiceFacade <br/>
 * Function: 企业会员登录接口. <br/>
 * Date: 2016年11月15日 下午4:30:53 <br/>
 *
 * @author kaiyun
 * @version 
 * @since JDK 1.7
 */
public interface EnterpriseUserLoginServiceFacade {
	
	/**
	 * login:企业会员登录. <br/>
	 *
	 * @param req
	 * @return
	 */
	public EnterpriseLoginResponse login(EnterpriseLoginRequest req);
	
	/**
	 * auth:企业会员登录鉴权. <br/>
	 *
	 * @param req
	 * @return
	 */
	public EnterpriseLoginAuthResponse auth(EnterpriseLoginAuthRequest req);
	
	/**
	 * loginout:企业会员登出. <br/>
	 *
	 * @param req
	 * @return
	 */
	public EnterpriseLoginOutResponse loginout(EnterpriseLoginOutRequest req);
	
	
	

}
