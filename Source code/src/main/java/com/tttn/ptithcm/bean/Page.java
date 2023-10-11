package com.tttn.ptithcm.bean;

import org.springframework.stereotype.Component;


@Component
public class Page {
	private int page = 1;
	
	private int rows;
	
	private int totalPage;  
	
	private int offset;
	
	private int totalCount;  

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getOffset() {
		this.offset = (page - 1) * rows;
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}
	
	public int getTotalPage() {
		return (totalCount-1)/rows+1;
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
