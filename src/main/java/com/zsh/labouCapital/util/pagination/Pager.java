/**
 * 
 */
package com.zsh.labouCapital.util.pagination;

import java.io.Serializable;

/**
 * @author gongyu 分页保证此对象创建时，curPage，curSize，startRow，endRow 都不为空
 */
public class Pager implements Serializable {

	private static final long serialVersionUID = -1305145316226660258L;
	/** 每页容量上限 **/
	private static final int pageLimit = 1000;
	/** 默认起始页 **/
	private static final int defaultPageNum = 1;
	/** 默认每页容量 **/
	private static final int defaultPageSize = 20;
	
	private int curPage;
	private int curSize;
	private int startRow;
	private int endRow;

	@SuppressWarnings("unused")
	private int offSet;// 偏移量

	public Pager() {
		this(defaultPageNum, defaultPageSize);// this(),super()方法只能出现在一个构造函数第一行，而且不能同时出现
		this.curPage = defaultPageNum;
		this.curSize = defaultPageSize;
	}

	public Pager(int curPage) {
		this(curPage, defaultPageSize);
	}

	public Pager(int curPage, int curSize) {
		if (curPage <= 0) {
			this.curPage = defaultPageNum;
		} else {
			this.curPage = curPage;
		}
		if (curSize > pageLimit) {
			this.curSize = pageLimit;
		} else {
			this.curSize = curSize;
		}
		//mysql limit(0,1)取第一行
		//this.startRow = ((this.curPage - 1) * this.curSize + 1);
		this.startRow = (this.curPage - 1) * this.curSize ;
		this.endRow = this.curPage * this.curSize;
	}
	
	public int getPageLimit() {
		return pageLimit;
	}

	/**
	 * @return the defaultpage
	 */
	public int getDefaultpage() {
		return defaultPageNum;
	}

	/**
	 * @return the defaultsize
	 */
	public int getDefaultsize() {
		return defaultPageSize;
	}

	/**
	 * @return the curPage
	 */
	public int getCurPage() {
		return curPage;
	}

	/**
	 * @param curPage
	 *            the curPage to set
	 */
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	/**
	 * @return the curSize
	 */
	public int getCurSize() {
		return curSize;
	}

	/**
	 * @param curSize
	 *            the curSize to set
	 */
	public void setCurSize(int curSize) {
		if (curSize > pageLimit) {
			this.curSize = pageLimit;
		} else {
			this.curSize = curSize;
		}
	}

	/**
	 * @return the startRow
	 */
	public int getStartRow() {
		return startRow;
	}

	/**
	 * @return the endRow
	 */
	public int getEndRow() {
		return endRow;
	}

}
