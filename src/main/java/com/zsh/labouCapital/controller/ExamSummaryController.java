package com.zsh.labouCapital.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zsh.labouCapital.comm.SystemConst;
import com.zsh.labouCapital.entity.Company;
import com.zsh.labouCapital.entity.DepartmentExamNoticeResult;
import com.zsh.labouCapital.entity.ExamSummary;
import com.zsh.labouCapital.entity.ExamSummaryVo;
import com.zsh.labouCapital.entity.ReturnValue;
import com.zsh.labouCapital.entity.StationExamNoticeResult;
import com.zsh.labouCapital.service.IExamEummaryService;
import com.zsh.labouCapital.service.ILoggerService;
import com.zsh.labouCapital.util.excel.ReadExcelUtil;
import com.zsh.labouCapital.util.ipaddress.RequestRealIp;
import com.zsh.labouCapital.util.pagination.PageBean;

/**
 * <p>
 * Title: ExamSummaryController
 * </p>
 * <p>
 * Description:考核汇总管理
 * </p>
 * 
 * @author cj
 * @date 2018年6月24日
 */
@Controller
@RequestMapping(value = "/examSummary")
public class ExamSummaryController extends BaseController {
	private static Logger log = Logger.getLogger(ExamSummaryController.class);
	@Autowired
	private IExamEummaryService examEummaryService;
	@Autowired
	private ILoggerService iloggerService;

	/**
	 * 上传excel.xls模板文件文件
	 * 
	 * @param request
	 * @param agency_id
	 * @param response
	 * @return
	 */
	@RequestMapping("/uploadExcel")
	@ResponseBody
	public ReturnValue uploadExcel(HttpServletRequest request, HttpServletResponse response) {
		ReturnValue rv = new ReturnValue();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		// 获取前台传值
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		String configPath = File.separator + "examSummary" + File.separator;
		String ctxPath = request.getSession().getServletContext().getRealPath("/");
		ctxPath += configPath;
		// 创建文件夹
		try {
			File file = new File(ctxPath);
			if (!file.exists()) {
				file.mkdirs();
			}
			String fileName = null;
			for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
				MultipartFile mf = entity.getValue();
				fileName = mf.getOriginalFilename();
				String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
				String finalFileName = fileName.substring(0, fileName.lastIndexOf("."));
				SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
				String newFileName = finalFileName + df.format(new Date()) + "_" + new Random().nextInt(1000) + "."
						+ fileExt;
				File uploadFile = new File(ctxPath + newFileName);
				String path = uploadFile.getPath();
				try {
					FileUtils.copyInputStreamToFile(mf.getInputStream(), uploadFile);
					// FileCopyUtils.copy(mf.getBytes(), uploadFile);
				} catch (IOException e) {
					e.printStackTrace();
					rv.setSuccess(false);
					rv.setMessage("excel文件上传失败,请联系客服人员解决!");
					return rv;
				}
				response.setHeader("Content-type", "text/html;charset=UTF-8");
				// 这句话的意思，是告诉servlet用UTF-8转码，而不是用默认的ISO8859
				response.setCharacterEncoding("UTF-8");

				// 1.将文件中的数据读入到内存
				// ReadExcel xlsMain = new ReadExcel();
				Map<String, Object> map = ReadExcelUtil.readCheckExamSummaryExcel(path, examEummaryService);
				if (!(boolean) map.get("success")) {
					rv.setSuccess(false);
					rv.setMessage(map.get("reason").toString());
					return rv;
				} else {
					// 将数据插入到数据库
					@SuppressWarnings("unchecked")
					List<ExamSummary> datas = (List<ExamSummary>) map.get("data");
					// 校验是否有不存在的机构：根据名称去company中查找
					Map<String, Object> checkMap = examEummaryService.checkIsCompanysExist(datas);
					if ((boolean) checkMap.get("success") == false) {
						rv.setSuccess(false);
						rv.setMessage("系统中不存在名为:" + map.get("list") + "的机构，请先添加机构信息，再重新导入！");
						return rv;
					}
					// 校验成功的数据写入到数据库
					examEummaryService.addExamSummary(datas);
				}
			}
			rv.setDatas(null);
			rv.setSuccess(true);
			rv.setMessage("考核結果导入成功！");
		} catch (Exception e) {
			rv.setDatas(null);
			rv.setSuccess(false);
			log.info("[uploadExcel]:err:{}", e);
			rv.setMessage("ERR:系统异常，请联系管理员!");
			e.printStackTrace();
			return rv;
		}
		return rv;
	}

	/**
	 * @Title: verifyTheDateInfo @Description: 校验年月信息是否合法 @param @return
	 *         参数 @return boolean 返回类型 @throws
	 */
	public boolean verifyTheDateInfo(String dateStr) throws Exception {
		boolean reSult = false;
		if (StringUtils.isEmpty(dateStr)) {
			return reSult;
		}
		Calendar calendar = Calendar.getInstance();
		int inYear = 0;
		int inMonth = 0;
		int nowYear = calendar.get(Calendar.YEAR);
		int nowMonth = calendar.get(Calendar.MONTH) + 1;
		try {
			String[] ymArr = dateStr.split("-");
			if (ymArr == null || ymArr.length != 2) {
				return false;
			}
			inYear = Integer.parseInt(ymArr[0]);
			inMonth = Integer.parseInt(ymArr[1]);
		} catch (Exception e) {
			throw e;
		}

		if (inYear > nowYear || inYear <= 0) {
			return false;
		}
		if (inMonth > nowMonth || inMonth <= 0) {
			return false;
		}
		return true;
	}

	/**
	 * @Title: queryExamSummarysPage
	 * @Description: 考核汇总导入结果查询
	 * @param examSummaryVo
	 */
	@RequestMapping("/queryExamSummarysPage")
	@ResponseBody
	public ReturnValue queryExamSummarysPage(HttpServletRequest request, ExamSummaryVo examSummaryVo) {
		ReturnValue rv = new ReturnValue();
		PageBean pageBean = parsePageParm(request);
		try {
			examSummaryVo
					.setAttributesPath((String) request.getSession().getAttribute(SystemConst.ACCOUNT_ATTRIBUTE_PATH));

			if (examSummaryVo != null && !StringUtils.isEmpty(examSummaryVo.getYearMonthDate())) {
				String tempym = examSummaryVo.getYearMonthDate();
				examSummaryVo.setYearMonthDate(tempym.replace("-", ""));
			}

			List<ExamSummary> examSummaryList = examEummaryService.queryExamSummarysPage(examSummaryVo, pageBean);
			int count = examEummaryService.getExamSummarysCount(examSummaryVo);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("count", count);
			map.put("list", examSummaryList);
			rv.setDatas(map);
			rv.setSuccess(true);
		} catch (Exception e) {
			rv.setSuccess(false);
			rv.setMessage("ERR:系统异常，请联系管理员!");
			log.info("[queryExamSummarysPage]:err:{}", e);
			return rv;
		}
		return rv;
	}

	/**
	 * @Title: genderXbmExamSummary 
	 * @Description: 生成县级部门通知单 
	 * @param @return
	 *         参数 
	 * @return ReturnValue 
	 * 返回类型 @throws
	 */
	@RequestMapping("/genderExamNotice")
	@ResponseBody
	public ReturnValue genderExamNotice(HttpServletRequest request, @RequestParam String params) {
		ReturnValue rv = new ReturnValue();
		if (StringUtils.isEmpty(params)) {
			rv.setSuccess(false);
			rv.setMessage("ERR:参数为空!");
			return rv;
		}
		try {
			// JSONObject paramJson = JSONObject.parseObject(params);
			JSONArray paramJsonArr = JSONObject.parseArray(params);
			if (paramJsonArr == null) {
				rv.setSuccess(false);
				rv.setMessage("ERR:解析参数失败!");
				return rv;
			}
			for (int i = 0; i < paramJsonArr.size(); i++) {
				JSONObject paramJson = (JSONObject) paramJsonArr.get(i);
				ExamSummary examSummary = parseJsonToExamSummary(paramJson);

				if (examSummary.getFbkName().equals("加油站")) {
					// 做数据校验,防止重复导入
					StationExamNoticeResult tempNotice = examEummaryService.checkIsJyzNoticeExist(examSummary);
					if (tempNotice != null) {// 已经生成了，
						rv.setSuccess(false);
						rv.setMessage("ERR:该明细已经加油站生成通知单，请勿重复生成!");
						return rv;
					}
					StationExamNoticeResult stationExamNotice = new StationExamNoticeResult();
					stationExamNotice.setSummaryId(examSummary.getId());
					stationExamNotice.setCompanyId(examSummary.getCompanyId());
					stationExamNotice.setYearMonthDate(examSummary.getYearMonthDate());
					String noticeNo = examSummary.getJyzName() + examSummary.getYearMonthDate().replace("-", "");
					stationExamNotice.setNoticeNo(noticeNo);
					stationExamNotice.setWtgs(examSummary.getDwName());
					stationExamNotice.setJyz(examSummary.getJyzName());

					double qyllxc = examSummary.getDyfjbz() + examSummary.getQycmbxc();// 轻油联量薪酬:Q+DI
					stationExamNotice.setQyllxc(qyllxc);
					stationExamNotice.setQycmbxc(examSummary.getQycmbxc());// 轻油超目标薪酬
					stationExamNotice.setDyrgfbz(examSummary.getDyrgfbz());// 吨油人工费标准
					stationExamNotice.setGasoline92(examSummary.getGasoline92());
					stationExamNotice.setGasoline95(examSummary.getGasoline95());
					stationExamNotice.setGasoline98(examSummary.getGasoline98());
					stationExamNotice.setCng(examSummary.getTrq());

					stationExamNotice.setCyxl0(examSummary.getDiesel0());
					stationExamNotice.setCyxl10(examSummary.getDiesel10());
					stationExamNotice.setQyzsxc(examSummary.getQyzsxc());// 轻油折算薪酬

					double jykllxc = examSummary.getJsjykxc() + examSummary.getJykdzxc();
					stationExamNotice.setJykllxc(jykllxc);// 加油卡联量薪酬
					stationExamNotice.setXcfczkcze(examSummary.getXcfczkcze());// 现场非充值卡充值额
					stationExamNotice.setCzkcze(examSummary.getCzkcze());// 充值卡充值额
					stationExamNotice.setCzkxse(examSummary.getCzkxse());// 充值卡销售额
					stationExamNotice.setZzfkzdcz(examSummary.getZzfkzdcz());// 自助发卡终端充值
					stationExamNotice.setJykkhcze(examSummary.getJykkhcze());// 加油卡考核充值额
					stationExamNotice.setJykdzxc(examSummary.getJykdzxc());// 加油卡调整薪酬
					stationExamNotice.setFdzxy(examSummary.getFdzxy());// 非定制香烟（零售）销售额（元）
					stationExamNotice.setYbsp(examSummary.getYbspxse());// 一般商品
					stationExamNotice.setZyppblsxse(examSummary.getZyppblsxse());// 异业联盟销售额

					stationExamNotice.setYylm(examSummary.getYylmxse());// 异业联盟
					stationExamNotice.setTg(examSummary.getTgxse());// 团购
					stationExamNotice.setOlz(examSummary.getOlzxse());// 欧露纸
					stationExamNotice.setGqxlxse(examSummary.getGqxlxse());// 枸杞系列销售额
					stationExamNotice.setFypjcxc(examSummary.getFypjcxc());// 非油品基础薪酬
					stationExamNotice.setFypmbwcqkjc(examSummary.getFypmbwcjcje());// 非油品目标完成情况奖惩
					// 其他
					double zykzjjddrygz = examSummary.getZykzjjddrygz() + examSummary.getJbf();
					stationExamNotice.setZykzjjddrygz(zykzjjddrygz);// 整月看站及借调等人员工资:CW+CX
					stationExamNotice.setLstykzgz(examSummary.getLstykzgz());
					stationExamNotice.setQjryxc(examSummary.getQjryxc());
					stationExamNotice.setBlc(examSummary.getBlc());// 保留差
					stationExamNotice.setGdbc(examSummary.getGdbc());// 过渡补差
					stationExamNotice.setZxjf(examSummary.getZxjf());
					stationExamNotice.setGlkf(examSummary.getGlkf());// 管理扣罚
					stationExamNotice.setTotal(examSummary.getHj());// 合计

					// 附加信息
					stationExamNotice.setRgfy(examSummary.getRgfy());// 人工费用
					stationExamNotice.setSjlxj(examSummary.getSjlxj());
					stationExamNotice.setJsjykxc(examSummary.getJsjykxc());
					stationExamNotice.setYdczk(examSummary.getYdczk());
					stationExamNotice.setZyppsxse(examSummary.getZyppsxse());
					stationExamNotice.setQcyp(examSummary.getQcyhxse());// 汽车用品
					stationExamNotice.setRybhjnjxse(examSummary.getRybhjnjxse());
					stationExamNotice.setJbf(examSummary.getJbf());
					stationExamNotice.setSjfj(examSummary.getSjfj());
					stationExamNotice.setDyxcbz(examSummary.getDyxcbz());
					stationExamNotice.setJyzType(examSummary.getJyzType());

					stationExamNotice.setStatus(1);// 状态为已生成未下发
					stationExamNotice.setSalaryStatus(0);
					log.info("stationExamNotice:" + stationExamNotice);
					examEummaryService.genderStationNotice(stationExamNotice);

					rv.setSuccess(true);
					rv.setMessage("生成加油站通知单成功!");
				} else {// 部门通知单
					// 做数据校验,防止重复导入
					DepartmentExamNoticeResult tempNotice = examEummaryService.checkIsBmNoticeExist(examSummary);
					if (tempNotice != null) {// 已经生成了，
						rv.setSuccess(false);
						rv.setMessage("ERR:该明细已经生成部门通知单，请勿重复生成!");
						return rv;
					}
					DepartmentExamNoticeResult deNoticeResult = new DepartmentExamNoticeResult();
					String bmName = "";
					if (examSummary.getFbkName().equals("机关附属")) {
						bmName = examSummary.getBnName() + examSummary.getJyzName();// 机构名
					} else {
						bmName = examSummary.getBnName();
					}
					deNoticeResult.setExamSummayId(examSummary.getId());
					deNoticeResult.setCompanyId(examSummary.getCompanyId());
					deNoticeResult.setNoticeNo(bmName + examSummary.getYearMonthDate().replace("-", ""));
					deNoticeResult.setYearMonthDate(examSummary.getYearMonthDate().replace("-", ""));
					deNoticeResult.setBmmc(bmName);// 部门名称
					deNoticeResult.setLlggxs(examSummary.getLlggxs());// 联量挂钩系数
					deNoticeResult.setXcxfy(examSummary.getHj());// 合计
					deNoticeResult.setJbxc(examSummary.getJbxc());// 基本薪酬
					deNoticeResult.setLlxc(examSummary.getLlxc());// 联量薪酬
					deNoticeResult.setQjryxc(examSummary.getQjryxc());// 请假人员工资
					deNoticeResult.setGdbc(examSummary.getGdbc());// 过渡补差
					deNoticeResult.setBlc(examSummary.getBlc());// 保留差
					deNoticeResult.setQtbt(examSummary.getQtbt());// 其他补贴
					deNoticeResult.setJbf(examSummary.getJbf());// 加班费
					deNoticeResult.setQtgz(examSummary.getQtbt());// 其他工资
					deNoticeResult.setZxjf(examSummary.getZxjf());// 专项奖罚
					deNoticeResult.setGlkf(examSummary.getGlkf());// 管理扣罚
					deNoticeResult.setLlxc(examSummary.getLlxc());// 劳效薪酬
					deNoticeResult.setStatus(1);
					deNoticeResult.setSalaryStatus(0);
					deNoticeResult.setTotal(deNoticeResult.getXcxfy());
					log.info("deNoticeResult:" + deNoticeResult);
					examEummaryService.genderDepartNotice(deNoticeResult);
					rv.setSuccess(true);
					rv.setMessage("生成部门通知单成功!");
				}
			}
		} catch (Exception e) {
			rv.setSuccess(false);
			rv.setMessage("ERR:系统异常，请联系管理员!");
			log.info("[genderXbmExamSummary]:err:{}", e);
			return rv;
		}
		return rv;
	}

	/**
	 * @Title: parseJsonToExamSummary
	 * @Description: TODO @param @return 参数
	 * @return ExamSummary 返回类型 @throws
	 */
	public ExamSummary parseJsonToExamSummary(JSONObject paramJson) {
		if (paramJson == null) {
			return null;
		}
		ExamSummary examSummary = new ExamSummary();

		int summaryId = paramJson.getInteger("id");
		int companyId = paramJson.getInteger("companyId");
		String fbk_name = paramJson.getString("fbkName");
		String jyz_type = paramJson.getString("jyzType");
		String dw_name = paramJson.getString("dwName");
		String bn_name = paramJson.getString("bnName");
		String year_month_date = paramJson.getString("yearMonthDate");
		String bw_code = paramJson.getString("bwCode");
		double sykhbfypxsedywcl = paramJson.getDouble("sykhbfypxsedywcl");
		double hj = paramJson.getDouble("hj");
		double trqydrwwclzb = paramJson.getDouble("trqydrwwclzb");
		double zyppsxse = paramJson.getDouble("zyppsxse");
		double dddwsbd = paramJson.getDouble("dwsb");
		double fypxsedywcl = paramJson.getDouble("fypxsedywcl");
		double wtcze = paramJson.getDouble("wtcze");
		double jbxc = paramJson.getDouble("jbxc");
		double cklkzxsxx = paramJson.getDouble("cklkzxsxx");
		double gldf = paramJson.getDouble("gldf");
		double fypydmledyrw = paramJson.getDouble("fypydmledyrw");
		double id = paramJson.getDouble("id");
		double dyxcbz = paramJson.getDouble("dyxcbz");
		double jykdzxc = paramJson.getDouble("jykdzxc");
		double sjlxj = paramJson.getDouble("sjlxj");
		double sykhbfypxseydrwwcl = paramJson.getDouble("sykhbfypxseydrwwcl");
		double fypydmleydrwwcl = paramJson.getDouble("fypydmleydrwwcl");
		double zpydmledyrw = paramJson.getDouble("zpydmledyrw");
		double ydlrydrwwclzb = paramJson.getDouble("ydlrydrwwclzb");
		double snyjckl = paramJson.getDouble("snyjckl");
		double qyxc = paramJson.getDouble("qyxc");
		double zpydmleydrwwclzb = paramJson.getDouble("zpydmleydrwwclzb");
		double jbf = paramJson.getDouble("jbf");
		double glkf = paramJson.getDouble("glkf");
		double ydczk = paramJson.getDouble("ydczk");
		double llggxs = paramJson.getDouble("llggxs");
		double diesel10 = paramJson.getDouble("diesel10");
		double rsdljgglf = paramJson.getDouble("rsdljgglf");
		double zpldywcl = paramJson.getDouble("zpldywcl");
		double zplydrwwcl = paramJson.getDouble("zplydrwwcl");
		double jrfl = paramJson.getDouble("jrfl");
		int status = paramJson.getInteger("status");
		double lxxc = paramJson.getDouble("lxxc");
		double dyfjbz = paramJson.getDouble("dyfjbz");
		double rclb = paramJson.getDouble("rclb");
		String bz = paramJson.getString("bz");
		double llxc = paramJson.getDouble("llxc");
		double glzbzb = paramJson.getDouble("glzbzb");
		double lmjxse = paramJson.getDouble("lmjxse");
		double sykhbfypxsedyrw = paramJson.getDouble("sykhbfypxsedyrw");
		double lsydmledywcl = paramJson.getDouble("lsydmledywcl");
		double trqydrwwcl = paramJson.getDouble("trqydrwwcl");
		double zpldyrw = paramJson.getDouble("zpldyrw");
		double jykkhcze = paramJson.getDouble("jykkhcze");
		double sjfj = paramJson.getDouble("sjfj");
		double fypjcxc = paramJson.getDouble("fypjcxc");
		double sykhbfypxseydrwwclzb = paramJson.getDouble("sykhbfypxseydrwwclzb");
		double blc = paramJson.getDouble("blc");
		double ybspxse = paramJson.getDouble("ybspxse");
		double lsydmleydrwwclzb = paramJson.getDouble("lsydmleydrwwclzb");
		double fypxseydrwwcl = paramJson.getDouble("fypxseydrwwcl");
		double diesel0 = paramJson.getDouble("diesel0");
		double zpydmledywcl = paramJson.getDouble("zpydmledywcl");
		double ydlrydrwwcl = paramJson.getDouble("ydlrydrwwcl");
		double qtbt = paramJson.getDouble("qtbt");
		double trqdywcl = paramJson.getDouble("trqdywcl");
		double fypxseydrwwclzb = paramJson.getDouble("fypxseydrwwclzb");
		double lslydrwwcl = paramJson.getDouble("lslydrwwcl");
		double ydlrdywcl = paramJson.getDouble("ydlrdywcl");
		double fypydmledywcl = paramJson.getDouble("fypydmledywcl");
		double trqdyrw = paramJson.getDouble("trqdyrw");
		double wqclyxse = paramJson.getDouble("wqclyxse");
		String jyz_name = paramJson.getString("jyzName");
		double dyrgfbz = paramJson.getDouble("dyrgfbz");
		double zzfkzdcz = paramJson.getDouble("zzfkzdcz");
		double rybhjnjxse = paramJson.getDouble("rybhjnjxse");
		double cpxse = paramJson.getDouble("cpxse");
		double jsjykxc = paramJson.getDouble("jsjykxc");
		double xgsjybbzglgxxs = paramJson.getDouble("xgsjybbzglgxxs");
		double lsldyrw = paramJson.getDouble("lsldyrw");
		double czkcze = paramJson.getDouble("czkcze");
		double zcklbhjwkfy = paramJson.getDouble("zcklbhjwkfy");
		double xcfczkcze = paramJson.getDouble("xcfczkcze");
		double cklkzxssx = paramJson.getDouble("cklkzxssx");
		String fbk_type = paramJson.getString("fbkType");
		double fypmbwcjcje = paramJson.getDouble("fypmbwcjcje");
		double czkxse = paramJson.getDouble("czkxse");
		double trq = paramJson.getDouble("trq");
		double lsldywcl = paramJson.getDouble("lsldywcl");
		double qyzsxc = paramJson.getDouble("qyzsxc");
		double fdzxy = paramJson.getDouble("fdzxy");
		double fypydmleydrwwclzb = paramJson.getDouble("fypydmleydrwwclzb");
		double fypxsedyrw = paramJson.getDouble("fypxsedyrw");
		double zxjf = paramJson.getDouble("zxjf");
		double fj = paramJson.getDouble("fj");
		double zplydrwwclzb = paramJson.getDouble("zplydrwwclzb");
		double qjryxc = paramJson.getDouble("qjryxc");
		double lsydmleydrwwcl = paramJson.getDouble("lsydmleydrwwcl");
		double zykzjjddrygz = paramJson.getDouble("zykzjjddrygz");
		double lstykzgz = paramJson.getDouble("lstykzgz");
		double lslydrwwclzb = paramJson.getDouble("lslydrwwclzb");
		double ycbz = paramJson.getDouble("ycbz");
		double zhdfl = paramJson.getDouble("zhdfl");
		double qzkqckds = paramJson.getDouble("qzkqckds");
		double qcyhxse = paramJson.getDouble("qcyhxse");
		double tgxse = paramJson.getDouble("tgxse");
		double zpydmleydrwwcl = paramJson.getDouble("zpydmleydrwwcl");
		double xc = paramJson.getDouble("xc");
		double gdbc = paramJson.getDouble("gdbc");
		double gqxlxse = paramJson.getDouble("gqxlxse");
		double gasoline98 = paramJson.getDouble("gasoline98");
		double ydlrdyrw = paramJson.getDouble("ydlrdyrw");
		double lsydmledyrw = paramJson.getDouble("lsydmledyrw");
		double gasoline92 = paramJson.getDouble("gasoline92");
		double zyppblsxse = paramJson.getDouble("zyppblsxse");
		double olzxse = paramJson.getDouble("olzxse");
		double yylmxse = paramJson.getDouble("yylmxse");
		double gasoline95 = paramJson.getDouble("gasoline95");
		double rgfy = paramJson.getDouble("rgfy");

		examSummary.setId(summaryId);
		examSummary.setCompanyId(companyId);
		examSummary.setSykhbfypxsedywcl(sykhbfypxsedywcl);
		examSummary.setHj(hj);
		examSummary.setTrqydrwwclzb(trqydrwwclzb);
		examSummary.setZyppsxse(zyppsxse);
		examSummary.setBwCode(bw_code);
		examSummary.setDwsb(dddwsbd);
		examSummary.setFypxsedywcl(fypxsedywcl);
		examSummary.setWtcze(wtcze);
		examSummary.setJbxc(jbxc);
		examSummary.setCklkzxsxx(cklkzxsxx);
		examSummary.setGldf(gldf);
		examSummary.setFypydmledyrw(fypydmledyrw);
		examSummary.setDyxcbz(dyxcbz);
		examSummary.setJykdzxc(jykdzxc);
		examSummary.setSjlxj(sjlxj);
		examSummary.setSykhbfypxseydrwwcl(sykhbfypxseydrwwcl);
		examSummary.setFypydmleydrwwcl(fypydmleydrwwcl);
		examSummary.setZpydmledyrw(zpydmledyrw);
		examSummary.setYdlrydrwwclzb(ydlrydrwwclzb);
		examSummary.setSnyjckl(snyjckl);
		examSummary.setQyxc(qyxc);
		examSummary.setZpydmleydrwwclzb(zpydmleydrwwclzb);
		examSummary.setJbf(jbf);
		examSummary.setGlkf(glkf);
		examSummary.setYdczk(ydczk);
		examSummary.setLlggxs(llggxs);
		examSummary.setDiesel10(diesel10);
		examSummary.setRsdljgglf(rsdljgglf);
		examSummary.setZpldywcl(zpldywcl);
		examSummary.setZplydrwwcl(zplydrwwcl);
		examSummary.setJrfl(jrfl);
		examSummary.setStatus(status);
		examSummary.setJyzType(jyz_type);
		examSummary.setFbkName(fbk_name);
		examSummary.setLxxc(lxxc);
		examSummary.setDyfjbz(dyfjbz);
		examSummary.setTgxse(tgxse);
		examSummary.setBz(bz);
		examSummary.setLlxc(llxc);
		examSummary.setGlzbzb(glzbzb);
		examSummary.setLmjxse(lmjxse);
		examSummary.setSykhbfypxsedyrw(sykhbfypxsedyrw);
		examSummary.setLsydmledywcl(lsydmledywcl);
		examSummary.setTrqydrwwcl(trqydrwwcl);
		examSummary.setZpldyrw(zpldyrw);
		examSummary.setJykkhcze(jykkhcze);
		examSummary.setSjfj(sjfj);
		examSummary.setFypjcxc(fypjcxc);
		examSummary.setSykhbfypxseydrwwclzb(sykhbfypxseydrwwclzb);
		examSummary.setBlc(blc);
		examSummary.setYbspxse(ybspxse);
		examSummary.setLsydmleydrwwclzb(lsydmleydrwwclzb);
		examSummary.setFypxseydrwwcl(fypxseydrwwcl);
		examSummary.setDiesel0(diesel0);
		examSummary.setZpydmledywcl(zpydmledywcl);
		examSummary.setYdlrydrwwcl(ydlrydrwwcl);
		examSummary.setQtbt(ydlrydrwwcl);
		examSummary.setTrqdywcl(trqdywcl);
		examSummary.setFypxseydrwwclzb(fypxseydrwwclzb);
		examSummary.setLslydrwwcl(lslydrwwcl);
		examSummary.setYdlrdywcl(ydlrdywcl);
		examSummary.setFypydmledywcl(fypydmledywcl);
		examSummary.setTrqdyrw(trqdyrw);
		examSummary.setWqclyxse(wqclyxse);
		examSummary.setJyzName(jyz_name);
		examSummary.setDyrgfbz(dyrgfbz);
		examSummary.setZzfkzdcz(zzfkzdcz);
		examSummary.setRybhjnjxse(rybhjnjxse);
		examSummary.setCpxse(cpxse);
		examSummary.setJsjykxc(jsjykxc);
		examSummary.setXgsjybbzglgxxs(xgsjybbzglgxxs);
		examSummary.setLsldyrw(lsldyrw);
		examSummary.setCzkcze(czkcze);
		examSummary.setZcklbhjwkfy(zcklbhjwkfy);
		examSummary.setXcfczkcze(xcfczkcze);
		examSummary.setCklkzxssx(cklkzxssx);
		examSummary.setFbkType(fbk_type);
		examSummary.setFypmbwcjcje(fypmbwcjcje);
		examSummary.setCzkxse(czkxse);
		examSummary.setTrq(trq);
		examSummary.setLsldywcl(lsldywcl);
		examSummary.setQyzsxc(qyzsxc);
		examSummary.setFdzxy(fdzxy);
		examSummary.setFypydmleydrwwclzb(fypydmleydrwwclzb);
		examSummary.setFypxsedyrw(fypxsedyrw);
		examSummary.setZxjf(zxjf);
		examSummary.setFj(fj);
		examSummary.setZplydrwwclzb(zplydrwwclzb);
		examSummary.setQjryxc(qjryxc);
		examSummary.setLsydmleydrwwcl(lsydmleydrwwcl);
		examSummary.setZykzjjddrygz(zykzjjddrygz);
		examSummary.setLstykzgz(lstykzgz);
		examSummary.setDwName(dw_name);
		examSummary.setLslydrwwclzb(lslydrwwclzb);
		examSummary.setYcbz(ycbz);
		examSummary.setBnName(bn_name);
		examSummary.setZhdfl(zhdfl);
		examSummary.setQzkqckds(qzkqckds);
		examSummary.setQcyhxse(qcyhxse);
		examSummary.setTgxse(tgxse);
		examSummary.setZpydmleydrwwcl(zpydmleydrwwcl);
		examSummary.setXc(xc);
		examSummary.setGdbc(gdbc);
		examSummary.setGqxlxse(gqxlxse);
		examSummary.setGasoline98(gasoline98);
		examSummary.setYearMonthDate(year_month_date);
		examSummary.setYdlrdyrw(ydlrdyrw);
		examSummary.setLsydmledyrw(lsydmledyrw);
		examSummary.setGasoline92(gasoline92);
		examSummary.setZyppblsxse(zyppblsxse);
		examSummary.setOlzxse(olzxse);
		examSummary.setYylmxse(yylmxse);
		examSummary.setGasoline95(gasoline95);
		examSummary.setRgfy(rgfy);

		return examSummary;
	}

	/**
	* @Title: exportJyzkhtzdmanage  
	* @Description: 导出考核汇总信息  
	* @param @param request
	* @param @param response    参数  
	* @return void    返回类型  
	* @throws
	 */
	@RequestMapping("/exportExamSummary")
	@ResponseBody
	public void exportExamSummary(HttpServletRequest request, HttpServletResponse response) {
		String xbmName = null;
		String wtgsName = null;
		String jyzName = null;
		String yearMonthDate = null;
		String status = null;

		Map<String, Object> params = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		if (session == null) {
			return;
		}
		try {
			String companyPath = request.getParameter("companyPath");
			if (StringUtils.isEmpty(companyPath)) {
				Company company = (Company) session.getAttribute(SystemConst.ACCOUNT_AGENCY);
				companyPath = company.getCompanyPath();
			}
			status = request.getParameter("status");
			xbmName = request.getParameter("xbmName");
			wtgsName = request.getParameter("wtgsName");
			jyzName = request.getParameter("jyzName");
			xbmName = java.net.URLDecoder.decode(xbmName, "UTF-8");
			wtgsName = java.net.URLDecoder.decode(wtgsName, "UTF-8");
			jyzName = java.net.URLDecoder.decode(jyzName, "UTF-8");

			yearMonthDate = request.getParameter("yearMonthDate");
			yearMonthDate = java.net.URLDecoder.decode(yearMonthDate, "UTF-8");
			params.put("companyPath", companyPath);
			params.put("status", status);
			params.put("xbmName", xbmName);
			params.put("wtgsName", wtgsName);
			params.put("jyzName", jyzName);
			params.put("yearMonthDate", yearMonthDate);

			examEummaryService.exportExamSummary(params, response);
			// 写入日志
			int userid = Integer.parseInt(session.getAttribute("userid").toString());
			String ip = RequestRealIp.getIpAddress(request);
			Date nowDate = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			iloggerService.writeLog(userid, sdf.format(nowDate), 10401, ip, "加油站考核通知单报表导出");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}