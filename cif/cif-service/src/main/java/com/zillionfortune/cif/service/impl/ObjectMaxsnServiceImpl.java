package com.zillionfortune.cif.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zillionfortune.cif.dal.dao.ObjectMaxsnDao;
import com.zillionfortune.cif.dal.entity.ObjectMaxsn;
import com.zillionfortune.cif.service.ObjectMaxsnService;

/**
 * 
 * @author zhengrunlong
 *
 */
@Service
public class ObjectMaxsnServiceImpl implements ObjectMaxsnService {
	
	@Autowired
	ObjectMaxsnDao  objectMaxsnDao;
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		
		return objectMaxsnDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(ObjectMaxsn record) {
		
		return objectMaxsnDao.insert(record);
	}

	@Override
	public int insertSelective(ObjectMaxsn record) {
		
		return objectMaxsnDao.insertSelective(record);
	}

	@Override
	public ObjectMaxsn selectByPrimaryKey(Long id) {
		
		return objectMaxsnDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(ObjectMaxsn record) {
		
		return objectMaxsnDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(ObjectMaxsn record) {
		
		return objectMaxsnDao.updateByPrimaryKey(record);
	}

	@Override
	public ObjectMaxsn selectByCriteria(ObjectMaxsn record) {
		
		return objectMaxsnDao.selectByCriteria(record);
	}

}
