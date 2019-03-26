/*
 * Copyright (c) ${year}, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 */
package com.zillionfortune.cif.facade.user;

import com.zillionfortune.cif.facade.user.dto.EnterpriseInfoUpdateRequest;
import com.zillionfortune.cif.facade.user.dto.EnterpriseInfoUpdateResponse;
import com.zillionfortune.cif.facade.user.dto.EnterpriseRegisterInfoUpdateRequest;
import com.zillionfortune.cif.facade.user.dto.EnterpriseRegisterInfoUpdateResponse;
import com.zillionfortune.cif.facade.user.dto.EnterpriseRegisterOpertorRequest;
import com.zillionfortune.cif.facade.user.dto.EnterpriseRegisterOpertorResponse;
import com.zillionfortune.cif.facade.user.dto.EnterpriseRegisterRequest;
import com.zillionfortune.cif.facade.user.dto.EnterpriseRegisterResponse;

/**
 * @author zhengrunlong
 * @desc 企业注册更新对外接口
 */
public interface EnterpriseServiceFacade {
	
	/**
	 * 企业客户注册
	 * @param req
	 * @return
	 */
	public EnterpriseRegisterResponse register(EnterpriseRegisterRequest req); 
	
	/**
	 * 企业会员添加操作员
	 * @param req
	 * @return
	 */
	public EnterpriseRegisterOpertorResponse registerOpertor(EnterpriseRegisterOpertorRequest req);
	
	/**
	 * 企业客户注册信息修改
	 * @param req
	 * @return
	 */
	public EnterpriseRegisterInfoUpdateResponse registerInfoUpdate(EnterpriseRegisterInfoUpdateRequest req); 

	/**
	 * 企业客户扩展信息修改
	 * @param req
	 * @return
	 */
	public EnterpriseInfoUpdateResponse enterpriseInfoUpdate(EnterpriseInfoUpdateRequest req); 
}
