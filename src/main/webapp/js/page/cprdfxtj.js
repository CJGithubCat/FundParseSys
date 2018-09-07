//----地图----------------------------------------------------------------------------------
var map;// 地图对象
var EARTH_RADIUS = 6378137.0;    //单位M
//经纬度距离转化为现实距离
var EARTH_LEN_MULTIPLES = EARTH_RADIUS * Math.PI / 180;

//初始化地图
function initMap() {
	SEGUtil.imageRootDir = "../resources/images/map";
	map = new SEGMap("map_canvas", "baidu");
	map.centerAndZoom(113.940301, 22.535572, 16);
}

function drawArea(data, shape) {
	clearAllMarker();
	// 圆形
	if (shape == 1) {
		var lon = data.point.split(',')[0];
		var lat = data.point.split(',')[1];
		var r = data.point.split(',')[2];
		var temp_circle = map.newCircle(lon, lat, r);
		map.centerAndZoom(lon, lat, data.mapZoom);
		map.addMarker(temp_circle);
		//map.setCenter(lon, lat);
		//map.setZoom(data.mapZoom);
		//map.addMarker(temp_circle);
		
	// 矩形
	} else if (shape == 2) {
		var new_rect = map.newRectangle(data.lon1, data.lat1, data.lon2,
				data.lat2);
		map.addMarker(new_rect);
       //map.setCenter(data[0].lon, data[0].lat);
		map.centerAndZoom(data.lon1, data.lat1, data.mapZoom);
	//多边形
	} else if (shape == 3) {
		var points=data.point.split(';');
		var linePoints = [];
		for (var i = 0; i < points.length-1; i++) {
			var lonLat=points[i].split(',');
			linePoints.push(lonLat[0] + "," + lonLat[1]);
		}
		var points = [];
		for (var i = 0; i < linePoints.length; i++) {
			var ps = linePoints[i].split(",");
			var p = new SEGPoint(parseFloat(ps[0]), parseFloat(ps[1]));
			points.push(p);
		}
		var new_polygon = map.newPolygon(points);
		map.addMarker(new_polygon);
        //console.log(points[0].lon);
		map.centerAndZoom(points[0].lon, points[0].lat, data.mapZoom);
	}
}

//画圆形
function drawCircle(lon,lat,r,zoom){
	clearAllMarker();
	var temp_circle = map.newCircle(lon, lat, r);
	map.centerAndZoom(lon, lat, zoom);
	map.addMarker(temp_circle);
}

//清除地图上留下的所有图标
function clearAllMarker(){
	map.clearNonStaticMarkers();
	map.clearVehicleMarkers();
	map.closeAllInfoWindow();
}

//关闭修改弹框
function closeUpdate(){
    closeDialog('update-dialog');
    clearAllMarker();
}

//关闭增加弹框
function closeAdddate(){
    closeDialog('add-dialog');
    clearAllMarker();    
}