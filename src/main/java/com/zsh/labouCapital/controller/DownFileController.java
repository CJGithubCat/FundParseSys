package com.zsh.labouCapital.controller;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 	文件模板下载
 * @author zhouwei 2015-7-2
 */
@Controller
@RequestMapping(value="/down/file")
public class DownFileController {
	@RequestMapping(value = "/findFileByFile", method = RequestMethod.GET)
	public  @ResponseBody List<Map<String,Object>>  findFileByFile(HttpServletRequest request){
		String filePathName=request.getParameter("filePathName");
		String path=request.getSession().getServletContext().getRealPath(filePathName);
		File file = new File(path);   
        //返回此抽象路径下的文件  
        File[] files = file.listFiles(); 
		List<Map<String,Object>> ls=new ArrayList<Map<String,Object>>();
		Map<String,Object> map2=null;
        if (files != null) {
        	 for (int i = 0; i < files.length; i++) {   
        	        //判断此文件是否是一个文件  
        		 if (!files[i].isDirectory()) { 
        			 	map2=new HashMap<String, Object>();
        			 	String str=files[i].getName();
        			 	String fileType=str.substring(str.lastIndexOf(".")+1);
        			 	String fileName=str.substring(0, str.lastIndexOf("."));
        			 	map2.put("id", i+1);
        			 	map2.put("fileName", fileName);
        			 	map2.put("fileType", fileType);
        			 	map2.put("file", files[i].getName());
        			 	ls.add(map2);
        	       }   
        	}
        }  
		return ls;
	}
	
	
	@RequestMapping(value = "/downFile", method = RequestMethod.GET)
	public void downFile(HttpServletRequest request,HttpServletResponse response) throws Exception{
		//设置响应给前台内容的数据格式
        response.setContentType("text/plain; charset=UTF-8");
		String filePathName=request.getParameter("filePathName");
		String fileName=new String(request.getParameter("fileName").getBytes("ISO-8859-1"),"utf-8");
		String path=request.getSession().getServletContext().getRealPath(filePathName);
		 File file = new File(path+"/"+fileName);// path是根据日志路径和文件名拼接出来的
		   // String filename = file.getName();// 获取日志文件名称
		    InputStream fis = new BufferedInputStream(new FileInputStream(path+"/"+fileName));
		    byte[] buffer = new byte[fis.available()];
		    fis.read(buffer);
		    fis.close();
		    response.reset();
		    // 先去掉文件名称中的空格,然后转换编码格式为utf-8,保证不出现乱码,这个文件名称用于浏览器的下载框中自动显示的文件名
		    response.addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.replaceAll(" ", "").getBytes("utf-8"),"iso8859-1"));
		    response.addHeader("Content-Length", "" + file.length());
		    OutputStream os = new BufferedOutputStream(response.getOutputStream());
		    response.setContentType("application/octet-stream");
		    os.write(buffer);// 输出文件
		    os.flush();
		    os.close();
		
	}
	
	
	

}
