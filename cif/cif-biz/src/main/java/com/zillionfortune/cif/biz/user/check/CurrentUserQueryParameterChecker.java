package com.zillionfortune.cif.biz.user.check;

import org.springframework.stereotype.Component;

import com.zillionfortune.cif.common.exception.BusinessException;
import com.zillionfortune.cif.common.util.StringUtils;
import com.zillionfortune.cif.facade.user.dto.CurrentUserQueryServiceRequest;

/**
 * ClassName: CurrentUserQueryParameterChecker <br/>
 * Function: 会员登录信息查询接口_参数校验. <br/>
 * Date: 2016年11月23日 上午10:41:18 <br/>
 *
 * @author pengting
 * @version 
 * @since JDK 1.7
 */
@Component
public class CurrentUserQueryParameterChecker {
	
	/**
	 * checkQueryRequest:当前用户登录信息查询参数校验. <br/>
	 *
	 * @param request
	 * @throws Exception
	 */
	public void checkQueryRequest(CurrentUserQueryServiceRequest request) throws Exception {
		if (null == request) {
			throw new BusinessException("请求对象不能为空");
		}
		if (StringUtils.isEmpty(request.getMemberId()) || StringUtils.isEmpty(request.getAccessToken())) {
			throw new BusinessException("memberId,accessToken皆不能为空");
		}
	}

}
