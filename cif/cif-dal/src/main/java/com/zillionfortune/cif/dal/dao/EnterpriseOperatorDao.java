package com.zillionfortune.cif.dal.dao;

import java.util.List;
import java.util.Map;

import com.zillionfortune.cif.dal.entity.EnterpriseOperator;
import com.zillionfortune.cif.support.mybatis.annotation.MyBatisRepository;

@MyBatisRepository
public interface EnterpriseOperatorDao {
	int deleteByPrimaryKey(Long id);

	int insert(EnterpriseOperator record);

	int insertSelective(EnterpriseOperator record);

	EnterpriseOperator selectByPrimaryKey(Long id);

	List<EnterpriseOperator> selectByCriteria(Map Criteria);

	int updateByPrimaryKeySelective(EnterpriseOperator record);

	int updateByPrimaryKey(EnterpriseOperator record);
}