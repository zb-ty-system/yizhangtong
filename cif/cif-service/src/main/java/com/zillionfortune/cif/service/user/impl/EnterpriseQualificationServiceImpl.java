/*
 * Copyright (c) 2016, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 *
 *
 */
package com.zillionfortune.cif.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zillionfortune.cif.dal.dao.EnterpriseQualificationDao;
import com.zillionfortune.cif.dal.entity.EnterpriseQualification;
import com.zillionfortune.cif.service.user.EnterpriseQualificationService;

/**
 * ClassName: EnterpriseQualificationServiceImpl <br/>
 * Function: 企业资质信息 Service 实现. <br/>
 * Date: 2016年12月20日 下午1:52:27 <br/>
 *
 * @author pengting
 * @version 
 * @since JDK 1.7
 */
@Service
public class EnterpriseQualificationServiceImpl implements EnterpriseQualificationService{
	@Autowired
	private EnterpriseQualificationDao enterpriseQualificationDao;

	/**
	 * @see com.zillionfortune.cif.service.user.EnterpriseQualificationService#updateByCustomerIdKeySelective(com.zillionfortune.cif.dal.entity.EnterpriseQualification)
	 */
	@Override
	public void updateByCustomerIdKeySelective(EnterpriseQualification entity) {
		enterpriseQualificationDao.updateByCustomerIdKeySelective(entity);
	}

	/**
	 * @see com.zillionfortune.cif.service.user.EnterpriseQualificationService#selectByCustormerId(java.lang.String)
	 */
	@Override
	public EnterpriseQualification selectByCustormerId(String customerId) {
		return enterpriseQualificationDao.selectByCustormerId(customerId);
	}
}
