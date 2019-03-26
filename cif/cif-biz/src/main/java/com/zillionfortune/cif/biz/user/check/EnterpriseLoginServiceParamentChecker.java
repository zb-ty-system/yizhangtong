package com.zillionfortune.cif.biz.user.check;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.zillionfortune.cif.common.enums.RegisterSource;
import com.zillionfortune.cif.common.exception.BusinessException;
import com.zillionfortune.cif.common.util.CommonUtil;
import com.zillionfortune.cif.facade.user.dto.EnterpriseLoginAuthRequest;
import com.zillionfortune.cif.facade.user.dto.EnterpriseLoginOutRequest;
import com.zillionfortune.cif.facade.user.dto.EnterpriseLoginRequest;

/**
 * ClassName: LoginServiceParamentChecker <br/>
 * Function: 企业_会员登入参数校验. <br/>
 * Date: 2016年11月15日 下午5:15:31 <br/>
 *
 * @author Administrator
 * @version 
 * @since JDK 1.7
 */
@Component
public class EnterpriseLoginServiceParamentChecker {
	
	/**
	 * checkEnterpriseLoginAuthRequest:企业_会员登录授权_请求参数校验. <br/>
	 *
	 * @param req
	 * @throws BusinessException 
	 * @throws Exception
	 */
	public void checkEnterpriseLoginRequest(EnterpriseLoginRequest req) throws BusinessException {
        //校验请求参数
        if (req == null) {
            throw new BusinessException("请求对象不能为空");
        }
        if (StringUtils.isBlank(req.getCustomerNo()) || StringUtils.isBlank(req.getLoginName()) || StringUtils.isBlank(req.getLoginSource())) {
            throw new BusinessException("客户ID、用户名或登录来源不能为空");
        }
        //登入来源
        if( !CommonUtil.isNumeric(req.getLoginSource())){
        	throw new BusinessException("登录来源只支持数字");
        }
        if (RegisterSource.getEnumItem(Integer.parseInt(req.getLoginSource())) == null){
        	throw new BusinessException("loginSource字段的参数值不在约定的范围");
        }
    }
	
	/**
	 * checkEnterpriseLoginRequest:企业_登录_请求参数校验. <br/>
	 *
	 * @param req
	 * @throws Exception
	 */
	public void checkEnterpriseLoginRequest(EnterpriseLoginAuthRequest req) throws BusinessException {
        //校验请求参数
        if (req == null) {
            throw new BusinessException("请求对象不能为空");
        }
        if (StringUtils.isBlank(req.getMemberId()) || StringUtils.isBlank(req.getOperatorId()) || StringUtils.isBlank(req.getAccessToken())) {
            throw new BusinessException("会员ID、操作员ID或访问token不能为空");
        }
    }
	
	/**
	 * checkEnterpriseLoginoutRequest:企业_登出_请求参数校验. <br/>
	 *
	 * @param req
	 * @throws Exception
	 */
	public void checkEnterpriseLoginoutRequest(EnterpriseLoginOutRequest req) throws BusinessException {
        //校验请求参数
        if (req == null) {
            throw new BusinessException("请求对象不能为空");
        }
        if (StringUtils.isBlank(req.getMemberId()) || StringUtils.isBlank(req.getOperatorId()) || StringUtils.isBlank(req.getAccessToken())) {
            throw new BusinessException("会员ID、操作员ID、访问token不能为空");
        }
    }

}
