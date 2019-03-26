/*
 * Copyright (c) 2016, 资邦金服（上海）网络科技有限公司. All Rights Reserved.
 *
 *
 *
 */
package com.zillionfortune.cif.service.user.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zillionfortune.cif.common.constants.CommonConstants;
import com.zillionfortune.cif.common.enums.UserStatusCodeEnum;
import com.zillionfortune.cif.dal.dao.EnterpriseMemberDao;
import com.zillionfortune.cif.dal.dao.EnterpriseOperatorDao;
import com.zillionfortune.cif.dal.entity.EnterpriseMember;
import com.zillionfortune.cif.dal.entity.EnterpriseOperator;
import com.zillionfortune.cif.service.user.EnterpriseStatusService;

/**
 * ClassName: EnterpriseStatusServiceImpl <br/>
 * Function: 企业会员冻结/解冻/注销业务Service实现. <br/>
 * Date: 2016年12月2日 下午1:16:36 <br/>
 *
 * @author wangzinan_tech@zillionfortune.com
 * @version 
 * @since JDK 1.7
 */
@Service
public class EnterpriseStatusServiceImpl implements EnterpriseStatusService{
	@Autowired
	private EnterpriseMemberDao enterpriseMemberDao;
	
	@Autowired
	private EnterpriseOperatorDao enterpriseOperatorDao;
	
	/**
	 * 冻结企业以及企业下面的所有操作员.
	 * @see com.zillionfortune.cif.service.user.EnterpriseStatusService#frozenEnterpriseAndAllOperator(com.zillionfortune.cif.dal.entity.EnterpriseMember, com.zillionfortune.cif.dal.entity.EnterpriseOperator)
	 */
	@Override
	@Transactional
	public void frozenEnterpriseAndAllOperator(EnterpriseMember enterpriseMember) {	
		// 根据memberId查询，企业下面的所有操作员
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("memberId", enterpriseMember.getMemberId());
		List<EnterpriseOperator> listEnterpriseOperator = enterpriseOperatorDao.selectByCriteria(map);
		for (EnterpriseOperator list : listEnterpriseOperator) {
			// 操作员状态为正常时，冻结操作员
			if (list.getStatus() != null
					&& list.getStatus() == UserStatusCodeEnum.NORMAL.getCode()) {
				// 冻结操作员用参数设定
				EnterpriseOperator enterpriseOperator = new EnterpriseOperator();
				enterpriseOperator.setId(list.getId());
				// 更新操作员状态（正常→冻结），进行冻结操作
				enterpriseOperator.setStatus(UserStatusCodeEnum.FROZEN.getCode());
				enterpriseOperator.setModifyTime(new Date());
				enterpriseOperatorDao.updateByPrimaryKeySelective(enterpriseOperator);
			}
		}
		
		// 冻结企业会员
		enterpriseMemberDao.updateByMemberIdSelective(enterpriseMember);
	}

	/**
	 * 解冻企业以及企业下面的所有操作员.
	 * @see com.zillionfortune.cif.service.user.EnterpriseStatusService#unfreezeEnterpriseAndAllOperator(com.zillionfortune.cif.dal.entity.EnterpriseMember)
	 */
	@Override
	@Transactional
	public void unfreezeEnterpriseAndAllOperator(EnterpriseMember enterpriseMember) {
		// 会员状态为冻结时，解冻企业
		if (enterpriseMember.getStatus() == UserStatusCodeEnum.FROZEN.getCode()) {
			// 解冻企业用参数设定
			EnterpriseMember member = new EnterpriseMember();
			member.setMemberId(enterpriseMember.getMemberId());
			member.setStatus(UserStatusCodeEnum.NORMAL.getCode());
			member.setModifyTime(new Date());
			// 解冻企业会员
			enterpriseMemberDao.updateByMemberIdSelective(member);
		}
		
		// 根据memberId查询，企业下面的所有操作员
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("memberId", enterpriseMember.getMemberId());
		List<EnterpriseOperator> listEnterpriseOperator = enterpriseOperatorDao.selectByCriteria(map);
		for (EnterpriseOperator list : listEnterpriseOperator) {
			// 操作员状态为冻结时，解冻操作员
			if (list.getStatus() != null
					&& list.getStatus() == UserStatusCodeEnum.FROZEN.getCode()) {
				// 冻结操作员用参数设定
				EnterpriseOperator enterpriseOperator = new EnterpriseOperator();
				enterpriseOperator.setId(list.getId());
				// 更新操作员状态（冻结→正常），进行解冻操作
				enterpriseOperator.setStatus(UserStatusCodeEnum.NORMAL.getCode());
				enterpriseOperator.setModifyTime(new Date());
				enterpriseOperatorDao.updateByPrimaryKeySelective(enterpriseOperator);
			}
		}
	}
	
	/**
	 * 解冻企业会员以及企业下面单个操作员.
	 * @see com.zillionfortune.cif.service.user.EnterpriseStatusService#unfreezeEnterpriseAndOneOperator(com.zillionfortune.cif.dal.entity.EnterpriseMember, com.zillionfortune.cif.dal.entity.EnterpriseOperator)
	 */
	@Override
	@Transactional
	public void unfreezeEnterpriseAndOneOperator(EnterpriseMember enterpriseMember, EnterpriseOperator enterpriseOperator) {
		// 解冻企业会员
		enterpriseMemberDao.updateByMemberIdSelective(enterpriseMember);
		
		// 解冻单个操作员
		enterpriseOperatorDao.updateByPrimaryKeySelective(enterpriseOperator);
	}

	/**
	 * 注销企业以及企业下面的所有操作员..
	 * @see com.zillionfortune.cif.service.user.EnterpriseStatusService#cancelEnterpriseAndAllOperator(com.zillionfortune.cif.dal.entity.EnterpriseMember)
	 */
	@Override
	@Transactional
	public void cancelEnterpriseAndAllOperator(EnterpriseMember enterpriseMember) {
		// 根据memberId查询，企业下面的所有操作员
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("memberId", enterpriseMember.getMemberId());
		List<EnterpriseOperator> listEnterpriseOperator = enterpriseOperatorDao.selectByCriteria(map);
		for (EnterpriseOperator list : listEnterpriseOperator) {
			// 操作员状态为正常/冻结时，注销操作员
			if (list.getStatus() != null
				&& list.getStatus() != UserStatusCodeEnum.CANCEL.getCode()) {
				// 注销操作员用参数设定
				EnterpriseOperator enterpriseOperator = new EnterpriseOperator();
				enterpriseOperator.setId(list.getId());
				// 更新操作员状态（正常/冻结→注销），进行注销操作
				enterpriseOperator.setStatus(UserStatusCodeEnum.CANCEL.getCode());
				// 更改别名
				enterpriseOperator.setUserName(list.getUserName() + CommonConstants.SUFFIX_FOR_CANCEL);
				enterpriseOperator.setModifyTime(new Date());
				enterpriseOperatorDao.updateByPrimaryKeySelective(enterpriseOperator);
			}
		}
		
		// 注销企业会员
		enterpriseMemberDao.updateByMemberIdSelective(enterpriseMember);
	}

}
