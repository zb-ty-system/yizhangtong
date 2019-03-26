package com.zillionfortune.cif.service.user.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zillionfortune.cif.common.util.StringUtils;
import com.zillionfortune.cif.dal.dao.PersonInfoDao;
import com.zillionfortune.cif.dal.entity.PersonInfo;
import com.zillionfortune.cif.service.user.PersonInfoService;

@Service
public class PersonInfoServiceImpl implements PersonInfoService {

	@Autowired
	PersonInfoDao personInfoDao;
	
	@Override
	public int updateByCustomerIdSelective(PersonInfo personInfo) {
		
		return personInfoDao.updateByCustomerIdSelective(personInfo);
	}
	
	@Override
	public PersonInfo queryByMemberId(String memberId) {
		if (StringUtils.isEmpty(memberId)) {
			return null;
		}
		Map<String, Object> criteria = new HashMap<String, Object>();
		criteria.put("memberId", memberId);
		List<PersonInfo> list = personInfoDao.selectByCriteria(criteria);
		if (list != null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public PersonInfo queryByCustomerId(String customerId) {
		if (StringUtils.isEmpty(customerId)) {
			return null;
		}
		Map<String, Object> criteria = new HashMap<String, Object>();
		criteria.put("customerId", customerId);
		List<PersonInfo> list = personInfoDao.selectByCriteria(criteria);
		if (list != null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public List<PersonInfo> queryByCriteria(Map<String, Object> criteria) {
		return  personInfoDao.selectByCriteria(criteria);
	}

}
