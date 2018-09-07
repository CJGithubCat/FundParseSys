//类功能：GPS工具类
window.GPSUtil ={};
GPSUtil.baiduGps = {};
GPSUtil.gpsToBaiduGps=function(lon,lat,from,to,callback){
	//gps坐标
	var ggPoint = new BMap.Point(lon,lat);
	var convertor = new BMap.Convertor();
    var pointArr = [];
    pointArr.push(ggPoint);
    convertor.translate(pointArr, from, to, function(data){
    	GPSUtil.baiduGps = {};
    	GPSUtil.baiduGps.lon = 0;//经度
    	GPSUtil.baiduGps.lat = 0;//纬度
//    	console.log(data.points);//////////////////////
    	if(data.status == 0){
    		GPSUtil.baiduGps.lon = data.points[0].lng;//经度
        	GPSUtil.baiduGps.lat = data.points[0].lat;//纬度
    	}if(data.status == 4){
    		GPSUtil.baiduGps.lon = lon;//经度
        	GPSUtil.baiduGps.lat = lat;//纬度
    	}
    	callback && callback(GPSUtil);
    	//console.log(GPSUtil.baiduGps);
    });
};
GPSUtil.test=function(){
	this.gpsToBaiduGps(114.277443,22.68367,1,5);
}