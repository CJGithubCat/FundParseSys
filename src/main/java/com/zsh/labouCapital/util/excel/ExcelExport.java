package com.zsh.labouCapital.util.excel;

import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.biff.DisplayFormat;
import jxl.format.Alignment;
import jxl.format.VerticalAlignment;
import jxl.write.DateFormat;
import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class ExcelExport {
	public WritableFont CELL_FONT = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false);
	public WritableCellFormat STRING_CELL = new WritableCellFormatExt(CELL_FONT);
	public WritableCellFormat INTEGER_CELL = new WritableCellFormatExt(CELL_FONT, NumberFormats.INTEGER);
	public WritableCellFormat FLOAT_CELL = new WritableCellFormatExt(CELL_FONT, NumberFormats.FLOAT);
	public WritableCellFormat PERCENT_FLOAT_CELL = new WritableCellFormatExt(CELL_FONT, NumberFormats.PERCENT_FLOAT);
	public WritableCellFormat PERCENT_INTEGER_CELL = new WritableCellFormatExt(CELL_FONT,
			NumberFormats.PERCENT_INTEGER);
	public WritableCellFormat DATE_CELL = new WritableCellFormatExt(CELL_FONT, new DateFormat("yyyy-MM-dd"));
	public WritableCellFormat DATETIME_CELL = new WritableCellFormatExt(CELL_FONT,
			new DateFormat("yyyy-MM-dd HH:mm:ss"));

	public void exportExcelFromBeanList(HttpServletResponse response, String fn, String[][] title_field_mapping,
			String[] fieldExcelTypes, List<?> list) throws Exception {
		OutputStream os = null;
		WritableWorkbook workbook = null;

		try {
			os = response.getOutputStream();
			String fileName = URLEncoder.encode(fn, "utf-8");
			response.setHeader("Content-disposition", "attachment;fileName=" + fileName);
			response.setContentType("aplication/excel");

			workbook = Workbook.createWorkbook(os);
			WritableSheet sheet = workbook.createSheet("sheet1", 1);
			// 表头
			String[] header = title_field_mapping[0];
			// 创建表头
			createOneRowFromStringArr(sheet, 0, header);
			String[] header_fields = title_field_mapping[1];
			for (int i = 0; i < list.size(); i++) {
				createOneRowFromBean(sheet, i + 1, list.get(i), header_fields, fieldExcelTypes);
			}
			workbook.write();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtil.close(workbook);
			IOUtil.close(os);
		}
	}

	public void exportExcelFromBeanList(HttpServletResponse response, String fn, String[] title, String[] fields,
			String[] cellTypes, List<?> list) throws Exception {
		OutputStream os = null;
		WritableWorkbook workbook = null;

		try {
			os = response.getOutputStream();
			String fileName = URLEncoder.encode(fn, "utf-8");
			response.setHeader("Content-disposition", "attachment;fileName=" + fileName);
			response.setContentType("aplication/excel");

			workbook = Workbook.createWorkbook(os);
			//WritableSheet sheet = workbook.createSheet("sheet1", 1);
			//WritableSheet sheet1 = workbook.createSheet("sheet2", 2);
			// 创建表头
			//createOneRowFromStringArr(sheet,0,title);
			//***************************************************************
			int len=60000;
			int size = list.size();  
			int count = (size + len - 1) / len;  
			if(size==0){
				//创建sheet
				WritableSheet sheet = workbook.createSheet("sheet", 1);
				// 创建表头
				createOneRowFromStringArr(sheet,0,title);
			}else {
				for (int i = 0; i < count; i++) {
					int index=i+1;
					//创建sheet
					WritableSheet sheet = workbook.createSheet("sheet"+index, i+1);
					// 创建表头
					createOneRowFromStringArr(sheet,0,title);
					java.util.List subList =   list.subList(i * len, ((i + 1) * len > size ? size : len * (i + 1)));  
					for(int k=0;k<subList.size();k++){
						if ( subList.get(k) instanceof Map) {
							createOneRowFromMap(sheet, k + 1, subList.get(k),fields,cellTypes);
						}else {
							createOneRowFromBean(sheet, k + 1, subList.get(k),fields,cellTypes);
						}
					}
				} 
			}
			
			//***************************************************************
			workbook.write();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtil.close(workbook);
			IOUtil.close(os);
		}
	}

	/**
	 * 根据pojo对象创建excel表格一行数据
	 * 
	 * @param sheet
	 * @param format
	 * @param row
	 * @param obj
	 *            数据对象
	 * @param fields
	 *            数据对象字段
	 */
	private void createOneRowFromBean(WritableSheet sheet, int row, Object obj, String[] fieldsName,
			String[] fieldExcelTypes) {
		int column = 0;
		Method[] methods = obj.getClass().getDeclaredMethods();
		for (int i = 0; i < fieldsName.length; i++) {
			for (int j = 0; j < methods.length; j++) {
				String methodName = methods[j].getName();
				if (methodName.equalsIgnoreCase("get" + fieldsName[i])) {
					try {
						Object objInvoke = methods[j].invoke(obj);
						if (objInvoke == null) {
							sheet.addCell(new Label(column++, row, " ", this.STRING_CELL));
						} else {
							String excelType = fieldExcelTypes[i];
							if (excelType.equals("STRING")) {// 添加文本
								sheet.addCell(new Label(column++, row, objInvoke.toString(), this.STRING_CELL));
							} else if (excelType.equals("INTEGER")) {// 添加整数
								sheet.addCell(new Number(column++, row, (Integer) objInvoke, this.INTEGER_CELL));
							} else if (excelType.equals("FLOAT")) {// 添加浮点数，double
																	// float
								sheet.addCell(new Number(column++, row, (Double) objInvoke, this.FLOAT_CELL));
							} else if (excelType.equals("PERCENT_INTEGER")) {// 添加整数百分比
								// 传出来的数据是乘以100后的百分比，excel百分比格式会自动乘以100
								Integer intPercent = ((Integer) objInvoke) / 100;
								sheet.addCell(
										new Number(column++, row, (Integer) intPercent, this.PERCENT_INTEGER_CELL));
							} else if (excelType.equals("PERCENT_FLOAT")) {// 添加浮点数百分比
								// 传出来的数据是乘以100后的百分比，excel百分比格式会自动乘以100
								// Double doublePercent=((Double)
								// objInvoke)/100;
								Double doublePercent = ((Double) objInvoke) / 100;
								sheet.addCell(new Number(column++, row, doublePercent, this.PERCENT_FLOAT_CELL));
							} else if (excelType.equals("DATE")) {// 添加日期年月日格式
								sheet.addCell(new DateTime(column++, row, (java.util.Date) objInvoke, this.DATE_CELL));
							} else if (excelType.equals("DATE_TIME")) {// 添加日期年月日时分秒格式
								sheet.addCell(
										new DateTime(column++, row, (java.util.Date) objInvoke, this.DATETIME_CELL));
							} else {
								sheet.addCell(new Label(column++, row, objInvoke.toString(), this.STRING_CELL));
							}
						}
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					} catch (RowsExceededException e) {
						e.printStackTrace();
					} catch (WriteException e) {
						e.printStackTrace();
					}
				} else {
					continue;
				}
			}
		}
	}

	/**
	 * 根据pojo对象创建excel表格一行数据
	 * 
	 * @param sheet
	 * @param format
	 * @param row
	 * @param obj
	 *            数据对象
	 * @param fields
	 *            数据对象字段
	 */
	private void createOneRowFromMap(WritableSheet sheet, int row, Object obj, String[] fieldsName,
			String[] fieldExcelTypes) {
		int column = 0;
		Map<String, Object> maps = (Map<String, Object>) obj;
		for (int i = 0; i < fieldsName.length; i++) {
			for (Map.Entry<String, Object> entry : maps.entrySet()) {
				String methodName = entry.getKey();
				if (methodName.equalsIgnoreCase(fieldsName[i])) {
					try {
						Object objInvoke = entry.getValue();
						if (objInvoke == null) {
							sheet.addCell(new Label(column++, row, " ", this.STRING_CELL));
						} else {
							String excelType = fieldExcelTypes[i];
							if (excelType.equals("STRING")) {// 添加文本
								sheet.addCell(new Label(column++, row, objInvoke.toString(), this.STRING_CELL));
							} else if (excelType.equals("INTEGER")) {// 添加整数
								sheet.addCell(new Number(column++, row, (Integer) objInvoke, this.INTEGER_CELL));
							} else if (excelType.equals("FLOAT")) {// 添加浮点数，double
																	// float
								sheet.addCell(new Number(column++, row, (Double) objInvoke, this.FLOAT_CELL));
							} else if (excelType.equals("PERCENT_INTEGER")) {// 添加整数百分比
								// 传出来的数据是乘以100后的百分比，excel百分比格式会自动乘以100
								Integer intPercent = ((Integer) objInvoke) / 100;
								sheet.addCell(
										new Number(column++, row, (Integer) intPercent, this.PERCENT_INTEGER_CELL));
							} else if (excelType.equals("PERCENT_FLOAT")) {// 添加浮点数百分比
								// 传出来的数据是乘以100后的百分比，excel百分比格式会自动乘以100
								// Double doublePercent=((Double)
								// objInvoke)/100;
								Double doublePercent = ((Double) objInvoke) / 100;
								sheet.addCell(new Number(column++, row, doublePercent, this.PERCENT_FLOAT_CELL));
							} else if (excelType.equals("DATE")) {// 添加日期年月日格式
								sheet.addCell(new DateTime(column++, row, (java.util.Date) objInvoke, this.DATE_CELL));
							} else if (excelType.equals("DATE_TIME")) {// 添加日期年月日时分秒格式
								sheet.addCell(
										new DateTime(column++, row, (java.util.Date) objInvoke, this.DATETIME_CELL));
							} else {
								sheet.addCell(new Label(column++, row, objInvoke.toString(), this.STRING_CELL));
							}
						}
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (RowsExceededException e) {
						e.printStackTrace();
					} catch (WriteException e) {
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					continue;
				}
			}
		}
	}

	private void createOneRowFromStringArr(WritableSheet sheet, int row, String[] rowDatas) {
		int len = rowDatas.length;
		for (int i = 0; i < len; i++) {
			try {
				sheet.addCell(new Label(i, row, rowDatas[i], this.STRING_CELL));
				sheet.setColumnView(i, 20);
			} catch (RowsExceededException e) {
				e.printStackTrace();
			} catch (WriteException e) {
				e.printStackTrace();
			}
		}
	}

}

class WritableCellFormatExt extends WritableCellFormat {
	public WritableCellFormatExt(WritableFont font) {
		super(font);
		try {
			this.setAlignment(Alignment.CENTRE);
			this.setVerticalAlignment(VerticalAlignment.CENTRE);
			this.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
		} catch (WriteException e) {
			e.printStackTrace();
		}
	}

	public WritableCellFormatExt(WritableFont font, DisplayFormat displayFormat) {
		super(font, displayFormat);
		try {
			this.setAlignment(Alignment.CENTRE);
			this.setVerticalAlignment(VerticalAlignment.CENTRE);
			this.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
		} catch (WriteException e) {
			e.printStackTrace();
		}
	}
}
