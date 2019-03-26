/*
 * Copyright (c) 2016, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 *
 *
 */
package com.zillionfortune.cif.facade.user;
import com.zillionfortune.cif.facade.user.dto.EnterpriseStatusUpdateRequest;
import com.zillionfortune.cif.facade.user.dto.EnterpriseStatusUpdateResponse;

/**
 * ClassName: EnterpriseUserStatusServiceFacade <br/>
 * Function: 企业会员冻结/解冻/注销业务  对外接口. <br/>
 * Date: 2016年12月1日 下午4:08:41 <br/>
 *
 * @author wangzinan_tech@zillionfortune.com
 * @version 
 * @since JDK 1.7
 */
public interface EnterpriseUserStatusServiceFacade {
	/**
	 * enterpriseFrozen:企业会员冻结. <br/>
	 *
	 * @param req 企业会员冻结/解冻/注销业务Request
	 * @return 企业会员冻结/解冻/注销业务Response
	 */
	public EnterpriseStatusUpdateResponse enterpriseFrozen(EnterpriseStatusUpdateRequest req); 
	
	/**
	 * enterpriseUnfreeze:企业会员解冻. <br/>
	 *
	 * @param req 企业会员冻结/解冻/注销业务Request
	 * @return 企业会员冻结/解冻/注销业务Response
	 */
	public EnterpriseStatusUpdateResponse enterpriseUnfreeze(EnterpriseStatusUpdateRequest req);
	
	/**
	 * enterpriseCancel:企业会员注销. <br/>
	 *
	 * @param req 企业会员冻结/解冻/注销业务Request
	 * @return 企业会员冻结/解冻/注销业务Response
	 */
	public EnterpriseStatusUpdateResponse enterpriseCancel(EnterpriseStatusUpdateRequest req);
	
}
