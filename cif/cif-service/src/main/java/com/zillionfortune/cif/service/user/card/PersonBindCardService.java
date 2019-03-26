/*
 * Copyright (c) 2016, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 *
 *
 */
package com.zillionfortune.cif.service.user.card;

import java.util.List;

import com.zillionfortune.cif.dal.entity.PersonBindCard;
import com.zillionfortune.cif.dal.entity.PersonInfo;
import com.zillionfortune.cif.dal.entity.PersonMember;

/**
 * ClassName: PersonBindCardService <br/>
 * Function: 个人会员绑卡Service. <br/>
 * Date: 2016年12月13日 上午10:52:57 <br/>
 *
 * @author pengting
 * @version 
 * @since JDK 1.7
 */
public interface PersonBindCardService {

	/**
	 * 插入会员绑卡信息
	 * @param
	 * @return
	 */
	public int insertSelective(PersonBindCard personBindCard);
	
	/**
	 * queryList:查询列表. <br/>
	 *
	 * @param personBindCard
	 * @return
	 */
	public List<PersonBindCard> queryList(PersonBindCard personBindCard);
	
	/**
	 * queryPersonBindCard: 查询单个对象. <br/>
	 *
	 * @param personBindCard
	 * @return
	 */
	public PersonBindCard queryPersonBindCard(PersonBindCard personBindCard);
	
	/**
	 * saveOrUpdateBySelective:新增或者更新. <br/>
	 *
	 * @param personBindCard
	 * @return
	 */
	public int saveOrUpdateBySelective(PersonBindCard personBindCard);
	
	/**
	 * authBindCardByMember:绑卡认证、落地客户信息. <br/>
	 *
	 * @param member PersonMember
	 * @param bindCard PersonBindCard
	 * @param personInfo PersonInfo
	 */
	public void authBindCardByMember(PersonMember member, PersonBindCard bindCard, PersonInfo personInfo);
}
