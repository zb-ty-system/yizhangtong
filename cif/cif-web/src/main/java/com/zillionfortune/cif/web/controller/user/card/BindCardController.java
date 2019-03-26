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

import com.zillionfortune.cif.facade.user.card.BindCardServiceFacade;
import com.zillionfortune.cif.facade.user.card.dto.BindCardQueryRequest;
import com.zillionfortune.cif.facade.user.card.dto.BindCardQueryResponse;
import com.zillionfortune.cif.facade.user.card.dto.BindCardRequest;
import com.zillionfortune.cif.facade.user.card.dto.BindCardResponse;

/**
 * ClassName: BindCardController <br/>
 * Function: 个人会员绑卡接口. <br/>
 * Date: 2016年12月13日 下午6:04:01 <br/>
 *
 * @author pengting
 * @version 
 * @since JDK 1.7
 */
@Controller
@RequestMapping(value = "/bindcardservice")
public class BindCardController {
	protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private BindCardServiceFacade bindCardServiceFacade;
	
	/**
	 * bindCard:个人会员绑定银行卡. <br/>
	 *
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/bindcard", method = RequestMethod.POST)
    @ResponseBody
	public BindCardResponse bindCard(BindCardRequest req) {
		return bindCardServiceFacade.bindCard(req);
	}
	
	/**
	 * bindCard:个人会员绑定银行卡短信验证. <br/>
	 *
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/smsverification", method = RequestMethod.POST)
    @ResponseBody
	public BindCardResponse smsVerification(BindCardRequest req) {
		return bindCardServiceFacade.smsVerification(req);
	}
	
	/**
	 * bindCard:个人会员解绑银行卡. <br/>
	 *
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/unbindcard", method = RequestMethod.POST)
    @ResponseBody
	public BindCardResponse unBindCard(BindCardRequest req) {
		return bindCardServiceFacade.unBindCard(req);
	}
	
	/**
	 * bindCard:个人会员查询已绑定银行卡. <br/>
	 *
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/querybindcard", method = RequestMethod.POST)
    @ResponseBody
	public BindCardQueryResponse queryBindCard(BindCardQueryRequest req) {
		return bindCardServiceFacade.queryBindCard(req);
	}
}
