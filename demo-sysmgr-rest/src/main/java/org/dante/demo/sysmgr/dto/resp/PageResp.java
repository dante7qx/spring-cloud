package org.dante.demo.sysmgr.dto.resp;

import java.io.Serializable;
import java.util.List;

public class PageResp<T> implements Serializable {

	private static final long serialVersionUID = -94390671281298676L;

	private List<T> result;
	
	private int pageNo;
	
	private int pageSize;
	
	private int totalPage;
	
	private int totalCount;
	
	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
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

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
}
