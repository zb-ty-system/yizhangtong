/*
 * Copyright (c) 2016, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 *
 *
 */
package com.zillionfortune.cif.facade.user.card;

import com.zillionfortune.cif.facade.user.card.dto.BindCardQueryRequest;
import com.zillionfortune.cif.facade.user.card.dto.BindCardQueryResponse;
import com.zillionfortune.cif.facade.user.card.dto.BindCardRequest;
import com.zillionfortune.cif.facade.user.card.dto.BindCardResponse;

/**
 * ClassName: BindCardServiceFacade <br/>
 * Function: 个人会员绑定银行卡服务接口. <br/>
 * Date: 2016年12月12日 下午3:34:48 <br/>
 *
 * @author pengting
 * @version 
 * @since JDK 1.7
 */
public interface BindCardServiceFacade {
	
	/**
	 * bindCard:个人会员绑定银行卡. <br/>
	 *
	 * @param req
	 * @return
	 */
	public BindCardResponse bindCard(BindCardRequest req);
	
	/**
	 * bindCard:个人会员绑定银行卡短信验证. <br/>
	 *
	 * @param req
	 * @return
	 */
	public BindCardResponse smsVerification(BindCardRequest req);
	
	/**
	 * bindCard:个人会员解绑银行卡. <br/>
	 *
	 * @param req
	 * @return
	 */
	public BindCardResponse unBindCard(BindCardRequest req);
	
	/**
	 * bindCard:个人会员查询已绑定银行卡. <br/>
	 *
	 * @param req
	 * @return
	 */
	public BindCardQueryResponse queryBindCard(BindCardQueryRequest req);
}
