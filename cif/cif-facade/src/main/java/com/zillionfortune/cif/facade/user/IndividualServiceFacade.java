/*
 * Copyright (c) ${year}, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 */
package com.zillionfortune.cif.facade.user;

import com.zillionfortune.cif.facade.user.dto.IndividualInfoUpdateRequest;
import com.zillionfortune.cif.facade.user.dto.IndividualInfoUpdateResponse;
import com.zillionfortune.cif.facade.user.dto.IndividualRegisterRequest;
import com.zillionfortune.cif.facade.user.dto.IndividualRegisterResponse;
import com.zillionfortune.cif.facade.user.dto.IndividualRegisterInfoUpdateRequest;
import com.zillionfortune.cif.facade.user.dto.IndividualRegisterInfoUpdateResponse;

/**
 * ClassName: IndividualServiceFacade <br/>
 * Function: 企业注册更新对外接口. <br/>
 * Date: 2016年12月9日 下午2:01:33 <br/>
 *
 * @author zhengrunlong@zillionfortune.com
 * @version 
 * @since JDK 1.7
 */
public interface IndividualServiceFacade {
	
	/**
	 * 个人客户注册
	 * @param req
	 * @return
	 */
	public IndividualRegisterResponse register(IndividualRegisterRequest req); 
	
	/**
	 * 个人客户注册信息修改
	 * @param req
	 * @return
	 */
	public IndividualRegisterInfoUpdateResponse registerInfoUpdate(IndividualRegisterInfoUpdateRequest req); 
	
	/**
	 * 个人客户扩展信息修改
	 * @param req
	 * @return
	 */
	public IndividualInfoUpdateResponse individualInfoUpdate(IndividualInfoUpdateRequest req); 
	
}
