package com.zsh.labouCapital.service;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.zsh.labouCapital.bean.WorkBookBean;

import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;

public interface FileUtilService {
	public void exportExcel(HttpServletResponse response,WorkBookBean wbBean,List list);
	void createTitle(String[] title, WritableSheet sheet, WritableCellFormat headFormat, int i);
	void createBody(WritableSheet sheet, WritableCellFormat cellFormat, int i, Object object, String[] fields);
	
}
