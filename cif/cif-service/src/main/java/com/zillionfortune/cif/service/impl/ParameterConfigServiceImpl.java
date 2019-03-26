package com.zillionfortune.cif.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.zillionfortune.cif.common.constants.RedisKey;
import com.zillionfortune.cif.common.exception.BusinessException;
import com.zillionfortune.cif.dal.dao.ParameterConfigDao;
import com.zillionfortune.cif.dal.entity.ParameterConfig;
import com.zillionfortune.cif.service.ParameterConfigService;

/**
 * 系统参数配置管理服务层实现
 * 
 * @author wangzhaolin
 *
 * @since 2016-08-10
 * 
 * @version 0.0.1
 */
@Service
public class ParameterConfigServiceImpl implements
		ParameterConfigService {

	/**
	 * 日志对象
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ParameterConfigServiceImpl.class);

	/** 系统参数配置数据访问层接口 */
	@Autowired
	private ParameterConfigDao parameterConfigDao;
	@Autowired
	protected RedisTemplate<String, String> redisTemplate;

	@Override
	public ParameterConfig queryByParameterName(String parameterName)
			throws BusinessException {
		if (StringUtils.isEmpty(parameterName)) {
			throw new BusinessException("业务参数名称为空");
		}
		ParameterConfig systemParameterConfig = null;
		String parameterValue = (String) redisTemplate.opsForHash().get(
				RedisKey.SYSTEM_PARAMETER_CONFIG, parameterName);
		if (StringUtils.isNotBlank(parameterValue)) {
			systemParameterConfig = new ParameterConfig();
			systemParameterConfig.setParameterName(parameterName);
			systemParameterConfig.setParameterValue(parameterValue);
			return systemParameterConfig;
		}
		Map<String, String> criteria = new HashMap<String, String>();
		criteria.put("parameterName", parameterName);
		systemParameterConfig = parameterConfigDao.selectByCriteria(criteria);
		if (systemParameterConfig == null) {
			LOGGER.error("业务参数名称为" + parameterName + "的参数配置不存在");
			throw new BusinessException("业务参数名称为" + parameterName + "的参数配置不存在");
		}
		try {
			redisTemplate.opsForHash().put(RedisKey.SYSTEM_PARAMETER_CONFIG,
					parameterName, systemParameterConfig.getParameterValue());
		} catch (Exception e) {
			LOGGER.warn("将key：" + parameterName + "value:" + parameterValue
					+ "保存到redis异常", e);
		}

		return systemParameterConfig;
	}

}
