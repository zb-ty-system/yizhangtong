/*
 * Copyright (c) 2016, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 *
 *
 */
package com.zillionfortune.cif.facade.user;

import com.zillionfortune.cif.facade.user.dto.EnterpriseUserAuditRequest;
import com.zillionfortune.cif.facade.user.dto.EnterpriseUserAuditResponse;

/**
 * ClassName: EnterpriseUserAuditServiceFacade <br/>
 * Function: 企业会员认证信息审核接口. <br/>
 * Date: 2016年12月27日 下午5:50:17 <br/>
 *
 * @author pengting
 * @version 
 * @since JDK 1.7
 */
public interface EnterpriseUserAuditServiceFacade {

	/**
	 * audit:企业会员认证信息审核. <br/>
	 *
	 * @param req
	 * @return
	 */
	public EnterpriseUserAuditResponse audit(EnterpriseUserAuditRequest req);
}
