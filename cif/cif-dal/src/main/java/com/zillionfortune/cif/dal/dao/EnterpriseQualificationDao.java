package com.zillionfortune.cif.dal.dao;

import com.zillionfortune.cif.dal.entity.EnterpriseQualification;
import com.zillionfortune.cif.support.mybatis.annotation.MyBatisRepository;

@MyBatisRepository
public interface EnterpriseQualificationDao {
    int deleteByPrimaryKey(Long id);

    int insert(EnterpriseQualification record);

    int insertSelective(EnterpriseQualification record);

    EnterpriseQualification selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(EnterpriseQualification record);

    int updateByPrimaryKey(EnterpriseQualification record);
    
    /**
     * updateByCustomerIdKeySelective:根据customerID更新. <br/>
     *
     * @param record
     * @return
     */
    int updateByCustomerIdKeySelective(EnterpriseQualification record);
    
    EnterpriseQualification selectByCustormerId(String customerId);
}