/*
 * Copyright (c) 2016, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 *
 *
 */
package com.zillionfortune.cif.service.user;

import com.zillionfortune.cif.dal.entity.EnterpriseMember;
import com.zillionfortune.cif.dal.entity.EnterpriseOperator;

/**
 * ClassName: EnterpriseStatusService <br/>
 * Function: 企业会员冻结/解冻/注销业务Service. <br/>
 * Date: 2016年12月2日 上午11:29:30 <br/>
 *
 * @author wangzinan_tech@zillionfortune.com
 * @version 
 * @since JDK 1.7
 */
public interface EnterpriseStatusService {

	/**
	 * frozenEnterpriseAndAllOperator:冻结企业会员以及企业下面所有的操作员. <br/>
	 *
	 * @param enterpriseMember
	 */
	public void frozenEnterpriseAndAllOperator(EnterpriseMember enterpriseMember);
	
	/**
	 * unfreezeEnterpriseAndAllOperator:解冻企业会员以及企业下面所有的操作员. <br/>
	 *
	 * @param enterpriseMember
	 */
	public void unfreezeEnterpriseAndAllOperator(EnterpriseMember enterpriseMember);
	
	/**
	 * unfreezeEnterpriseAndOneOperator:解冻企业会员以及企业下面单个操作员. <br/>
	 *
	 * @param enterpriseMember
	 * @param enterpriseOperator
	 */
	public void unfreezeEnterpriseAndOneOperator(EnterpriseMember enterpriseMember, EnterpriseOperator enterpriseOperator);
	
	/**
	 * cancelEnterpriseAndAllOperator:注销企业会员以及企业下面所有的操作员. <br/>
	 *
	 * @param enterpriseMember
	 */
	public void cancelEnterpriseAndAllOperator(EnterpriseMember enterpriseMember);
	
}

