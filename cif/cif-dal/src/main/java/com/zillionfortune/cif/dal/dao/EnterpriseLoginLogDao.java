package com.zillionfortune.cif.dal.dao;

import com.zillionfortune.cif.dal.entity.EnterpriseLoginLog;
import com.zillionfortune.cif.support.mybatis.annotation.MyBatisRepository;

@MyBatisRepository
public interface EnterpriseLoginLogDao {
    int deleteByPrimaryKey(Long id);

    int insert(EnterpriseLoginLog record);

    int insertSelective(EnterpriseLoginLog record);

    EnterpriseLoginLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(EnterpriseLoginLog record);

    int updateByPrimaryKey(EnterpriseLoginLog record);
}