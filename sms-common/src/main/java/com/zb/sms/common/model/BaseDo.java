package com.zb.sms.common.model;

import java.util.Date;

/**
 * 功能: 数据库实体类基类
 * 创建: liuchongguang - liuchongguang@zillionfortune.com
 * 日期: 2016/12/12 0012 14:06
 * 版本: V1.0
 */
public class BaseDo {

    /**
     * 自增主键
     */
    private Integer id;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后修改时间
     */
    private Date modifiedTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }
}
