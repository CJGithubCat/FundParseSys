package com.zsh.labouCapital.util.fund;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;

import com.zsh.labouCapital.entity.MarketSituation;
import com.zsh.labouCapital.util.HttpUtil;

import jxl.Sheet;
import jxl.Workbook;

/**
 * Excel导出通用工具类
 * 
 * @author zhouwei 2015-6-29
 */
public class FundMarketSituationUtil {

    
    /**
     * @Title: downLoadFile   
     * @Description: TODO   
     * @param: @param url
     * @param: @param path      
     * @return: void      
     * @throws
     */
    public static String requestHtmlOrJsFile(String url,List<NameValuePair> params) {
        try {
            String respBody = HttpUtil.get(url, params);
            return respBody;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * @Title: downLoadFile
     * @Description: 从网上下载文件，存储到本地
     * @param: @param url
     * @param: @param path
     * @return: void
     * @throws
     */
    public static void downLoadFile(String url, String path) {
        try {
            URL fileUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) fileUrl.openConnection();
            // 设置超时间为3秒
            conn.setConnectTimeout(10 * 1000);
            // 防止屏蔽程序抓取而返回403错误
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

            // 获取输入流
            InputStream inputStream = conn.getInputStream();
            // 获取自己数组
            byte[] getData = readInputStream(inputStream);
            // 文件保存位置
            SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
            String newDir = path + File.separator + sdf.format(new Date());
            File saveDir = new File(newDir);
            if (!saveDir.exists()) {
                saveDir.mkdir();
            }

            String fileName = url.substring(url.lastIndexOf("/") + 1);
            File file = new File(saveDir + File.separator + fileName);
            if (file.exists()) {
                System.out.println("文件已经存在，删除之前的文件！");
                file.delete();
            }
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(getData);
            if (fos != null) {
                fos.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }

            System.out.println("info:" + url + "  fileName:" + fileName + " download success");

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @Title: parseMarketSituationInfo
     * @Description: TODO
     * @param: @return
     * @return: List<MarketSituation>
     * @throws
     */
    public static List<MarketSituation> parseMarketSituationInfo(String dirPath) {
        List<MarketSituation> reList = new ArrayList<>();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            // 1.读取excel路径下所有的文件
            File dir = new File(dirPath);
            File[] fileArray = dir.listFiles();
            List<String> pathList = new ArrayList<String>();
            for (int i = 0; i < fileArray.length; i++) {
                File file = fileArray[i];
                pathList.add(file.getPath());
            }
            System.out.println(pathList);
            // 2.解析excel文件内容
            InputStream fileIn = null;
            for (int j = 0; j < pathList.size(); j++) {
                String filePath = pathList.get(j);
                fileIn = new FileInputStream(filePath);
                Workbook workBook = Workbook.getWorkbook(fileIn);

                // 1.sheetsize
                int sheeSize = workBook.getNumberOfSheets();
                if (sheeSize <= 0) {
                    System.out.println("文档sheet为空...");
                    return null;
                }

                Sheet sheet = workBook.getSheet(0);
                int rows = sheet.getRows();
                int colums = sheet.getColumns();
                for (int r = 1; r < rows; r++) {
                    MarketSituation marketSituation = new MarketSituation();
                    for (int c = 0; c < colums; c++) {
                        String contet = sheet.getCell(c, r).getContents();
                        
                        if(c == 0){//日期
                            System.out.println(sdf.parse(contet).getTime());
                            
                            marketSituation.setInfoDate(sdf.format(new Date(sdf.parse(contet).getTime())));
                        }
                        if(c == 1){//fundCode
                            marketSituation.setIndexCode(contet);
                        }
                        if(c == 2){//中文全称
                            marketSituation.setIndexFname(contet);
                        }
                        if(c == 3){//中文名简称
                            marketSituation.setIndexSname(contet);
                        }
                        if(c == 6){//开盘
                            marketSituation.setOpenPoint(contet);
                        }
                        if(c == 7){//最高
                            marketSituation.setHighestPoint(contet);
                        }
                        if(c == 8){//最低
                            marketSituation.setLowestPoint(contet);
                        }
                        if(c == 9){//收盘
                            marketSituation.setClosePoint(contet);
                        }
                        if(c == 10){//涨跌
                            marketSituation.setRiseFall(contet);
                        }
                        if(c == 11){//涨跌幅
                            marketSituation.setRiseFallRange(contet);
                        }
                        if(c == 12){//成交量
                            marketSituation.setDealAmount(contet);
                        }
                        if(c == 13){//成交金额
                            marketSituation.setDealMoney(contet);
                        }
                        if(c == 14){//成交股数
                            marketSituation.setStockMemberNum(Integer.parseInt(contet));
                        }
                        if(c == 15){//市盈率1
                            marketSituation.setPe1Ratio(Double.parseDouble(contet));
                            if(marketSituation.getPe1Ratio() != 0){
                                marketSituation.setEarnRatio(1/marketSituation.getPe1Ratio());
                            }
                        }
                        if(c == 16){//市盈率2
                            marketSituation.setPe2Ratio(Double.parseDouble(contet));
                        }
                        if(c == 17){//股息率1
                            marketSituation.setDp1Ratio(Double.parseDouble(contet));
                        }
                        if(c == 18){//股息率2
                            marketSituation.setDp2Ratio(Double.parseDouble(contet));
                        }
                    }
                    reList.add(marketSituation);
                }
                System.out.println(reList);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return reList;
    }

    /**
     * 从输入流中获取字节数组
     * 
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }

    /**
     * 样式设置
     */
    public static HSSFCellStyle createCellStyle(HSSFWorkbook workbook) {
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
    public static HSSFFont createCellFont(HSSFWorkbook workbook) {
        // *生成一个字体
        HSSFFont font = workbook.createFont();
        // 字体颜色
        font.setColor(HSSFColor.BLACK.index);
        // 字体大小
        font.setFontHeightInPoints((short) 12);
        // 字体加粗
        // 是否使用斜体
        font.setItalic(false);
        // 是否使用划线
        // font.setStrikeout(true);
        // 字体名字
        font.setFontName(" 宋体 ");
        return font;
    }

    /**
     * 导出EXCEL
     */
    public static void excel(List<Map<String, Object>> list, String[] head, String excelName, HttpServletResponse response, int rowNumber,
            String TittleName) throws Exception {
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

        HSSFRow row0 = sheet.createRow(0); // --->创建一行
        sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) rowNumber));
        row0.setHeightInPoints(25);
        HSSFCell cell1 = row0.createCell(0); // --->创建一个单元格
        cell1.setCellValue(TittleName);
        cell1.setCellStyle(style);
        // 标题结束
        // 字段开始
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
        for (int i = 1, k = list.size() + 1; i < k; i++) {
            // 创建新行(row)
            row = sheet.createRow(i + 1);
            // 设置行高
            row.setHeightInPoints(20);
            // 设置图片所在列宽度为80px,
            sheet.setColumnWidth(i, (short) (35.7 * 160));
            for (int j = 0; j < head.length; j++) {
                HSSFCell cell = row.createCell(j);
                Map<String, Object> map = (Map<String, Object>) list.get(i - 1);
                HSSFRichTextString text = new HSSFRichTextString(String.valueOf(map.get(String.valueOf(j))));
                cell.setCellStyle(style);
                cell.setCellValue(text);
            }
        }
        response.setContentType("applicationnd.ms-excel");
        String fileName = new String(excelName.getBytes("utf-8"), "ISO-8859-1");
        response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xls");
        workbook.write(response.getOutputStream());
        response.getOutputStream().flush();
        response.getOutputStream().close();
    }

    public static void main(String[] args) {
        // String path = "G:\\test\\fundexcel";
        // ExcelUtil.downLoadFile("http://www.csindex.com.cn/uploads/file/autofile/perf/000001perf.xls ", path);

        String dir = "G:\\test\\fundexcel\\180914";
        FundMarketSituationUtil.parseMarketSituationInfo(dir);
    }
}
