package com.zillionfortune.cif.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zillionfortune.cif.facade.user.UserGradeServiceFacade;
import com.zillionfortune.cif.facade.user.dto.UserGradeServiceRequest;
import com.zillionfortune.cif.facade.user.dto.UserGradeServiceResponse;
import com.zillionfortune.cif.facade.user.dto.UserGradeUpdateResponse;

/**
 * ClassName: UserInfoQueryController <br/>
 * Function: 会员等级服务接口封装. <br/>
 * Date: 2016年11月22日 下午4:09:41 <br/>
 *
 * @author pengting
 * @version 
 * @since JDK 1.7
 */
@Controller
@RequestMapping(value="/usergradeservice")
public class UserGradeController {
	
	protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserGradeServiceFacade userGradeServiceFacade;
	
	/**
	 * queryuserinfo:会员等级更新
	 * 
	 * 测试url：http://localhost:8080/cif/usergradeservice/update.html?memberId=100001&grade=1&gradeType=1
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public UserGradeUpdateResponse update(UserGradeServiceRequest req){
		UserGradeUpdateResponse resp = userGradeServiceFacade.updateGrade(req);
		return resp;
	}

	/**
	 * queryindividualinfo:会员等级查询查询. <br/>
	 * 
	 * 测试url：http://localhost:8080/cif/usergradeservice/querygrade.html?memberId=100001&&gradeType=1
	 *
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/querygrade", method=RequestMethod.POST)
	public UserGradeServiceResponse queryGrade(UserGradeServiceRequest req){
		UserGradeServiceResponse resp = userGradeServiceFacade.queryGrade(req);
		return resp;
	}
}
