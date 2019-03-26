package com.zillionfortune.cif.service.user.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zillionfortune.cif.common.enums.EnterpriseAuditStatusEnum;
import com.zillionfortune.cif.common.enums.EnterpriseAuthGrade;
import com.zillionfortune.cif.common.enums.LegalPersonAuditStatusEnum;
import com.zillionfortune.cif.common.util.StringUtils;
import com.zillionfortune.cif.dal.dao.EnterpriseInfoDao;
import com.zillionfortune.cif.dal.dao.EnterpriseMemberDao;
import com.zillionfortune.cif.dal.dao.EnterpriseOperatorDao;
import com.zillionfortune.cif.dal.dao.EnterpriseQualificationDao;
import com.zillionfortune.cif.dal.entity.EnterpriseInfo;
import com.zillionfortune.cif.dal.entity.EnterpriseInfoMember;
import com.zillionfortune.cif.dal.entity.EnterpriseMember;
import com.zillionfortune.cif.dal.entity.EnterpriseOperator;
import com.zillionfortune.cif.dal.entity.EnterpriseQualification;
import com.zillionfortune.cif.service.user.EnterpriseInfoService;

@Service
public class EnterpriseInfoServiceImpl implements EnterpriseInfoService {
	@Autowired
	private EnterpriseInfoDao enterpriseInfoDao;
	
	@Autowired
	private EnterpriseMemberDao enterpriseMemberDao;
	
	@Autowired
	private EnterpriseOperatorDao enterpriseOperatorDao;
	
	@Autowired
	private EnterpriseQualificationDao enterpriseQualificationDao;

	@Override
	public EnterpriseInfo queryEnterpriseInfo(EnterpriseInfo enterpriseInfo) {
		return enterpriseInfoDao.selectByCriteria(enterpriseInfo);
	}

	@Override
	public EnterpriseInfo queryByCustomerId(String customerId) {
		if (StringUtils.isEmpty(customerId)) {
			return null;
		}
		EnterpriseInfo enterpriseInfo = new EnterpriseInfo();
		enterpriseInfo.setCustomerId(customerId);
		return queryEnterpriseInfo(enterpriseInfo);
	}

	@Override
	public EnterpriseInfo queryByMemberId(String memberId) {
		return enterpriseInfoDao.selectByMemberId(memberId);
	}
	
	@Override
	public int insertSelective(EnterpriseInfo enterpriseInfo) {
		
		return enterpriseInfoDao.insertSelective(enterpriseInfo);
	}

	@Override
	public int updateByCustomerIdSelective(EnterpriseInfo enterpriseInfo) {
		
		return enterpriseInfoDao.updateByCustomerIdSelective(enterpriseInfo);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void register(EnterpriseInfo enterpriseInfo,EnterpriseMember enterpriseMember,EnterpriseOperator enterpriseOperator) {
		
		enterpriseInfoDao.insertSelective(enterpriseInfo);//落地企业扩展信息
		
		enterpriseMemberDao.insertSelective(enterpriseMember);//落地会员信息
		
		enterpriseOperatorDao.insertSelective(enterpriseOperator);//落地操作员信息
		
		EnterpriseQualification qualification = new EnterpriseQualification();
		qualification.setCustomerId(enterpriseInfo.getCustomerId());
		enterpriseQualificationDao.insertSelective(qualification);// 初始化企业资质信息
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void register(EnterpriseMember enterpriseMember,EnterpriseOperator enterpriseOperator) {
		
		enterpriseMemberDao.insertSelective(enterpriseMember);//落地会员信息
		
		enterpriseOperatorDao.insertSelective(enterpriseOperator);//落地操作员信息
	}
	
	@Override
	public List<EnterpriseInfoMember> queryUserAuthInfoByPage(Map<String, Object> map) {
		return enterpriseInfoDao.queryUserAuthInfoByPage(map);
	}
	
	@Override
	public long queryUserAuthInfoByPageCount(Map<String, Object> map) {
		return enterpriseInfoDao.queryUserAuthInfoByPageCount(map);
	}
	
	@Override
	@Transactional
	public String auditAuth(EnterpriseInfo enterpriseInfo, String authGrade) {
		// 更新企业审核状态和法人审核状态
		enterpriseInfoDao.updateByCustomerIdSelective(enterpriseInfo);
		
		EnterpriseInfo info = queryByCustomerId(enterpriseInfo.getCustomerId());
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("customerId", enterpriseInfo.getCustomerId());
		
		// 如果企业审核状态和法人审核状态都为通过  则将客户对应的member全部更改认证等级为 已认证
		if (info.getEnterpriseAuditStatus() != null &&
				info.getEnterpriseAuditStatus() == EnterpriseAuditStatusEnum.CHECK_PASS.code()
				&& info.getLegalPersonAuditStatus() != null &&
				LegalPersonAuditStatusEnum.CHECK_PASS.code() == info.getLegalPersonAuditStatus()) {
			paramMap.put("authGrade", EnterpriseAuthGrade.AUTH_SUCCESS.getCode());
			enterpriseMemberDao.updateAuthGradeByCustomerId(paramMap);
			authGrade = EnterpriseAuthGrade.AUTH_SUCCESS.getCode();
		} else if ((info.getEnterpriseAuditStatus() != null && 
				info.getEnterpriseAuditStatus() == EnterpriseAuditStatusEnum.CHECK_NOT_PASS.code()) ||
				(info.getLegalPersonAuditStatus() != null && 
				LegalPersonAuditStatusEnum.CHECK_NOT_PASS.code() == info.getLegalPersonAuditStatus())) { 
			// 如果企业审核状态和法人审核状态有一个为不通过  则将客户对应的member全部更改认证等级为 认证失败
			paramMap.put("authGrade", EnterpriseAuthGrade.AUTH_FAIL.getCode());
			enterpriseMemberDao.updateAuthGradeByCustomerId(paramMap);
			authGrade = EnterpriseAuthGrade.AUTH_FAIL.getCode();
		}  else if (info.getEnterpriseAuditStatus() != null && 
				info.getEnterpriseAuditStatus() == EnterpriseAuditStatusEnum.CHECK_WAIT.code() &&
				info.getLegalPersonAuditStatus() != null &&
				LegalPersonAuditStatusEnum.CHECK_WAIT.code() == info.getLegalPersonAuditStatus()) { 
			// 如果企业审核状态和法人审核状态 都为待审核  则将客户对应的member全部更改认证等级为 认证中
			paramMap.put("authGrade", EnterpriseAuthGrade.AUTH_PROCESS.getCode());
			enterpriseMemberDao.updateAuthGradeByCustomerId(paramMap);
			authGrade = EnterpriseAuthGrade.AUTH_PROCESS.getCode();
		}
		
		return authGrade;
	}
	
	@Override
	public List<EnterpriseInfo> selectListByCriteria(Map map) {
		return enterpriseInfoDao.selectListByCriteria(map);
	}
}
