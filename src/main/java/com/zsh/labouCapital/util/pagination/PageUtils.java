package com.zsh.labouCapital.util.pagination;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;



/**
 * 分页请求工具类
 * @author Lxb
 * @Decription 根据分页请求获取分页对象
 * @Date 2015-05-13 上午11:19:30
 */
public class PageUtils {

	public static Page getPage(Map<String, Object> params){
		
		Page page = new Page();
		// 默认分页参数
		int curPageNo = 1, pageSize = 50;
		if (params != null) {
			if (params.containsKey("curPageNo")
					&& StringUtils.isNotBlank(params.get("curPageNo").toString())
					&& StringUtils.isNumeric(params.get("curPageNo").toString()))
				curPageNo = Integer.valueOf(params.get("curPageNo").toString());
			if (params.containsKey("pageSize")
					&& StringUtils.isNotBlank(params.get("pageSize").toString())
					&& StringUtils.isNumeric(params.get("pageSize").toString()))
				pageSize = Integer.valueOf(params.get("pageSize").toString());
		}
		
		page.setPageNum(curPageNo);
		page.setNumPerPage(pageSize);
		return page;
		
	}
	
	
	public static Page getPage(PageBean pageBean) {

		Page page = new Page();
		// 默认分页参数
		int curPageNo = 1, pageSize = 20;
		if (pageBean != null) {
			curPageNo = pageBean.getPage();
			pageSize = pageBean.getPagesize();
		}

		page.setPageNum(curPageNo);
		page.setNumPerPage(pageSize);
		return page;

	}
	
}
