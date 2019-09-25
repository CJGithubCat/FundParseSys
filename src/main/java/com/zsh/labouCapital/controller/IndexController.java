//package com.zsh.labouCapital.controller;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.zsh.labouCapital.comm.SystemConst;
//import com.zsh.labouCapital.entity.Company;
//import com.zsh.labouCapital.entity.IndexCount;
//import com.zsh.labouCapital.entity.ReturnValue;
//import com.zsh.labouCapital.service.IndexCountService;
//
//@Controller
//@RequestMapping("/home")
//public class IndexController {
//
//	@Autowired
//	private IndexCountService indexService;
//
//	/***
//	 * 函数功能：第一次登录
//	 * 
//	 * @throws IOException
//	 * @throws ServletException
//	 */
//	@RequestMapping("/firstLog")
//	public void firstLog1(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		// 获取各种统计的数据
//		HttpSession session = request.getSession(true);
//		if (session == null) {
//			request.getRequestDispatcher("/htmls/index.html").forward(request, response);
//		}
//		Company company = (Company) session.getAttribute(SystemConst.ACCOUNT_AGENCY);
//		String companyType = (String) session.getAttribute(SystemConst.ACCOUNT_COMPANY_TYPE);
//		IndexCount entity = new IndexCount();
//		entity.setCompanyPath(company.getCompanyPath());
//		entity.setCompanyType(companyType);
//		session.setAttribute("indexEntity", entity);
//		ArrayList<Integer> list = new ArrayList<Integer>();
//		for (int i = 0; i < 4; i++) {
//			list.add(-1);
//		}
//		HashMap<String, Object> result = new HashMap<String, Object>();
//		result.put("daishenhe", list);
//		result.put("daiqueren", list);
//		result.put("shenheweitongguo", list);
//		request.setAttribute("data", result);
//		
//		
//		request.getRequestDispatcher("../jsp/home.jsp?companyType="+companyType).forward(request, response);
//	}
//
//	/**
//	 * 函数功能：处理首页展示的统计数据的获取；
//	 * 
//	 * @throws IOException
//	 * @throws ServletException
//	 */
//	@RequestMapping("/count")
//	@ResponseBody
//	public ReturnValue count(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		HttpSession session = request.getSession(true);
//		if (session == null) {
//			request.getRequestDispatcher("/htmls/index.html").forward(request, response);
//		}
//		response.setContentType("application/json; charset=utf-8");
//		IndexCount entity = new IndexCount();
//		String companyType = (String) session.getAttribute(SystemConst.ACCOUNT_COMPANY_TYPE);
//		String companyPath = (String) session.getAttribute(SystemConst.ACCOUNT_ATTRIBUTE_PATH);
//		// 市公司
//		if (companyType.equals("0")) {
//			entity.setXgs_gzd_daishenhe(indexService.queryXgsSalaryNoticeCount(companyPath, "4"));//县公司工资单待审核
//			entity.setJyz_gzd_daishenhe(indexService.queryJyzSalaryNoticeCount(companyPath, "4"));//加油站工资单待审核
//			//indexService.queryXgsSalaryNoticeCount(companyPath, "-1");//县公司工资单未通过
//			//indexService.queryJyzSalaryNoticeCount(companyPath, "-1");//加油站工资单未通过
//			entity.setJyz_khtzd_weixifa(indexService.queryJyzExamNoticeCount(companyPath, "1"));//加油站考核通知单未下发
//			entity.setXgs_khtzd_weixifa(indexService.queryXgsExamNoticeCount(companyPath, "1"));//县公司考核通知单未下发
//			//indexService.queryJyzExamNoticeCount(companyPath, "");////县公司考核通知单待确认
//			//indexService.queryJyzExamNoticeCount(companyPath, "");//加油站考核通知单待确认
//		} else if (companyType.equals("1") || companyType.equals("2") || companyType.equals("3")
//				|| companyType.equals("4")) {
//			entity.setJyz_gzd_daishenhe(indexService.queryJyzSalaryNoticeCount(companyPath, "2"));//加油站工资单待审核
//			entity.setJyz_khtzd_daiqueren(indexService.queryJyzExamNoticeCount(companyPath, "4"));//加油站考核通知单待确认
//			entity.setXgs_khtzd_daiqueren(indexService.queryXgsExamNoticeCount(companyPath, "2"));//县公司考核通知单待确认
//			entity.setXgs_gzd_weitongguo(indexService.queryXgsSalaryNoticeCount(companyPath, "-1"));
//		} else if (companyType.equals("5")) {
//			entity.setJyz_gzd_daishenhe(indexService.queryJyzSalaryNoticeCount(companyPath, "-1"));//加油站工资单未通过
//			entity.setJyz_khtzd_daiqueren(indexService.queryJyzExamNoticeCount(companyPath, "4"));//加油站考核通知单待审核
//		}
//		entity.setCompanyType(companyType);
//		ReturnValue rv = new ReturnValue();
//		rv.setDatas(entity);
//		rv.setErrorCode(-1);
//		rv.setMessage("sucess");
//		rv.setSuccess(true);
//		return rv;
//	}
//}
