package com.zsh.labouCapital.controller.request;

public class BaseForm {
	public int limit = 20; //每页条数
	public int page = 1; //页码
	public int total = 0;
	public boolean doPagination = true; //是否分页
	
	public String startDate;
	public String endDate;
	public String fundCode;
	
	public int offSet;//便宜量，后端使用的分页参数
	public int pageSize;//每页条数
	
	public int getOffSet() {
		return (this.page - 1) * this.limit;
	}
	public void setOffSet(int offSet) {
		this.offSet = offSet;
	}
	public int getPageSize() {
		return this.limit;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getFundCode() {
		return fundCode;
	}
	public void setFundCode(String fundCode) {
		this.fundCode = fundCode;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public boolean isDoPagination() {
		return doPagination;
	}
	public void setDoPagination(boolean doPagination) {
		this.doPagination = doPagination;
	}
}
