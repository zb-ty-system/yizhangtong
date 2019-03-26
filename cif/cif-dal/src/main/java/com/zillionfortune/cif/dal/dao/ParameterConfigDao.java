package com.zillionfortune.cif.dal.dao;

import java.util.Map;

import com.zillionfortune.cif.dal.entity.ParameterConfig;
import com.zillionfortune.cif.support.mybatis.annotation.MyBatisRepository;

@MyBatisRepository
public interface ParameterConfigDao {
	int deleteByPrimaryKey(Long id);

	int insert(ParameterConfig record);

	int insertSelective(ParameterConfig record);

	ParameterConfig selectByPrimaryKey(Long id);

	ParameterConfig selectByCriteria(Map criteria);

	int updateByPrimaryKeySelective(ParameterConfig record);

	int updateByPrimaryKey(ParameterConfig record);
}