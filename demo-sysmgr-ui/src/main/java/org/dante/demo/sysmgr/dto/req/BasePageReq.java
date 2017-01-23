package org.dante.demo.sysmgr.dto.req;

import java.io.Serializable;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.google.common.collect.Maps;

/**
 * JPA分页的页数是从0开始的
 * 
 * filter: key 必须和domain的字段名保持一直
 * 
 * @author dante
 *
 * @param <T>
 */
public class BasePageReq implements Serializable {

	private static final long serialVersionUID = -4983844912441446181L;

	private final static int DEFAULT_PAGENO = 1;
	private final static int DEFAULT_PAGESIZE = 20;
	public final static String ASC = "asc";
	public final static String DESC = "desc";

	private int pageNo = DEFAULT_PAGENO;
	private int pageSize = DEFAULT_PAGESIZE;
	private Map<String, Object> filter;
	/**
	 * 排序方向
	 */
	private String sortDir = ASC;
	/**
	 * 排序字段
	 */
	private String sortCol; 
	
	public BasePageReq() {
	}

	public BasePageReq(int pageNo, int pageSize) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getSortDir() {
		return sortDir;
	}

	public void setSortDir(String sortDir) {
		this.sortDir = sortDir;
	}

	public String getSortCol() {
		return sortCol;
	}

	public void setSortCol(String sortCol) {
		this.sortCol = sortCol;
	}

	public Map<String, Object> getFilter() {
		if(this.filter == null) {
			this.filter = Maps.newHashMap();
		}
		return filter;
	}
	
	public void addFilter(String key, Object value) {
		if(this.filter == null) {
			this.filter = Maps.newHashMap();
		}
		if(!StringUtils.isEmpty(key) && value != null) {
			this.filter.put(key, value);
		}
	}

	@Override
	public String toString() {
		return "BasePageReq [pageNo=" + pageNo + ", pageSize=" + pageSize + ", filter=" + filter + "]";
	}
	
	
}
