package com.zsh.labouCapital.comm.filter;

/**
 * @author gongyu
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zsh.labouCapital.comm.SystemConst;

public class SessionFilterEx implements Filter {
	private static List<String> unFilterDirList;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// 不需要过滤的静态文件
		String unfilter = filterConfig.getInitParameter("unfilterresource");
		if (unfilter != null) {
			unFilterDirList = new ArrayList<String>();
			StringTokenizer st = new StringTokenizer(unfilter, ",");
			unFilterDirList.clear();
			while (st.hasMoreTokens()) {
				unFilterDirList.add(st.nextToken());
			}
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession(false);
		String requestUrl = httpRequest.getRequestURL().toString();
		String basePath = request.getScheme() + "://" + httpRequest.getServerName() + ":" + httpRequest.getLocalPort()
				+ httpRequest.getContextPath() + "/";
		// 放行登录页面，和静态资源
		// 需要在filter初始化参数中配置放行的资源，使用正则表达式匹配

		if (unFilterDirList != null) {
			// for (int i = 0; i < unFilterDirList.size(); i++) {
			// if (requestUrl.startsWith(urlServerOuter +
			// unFilterDirList.get(i))
			// || requestUrl.startsWith(urlServerLocal + unFilterDirList.get(i))
			// || requestUrl.startsWith(urlTestOuter + unFilterDirList.get(i))
			// || requestUrl.startsWith(urlTestLocal + unFilterDirList.get(i))
			// || requestUrl.startsWith(urlLocal + unFilterDirList.get(i))) {
			// chain.doFilter(httpRequest, httpResponse);
			// return;
			// }
			// }
			for (String unFilterDir : unFilterDirList) {
				if (requestUrl.indexOf(unFilterDir) > -1) {
					chain.doFilter(httpRequest, httpResponse);
					return;
				}
			}
		}

		/*
		 * if (requestUrl.equals(urlServerOuter) ||
		 * requestUrl.equals(urlServerOuter + "validatepwd")||
		 * requestUrl.equals(urlServerOuter + "logout") ||
		 * requestUrl.equals(urlServerOuter + "queryUserCacheStatus")||
		 * requestUrl.equals(urlServerOuter + "updateUserCacheStatus")
		 * 
		 * || requestUrl.equals(urlServerLocal) ||
		 * requestUrl.equals(urlServerLocal + "validatepwd") ||
		 * requestUrl.equals(urlServerLocal + "logout") ||
		 * requestUrl.equals(urlServerLocal + "queryUserCacheStatus") ||
		 * requestUrl.equals(urlServerLocal + "updateUserCacheStatus")
		 * 
		 * || requestUrl.equals(urlTestOuter) || requestUrl.equals(urlTestOuter
		 * + "validatepwd") || requestUrl.equals(urlTestOuter + "logout") ||
		 * requestUrl.equals(urlTestOuter + "queryUserCacheStatus") ||
		 * requestUrl.equals(urlTestOuter + "updateUserCacheStatus")
		 * 
		 * || requestUrl.equals(urlTestLocal) || requestUrl.equals(urlTestLocal
		 * + "validatepwd") || requestUrl.equals(urlTestLocal + "logout") ||
		 * requestUrl.equals(urlTestLocal + "queryUserCacheStatus") ||
		 * requestUrl.equals(urlTestLocal + "updateUserCacheStatus")
		 * 
		 * || requestUrl.equals(urlLocal) || requestUrl.equals(urlLocal +
		 * "validatepwd") || requestUrl.equals(urlLocal + "logout") ||
		 * requestUrl.equals(urlLocal + "queryUserCacheStatus") ||
		 * requestUrl.equals(urlLocal + "updateUserCacheStatus")) {
		 * chain.doFilter(httpRequest, httpResponse); return; }
		 */
//		System.out.println("requestUrl:" + requestUrl);
//		System.out.println("basePath:" + basePath);
		if (requestUrl.equals(basePath) || requestUrl.equals(basePath + "validatepwd")
				|| requestUrl.equals(basePath + "logout") || requestUrl.equals(basePath + "queryUserCacheStatus")
				|| requestUrl.equals(basePath + "updateUserCacheStatus")) {
			chain.doFilter(httpRequest, httpResponse);
			return;
		}

		// 判断当前页是否是重定向以后的登录页面
		String ref = httpRequest.getHeader("referer"); // 是否是从地址栏直接输入的地址吗
		if ((ref == null) || (ref.equals(""))) {
			httpResponse.sendRedirect(basePath);
			return;
		}

		if (session != null) {
			if (session.getAttribute(SystemConst.ACCOUNT_LOGINNAME) != null) {
				chain.doFilter(httpRequest, httpResponse);
				return;
			} else {
				Enumeration<String> sessionAttrNameEnu = session.getAttributeNames();
				while (sessionAttrNameEnu.hasMoreElements()) {
					String sessioAttrName = sessionAttrNameEnu.nextElement();
					session.removeAttribute(sessioAttrName);
				}
				session.invalidate();
				httpResponse.sendRedirect(basePath);
			}
		} else {
			httpResponse.sendRedirect(basePath);
		}

	}

	@Override
	public void destroy() {
	}

}
