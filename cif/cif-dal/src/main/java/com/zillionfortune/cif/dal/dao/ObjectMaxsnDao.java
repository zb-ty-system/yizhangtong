package com.zillionfortune.cif.dal.dao;

import com.zillionfortune.cif.dal.entity.ObjectMaxsn;
import com.zillionfortune.cif.support.mybatis.annotation.MyBatisRepository;

@MyBatisRepository
public interface ObjectMaxsnDao {
    int deleteByPrimaryKey(Long id);

    int insert(ObjectMaxsn record);

    int insertSelective(ObjectMaxsn record);

    ObjectMaxsn selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ObjectMaxsn record);

    int updateByPrimaryKey(ObjectMaxsn record);
    
    ObjectMaxsn selectByCriteria(ObjectMaxsn record);
}