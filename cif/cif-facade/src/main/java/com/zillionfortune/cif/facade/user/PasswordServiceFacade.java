/*
 * Copyright (c) 2016, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 *
 *
 */
package com.zillionfortune.cif.facade.user;

import com.zillionfortune.cif.facade.user.dto.IndividualLoginPasswordModifyRequest;
import com.zillionfortune.cif.facade.user.dto.IndividualLoginPasswordModifyResponse;
import com.zillionfortune.cif.facade.user.dto.IndividualLoginPasswordRetrieveRequest;
import com.zillionfortune.cif.facade.user.dto.IndividualLoginPasswordRetrieveResponse;
import com.zillionfortune.cif.facade.user.dto.IndividualTradePasswordModifyResponse;
import com.zillionfortune.cif.facade.user.dto.IndividualTradePasswordRetrieveResponse;
import com.zillionfortune.cif.facade.user.dto.IndividualTradePasswordSetResponse;
import com.zillionfortune.cif.facade.user.dto.IndividualTradePasswordVerifyResponse;

/**
 * ClassName: PasswordServiceFacade <br/>
 * Function: 个人会员 重置登录密码/登录密码更新 接口. <br/>
 * Date: 2016年12月9日 下午2:45:33 <br/>
 *
 * @author wangzinan_tech@zillionfortune.com
 * @version 
 * @since JDK 1.7
 */
public interface PasswordServiceFacade {
	
	/**
	 * retrieveLoginPassword:个人会员重置登录密码. <br/>
	 *
	 * @param req
	 * @return
	 */
	public IndividualLoginPasswordRetrieveResponse retrieveLoginPassword(IndividualLoginPasswordRetrieveRequest req); 
	
	/**
	 * modifyLoginPassword:个人会员更新登录密码. <br/>
	 *
	 * @param req
	 * @return
	 */
	public IndividualLoginPasswordModifyResponse modifyLoginPassword(IndividualLoginPasswordModifyRequest req);
	
	/**
	 * setTradePassword:个人会员设置交易密码. <br/>
	 *
	 * @throws 
	 * @param memberId
	 * @param password
	 * @return IndividualTradePasswordSetResponse
	 */
	public IndividualTradePasswordSetResponse setTradePassword(String memberId,String password);
	
	/**
	 * retrieveTradePassword:个人会员重置交易密码. <br/>
	 *
	 * @throws 
	 * @param memberId
	 * @param newPassword
	 * @return IndividualTradePasswordRetrieveResponse
	 */
	public IndividualTradePasswordRetrieveResponse retrieveTradePassword(String memberId,String newPassword);
	
	/**
	 * modifyTradePassword:个人会员更新交易密码. <br/>
	 *
	 * @throws 
	 * @param memberId
	 * @param newPassword
	 * @param orgiPassword
	 * @return IndividualTradePasswordModifyResponse
	 */
	public IndividualTradePasswordModifyResponse modifyTradePassword(String memberId,String newPassword,String orgiPassword);
	
	/**
	 * verifyTradePassword:个人会员验证交易密码. <br/>
	 *
	 * @throws 
	 * @param memberId
	 * @param password
	 * @return IndividualTradePasswordVerifyResponse
	 */
	public IndividualTradePasswordVerifyResponse verifyTradePassword(String memberId,String password);

}
