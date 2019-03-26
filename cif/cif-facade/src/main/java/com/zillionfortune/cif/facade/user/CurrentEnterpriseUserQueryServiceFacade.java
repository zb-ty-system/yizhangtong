package com.zillionfortune.cif.facade.user;

import com.zillionfortune.cif.facade.user.dto.CurrentEnterpriseUserQueryRequest;
import com.zillionfortune.cif.facade.user.dto.CurrentEnterpriseUserQueryResponse;

/**
 * ClassName: CurrentEnterpriseUserQueryServiceFacade <br/>
 * Function: 企业会员登录信息查询接口. <br/>
 * Date: 2016年11月22日 下午3:35:04 <br/>
 *
 * @author pengting
 * @version 
 * @since JDK 1.7
 */
public interface CurrentEnterpriseUserQueryServiceFacade {

	/**
	 * query:企业会员登录信息查询. <br/>
	 *
	 * @param request
	 * @return
	 */
	public CurrentEnterpriseUserQueryResponse query (CurrentEnterpriseUserQueryRequest request);
}
