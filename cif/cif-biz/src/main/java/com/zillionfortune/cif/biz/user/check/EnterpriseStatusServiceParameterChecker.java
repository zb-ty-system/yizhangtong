/*
 * Copyright (c) 2016, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 *
 *
 */
package com.zillionfortune.cif.biz.user.check;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.zillionfortune.cif.common.exception.BusinessException;
import com.zillionfortune.cif.facade.user.dto.EnterpriseStatusUpdateRequest;

/**
 * ClassName: EnterpriseStatusServiceParameterChecker <br/>
 * Function: 企业会员冻结/解冻/注销业务  请求参数校验. <br/>
 * Date: 2016年12月2日 上午9:40:39 <br/>
 *
 * @author wangzinan_tech@zillionfortune.com
 * @version 
 * @since JDK 1.7
 */
@Component
public class EnterpriseStatusServiceParameterChecker {

	/**
	 * checkEnterpriseStatusUpdateRequest:企业会员冻结/解冻/注销业务  请求参数校验. <br/>
	 *
	 * @param req 企业会员冻结/解冻/注销业务Request
	 * @throws Exception
	 */
	public void checkEnterpriseStatusUpdateRequest(EnterpriseStatusUpdateRequest req)
            throws Exception {
        //校验请求参数
        if (req == null) {
            throw new BusinessException("请求对象不能为空");
        }
        if (StringUtils.isBlank(req.getMemberId())) {
            throw new BusinessException("memberId字段不能为空");
        }
        if (StringUtils.isNotBlank(req.getOperatorId()) 
				&& !StringUtils.isNumeric(req.getOperatorId())) {
			throw new BusinessException("operatorId字段的参数值必须是整数");
		}
    }
	
}
