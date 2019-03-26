/*
 * Copyright (c) 2016, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 *
 *
 */
package com.zillionfortune.cif.facade.user.card;

import com.zillionfortune.cif.facade.user.card.dto.EnterpriseBankNoQueryResponse;
import com.zillionfortune.cif.facade.user.card.dto.EnterpriseBindCardQueryResponse;
import com.zillionfortune.cif.facade.user.card.dto.EnterpriseBindCardRequest;
import com.zillionfortune.cif.facade.user.card.dto.EnterpriseBindCardResponse;

/**
 * ClassName: EnterpriseBindCardServiceFacade <br/>
 * Function: 企业会员绑定银行账户接口. <br/>
 * Date: 2016年12月13日 下午6:38:36 <br/>
 *
 * @author pengting
 * @version 
 * @since JDK 1.7
 */
public interface EnterpriseBindCardServiceFacade {

	/**
	 * bindCard:企业会员绑定银行账户. <br/>
	 *
	 * @param req
	 * @return
	 */
	public EnterpriseBindCardResponse bindCard(EnterpriseBindCardRequest req);
	
	/**
	 * unBindCard:企业会员解绑银行账户. <br/>
	 *
	 * @param req
	 * @return
	 */
	public EnterpriseBindCardResponse unBindCard(EnterpriseBindCardRequest req);
	
	/**
	 * queryEnterpriseBindCard:企业会员查询已绑定银行账户. <br/>
	 *
	 * @param req
	 * @return
	 */
	public EnterpriseBindCardQueryResponse queryEnterpriseBindCard(EnterpriseBindCardRequest req);
	
	/**
	 * checkBankNo:查找银行账户号. <br/>
	 *
	 * @param bankAccountNo
	 * @return
	 */
	public EnterpriseBankNoQueryResponse findBankAccountNo(String bankAccountNo);
}
