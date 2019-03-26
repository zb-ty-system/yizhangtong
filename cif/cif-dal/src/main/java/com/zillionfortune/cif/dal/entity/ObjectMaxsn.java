package com.zillionfortune.cif.dal.entity;

import java.util.Date;

public class ObjectMaxsn {
    private Long id;

    private String tableName;

    private String columnName;

    private String maxSerialNo;

    private String noFormat;

    private Date createTime;

    private String createBy;

    private Date modifyTime;

    private String modifyBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName == null ? null : tableName.trim();
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName == null ? null : columnName.trim();
    }

    public String getMaxSerialNo() {
        return maxSerialNo;
    }

    public void setMaxSerialNo(String maxSerialNo) {
        this.maxSerialNo = maxSerialNo == null ? null : maxSerialNo.trim();
    }

    public String getNoFormat() {
        return noFormat;
    }

    public void setNoFormat(String noFormat) {
        this.noFormat = noFormat == null ? null : noFormat.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy == null ? null : modifyBy.trim();
    }
}