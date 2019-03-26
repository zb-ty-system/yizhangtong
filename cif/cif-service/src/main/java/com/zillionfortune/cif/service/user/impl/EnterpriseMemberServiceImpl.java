package com.zillionfortune.cif.service.user.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zillionfortune.cif.common.enums.EnterpriseGradeType;
import com.zillionfortune.cif.common.util.StringUtils;
import com.zillionfortune.cif.dal.dao.EnterpriseMemberDao;
import com.zillionfortune.cif.dal.entity.EnterpriseInfoMember;
import com.zillionfortune.cif.dal.entity.EnterpriseMember;
import com.zillionfortune.cif.service.user.EnterpriseMemberService;

@Service
public class EnterpriseMemberServiceImpl implements EnterpriseMemberService {
	
	private static Logger log = LoggerFactory.getLogger(EnterpriseMemberServiceImpl.class);

	@Autowired
	private EnterpriseMemberDao enterpriseMemberDao;

	@Override
	public List<EnterpriseMember> queryList(EnterpriseMember enterpriseMember) {
		return enterpriseMemberDao.selectByCriteria(enterpriseMember);
	}

	@Override
	public EnterpriseMember queryEnterpriseMember(
			EnterpriseMember enterpriseMember) {
		List<EnterpriseMember> list = queryList(enterpriseMember);
		if (list != null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public EnterpriseMember queryByMemberId(String memberId) {
		if (StringUtils.isEmpty(memberId)) {
			return null;
		}
		EnterpriseMember enterpriseMember = new EnterpriseMember();
		enterpriseMember.setMemberId(memberId);
		return queryEnterpriseMember(enterpriseMember);
	}

	@Override
	public int insertSelective(EnterpriseMember enterpriseMember) {
		
		return enterpriseMemberDao.insertSelective(enterpriseMember);
	}
	
	@Override
	public void updateGrade(String memberId,String grade, String val, int index) {
		// 如果grade为空 则需要先查
		if (StringUtils.isEmpty(grade)) {
			EnterpriseMember user = queryByMemberId(memberId);
			grade = user.getGrade();
		}
		
		StringBuffer gradeSb = new StringBuffer(grade);
		EnterpriseMember userUp = new EnterpriseMember();
		userUp.setMemberId(memberId);
		userUp.setGrade(gradeSb.replace(index - 1, index, val).toString());
		userUp.setModifyTime(new Date());
		// 如果是更新认证等级
		if (EnterpriseGradeType.AUTH.getCode() == index) {
			userUp.setRealNameTime(new Date());
		}
		enterpriseMemberDao.updateByMemberIdSelective(userUp);
		log.info(String.format("memberId(%s)更新等级数据：%s---->%s", memberId, grade,
				userUp.getGrade()));
	}

	@Override
	public String getGrade(String memberId, int index) {
		EnterpriseMember user = queryByMemberId(memberId);
		if (user == null) {
			return null;
		}
		return user.getGrade().substring(index - 1, index);
	}

	/**
	 * 根据MemberId选择性更新.
	 * @see com.zillionfortune.cif.service.user.EnterpriseMemberService#updateByMemberIdSelective(com.zillionfortune.cif.dal.entity.EnterpriseMember)
	 */
	@Override
	public int updateByMemberIdSelective(EnterpriseMember record) {
		return enterpriseMemberDao.updateByMemberIdSelective(record);
	}

	@Override
	public int getEnterpriseMemberCount(Map paramMap) {
		return enterpriseMemberDao.getEnterpriseMemberCount(paramMap);
	}
	
	/**
	 * @see com.zillionfortune.cif.service.user.EnterpriseMemberService#queryInfoByPage(java.util.Map)
	 */
	@Override
	public List<EnterpriseInfoMember> queryInfoByPage(Map paramMap) {
		return enterpriseMemberDao.queryInfoByPage(paramMap);
	}
	
	/**
	 * @see com.zillionfortune.cif.service.user.EnterpriseMemberService#queryInfoByPageCount(java.util.Map)
	 */
	@Override
	public long queryInfoByPageCount(Map paramMap) {
		return enterpriseMemberDao.queryInfoByPageCount(paramMap);
	}

}
