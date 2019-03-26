package com.zillionfortune.cif.dal.dao;

import com.zillionfortune.cif.dal.entity.PersonLoginLog;
import com.zillionfortune.cif.support.mybatis.annotation.MyBatisRepository;

@MyBatisRepository
public interface PersonLoginLogDao {
    int deleteByPrimaryKey(Long id);

    int insert(PersonLoginLog record);

    int insertSelective(PersonLoginLog record);

    PersonLoginLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PersonLoginLog record);

    int updateByPrimaryKey(PersonLoginLog record);
}