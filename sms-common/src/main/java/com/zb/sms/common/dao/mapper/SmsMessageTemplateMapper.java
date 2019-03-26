package com.zb.sms.common.dao.mapper;

import com.zb.sms.common.model.SmsMessageTemplateDo;

import java.util.List;
import java.util.Map;

/**
 * 功能: 短信模板数据访问接口
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2016/12/14 0014 11:33
 * 版本: V1.0
 */
public interface SmsMessageTemplateMapper {

    /**
     * 查询单一结果
     *
     * @param messageTemplateDo 查询参数
     * @return 数据持久对象
     */
    SmsMessageTemplateDo select(SmsMessageTemplateDo messageTemplateDo);

    /**
     * 查询结果集
     *
     * @param messageTemplateDo 查询参数
     * @return 数据持久对象结果集
     */
    List<SmsMessageTemplateDo> selectList(SmsMessageTemplateDo messageTemplateDo);

    /**
     * 查询单一结果
     *
     * @param params 查询参数
     * @return 数据持久对象
     */
    SmsMessageTemplateDo selectByMap(Map params);

    /**
     * 查询结果集
     *
     * @param params 查询参数
     * @return 数据持久对象结果集
     */
    List<SmsMessageTemplateDo> selectListByMap(Map params);

    /**
     * 插入记录
     *
     * @param messageTemplateDo 数据持久对象
     * @return 受影响行数
     */
    int insert(SmsMessageTemplateDo messageTemplateDo);

    /**
     * 更新记录
     *
     * @param messageTemplateDo 数据持久对象
     * @return 受影响行数
     */
    int update(SmsMessageTemplateDo messageTemplateDo);

    /**
     * 根据ID删除记录
     *
     * @param id 记录ID
     * @return 受影响的行数
     */
    int delete(Integer id);
}
