var vehicle_map = null;
$(function(){
	//点击车辆分布按钮弹出聚合地图
	$("#viewVehiclesBtn").click(function(e) {
		openPop("vehicles", e, {
			title : '车辆分布',
			top : document.documentElement.scrollTop + 85,
//			height : 550,
			htmltxt : 'vehiclesMapDiv',
			resize:2
		});
		
		showVehiclesMapWidthData();
		document.getElementById("PopDivvehicles").onmouseover = function() {
			disable_scroll();
		}
		document.getElementById("PopDivvehicles").onmouseout = function() {
			enable_scroll();
		}
		
		
	});
	
});
function showVehiclesMapWidthData(){
	vehicle_map = new BMap.Map("vehiclesMapDiv");
	vehicle_map.centerAndZoom(new BMap.Point(106.025,27.963),7);
	vehicle_map.enableScrollWheelZoom();
	myMarkerClusterer();
}

function myMarkerClusterer() {
	$.ajax({
		url : 'VehicleServlet',
		data : 'method=queryVehicleLocationInfo&attributesPath=,1,2,3,',
		success : function(txt) {
			points_str = new Array();
			var json = eval("(" + txt + ")");
			if (json.success) {
				var data = json.data;
				if (data == null || data == "") {
					alert('数据为空!');
				} else {
					var markers = [];
					for (var i = 0; i < data.length; i++) {
						var lon = data[i].lon;
						var lat = data[i].lat;
						pt = new BMap.Point(lon, lat);
						markers.push(new BMap.Marker(pt));
					}
					// 最简单的用法，生成一个marker数组，然后调用markerClusterer类即可。
					var markerClusterer = new BMapLib.MarkerClusterer(vehicle_map, {
						markers : markers
					});
					vehicle_map.centerAndZoom(new BMap.Point(106.025,27.963),7);
				}

			} else {
				alert("加载失败!");
			}
		},
		error : function() {
			alert("error!");
		}
	})
}

