//----地图----------------------------------------------------------------------------------
var map;// 地图对象
var EARTH_RADIUS = 6378137.0;    //单位M
//经纬度距离转化为现实距离
var EARTH_LEN_MULTIPLES = EARTH_RADIUS * Math.PI / 180;
var baiduMap;

//初始化地图
function initMap() {
	SEGUtil.imageRootDir = "../resources/images/map";
	map = new SEGMap("map_canvas", "baidu");
	map.centerAndZoom(113.940301, 22.535572, 16);
	baiduMap = map.getOriMap();
}

//清除地图上留下的所有图标
function clearAllMarker(){
	map.clearNonStaticMarkers();
	map.clearVehicleMarkers();
	map.closeAllInfoWindow();	
}

$(function(){
    var routeManage=window.routeManage={};
    routeManage.centerLayout = $("#center-layout").ligerLayout({
        leftWidth:284,
        onEndResize:function(){
            $(window).trigger('resize');
        }
    });
   
    routeManage.enterpriseVehicleLayout = $("#enterprise-vehicle-layout").ligerLayout({
        topHeight:110,
        onEndResize:function(){
            $(window).trigger('resize');
        }
    });
    routeManage.rightLayout = $("#right-layout").ligerLayout({
        leftWidth:284,
        onEndResize:function(){
            resizePage();
        }
    });
    
   $('input').focus(function(){
        $(this).css('border',"1px solid #008dea");
    })
   $('input').blur(function(){
        $(this).css('border',"1px solid #ccc");
    });
   //清除地图上的标注
   var str='';
   str+='<div id="GPSTR"><img src="../images/carGIS/GPS7.png" />';
   str+='<a href="javascript:;" style="display:inline-block;line-height:24px;" class="qingkongtb">清除标注</a></div>';
   $('#map_canvas').append(str);

   $('.qingkongtb').click(function(){
       clearAllMarker();   
   })
});

//地图上画出路线
function drawLine(data) {
	var linePoints = [];
	if (data != null && data.length > 0) {
		for (var i = 0; i < data.length; i++) {
			linePoints.push(data[i].lon + "," + data[i].lat);
		}
		var points = [];
		for (var i = 0; i < linePoints.length; i++) {
			var ps = linePoints[i].split(",");
			var p = new SEGPoint(parseFloat(ps[0]), parseFloat(ps[1]));
			points.push(p);
		}
		var polyline = map.newPolyline(points, null, "#FF0000", 3, 0.8);
		map.addMarker(polyline);
		var startPoint = points[0];
		map.centerAndZoom(startPoint.lon, startPoint.lat, 16);
	}
}

//地图上画点
function drawPoints(data) {
	var linePoints = [];
	if (data != null && data.length > 0) {
		for (var i = 0; i < data.length; i++) {
			linePoints.push(data[i].lon + "," + data[i].lat);
		}
		for (var i = 0; i < linePoints.length; i++) {
			var ps = linePoints[i].split(",");
			var point = new BMap.Point(parseFloat(ps[0]),parseFloat(ps[1]));
			baiduMap.centerAndZoom(point,18);
			var marker = new BMap.Marker(point);// 创建标注
			baiduMap.addOverlay(marker);
			baiduMap.centerAndZoom(parseFloat(ps[0]), parseFloat(ps[1]), 18);
		}
	}
}
//地图上画跳动元素
function drawFlash(lon,lat){
	clearMap();
	var point = new BMap.Point(lon,lat);
	map.centerAndZoom(point, 18);
	var myIcon = new BMap.Icon("./images/markers.png", new BMap.Size(23, 25), {
	    offset: new BMap.Size(10, 25),
	    imageOffset: new BMap.Size(0, 0 - 11 * 25)

	  });
	var marker = new BMap.Marker(point,{icon:myIcon});  // 创建标注
	baiduMap.addOverlay(marker);               // 将标注添加到地图中
	marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
}
function clearMap(){
	baiduMap.clearOverlays();
}
//地图上画点加标注
function drawPointsAndLabel(data) {
	baiduMap.clearOverlays();
	var linePoints = [];
	if (data != null && data.length > 0) {
		for (var i = 0; i < data.length; i++) {
			linePoints.push(data[i].lon + "," + data[i].lat);
		}
		for (var i = 0; i < linePoints.length; i++) {
			var ps = linePoints[i].split(",");
			var point = new BMap.Point(parseFloat(ps[0]),parseFloat(ps[1]));
			baiduMap.centerAndZoom(point,16);
			var marker = new BMap.Marker(point);// 创建标注
			var opts = {
					  position : point,    // 指定文本标注所在的地理位置
					  offset   : new BMap.Size(10, -30)    //设置文本偏移量
					}
			var label = new BMap.Label(i, opts);  // 创建文本标注对象
			label.setStyle({
				 color : "red",
				 fontSize : "18px",
				 height : "0px",
	             width:"0px",
	             lineHeight : "20px",
				 fontFamily:"微软雅黑",
				 border:"0px"
			 });
			baiduMap.addOverlay(label);   
			baiduMap.addOverlay(marker);
			baiduMap.centerAndZoom(parseFloat(ps[0]), parseFloat(ps[1]), 18);
		}
	}
}
//获取参考位置
function getRefPosition(lon,lat){//百度api获取参考位置
	var address = "";
	if(lon == null || lat == null){
		address = "参考位置为空";
	}
	var point = new BMap.Point(lon,lat);
	var geoc = new BMap.Geocoder();   
	geoc.getLocation(point, function(rs){
		var addComp = rs.addressComponents;
		address = addComp.province + addComp.city + addComp.district + addComp.street + addComp.streetNumber;
	});
	return address;
}




