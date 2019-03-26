package com.zillionfortune.cif.biz.user.check;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.zillionfortune.cif.common.enums.RegisterSource;
import com.zillionfortune.cif.common.exception.BusinessException;
import com.zillionfortune.cif.common.util.CommonUtil;
import com.zillionfortune.cif.facade.user.dto.IndividualLoginAuthRequest;
import com.zillionfortune.cif.facade.user.dto.IndividualLoginOutRequest;
import com.zillionfortune.cif.facade.user.dto.IndividualLoginRequest;

/**
 * ClassName: IndividualLoginServiceParamentChecker <br/>
 * Function: 个人_会员登入_参数校验. <br/>
 * Date: 2016年11月15日 下午5:15:31 <br/>
 *
 * @author Administrator
 * @version 
 * @since JDK 1.7
 */
@Component
public class IndividualLoginServiceParamentChecker {
	
	/**
	 * checkIndividualLoginAuthRequest:个人_会员登录_请求参数校验. <br/>
	 *
	 * @param req
	 * @throws Exception
	 */
	public void checkIndividualLoginRequest(IndividualLoginRequest req) throws BusinessException {
        //校验请求参数
        if (req == null) {
            throw new BusinessException("请求对象不能为空");
        }
        String loginSource = req.getLoginSource();
        if (StringUtils.isBlank(req.getUserName()) || StringUtils.isBlank(loginSource)) {
            throw new BusinessException("用户名、登录来源不能为空");
        }
        if( !CommonUtil.isNumeric(req.getLoginSource())){
        	throw new BusinessException("登录来源只支持数字");
        }
        if (RegisterSource.getEnumItem(Integer.parseInt(req.getLoginSource())) == null){
        	throw new BusinessException("loginSource字段的参数值不在约定的范围");
        }
    }
	
	/**
	 * checkIndividualLoginRequest:个人_登录_请求参数校验. <br/>
	 *
	 * @param req
	 * @throws Exception
	 */
	public void checkIndividualLoginAuthRequest(IndividualLoginAuthRequest req) throws BusinessException {
        //校验请求参数
        if (req == null) {
            throw new BusinessException("请求对象不能为空");
        }
        if (StringUtils.isBlank(req.getMemberId()) || StringUtils.isBlank(req.getAccessToken())) {
            throw new BusinessException("会员ID、访问token不能为空");
        }
    }
	
	/**
	 * checkIndividualLoginoutRequest:个人_登出_请求参数校验. <br/>
	 *
	 * @param req
	 * @throws Exception
	 */
	public void checkIndividualLoginoutRequest(IndividualLoginOutRequest req) throws BusinessException {
        //校验请求参数
        if (req == null) {
            throw new BusinessException("请求对象不能为空");
        }
        if (StringUtils.isBlank(req.getMemberId()) || StringUtils.isBlank(req.getAccessToken())) {
            throw new BusinessException("会员ID、访问token不能为空");
        }
    }

}
