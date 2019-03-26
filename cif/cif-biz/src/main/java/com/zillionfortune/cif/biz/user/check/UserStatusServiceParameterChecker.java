package com.zillionfortune.cif.biz.user.check;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.zillionfortune.cif.common.exception.BusinessException;
import com.zillionfortune.cif.facade.user.dto.UserStatusUpdateRequest;

/**
 * 个人会员冻结/解冻/注销业务  请求参数校验
 * 
 * @author wangzinan
 *
 */

@Component
public class UserStatusServiceParameterChecker {

	/**
	 * 个人会员冻结/解冻/注销业务  请求参数校验
	 * 
	 * @param req
	 * @throws Exception
	 */
	public void checkUserStatusUpdateRequest(UserStatusUpdateRequest req)
            throws Exception {
        //校验请求参数
        if (req == null) {
            throw new BusinessException("请求对象不能为空");
        }
        if (StringUtils.isBlank(req.getMemberId())) {
            throw new BusinessException("memberId字段不能为空");
        }
    }
	
}
