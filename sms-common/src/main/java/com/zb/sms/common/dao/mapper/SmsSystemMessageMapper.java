package com.zb.sms.common.dao.mapper;

/**
 * 功能: 内部接入系统信息数据访问接口
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2016/12/14 0014 11:35
 * 版本: V1.0
 */
public interface SmsSystemMessageMapper {

    /**
     * 查询单一结果
     *
     * @param systemMessageDo 查询参数
     * @return 数据持久对象
     */
    //SmsSystemMessageDo select(SmsSystemMessageDo systemMessageDo);

    /**
     * 查询结果集
     *
     * @param systemMessageDo 查询参数
     * @return 数据持久对象结果集
     */
    //List<SmsSystemMessageDo> selectList(SmsSystemMessageDo systemMessageDo);

    /**
     * 查询单一结果
     *
     * @param params 查询参数
     * @return 数据持久对象
     */
    //SmsSystemMessageDo selectByMap(Map params);

    /**
     * 查询结果集
     *
     * @param params 查询参数
     * @return 数据持久对象结果集
     */
    //List<SmsSystemMessageDo> selectListByMap(Map params);

    /**
     * 插入记录
     *
     * @param systemMessageDo 数据持久对象
     * @return 受影响行数
     */
    //int insert(SmsSystemMessageDo systemMessageDo);

    /**
     * 更新记录
     *
     * @param systemMessageDo 数据持久对象
     * @return 受影响行数
     */
    //int update(SmsSystemMessageDo systemMessageDo);

    /**
     * 根据ID删除记录
     *
     * @param id 记录ID
     * @return 受影响的行数
     */
    //int delete(Integer id);
}
