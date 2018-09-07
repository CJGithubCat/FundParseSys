package com.zsh.labouCapital.util;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 读取配置项<br/>
 * 传入的 key 需带上文件名前缀（包括文件路径），例如：<br/>
 * classpath/test.properties 存在 a=b，读取方式：Config.get("test.a");<br/>
 * classpath/path/test.properties 存在 a=b，读取方式：Config.get("path.test.a");
 * 
 * @author zhangming
 *
 */
public class Config {
	public static void main(String[] args) {
		for (Entry<Object, Object> entry : PS.entrySet()) {
			System.out.println(entry.getKey().toString() + '=' + entry.getValue());
		}
	}

	private static Logger logger = Logger.getLogger(Config.class);
	private static Properties PS = new Properties();
	private static FileFilter configFilter = new FileFilter() {
		@Override
		public boolean accept(File dir) {
			return dir.isDirectory() || dir.getAbsolutePath().endsWith(".properties");
		}
	};
	private static String ROOT;
	static {
		try {
			ROOT = new File(Config.class.getClassLoader().getResource("").getPath()).getCanonicalPath()
					+ File.separatorChar;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	static {
		load("config.properties");
		 load("data.properties");
		 load("memcached.properties");
		for (String str : search(configFilter, new File(ROOT))) {
			load(str);
		};
	}

	/**
	 * 递归地扫描指定目录，得到符合条件的文件绝对路径列表
	 * 
	 * @param path
	 *            指定目录，如果为空则扫描整个 classpath
	 * @param filter
	 * @return
	 */
	private static HashSet<String> search(FileFilter filter, File path) {
		HashSet<String> hs = new HashSet<String>();
		if (path.isFile()) {
			try {
				hs.add(path.getCanonicalPath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (path.isDirectory()) {
			for (File f : path.listFiles(filter)) {
				hs.addAll(search(filter, f));
			}
		}
		return hs;
	}

	public static void load(String path) {
		logger.debug("start loading config: " + path);
		if (path.startsWith(ROOT)) {
			path = path.substring(ROOT.length());
		}
		// path = path.replaceAll("^[.]{" + ROOT.length() + "}(.+)$", "$1");
		String bf = path.substring(0, path.lastIndexOf('.') + 1).replaceAll('\\' + File.separator, ".");
		InputStream fis = null;
		try {
			Properties _p = new Properties();
			fis = Config.class.getClassLoader().getResourceAsStream(path);
			_p.load(fis);
			// _p.list(System.out);
			for (Entry<Object, Object> entry : _p.entrySet()) {
				PS.setProperty(bf + entry.getKey().toString(),
						new String(entry.getValue().toString().getBytes("iso-8859-1"), "utf-8"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fis == null) {
				logger.warn("不存在的路径：" + path);
			}
			try {
				fis.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		logger.debug("load succeed");
	}

	/**
	 * 获取一个配置项
	 * 
	 * @param key
	 * @return
	 */
	public static String get(String key) {
		return PS.getProperty(key);
	}

	/**
	 * 强转为 整数
	 * 
	 * @param key
	 * @return
	 */
	public static int getInt(String key) {
		return Integer.parseInt(PS.getProperty(key));
	}

	/**
	 * 强转为 长整数
	 * 
	 * @param key
	 * @return
	 */
	public static long getLong(String key) {
		return Long.parseLong(PS.getProperty(key));
	}

}
