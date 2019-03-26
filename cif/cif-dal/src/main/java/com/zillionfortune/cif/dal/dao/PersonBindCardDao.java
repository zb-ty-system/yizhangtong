package com.zillionfortune.cif.dal.dao;

import java.util.List;

import com.zillionfortune.cif.dal.entity.PersonBindCard;
import com.zillionfortune.cif.support.mybatis.annotation.MyBatisRepository;

@MyBatisRepository
public interface PersonBindCardDao {
    int deleteByPrimaryKey(Long id);

    int insert(PersonBindCard record);

    int insertSelective(PersonBindCard record);
    
    List<PersonBindCard> selectBySelective(PersonBindCard record);

    PersonBindCard selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PersonBindCard record);

    int updateByPrimaryKey(PersonBindCard record);
}