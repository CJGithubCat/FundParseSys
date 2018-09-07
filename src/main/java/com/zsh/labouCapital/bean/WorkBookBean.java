package com.zsh.labouCapital.bean;

import java.util.HashMap;

import jxl.write.WritableCellFormat;

public class WorkBookBean {
	private String fileName;
	private String sheetName;
	private WritableCellFormat headFormat;
	private WritableCellFormat cellFormat;
	private HashMap<String, Object> cellInfo;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getSheetName() {
		return sheetName;
	}
	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}
	public WritableCellFormat getHeadFormat() {
		return headFormat;
	}
	public void setHeadFormat(WritableCellFormat headFormat) {
		this.headFormat = headFormat;
	}
	public WritableCellFormat getCellFormat() {
		return cellFormat;
	}
	public void setCellFormat(WritableCellFormat cellFormat) {
		this.cellFormat = cellFormat;
	}
	
	public HashMap<String, Object> getCellInfo() {
		return cellInfo;
	}
	public void setCellInfo(HashMap<String, Object> cellInfo) {
		this.cellInfo = cellInfo;
	}
	@Override
	public String toString() {
		return "WorkBookBean [fileName=" + fileName + ", sheetName=" + sheetName + ", headFormat=" + headFormat
				+ ", cellFormat=" + cellFormat + ", cellInfo=" + cellInfo + "]";
	}
}
