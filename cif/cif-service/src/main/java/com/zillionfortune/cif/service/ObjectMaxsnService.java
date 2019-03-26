package com.zillionfortune.cif.service;

import com.zillionfortune.cif.dal.entity.ObjectMaxsn;

public interface ObjectMaxsnService {
	
	 int deleteByPrimaryKey(Long id);

	 int insert(ObjectMaxsn record);

	 int insertSelective(ObjectMaxsn record);

	 ObjectMaxsn selectByPrimaryKey(Long id);

	 int updateByPrimaryKeySelective(ObjectMaxsn record);

	 int updateByPrimaryKey(ObjectMaxsn record);
	    
	 ObjectMaxsn selectByCriteria(ObjectMaxsn record);
}
