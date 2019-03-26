package com.zillionfortune.cif.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zillionfortune.cif.facade.user.CurrentUserQueryServiceFacade;
import com.zillionfortune.cif.facade.user.dto.CurrentUserQueryServiceRequest;
import com.zillionfortune.cif.facade.user.dto.CurrentUserQueryServiceResponse;

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
@RequestMapping(value="/currentuserqueryservice")
public class CurrentUserQueryController {
	
	protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CurrentUserQueryServiceFacade currentUserQueryServiceFacade;

	/**
	 * query:个人会员登录信息查询. <br/>
	 * 
	 * 测试url：http://localhost:8080/cif/currentuserqueryservice/query.html?memberId=&accessToken=
	 *
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/query", method=RequestMethod.POST)
	public CurrentUserQueryServiceResponse query(CurrentUserQueryServiceRequest req) {
		CurrentUserQueryServiceResponse response = currentUserQueryServiceFacade.query(req);
		return response;
	}
}
