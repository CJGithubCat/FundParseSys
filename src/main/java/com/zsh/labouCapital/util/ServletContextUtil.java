package com.zsh.labouCapital.util;


import javax.servlet.ServletContext;

import org.springframework.web.context.ServletContextAware;

public class ServletContextUtil implements ServletContextAware {
	private static ServletContext servletContext;

	@SuppressWarnings("static-access")
	public void setServletContext(ServletContext sc) {
		this.servletContext = sc;
		System.out.println("项目的绝对路径为：" + servletContext.getRealPath("/"));
	}
	
	 public static ServletContext getContext() {
		return servletContext;
	} 
}