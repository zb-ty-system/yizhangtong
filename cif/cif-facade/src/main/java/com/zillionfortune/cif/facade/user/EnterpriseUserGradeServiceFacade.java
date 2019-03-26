package com.zillionfortune.cif.facade.user;

import com.zillionfortune.cif.facade.user.dto.UserGradeServiceRequest;
import com.zillionfortune.cif.facade.user.dto.UserGradeServiceResponse;
import com.zillionfortune.cif.facade.user.dto.UserGradeUpdateResponse;

/**
 * ClassName: EnterpriseUserGradeServiceFacade <br/>
 * Function:  企业会员等级相关服务. <br/>
 * Date: 2016年11月28日 上午11:42:39 <br/>
 *
 * @author pengting
 * @version 
 * @since JDK 1.7
 */
public interface EnterpriseUserGradeServiceFacade {
	/**
	 * 会员等级更新
	 * 
	 * @param request
	 * @return
	 */
	UserGradeUpdateResponse updateGrade(UserGradeServiceRequest request);

	/**
	 * 会员等级查询
	 * 
	 * @param request
	 * @return
	 */
	UserGradeServiceResponse queryGrade(UserGradeServiceRequest request);
}
