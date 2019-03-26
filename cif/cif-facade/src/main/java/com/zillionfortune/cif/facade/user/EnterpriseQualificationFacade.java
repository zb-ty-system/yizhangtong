/*
 * Copyright (c) 2016, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 *
 *
 */
package com.zillionfortune.cif.facade.user;

import com.zillionfortune.cif.facade.user.dto.EnterpriseQualificationUpdateRequest;
import com.zillionfortune.cif.facade.user.dto.EnterpriseQualificationUpdateResponse;

/**
 * ClassName: EnterpriseQualificationFacade <br/>
 * Function: 企业用户资质数据操作接口. <br/>
 * Date: 2016年12月20日 上午11:00:26 <br/>
 *
 * @author pengting
 * @version 
 * @since JDK 1.7
 */
public interface EnterpriseQualificationFacade {
	
	/**
	 * qualificationUpdate ：企业资质信息更新 <br/>
	 *
	 * @param req
	 * @return
	 */
	public EnterpriseQualificationUpdateResponse qualificationUpdate(EnterpriseQualificationUpdateRequest req);

}
