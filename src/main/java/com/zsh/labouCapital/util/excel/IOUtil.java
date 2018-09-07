package com.zsh.labouCapital.util.excel;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class IOUtil {
	public static void closeOS(OutputStream os){
		if(os != null){
			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void closeIS(InputStream is){
		if(is != null){
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void close(Closeable c){
		if(c != null){
			try {
				c.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void close(WritableWorkbook workbook) throws WriteException{
		if(workbook != null){
			try {
				workbook.close();
			} catch (IOException e) {
				e.printStackTrace();
			} /*catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}
	}
}
