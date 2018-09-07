package com.zsh.labouCapital.service.impl;


import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.zsh.labouCapital.bean.WorkBookBean;
import com.zsh.labouCapital.service.FileUtilService;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
@Service("fileUtilService")
public class FileUtilServiceImpl implements FileUtilService {
	/**
	 * 函数功能：导出excel文件；
	 * */
	@Override
	public void exportExcel(HttpServletResponse response,WorkBookBean wbBean,List list){
		OutputStream os = null;
		WritableWorkbook workbook = null;
		
		try {
			os = response.getOutputStream();
			response.setHeader("Content-disposition", "attachment;filename="+wbBean.getFileName());
			response.setContentType("application/excel");
			
			workbook = Workbook.createWorkbook(os);
			WritableSheet sheet = workbook.createSheet(wbBean.getSheetName(), 1);
			//组装表头信息
			HashMap<String, Object> cellinfo = wbBean.getCellInfo();
			String title[] = new String[cellinfo.size()];
			String[] fields = new String[cellinfo.size()];
			Set set = cellinfo.keySet();
			Iterator<?> iterator = set.iterator();
			int k = 0;
			while(iterator.hasNext()){
				fields[k] = (String)iterator.next();
				title[k] = (String) cellinfo.get(fields[k]);
				k++;
			}
			//创建表头
			createTitle(title, sheet, wbBean.getHeadFormat(), 0);
			for(int i = 0; i < list.size(); i++){
				createBody(sheet, wbBean.getCellFormat(), i+1, list.get(i), fields);
			}
			workbook.write();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(workbook != null){
					workbook.close();
				}
				if(os != null){
					os.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	/**
	 * 函数功能：创建excel表格的标题
	 * @param title 标题组成的数组
	 * @sheet 
	 * @param format 单元格样式
	 * @param row 标题栏的行数
	 * */
	@Override
	public void createTitle(String[] title, WritableSheet sheet, WritableCellFormat headFormat, int row) {
		int len = title.length;			
		for(int i = 0; i < len; i++){
			try {
				sheet.addCell(new Label(i,row,title[i],headFormat));
				sheet.setColumnView(i, 20);
			} catch (RowsExceededException e) {
				e.printStackTrace();
			} catch (WriteException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 函数功能： 创建excel表格单元
	 * @param 创建表格主体
	 * @param format 单元格的样式
	 * @param row 单元格的行数
	 * @param obj 数据对象
	 * @param 与标题对应的字段数组
	 * */
	@Override
	public void createBody(WritableSheet sheet, WritableCellFormat cellFormat, int row, Object obj, String[] fieldSort) {
		int column =0;
		ArrayList<Method> methods = new ArrayList<Method>();
		Method[] methodarr = obj.getClass().getDeclaredMethods();
		methods = getSortMethods(methodarr,fieldSort);
		String value ="";
		for (int i = 0; i < methods.size(); i++) {
			try {
				Object ss = (methods.get(i)).invoke(obj);
				if(ss == null){
					value = "";
				}else{
					value = ss.toString();
				}
				sheet.addCell(new Label(column++, row,value, cellFormat));
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
		}
	}
	
	/**
	 * 函数功能：获取正确顺序的getter方法；
	 * @param ms 对象的函数数组
	 * @param fieldSort 排序之后与标题对应的字段数组
	 * */
	public ArrayList<Method> getSortMethods(Method[] ms,String[] fieldSort){
		ArrayList<Method> methods = new ArrayList<Method>();
		String mname = "";

		for(int i = 0;i < ms.length; i++){
			if(ms[i].getName().contains("get")){				
				methods.add(ms[i]);
			}
		}
		for(int k = 0; k< fieldSort.length;k++){
			for(int j = 0; j< methods.size();j++){
				mname = methods.get(j).getName();
				if(mname.contains("get")&&(mname.substring(3).toLowerCase().equals(fieldSort[k].toLowerCase()))){
					Method temm = methods.get(k);
					methods.set(k, methods.get(j));
					methods.set(j, temm);
					continue;
				}
			}
		}		
		return methods;
	}
}
