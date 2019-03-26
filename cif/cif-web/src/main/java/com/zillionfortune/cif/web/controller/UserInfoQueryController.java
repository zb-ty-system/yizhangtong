package com.zillionfortune.cif.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zillionfortune.cif.facade.user.UserInfoQueryServiceFacade;
import com.zillionfortune.cif.facade.user.dto.IndividualBasicInfoQueryResponse;
import com.zillionfortune.cif.facade.user.dto.IndividualExtInfoQueryResponse;
import com.zillionfortune.cif.facade.user.dto.IndividualInfoQueryRequest;
import com.zillionfortune.cif.facade.user.dto.UserAuthInfoQueryResponse;

/**
 * ClassName: UserInfoQueryController <br/>
 * Function: 个人会员http查询接口封装. <br/>
 * Date: 2016年11月22日 下午4:09:41 <br/>
 *
 * @author pengting
 * @version 
 * @since JDK 1.7
 */
@Controller
@RequestMapping(value="/userinfoqueryservice")
public class UserInfoQueryController {
	
	protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserInfoQueryServiceFacade userInfoQueryServiceFacade;
	
	/**
	 * queryuserinfo:个人会员基本信息查询. 
	 * 
	 * 测试url：http://localhost:8080/cif/userinfoqueryservice/queryuserinfo.html?memberId=100001
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryuserinfo", method=RequestMethod.POST)
	public IndividualBasicInfoQueryResponse queryUserInfo(IndividualInfoQueryRequest req){
		IndividualBasicInfoQueryResponse resp = userInfoQueryServiceFacade.queryUserInfo(req);
		return resp;
	}

	/**
	 * queryindividualinfo:个人会员扩展信息查询. <br/>
	 * 
	 * 测试url：http://localhost:8080/cif/userinfoqueryservice/queryindividualinfo.html?memberId=100001
	 *
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryindividualinfo", method=RequestMethod.POST)
	public IndividualExtInfoQueryResponse queryIndividualInfo(IndividualInfoQueryRequest req){
		IndividualExtInfoQueryResponse resp = userInfoQueryServiceFacade.queryIndividualInfo(req);
		return resp;
	}
	
	/**
	 * queryuserauthinfo:会员认证信息查询. <br/>
	 *
	 *测试url http://localhost:8080/cif/userinfoqueryservice/queryuserauthinfo.html?memberId=100001
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryuserauthinfo", method=RequestMethod.POST)
	public UserAuthInfoQueryResponse queryUserAuthInfo(IndividualInfoQueryRequest req){
		UserAuthInfoQueryResponse resp = userInfoQueryServiceFacade.queryUserAuthInfo(req);
		return resp;
	}
}
