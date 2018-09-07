package com.zsh.labouCapital.comm;

/**
 * 类功能：系统常量类；
 * */
public class Constant {
	//坐标转换(标准坐标转化为百度坐标)URL
	/**[参数格式:经度，纬度]
	 * 示例:http://api.map.baidu.com/geoconv/v1/?from=1&to=5&ak=uDA20omIlYPHDvoLx7sxiubZnfjKFsnS&coords=114.277767,22.68325
	 * */
	public static final String GET_TRANS_BAIDUGPS_DEV = "http://api.map.baidu.com/geoconv/v1/?from=1&to=5&ak=uDA20omIlYPHDvoLx7sxiubZnfjKFsnS&coords=";//使用的ak限制次数为：6000次，测试用
	public static final String GET_TRANS_BAIDUGPS_DEPLOY = "http://api.map.baidu.com/geoconv/v1/?from=1&to=5&ak=uDA20omIlYPHDvoLx7sxiubZnfjKFsnS&coords=";//使用的ak通过域名验证，无次数限制，正式部署使用用
	
	//获取参考位置URL(百度经纬度获取参考位置)
	/**[参数格式:纬度，经度](百度经纬度)
	 * 示例:http://api.map.baidu.com/geocoder/v2/?ak=uDA20omIlYPHDvoLx7sxiubZnfjKFsnS&output=json&pois=1&location=22.686213,114.28915
	 * */
	public static final String GET_REFPOS_URL_DEV = "http://api.map.baidu.com/geocoder/v2/?ak=uDA20omIlYPHDvoLx7sxiubZnfjKFsnS&output=json&pois=0&location=";//使用的ak限制次数为：6000次，测试用
//	public static final String GET_REFPOS_URL_DEPLOY = "http://api.map.baidu.com/geocoder/v2/?ak=uDA20omIlYPHDvoLx7sxiubZnfjKFsnS&output=json&pois=0&location=";//使用的ak通过域名验证，无次数限制，正式部署使用用

	//获取参考位置URL (标准经纬度直接获取参考位置)
	/**[参数格式:纬度，经度](标准经纬度)
	 * 示例:http://api.map.baidu.com/geocoder/v2/?ak=uDA20omIlYPHDvoLx7sxiubZnfjKFsnS&output=json&pois=1&coordtype=wgs84ll&location=22.686213,114.28915
	 * */
	public static final String GET_REFPOS_WGS_URL_DEV = "http://api.map.baidu.com/geocoder/v2/?ak=uDA20omIlYPHDvoLx7sxiubZnfjKFsnS&output=json&pois=0&coordtype=wgs84ll&location=";

	//获取参考位置URL (高德地图转换获取参考位置)
		/**[参数格式:纬度，经度](标准经纬度)
		 * 示例:
		 * */											   
	public static final String GET_REFPOS_GD_WGS_URL_DEV = "http://restapi.amap.com/v3/geocode/regeo?key=06a0bad772a2f132d17bc24455de2ed6&batch=true&location=";
	
	//坐标转换(标准坐标转化为高德坐标)URL
		/**[参数格式:经度，纬度]
		 * 示例:http://restapi.amap.com/v3/assistant/coordinate/convert?locations=116.481499,39.990475&coordsys=gps&key=06a0bad772a2f132d17bc24455de2ed6
		 * 坐标转换(标准坐标转化为高德坐标)URL
		 * */
	public static final String GET_TRANS_GAODEGPS_DEV =  "http://restapi.amap.com/v3/assistant/coordinate/convert?key=06a0bad772a2f132d17bc24455de2ed6&coordsys=gps&output=json&locations=";
	
}
