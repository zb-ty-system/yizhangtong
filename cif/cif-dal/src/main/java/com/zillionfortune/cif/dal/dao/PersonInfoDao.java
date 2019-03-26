package com.zillionfortune.cif.dal.dao;

import java.util.List;
import java.util.Map;

import com.zillionfortune.cif.dal.entity.PersonInfo;
import com.zillionfortune.cif.support.mybatis.annotation.MyBatisRepository;

@MyBatisRepository
public interface PersonInfoDao {
	int deleteByPrimaryKey(Long id);

	int insert(PersonInfo record);

	int insertSelective(PersonInfo record);

	PersonInfo selectByPrimaryKey(Long id);

	List<PersonInfo> selectByCriteria(Map criteria);

	int updateByPrimaryKeySelective(PersonInfo record);

    int updateByPrimaryKey(PersonInfo record);
    
    /**
     * 根据CustomerId更新客户扩展信息
     * @param
     * @return
     */
    int updateByCustomerIdSelective(PersonInfo personInfo);

}