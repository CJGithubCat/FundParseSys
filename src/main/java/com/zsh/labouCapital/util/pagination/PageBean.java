package com.zsh.labouCapital.util.pagination;

public class PageBean {
	private int page = 1;// 第几页
	private int pagesize = 20;// 每页记录数
	private int pageOffset;
	
	public PageBean(){
		
	}
	
	public PageBean(int page, int pagesize) {
		super();
		this.page = page;
		this.pagesize = pagesize;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public int getPageOffset() {
		this.pageOffset  = (page-1)*pagesize;
		return pageOffset;
	}

	public void setPageOffset(int pageOffset) {
		this.pageOffset = pageOffset;
	}

}
