/*
 * Copyright (c) 2016, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 *
 *
 */
package com.zillionfortune.cif.facade.user;

import com.zillionfortune.cif.facade.user.dto.UserStatusUpdateRequest;
import com.zillionfortune.cif.facade.user.dto.UserStatusUpdateResponse;

/**
 * ClassName: UserStatusServiceFacade <br/>
 * Function: 会员冻结/解冻/注销业务  对外接口. <br/>
 * Date: 2016年12月9日 下午2:10:24 <br/>
 *
 * @author wangzinan_tech@zillionfortune.com
 * @version 
 * @since JDK 1.7
 */
public interface UserStatusServiceFacade {
	
	/**
	 * userFrozen:会员冻结. <br/>
	 *
	 * @param req
	 * @return
	 */
	public UserStatusUpdateResponse userFrozen(UserStatusUpdateRequest req); 
	
	/**
	 * userUnfreeze:会员解冻. <br/>
	 *
	 * @param req
	 * @return
	 */
	public UserStatusUpdateResponse userUnfreeze(UserStatusUpdateRequest req);
	
	/**
	 * userCancel:会员注销. <br/>
	 *
	 * @param req
	 * @return
	 */
	public UserStatusUpdateResponse userCancel(UserStatusUpdateRequest req);
	
}
