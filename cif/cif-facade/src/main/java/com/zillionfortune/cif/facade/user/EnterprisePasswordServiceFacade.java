/*
 * Copyright (c) 2016, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 *
 *
 */
package com.zillionfortune.cif.facade.user;

import com.zillionfortune.cif.facade.user.dto.EnterpriseLoginPasswordModifyRequest;
import com.zillionfortune.cif.facade.user.dto.EnterpriseLoginPasswordModifyResponse;
import com.zillionfortune.cif.facade.user.dto.EnterpriseLoginPasswordRetrieveRequest;
import com.zillionfortune.cif.facade.user.dto.EnterpriseLoginPasswordRetrieveResponse;
import com.zillionfortune.cif.facade.user.dto.EnterpriseTradePasswordModifyResponse;
import com.zillionfortune.cif.facade.user.dto.EnterpriseTradePasswordRetrieveResponse;
import com.zillionfortune.cif.facade.user.dto.EnterpriseTradePasswordSetResponse;
import com.zillionfortune.cif.facade.user.dto.EnterpriseTradePasswordVerifyResponse;

/**
 * ClassName: EnterprisePasswordServiceFacade <br/>
 * Function: 企业会员 重置登录密码/登录密码更新 接口. <br/>
 * Date: 2016年12月9日 下午3:47:13 <br/>
 *
 * @author wangzinan_tech@zillionfortune.com
 * @version 
 * @since JDK 1.7
 */
public interface EnterprisePasswordServiceFacade {
	
	/**
	 * retrieveLoginPassword:企业会员重置登录密码. <br/>
	 *
	 * @param req
	 * @return
	 */
	public EnterpriseLoginPasswordRetrieveResponse retrieveLoginPassword(EnterpriseLoginPasswordRetrieveRequest req); 
	
	/**
	 * modifyLoginPassword:企业会员更新登录密码. <br/>
	 *
	 * @param req
	 * @return
	 */
	public EnterpriseLoginPasswordModifyResponse modifyLoginPassword(EnterpriseLoginPasswordModifyRequest req);
	
	/**
	 * retrieveTradePassword:企业会员重置交易密码. <br/>
	 *
	 * @throws 
	 * @param memberId
	 * @param newPassword
	 * @return EnterpriseTradePasswordRetrieveResponse
	 */
	public EnterpriseTradePasswordRetrieveResponse retrieveTradePassword(String memberId,String newPassword);
	
	/**
	 * modifyTradePassword:企业会员更新交易密码. <br/>
	 *
	 * @throws 
	 * @param memberId
	 * @param newPassword
	 * @param orgiPassword
	 * @return EnterpriseTradePasswordModifyResponse
	 */
	public EnterpriseTradePasswordModifyResponse modifyTradePassword(String memberId,String newPassword,String orgiPassword);
	
	/**
	 * verifyTradePassword:TODO(这里用一句话描述这个方法的作用). <br/>
	 *
	 * @throws 
	 * @param memberId
	 * @param password
	 * @return EnterpriseTradePasswordVerifyResponse
	 */
	public EnterpriseTradePasswordVerifyResponse verifyTradePassword(String memberId,String password);
	
	/**
	 * setTradePassword:企业会员设置交易密码. <br/>
	 *
	 * @throws 
	 * @param memberId
	 * @param password
	 * @return EnterpriseTradePasswordSetResponse
	 */
	public EnterpriseTradePasswordSetResponse setTradePassword(String memberId,String password);
}
