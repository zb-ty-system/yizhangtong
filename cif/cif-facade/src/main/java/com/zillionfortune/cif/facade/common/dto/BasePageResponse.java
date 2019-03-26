package com.zillionfortune.cif.facade.common.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: BasePageResponse <br/>
 * Function: 相应对象基础类封装. <br/>
 * Date: 2016年11月8日 上午9:48:40 <br/>
 *
 * @author Administrators
 * @version 
 * @since JDK 1.7
 */
public class BasePageResponse extends BaseResponse {

	private static final long serialVersionUID = 1L;
	
    /**
     * total:	总条数
     */
    private long total;
    /**
     * totalPage:	总页数
     */
    private long totalPage;
    /**
     * pageSize:	一页多少条
     */
    private int pageSize;
    /**
     * currentPage:	当前页
     */
    private int currentPage;

    /**
     * pageList:返回结果集
     */
	private List data = new ArrayList();

    public BasePageResponse() {

    }

    public BasePageResponse(String respCode) {
        super(respCode);
    }

    public BasePageResponse(String respCode, String resultCode, String resultMsg) {
		super(respCode, resultCode, resultMsg);
	}

	public BasePageResponse(String respCode, String resultMsg) {
		super(respCode, resultMsg);
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public long getTotalPage() {
		if (pageSize > 0) {
			totalPage = total%pageSize > 0 ? total/pageSize + 1 :total/pageSize;
		}
		return totalPage;
	}

	public void setTotalPage(long totalPage) {
		this.totalPage = totalPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public List getData() {
		return data;
	}

	public void setData(List data) {
		this.data = data;
	}
    
}
