package com.zsh.labouCapital.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zsh.labouCapital.bean.TreeNode;
import com.zsh.labouCapital.comm.SystemConst;
import com.zsh.labouCapital.entity.Company;
import com.zsh.labouCapital.entity.ReturnValue;
import com.zsh.labouCapital.entity.User;
import com.zsh.labouCapital.service.ICompanyService;
import com.zsh.labouCapital.service.ILoggerService;
import com.zsh.labouCapital.service.IUserService;
import com.zsh.labouCapital.util.ipaddress.RequestRealIp;
import com.zsh.labouCapital.util.pagination.PageBean;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "/company")
public class CompanyController extends BaseController {

	protected static final Logger LOGGER = LoggerFactory.getLogger(CompanyController.class);
	@Autowired
	private ICompanyService companyService;

	@Autowired
	private IUserService userService;

	@Autowired
	private ILoggerService iloggerService;

	@RequestMapping("/add")
	@ResponseBody
	@Transactional
	public ReturnValue addCompany(HttpServletRequest request, Company company) throws Exception {
		ReturnValue rv = new ReturnValue();
		if (StringUtils.isBlank(company.getCompanyName())) {
			rv.setSuccess(false);
			rv.setMessage("机构名称为空");
			return rv;
		}
		Company oldCompany = companyService.findById(company.getParentCompanyId().toString());
		if (oldCompany == null) {
			rv.setSuccess(false);
			rv.setMessage("父机构不存在");
			return rv;
		}
		if (company.getParentCompanyId() == null) {
			rv.setSuccess(false);
			rv.setMessage("父机构id为空");
			return rv;
		}
		try {
			HttpSession session = request.getSession();
			Integer newCompanyId = companyService.add(company);
			// 返回树节点
			TreeNode newNode = new TreeNode();
			String attribytesPath = companyService.getCompanyPathById(newCompanyId);
			if (attribytesPath == null) {
				rv.setDatas(newNode);
				rv.setSuccess(true);
				rv.setMessage("添加失败");
				return rv;
			} else {
				// newNode.setAttributesPath(attribytesPath);
				newNode.setCompanyPath(attribytesPath);
				newNode.setId(newCompanyId);
				newNode.setIsLeaf(true);
				newNode.setIsRoot(false);
				newNode.setParentNodeId(company.getParentCompanyId());
				newNode.setText(company.getCompanyName());
				rv.setDatas(newNode);
				rv.setSuccess(true);
				rv.setMessage("添加成功");
				// 写入日志
				int userid = Integer.parseInt(session.getAttribute("userid").toString());
				String ip = RequestRealIp.getIpAddress(request);
				Date nowDate = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				iloggerService.writeLog(userid, sdf.format(nowDate), 10201, ip, "添加机构");
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
			rv.setSuccess(false);
			rv.setMessage(e.getMessage());
			return rv;
		} finally {

		}
	}

	@RequestMapping("/update")
	@ResponseBody
	public ReturnValue updateCompany(HttpServletRequest request, Company company) throws Exception {
		HttpSession session = request.getSession();
		ReturnValue rv = new ReturnValue();

		if (company.getCompanyId() == null) {
			rv.setSuccess(false);
			rv.setMessage("机构id为空");
			return rv;
		}

		if (StringUtils.isBlank(company.getCompanyName())) {
			rv.setSuccess(false);
			rv.setMessage("机构名称为空");
			return rv;
		}

		if (company.getParentCompanyId() == null) {
			rv.setSuccess(false);
			rv.setMessage("父机构id为空");
			return rv;
		}

		try {
			Integer companyId = company.getCompanyId();
			String companyName = company.getCompanyName();
			// 判断该机构是否存在
			Company nowCompany = companyService.findById(companyId + "");
			if (nowCompany == null) {
				rv.setSuccess(false);
				rv.setMessage("当前机构已删除，请刷新机构树!");
				return rv;
			}

			// 判断是否含有重复的机构名字
			int count = companyService.getCountByCompanyName(companyId, companyName);
			if (count != 0) {
				rv.setSuccess(false);
				rv.setMessage("该机构名已存在!");
				return rv;
			}

			companyService.edit(company);
			rv.setSuccess(true);
			rv.setMessage("更新成功");
			// 写入日志
			int userid = Integer.parseInt(session.getAttribute("userid").toString());
			String ip = RequestRealIp.getIpAddress(request);
			Date nowDate = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			iloggerService.writeLog(userid, sdf.format(nowDate), 10201, ip, "修改机构");
			return rv;
		} catch (Exception e) {
			e.printStackTrace();
			rv.setSuccess(false);
			rv.setMessage(e.getMessage());
			return rv;
		}
	}

	@RequestMapping("/del")
	@ResponseBody
	@Transactional
	public ReturnValue delCompany(HttpServletRequest request, Company Company) throws Exception {
		ReturnValue rv = new ReturnValue();
		if (Company.getCompanyId() == null) {
			rv.setSuccess(false);
			rv.setMessage("id为空");
			return rv;
		}
		try {
			HttpSession session = request.getSession();
			Company nowCompany = companyService.findById(Company.getCompanyId() + "");
			if (nowCompany == null) {
				rv.setSuccess(false);
				rv.setMessage("当前机构已删除，请刷新机构树!");
				return rv;
			}
			// 删除之前检查当前Company下是否有子机构；如果没有则检查机构下是否有车辆
			List<Company> list = companyService.queryChildrenCompanyById(Company.getCompanyId());
			if (list != null && list.size() > 0) {
				rv.setSuccess(false);
				rv.setMessage("该机构下含子机构，请先删除子机构");
				return rv;
			}

			List<User> users = userService.queryUserByCompanyId(Company.getCompanyId());
			if (users != null && users.size() > 0) {
				rv.setSuccess(false);
				rv.setMessage("该机构下含用户，请先删除用户");
				return rv;
			}
			companyService.removeById(String.valueOf(Company.getCompanyId()));
			rv.setSuccess(true);
			rv.setMessage("删除成功");
			// 写入日志
			int userid = Integer.parseInt(session.getAttribute("userid").toString());
			String ip = RequestRealIp.getIpAddress(request);
			Date nowDate = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			iloggerService.writeLog(userid, sdf.format(nowDate), 10201, ip, "删除机构");
			return rv;
		} catch (Exception e) {
			e.printStackTrace();
			rv.setSuccess(false);
			rv.setMessage(e.getMessage());
			return rv;
		}
	}

	@RequestMapping("/queryTreeNodeList")
	@ResponseBody
	public ReturnValue queryTreeNodeList(Integer nodeId,HttpServletRequest request) throws Exception {
		ReturnValue rv = new ReturnValue();
		if (nodeId == null) {
			rv.setSuccess(false);
			rv.setMessage("nodeId为空");
			return rv;
		}
		String needChild = request.getParameter("needChild");
		if(StringUtils.isEmpty(needChild)){
			needChild = "1";
		}
		try {
			List<Company> list = companyService.queryChildrenCompanyById(nodeId);
			List<TreeNode> nodes = new ArrayList<TreeNode>();
			for (Company company : list) {
				if(needChild.equals("1")){
					TreeNode treeNode = new TreeNode();
					treeNode.setId(company.getCompanyId());
					treeNode.setText(company.getCompanyName());
					treeNode.setParentNodeId(company.getParentCompanyId());
					treeNode.setCompanyType(company.getCompanyType() + "");
					// treeNode.setAttributesPath(company.getCompanyPath());
					treeNode.setCompanyPath(company.getCompanyPath());
					List<Company> agencies = companyService.queryChildrenCompanyById(company.getCompanyId());
					if (agencies != null && agencies.size() > 0) {
						treeNode.setIsLeaf(false);
					} else {
						treeNode.setIsLeaf(true);
					}
					nodes.add(treeNode);
				}else if(needChild.equals("0")){//不需要
					if(nodeId == company.getCompanyId()){
						TreeNode treeNode = new TreeNode();
						treeNode.setId(company.getCompanyId());
						treeNode.setText(company.getCompanyName());
						treeNode.setParentNodeId(company.getParentCompanyId());
						treeNode.setCompanyType(company.getCompanyType() + "");
						treeNode.setCompanyPath(company.getCompanyPath());
						treeNode.setIsLeaf(true);
						nodes.add(treeNode);
						break;
					}
				}
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("list", nodes);
			rv.setDatas(map);
			rv.setSuccess(true);
			return rv;
		} catch (Exception e) {
			e.printStackTrace();
			rv.setSuccess(false);
			rv.setMessage(e.getMessage());
			return rv;
		}
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping("/queryTreeNodeListByCompanyName")
	@ResponseBody
	public ReturnValue queryTreeNodeListByCompanyName(String companyName) throws Exception {
		ReturnValue rv = new ReturnValue();
		if (companyName == null && !"".equals(companyName)) {
			rv.setSuccess(false);
			rv.setMessage("companyName为空");
			return rv;
		}
		try {
			List<Company> listByName = companyService.queryCompanyByCompanyName(companyName);
			Company company = new Company();
			if (listByName != null && listByName.size() > 0) {
				company = listByName.get(0);
			} else {
				rv.setSuccess(false);
				rv.setMessage("没有此公司");
				return rv;
			}
			String path = company.getCompanyPath();
			path = path.substring(1);
			List pathlist = new ArrayList<>();
			pathlist = Arrays.asList(path.split(","));
			rv.setDatas(pathlist);
			rv.setSuccess(true);
			return rv;
		} catch (Exception e) {
			e.printStackTrace();
			rv.setSuccess(false);
			rv.setMessage(e.getMessage());
			return rv;
		}
	}

	@RequestMapping("/queryTreeData")
	@ResponseBody
	public ReturnValue queryTreeData(HttpServletRequest request) throws Exception {
		ReturnValue rv = new ReturnValue();

		Object currentCompanyId = request.getSession().getAttribute("current_company_id");
		if (currentCompanyId == null) {
			rv.setSuccess(false);
			rv.setMessage("当前companyid为空");
			return rv;
		}
		try {
			List<Company> list = companyService.queryAllAgencyById((Integer) currentCompanyId);
			List<TreeNode> nodes = new ArrayList<TreeNode>();
			for (Company company : list) {
				TreeNode treeNode = new TreeNode();
				if (company.getCompanyId().intValue() == ((Integer) currentCompanyId).intValue()) {
					treeNode.setIsRoot(true);
				}
				treeNode.setId(company.getCompanyId());
				treeNode.setText(company.getCompanyName());
				treeNode.setParentNodeId(company.getParentCompanyId());
				nodes.add(treeNode);
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("list", nodes);
			rv.setDatas(map);
			rv.setSuccess(true);
			return rv;
		} catch (Exception e) {
			e.printStackTrace();
			rv.setSuccess(false);
			rv.setMessage(e.getMessage());
			return rv;
		}
	}

	@RequestMapping("/queryTreeRootNode")
	@ResponseBody
	public ReturnValue queryTreeRootNode(HttpServletRequest request) throws Exception {
		ReturnValue rv = new ReturnValue();

		Object currentCompanyId = request.getSession().getAttribute("current_agency_id");
		if (currentCompanyId == null) {
			rv.setSuccess(false);
			rv.setMessage("当前Companyid为空");
			return rv;
		}
		try {
			Company company = companyService.findById(currentCompanyId.toString());
			TreeNode treeNode = new TreeNode();
			treeNode.setId(company.getCompanyId());
			treeNode.setText(company.getCompanyName());
			treeNode.setParentNodeId(company.getParentCompanyId());
			// treeNode.setAttributesPath(company.getCompanyPath());
			treeNode.setCompanyPath(company.getCompanyPath());
			treeNode.setCompanyType(company.getCompanyType() + "");
			List<Company> agencies = companyService.queryChildrenCompanyById(company.getCompanyId());
			if (agencies != null && agencies.size() > 0) {
				treeNode.setIsLeaf(false);
			} else {
				treeNode.setIsLeaf(true);
			}
			rv.setDatas(treeNode);
			rv.setSuccess(true);
			return rv;
		} catch (Exception e) {
			e.printStackTrace();
			rv.setSuccess(false);
			rv.setMessage(e.getMessage());
			return rv;
		}
	}

	@RequestMapping("/getAllAgencyName")
	@ResponseBody
	public ReturnValue getAllAgencyName(HttpServletRequest request) throws Exception {
		ReturnValue rv = new ReturnValue();
		Object currentAgencyId = request.getSession().getAttribute("current_agency_id");
		if (currentAgencyId == null) {
			rv.setSuccess(false);
			rv.setMessage("当前agencyid为空");
			return rv;
		}
		try {
			List<String> agencyNames = companyService.getAllAgencyName(currentAgencyId.toString());
			String[] names = (String[]) agencyNames.toArray(new String[agencyNames.size()]);
			rv.setDatas(names);
			rv.setSuccess(true);
			return rv;
		} catch (Exception e) {
			e.printStackTrace();
			rv.setSuccess(false);
			rv.setMessage(e.getMessage());
			return rv;
		}
	}

	@RequestMapping("/queryMaxLeng")
	@ResponseBody
	public ReturnValue queryMaxLeng(HttpServletRequest request) throws Exception {
		ReturnValue rv = new ReturnValue();
		HttpSession session = request.getSession();
		Company company = (Company) session.getAttribute(SystemConst.ACCOUNT_AGENCY);
		try {
			rv = companyService.queryMaxLeng(company);
			return rv;
		} catch (Exception e) {
			e.printStackTrace();
			rv.setSuccess(false);
			rv.setMessage(e.getMessage());
			return rv;
		}
	}

	@RequestMapping("/queryCompanyByName")
	@ResponseBody
	public ReturnValue queryCompanyByName(String companyName) throws Exception {
		ReturnValue rv = new ReturnValue();
		if (StringUtils.isBlank(companyName)) {
			rv.setSuccess(false);
			rv.setMessage("companyName为空");
			return rv;
		}
		try {
			List<Company> agencies = companyService.queryAgencyByAgencyName(companyName);

			rv.setDatas(agencies);
			rv.setSuccess(true);
			return rv;
		} catch (Exception e) {
			e.printStackTrace();
			rv.setSuccess(false);
			rv.setMessage(e.getMessage());
			return rv;
		}
	}

	@RequestMapping("/queryCompanyPage")
	@ResponseBody
	public ReturnValue queryCompanyPage(Company company, HttpServletRequest request) throws Exception {
		ReturnValue rv = new ReturnValue();
		PageBean pageBean = parsePageParm(request);
		try {
			rv = companyService.queryCompanyPage(company, pageBean);
			// 写入日志
			HttpSession session = request.getSession();
			int userid = Integer.parseInt(session.getAttribute("userid").toString());
			String ip = RequestRealIp.getIpAddress(request);
			Date nowDate = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			iloggerService.writeLog(userid, sdf.format(nowDate), 10201, ip, "查询机构");
			return rv;
		} catch (Exception e) {
			e.printStackTrace();
			rv.setSuccess(false);
			rv.setMessage(e.getMessage());
			return rv;
		}
	}

	/**
	 * 更改机构
	 */
	@RequestMapping("/moveAgency")
	@ResponseBody
	@Transactional
	public ReturnValue moveAgency(HttpServletRequest request, Company company, @RequestBody String agencyIdJson)
			throws Exception {
		ReturnValue rv = new ReturnValue();
		HttpSession session = request.getSession();
		if (session == null) {
			return null;
		}
		try {
			JSONObject jsonObject = JSONObject.fromObject(agencyIdJson);
			Integer companyId = jsonObject.getInt("companyId");
			Integer parentCompanyId = jsonObject.getInt("parentCompanyId");
			String parentCompanyPath = companyService.getCompanyPathById(parentCompanyId);
			String agencyIdStr = Integer.toString(companyId);

			Company nowCompany = companyService.findById(companyId + "");
			if (nowCompany == null) {
				rv.setSuccess(false);
				rv.setMessage("当前机构已删除，请刷新机构树!");
				return rv;
			}
			//
			if (companyId == 1) {
				rv.setSuccess(false);
				rv.setMessage("该机构不可更改!");
				return rv;
			} else if (parentCompanyPath == null || parentCompanyPath == "") {
				rv.setSuccess(false);
				rv.setMessage("需更改到的机构已删除,请刷新后再更改!");
				return rv;
			} else if (parentCompanyPath.indexOf("," + agencyIdStr + ",") >= 0) {
				rv.setSuccess(false);
				rv.setMessage("不能修改为当前或下级机构的子机构!");
				return rv;
			} else {
				company.setCompanyId(companyId);
				company.setParentCompanyId(parentCompanyId);
				companyService.edit(company);
				rv.setSuccess(true);
				rv.setMessage("机构更改成功!");
				// 写入日志
				int userid = Integer.parseInt(session.getAttribute("userid").toString());
				String ip = RequestRealIp.getIpAddress(request);
				Date nowDate = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				iloggerService.writeLog(userid, sdf.format(nowDate), 10201, ip,
						"更改机构,机构id:" + companyId + ",父机构id:" + parentCompanyId);
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
			rv.setSuccess(false);
			rv.setMessage(e.getMessage());
			return rv;
		}
	}

}
