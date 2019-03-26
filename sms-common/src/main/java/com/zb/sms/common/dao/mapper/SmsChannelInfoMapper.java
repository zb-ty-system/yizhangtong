package com.zb.sms.common.dao.mapper;

import com.zb.sms.common.model.SmsChannelInfoDo;

import java.util.List;
import java.util.Map;

/**
 * 功能: 外部短信渠道数据访问接口
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2016/12/14 0014 11:21
 * 版本: V1.0
 */
public interface SmsChannelInfoMapper {

    /**
     * 查询单一结果
     *
     * @param channelInfoDo 查询参数
     * @return 数据持久对象
     */
    SmsChannelInfoDo select(SmsChannelInfoDo channelInfoDo);

    /**
     * 查询结果集
     *
     * @param channelInfoDo 查询参数
     * @return 数据持久对象结果集
     */
    List<SmsChannelInfoDo> selectList(SmsChannelInfoDo channelInfoDo);

    /**
     * 查询单一结果
     *
     * @param params 查询参数
     * @return 数据持久对象
     */
    SmsChannelInfoDo selectByMap(Map params);

    /**
     * 查询结果集
     *
     * @param params 查询参数
     * @return 数据持久对象结果集
     */
    List<SmsChannelInfoDo> selectListByMap(Map params);

    /**
     * 插入记录
     *
     * @param channelInfoDo 数据持久对象
     * @return 受影响行数
     */
    int insert(SmsChannelInfoDo channelInfoDo);

    /**
     * 更新记录
     *
     * @param channelInfoDo 数据持久对象
     * @return 受影响行数
     */
    int update(SmsChannelInfoDo channelInfoDo);

    /**
     * 根据ID删除记录
     *
     * @param id 记录ID
     * @return 受影响的行数
     */
    int delete(Integer id);
}
