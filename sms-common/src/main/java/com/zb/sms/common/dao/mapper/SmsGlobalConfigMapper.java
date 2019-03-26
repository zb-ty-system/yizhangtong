package com.zb.sms.common.dao.mapper;

import com.zb.sms.common.model.SmsGlobalConfigDo;

import java.util.List;
import java.util.Map;

/**
 * 功能: 全局配置数据访问接口
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2016/12/14 0014 11:24
 * 版本: V1.0
 */
public interface SmsGlobalConfigMapper {

    /**
     * 查询单一结果
     *
     * @param globalConfigDo 查询参数
     * @return 数据持久对象
     */
    SmsGlobalConfigDo select(SmsGlobalConfigDo globalConfigDo);

    /**
     * 查询结果集
     *
     * @param globalConfigDo 查询参数
     * @return 数据持久对象结果集
     */
    List<SmsGlobalConfigDo> selectList(SmsGlobalConfigDo globalConfigDo);

    /**
     * 查询单一结果
     *
     * @param params 查询参数
     * @return 数据持久对象
     */
    SmsGlobalConfigDo selectByMap(Map params);

    /**
     * 查询结果集
     *
     * @param params 查询参数
     * @return 数据持久对象结果集
     */
    List<SmsGlobalConfigDo> selectListByMap(Map params);

    /**
     * 插入记录
     *
     * @param globalConfigDo 数据持久对象
     * @return 受影响行数
     */
    int insert(SmsGlobalConfigDo globalConfigDo);

    /**
     * 更新记录
     *
     * @param globalConfigDo 数据持久对象
     * @return 受影响行数
     */
    int update(SmsGlobalConfigDo globalConfigDo);

    /**
     * 根据ID删除记录
     *
     * @param id 记录ID
     * @return 受影响的行数
     */
    int delete(Integer id);
}
