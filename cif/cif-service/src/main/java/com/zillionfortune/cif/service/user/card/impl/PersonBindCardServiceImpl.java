/*
 * Copyright (c) 2016, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 *
 *
 */
package com.zillionfortune.cif.service.user.card.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zillionfortune.cif.dal.dao.PersonBindCardDao;
import com.zillionfortune.cif.dal.dao.PersonInfoDao;
import com.zillionfortune.cif.dal.dao.PersonMemberDao;
import com.zillionfortune.cif.dal.entity.PersonBindCard;
import com.zillionfortune.cif.dal.entity.PersonInfo;
import com.zillionfortune.cif.dal.entity.PersonMember;
import com.zillionfortune.cif.service.user.card.PersonBindCardService;

/**
 * ClassName: PersonBindCardServiceImpl <br/>
 * Function: 个人会员绑卡Service实现. <br/>
 * Date: 2016年12月13日 上午10:55:35 <br/>
 *
 * @author pengting
 * @version 
 * @since JDK 1.7
 */
@Service
public class PersonBindCardServiceImpl implements PersonBindCardService {
	
	@Autowired
	private PersonBindCardDao personBindCardDao;
	
	@Autowired
	private PersonMemberDao personMemberDao;

	@Autowired
	private PersonInfoDao personInfoDao;
	
	/**
	 * @see com.zillionfortune.cif.service.user.card.PersonBindCardService#insertSelective(com.zillionfortune.cif.dal.entity.PersonBindCard)
	 */
	@Override
	public int insertSelective(PersonBindCard personBindCard) {
		return personBindCardDao.insertSelective(personBindCard);
	}

	@Override
	public List<PersonBindCard> queryList(PersonBindCard personBindCard) {
		return personBindCardDao.selectBySelective(personBindCard);
	}

	@Override
	public PersonBindCard queryPersonBindCard(PersonBindCard personBindCard) {
		if (personBindCard == null) {
			return null;
		}
		List<PersonBindCard> rsList = queryList(personBindCard);
		if (rsList == null || rsList.isEmpty()) {
			return null ;
		}
		return rsList.get(0);
	}
	
	@Override
	public int saveOrUpdateBySelective(PersonBindCard personBindCard) {
		if (personBindCard.getId() != null) {
			return personBindCardDao.updateByPrimaryKeySelective(personBindCard);
		} else {
			return personBindCardDao.insertSelective(personBindCard);
		}
	}

	@Override
	@Transactional
	public void authBindCardByMember(PersonMember member, PersonBindCard bindCard, PersonInfo personInfo) {
		if (member != null) {
			// 更改认证等级
			personMemberDao.updateByMemberIdSelective(member);
		}
		if (personInfo != null) {
			// 添加用户信息
			personInfoDao.insertSelective(personInfo);
		}
		// 更新绑定信息
		saveOrUpdateBySelective(bindCard);
	}
}
