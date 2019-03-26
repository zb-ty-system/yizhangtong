package com.zillionfortune.cif.facade.user;

import com.zillionfortune.cif.facade.user.dto.UserGradeServiceRequest;
import com.zillionfortune.cif.facade.user.dto.UserGradeServiceResponse;
import com.zillionfortune.cif.facade.user.dto.UserGradeUpdateResponse;

/**
 * @desc 会员等级相关服务
 * @author pengting
 * @date 2016年11月11日 上午9:24:47
 * @version 1.0.0
 */
public interface UserGradeServiceFacade {
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
