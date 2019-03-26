package com.zillionfortune.cif.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zillionfortune.cif.facade.common.dto.BasePageResponse;
import com.zillionfortune.cif.facade.user.EnterpriseUserInfoQueryServiceFacade;
import com.zillionfortune.cif.facade.user.dto.EntUserAuthInfoByPageQueryRequest;
import com.zillionfortune.cif.facade.user.dto.EntUserAuthInfoQueryResponse;
import com.zillionfortune.cif.facade.user.dto.EntUserQueryInfoByPageQueryRequest;
import com.zillionfortune.cif.facade.user.dto.EnterpriseExtInfoQueryResponse;
import com.zillionfortune.cif.facade.user.dto.EnterpriseInfoQueryByCertTypeNoRequest;
import com.zillionfortune.cif.facade.user.dto.EnterpriseInfoResponse;
import com.zillionfortune.cif.facade.user.dto.EnterpriseUserInfoQueryRequest;
import com.zillionfortune.cif.facade.user.dto.EnterpriseUserNameFindResponse;
import com.zillionfortune.cif.facade.user.dto.OperatorInfoQueryResponse;

/**
 * ClassName: UserInfoQueryController <br/>
 * Function: 企业会员http查询接口封装. <br/>
 * Date: 2016年11月22日 下午4:09:41 <br/>
 *
 * @author pengting
 * @version 
 * @since JDK 1.7
 */
@Controller
@RequestMapping(value="/enterpriseuserinfoqueryservice")
public class EnterpriseUserInfoQueryController {
	
	protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private EnterpriseUserInfoQueryServiceFacade enterpriseUserInfoQueryServiceFacade;
	
	/**
	 * queryUserInfo:企业会员基本信息查询 
	 * 
	 * 测试url：http://localhost:8080/cif/enterpriseuserinfoqueryservice/queryuserinfo.html?memberId=100001100001&operatorId=1
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryuserinfo", method=RequestMethod.POST)
	public OperatorInfoQueryResponse queryUserInfo(EnterpriseUserInfoQueryRequest req){
		OperatorInfoQueryResponse resp = enterpriseUserInfoQueryServiceFacade.queryUserInfo(req);
		return resp;
	}

	/**
	 * queryInfo:企业会员扩展信息查询. <br/>
	 * 
	 * 测试url：http://localhost:8080/cif/enterpriseuserinfoqueryservice/queryinfo.html?memberId=100001100001
	 *
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryinfo", method=RequestMethod.POST)
	public EnterpriseExtInfoQueryResponse queryInfo(EnterpriseUserInfoQueryRequest req){
		EnterpriseExtInfoQueryResponse resp = enterpriseUserInfoQueryServiceFacade.queryInfo(req);
		return resp;
	}
	
	/**
	 * queryInfoByCertTypeAndNo:企业会员扩展信息查询. <br/>
	 * 
	 * 测试url：http://localhost:8080/cif/enterpriseuserinfoqueryservice/queryinfobycerttypeandno.html
	 *
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryinfobycerttypeandno", method=RequestMethod.POST)
	public EnterpriseInfoResponse queryInfoByCertTypeAndNo(EnterpriseInfoQueryByCertTypeNoRequest req){
		EnterpriseInfoResponse resp = enterpriseUserInfoQueryServiceFacade.queryInfoByCertTypeAndNo(req);
		return resp;
	}
	
	
	/**
	 * queryuserauthinfo:会员认证信息查询. <br/>
	 *
	 *测试url http://localhost:8080/cif/enterpriseuserinfoqueryservice/queryuserauthinfo.html?memberId=100001
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryuserauthinfo", method=RequestMethod.POST)
	public EntUserAuthInfoQueryResponse queryUserAuthInfo(EnterpriseUserInfoQueryRequest req){
		EntUserAuthInfoQueryResponse resp = enterpriseUserInfoQueryServiceFacade.queryUserAuthInfo(req);
		return resp;
	}
	
	/**
	 * queryUserAuthInfoByPage:企业会员认证信息分页查询. <br/>
	 *
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryuserauthinfobypage", method=RequestMethod.POST)
	public BasePageResponse queryUserAuthInfoByPage(EntUserAuthInfoByPageQueryRequest req) {
		return enterpriseUserInfoQueryServiceFacade.queryUserAuthInfoByPage(req);
	}
	
	/**
	 * queryInfoByPage:企业会员信息列表分页查询. <br/>
	 *
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryinfobypage", method=RequestMethod.POST)
	public BasePageResponse queryInfoByPage(EntUserQueryInfoByPageQueryRequest req) {
		return enterpriseUserInfoQueryServiceFacade.queryInfoByPage(req);
	}
	
	/**
	 * findUserNameIsExist: 用户登录名是否已存在. <br/>
	 *
	 * @param userName
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/findusernameisexist", method=RequestMethod.POST)
	public EnterpriseUserNameFindResponse findUserNameIsExist(String userName) {
		return enterpriseUserInfoQueryServiceFacade.findUserNameIsExist(userName);
	}
}
