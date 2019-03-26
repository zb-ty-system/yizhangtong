package com.zillionfortune.cif.biz.user.check;

import org.springframework.stereotype.Component;

import com.zillionfortune.cif.common.enums.EnterpriseAuthGrade;
import com.zillionfortune.cif.common.enums.EnterpriseGradeType;
import com.zillionfortune.cif.common.exception.BusinessException;
import com.zillionfortune.cif.common.util.StringUtils;
import com.zillionfortune.cif.facade.user.dto.UserGradeServiceRequest;

/**
 * ClassName: UserGradeServiceParameterChecker <br/>
 * Function: 企业会员等级服务参数校验. <br/>
 * Date: 2016年11月23日 上午9:48:05 <br/>
 *
 * @author pengting
 * @version 
 * @since JDK 1.7
 */
@Component
public class EntUserGradeServiceParameterChecker {
	
	/**
	 * checkUpdateGradeRequest:会员等级更新参数校验. <br/>
	 *
	 * @param request
	 * @throws Exception
	 */
	public void checkUpdateGradeRequest(UserGradeServiceRequest request) throws Exception {
		if (null == request) {
			throw new BusinessException("请求对象不能为空");
		}
		if (StringUtils.isEmpty(request.getMemberId())
				|| StringUtils.isEmpty(request.getGrade())
				|| StringUtils.isEmpty(request.getGradeType())) {
			throw new BusinessException("memberId,grade,gradeType皆不能为空");
		}
		if (!StringUtils.isNumber(request.getGradeType())) {
			throw new BusinessException("gradeType 必须为整数型");
		}
		if (null == EnterpriseGradeType.getEnum(Integer.valueOf(request.getGradeType()))) {
			throw new BusinessException("gradeType 字段的参数值不在约定的范围");
		}
		if (request.getGrade().length() != 1) {
			throw new BusinessException("grade 字符长度超出");
		}
		if (request.getGradeType().equals(String.valueOf(EnterpriseGradeType.AUTH.getCode())) && 
				null == EnterpriseAuthGrade.getEnum(request.getGrade())) {
			throw new BusinessException("grade 字段的参数值不在约定的范围");
		}
	}
	
	/**
	 * checkQueryGradeRequest:会员等级参数校验. <br/>
	 *
	 * @param request
	 * @throws Exception
	 */
	public void checkQueryGradeRequest(UserGradeServiceRequest request) throws Exception {
		if (null == request) {
			throw new BusinessException("请求对象不能为空");
		}
		if (StringUtils.isEmpty(request.getMemberId())
				|| StringUtils.isEmpty(request.getGradeType())) {
			throw new BusinessException("memberId,gradeType皆不能为空");
		}
		if (!StringUtils.isNumber(request.getGradeType())) {
			throw new BusinessException("gradeType 必须为整数型");
		}
		if (null == EnterpriseGradeType.getEnum(Integer.valueOf(request.getGradeType()))) {
			throw new BusinessException("gradeType 字段的参数值不在约定的范围");
		}
	}

}
