package com.zsh.labouCapital.util;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;
/**
 * 	Excel导出通用工具类
 * @author zhouwei 2015-6-29
 */
public class ExcelUtil {
	/**
	 * 样式设置
	 */
	public static HSSFCellStyle createCellStyle(HSSFWorkbook workbook){
		// *生成一个样式
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置这些样式
        // 前景色
        style.setFillForegroundColor(HSSFColor.WHITE.index);
        // 背景色
        style.setFillBackgroundColor(HSSFColor.WHITE.index);
        // 填充样式
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        // 设置底边框
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        // 设置底边框颜色
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        // 设置左边框
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        // 设置左边框颜色
  	  	style.setLeftBorderColor(HSSFColor.BLACK.index);
        // 设置右边框
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        // 设置右边框颜色
  	  	style.setRightBorderColor(HSSFColor.BLACK.index);
        // 设置顶边框
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        // 设置顶边框颜色  
  	  	style.setTopBorderColor(HSSFColor.BLACK.index);
  	  	// 设置自动换行
  	  	style.setWrapText(false);
  	  	// 设置水平对齐的样式为居中对齐
  	  	style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
  	  	// 设置垂直对齐的样式为居中对齐
  	  	style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		return style;
	}
	

	/**
	 * 字体设置
	 */
	public static HSSFFont createCellFont(HSSFWorkbook workbook){
		// *生成一个字体
        HSSFFont font = workbook.createFont();
        // 字体颜色
        font.setColor(HSSFColor.BLACK.index);
        // 字体大小
        font.setFontHeightInPoints((short) 12);
        // 字体加粗
        // 是否使用斜体
        font.setItalic( false );
        // 是否使用划线
        //font.setStrikeout(true);
        // 字体名字
        font.setFontName( " 宋体 " );
        return font;
	}
/**
	 * 导出EXCEL
	 */
	public static void excel(List<Map<String, Object>> list, String[] head, String excelName,HttpServletResponse response ,int rowNumber,String TittleName) throws Exception{
		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook(); 
		// 生成一个表格
	    HSSFSheet sheet = workbook.createSheet(excelName); 
	    // 设置表格默认列宽度为20个字节
        sheet.setDefaultColumnWidth(30);
        // 字体设置
        HSSFFont font = createCellFont(workbook);
        // 样式设置
        HSSFCellStyle style = createCellStyle(workbook);
        // 把字体应用到当前的样式
        style.setFont(font);
  
        HSSFRow row0 = sheet.createRow(0);   //--->创建一行  
        sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) rowNumber));  
        row0.setHeightInPoints(25);  
        HSSFCell cell1 = row0.createCell(0);   //--->创建一个单元格  
        cell1.setCellValue(TittleName); 
        cell1.setCellStyle(style);  
        //标题结束
        //字段开始
        HSSFRow row = sheet.createRow(1);
        row.setHeightInPoints(25);
        for (int i = 0; i < head.length; i++) {
        	// 创建单元格
        	HSSFCell cell = row.createCell(i);
        	// 获取内容
        	HSSFRichTextString text = new HSSFRichTextString(head[i]);
        	// 设置单元格内容
        	cell.setCellValue(text);
        	// 设置单元格样式
        	cell.setCellStyle(style);
        }
        // *遍历集合数据,产生表格数据行
	    for (int i=1, k=list.size()+1; i<k; i++) {
	    	// 创建新行(row)
	    	row = sheet.createRow(i+1);
	    	// 设置行高
	    	row.setHeightInPoints(20);
	    	// 设置图片所在列宽度为80px,
	    	sheet.setColumnWidth(i, (short)(35.7 * 160));
	    	for(int j =0;j<head.length;j++ ){
	    		HSSFCell cell =row.createCell(j);
	    		Map<String , Object> map=(Map<String, Object>) list.get(i-1);
	    		HSSFRichTextString text = new HSSFRichTextString(String.valueOf( map.get(String.valueOf(j)) ));
	    		cell.setCellStyle(style);
	    		cell.setCellValue(text);
	    	}
	    }
	    response.setContentType("applicationnd.ms-excel");
		String fileName = new String(excelName.getBytes("utf-8"),"ISO-8859-1");
		response.setHeader("Content-disposition", "attachment; filename="+fileName+".xls");
		workbook.write(response.getOutputStream());
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}
	
	
	
}
