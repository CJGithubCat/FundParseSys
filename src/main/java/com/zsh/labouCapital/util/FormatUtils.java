package com.zsh.labouCapital.util;
import java.util.ArrayList;
/**
 * 读取配置文件
 * 
 * @author Ben
 * 
 */
public class FormatUtils {
	private FormatUtils() {
		
	}

	/**
     * 函数功能：解析得到首页的统计数据的每一位的数字；6位；
     * 备注：如：564得到：HashMap:num1=5,num1=6,num1=4
     * */
    public static ArrayList<String> ParseIndexCountNum(long num){
    	String count = num + "";
    	ArrayList<String> list = new ArrayList<String>();
    	for(int i =0; i < count.length(); i++){
    		list.add(count.substring(i,i+1));
    	}
    	while(list.size() < 6){
    		list.add(0, "0");
    	}    	
    	return list;
    }

    public static void main(String[] args) {
    	ParseIndexCountNum(2);
	}
}