package com.zsh.labouCapital.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;

import com.zsh.labouCapital.comm.SystemConst;
import com.zsh.labouCapital.pojo.DataPermission;
import com.zsh.labouCapital.service.IDataPermissionService;
import com.zsh.labouCapital.util.MemcachedUtil;
import com.zsh.labouCapital.util.RequestUtil;
import com.zsh.labouCapital.util.SpringContext;
import com.zsh.labouCapital.util.pagination.PageBean;

@Controller
public abstract class BaseController {

	static final String message = "message";

	static final String post = "POST";

	private static final String DEFAULT_ENCODING = "UTF-8";

	public PageBean parsePageParm(HttpServletRequest request) {
		int page = Integer.parseInt(request.getParameter("page") == null ? "1" : request.getParameter("page"));
		int pagesize = Integer
				.parseInt(request.getParameter("pagesize") == null ? "20" : request.getParameter("pagesize"));
		return new PageBean(page, pagesize);
	}

	/**
	 * 获取请求参数键值对数组 </p>
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @return HashMap<String,String> 分请求参数键值对数组
	 */
	@SuppressWarnings({ "rawtypes" })
	public Map<String, Object> parseReqParam(HttpServletRequest request) {

		// 返回值Map
		Map<String, Object> map = new HashMap<String, Object>();
		Iterator entries = request.getParameterMap().entrySet().iterator();
		Map.Entry entry = null;
		String name = "";
		String value = "";
		while (entries.hasNext()) {
			entry = (Map.Entry) entries.next();
			name = (String) entry.getKey();
			Object valueObj = entry.getValue();
			if (null == valueObj) {
				value = "";
			} else if (valueObj instanceof String[]) {
				String[] values = (String[]) valueObj;
				for (int i = 0; i < values.length; i++) {
					value = values[i] + ",";
				}
				value = value.substring(0, value.length() - 1);
			} else {
				value = valueObj.toString();
			}
			if ("null".equals(value)) {
				value = "";
			}
			if (StringUtils.isNotBlank(name) && StringUtils.isNotBlank(value)) {
				map.put(name, value);
			}
		}
		return map;

		/*
		 * // 取消对请求串的UTF-8编码，防止不同浏览器中发送请求时进行默认编码处理导致的转码失败 Map<String, Object>
		 * map = null; try { byte[] buffer = new byte[512];
		 * ByteArrayOutputStream baos = new ByteArrayOutputStream(); InputStream
		 * in = request.getInputStream(); for (int len = 0; (len =
		 * in.read(buffer)) > 0;) { baos.write(buffer, 0, len); } String content
		 * = new String(baos.toByteArray(), DEFAULT_ENCODING);
		 * if(StringUtils.isNotBlank(content)){ ObjectMapper mapper = new
		 * ObjectMapper(); map = mapper.readValue(content, Map.class); }
		 * 
		 * baos.close(); } catch (IOException e) { e.printStackTrace(); } return
		 * map;
		 */}

	/**
	 * 获取用户权限数据
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	public DataPermission getDataPermission(HttpServletRequest request) throws Exception {
		DataPermission dp = null;

		if (request.getSession().getAttribute(SystemConst.ACCOUNT_WG_CUSTOMERID) == null) {
			Long orgId = Long.valueOf(request.getSession().getAttribute(SystemConst.ACCOUNT_WG_ORGID).toString());
			if (orgId != null
					&& MemcachedUtil.asyncGet(orgId + SystemConst.ACCOUNT_WG_MEMCACHED_DATA_PERMISSION) != null) {
				dp = (DataPermission) RequestUtil.getParameters(
						MemcachedUtil.asyncGet(orgId + SystemConst.ACCOUNT_WG_MEMCACHED_DATA_PERMISSION).toString(),
						DataPermission.class);

			} else {
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("orgId", orgId);
				IDataPermissionService dataPermissionService = (IDataPermissionService) SpringContext
						.getBean("dataPermissionService");
				dp = dataPermissionService.findDataPermission(m);
			}
		} else {
			Long cusId = (Long.valueOf((String) request.getSession().getAttribute(SystemConst.ACCOUNT_WG_CUSTOMERID)));
			if (cusId != null
					&& MemcachedUtil.asyncGet(cusId + SystemConst.ACCOUNT_WG_MEMCACHED_DATA_PERMISSION) != null)
				dp = (DataPermission) RequestUtil.getParameters(
						MemcachedUtil.asyncGet(cusId + SystemConst.ACCOUNT_WG_MEMCACHED_DATA_PERMISSION).toString(),
						DataPermission.class);
			else {
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("customerId", cusId);
				IDataPermissionService dataPermissionService = (IDataPermissionService) SpringContext
						.getBean("dataPermissionService");
				dp = dataPermissionService.findDataPermission(m);
			}
		}

		return dp;

	}

}
