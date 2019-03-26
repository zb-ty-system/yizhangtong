/*
 * Copyright (c) 2016, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 *
 *
 */
package com.zillionfortune.cif.service.user;

import com.zillionfortune.cif.dal.entity.EnterpriseQualification;

/**
 * ClassName: EnterpriseQualificationService <br/>
 * Function: 企业资质信息 Service. <br/>
 * Date: 2016年12月20日 下午1:51:36 <br/>
 *
 * @author pengting
 * @version 
 * @since JDK 1.7
 */
public interface EnterpriseQualificationService {
	
	/**
	 * updateByCustomerIdKeySelective:根据customerID选择性更新数据. <br/>
	 *
	 * @param entity
	 */
	public void updateByCustomerIdKeySelective(EnterpriseQualification entity);
	
	/**
	 * selectByCustormerId:根据customerId查询. <br/>
	 *
	 * @param customerId
	 * @return
	 */
	public EnterpriseQualification selectByCustormerId(String customerId);

}
