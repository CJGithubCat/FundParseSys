package com.zsh.labouCapital.util.excel;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.alibaba.druid.util.StringUtils;
import com.zsh.labouCapital.entity.Company;
import com.zsh.labouCapital.entity.ExamSummary;
import com.zsh.labouCapital.entity.Salary;
import com.zsh.labouCapital.service.ICompanyService;
import com.zsh.labouCapital.service.IExamEummaryService;
import com.zsh.labouCapital.service.ISalaryService;

/**
 * Copyright:   Copyright(C) 2018
 * author:      chenjie
 * todo:读取Excel文本内容
 * Createdate:  2018年7月17日下午4:49:18
 */
public class ReadExcelUtil {
	// 可以优化为从配置文件读取
	public static String[] EXAM_SUMMARY_EXL_HEADE = new String[] { "序号", "日期", "分版块", "单位", "BW代码", "部门", "加油站",
			"加油站分类", "92#汽油", "95#汽油", "98#汽油", "0#柴油", "10#号柴油", "天然气（方）", "实际量小计", "吨油薪酬标准", "薪酬", "吨油附加标准", "附加",
			"吨油人工费标准", "人工费用", "实际附加", "单位社保", "节日福利", "日常劳保", "用餐补助", "人事代理机构管理费", "轻油薪酬（含加班费及在站补助）", "现场非充值卡充值额",
			"充值卡充值额", "充值卡销售额", "网厅充值额", "自助发卡终端充值", "加油卡考核充值额（元）", "计算加油卡薪酬", "非定制香烟（零售）销售额（元）",
			"一般商品（含酒类/饮料/食品/百货/音像图书）销售额（元）", "汽车用品/润滑油/农资/汽车养护销售额（元）", "移动充值卡/彩票销售额（元）", "异业联盟（ETC)销售额（元）",
			"团购（所有商品）销售额（元）", "燃油宝和降凝剂销售额（元）", "自有品牌水销售额（元）", "尾气处理液销售额（元）", "赖茅酒销售额（元）", "自有品牌玻璃水销售额（元）",
			"欧露纸/定制香烟销售额（元）", "枸杞系列销售额（元）", "彩票销售额（元）", "非油品基础薪酬", "非油品目标完成情况奖惩", "轻油折算薪酬", "加油卡调整薪酬", "零售量当月任务",
			"零售量当月完成量", "零售量月度任务完成率", "零售量月度任务完成率占比", "天然气当月任务", "天然气当月完成量", "天然气月度任务完成率", "天然气月度任务完成率占比",
			"零售月度毛利额当月任务", "零售月度毛利额当月完成量", "零售月度毛利额月度任务完成率", "零售月度毛利额月度任务完成率占比", "非油品销售额当月任务", "非油品销售额当月完成量",
			"非油品销售额月度任务完成率", "非油品销售额月度任务完成率占比", "商业客户部非油品销售额当月任务", "商业客户部非油品销售额当月完成量", "商业客户部非油品销售额月度任务完成率",
			"商业客户部非油品销售额月度任务完成率占比", "非油品月度毛利额当月任务", "非油品月度毛利额当月完成量", "非油品月度毛利额月度任务完成率", "非油品月度毛利额月度任务完成率占比", "直批量当月任务",
			"直批量当月完成量", "直批量月度任务完成率", "直批量月度任务完成率占比", "直批月度毛利额当月任务", "直批月度毛利额当月完成量", "直批月度毛利额月度任务完成率",
			"直批月度毛利额月度任务完成率占比", "月度利润当月任务", "月度利润当月完成量", "月度利润月度任务完成率", "月度利润月度任务完成率占比", "上年月均出库量", "总出库量不含寄外库发油",
			"其中跨区出库吨数", "出库量控制系数下限", "出库量控制系数上限", "管理得分", "管理指标占比", "县公司经营部班子管理贡献系数", "综合得分率", "联量挂钩系数", "基本薪酬", "联量薪酬",
			"整月看站及借调等人员工资", "加班费（看站及其他特定非专项奖励加班费）", "请假人员薪酬", "过渡补差", "保留差", "其他补贴", "临时停业看站工资", "专项奖罚", "管理扣罚", "劳效薪酬",
			"合计：薪酬性费用", "备注", "轻油超目标薪酬" };
	// 工资表单头
	public static String[] SALARY_EXL_HEADE = new String[] { "序号", "单位", "姓名", "身份证号", "岗位", "基本薪酬", "联量薪酬", "非油品薪酬",
			"加油卡薪酬", "加班费", "过渡补差", "其他工资", "专项奖金", "专项扣罚", "其他奖罚", "其他津贴补助等", "应发合计", "公积金", "养老金", "失业金", "医保金", "年金",
			"个人所得税", "实发工资", "帐号", "签名", "新开户日期（换卡必填）", "借支", "卫生费", "房费", "电费", "暖气费", "打卡金额", "身份证号", "已发需要合并扣税项合计",
			"公积金单位每月缴费", "养老单位每月缴费", "失业单位每月缴费", "工伤单位每月缴费", "医保单位每月缴费", "生育保险单位每月缴费", "大病保险单位每月缴费", "年金企业缴费每月划入个人账户金额",
			"年金企业缴费", "管理费", "委托公司名称", "社保参保地" };

	/**
	 * @Title: checkXlsHeadInfo
	 * @Description: 校验上传文件头信息 顺序和名字必须一致
	 * @param @return
	 *            参数 @return Map<String,Object> 返回类型 @throws
	 */
	public static Map<String, Object> checkXlsHeadInfo(String[] headModule, Row headRow) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (headModule == null || headModule.length <= 0 || headRow == null || headModule.length <= 0) {
			map.put("success", false);
			map.put("reason", "[checkXlsHeadInfo]:ERR：params is null!");
			return map;
		}
		if (headModule.length != headRow.getPhysicalNumberOfCells()) {
			map.put("success", false);
			map.put("reason", "[checkXlsHeadInfo]:ERR：模板列数和excel列数不一致!模板长度:" + headModule.length + "  文件头长度："
					+ headRow.getPhysicalNumberOfCells());
			return map;
		}
		int len = headModule.length;
		for (int i = 0; i < len; i++) {
			headRow.getCell(i);// 获取第i列
			if (!headModule[i].equals(getCellValue(headRow.getCell(i)))) {
				map.put("success", false);
				map.put("reason", "[checkXlsHeadInfo]:ERR：模板第" + (i + 1) + "列和excel表头不一致,模板:" + headModule[i] + "  列头:"
						+ getCellValue(headRow.getCell(i)));
				return map;
			}
		}
		map.put("success", true);
		return map;
	}

	/*
	 * 函数功能:读取考核明细
	 */
	public static Map<String, Object> readCheckExamSummaryExcel(String path, IExamEummaryService examEummaryService) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.isEmpty(path)) {
			map.put("success", false);
			map.put("reason", "ERR：path is null!");
			return map;
		}
		
		int startRow = 2;
		Workbook workbook = null;
		InputStream is = null;

		try {
			is = new FileInputStream(path);
			String extString = path.substring(path.lastIndexOf("."));
			if(".xls".equals(extString)){
				workbook = new HSSFWorkbook(is);
            }else if(".xlsx".equals(extString)){
            	workbook = new XSSFWorkbook(is);
            }else{
            	map.put("success", false);
    			map.put("reason", "ERR：the file type error!");
    			return map;
            }
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("reason", "excel文件解析失败,请联系客服人员！");
			return map;
		}
		
		// 存儲解析讀取到的數據
		List<ExamSummary> list = new ArrayList<ExamSummary>();
		// 循环工作表Sheet
		for (int numSheet = 0; numSheet < workbook.getNumberOfSheets(); numSheet++) {
			Sheet hssfSheet = workbook.getSheetAt(numSheet);
			if (hssfSheet == null) {
				continue;
			}
			// 1.校验第一行表头信息
			Row hssfRow0 = hssfSheet.getRow(startRow);
			if (hssfRow0 == null) {
				break;
			}
			Map<String, Object> reMap = checkXlsHeadInfo(EXAM_SUMMARY_EXL_HEADE, hssfRow0);
			if ((boolean) reMap.get("success") == false) {
				return reMap;
			}

			String dateStr = null;
			// 2.循环读取行Row中的数据
			try {
				for (int rowNum = startRow + 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
					int f = hssfSheet.getLastRowNum();
					Row hssfRow = hssfSheet.getRow(rowNum);
					int sheet = numSheet + 1;
					int row = rowNum + 1;
					if (hssfRow != null) {

						if (checkIsBlankRow(hssfRow)) {
							System.out.println("数据错误！第[" + rowNum + "行是空行!");
							break;
						}
						ExamSummary examSummary = new ExamSummary();
						// 读取每一列数据
						int index = 0;
						Cell xuhao = hssfRow.getCell(index);// 序号
						index = index + 1;

						// 日期
						Cell year_month_date = hssfRow.getCell(index);// 日期
						if (getCellValue(year_month_date) == null || getCellValue(year_month_date).equals("")) {// 日期
							map.put("success", false);
							map.put("reason", "数据错误！第[" + rowNum + "行,第" + index + "列]的日期数据为空!");
							return map;
						} else {
							String cellDateStr = getCellValue(year_month_date) + "";
							if (dateStr == null) {// 第一次
								dateStr = cellDateStr;
							} else {
								if (!dateStr.equals(cellDateStr)) {
									map.put("success", false);
									map.put("reason", "数据错误！第[" + rowNum + "行,第" + index + "列]的日期数据" + cellDateStr
											+ "不一致，请检查数据!");
									return map;
								}
							}
							index = index + 1;
							if(cellDateStr.contains("年") && cellDateStr.contains("月")){
								String year = cellDateStr.substring(0,cellDateStr.indexOf("年"));
								String month = cellDateStr.substring(cellDateStr.indexOf("年")+1).replace("月", "").replace("份", "");
								if(month.length() == 1){
									month = "0" + month;
								}
								String yearMonth = year+month;
								examSummary.setYearMonthDate(yearMonth);
							}else{
								examSummary.setYearMonthDate(cellDateStr.substring(0, 6));
							}

							// 查询是否有这个日期的记录已经上传了，如果上传了就返回，防止重复上传
							int count = examEummaryService.countExamSummaryByDate(cellDateStr);
							if (count > 0) {
								map.put("success", false);
								map.put("reason", "该日期[" + cellDateStr + " ]数据已经上传，请勿重复上传!");
								return map;
							}
						}

						// 分版块
						Cell fbk_name = hssfRow.getCell(index);// 分版块
						if (StringUtils.isEmpty(getCellValue(fbk_name))) {// 分版块
							map.put("success", false);
							map.put("reason", "数据错误！第[" + rowNum + "行,第" + index + "列]的分版块数据为空!");
							return map;
						}
						index = index + 1;
						examSummary.setFbkName(getCellValue(fbk_name));

						// 单位名
						Cell dw_name = hssfRow.getCell(index);
						if (StringUtils.isEmpty(getCellValue(dw_name))) {// 分版块
							map.put("success", false);
							map.put("reason", "数据错误！第[" + rowNum + "行,第" + index + "列]的[单位名]数据为空!");
							return map;
						}
						index = index + 1;
						examSummary.setDwName(getCellValue(dw_name));
						// BW代码
						Cell bw_code = hssfRow.getCell(index);
						if (bw_code.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(bw_code))) {// 分版块
								map.put("success", false);
								map.put("reason", "数据错误！第[" + rowNum + "行,第" + index + "列]的[BW代码]数据为空!");
								return map;
							}
							examSummary.setBwCode(getCellValue(bw_code));
						}
						if (bw_code.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							bw_code.setCellType(Cell.CELL_TYPE_STRING);
							System.out.println(bw_code.getStringCellValue());
							examSummary.setBwCode(bw_code.getStringCellValue() + "");
						}
						index = index + 1;
						// 部门
						Cell bn_name = hssfRow.getCell(index);
						if (StringUtils.isEmpty(getCellValue(bn_name))) {// 分版块
							map.put("success", false);
							map.put("reason", "数据错误！第[" + rowNum + "行,第" + index + "列]的[部门名称]数据为空!");
							return map;
						}
						index = index + 1;
						examSummary.setBnName(getCellValue(bn_name));
						// 加油站
						Cell jyz_name = hssfRow.getCell(index);
						/*
						 * if(StringUtils.isEmpty(jyz_name.getCellValue())
						 * ){//分版块 map.put("success", false); map.put("reason",
						 * "数据错误！第["+rowNum+"行,第"+index + "列]的[加油站名称]数据为空!");
						 * return map; }
						 */
						index = index + 1;
						examSummary.setJyzName(getCellValue(jyz_name));
						// 加油站分类
						Cell jyz_type = hssfRow.getCell(index);
						if (StringUtils.isEmpty(getCellValue(jyz_type))) {// 分版块
							map.put("success", false);
							map.put("reason", "数据错误！第[" + rowNum + "行,第" + index + "列]的[加油站分类]数据为空!");
							return map;
						}
						index = index + 1;
						examSummary.setJyzType(getCellValue(jyz_type));
						// 92号汽油 -- <可为空>
						Cell gasoline92 = hssfRow.getCell(index);
						if (gasoline92.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(gasoline92))) {// 为空
								examSummary.setGasoline92(0);
							} else {
								examSummary.setGasoline92(Double.parseDouble(getCellValue(gasoline92)));
							}
						}
						if (gasoline92.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setGasoline92(gasoline92.getNumericCellValue());
						}
						index = index + 1;
						// 95号汽油-- <可为空>
						Cell gasoline95 = hssfRow.getCell(index);
						if (gasoline95.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(gasoline95))) {// 为空
								examSummary.setGasoline95(0);
							} else {
								examSummary.setGasoline95(Double.parseDouble(getCellValue(gasoline95)));
							}
						}
						if (gasoline95.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setGasoline95(gasoline95.getNumericCellValue());
						}
						index = index + 1;
						// 98号汽油 -- <可为空>
						Cell gasoline98 = hssfRow.getCell(index);
						if (gasoline98.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(gasoline98))) {// 为空
								examSummary.setGasoline98(0);
							} else {
								examSummary.setGasoline98(Double.parseDouble(getCellValue(gasoline98)));
							}
						}
						if (gasoline98.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setGasoline98(gasoline98.getNumericCellValue());
						}
						index = index + 1;
						// 0号柴油 -- <可为空>
						Cell diesel0 = hssfRow.getCell(index);
						if (diesel0.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(diesel0))) {// 为空
								examSummary.setDiesel0(0);
							} else {
								examSummary.setDiesel0(Double.parseDouble(getCellValue(diesel0)));
							}
						}
						if (diesel0.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setDiesel0(diesel0.getNumericCellValue());
						}
						index = index + 1;
						// 10号柴油 -- <可为空>
						Cell diesel10 = hssfRow.getCell(index);
						if (diesel10.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(diesel10))) {// 为空
								examSummary.setDiesel10(0);
							} else {
								examSummary.setDiesel10(Double.parseDouble(getCellValue(diesel10)));
							}
						}
						if (diesel10.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setDiesel10(diesel10.getNumericCellValue());
						}
						index = index + 1;
						// 天然气 -- <可为空>
						Cell trq = hssfRow.getCell(index);
						if (trq.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(trq))) {// 为空
								examSummary.setTrq(0);
							} else {
								examSummary.setTrq(Double.parseDouble(getCellValue(trq)));
							}
						}
						if (trq.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setTrq(trq.getNumericCellValue());
						}
						index = index + 1;
						// 实际量小计 -- <可为空>
						Cell sjlxj = hssfRow.getCell(index);
						if (sjlxj.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(sjlxj))) {// 为空
								examSummary.setSjlxj(0);
							} else {
								examSummary.setSjlxj(Double.parseDouble(getCellValue(sjlxj)));
							}
						}
						if (sjlxj.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setSjlxj(sjlxj.getNumericCellValue());
						}
						index = index + 1;
						// 吨油薪酬标准 --<可为空>
						Cell dyxcbz = hssfRow.getCell(index);
						if (dyxcbz.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(dyxcbz))) {// 为空
								examSummary.setDyxcbz(0);
							} else {
								examSummary.setDyxcbz(Double.parseDouble(getCellValue(dyxcbz)));
							}
						}
						if (dyxcbz.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setDyxcbz(dyxcbz.getNumericCellValue());
						}
						index = index + 1;
						// 薪酬
						Cell xc = hssfRow.getCell(index);
						if (xc.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(xc))) {// 为空
								examSummary.setXc(0);
							} else {
								examSummary.setXc(Double.parseDouble(getCellValue(xc)));
							}
						}
						if (xc.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setXc(xc.getNumericCellValue());
						}
						index = index + 1;
						// 吨油附加标准
						Cell dyfjbz = hssfRow.getCell(index);
						if (dyfjbz.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(dyfjbz))) {// 为空
								examSummary.setDyfjbz(0);
							} else {
								examSummary.setDyfjbz(Double.parseDouble(getCellValue(dyfjbz)));
							}
						}
						if (dyfjbz.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setDyfjbz(dyfjbz.getNumericCellValue());
						}
						index = index + 1;
						// 附加
						Cell fj = hssfRow.getCell(index);
						if (fj.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(fj))) {// 为空
								examSummary.setFj(0);
							} else {
								examSummary.setFj(Double.parseDouble(getCellValue(fj)));
							}
						}
						if (fj.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setFj(fj.getNumericCellValue());
						}
						index = index + 1;
						// 吨油人工费标准
						Cell dyrgfbz = hssfRow.getCell(index);
						if (dyrgfbz.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(dyrgfbz))) {// 为空
								examSummary.setDyrgfbz(0);
							} else {
								examSummary.setDyrgfbz(Double.parseDouble(getCellValue(dyrgfbz)));
							}
						}
						if (dyrgfbz.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setDyrgfbz(dyrgfbz.getNumericCellValue());
						}
						index = index + 1;
						// 人工费用
						Cell rgfy = hssfRow.getCell(index);
						if (rgfy.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(rgfy))) {// 为空
								examSummary.setDyrgfbz(0);
							} else {
								examSummary.setDyrgfbz(Double.parseDouble(getCellValue(rgfy)));
							}
						}
						if (rgfy.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setDyrgfbz(rgfy.getNumericCellValue());
						}
						index = index + 1;
						// 实际附加
						Cell sjfj = hssfRow.getCell(index);
						if (sjfj.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(sjfj))) {// 为空
								examSummary.setSjfj(0);
							} else {
								examSummary.setSjfj(Double.parseDouble(getCellValue(sjfj)));
							}
						}
						if (sjfj.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setSjfj(sjfj.getNumericCellValue());
						}
						index = index + 1;
						// 单位社保
						Cell dwsb = hssfRow.getCell(index);
						if (dwsb.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(dwsb))) {// 为空
								examSummary.setDwsb(0);
							} else {
								examSummary.setDwsb(Double.parseDouble(getCellValue(dwsb)));
							}
						}
						if (dwsb.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setDwsb(dwsb.getNumericCellValue());
						}
						index = index + 1;
						// 节日福利
						Cell jrfl = hssfRow.getCell(index);
						if (jrfl.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(jrfl))) {// 为空
								examSummary.setJrfl(0);
							} else {
								examSummary.setJrfl(Double.parseDouble(getCellValue(jrfl)));
							}
						}
						if (jrfl.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setJrfl(jrfl.getNumericCellValue());
						}
						index = index + 1;
						// 日常劳保
						Cell rclb = hssfRow.getCell(index);
						if (rclb.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(rclb))) {// 为空
								examSummary.setRclb(0);
							} else {
								examSummary.setRclb(Double.parseDouble(getCellValue(rclb)));
							}
						}
						if (rclb.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setRclb(rclb.getNumericCellValue());
						}
						index = index + 1;
						// 用餐补助
						Cell ycbz = hssfRow.getCell(index);
						if (ycbz.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(ycbz))) {// 为空
								examSummary.setYcbz(0);
							} else {
								examSummary.setYcbz(Double.parseDouble(getCellValue(ycbz)));
							}
						}
						if (ycbz.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setYcbz(ycbz.getNumericCellValue());
						}
						index = index + 1;
						// 人事代理机构管理费
						Cell rsdljgglf = hssfRow.getCell(index);
						if (rsdljgglf.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(rsdljgglf))) {// 为空
								examSummary.setRsdljgglf(0);
							} else {
								examSummary.setDyrgfbz(Double.parseDouble(getCellValue(rsdljgglf)));
							}
						}
						if (rsdljgglf.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setDyrgfbz(rsdljgglf.getNumericCellValue());
						}
						index = index + 1;
						// 轻油薪酬（含加班费及在站补助）
						Cell qyxc = hssfRow.getCell(index);
						if (qyxc.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(qyxc))) {// 为空
								examSummary.setQyxc(0);
							} else {
								examSummary.setQyxc(Double.parseDouble(getCellValue(qyxc)));
							}
						}
						if (qyxc.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setQyxc(qyxc.getNumericCellValue());
						}
						index = index + 1;
						// 现场非充值卡充值额
						Cell xcfczkcze = hssfRow.getCell(index);
						if (xcfczkcze.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(xcfczkcze))) {// 为空
								examSummary.setXcfczkcze(0);
							} else {
								examSummary.setXcfczkcze(Double.parseDouble(getCellValue(xcfczkcze)));
							}
						}
						if (xcfczkcze.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setXcfczkcze(xcfczkcze.getNumericCellValue());
						}
						index = index + 1;
						// 充值卡充值额
						Cell czkcze = hssfRow.getCell(index);
						if (czkcze.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(czkcze))) {// 为空
								examSummary.setCzkcze(0);
							} else {
								examSummary.setCzkcze(Double.parseDouble(getCellValue(czkcze)));
							}
						}
						if (czkcze.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setCzkcze(czkcze.getNumericCellValue());
						}
						index = index + 1;
						// 移动充值卡/彩票销售额（元）
						Cell czkxse = hssfRow.getCell(index);
						if (czkxse.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(czkxse))) {// 为空
								examSummary.setCzkxse(0);
							} else {
								examSummary.setCzkxse(Double.parseDouble(getCellValue(czkxse)));
							}
						}
						if (czkxse.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setCzkxse(czkxse.getNumericCellValue());
						}
						index = index + 1;
						// 网厅充值额
						Cell wtcze = hssfRow.getCell(index);
						if (wtcze.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(wtcze))) {// 为空
								examSummary.setWtcze(0);
							} else {
								examSummary.setWtcze(Double.parseDouble(getCellValue(wtcze)));
							}
						}
						if (wtcze.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setWtcze(wtcze.getNumericCellValue());
						}
						index = index + 1;
						// 自助发卡终端充值
						Cell zzfkzdcz = hssfRow.getCell(index);
						if (zzfkzdcz.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(zzfkzdcz))) {// 为空
								examSummary.setZzfkzdcz(0);
							} else {
								examSummary.setZzfkzdcz(Double.parseDouble(getCellValue(zzfkzdcz)));
							}
						}
						if (zzfkzdcz.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setZzfkzdcz(zzfkzdcz.getNumericCellValue());
						}
						index = index + 1;
						// 加油卡考核充值额（元）
						Cell jykkhcze = hssfRow.getCell(index);
						if (jykkhcze.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(jykkhcze))) {// 为空
								examSummary.setJykkhcze(0);
							} else {
								examSummary.setJykkhcze(Double.parseDouble(getCellValue(jykkhcze)));
							}
						}
						if (jykkhcze.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setJykkhcze(jykkhcze.getNumericCellValue());
						}
						index = index + 1;
						// 计算加油卡薪酬
						Cell jsjykxc = hssfRow.getCell(index);
						if (jsjykxc.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(jsjykxc))) {// 为空
								examSummary.setJsjykxc(0);
							} else {
								examSummary.setJsjykxc(Double.parseDouble(getCellValue(jsjykxc)));
							}
						}
						if (jsjykxc.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setJsjykxc(jsjykxc.getNumericCellValue());
						}
						index = index + 1;
						// 非易捷之风系列（零售）销售额（元）
						Cell fdzxy = hssfRow.getCell(index);
						if (fdzxy.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(fdzxy))) {// 为空
								examSummary.setFdzxy(0);
							} else {
								examSummary.setFdzxy(Double.parseDouble(getCellValue(fdzxy)));
							}
						}
						if (fdzxy.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setFdzxy(fdzxy.getNumericCellValue());
						}
						index = index + 1;
						// 一般商品（含酒类/饮料/食品/百货/音像图书）销售额（元）
						Cell ybspxse = hssfRow.getCell(index);
						if (ybspxse.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(ybspxse))) {// 为空
								examSummary.setYbspxse(0);
							} else {
								examSummary.setYbspxse(Double.parseDouble(getCellValue(ybspxse)));
							}
						}
						if (ybspxse.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setYbspxse(ybspxse.getNumericCellValue());
						}
						index = index + 1;
						// 汽车用品/润滑油/农资/汽车养护销售额（元）
						Cell rybhjnjxse = hssfRow.getCell(index);
						if (rybhjnjxse.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(rybhjnjxse))) {// 为空
								examSummary.setRybhjnjxse(0);
							} else {
								examSummary.setRybhjnjxse(Double.parseDouble(getCellValue(rybhjnjxse)));
							}
						}
						if (rybhjnjxse.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setRybhjnjxse(rybhjnjxse.getNumericCellValue());
						}
						index = index + 1;
						// 自品牌水销售额（元）
						Cell zyppsxse = hssfRow.getCell(index);
						if (zyppsxse.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(zyppsxse))) {// 为空
								examSummary.setZyppsxse(0);
							} else {
								examSummary.setZyppsxse(Double.parseDouble(getCellValue(zyppsxse)));
							}
						}
						if (zyppsxse.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setZyppsxse(zyppsxse.getNumericCellValue());
						}
						index = index + 1;
						// 尾气处理液销售额（元）
						Cell wqclyxse = hssfRow.getCell(index);
						if (wqclyxse.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(wqclyxse))) {// 为空
								examSummary.setWqclyxse(0);
							} else {
								examSummary.setWqclyxse(Double.parseDouble(getCellValue(wqclyxse)));
							}
						}
						if (wqclyxse.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setWqclyxse(wqclyxse.getNumericCellValue());
						}
						index = index + 1;
						// 赖茅酒销售额（元）
						Cell lmjxse = hssfRow.getCell(index);
						if (lmjxse.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(lmjxse))) {// 为空
								examSummary.setLmjxse(0);
							} else {
								examSummary.setLmjxse(Double.parseDouble(getCellValue(lmjxse)));
							}
						}
						if (lmjxse.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setLmjxse(lmjxse.getNumericCellValue());
						}
						index = index + 1;
						// 自有品牌玻璃水销售额（元）
						Cell zyppblsxse = hssfRow.getCell(index);
						if (zyppblsxse.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(zyppblsxse))) {// 为空
								examSummary.setZyppblsxse(0);
							} else {
								examSummary.setZyppblsxse(Double.parseDouble(getCellValue(zyppblsxse)));
							}
						}
						if (zyppblsxse.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setZyppblsxse(zyppblsxse.getNumericCellValue());
						}
						index = index + 1;
						// 欧露纸/定制香烟销售额（元）
						Cell olzxse = hssfRow.getCell(index);
						if (olzxse.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(olzxse))) {// 为空
								examSummary.setOlzxse(0);
							} else {
								examSummary.setOlzxse(Double.parseDouble(getCellValue(olzxse)));
							}
						}
						if (olzxse.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setOlzxse(olzxse.getNumericCellValue());
						}
						index = index + 1;
						// 枸杞系列销售额（元）
						Cell gqxlxse = hssfRow.getCell(index);
						if (gqxlxse.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(gqxlxse))) {// 为空
								examSummary.setGqxlxse(0);
							} else {
								examSummary.setGqxlxse(Double.parseDouble(getCellValue(gqxlxse)));
							}
						}
						if (gqxlxse.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setGqxlxse(gqxlxse.getNumericCellValue());
						}
						index = index + 1;
						// 充值卡销售额（元）
						Cell ydczk = hssfRow.getCell(index);
						if (ydczk.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(ydczk))) {// 为空
								examSummary.setYdczk(0);
							} else {
								examSummary.setYdczk(Double.parseDouble(getCellValue(ydczk)));
							}
						}
						if (ydczk.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setFypmbwcjcje(ydczk.getNumericCellValue());
							examSummary.setBeginfypmbwcjcje(ydczk.getNumericCellValue());
						}
						index = index + 1;
						// 彩票销售额（元）
						Cell cpxse = hssfRow.getCell(index);
						if (cpxse.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(cpxse))) {// 为空
								examSummary.setCpxse(0);
							} else {
								examSummary.setCpxse(Double.parseDouble(getCellValue(cpxse)));
							}
						}
						if (cpxse.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setCpxse(cpxse.getNumericCellValue());
						}
						index = index + 1;
						// 异业联盟（ETC)销售额（元）
						Cell yylmxse = hssfRow.getCell(index);
						if (yylmxse.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(yylmxse))) {// 为空
								examSummary.setYylmxse(0);
							} else {
								examSummary.setYylmxse(Double.parseDouble(getCellValue(yylmxse)));
							}
						}
						if (yylmxse.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setYylmxse(yylmxse.getNumericCellValue());
						}
						index = index + 1;
						// 汽车用品/润滑油/农资/汽车养护销售额（元）
						Cell qcyhxse = hssfRow.getCell(index);
						if (qcyhxse.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(qcyhxse))) {// 为空
								examSummary.setQcyhxse(0);
							} else {
								examSummary.setQcyhxse(Double.parseDouble(getCellValue(qcyhxse)));
							}
						}
						if (qcyhxse.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setQcyhxse(qcyhxse.getNumericCellValue());
						}
						index = index + 1;
						// 团购（所有商品）销售额（元）
						Cell tgxse = hssfRow.getCell(index);
						if (tgxse.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(tgxse))) {// 为空
								examSummary.setTgxse(0);
							} else {
								examSummary.setTgxse(Double.parseDouble(getCellValue(tgxse)));
							}
						}
						if (tgxse.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setTgxse(tgxse.getNumericCellValue());
						}
						index = index + 1;
						// 非油基础薪酬
						Cell fypjcxc = hssfRow.getCell(index);
						if (fypjcxc.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(fypjcxc))) {// 为空
								examSummary.setFypjcxc(0);
							} else {
								examSummary.setFypjcxc(Double.parseDouble(getCellValue(fypjcxc)));
							}
						}
						if (fypjcxc.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setFypjcxc(fypjcxc.getNumericCellValue());
						}
						index = index + 1;
						// 非油品目标完成奖惩金额
						Cell fypmbwcjcje = hssfRow.getCell(index);
						if (fypmbwcjcje.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(fypmbwcjcje))) {// 为空
								examSummary.setFypmbwcjcje(0);
							} else {
								examSummary.setFypmbwcjcje(Double.parseDouble(getCellValue(fypmbwcjcje)));
							}
						}
						if (fypmbwcjcje.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setFypmbwcjcje(fypmbwcjcje.getNumericCellValue());
						}
						index = index + 1;
						// 轻油折算薪酬
						Cell qyzsxc = hssfRow.getCell(index);
						if (qyzsxc.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(qyzsxc))) {// 为空
								examSummary.setQyzsxc(0);
								examSummary.setBeginqyzsxc(0);
							} else {
								examSummary.setQyzsxc(Double.parseDouble(getCellValue(qyzsxc)));
								examSummary.setBeginqyzsxc(Double.parseDouble(getCellValue(qyzsxc)));
							}
						}
						if (qyzsxc.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setQyzsxc(qyzsxc.getNumericCellValue());
						}
						index = index + 1;
						// 加油卡调整薪酬
						Cell jykdzxc = hssfRow.getCell(index);
						if (jykdzxc.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(jykdzxc))) {// 为空
								examSummary.setJykdzxc(0);
								examSummary.setBeginjykdzxc(0);
							} else {
								examSummary.setJykdzxc(Double.parseDouble(getCellValue(jykdzxc)));
								examSummary.setBeginjykdzxc(Double.parseDouble(getCellValue(jykdzxc)));
							}
						}
						if (jykdzxc.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setJykdzxc(jykdzxc.getNumericCellValue());
						}
						index = index + 1;
						// 零售量当月任务
						Cell lsldyrw = hssfRow.getCell(index);
						if (lsldyrw.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(lsldyrw))) {// 为空
								examSummary.setLsldyrw(0);
							} else {
								examSummary.setLsldyrw(Double.parseDouble(getCellValue(lsldyrw)));
							}
						}
						if (lsldyrw.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setLsldyrw(lsldyrw.getNumericCellValue());
						}

						index = index + 1;
						// 零售量当月完成量
						Cell lsldywcl = hssfRow.getCell(index);
						if (lsldywcl.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(lsldywcl))) {// 为空
								examSummary.setLsldywcl(0);
							} else {
								examSummary.setLsldywcl(Double.parseDouble(getCellValue(lsldywcl)));
							}
						}
						if (lsldywcl.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setLsldywcl(lsldywcl.getNumericCellValue());
						}
						index = index + 1;
						// 零售量月度任务完成率
						Cell lslydrwwcl = hssfRow.getCell(index);
						if (lslydrwwcl.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(lslydrwwcl))) {// 为空
								examSummary.setLslydrwwcl(0);
							} else {
								examSummary.setLslydrwwcl(Double.parseDouble(getCellValue(lslydrwwcl)));
							}
						}
						if (lslydrwwcl.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setLslydrwwcl(lslydrwwcl.getNumericCellValue());
						}
						index = index + 1;
						// 零售量月度任务完成率占比
						Cell lslydrwwclzb = hssfRow.getCell(index);
						if (lslydrwwclzb.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(lslydrwwclzb))) {// 为空
								examSummary.setLslydrwwclzb(0);
							} else {
								examSummary.setLslydrwwclzb(Double.parseDouble(getCellValue(lslydrwwclzb)));
							}
						}
						if (lslydrwwclzb.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setLslydrwwclzb(lslydrwwclzb.getNumericCellValue());
						}
						index = index + 1;
						// 天然气当月任务
						Cell trqdyrw = hssfRow.getCell(index);
						if (trqdyrw.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(trqdyrw))) {// 为空
								examSummary.setTrqdyrw(0);
							} else {
								examSummary.setTrqdyrw(Double.parseDouble(getCellValue(trqdyrw)));
							}
						}
						if (trqdyrw.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setTrqdyrw(trqdyrw.getNumericCellValue());
						}
						index = index + 1;
						// 天然气当月完成量
						Cell trqdywcl = hssfRow.getCell(index);
						if (trqdyrw.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(trqdywcl))) {// 为空
								examSummary.setTrqdywcl(0);
							} else {
								examSummary.setTrqdywcl(Double.parseDouble(getCellValue(trqdywcl)));
							}
						}
						if (trqdyrw.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setTrqdywcl(lslydrwwclzb.getNumericCellValue());
						}

						index = index + 1;
						// 天然气月度任务完成率
						Cell trqydrwwcl = hssfRow.getCell(index);
						if (trqydrwwcl.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(trqydrwwcl))) {// 为空
								examSummary.setTrqydrwwcl(0);
							} else {
								examSummary.setTrqydrwwcl(Double.parseDouble(getCellValue(trqydrwwcl)));
							}
						}
						if (trqydrwwcl.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setTrqydrwwcl(trqydrwwcl.getNumericCellValue());
						}
						index = index + 1;
						// 天然气月度任务完成率占比
						Cell trqydrwwclzb = hssfRow.getCell(index);
						if (trqydrwwclzb.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(trqydrwwclzb))) {// 为空
								examSummary.setTrqydrwwclzb(0);
							} else {
								examSummary.setTrqydrwwclzb(Double.parseDouble(getCellValue(trqydrwwclzb)));
							}
						}
						if (trqydrwwclzb.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setTrqydrwwclzb(trqydrwwclzb.getNumericCellValue());
						}
						index = index + 1;
						// 零售月度毛利额当月任务
						Cell lsydmledyrw = hssfRow.getCell(index);
						if (lsydmledyrw.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(lsydmledyrw))) {// 为空
								examSummary.setLsydmledyrw(0);
							} else {
								examSummary.setLsydmledyrw(Double.parseDouble(getCellValue(lsydmledyrw)));
							}
						}
						if (lsydmledyrw.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setLsydmledyrw(lsydmledyrw.getNumericCellValue());
						}
						index = index + 1;
						// 零售月度毛利额当月完成量
						Cell lsydmledywcl = hssfRow.getCell(index);
						if (lsydmledywcl.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(lsydmledywcl))) {// 为空
								examSummary.setLsydmledywcl(0);
							} else {
								examSummary.setLsydmledywcl(Double.parseDouble(getCellValue(lsydmledywcl)));
							}
						}
						if (lsydmledywcl.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setLsydmledywcl(lsydmledywcl.getNumericCellValue());
						}
						index = index + 1;
						// 零售月度毛利额月度任务完成率
						Cell lsydmleydrwwcl = hssfRow.getCell(index);
						if (lsydmleydrwwcl.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(lsydmleydrwwcl))) {// 为空
								examSummary.setLsydmleydrwwcl(0);
							} else {
								examSummary.setLsydmleydrwwcl(Double.parseDouble(getCellValue(lsydmleydrwwcl)));
							}
						}
						if (lsydmleydrwwcl.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setLsydmleydrwwcl(lsydmleydrwwcl.getNumericCellValue());
						}
						index = index + 1;
						// 零售月度毛利额月度任务完成率占比
						Cell lsydmleydrwwclzb = hssfRow.getCell(index);
						if (lsydmleydrwwclzb.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(lsydmleydrwwclzb))) {// 为空
								examSummary.setLsydmleydrwwclzb(0);
							} else {
								examSummary
										.setLsydmleydrwwclzb(Double.parseDouble(getCellValue(lsydmleydrwwclzb)));
							}
						}
						if (lsydmleydrwwclzb.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setLsydmleydrwwclzb(lsydmleydrwwclzb.getNumericCellValue());
						}
						index = index + 1;
						// 非油品销售额当月任务
						Cell fypxsedyrw = hssfRow.getCell(index);
						if (fypxsedyrw.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(fypxsedyrw))) {// 为空
								examSummary.setFypxsedyrw(0);
							} else {
								examSummary.setFypxsedyrw(Double.parseDouble(getCellValue(fypxsedyrw)));
							}
						}
						if (fypxsedyrw.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setFypxsedyrw(fypxsedyrw.getNumericCellValue());
						}
						index = index + 1;
						// 非油品销售额当月完成量
						Cell fypxsedywcl = hssfRow.getCell(index);
						if (fypxsedywcl.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(fypxsedywcl))) {// 为空
								examSummary.setFypxsedywcl(0);
							} else {
								examSummary.setFypxsedywcl(Double.parseDouble(getCellValue(fypxsedywcl)));
							}
						}
						if (fypxsedywcl.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setFypxsedywcl(fypxsedywcl.getNumericCellValue());
						}
						index = index + 1;
						// 非油品销售额月度任务完成率
						Cell fypxseydrwwcl = hssfRow.getCell(index);
						if (fypxseydrwwcl.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(fypxseydrwwcl))) {// 为空
								examSummary.setFypxseydrwwcl(0);
							} else {
								examSummary.setFypxseydrwwcl(Double.parseDouble(getCellValue(fypxseydrwwcl)));
							}
						}
						if (fypxseydrwwcl.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setFypxseydrwwcl(fypxseydrwwcl.getNumericCellValue());
						}
						index = index + 1;
						// 非油品销售额月度任务完成率占比
						Cell fypxseydrwwclzb = hssfRow.getCell(index);
						if (fypxseydrwwclzb.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(fypxseydrwwclzb))) {// 为空
								examSummary.setFypxseydrwwclzb(0);
							} else {
								examSummary
										.setFypxseydrwwclzb(Double.parseDouble(getCellValue(fypxseydrwwclzb)));
							}
						}
						if (fypxseydrwwclzb.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setFypxseydrwwclzb(fypxseydrwwclzb.getNumericCellValue());
						}
						index = index + 1;
						// 商业客户部非油品销售额当月任务
						Cell sykhbfypxsedyrw = hssfRow.getCell(index);
						if (sykhbfypxsedyrw.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(sykhbfypxsedyrw))) {// 为空
								examSummary.setSykhbfypxsedyrw(0);
							} else {
								examSummary
										.setSykhbfypxsedyrw(Double.parseDouble(getCellValue(sykhbfypxsedyrw)));
							}
						}
						if (sykhbfypxsedyrw.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setSykhbfypxsedyrw(sykhbfypxsedyrw.getNumericCellValue());
						}
						index = index + 1;
						// 商业客户部非油品销售额当月完成量
						Cell sykhbfypxsedywcl = hssfRow.getCell(index);
						if (sykhbfypxsedywcl.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(sykhbfypxsedywcl))) {// 为空
								examSummary.setSykhbfypxsedywcl(0);
							} else {
								examSummary
										.setSykhbfypxsedywcl(Double.parseDouble(getCellValue(sykhbfypxsedywcl)));
							}
						}
						if (sykhbfypxsedywcl.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setSykhbfypxsedywcl(sykhbfypxsedywcl.getNumericCellValue());
						}
						index = index + 1;
						// 商业客户部非油品销售额月度任务完成率
						Cell sykhbfypxseydrwwcl = hssfRow.getCell(index);
						if (sykhbfypxseydrwwcl.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(sykhbfypxseydrwwcl))) {// 为空
								examSummary.setSykhbfypxseydrwwcl(0);
							} else {
								examSummary.setSykhbfypxseydrwwcl(
										Double.parseDouble(getCellValue(sykhbfypxseydrwwcl)));
							}
						}
						if (sykhbfypxseydrwwcl.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setSykhbfypxseydrwwcl(sykhbfypxseydrwwcl.getNumericCellValue());
						}
						index = index + 1;
						// 商业客户部非油品销售额月度任务完成率占比
						Cell sykhbfypxseydrwwclzb = hssfRow.getCell(index);
						if (sykhbfypxseydrwwclzb.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(sykhbfypxseydrwwclzb))) {// 为空
								examSummary.setSykhbfypxseydrwwclzb(0);
							} else {
								examSummary.setSykhbfypxseydrwwclzb(
										Double.parseDouble(getCellValue(sykhbfypxseydrwwclzb)));
							}
						}
						if (sykhbfypxseydrwwclzb.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setSykhbfypxseydrwwclzb(sykhbfypxseydrwwclzb.getNumericCellValue());
						}
						index = index + 1;
						// 非油品月度毛利额当月任务
						Cell fypydmledyrw = hssfRow.getCell(index);
						if (fypydmledyrw.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(fypydmledyrw))) {// 为空
								examSummary.setFypydmledyrw(0);
							} else {
								examSummary.setFypydmledyrw(Double.parseDouble(getCellValue(fypydmledyrw)));
							}
						}
						if (fypydmledyrw.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setFypydmledyrw(fypydmledyrw.getNumericCellValue());
						}
						index = index + 1;
						// 非油品月度毛利额当月完成量
						Cell fypydmledywcl = hssfRow.getCell(index);
						if (fypydmledywcl.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(fypydmledywcl))) {// 为空
								examSummary.setFypydmledywcl(0);
							} else {
								examSummary.setFypydmledywcl(Double.parseDouble(getCellValue(fypydmledywcl)));
							}
						}
						if (fypydmledywcl.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setFypydmledyrw(fypydmledywcl.getNumericCellValue());
						}
						index = index + 1;
						// 非油品月度毛利额月度任务完成率
						Cell fypydmleydrwwcl = hssfRow.getCell(index);
						if (fypydmleydrwwcl.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(fypydmleydrwwcl))) {// 为空
								examSummary.setFypydmleydrwwcl(0);
							} else {
								examSummary
										.setFypydmleydrwwcl(Double.parseDouble(getCellValue(fypydmleydrwwcl)));
							}
						}
						if (fypydmleydrwwcl.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setFypydmleydrwwcl(fypydmleydrwwcl.getNumericCellValue());
						}
						index = index + 1;
						// 非油品月度毛利额月度任务完成率占比
						Cell fypydmleydrwwclzb = hssfRow.getCell(index);
						if (fypydmleydrwwclzb.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(fypydmleydrwwclzb))) {// 为空
								examSummary.setFypydmleydrwwclzb(0);
							} else {
								examSummary.setFypydmleydrwwclzb(
										Double.parseDouble(getCellValue(fypydmleydrwwclzb)));
							}
						}
						if (fypydmleydrwwclzb.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setFypydmleydrwwclzb(fypydmleydrwwclzb.getNumericCellValue());
						}
						index = index + 1;
						// 直批量当月任务
						Cell zpldyrw = hssfRow.getCell(index);
						if (zpldyrw.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(zpldyrw))) {// 为空
								examSummary.setZpldyrw(0);
							} else {
								examSummary.setZpldyrw(Double.parseDouble(getCellValue(zpldyrw)));
							}
						}
						if (zpldyrw.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setZpldyrw(zpldyrw.getNumericCellValue());
						}
						index = index + 1;
						// 直批量当月完成量
						Cell zpldywcl = hssfRow.getCell(index);
						if (zpldywcl.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(zpldywcl))) {// 为空
								examSummary.setZpldywcl(0);
							} else {
								examSummary.setZpldywcl(Double.parseDouble(getCellValue(zpldywcl)));
							}
						}
						if (zpldywcl.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setZpldywcl(zpldywcl.getNumericCellValue());
						}
						index = index + 1;
						// 直批量月度任务完成率
						Cell zplydrwwcl = hssfRow.getCell(index);
						if (zplydrwwcl.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(zplydrwwcl))) {// 为空
								examSummary.setZplydrwwcl(0);
							} else {
								examSummary.setZplydrwwcl(Double.parseDouble(getCellValue(zplydrwwcl)));
							}
						}
						if (zplydrwwcl.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setZpldywcl(zplydrwwcl.getNumericCellValue());
						}
						index = index + 1;
						// 直批量月度任务完成率占比
						Cell zplydrwwclzb = hssfRow.getCell(index);
						if (zplydrwwclzb.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(zplydrwwclzb))) {// 为空
								examSummary.setZplydrwwclzb(0);
							} else {
								examSummary.setZplydrwwclzb(Double.parseDouble(getCellValue(zplydrwwclzb)));
							}
						}
						if (zplydrwwclzb.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setZplydrwwclzb(zplydrwwclzb.getNumericCellValue());
						}
						index = index + 1;
						// 直批月度毛利额当月任务
						Cell zpydmledyrw = hssfRow.getCell(index);
						if (zpydmledyrw.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(zpydmledyrw))) {// 为空
								examSummary.setZpydmledyrw(0);
							} else {
								examSummary.setZpydmledyrw(Double.parseDouble(getCellValue(zpydmledyrw)));
							}
						}
						if (zpydmledyrw.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setZpydmledyrw(zpydmledyrw.getNumericCellValue());
						}
						index = index + 1;
						// 直批月度毛利额当月完成量
						Cell zpydmledywcl = hssfRow.getCell(index);
						if (zpydmledywcl.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(zpydmledywcl))) {// 为空
								examSummary.setZpydmledywcl(0);
							} else {
								examSummary.setZpydmledywcl(Double.parseDouble(getCellValue(zpydmledywcl)));
							}
						}
						if (zpydmledywcl.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setZpydmledywcl(zpydmledywcl.getNumericCellValue());
						}
						index = index + 1;
						// 直批月度毛利额月度任务完成率
						Cell zpydmleydrwwcl = hssfRow.getCell(index);
						if (zpydmleydrwwcl.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(zpydmleydrwwcl))) {// 为空
								examSummary.setZpydmleydrwwcl(0);
							} else {
								examSummary.setZpydmleydrwwcl(Double.parseDouble(getCellValue(zpydmleydrwwcl)));
							}
						}
						if (zpydmleydrwwcl.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setZpydmleydrwwcl(zpydmleydrwwcl.getNumericCellValue());
						}
						index = index + 1;
						// 直批月度毛利额月度任务完成率占比
						Cell zpydmleydrwwclzb = hssfRow.getCell(index);
						if (zpydmleydrwwclzb.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(zpydmleydrwwclzb))) {// 为空
								examSummary.setZpydmleydrwwclzb(0);
							} else {
								examSummary
										.setZpydmleydrwwclzb(Double.parseDouble(getCellValue(zpydmleydrwwclzb)));
							}
						}
						if (zpydmleydrwwclzb.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setZpydmleydrwwclzb(zpydmleydrwwclzb.getNumericCellValue());
						}
						index = index + 1;
						// 月度利润当月任务
						Cell ydlrdyrw = hssfRow.getCell(index);
						if (ydlrdyrw.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(ydlrdyrw))) {// 为空
								examSummary.setYdlrdyrw(0);
							} else {
								examSummary.setYdlrdyrw(Double.parseDouble(getCellValue(ydlrdyrw)));
							}
						}
						if (ydlrdyrw.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setYdlrdyrw(ydlrdyrw.getNumericCellValue());
						}
						index = index + 1;
						// 月度利润当月完成量
						Cell ydlrdywcl = hssfRow.getCell(index);
						if (ydlrdywcl.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(ydlrdywcl))) {// 为空
								examSummary.setYdlrdywcl(0);
							} else {
								examSummary.setYdlrdywcl(Double.parseDouble(getCellValue(ydlrdywcl)));
							}
						}
						if (ydlrdywcl.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setYdlrdywcl(ydlrdywcl.getNumericCellValue());
						}
						index = index + 1;
						// 月度利润月度任务完成率
						Cell ydlrydrwwcl = hssfRow.getCell(index);
						if (ydlrydrwwcl.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(ydlrydrwwcl))) {// 为空
								examSummary.setYdlrydrwwcl(0);
							} else {
								examSummary.setYdlrydrwwcl(Double.parseDouble(getCellValue(ydlrydrwwcl)));
							}
						}
						if (ydlrydrwwcl.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setYdlrydrwwcl(ydlrydrwwcl.getNumericCellValue());
						}
						index = index + 1;
						// 月度利润月度任务完成率占比
						Cell ydlrydrwwclzb = hssfRow.getCell(index);
						if (ydlrydrwwclzb.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(ydlrydrwwclzb))) {// 为空
								examSummary.setYdlrydrwwclzb(0);
							} else {
								examSummary.setYdlrydrwwclzb(Double.parseDouble(getCellValue(ydlrydrwwclzb)));
							}
						}
						if (ydlrydrwwclzb.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setYdlrydrwwclzb(ydlrydrwwclzb.getNumericCellValue());
						}
						index = index + 1;
						// 上年月均出库量
						Cell snyjckl = hssfRow.getCell(index);
						if (snyjckl.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(snyjckl))) {// 为空
								examSummary.setSnyjckl(0);
							} else {
								examSummary.setSnyjckl(Double.parseDouble(getCellValue(snyjckl)));
							}
						}
						if (snyjckl.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setSnyjckl(snyjckl.getNumericCellValue());
						}
						index = index + 1;
						// 总出库量不含寄外库发油
						Cell zcklbhjwkfy = hssfRow.getCell(index);
						if (zcklbhjwkfy.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(zcklbhjwkfy))) {// 为空
								examSummary.setZcklbhjwkfy(0);
							} else {
								examSummary.setZcklbhjwkfy(Double.parseDouble(getCellValue(zcklbhjwkfy)));
							}
						}
						if (zcklbhjwkfy.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setZcklbhjwkfy(zcklbhjwkfy.getNumericCellValue());
						}
						index = index + 1;
						// 其中跨区出库吨数
						Cell qzkqckds = hssfRow.getCell(index);
						if (qzkqckds.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(qzkqckds))) {// 为空
								examSummary.setQzkqckds(0);
							} else {
								examSummary.setQzkqckds(Double.parseDouble(getCellValue(qzkqckds)));
							}
						}
						if (qzkqckds.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setQzkqckds(qzkqckds.getNumericCellValue());
						}
						index = index + 1;
						// 出库量控制系数下限
						Cell cklkzxsxx = hssfRow.getCell(index);
						if (cklkzxsxx.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(cklkzxsxx))) {// 为空
								examSummary.setCklkzxsxx(0);
							} else {
								examSummary.setCklkzxsxx(Double.parseDouble(getCellValue(cklkzxsxx)));
							}
						}
						if (cklkzxsxx.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setCklkzxsxx(cklkzxsxx.getNumericCellValue());
						}
						index = index + 1;
						// 出库量控制系数上限
						Cell cklkzxssx = hssfRow.getCell(index);
						if (cklkzxssx.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(cklkzxssx))) {// 为空
								examSummary.setCklkzxssx(0);
							} else {
								examSummary.setCklkzxssx(Double.parseDouble(getCellValue(cklkzxssx)));
							}
						}
						if (cklkzxssx.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setCklkzxssx(cklkzxssx.getNumericCellValue());
						}
						index = index + 1;
						// 管理得分
						Cell gldf = hssfRow.getCell(index);
						if (gldf.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(gldf))) {// 为空
								examSummary.setGldf(0);
							} else {
								examSummary.setGldf(Double.parseDouble(getCellValue(gldf)));
							}
						}
						if (gldf.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setGldf(gldf.getNumericCellValue());
						}
						index = index + 1;
						// 管理指标占比
						Cell glzbzb = hssfRow.getCell(index);
						if (glzbzb.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(glzbzb))) {// 为空
								examSummary.setGlzbzb(0);
							} else {
								examSummary.setGlzbzb(Double.parseDouble(getCellValue(glzbzb)));
							}
						}
						if (glzbzb.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setGlzbzb(glzbzb.getNumericCellValue());
						}
						index = index + 1;
						// 县公司经营部班子管理贡献系数
						Cell xgsjybbzglgxxs = hssfRow.getCell(index);
						if (xgsjybbzglgxxs.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(xgsjybbzglgxxs))) {// 为空
								examSummary.setXgsjybbzglgxxs(0);
							} else {
								examSummary.setXgsjybbzglgxxs(Double.parseDouble(getCellValue(xgsjybbzglgxxs)));
							}
						}
						if (xgsjybbzglgxxs.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setXgsjybbzglgxxs(xgsjybbzglgxxs.getNumericCellValue());
						}
						index = index + 1;
						// 综合得分率
						Cell zhdfl = hssfRow.getCell(index);
						if (zhdfl.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(zhdfl))) {// 为空
								examSummary.setZhdfl(0);
							} else {
								examSummary.setZhdfl(Double.parseDouble(getCellValue(zhdfl)));
							}
						}
						if (zhdfl.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setZhdfl(zhdfl.getNumericCellValue());
						}
						index = index + 1;
						// 联量挂钩系数
						Cell llggxs = hssfRow.getCell(index);
						if (llggxs.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(llggxs))) {// 为空
								examSummary.setLlggxs(0);
							} else {
								examSummary.setLlggxs(Double.parseDouble(getCellValue(llggxs)));
							}
						}
						if (llggxs.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setLlggxs(llggxs.getNumericCellValue());
						}
						index = index + 1;
						// 基本薪酬
						Cell jbxc = hssfRow.getCell(index);
						if (jbxc.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(jbxc))) {// 为空
								examSummary.setJbxc(0);
							} else {
								examSummary.setJbxc(Double.parseDouble(getCellValue(jbxc)));
							}
						}
						if (jbxc.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setJbxc(jbxc.getNumericCellValue());
						}
						index = index + 1;
						// 联量薪酬
						Cell llxc = hssfRow.getCell(index);
						if (llxc.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(llxc))) {// 为空
								examSummary.setLlxc(0);
							} else {
								examSummary.setLlxc(Double.parseDouble(getCellValue(llxc)));
							}
						}
						if (llxc.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setLlxc(llxc.getNumericCellValue());
						}
						index = index + 1;
						// 整月看站及借调等人员工资
						Cell zykzjjddrygz = hssfRow.getCell(index);
						if (zykzjjddrygz.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(zykzjjddrygz))) {// 为空
								examSummary.setZykzjjddrygz(0);
							} else {
								examSummary.setZykzjjddrygz(Double.parseDouble(getCellValue(zykzjjddrygz)));
							}
						}
						if (zykzjjddrygz.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setLlxc(zykzjjddrygz.getNumericCellValue());
						}
						index = index + 1;
						// 加班费（看站及其他特定非专项奖励加班费）
						Cell jbf = hssfRow.getCell(index);
						if (jbf.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(jbf))) {// 为空
								examSummary.setJbf(0);
							} else {
								examSummary.setJbf(Double.parseDouble(getCellValue(jbf)));
							}
						}
						if (jbf.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setJbf(jbf.getNumericCellValue());
						}
						index = index + 1;
						// 请假人员薪酬
						Cell qjryxc = hssfRow.getCell(index);
						if (qjryxc.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(qjryxc))) {// 为空
								examSummary.setQjryxc(0);
							} else {
								examSummary.setQjryxc(Double.parseDouble(getCellValue(qjryxc)));
							}
						}
						if (qjryxc.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setQjryxc(qjryxc.getNumericCellValue());
						}
						index = index + 1;
						// 过渡补差
						Cell gdbc = hssfRow.getCell(index);
						if (gdbc.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(gdbc))) {// 为空
								examSummary.setGdbc(0);
							} else {
								examSummary.setGdbc(Double.parseDouble(getCellValue(gdbc)));
							}
						}
						if (gdbc.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setGdbc(gdbc.getNumericCellValue());
						}
						index = index + 1;
						// 保留差
						Cell blc = hssfRow.getCell(index);
						if (blc.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(blc))) {// 为空
								examSummary.setBlc(0);
							} else {
								examSummary.setBlc(Double.parseDouble(getCellValue(blc)));
							}
						}
						if (blc.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setBlc(blc.getNumericCellValue());
						}
						index = index + 1;
						// 其他补贴
						Cell qtbt = hssfRow.getCell(index);
						if (qtbt.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(qtbt))) {// 为空
								examSummary.setQtbt(0);
							} else {
								examSummary.setQtbt(Double.parseDouble(getCellValue(qtbt)));
							}
						}
						if (qtbt.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setQtbt(qtbt.getNumericCellValue());
						}
						index = index + 1;
						// 临时停业看站工资
						Cell lstykzgz = hssfRow.getCell(index);
						if (lstykzgz.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(lstykzgz))) {// 为空
								examSummary.setLstykzgz(0);
							} else {
								examSummary.setLstykzgz(Double.parseDouble(getCellValue(lstykzgz)));
							}
						}
						if (lstykzgz.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setLstykzgz(lstykzgz.getNumericCellValue());
						}
						index = index + 1;
						// 专项奖罚
						Cell zxjf = hssfRow.getCell(index);
						if (zxjf.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(zxjf))) {// 为空
								examSummary.setZxjf(0);
								examSummary.setBeginzxjf(0);
							} else {
								examSummary.setZxjf(Double.parseDouble(getCellValue(zxjf)));
								examSummary.setBeginzxjf(Double.parseDouble(getCellValue(zxjf)));
							}
						}
						if (zxjf.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setZxjf(zxjf.getNumericCellValue());
						}
						index = index + 1;
						// 管理扣罚
						Cell glkf = hssfRow.getCell(index);
						if (glkf.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(glkf))) {// 为空
								examSummary.setGlkf(0);
								examSummary.setBeginglkf(0);
							} else {
								examSummary.setGlkf(Double.parseDouble(getCellValue(glkf)));
								examSummary.setBeginglkf(Double.parseDouble(getCellValue(glkf)));
							}
						}
						if (glkf.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setGlkf(glkf.getNumericCellValue());
						}
						index = index + 1;
						// 劳效薪酬
						Cell lxxc = hssfRow.getCell(index);
						if (lxxc.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(lxxc))) {// 为空
								examSummary.setLxxc(0);
							} else {
								examSummary.setLxxc(Double.parseDouble(getCellValue(lxxc)));
							}
						}
						if (lxxc.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setLxxc(lxxc.getNumericCellValue());
						}
						index = index + 1;
						// 合计：薪酬性费用
						Cell hj = hssfRow.getCell(index);
						if (hj.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(hj))) {// 为空
								examSummary.setHj(0);
							} else {
								examSummary.setHj(Double.parseDouble(getCellValue(hj)));
							}
						}
						if (hj.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setHj(hj.getNumericCellValue());
						}
						index = index + 1;
						// 备注
						Cell bz = hssfRow.getCell(index);
						examSummary.setBz(getCellValue(bz));
						index = index + 1;

						// 轻油超目标薪酬
						Cell qycmbxc = hssfRow.getCell(index);
						if (hj.getCellType() == Cell.CELL_TYPE_STRING) {
							if (StringUtils.isEmpty(getCellValue(qycmbxc))) {// 为空
								examSummary.setQycmbxc(0);
							} else {
								examSummary.setQycmbxc(Double.parseDouble(getCellValue(qycmbxc)));
							}
						}
						if (hj.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							examSummary.setQycmbxc(qycmbxc.getNumericCellValue());
						}
						index = index + 1;

						list.add(examSummary);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				map.put("success", false);
				map.put("reason", "excel导入出错，请联系客服人员！");
				return map;
			}
			System.out.println(list);
			map.put("success", true);
			map.put("data", list);
			map.put("reason", "数据读取成功！");
		}
		return map;
	}

	/**
	 * @throws Exception
	 * @Title: readCheckExamSummaryXls @Description: 读取工资明细表 @param @param
	 *         path @param @param examEummaryService @param @return 参数 @return
	 *         Map<String,Object> 返回类型 @throws
	 */
	public static Map<String, Object> readCheckSalaryXls(String path, Integer companyId, Integer noticeId, Integer salaryType,
			ICompanyService companyService, ISalaryService examEummaryService) throws Exception {
		int startRow = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.isEmpty(path)) {
			map.put("success", false);
			map.put("reason", "ERR：文件路径为null!");
			return map;
		}
		Company company = companyService.findById(companyId + "");
		if (company == null) {// 机构不存在
			map.put("success", false);
			map.put("reason", "ERR：机构不存在!");
			return map;
		}
		//查询通知单的年月信息
		Map<String,Object> noticeInfoMap = examEummaryService.getNoticeInfoById(noticeId,salaryType);
		String yearMonthDate = null;
		if(noticeInfoMap != null){
			yearMonthDate = (String) noticeInfoMap.get("yearMonthDate");
		}
		
		Workbook workbook = null;
		InputStream is = null;
		try {
			is = new FileInputStream(path);
			String extString = path.substring(path.lastIndexOf("."));
			if(".xls".equals(extString)){
				workbook = new HSSFWorkbook(is);
            }else if(".xlsx".equals(extString)){
            	workbook = new XSSFWorkbook(is);
            }else{
            	map.put("success", false);
    			map.put("reason", "ERR：the file type error!");
    			return map;
            }
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("reason", "excel文件解析失败,请联系客服人员！");
			return map;
		}
		// 存儲解析讀取到的數據
		List<Salary> list = new ArrayList<Salary>();
		// 循环工作表Sheet
		for (int numSheet = 0; numSheet < workbook.getNumberOfSheets(); numSheet++) {
			Sheet hssfSheet = workbook.getSheetAt(numSheet);
			if (hssfSheet == null) {
				continue;
			}
			// 第0行信息
			// HSSFRow hssfRow0 = hssfSheet.getRow(startRow);

			// 1.校验第一行表头信息
			Row hssfRow0 = hssfSheet.getRow(startRow + 1);
			if (hssfRow0 == null) {
				break;
			}
			Map<String, Object> reMap = checkXlsHeadInfo(SALARY_EXL_HEADE, hssfRow0);
			if ((boolean) reMap.get("success") == false) {
				return reMap;
			}

			String dateStr = null;
			// 2.循环读取行Row中的数据
			try {
				for (int rowNum = startRow + 2; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
					int f = hssfSheet.getLastRowNum();
					Row hssfRow = hssfSheet.getRow(rowNum);
					int sheet = numSheet + 1;
					int row = rowNum + 1;
					if (hssfRow != null) {
						if (checkIsBlankRow(hssfRow)) {
							System.out.println("第" + rowNum + "  行是空行！");
							break;
						}
						Salary salary = new Salary();
						
						// 读取每一列数据
						int index = 0;
						// 1.序号
						Cell xuhao = hssfRow.getCell(index);
						String xuhaoStr = getCellValue(xuhao);
						salary.setXh(StringUtils.isEmpty(xuhaoStr)? 0:(int)Double.parseDouble(xuhaoStr));
						index = index + 1;
						// 2.单位/委托公司
						Cell dw_wtgs = hssfRow.getCell(index);
						index = index + 1;
						if(salaryType == 1){//部门
							salary.setDw(getCellValue(dw_wtgs));
						}else{//加油站
							//salary.setJyzmc(getCellValue(dw_wtgs));
							salary.setDw(getCellValue(dw_wtgs));
						}

						// 3.姓名
						Cell xm = hssfRow.getCell(index);
						if (StringUtils.isEmpty(getCellValue(xm))) {// 分版块
							map.put("success", false);
							map.put("reason", "数据错误！第[" + rowNum + "行,第" + index + "列]的[姓名]数据为空!");
							return map;
						}
						index = index + 1;
						salary.setXm(getCellValue(xm));

						// 4.身份证号
						Cell sfzh = hssfRow.getCell(index);
						index = index + 1;
						salary.setSfzh(getCellValue(sfzh));
						// 5.岗位
						Cell gw = hssfRow.getCell(index);
						index = index + 1;
						salary.setGw(getCellValue(gw));
						// 6.基本薪酬
						Cell jbxc = hssfRow.getCell(index);
						index = index + 1;
						salary.setJbxc(getCellValue(jbxc) == "" ? 0 : Double.parseDouble(getCellValue(jbxc)));
						// 7.联量薪酬
						Cell llxc = hssfRow.getCell(index);
						index = index + 1;
						salary.setLlxc(StringUtils.isEmpty(getCellValue(llxc)) ? 0 : Double.parseDouble(getCellValue(llxc)));

						// 8.非油品薪酬
						Cell fypxc = hssfRow.getCell(index);
						index = index + 1;
						salary.setFypxc(StringUtils.isEmpty(getCellValue(fypxc)) ? 0 : Double.parseDouble(getCellValue(fypxc)));
						
						// 9.加油卡薪酬
						Cell jykxc = hssfRow.getCell(index);
						index = index + 1;
						salary.setJykxc(StringUtils.isEmpty(getCellValue(jykxc)) ? 0 : Double.parseDouble(getCellValue(jykxc)));

						// 10.加班费
						Cell jbf = hssfRow.getCell(index);
						index = index + 1;
						salary.setJbf(StringUtils.isEmpty(getCellValue(jbf)) ? 0 : Double.parseDouble(getCellValue(jbf)));
						// 11.过渡补差
						Cell gdbc = hssfRow.getCell(index);
						index = index + 1;
						salary.setGdbc(StringUtils.isEmpty(getCellValue(gdbc)) ? 0 : Double.parseDouble(getCellValue(gdbc)));
						
						//12.其他工资
						Cell qtgz = hssfRow.getCell(index);
						index = index + 1;
						salary.setQtgz(StringUtils.isEmpty(getCellValue(qtgz)) ? 0 : Double.parseDouble(getCellValue(qtgz)));
						//13.专项奖金
						Cell zxjf = hssfRow.getCell(index);
						index = index + 1;
						salary.setZxjj(StringUtils.isEmpty(getCellValue(zxjf)) ? 0 : Double.parseDouble(getCellValue(zxjf)));
						//14.专项扣罚
						Cell zxkf = hssfRow.getCell(index);
						index = index + 1;
						salary.setZxkf(StringUtils.isEmpty(getCellValue(zxkf)) ? 0 : Double.parseDouble(getCellValue(zxkf)));
						//15.其他奖罚
						Cell qtjf = hssfRow.getCell(index);
						index = index + 1;
						salary.setQtjf(StringUtils.isEmpty(getCellValue(qtjf)) ? 0 : Double.parseDouble(getCellValue(qtjf)));
						//16.其他津贴补助等
						Cell qtjtbz = hssfRow.getCell(index);
						index = index + 1;
						salary.setQtgz(
								StringUtils.isEmpty(getCellValue(qtjtbz)) ? 0 : Double.parseDouble(getCellValue(qtjtbz)));
						//17.应发合计
						Cell yfhj = hssfRow.getCell(index);
						if (StringUtils.isEmpty(getCellValue(yfhj))) {
							map.put("success", false);
							map.put("reason", "数据错误！第[" + rowNum + "行,第" + index + "列]的[加油站分类]数据为空!");
							return map;
						}
						index = index + 1;
						salary.setYfhj(StringUtils.isEmpty(getCellValue(yfhj)) ? 0 : Double.parseDouble(getCellValue(yfhj)));

						// 机构校验
						salary.setCompanyId(companyId);// 机构id
						salary.setSalaryType(salaryType);// 工资单类型
						salary.setNoticeId(noticeId);// 通知单id
						salary.setYearMonthDate(yearMonthDate);
						// 将对象添加到队列中
						list.add(salary);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				map.put("success", false);
				map.put("reason", "excel导入出错，请联系客服人员！");
				return map;
			}
			System.out.println(list);//////////////////////////
			map.put("success", true);
			map.put("data", list);
			map.put("reason", "数据读取成功！");
		}
		return map;
	}
	
	/**
	 * @Name: getCellValue @Description: 获取单元格值 @param @param
	 * cell @param @return 参数 @return Object 返回类型 @throws
	 */
	public static String getCellValue(Cell cell) {
		String cellValue = null;
		
		if (cell != null) {
			// 判断cell类型
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_NUMERIC: {
				cellValue = String.valueOf(cell.getNumericCellValue());
				break;
			}
			case Cell.CELL_TYPE_FORMULA: {
				System.out.println("Index: " + cell.getColumnIndex());///////////////////////////
				// 判断cell是否为日期格式
				/*if (DateUtil.isCellDateFormatted(cell)) {
					// 转换为日期格式YYYY-mm-dd
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					cellValue = sdf.format(cell.getDateCellValue());
				} else {
					// 数字
					cellValue = cell.getRichStringCellValue().getString();
				}*/
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cellValue = cell.getRichStringCellValue().getString();
				break;
			}
			case Cell.CELL_TYPE_STRING: {
				cellValue = cell.getRichStringCellValue().getString();
				break;
			}
			case Cell.CELL_TYPE_BOOLEAN: {
				// 返回布尔类型的值
				cellValue = String.valueOf(cell.getBooleanCellValue()).trim();
				break;
			}
			case Cell.CELL_TYPE_BLANK:
			case Cell.CELL_TYPE_ERROR: {
				cellValue = "";
				break;
			}
			default: {
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cellValue = cell.getRichStringCellValue().getString();
			}
			}
		} else {
			cellValue = "";
		}
		//System.out.println(" cellValue:" + cellValue);
		return cellValue;
	}

	/**
	 * @Name: checkIsBlankRow 
	 * @Description:校验是否是空行 
	 * @param 参数 
	 * @return boolean 返回类型 @throws
	 */
	public static boolean checkIsBlankRow(Row hssfRow) {
		boolean flag = true;// 是
		if (hssfRow == null) {
			return flag;
		}
		int minColIx = hssfRow.getFirstCellNum();
		int maxColIx = hssfRow.getLastCellNum();
		for (int colIx = minColIx; colIx < maxColIx; colIx++) {// 有一个cell不为空的话，设置为false
			Cell cell = hssfRow.getCell(colIx);
			if (cell != null && hssfRow.getCell(colIx).getCellType() != Cell.CELL_TYPE_BLANK
					&& hssfRow.getCell(colIx).getCellType() != Cell.CELL_TYPE_ERROR) {
				flag = false;
			}
		}
		return flag;
	}
	
	/**
	* @Name: checkIsTotalRow  
	* @Description:  检查是否是合计行
	* @param @param hssfRow
	* @param @param index
	* @param @return    参数  
	* @return boolean    返回类型  
	* @throws
	 */
	public boolean checkIsTotalRow(Row hssfRow, int index) {
		boolean flag = false;
		if (hssfRow.getLastCellNum() < index) {
			return false;
		}

		if (getCellValue(hssfRow.getCell(index)).equals("合计")) {
			flag = true;
		}
		return flag;
	}
	
	/**
	 * @Title: checkBlankRow
	 * @Description: TODO @param 参数 startIndex -- 开始列索引 endIndex -- 结束列索引
	 * @return void 返回类型 @throws
	 */
	public double getRangCellValue(Row hssfRow, int startIndex, int endIndex) {
		double total = 0;
		if (hssfRow == null || startIndex < 0 || endIndex < 0 || startIndex > endIndex) {
			return total;
		}

		if (startIndex == endIndex) {// 起始和结束列号一样，取这一列的cell
			String cellStr = getCellValue(hssfRow.getCell(startIndex));
			total = StringUtils.isEmpty(cellStr) ? 0 : Double.parseDouble(cellStr);
		}
		if (startIndex < endIndex) {
			for (int i = startIndex; i <= endIndex; i++) {
				String cellStr = getCellValue(hssfRow.getCell(i));
				total += StringUtils.isEmpty(cellStr) ? 0 : Double.parseDouble(cellStr);
			}
		}
		return total;
	}
	
	/**
	 * @Name: sjbmExportSalaryXls 
	 * @Description:市级部门导入工资mingxi 
	 * @return Map<String,Object>
	 * @throws
	 */
	public static Map<String, Object> sjbmExportSalaryXls(String path, Integer companyId, Integer noticeId, Integer salaryType,
			ICompanyService companyService, ISalaryService examEummaryService) throws Exception {
		int startRow = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.isEmpty(path)) {
			map.put("success", false);
			map.put("reason", "ERR：文件路径为null!");
			return map;
		}
		Company company = companyService.findById(companyId + "");
		if (company == null) {// 机构不存在
			map.put("success", false);
			map.put("reason", "ERR：机构不存在!");
			return map;
		}

		Workbook workbook = null;
		InputStream is = null;

		try {
			is = new FileInputStream(path);
			String extString = path.substring(path.lastIndexOf("."));
			if(".xls".equals(extString)){
				workbook = new HSSFWorkbook(is);
            }else if(".xlsx".equals(extString)){
            	workbook = new XSSFWorkbook(is);
            }else{
            	map.put("success", false);
    			map.put("reason", "ERR：the file type error!");
    			return map;
            }
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("reason", "excel文件解析失败,请联系客服人员！");
			return map;
		}
		// 存儲解析讀取到的數據
		List<Salary> list = new ArrayList<Salary>();
		// 循环工作表Sheet
		for (int numSheet = 0; numSheet < workbook.getNumberOfSheets(); numSheet++) {
			Sheet hssfSheet = workbook.getSheetAt(numSheet);
			if (hssfSheet == null) {
				continue;
			}
			// 第0行信息
			// HSSFRow hssfRow0 = hssfSheet.getRow(startRow);

			// 1.校验第一行表头信息
			Row hssfRow0 = hssfSheet.getRow(startRow + 1);
			if (hssfRow0 == null) {
				break;
			}
			Map<String, Object> reMap = checkXlsHeadInfo(SALARY_EXL_HEADE, hssfRow0);
			if ((boolean) reMap.get("success") == false) {
				return reMap;
			}

			String dateStr = null;
			// 2.循环读取行Row中的数据
			try {
				for (int rowNum = startRow + 2; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
					int f = hssfSheet.getLastRowNum();
					Row hssfRow = hssfSheet.getRow(rowNum);
					int sheet = numSheet + 1;
					int row = rowNum + 1;
					if (hssfRow != null) {
						if (checkIsBlankRow(hssfRow)) {
							System.out.println("第" + rowNum + "  行是空行！");
							break;
						}
						Salary salary = new Salary();
						// 读取每一列数据
						int index = 17;
						// 17.公积金
						Cell gjj = hssfRow.getCell(index);
						index = index + 1;
						double gjjD = StringUtils.isEmpty(getCellValue(gjj)) ? 0 : Double.parseDouble(getCellValue(gjj));
						salary.setGjj(gjjD);
						// 18.养老金
						Cell ylj = hssfRow.getCell(index);
						index = index + 1;
						double yljD = StringUtils.isEmpty(getCellValue(ylj)) ? 0 : Double.parseDouble(getCellValue(ylj));
						salary.setYlj(yljD);
						// 19.失业金
						Cell syj = hssfRow.getCell(index);
						index = index + 1;
						double syjD = StringUtils.isEmpty(getCellValue(syj)) ? 0 : Double.parseDouble(getCellValue(syj));
						salary.setSyj(syjD);
						// 20.医保金
						Cell ybj = hssfRow.getCell(index);
						index = index + 1;
						double ybjD = StringUtils.isEmpty(getCellValue(ybj)) ? 0 : Double.parseDouble(getCellValue(ybj));
						salary.setYbj(ybjD);
						// 21.年金
						Cell nj = hssfRow.getCell(index);
						index = index + 1;
						double njD = StringUtils.isEmpty(getCellValue(nj)) ? 0 : Double.parseDouble(getCellValue(nj));
						salary.setNj(njD);
						// 22.个人所得税
						Cell grsds = hssfRow.getCell(index);
						index = index + 1;
						salary.setGrsds(StringUtils.isEmpty(getCellValue(grsds)) ? 0 : Double.parseDouble(getCellValue(grsds)));
						// 23.实发工资
						Cell sfgz = hssfRow.getCell(index);
						index = index + 1;
						salary.setSfgz(StringUtils.isEmpty(getCellValue(sfgz)) ? 0 : Double.parseDouble(getCellValue(sfgz)));
						// 24.帐号
						Cell zh = hssfRow.getCell(index);
						index = index + 1;
						salary.setZh(getCellValue(zh));
						// 25.签名
						Cell qm = hssfRow.getCell(index);
						index = index + 1;
						salary.setQm(getCellValue(qm));
						// 26.新开户日期（换卡必填）
						Cell xkhrq = hssfRow.getCell(index);
						index = index + 1;
						salary.setXkhrq(getCellValue(xkhrq));
						//27.借支
						Cell jz = hssfRow.getCell(index);
						salary.setJz(StringUtils.isEmpty(getCellValue(jz)) ? 0 : Double.parseDouble(getCellValue(jz)));
						index = index + 1;
						//28.卫生费
						Cell wsf = hssfRow.getCell(index);
						salary.setWsf(StringUtils.isEmpty(getCellValue(wsf)) ? 0 : Double.parseDouble(getCellValue(wsf)));
						index = index + 1;
						//29.房费
						Cell ff = hssfRow.getCell(index);
						salary.setFf(StringUtils.isEmpty(getCellValue(ff)) ? 0 : Double.parseDouble(getCellValue(ff)));
						index = index + 1;
						//30.电费
						Cell df = hssfRow.getCell(index);
						salary.setDf(StringUtils.isEmpty(getCellValue(df)) ? 0 : Double.parseDouble(getCellValue(df)));
						index = index + 1;
						//31.暖气费
						Cell nqf = hssfRow.getCell(index);
						salary.setNqf(StringUtils.isEmpty(getCellValue(nqf)) ? 0 : Double.parseDouble(getCellValue(nqf)));
						index = index + 1;
						//32.打卡金额
						Cell dkje = hssfRow.getCell(index);
						salary.setDkje(StringUtils.isEmpty(getCellValue(dkje)) ? 0 : Double.parseDouble(getCellValue(dkje)));
						index = index + 1;
						// 33.身份证号
						Cell sfzh2 = hssfRow.getCell(index);
						index = index + 1;
						salary.setSfzh2(getCellValue(sfzh2));
						//34.已发需要合并扣税项合计
						Cell yfxyhbksxhj = hssfRow.getCell(index);
						index = index + 1;
						salary.setYfxyhbksxhj(StringUtils.isEmpty(getCellValue(yfxyhbksxhj)) ? 0 : Double.parseDouble(getCellValue(yfxyhbksxhj)));
						// 35.公积金单位每月缴费
						Cell gjjdwmyjf = hssfRow.getCell(index);
						index = index + 1;
						salary.setGjjdwmyjf(StringUtils.isEmpty(getCellValue(gjjdwmyjf)) ? 0 : Double.parseDouble(getCellValue(gjjdwmyjf)));
						//36.养老单位每月缴费
						Cell yljdwmyjf = hssfRow.getCell(index);
						index = index + 1;
						salary.setYldwmyjf(
								StringUtils.isEmpty(getCellValue(yljdwmyjf)) ? 0 : Double.parseDouble(getCellValue(yljdwmyjf)));
						// 37.失业单位每月缴费
						Cell sydwmyjf = hssfRow.getCell(index);
						index = index + 1;
						salary.setSydwmyjf(
								StringUtils.isEmpty(getCellValue(sydwmyjf)) ? 0 : Double.parseDouble(getCellValue(sydwmyjf)));
						
						// 38.工伤单位每月缴费
						Cell gsdwmyjf = hssfRow.getCell(index);
						index = index + 1;
						salary.setGsdwmyjf(
								StringUtils.isEmpty(getCellValue(gsdwmyjf)) ? 0 : Double.parseDouble(getCellValue(gsdwmyjf)));
						// 39.医保单位每月缴费
						Cell ybdwmyjf = hssfRow.getCell(index);
						index = index + 1;
						salary.setYbdwmyjf(
								StringUtils.isEmpty(getCellValue(ybdwmyjf)) ? 0 : Double.parseDouble(getCellValue(ybdwmyjf)));
						// 40.生育保险单位每月缴费
						Cell sybxdwmyjf = hssfRow.getCell(index);
						index = index + 1;
						salary.setSybxdwmyjf(StringUtils.isEmpty(getCellValue(sybxdwmyjf)) ? 0
								: Double.parseDouble(getCellValue(sybxdwmyjf)));
						// 41.大病保险单位缴费
						Cell dbbxdwjf = hssfRow.getCell(index);
						index = index + 1;
						salary.setDbbxdwmyjf(
								StringUtils.isEmpty(getCellValue(dbbxdwjf)) ? 0 : Double.parseDouble(getCellValue(dbbxdwjf)));
						
						// 42.年金企业缴费计入个人账户金额
						Cell njqyjfjrgrzhje = hssfRow.getCell(index);
						index = index + 1;
						salary.setNjqyjfjrgrzhje(StringUtils.isEmpty(getCellValue(njqyjfjrgrzhje)) ? 0
								: Double.parseDouble(getCellValue(njqyjfjrgrzhje)));
						// 43.年金企业缴费
						Cell njqyjf = hssfRow.getCell(index);
						index = index + 1;
						salary.setNjqyjf(
								StringUtils.isEmpty(getCellValue(njqyjf)) ? 0 : Double.parseDouble(getCellValue(njqyjf)));
						// 44.管理费
						Cell glf = hssfRow.getCell(index);
						index = index + 1;
						salary.setGlf(
								StringUtils.isEmpty(getCellValue(glf)) ? 0 : Double.parseDouble(getCellValue(glf)));
						// 45.委托公司名称
						Cell wtgsmc = hssfRow.getCell(index);
						index = index + 1;
						salary.setWtgsmc(getCellValue(wtgsmc));
						// 46.社保参保地
						Cell sbcbd = hssfRow.getCell(index);
						index = index + 1;
						salary.setSbcbd(getCellValue(sbcbd));

						// 机构校验
						salary.setCompanyId(companyId);// 机构id
						salary.setSalaryType(salaryType);// 工资单类型
						salary.setNoticeId(noticeId);// 通知单id
						// 将对象添加到队列中
						list.add(salary);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				map.put("success", false);
				map.put("reason", "excel导入出错，请联系客服人员！");
				return map;
			}
			// 应发合计校验
			System.out.println(list);//////////////////////////
			map.put("success", true);
			map.put("data", list);
			map.put("reason", "数据读取成功！");
		}
		return map;
	}
}
