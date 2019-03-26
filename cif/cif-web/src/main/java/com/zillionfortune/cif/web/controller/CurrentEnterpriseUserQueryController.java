package com.zillionfortune.cif.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zillionfortune.cif.facade.user.CurrentEnterpriseUserQueryServiceFacade;
import com.zillionfortune.cif.facade.user.dto.CurrentEnterpriseUserQueryRequest;
import com.zillionfortune.cif.facade.user.dto.CurrentEnterpriseUserQueryResponse;

/**
 * ClassName: CurrentUserQueryController <br/>
 * Function: 个人会员登录信息查询Http接口. <br/>
 * Date: 2016年11月22日 下午6:27:35 <br/>
 *
 * @author pengting
 * @version 
 * @since JDK 1.7
 */
@Controller
@RequestMapping(value="/currententerpriseuserqueryservice")
public class CurrentEnterpriseUserQueryController {
	
	protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CurrentEnterpriseUserQueryServiceFacade currentEnterpriseUserQueryServiceFacade;

	/**
	 * query:个人会员登录信息查询. <br/>
	 * 
	 * 测试url：http://localhost:8080/cif/currententerpriseuserqueryservice/query.html?memberId=&accessToken=&operatorId=
	 *
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/query", method=RequestMethod.POST)
	public CurrentEnterpriseUserQueryResponse query(CurrentEnterpriseUserQueryRequest req) {
		CurrentEnterpriseUserQueryResponse response = currentEnterpriseUserQueryServiceFacade.query(req);
		return response;
	}
}
