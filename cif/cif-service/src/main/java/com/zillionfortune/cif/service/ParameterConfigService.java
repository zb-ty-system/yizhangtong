package com.zillionfortune.cif.service;

import com.zillionfortune.cif.common.exception.BusinessException;
import com.zillionfortune.cif.common.exception.PaycoreException;
import com.zillionfortune.cif.dal.entity.ParameterConfig;

/**
 * 系统参数配置管理服务层接口
 * 
 * @author wangzhaolin
 * 
 * @since 2016-08-10s
 *
 * @version 0.0.1
 */
public interface ParameterConfigService {

	/**
	 * 根据参数名称查询
	 * 
	 * @param parameterName
	 * @return
	 * @throws PaycoreException
	 */
	ParameterConfig queryByParameterName(String parameterName)
			throws BusinessException;
}
