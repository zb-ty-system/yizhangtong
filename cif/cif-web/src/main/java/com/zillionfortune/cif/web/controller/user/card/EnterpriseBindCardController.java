/*
 * Copyright (c) 2016, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 *
 *
 */
package com.zillionfortune.cif.web.controller.user.card;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zillionfortune.cif.facade.user.card.EnterpriseBindCardServiceFacade;
import com.zillionfortune.cif.facade.user.card.dto.EnterpriseBankNoQueryResponse;
import com.zillionfortune.cif.facade.user.card.dto.EnterpriseBindCardQueryResponse;
import com.zillionfortune.cif.facade.user.card.dto.EnterpriseBindCardRequest;
import com.zillionfortune.cif.facade.user.card.dto.EnterpriseBindCardResponse;

/**
 * ClassName: BindCardController <br/>
 * Function: 企业会员绑定银行账户. <br/>
 * Date: 2016年12月13日 下午6:04:01 <br/>
 *
 * @author pengting
 * @version 
 * @since JDK 1.7
 */
@Controller
@RequestMapping(value = "/enterprisebindcardservice")
public class EnterpriseBindCardController {
	protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private EnterpriseBindCardServiceFacade facade;
	
	/**
	 * bindCard:企业会员绑定银行账户. <br/>
	 *
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/bindcard", method = RequestMethod.POST)
    @ResponseBody
	public EnterpriseBindCardResponse bindCard(EnterpriseBindCardRequest req) {
		return facade.bindCard(req);
	}
	
	/**
	 * unBindCard:企业会员解绑银行账户. <br/>
	 *
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/unbindcard", method = RequestMethod.POST)
    @ResponseBody
	public EnterpriseBindCardResponse unBindCard(EnterpriseBindCardRequest req) {
		return facade.unBindCard(req);
	}
	
	/**
	 * queryEnterpriseBindCard:企业会员查询已绑定银行账户. <br/>
	 *
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/querybindcard", method = RequestMethod.POST)
    @ResponseBody
	public EnterpriseBindCardQueryResponse queryEnterpriseBindCard(EnterpriseBindCardRequest req) {
		return facade.queryEnterpriseBindCard(req);
	}
	
	/**
	 * findBankAccountNo: 查找银行账号是否存在. <br/>
	 *
	 * @param bankAccountNo
	 * @return
	 */
	@RequestMapping(value = "/findbankaccountno", method = RequestMethod.POST)
    @ResponseBody
	public EnterpriseBankNoQueryResponse findBankAccountNo(String bankAccountNo) {
		return facade.findBankAccountNo(bankAccountNo);
	}
	
}
