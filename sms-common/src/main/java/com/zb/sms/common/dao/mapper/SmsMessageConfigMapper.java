package com.zb.sms.common.dao.mapper;

import com.zb.sms.common.model.SmsMessageConfigDo;

import java.util.List;
import java.util.Map;

/**
 * 功能: 短信路由配置数据访问接口
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2016/12/14 0014 11:30
 * 版本: V1.0
 */
public interface SmsMessageConfigMapper {

    /**
     * 查询单一结果
     *
     * @param messageConfigDo 查询参数
     * @return 数据持久对象
     */
    SmsMessageConfigDo select(SmsMessageConfigDo messageConfigDo);

    /**
     * 查询结果集
     *
     * @param messageConfigDo 查询参数
     * @return 数据持久对象结果集
     */
    List<SmsMessageConfigDo> selectList(SmsMessageConfigDo messageConfigDo);

    /**
     * 查询单一结果
     *
     * @param params 查询参数
     * @return 数据持久对象
     */
    SmsMessageConfigDo selectByMap(Map params);

    /**
     * 查询结果集
     *
     * @param params 查询参数
     * @return 数据持久对象结果集
     */
    List<SmsMessageConfigDo> selectListByMap(Map params);

    /**
     * 插入记录
     *
     * @param messageConfigDo 数据持久对象
     * @return 受影响行数
     */
    int insert(SmsMessageConfigDo messageConfigDo);

    /**
     * 更新记录
     *
     * @param messageConfigDo 数据持久对象
     * @return 受影响行数
     */
    int update(SmsMessageConfigDo messageConfigDo);

    /**
     * 根据ID删除记录
     *
     * @param id 记录ID
     * @return 受影响的行数
     */
    int delete(Integer id);
}
