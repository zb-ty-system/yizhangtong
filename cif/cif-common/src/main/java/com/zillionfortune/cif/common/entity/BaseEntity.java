package com.zillionfortune.cif.common.entity;

import java.util.Date;

/**
 * Created by zhengrunlong on 16/11/08.
 */
public class BaseEntity {

    protected Integer id;

    protected Date createTime;

    protected Date modifyTime;

    /**
     * 分页参数,起始序号
     */
    private Integer pageStart;

    /**
     * 分页参数,分页大小
     */
    private Integer pageSize;

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

    public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Integer getPageStart() {
        return pageStart;
    }

    public void setPageStart(Integer pageStart) {
        this.pageStart = pageStart;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
