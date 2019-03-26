package com.zb.sms.common.dao.mapper;

import com.zb.sms.common.model.SmsSystemInfoDo;

import java.util.List;
import java.util.Map;

/**
 * 功能: 内部接入系统数据访问接口
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2016/12/14 0014 10:16
 * 版本: V1.0
 */
public interface SmsSystemInfoMapper {

    /**
     * 查询单一结果
     *
     * @param systemInfoDo 查询参数
     * @return 数据持久对象
     */
    SmsSystemInfoDo select(SmsSystemInfoDo systemInfoDo);

    /**
     * 查询结果集
     *
     * @param systemInfoDo 查询参数
     * @return 数据持久对象结果集
     */
    List<SmsSystemInfoDo> selectList(SmsSystemInfoDo systemInfoDo);

    /**
     * 查询单一结果
     *
     * @param params 查询参数
     * @return 数据持久对象
     */
    SmsSystemInfoDo selectByMap(Map params);

    /**
     * 查询结果集
     *
     * @param params 查询参数
     * @return 数据持久对象结果集
     */
    List<SmsSystemInfoDo> selectListByMap(Map params);

    /**
     * 插入记录
     *
     * @param systemInfoDo 数据持久对象
     * @return 受影响行数
     */
    int insert(SmsSystemInfoDo systemInfoDo);

    /**
     * 更新记录
     *
     * @param systemInfoDo 数据持久对象
     * @return 受影响行数
     */
    int update(SmsSystemInfoDo systemInfoDo);

    /**
     * 根据ID删除记录
     *
     * @param id 记录ID
     * @return 受影响的行数
     */
    int delete(Integer id);
}
