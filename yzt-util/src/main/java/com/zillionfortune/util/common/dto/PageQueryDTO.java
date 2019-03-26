package com.zillionfortune.util.common.dto;

import java.util.Collections;
import java.util.List;

/**
 * Created by wangwanbin on 2017/2/23.
 */
public class PageQueryDTO<T> extends BaseDTO {

    private Integer pageNo = 1;                            // 页码(当前页)

    private Integer pageSize = 10;        // 每页的记录数量

    private Integer totalPage = 0;                        // 总页数

    private Integer totalCount = 0;                        // 总记录数

    private List<T> subResponseList = Collections.emptyList();//数据集合


    /**
     * 获得当前页的页号,序号从1开始,默认为1.
     */
    public Integer getPageNo() {
        return pageNo;
    }

    /**
     * 设置当前页的页号,序号从1开始,低于1时自动调整为1.
     */
    public void setPageNo(Integer pageNo) {
        this.pageNo = (pageNo < 1) ? 1 : pageNo;
    }

    /**
     * 获得每页的记录数量,默认为DEFAULT_PAGESIZE.
     */
    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * 设置每页的记录数量,低于1时自动调整为DEFAULT_PAGESIZE.
     */
    public void setPageSize(Integer pageSize) {
        this.pageSize = (pageSize < 1) ? 10 : pageSize;
    }

    /**
     * 取得总记录数, 默认值为0.
     */
    public Integer getTotalCount() {
        return totalCount;
    }

    /**
     * 设置总记录数, 默认值为0.
     */
    public void setTotalCount(Integer totalCount) {
        this.totalCount = (totalCount < 0) ? 0 : totalCount;
    }

    /**
     * 总页数.
     */
    public Integer getTotalPage() {
        totalPage = totalCount / pageSize;
        if (totalCount % pageSize > 0) {
            totalPage++;
        }
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getSubResponseList() {
        return subResponseList;
    }

    public void setSubResponseList(List<T> subResponseList) {
        this.subResponseList = subResponseList;
    }

    @Override
    public String toString() {
        return getClass().getName() + " [pageNo=" + getPageNo() + ",pageSize=" + getPageSize() + ",totalPage=" + getTotalPage() + ",totalCount=" + getTotalCount() + ",resultSize=" + getSubResponseList().size() + "]";
    }


}
