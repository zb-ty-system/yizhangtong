package com.zillionfortune.cif.service.user.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zillionfortune.cif.common.enums.PersonGradeType;
import com.zillionfortune.cif.common.util.StringUtils;
import com.zillionfortune.cif.dal.dao.PersonMemberDao;
import com.zillionfortune.cif.dal.entity.PersonMember;
import com.zillionfortune.cif.service.user.PersonMemberService;

@Service
public class PersonMemberServiceImpl implements PersonMemberService {

	private static Logger log = LoggerFactory
			.getLogger(PersonMemberServiceImpl.class);

	@Autowired
	private PersonMemberDao personMemberDao;

	@Override
	public PersonMember queryByMemberId(String memberId) {
		if (StringUtils.isEmpty(memberId)) {
			return null;
		}
		PersonMember personMember = new PersonMember();
		personMember.setMemberId(memberId);
		return queryPersonMember(personMember);
	}

	@Override
	public List<PersonMember> queryList(PersonMember personMember) {
		return personMemberDao.selectByCriteria(personMember);
	}

	@Override
	public PersonMember queryPersonMember(PersonMember personMember) {
		List<PersonMember> list = queryList(personMember);
		if (null != list && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	
	/**
	 * @see com.zillionfortune.cif.service.user.PersonMemberService#updateGrade(java.lang.String, java.lang.String, java.lang.String, int)
	 */
	@Override
	public void updateGrade(String memberId, String grade, String val, int index) {
		// 如果grade为空 则需要先查
		if (StringUtils.isEmpty(grade)) {
			PersonMember user = queryByMemberId(memberId);
			grade = user.getGrade();
		}
		
		StringBuffer gradeSb = new StringBuffer(grade);

		PersonMember userUp = new PersonMember();
		userUp.setMemberId(memberId);
		userUp.setGrade(gradeSb.replace(index - 1, index, val).toString());
		userUp.setModifyTime(new Date());
		// 如果是更新认证等级
		if (PersonGradeType.AUTH.getCode() == index) {
			userUp.setRealNameTime(new Date());
		}
		personMemberDao.updateByMemberIdSelective(userUp);
		log.info(String.format("memberId(%s)更新等级数据：%s---->%s", memberId, grade,
				userUp.getGrade()));
	}

	/**
	 * @see com.zillionfortune.cif.service.user.PersonMemberService#getGrade(java.lang.String,
	 *      int)
	 */
	@Override
	public String getGrade(String memberId, int index) {
		PersonMember user = queryByMemberId(memberId);
		if (user == null) {
			return null;
		}
		return user.getGrade().substring(index - 1, index);
	}

	@Override
	public int getPersonMemberCount(Map paramMap) {

		return personMemberDao.getPersonMemberCount(paramMap);
	}

	@Override
	public int insertSelective(PersonMember personMember) {

		return personMemberDao.insertSelective(personMember);
	}

	@Override
	public int updateByMemberIdSelective(PersonMember personMember) {

		return personMemberDao.updateByMemberIdSelective(personMember);
	}

	@Override
	public PersonMember selectByMemberId(String memberId) {
		if (StringUtils.isEmpty(memberId)) {
			return null;
		}
		return personMemberDao.selectByMemberId(memberId);

	}
}