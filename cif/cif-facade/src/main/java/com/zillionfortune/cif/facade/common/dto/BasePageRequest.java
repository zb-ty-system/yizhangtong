package com.zillionfortune.cif.facade.common.dto;

/**
 * ClassName: BasePageRequest <br/>
 * Function: 请求对象基础类封装. <br/>
 * Date: 2016年11月8日 上午9:48:40 <br/>
 *
 * @author Administrators
 * @version 
 * @since JDK 1.7
 */
public class BasePageRequest extends BaseRequest {

	private static final long serialVersionUID = 1L;
	
	/** 默认每页条数 */
    public static final int DEFAULT_PAGE_SIZE = 10;
    
    /** 默认当前页 */
    public static final int DEFAULT_CURRENT_PAGE = 1;

	/**
     * 分页参数,起始序号
     */
	private int pageStart;
    
    /**
     * currentPage:当前页
     */
    private int currentPage;

    /**
     * 分页参数,分页大小
     */
    private int pageSize;

    public int getPageStart() {
    	pageStart = (getCurrentPage()-1) * getPageSize();
        return pageStart;
    }

    public void setPageStart(int pageStart) {
        this.pageStart = pageStart;
    }

    public int getPageSize() {
    	if (pageSize <= 0) {
			return DEFAULT_PAGE_SIZE;
		}
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

	public int getCurrentPage() {
		if (currentPage <= 0) {
			return DEFAULT_CURRENT_PAGE;
		}
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
    
}
