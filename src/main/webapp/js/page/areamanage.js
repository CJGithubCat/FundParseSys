//----地图----------------------------------------------------------------------------------
var map;// 地图对象
var EARTH_RADIUS = 6378137.0; // 单位M
// 经纬度距离转化为现实距离
var EARTH_LEN_MULTIPLES = EARTH_RADIUS * Math.PI / 180;
// 初始化地图
function initMap() {
	SEGUtil.imageRootDir = "../resources/images/map";
	map = new SEGMap("map_canvas", "baidu");
	map.centerAndZoom(113.940301, 22.535572, 16);
}
/**
 * 地图上画圆形区域，并把百度坐标转换为GPS坐标
 * 
 * @param dialogId
 * @param pointsDataAreaId
 * @returns {Function}
 */
function convertCircleGspFromBdToStandard(dialogId, pointsDataAreaId) {
	var callback = function(lon, lat, r, marker) {
		map.removeMarker(marker);
		var new_circle = map.newCircle(lon, lat, r);
		map.addMarker(new_circle);
		// 计算圆外接矩形左下角顶点经纬度（lon1,lat1）,右上角顶点经纬度（lon2,lat2）
		var lon1 = (lon - r / EARTH_LEN_MULTIPLES).toFixed(6);
		var lat1 = (lat - r / EARTH_LEN_MULTIPLES).toFixed(6);
		var lon2 = (lon + r / EARTH_LEN_MULTIPLES).toFixed(6);
		var lat2 = (lat + r / EARTH_LEN_MULTIPLES).toFixed(6);

		$("#" + dialogId + " input[name='rad']").prop('value', r.toFixed(0));
		$("#" + dialogId + " input[name='mapZoom']").prop('value',
				map.getZoom());

		var centerPoint = new BMap.Point(lon, lat);
		var leftBottomPoint = new BMap.Point(lon1, lat1);
		var rightTopPoint = new BMap.Point(lon2, lat2);
		var pointArr = [];
		pointArr.push(centerPoint);
		pointArr.push(leftBottomPoint);
		pointArr.push(rightTopPoint);
		console.log(lon1, lat1, lon2, lat2, lon, lat, r);

		var convertor = new BMap.Convertor();
		convertor.translate(pointArr, 1, 5, function translateCb(data) {
			if (data.status === 0) {
				var lonCov = data.points[0].lng;
				var lon1Cov = data.points[1].lng;
				var lon2Cov = data.points[2].lng;
				var latCov = data.points[0].lat;
				var lat1Cov = data.points[0].lat;
				var lat2Cov = data.points[1].lat;
				var gpsLon = (2 * lon - lonCov).toFixed(6);
				var gpsLon1 = (2 * lon1 - lon1Cov).toFixed(6);
				var gpsLon2 = (2 * lon2 - lon2Cov).toFixed(6);
				var gpsLat = (2 * lat - latCov).toFixed(6);
				var gpsLat1 = (2 * lat1 - lat1Cov).toFixed(6);
				var gpsLat2 = (2 * lat2 - lat2Cov).toFixed(6);
				console.log("afterconvert", gpsLon1, gpsLat1, gpsLon2, gpsLat2,
						gpsLon, gpsLat, r);

				var points = gpsLon + "," + gpsLat + "," + r.toFixed(2);
				$("#" + dialogId + " input[name='lon1']")
						.prop('value', gpsLon2);
				$("#" + dialogId + " input[name='lat1']")
						.prop('value', gpsLat1);
				$("#" + dialogId + " input[name='lon2']")
						.prop('value', gpsLon2);
				$("#" + dialogId + " input[name='lat2']")
						.prop('value', gpsLat2);
				$("#" + dialogId + " input[name='centre']").prop('value',
						gpsLon + "," + gpsLat);
				$("#" + pointsDataAreaId).val(points);
				openDialog(dialogId);
			}
		});
	};
	return callback;
}
/**
 * 地图上画矩形区域，并把百度坐标转换为GPS坐标
 * 
 * @param dialogId
 * @param operateType
 * @returns {Function}
 */
function convertRectangleGspFromBdToStandard(dialogId, pointsDataAreaId) {
	var callback = function(lon1, lat1, lon2, lat2, marker) {
		map.removeMarker(marker);
		var new_rect = map.newRectangle(lon1, lat1, lon2, lat2);
		map.addMarker(new_rect);
		;
		$("#" + dialogId + " input[name='mapZoom']").prop('value',
				map.getZoom());

		var leftBottomPoint = new BMap.Point(lon1, lat1);
		var rightTopPoint = new BMap.Point(lon2, lat2);
		var pointArr = [];
		pointArr.push(leftBottomPoint);
		pointArr.push(rightTopPoint);

		var convertor = new BMap.Convertor();
		convertor.translate(pointArr, 1, 5,
				function translateCb(data) {
					if (data.status === 0) {
						var lon1Cov = data.points[0].lng;
						var lon2Cov = data.points[1].lng;
						var lat1Cov = data.points[0].lat;
						var lat2Cov = data.points[1].lat;
						var gpsLon1 = (2 * lon1 - lon1Cov).toFixed(6);
						var gpsLon2 = (2 * lon2 - lon2Cov).toFixed(6);
						var gpsLat1 = (2 * lat1 - lat1Cov).toFixed(6);
						var gpsLat2 = (2 * lat2 - lat2Cov).toFixed(6);
						console.log("afterconvert", gpsLon1, gpsLat1, gpsLon2,
								gpsLat2);
						var points = gpsLon1 + ',' + gpsLat1 + ';' + gpsLon2
								+ ',' + gpsLat2;

						$("#" + dialogId + " input[name='lon1']").prop('value',
								gpsLon1);
						$("#" + dialogId + " input[name='lat1']").prop('value',
								gpsLat1);
						$("#" + dialogId + " input[name='lon2']").prop('value',
								gpsLon2);
						$("#" + dialogId + " input[name='lat2']").prop('value',
								gpsLat2);
						$("#" + pointsDataAreaId).val(points);
						openDialog(dialogId);
					}
				});
	};
	return callback;
}
/**
 * 地图上画多边形区域，并把百度坐标转换为GPS坐标
 * 
 * @param dialogId
 * @param operateType
 */
function convertPolygonGspFromBdToStandard(dialogId, pointsDataAreaId) {
	var callback = function(ps, marker) {
		map.removeMarker(marker);
		var new_polygon = map.newPolygon(ps);
		map.addMarker(new_polygon);
		$("#" + dialogId + " input[name='mapZoom']").prop('value',
				map.getZoom());
		var points = '';
		var lonArr = new Array();// 所有点经度数组
		var latArr = new Array();// 所有点纬度数组
		for (var i = 0; i < ps.length; i++) {
			if (i == ps.length - 1) {
				points += ps[i].lon + ',' + ps[i].lat;
			} else {
				points += ps[i].lon + ',' + ps[i].lat + ';';
			}
			lonArr.push(ps[i].lon);
			latArr.push(ps[i].lat);
		}
		lonArr.sort(function(a, b) {
			return a - b;
		});
		latArr.sort(function(a, b) {
			return a - b;
		});
		lon1 = lonArr[0];
		lat1 = latArr[0];
		lon2 = lonArr[lonArr.length - 1];
		lat2 = latArr[latArr.length - 1];

		var coords = lon1 + "," + lat1 + ";" + lon2 + "," + lat2;
		if (ps.length != 0) {
			coords = coords + ";";
		}
		for (var j = 0; j < ps.length; j++) {
			if (j != ps.length - 1) {
				coords = coords + ps[j].lon + "," + ps[j].lat + ";"
			} else {
				coords = coords + ps[j].lon + "," + ps[j].lat;
			}
		}
		var ak = "oRTEhIarHzXK52qPGuFKQGAPqKIrnmdn";
		var converBdUrl = "http://api.map.baidu.com/geoconv/v1/?coords="
				+ coords + "&ak=" + ak + "&from=1&to=5&output=json&callback=?";
		/*
		 * //跨域操作 $.ajax({ type: "GET", url: converBdUrl, dataType: 'jsonp',
		 * crossDomain: true, success: function(data) { console.log(data); },
		 * error:function(xhr,status,err){ console.log("坐标转换失败"); return; } });
		 */
		$.getJSON(converBdUrl, function(cbdata) {
			if (cbdata && cbdata.status == '0') {
				var cbPointArr = cbdata.result;
				var lon1Cov = cbPointArr[0].x;
				var lon2Cov = cbPointArr[1].x;
				var lat1Cov = cbPointArr[0].y;
				var lat2Cov = cbPointArr[1].y;
				var gpsLon1 = (2 * lon1 - lon1Cov).toFixed(6);
				var gpsLon2 = (2 * lon2 - lon2Cov).toFixed(6);
				var gpsLat1 = (2 * lat1 - lat1Cov).toFixed(6);
				var gpsLat2 = (2 * lat2 - lat2Cov).toFixed(6);
				var gpsPoints = '';
				var gpsPoints = '';
				for (var j = 2; j < cbPointArr.length; j++) {
					if (j == cbPointArr.length - 1) {
						var tempLon = 2 * ps[j - 2].lon - cbPointArr[j].x;
						var tempLat = 2 * ps[j - 2].lat - cbPointArr[j].y;
						gpsPoints += tempLon.toFixed(6) + ','
								+ tempLat.toFixed(6);
					} else {
						var tempLon = 2 * ps[j - 2].lon - cbPointArr[j].x;
						var tempLat = 2 * ps[j - 2].lat - cbPointArr[j].y;
						gpsPoints += tempLon.toFixed(6) + ','
								+ tempLat.toFixed(6) + ";"
					}
				}
				$("#" + dialogId + " input[name='lon1']")
						.prop('value', gpsLon1);
				$("#" + dialogId + " input[name='lat1']")
						.prop('value', gpsLat1);
				$("#" + dialogId + " input[name='lon2']")
						.prop('value', gpsLon2);
				$("#" + dialogId + " input[name='lat2']")
						.prop('value', gpsLat2);
				$("#" + pointsDataAreaId).val(gpsPoints);
				openDialog(dialogId);
			} else {
				console.log("坐标转换失败:" + cbdata.status);
			}
		});
	};
	return callback;
}

/**
 * 把圆形标准GPS坐标转换为百度坐标，并在地图上画出区域
 * 
 * @param areaData
 */
function convertCircleGpsFromStandardToBd(areaData) {
	var data = areaData.area;
	var lon = data.point.split(',')[0];
	var lat = data.point.split(',')[1];
	var r = data.point.split(',')[2];
	var pointArr = [];
	pointArr.push(new BMap.Point(lon, lat));
	console.log(pointArr);
	var convertor = new BMap.Convertor();
	convertor.translate(pointArr, 1, 5, function translateCb(cbdata) {
		if (cbdata.status === 0) {
			var temp_circle = map.newCircle(cbdata.points[0].lng,
					cbdata.points[0].lat, r);
			map.centerAndZoom(cbdata.points[0].lng, cbdata.points[0].lat,
					data.mapZoom);
			map.addMarker(temp_circle);
		} else {
			console.log("地图坐标转换失败");
		}
	});
}
/**
 * 把矩形形标准GPS坐标转换为百度坐标，并在地图上画出区域
 * 
 * @param areaData
 */
function convertRectangleGpsFromStandardToBd(areaData) {
	var data = areaData.area;
	var lon1 = data.lon1;
	var lat1 = data.lat1;
	var lon2 = data.lon2;
	var lat2 = data.lat2;
	var pointArr = [];
	pointArr.push(new BMap.Point(lon1, lat1));
	pointArr.push(new BMap.Point(lon2, lat2));
	var convertor = new BMap.Convertor();
	convertor.translate(pointArr, 1, 5, function translateCb(cbdata) {
		if (cbdata.status === 0) {
			var new_rect = map.newRectangle(cbdata.points[0].lng,
					cbdata.points[0].lat, cbdata.points[1].lng,
					cbdata.points[1].lat);
			map.addMarker(new_rect);
			var centerLon = cbdata.points[0].lng
					+ (cbdata.points[1].lng - cbdata.points[0].lng) / 2;
			var centerLat = cbdata.points[0].lat
					+ (cbdata.points[1].lat - cbdata.points[0].lat) / 2;
			map.centerAndZoom(centerLon, centerLat, data.mapZoom);
		} else {
			console.log("地图坐标转换失败");
		}
	});
}
/**
 * 把多边形标准GPS坐标转换为百度坐标，并在地图上画出区域
 * 
 * @param areaData
 */
function convertPolygonGpsFromStandardToBd(areaData) {
	var data = areaData.area;
	var lon1 = data.lon1;
	var lat1 = data.lat1;
	var lon2 = data.lon2;
	var lat2 = data.lat2;
	var pointArr = [];
	pointArr.push(new BMap.Point(lon1, lat1));
	pointArr.push(new BMap.Point(lon2, lat2));

	var points = data.point.split(';');
	for (var i = 0; i < points.length; i++) {
		var lonLat = points[i].split(',');
		pointArr.push(new BMap.Point(lonLat[0], lonLat[1]));
	}
	var coords = "";
	for (var j = 0; j < pointArr.length; j++) {
		if (j != pointArr.length - 1) {
			coords = coords + pointArr[j].lng + "," + pointArr[j].lat + ";"
		} else {
			coords = coords + pointArr[j].lng + "," + pointArr[j].lat;
		}
	}
	var ak = "oRTEhIarHzXK52qPGuFKQGAPqKIrnmdn";
	var converBdUrl = "http://api.map.baidu.com/geoconv/v1/?coords=" + coords
			+ "&ak=" + ak + "&from=1&to=5&output=json&callback=?";
	$.getJSON(converBdUrl,
			function(cbdata) {
				if (cbdata && cbdata.status == '0') {
					var cbpoints = cbdata.result;
					var polygonPointArr = [];
					for (var j = 2; j < cbpoints.length; j++) {
						var p = new SEGPoint(parseFloat(cbpoints[j].x),
								parseFloat(cbpoints[j].y));
						polygonPointArr.push(p);
					}
					var new_polygon = map.newPolygon(polygonPointArr);
					map.addMarker(new_polygon);
					var centerLon = cbpoints[0].x
							+ (cbpoints[1].x - cbpoints[0].x) / 2;
					var centerLat = cbpoints[0].y
							+ (cbpoints[1].y - cbpoints[0].y) / 2;
					map.centerAndZoom(centerLon, centerLat, data.mapZoom);
				} else {
					console.log("坐标转换失败:" + cbdata.status);
				}
			});
}
/**
 * 在地图上画出省市区域
 * 
 * @param areaData
 */
function drawProvinceCityArea(areaData) {
	var data = areaData.pointlist;
	var polygone = areaData.polygone;
	for(var l = 0; l < data.length; l++){
		var points = [];
		var pointList = data[l];
		for (var i = 0; i < pointList.length; i++) {
			var point = pointList[i];
			var p = new SEGPoint(point.lon / Math.pow(10, 6), point.lat
					/ Math.pow(10, 6));
			points.push(p);
		}
		var new_polygon = map.newPolygon(points);
		map.addMarker(new_polygon);
	}
	var centerLon = (polygone.minx + (polygone.maxx - polygone.minx) / 2)
			/ Math.pow(10, 6);
	var centerLat = (polygone.miny + (polygone.maxy - polygone.miny) / 2)
			/ Math.pow(10, 6);
	var mapZoom = 9;
	if (polygone.leval == 3) {
		mapZoom = 10;
	} else if (polygone.leval == 2) {
		mapZoom = 9;
	} else if (polygone.leval == 1) {
		mapZoom = 7;
	}
	map.centerAndZoom(centerLon, centerLat, mapZoom);
}
/**
 * 地图上画区域
 * 
 * @param areadata
 * @param shape
 */
function drawArea(areadata, shape) {
	clearAllMarker();
	if (shape == 1) {
		convertCircleGpsFromStandardToBd(areadata);
	} else if (shape == 2) {
		convertRectangleGpsFromStandardToBd(areadata);
	} else if (shape == 3) {
		convertPolygonGpsFromStandardToBd(areadata);
	} else if (shape == 4) {
		drawProvinceCityArea(areadata)
	}
}
/*function showPublicArea(){
	
	map.centerAndZoom(new BMap.Point(103.388611,35.563611), 5);
	return false;
	var map = new BMap.Map("map_canvas");
	map.centerAndZoom(new BMap.Point(103.388611,35.563611), 5);  // 初始化地图,设置中心点坐标和地图级别
	map.addControl(new BMap.MapTypeControl());   //添加地图类型控件
	map.addControl(new BMap.NavigationControl({enableGeolocation:true}));
	map.addControl(new BMap.OverviewMapControl());
	map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
	function getAttr(e){
		console.log(e)
		var content = "<p>现在所在的位置经度为"+e.point.lat+"</p>";
		content += "<p>现在所在的位置纬度为"+e.point.lng+"</p>"
		openInfo(content,e);
	}

	function openInfo(content,e){
		var opts = {
			width : 250,     // 信息窗口宽度
			height: 120,     // 信息窗口高度
			title : "信息窗口" , // 信息窗口标题
			enableMessage:true//设置允许信息窗发送短息
		};
		var p = e.target;
		var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
		var infoWindow = new BMap.InfoWindow(content,opts);  // 创建信息窗口对象 
		map.openInfoWindow(infoWindow,point); //开启信息窗口
	}
	$.ajax({
		  type: "POST",
		  url:D2V.areaManageURL.showPublicArea,
		  dataType:'json',
		  error:function(xhr,status,err){
			  $.ligerDialog.error('网络故障!');
			  return;
		  },
		  success:function(data){
			if(data.success===true){
				var shapeType = data.datas;
				var myIcon = new BMap.Icon("../resources/images/map/cars_d36_25.png", new BMap.Size(25,25));
				var markers = [];
				var pt=null;
				console.log(shapeType);
				for(var i=0;i<shapeType.length;i++){
					if(shapeType[i].shapeType==1){
						console.log(1);
						var lon = shapeType[i].point.split(',')[0];
						var lat = shapeType[i].point.split(',')[1];
						pt = new BMap.Point(lon,lat);
						markers.push(new BMap.Marker(pt,{icon:myIcon}));
					}else if(shapeType[i].shapeType ==2){
						//console.log(2);
						//var lon = shapeType[i].point.split(',')[0];
						//var lat = shapeType[i].point.split(',')[1];
					}else if(shapeType[i].shapeType ==3){
						console.log(3);
						var firstdata = shapeType[i].point.split(';')[0];
						var lon = firstdata.split(',')[0];
						var lat = firstdata.split(',')[1];
						pt = new BMap.Point(lon,lat);
						markers.push(new BMap.Marker(pt,{icon:myIcon}));
					}else if(shapeType[i].shapeType == 4){
						//console.log(4);
						//console.log(shapeType[i]);
					}
				}
				for(var i=0;i<markers.length;i++){
					markers[i].addEventListener("click",getAttr);
				}
				//console.log(markers);
				//return false;
				var c = new BMapLib.MarkerClusterer(map,
						{
							markers:markers,
							girdSize : 100,
							styles : [{
					            url:'./images/red.png',
					            color:'white',
					            size: new BMap.Size(92, 92),
								backgroundColor : '#E64B4E'
							}]
				});
				//console.log(markers)
				return false;  
				drawArea(data.datas, bindGrids.getRow(rowid).shapeType);
			}else{
			   $.ligerDialog.error(data.message);
			}
		  }
	});
}*/

// 清除地图上留下的所有图标
function clearAllMarker() {
	map.clearNonStaticMarkers();
	map.clearVehicleMarkers();
	map.closeAllInfoWindow();
}

$(function() {
	var routeManage = window.routeManage = {};
	routeManage.centerLayout = $("#center-layout").ligerLayout({
		leftWidth : 284,
		onEndResize : function() {
			$(window).trigger('resize');
		}
	});

	routeManage.enterpriseVehicleLayout = $("#enterprise-vehicle-layout")
			.ligerLayout({
				topHeight : 110,
				onEndResize : function() {
					$(window).trigger('resize');
				}
			});
	routeManage.rightLayout = $("#right-layout").ligerLayout({
		leftWidth : 284,
		onEndResize : function() {
			resizePage();
		}
	});
	// 选择省市,或者选择省市之后选择其他形状时候的功能
	$('#add-form').find('select[name=shapeType]').change(
			function() {
				if ($(this).val() == '4') {
					clearAllMarker();
					$('#add-form .addSShide').hide();
					$('#addQYDJ').val('');
					$('#add-form .addSSshow').show();
					$.ajax({
						type : "POST",
						url : D2V.areaManageURL.queryProvince,
						dataType : 'json',
						error : function(xhr, status, err) {
							$.ligerDialog.error('网络故障!');
							return;
						},
						success : function(data) {
							if (data.success === true) {
								var d = data.datas;
								var SSLength = d.length;
								$('#add-form').find('.addSSshow').eq(1).find(
										'select option:not(.a)').remove();
								$('#add-form').find('.addSSshow').eq(2).find(
										'select option:not(.a)').remove();
								for (var i = 0; i < SSLength; i++) {
									var str = '';
									str += '<option value="' + d[i].id
											+ '" class="ss' + i + '">'
											+ d[i].name + '</option>';
									$('#add-form')
											.find('select[name=province]')
											.append(str);
								}
							} else {
								$.ligerDialog.error(data.message);
							}
						}
					});
				} else {
					$('#add-form').find('.addSSshow').eq(0).find(
							'select option:not(.a)').remove();
					$('#add-form').find('.addSSshow').eq(1).find(
							'select option:not(.a)').remove();
					$('#add-form').find('.addSSshow').eq(2).find(
							'select option:not(.a)').remove();
					$('#addQYDJ').val('');
					$('#add-form .addSShide').show();
					$('#add-form .addSSshow').hide();
				}
			})
	// 选择省份。
	$('#add-form').find('select[name=province]').change(
			function() {
				$('#add-form').find('.addSSshow').eq(1).find(
						'select option:not(.a)').remove();
				$('#add-form').find('.addSSshow').eq(2).find(
						'select option:not(.a)').remove();
				if ($(this).val() == '') {
					return;
				} else {
					var thisId = $(this).val();
					$.ajax({
						type : "POST",
						url : D2V.areaManageURL.queryCityByProvince,
						data : {
							province : thisId
						},
						dataType : 'json',
						error : function(xhr, status, err) {
							$.ligerDialog.error('网络故障!');
							return;
						},
						success : function(data) {
							if (data.success === true) {
								var d = data.datas;
								var SSLength = d.length;
								for (var i = 0; i < SSLength; i++) {
									if (SSLength == '0') {

									}
									var str = '';
									str += '<option value="' + d[i].cityid
											+ '" class="ss' + i + '">'
											+ d[i].name + '</option>';
									$('#add-form').find('select[name=city]')
											.append(str);
								}
							} else {
								$.ligerDialog.error(data.message);
							}
						}
					})
				}
			})
	// 选择城市。
	$('#add-form').find('select[name=city]').change(
			function() {
				$('#add-form').find('.addSSshow').eq(2).find(
						'select option:not(.a)').remove();
				if ($(this).val() == '') {
					return;
				} else {
					var thisId = $(this).val();
					$.ajax({
						type : "POST",
						url : D2V.areaManageURL.queryCountyByCity,
						data : {
							city : thisId
						},
						dataType : 'json',
						error : function(xhr, status, err) {
							$.ligerDialog.error('网络故障!');
							return;
						},
						success : function(data) {
							console.log(data);
							if (data.success === true) {
								var d = data.datas;
								var SSLength = d.length;
								for (var i = 0; i < SSLength; i++) {
									var str = '';
									str += '<option value="' + d[i].countyid
											+ '" class="ss' + i + '">'
											+ d[i].name + '</option>';
									$('#add-form').find('select[name=county]')
											.append(str);
								}
							} else {
								$.ligerDialog.error(data.message);
							}
						}
					})
				}
			})

	// 选择省市,或者选择省市之后选择其他形状时候的功能
	$('#add-form').find('select[name=zoonState]').change(
			function() {
				if ($(this).val() == '2') {
					clearAllMarker();
					$('#add-form .addGXSSshow').show();
					$.ajax({
						type : "POST",
						url : D2V.areaManageURL.queryProvince,
						dataType : 'json',
						error : function(xhr, status, err) {
							$.ligerDialog.error('网络故障!');
							return;
						},
						success : function(data) {
							if (data.success === true) {
								var d = data.datas;
								var SSLength = d.length;
								$('#add-form').find('.addGXSSshow').eq(1).find(
										'select option:not(.a)').remove();
								for (var i = 0; i < SSLength; i++) {
									var str = '';
									str += '<option value="' + d[i].id
											+ '" class="ss' + i + '">'
											+ d[i].name + '</option>';
									$('#add-form').find(
											'select[name=gxprovince]').append(
											str);
								}
							} else {
								$.ligerDialog.error(data.message);
							}
						}
					});
				} else {
					$('#add-form').find('.addGXSSshow').eq(0).find(
							'select option:not(.a)').remove();
					$('#add-form').find('.addGXSSshow').eq(1).find(
							'select option:not(.a)').remove();
					$('#add-form .addGXSSshow').hide();
				}
			})
	// 选择省份。
	$('#add-form').find('select[name=gxprovince]').change(
			function() {
				$('#add-form').find('.addGXSSshow').eq(1).find(
						'select option:not(.a)').remove();
				if ($(this).val() == '') {
					return;
				} else {
					var thisId = $(this).val();
					$.ajax({
						type : "POST",
						url : D2V.areaManageURL.queryCityByProvince,
						data : {
							province : thisId
						},
						dataType : 'json',
						error : function(xhr, status, err) {
							$.ligerDialog.error('网络故障!');
							return;
						},
						success : function(data) {
							if (data.success === true) {
								var d = data.datas;
								var SSLength = d.length;
								for (var i = 0; i < SSLength; i++) {
									var str = '';
									str += '<option value="' + d[i].cityid
											+ '" class="ss' + i + '">'
											+ d[i].name + '</option>';
									$('#add-form').find('select[name=gxcity]')
											.append(str);
								}
							} else {
								$.ligerDialog.error(data.message);
							}
						}
					})
				}
			})

	$('input').focus(function() {
		$(this).css('border', "1px solid #008dea");
	})
	$('input').blur(function() {
		$(this).css('border', "1px solid #ccc");
	});
	// 清除地图上的标注
	var str = '';
	str += '<div id="GPSTR"><img src="../images/carGIS/GPS7.png" />';
	str += '<a href="javascript:;" style="display:inline-block;line-height:24px;" class="qingkongtb">清除标注</a></div>';
	$('#map_canvas').append(str);

	$('.qingkongtb').click(function() {
		clearAllMarker();
	})

	// 查询,区域,表格参数设置
	window.gridParms = {
		onLoaded : function(grid) {
			setTimeout(function() {
				$('.l-grid-loading').hide();
				$(".l-grid-row-cell-inner").each(function() {
					var that = $(this);
					that.attr('title', that.text());
				})
			}, 200);
		},
		url : D2V.areaManageURL.queryArea,
		parms : {
			// "zoonState":0,
			"zoonState" : -1,
			"validState" : -1,
			"shapeType" : -1,
			"areaType" : -1
		},
		toolbar : {
			items : [ {
				text : '增加',
				click : addArea,
				icon : 'add'
			}, {
				text : '删除',
				click : deleteArea,
				icon : 'delete'
			}, {
				text : '修改',
				click : updateArea,
				icon : 'xiugai'
			}, {
				text : '共享区域下载',
				click : publicArea,
				icon : 'xiugai'
			}/*, {
				text : '点聚加载',
				click : showPublicArea,
				icon : 'xiugai'
			}*/
			]
		},
		onSuccess : function(data) {
			if (data.success == true) {
				data.list = data.datas.list;
				data.count = data.datas.count;
			} else {
				$.ligerDialog.error(data.message);
			}
		},
		onError : function(XMLHttpRequest, textStatus, errorThrown) {
			console.log(textStatus, errorThrown);
		},
		width : '100%',
		pageSizeOptions : [ 5, 10, 15, 20 ],
		height : '100%',
		// rowSelectable:true,
		// checkbox:true,
		// whenRClickToSelect:true,
		rownumbers : false,
		onDblClickRow : function(data, rowid, rowdata) {
			// $('#main-gridgrid .l-grid-body-inner tbody >
			// tr').css('background-color','#fff').removeClass('l-selected');
			// $(rowdata).css('background-color',"#f27066").addClass('l-selected');
			var areaId = data.areaId;
			$.ajax({
				type : "POST",
				dataType : 'json',
				data : {
					"areaId" : areaId
				},
				url : D2V.areaManageURL.queryAreaById,
				error : function(xhr, status, err) {
					console.log(status, err);
					$.ligerDialog.error('查询失败,网络故障!');
					return;
				},
				success : function(data) {
					if (data.success === true) {
						clearAllMarker();
						drawArea(data.datas, mainGrid.getRow(rowid).shapeType);
					} else {
						$.ligerDialog.error(data.message);
					}
				}

			});
		}

	};
	var optcol = [ {
		show : '操作',
		field : 'zzz0',
		width : 80,
		align : 'center',
		render : 'renderOpt'
	} ];
	window.mainGrid = initMainGrid('main-grid', gridParms, optcol
			.concat(D2V.areaManageAreaGrid));

	// 地图上画区域操作
	$('#add-playWires').click(
			function() {
				// var areaShap=$("#add-dialog
				// input[type='radio'][name='shapeType']:checked").prop('value');
				var areaShap = $("#add-dialog select[name='shapeType']").val();
				console.log(areaShap);
				closeDialog('add-dialog');
				clearAllMarker();
				if (areaShap == 1) {// 圆形
					var callback = convertCircleGspFromBdToStandard(
							"add-dialog", "addQYDJ");
					map.drawCircle(callback);
				} else if (areaShap == 2) {// 矩形
					var callback = convertRectangleGspFromBdToStandard(
							"add-dialog", "addQYDJ");
					map.drawRectangle(callback);
				} else if (areaShap == 3) {// 多边形
					var callback = convertPolygonGspFromBdToStandard(
							"add-dialog", "addQYDJ");
					map.drawPolygon(callback);
				} else if (areaShap == 4) {
					openDialog('add-SS-dialog', {
						title : '选择省市区域',
						width : 300,
						height : 300
					}, function dialog() {
					});
				} else {
					$.ligerDialog.warn('请选择画线区域形状!');
				}
			});
	// 添加区域数据到服务器
	$("#addSubmit").on('click', function(e) {
		var areaName = $("#add-dialog input[name='areaName']").val();
		var points = $("#addQYDJ").val();
		var areaShape = $("#add-dialog select[name='shapeType']").val();
		var province = $('#add-form').find('select[name=province]').val();
		var zoonState = $("#add-dialog select[name='zoonState']").val();
		var pid = $("#add-dialog select[name='gxprovince']").val();;
		var pcid = $("#add-dialog select[name='gxcity']").val();
		if (areaName == null || areaName == '') {
			$.ligerDialog.warn('区域名称不能为空!');
			return;
		}
		if (areaShape == '4') {
			if (province == '') {
				$.ligerDialog.warn('请选择省份!');
				return;
			}
		} else {
			if (points == null || points == '') {
				$.ligerDialog.warn('区域点集不能为空!');
				return;
			}
		}
		if (areaName.length > 20) {
			$.ligerDialog.warn("区域名称过长,请重新输入");
			return;
		}
		if(zoonState=="2"){
			if(pid==""){
				$.ligerDialog.warn('请选择共享省份!');
				return;
			}
			if(pcid==""){
				$.ligerDialog.warn('请选择共享城市!');
				return;
			}
		}
		$("#add-form").trigger("submit");
	});
	$("#add-form").submit(function(e) {
		e.preventDefault();
		var areaObj;
		var shapeType = $("#add-dialog select[name='shapeType']").val();
		if (shapeType == '4') {
			var areaName = $("#add-dialog input[name='areaName']").val();
			var areaType = $("#add-dialog select[name='areaType']").val();
			var zoonState = $("#add-dialog select[name='zoonState']").val();
			var pid = $("#add-dialog select[name='gxprovince']").val();;
			var pcid = $("#add-dialog select[name='gxcity']").val();
			var validState = $("#add-dialog select[name='validState']").val();
			var province = $("#add-dialog select[name='province']").val();
			var city = $("#add-dialog select[name='city']").val();
			var county = $("#add-dialog select[name='county']").val();
			var areaObj = {
				"areaName" : areaName,
				"shapeType" : shapeType,
				"areaType" : areaType,
				"zoonState" : zoonState,
				"validState" : validState,
				"pId":pid,
				"pcId":pcid,
				"province" : province,
				"city" : city,
				"county" : county
			};
		} else {
			areaObj = getFormAjaxData('add-form');
			if(areaObj.gxprovince!=undefined){
				areaObj.pId = areaObj.gxprovince;
			}
			if(areaObj.gxcity!=undefined){
				areaObj.pcId = areaObj.gxcity;
			}
			
		}
		console.log(areaObj);
		// console.log(JSON.stringify(areaObj));
		$.ajax({
			url : D2V.areaManageURL.addArea,
			type : "POST",
			// contentType:'application/json;charset=utf-8',
			// data:JSON.stringify(areaObj),
			data : areaObj,
			dataType : "json",
			success : function(rsp) {
				if (rsp.success == true) {
					$.ligerDialog.success(rsp.message);
					closeDialog('add-dialog');
					clearAllMarker();
					mainGrid.setParm("zoonState", "-1");
					mainGrid.reload();
				} else {
					if (rsp.errorCode == 1) {
						$.ligerDialog.warn(rsp.message + ",区域名已经存在");
					} else if (rsp.errorCode == 2) {
						$.ligerDialog.warn(rsp.message + ",未找到所选区域地图边界点");
					} else {
						// clearAllMarker();
						//$.ligerDialog.error(rsp.message);
						$.ligerDialog.warn("点数超过限制");
					}
					return;
				}
			},
			error : function(xhr, status, err) {
				console.log(status, err);
				// clearAllMarker();
				$.ligerDialog.error('保存失败,网络故障!');
				return;
			}
		})
	});
	// 修改操作,画区域
	$('#update-playWires').click(
			function() {
				var areaShap = $("#update-dialog select[name='shapeType']")
						.val();
				clearAllMarker();
				closeDialog('update-dialog');
				if (areaShap == 1) {// 圆形
					var callback = convertCircleGspFromBdToStandard(
							"update-dialog", "updateQYDJ");
					map.drawCircle(callback);
				} else if (areaShap == 2) {// 矩形
					var callback = convertRectangleGspFromBdToStandard(
							"update-dialog", "updateQYDJ");
					map.drawRectangle(callback);
				} else if (areaShap == 3) {// 多边形
					var callback = convertPolygonGspFromBdToStandard(
							"update-dialog", "updateQYDJ");
					map.drawPolygon(callback);
				} else if (areaShap == 4) {
					openDialog('update-SS-dialog', {
						title : '选择省市区域',
						width : 300,
						height : 300
					}, function dialog() {
					});
				}
			});

	$("#updateSubmit").on('click', function(e) {
		var areaName = $("#update-dialog input[name='areaName']").val();
		var points = $("#updateQYDJ").val();
		var areaShape = $("#update-dialog select[name='shapeType']").val();
		var province = $('#update-form').find('select[name=province]').val();
		if (areaName == null || areaName == '') {
			$.ligerDialog.warn('区域名称不能为空!');
			$("#update-dialog input[name='areaName']").focus();
			return;
		}
		if (areaShape == '4') {
			if (province == '') {
				$.ligerDialog.warn('请选择省份!');
				return;
			}
		} else {
			if (points == null || points == '') {
				$.ligerDialog.warn('区域点集不能为空!');
				return;
			}
		}
		if (areaName.length > 20) {
			$.ligerDialog.warn("区域名称过长,请重新输入");
			return;
		}
		$("#update-form").trigger("submit");
	});

	$("#update-form")
			.submit(
					function(e) {
						e.preventDefault();
						var areaObj;
						var shapeType = $(
								"#update-dialog select[name='shapeType']")
								.val();
						if (shapeType == '4') {
							var areaName = $(
									"#update-dialog input[name='areaName']")
									.val();
							var areaType = $(
									"#update-dialog select[name='areaType']")
									.val();
							var zoonState = $(
									"#update-dialog select[name='zoonState']")
									.val();
							var validState = $(
									"#update-dialog select[name='validState']")
									.val();
							var province = $(
									"#update-dialog select[name='province']")
									.val();
							var city = $("#update-dialog select[name='city']")
									.val();
							var county = $(
									"#update-dialog select[name='county']")
									.val();
							var areaId = $(
									"#update-dialog input[name='areaId']")
									.val();
							var areaObj = {
								"areaId" : areaId,
								"areaName" : areaName,
								"shapeType" : shapeType,
								"areaType" : areaType,
								"zoonState" : zoonState,
								"validState" : validState,
								"province" : province,
								"city" : city,
								"county" : county
							};
							console.log(areaObj);
						} else {
							areaObj = getFormAjaxData('update-form');
						}
						$.ajax({
							url : D2V.areaManageURL.updateArea,
							type : "POST",
							// contentType:'application/json;charset=utf-8',
							// data:JSON.stringify(areaObj),
							data : areaObj,
							dataType : "json",
							success : function(rsp) {
								if (rsp.success == true) {
									$.ligerDialog.success(rsp.message);
									closeDialog('update-dialog');
									clearAllMarker();
									mainGrid.reload();
								} else {
									if (rsp.errorCode === 1) {
										$.ligerDialog.warn(rsp.message
												+ ",区域名已经存在");
									} else if (rsp.errorCode === 2) {
										$.ligerDialog.warn(rsp.message
												+ ",未找到所选区域地图边界点");
									} else {
										// clearAllMarker();
										$.ligerDialog.error(rsp.message);
									}
								}
							},
							error : function(xhr, status, err) {
								consle.log(status, err);
								// clearAllMarker();
								$.ligerDialog.error('保存失败,网络故障!');
								return;
							}
						})
					});

});

// 点击 搜索 按钮后的事件
function queryArea() {
	// 修改显示页码不正常
	mainGrid.setOptions({
		newPage : 1
	});
	var searchData = {};
	var formParam = getFormDataAll('searchForm');
	for ( var k in formParam) {
		var newdata;
		newdata = $.trim(formParam[k]);
		if (newdata != '') {
			searchData[k] = newdata;
			mainGrid.setParm(k, newdata);
		} else {
			mainGrid.removeParm(k);
			delete formParam[k];
		}
	}
	formParam.page = 1;
	var rp_value = $("select[name='rp']").val();
	formParam.pagesize = rp_value;
	console.log(formParam);
	$("#main-grid").ligerGetGridManager().loadServerData(formParam);
}

// 生成的操作
function renderOpt(rowdata, index, value) {
	var str = '';
	str = '<a href="javascript:;" onclick="startBindVehicle(' + index
			+ ')">绑车</a>';
	/*
	 * str += '&nbsp;&nbsp;<a href="javascript:;" onclick="startBindAgency(' +
	 * index + ')">绑定机构车辆</a>';
	 */
	return str;
}

function renderGXOpt(rowdata, index, value) {
	var str = '';
	if(rowdata.gxId == undefined){
		str += '&nbsp;&nbsp;<a href="javascript:;" style="color:green"  onclick="addGxqy(' + index
		+ ')">下载</a>';
	}else{
		str += '&nbsp;&nbsp;<a style="color:red" href="javascript:;" onclick="cancelGxqy(' +
		index + ')">移除</a>';
	}
	if(rowdata.cusId == rowdata.userId){
		str += '&nbsp;&nbsp;<a  href="javascript:;" onclick="deleteGxqy(' +
		index + ')">删除</a>';
	}
	 
	return str;
}

function addGxqy(rowIndex){
	var rowObj = bindGrids.getRow(rowIndex);
	var areaIds = rowObj.areaId;
	var areaName = rowObj.areaName;
	$.ajax({
		type : "POST",
		url : D2V.areaManageURL.addGxqy,
		data : {
				"areaid" : areaIds,
				"areaName" : areaName
				},
		dataType : 'json',
		error : function(xhr, status, err) {
			$.ligerDialog.error('网络故障!');
			return;
		},
		success : function(data) {
			if (data.success === true) {
				$.ligerDialog.success('共享区域下载成功!');
				bindGrids.reload();
				mainGrid.reload();
			}else{
				$.ligerDialog.warn('共享区域下载失败!');
			}
		}
	});
}

function cancelGxqy(rowIndex){
	$.ligerDialog.confirm('移除共享区域也会移除该区域和车辆的绑定关系,确定是否移除?', 
		function(yes) {
			if (yes === false)
				return;
			var rowObj = bindGrids.getRow(rowIndex);
			var areaName = rowObj.areaName;
			var areaIds = rowObj.areaId;
			$.ajax({
				type : "POST",
				url : D2V.areaManageURL.cancelGxqy,
				data : {
					"areaid" : areaIds,
					"areaName" : areaName
					},
				dataType : 'json',
				error : function(xhr, status, err) {
					$.ligerDialog.error('网络故障!');
					return;
				},
				success : function(data) {
					if (data.success === true) {
						$.ligerDialog.success('共享区域移除成功!');
						bindGrids.reload();
						mainGrid.reload();
					}else{
						$.ligerDialog.warn('共享区域移除失败!');
					}
				}
			});
		}
	)
}

function deleteGxqy(rowIndex){
	$.ligerDialog.confirm('删除共享区域也会删除该区域和车辆的绑定关系,确定是否删除?', 
		function(yes) {
			if (yes === false)
				return;
			var rowObj = bindGrids.getRow(rowIndex);
			var areaIds = rowObj.areaId;
			var areaName = rowObj.areaName;
			$.ajax({
				type : "POST",
				url : D2V.areaManageURL.deleteGxqy,
				data : {
					"areaid" : areaIds,
					"areaName" : areaName
					},
				dataType : 'json',
				error : function(xhr, status, err) {
					$.ligerDialog.error('网络故障!');
					return;
				},
				success : function(data) {
					if (data.success === true) {
						$.ligerDialog.success('共享区域删除成功!');
						bindGrids.reload();
						mainGrid.reload();
					}else{
						$.ligerDialog.warn('共享区域删除失败!');
					}
				}
			});
		}
	)
	
}

// 点击增加按钮后的事件
function addArea() {
	clearForm('add-form', {
		attributesPath : true,
		agencyId : true
	});
	$('#add-form').find('select[name=shapeType]').val('1');
	$('#add-form').find('select[name=shapeType]').trigger('change');
	openDialog('add-dialog', {
		title : '添加区域',
		width : 450,
		height : 516
	}, function dialog() {
		$(window).trigger('resize');
		 $.ajax({
             type:"POST",
    	     dataType:"json",
    	     url:'../manage/area/gxqySelectShow',
    	     success:function(data){
    	       var selectshare=data.success;
    	 		if(!selectshare){
    	 			$("select[name=zoonState] option[value=2]").remove();
    	 		}
    		 },
    		 error:function(xhr,status,err){
    			
    		 }		
    	 });
		
	});
}
function publicArea(){
	 window.bindDialogId = 'public-dialog';
     var bindGridId = 'public-grid';
     var initBindParams = [];
     var pageSize=$("#public-grid select[name='rp']").val();
     var gridHeight = $(".data-list").height()-100;
     window.gridParmsss = {
        url:D2V.areaManageURL.queryPublicArea,
        parms: initBindParams,
        width: '100%',
        pageSizeOptions: [5, 10, 15, 20],
        //height: 430,
        height:gridHeight,
        usePager:true,
        rownumbersColWidth:60,
        onDblClickRow : function(data, rowid, rowdata) {
			// $('#main-gridgrid .l-grid-body-inner tbody >
			// tr').css('background-color','#fff').removeClass('l-selected');
			// $(rowdata).css('background-color',"#f27066").addClass('l-selected');
			var areaId = data.areaId;
			$.ajax({
				type : "POST",
				dataType : 'json',
				data : {
					"areaId" : areaId
				},
				url : D2V.areaManageURL.queryAreaById,
				error : function(xhr, status, err) {
					console.log(status, err);
					$.ligerDialog.error('查询失败,网络故障!');
					return;
				},
				success : function(data) {
					if (data.success === true) {
						//clearAllMarker();
						//drawArea(data.datas, bindGrids.getRow(rowid).shapeType);
						newDrawArea(data.datas, bindGrids.getRow(rowid).shapeType);
					} else {
						$.ligerDialog.error(data.message);
					}
				}

			});
			return false;
		}
    };
    var optcol = [ {
		show : '操作',
		field : 'gxopt',
		width : 120,
		align : 'center',
		render : 'renderGXOpt'
	}];
    
        window.bindGrids = initMainGrid(bindGridId,gridParmsss,optcol.concat(
        	[{show:'区域名称',field:'areaName',align:'center',width:245},
        	 {show:'类型',field:'areaType',width:120,align:'center',
           	 render:function(rowdata,index,value){
           	   return rowdata['areaType'] === 1 ? "普通区域" : (rowdata['areaType'] === 2 ? "高危区域" : "");
                }
            } ,
            {show:'状态',field:'validState',width:120,align:'center',
           	 render:function(rowdata,index,value){
           	   return rowdata['validState'] === 0 ? "有效" : "失效";
                }
            }])
        );
        shareDialog();
       
//        gridSearch();
};
function searchShareArea(){
		var formParam = [];
		var formParam = getFormDataAll('publicAreaSearchForm');
		var searchData = {};
		for ( var k in formParam) {
			var newdata;
			newdata = $.trim(formParam[k]);
			if (newdata != '') {
				searchData[k] = newdata;
				bindGrids.setParm(k, newdata);
			} else {
				bindGrids.removeParm(k);
				delete formParam[k];
			}
		}
		bindGrids.setOptions({ newPage: 1 });  
		formParam.page = 1;
		var rp_value = $("select[name='rp']").eq(1).val();
		formParam.pagesize = rp_value;
		$("#public-grid").ligerGetGridManager().loadServerData(formParam);
}

// 点击修改按钮后的事件
function updateArea() {
	var selectedRowObj = mainGrid.getSelectedRow();
	if (selectedRowObj == null) {
		$.ligerDialog.warn('请选择要修改的数据!');
		return;
	}
	if (selectedRowObj.zoonState === 2) {
		$.ligerDialog.warn('该区域为共享区域不可修改!');
		return;
	}
	clearForm('update-form');
	// clearAllMarker();
	$('#update-form').find('.updateSSshow').eq(0).find('select option:not(.a)')
			.remove();
	$('#update-form').find('.updateSSshow').eq(1).find('select option:not(.a)')
			.remove();
	$('#update-form').find('.updateSSshow').eq(2).find('select option:not(.a)')
			.remove();

	$("#update-dialog input[name='areaName']").prop('value',
			selectedRowObj.areaName);
	$("#update-dialog select[name='zoonState'] option[value="
					+ selectedRowObj.zoonState + "]").prop("selected", true);
	$("#update-dialog select[name='validState'] option[value="
					+ selectedRowObj.validState + "]").prop("selected", true);
	$("#update-dialog select[name='shapeType'] option[value="
					+ selectedRowObj.shapeType + "]").prop("selected", true);
	$("#update-dialog select[name='areaType'] option[value="
					+ selectedRowObj.areaType + "]").prop("selected", true);
	$("#update-dialog textarea[name='point']").prop('value',
			selectedRowObj.point);
	$("#update-dialog input[name='areaId']").prop('value',
			selectedRowObj.areaId);
	
	if ($('#update-dialog').find('select[name=shapeType]').val() == '4') {
//		// 获取省，市，县的ID
//		$.ajax({
//					type : "POST",
//					url : D2V.areaManageURL.queryPvCyCtByPcc,
//					// contentType:'application/json;charset=utf-8',
//					dataType : 'json',
//					data : {
//						pccId : selectedRowObj.pccId
//					},
//					error : function(xhr, status, err) {
//						$.ligerDialog.error('查询当前资料失败,网络故障!');
//						return;
//					},
//					success : function(data) {
//						console.log(data);
//						if (data.success === true) {
//							var dataSelect = data.datas;
//							provinceName = dataSelect.provinceName;
//							cityName = dataSelect.cityName;
//							countyName = dataSelect.countyName;
							provinceId = selectedRowObj.province;
							cityId = selectedRowObj.city;
							countyId = selectedRowObj.county;
							console.log(provinceId, cityId, countyId);
							$('#update-form .updateSShide').hide();
							$('#updateQYDJ').val('');
							$('#update-form .updateSSshow').show();
							// 得到省数据，并默认值是之前选中的值。
							$.ajax({
										type : "POST",
										url : D2V.areaManageURL.queryProvince,
										dataType : 'json',
										error : function(xhr, status, err) {
											$.ligerDialog.error('网络故障!');
											return;
										},
										success : function(data) {
											if (data.success === true) {
												var d = data.datas;
												var SSLength = d.length;
												if (SSLength == '0') {
													return;
												}
												for (var i = 0; i < SSLength; i++) {
													var str = '';
													str += '<option value="'
															+ d[i].id
															+ '" class="ss' + i
															+ '">' + d[i].name
															+ '</option>';
													$('#update-form')
															.find(
																	'select[name=province]')
															.append(str);
													$('#update-form')
															.find(
																	'select[name=province]')
															.val(provinceId);
												}
												$
														.ajax({
															type : "POST",
															url : D2V.areaManageURL.queryCityByProvince,
															data : {
																province : provinceId
															},
															dataType : 'json',
															error : function(
																	xhr,
																	status, err) {
																$.ligerDialog
																		.error('网络故障!');
																return;
															},
															success : function(
																	data) {
																if (data.success === true) {
																	var d = data.datas;
																	var SSLength = d.length;
																	if (SSLength == '0') {
																		return;
																	}
																	for (var i = 0; i < SSLength; i++) {
																		var str = '';
																		str += '<option value="'
																				+ d[i].cityid
																				+ '" class="ss'
																				+ i
																				+ '">'
																				+ d[i].name
																				+ '</option>';
																		$(
																				'#update-form')
																				.find(
																						'select[name=city]')
																				.append(
																						str);
																		$(
																				'#update-form')
																				.find(
																						'select[name=city]')
																				.val(
																						cityId);
																	}
																	// 县数据
																	$
																			.ajax({
																				type : "POST",
																				url : D2V.areaManageURL.queryCountyByCity,
																				data : {
																					city : cityId
																				},
																				dataType : 'json',
																				error : function(
																						xhr,
																						status,
																						err) {
																					$.ligerDialog
																							.error('网络故障!');
																					return;
																				},
																				success : function(
																						data) {
																					if (data.success === true) {
																						var d = data.datas;
																						var SSLength = d.length;
																						if (SSLength == '0') {
																							return;
																						}
																						for (var i = 0; i < SSLength; i++) {
																							var str = '';
																							str += '<option value="'
																									+ d[i].countyid
																									+ '" class="ss'
																									+ i
																									+ '">'
																									+ d[i].name
																									+ '</option>';
																							$(
																									'#update-form')
																									.find(
																											'select[name=county]')
																									.append(
																											str);
																							$(
																									'#update-form')
																									.find(
																											'select[name=county]')
																									.val(
																											countyId);
																						}
																					}
																				}
																			});
																}
															}
														});
											}
										}
									});
//						}
//					}
//				});
	} else {
		$('#update-form .updateSShide').show();
		$('#update-form .updateSSshow').hide();
	}
	openDialog('update-dialog', {
		title : '修改区域',
		width : 450,
		height : 516
	}, function dialog() {
		$(window).trigger('resize');
	});
}
/**
 * 删除区域
 */
function deleteArea() {
	var selectedRow = mainGrid.getSelectedRow();
	if (selectedRow == null) {
		$.ligerDialog.warn('请选定要删除的数据');
		return;
	}
	if (selectedRow.zoonState === 2) {
		$.ligerDialog.warn('该区域为共享区域不可删除!');
		return;
	}
	selectedRow != null ? $.ligerDialog.confirm('删除区域也会删除区域和车辆的绑定关系,确定是否删除?',
			function(yes) {
				if (yes === false)
					return;
				$.ajax({
					type : "POST",
					url : D2V.areaManageURL.deleteArea,
					// contentType:'application/json;charset=utf-8',
					dataType : 'json',
					data : {
						"areaId" : selectedRow.areaId
					},
					error : function(xhr, status, err) {
						$.ligerDialog.error('删除区域失败,网络故障!');
						return;
					},
					success : function(data) {
						if (data.success === true) {
							$.ligerDialog.success(data.message);
							queryArea();
						} else {
							$.ligerDialog.error(data.message);
						}
					}
				});
			}) : $.ligerDialog.warn('请选择要刪除的区域!');
}
function deleteAreas() {
	var selectedRows = mainGrid.getSelectedRows();
	console.log(selectedRowObj.zoonState);
	if (selectedRows.zoonState === 2) {
		$.ligerDialog.warn('该区域为共享区域不可删除!');
		return;
	}
	selectedRows.length > 0 ? $.ligerDialog.confirm(
			'删除区域也会删除区域和车辆的绑定关系,确定是否删除?', function(yes) {
				if (yes === false)
					return;
				var areaObjArr = [];
				$(selectedRows).each(function() {
					if (this.areaId !== undefined)
						areaObjArr.push({
							"areaId" : this.areaId
						});
				});
				var areaArrJson = JSON.stringify(areaObjArr);
				$.ajax({
					type : "POST",
					url : D2V.areaManageURL.deleteArea,
					contentType : 'application/json;charset=utf-8',
					dataType : 'json',
					data : areaArrJson,
					error : function(xhr, status, err) {
						$.ligerDialog.error('删除区域失败,网络故障!');
						return;
					},
					success : function(data) {
						if (data.success === true) {
							$.ligerDialog.success(data.message);
							queryArea();
						} else {
							$.ligerDialog.error(data.message);
						}
					},
				});
			}) : $.ligerDialog.warn('请选择要删除的数据!');

}

var vehicleBindGripParms = {
	url : D2V.areaManageURL.queryBindedVehicle,
	onLoaded : function(grid) {
		setTimeout(function() {
			$('.l-grid-loading').hide();
			$(".l-grid-row-cell-inner").each(function() {
				var that = $(this);
				that.attr('title', that.text());
			})
		}, 200);
	},
	onSuccess : function(data) {
		if (data.success == true) {
			data.list = data.datas.list;
			data.count = data.datas.count;
		} else {
			$.ligerDialog.warn(data.message);
		}
	},

	onError : function(XMLHttpRequest, textStatus, errorThrown) {
		console.log(textStatus, errorThrown);
	},
	delayLoad : false,
	width : '100%',
	pageSizeOptions : [ 5, 10, 15, 20 ],
	height : 450,
	rownumbers : false,
	usePager : true,
	checkbox : true,
	whenRClickToSelect : true
};
var optcol = [ {
	show : '车牌号码',
	field : 'plateNo',
	width : 200,
	align : 'center'
}, {
	show : '车载号码',
	field : 'callLetter',
	width : 200,
	align : 'center'
}, {
	show : '所属机构',
	field : 'agencyName',
	width : 205,
	align : 'center'
} ];
/**
 * 点击绑车按钮
 * 
 * @param rowIndex
 */
var bindGrid;

/*
 * function startBindAgency(rowIndex) { var selectDatas =
 * mainGrid.getRow(rowIndex); var areaId = selectDatas.areaId; $("#org-dialog
 * input[name='areaId']").prop('value', areaId); openDialog('org-dialog', {
 * title : '区域绑定机构车辆', width : 400, height : 620, isResize : true }, function
 * dialog() { var rowdata = selectDatas[0];
 * $('#org-dialog').find('select[name=areaRule2]').find('option').remove();
 * $.ajax({ url : D2V.areaManageURL.queryAreaRuleByAgency, type : "POST",
 * dataType : 'json', success : function(data) { if (data.success !== true)
 * return; var areaRuleObj = $("#org-dialog select[name='areaRule2']");
 * areaRuleObj.append('<option value="">--选择规则--</option>');
 * $(data.datas).each( function(index, elem) { areaRuleObj.append("<option
 * value=" + elem.ruleId + ">" + elem.ruleName + "</option>"); }); }, error :
 * function(xhr, status, err) { $.ligerDialog.error('保存失败,网络故障!'); return; }, }) }) }
 */

function startBindVehicle(rowIndex) {
	var rowObj = mainGrid.getRow(rowIndex);
	var areaId = rowObj.areaId;
	$("#bind-vehicle-dialog input[name='areaId']").prop('value', areaId);
	vehicleBindGripParms.parms = [ {
		name : "areaId",
		value : areaId
	}, {
		name : "plateNo",
		value : ''
	} ];
	bindGrid = initMainGrid('bind-vehicle-grid', vehicleBindGripParms, optcol);
	// if(vehicleBindGripParms.parms[1]){
	// vehicleBindGripParms.parms[1]={name:"plateNo",value:''};
	// }
	console.log(vehicleBindGripParms.parms);
	openDialog(
			"bind-vehicle-dialog",
			{
				title : '区域绑定车辆',
				width : 670,
				height : 600,
				top : 20
			},
			function dialog() {
				$('#bind-vehicle-dialog').find('select[name=areaRule]').find(
						'option').remove();
				$
						.ajax({
							url : D2V.areaManageURL.queryAreaRuleByAgency,
							type : "POST",
							dataType : 'json',
							success : function(data) {
								if (data.success !== true)
									return;
								var areaRuleObj = $("#bind-vehicle-dialog select[name='areaRule']");
								areaRuleObj
										.append('<option value="">--选择规则--</option>');
								$(data.datas).each(
										function(index, elem) {
											areaRuleObj.append("<option value="
													+ elem.ruleId + ">"
													+ elem.ruleName
													+ "</option>");
										});
							},
							error : function(xhr, status, err) {
								$.ligerDialog.error('保存失败,网络故障!');
								return;
							},
						})
			});
	var ignoreList = {};
	ignoreList["areaId"] = "areaId";
	clearForm('bindVehicleform', ignoreList);
	// gridSearch();
};
// function gridSearch(params) {
// var formParam = getFormData('bindVehicleform');
// if (params !== undefined) formParam = formParam.concat(params);
// formParam.push({
// name: 'page',
// value: 1
// });
// formParam.push({
// name: 'pagesize',
// value: 20
// });
// $("#bind-vehicle-grid").ligerGetGridManager().loadServerData(formParam);
// };
/**
 * 查询车辆
 */
function queryBindVehicle() {
	// 修改显示页码不正常
	bindGrid.setOptions({
		newPage : 1
	});
	var searchData = {};
	var formParam = getFormDataAll('bindVehicleform');
	for ( var k in formParam) {
		var newdata;
		newdata = $.trim(formParam[k]);
		if (newdata != '') {
			searchData[k] = newdata;
			mainGrid.setParm(k, newdata);
		} else {
			mainGrid.removeParm(k);
			delete formParam[k];
		}
	}
	formParam.page = 1;
	var rp_value = $("select[name='rp']").val();
	formParam.pagesize = rp_value;
	// bindGrid.loadServerData(formParam);
	vehicleBindGripParms.parms[1] = {
		name : "plateNo",
		value : $('#bindVehicleform').find('input[name=plateNo]').val()
	};
	initMainGrid('bind-vehicle-grid', vehicleBindGripParms, [ {
		show : '车牌号码',
		field : 'plateNo',
		width : 200,
		align : 'center'
	}, {
		show : '车载号码',
		field : 'callLetter',
		width : 200,
		align : 'center'
	}, {
		show : '所属机构',
		field : 'agencyName',
		width : 205,
		align : 'center'
	} ]);
}

/**
 * 绑定车辆
 */
function bindVehicle() {
	var ruleId = $("#bind-vehicle-dialog select[name='areaRule']")
			.prop("value");
	var areaId = $("#bind-vehicle-dialog input[name='areaId']").prop('value');
	if (ruleId == null || ruleId === "") {
		$.ligerDialog.warn("请选定区域规则!");
		return;
	}
	var selectedRows = bindGrid.getSelectedRows();
	selectedRows.length > 0 ? $.ligerDialog.confirm('确定要绑定所选记录?',
			function(yes) {
				if (yes === false)
					return;

				var vehicleObjArr = [];
				$(selectedRows).each(function() {
					if (this.vehicleId !== undefined)
						vehicleObjArr.push({
							"vehicleId" : this.vehicleId,
							"callLetter" : this.callLetter
						});
				});

				var bindedVehicleJson = {
					"areaId" : areaId,
					"ruleId" : ruleId,
					"vehicle" : vehicleObjArr
				};
				console.log(bindedVehicleJson);
				$.ajax({
					type : "POST",
					url : D2V.areaManageURL.bindVehicle,
					contentType : 'application/json;charset=utf-8',
					dataType : 'json',
					data : JSON.stringify(bindedVehicleJson),
					error : function(xhr, status, err) {
						$.ligerDialog.error('绑定失败,网络故障!');
						return;
					},
					success : function(data) {
						if (data.success === true) {
							$.ligerDialog.success(data.message);
							queryBindVehicle();
						} else {
							$.ligerDialog.error(data.message);
						}
					},

				});
			}) : $.ligerDialog.warn('请选择要绑定的车辆!');
}

// 关闭修改弹框
function closeUpdate() {
	closeDialog('update-dialog');
	clearAllMarker();
}

// 关闭增加弹框
function closeAdddate() {
	closeDialog('add-dialog');
	clearAllMarker();
}

// 修改
$(function() {
	$('#update-form').find('select[name=shapeType]').change(
			function() {
				if ($(this).val() == '4') {
					$('#update-form .updateSShide').hide();
					$('#updateQYDJ').val('');
					$('#update-form .updateSSshow').show();
					$
							.ajax({
								type : "POST",
								url : D2V.areaManageURL.queryProvince,
								dataType : 'json',
								error : function(xhr, status, err) {
									$.ligerDialog.error('网络故障!');
									return;
								},
								success : function(data) {
									if (data.success === true) {
										var d = data.datas;
										var SSLength = d.length;
										$('#update-form').find('.updateSSshow')
												.eq(1)
												.find('select option:not(.a)')
												.remove();
										$('#update-form').find('.updateSSshow')
												.eq(2)
												.find('select option:not(.a)')
												.remove();
										for (var i = 0; i < SSLength; i++) {
											var str = '';
											str += '<option value="' + d[i].id
													+ '" class="ss' + i + '">'
													+ d[i].name + '</option>';
											$('#update-form').find(
													'select[name=province]')
													.append(str);
										}
									} else {
										$.ligerDialog.error(data.message);
									}
								}
							});
				} else {
					$('#update-form').find('.updateSSshow').eq(0).find(
							'select option:not(.a)').remove();
					$('#update-form').find('.updateSSshow').eq(1).find(
							'select option:not(.a)').remove();
					$('#update-form').find('.updateSSshow').eq(2).find(
							'select option:not(.a)').remove();
					$('#updateQYDJ').val('');
					$('#update-form .updateSShide').show();
					$('#update-form .updateSSshow').hide();
				}
			})
	// 选择省份。
	$('#update-form').find('select[name=province]').change(
			function() {
				$('#update-form').find('.updateSSshow').eq(1).find(
						'select option:not(.a)').remove();
				$('#update-form').find('.updateSSshow').eq(2).find(
						'select option:not(.a)').remove();
				if ($(this).val() == '') {
					return;
				} else {
					var thisId = $(this).val();
					$.ajax({
						type : "POST",
						url : D2V.areaManageURL.queryCityByProvince,
						data : {
							province : thisId
						},
						dataType : 'json',
						error : function(xhr, status, err) {
							$.ligerDialog.error('网络故障!');
							return;
						},
						success : function(data) {
							if (data.success === true) {
								var d = data.datas;
								var SSLength = d.length;
								for (var i = 0; i < SSLength; i++) {
									if (SSLength == '0') {

									}
									var str = '';
									str += '<option value="' + d[i].cityid
											+ '" class="ss' + i + '">'
											+ d[i].name + '</option>';
									$('#update-form').find('select[name=city]')
											.append(str);
								}
							} else {
								$.ligerDialog.error(data.message);
							}
						}
					});
				}
			})
	// 选择城市。
	$('#update-form').find('select[name=city]').change(
			function() {
				$('#update-form').find('.updateSSshow').eq(2).find(
						'select option:not(.a)').remove();
				if ($(this).val() == '') {
					return;
				} else {
					var thisId = $(this).val();
					$.ajax({
						type : "POST",
						url : D2V.areaManageURL.queryCountyByCity,
						data : {
							city : thisId
						},
						dataType : 'json',
						error : function(xhr, status, err) {
							$.ligerDialog.error('网络故障!');
							return;
						},
						success : function(data) {
							console.log(data);
							if (data.success === true) {
								var d = data.datas;
								var SSLength = d.length;
								for (var i = 0; i < SSLength; i++) {
									var str = '';
									str += '<option value="' + d[i].countyid
											+ '" class="ss' + i + '">'
											+ d[i].name + '</option>';
									$('#update-form').find(
											'select[name=county]').append(str);
								}
							} else {
								$.ligerDialog.error(data.message);
							}
						}
					});
				}
			})
})

// 点击修改机构保存按钮，主动触发
$('#orgSubmit').on('click', function(e) {
	var form = $('#org-form');
	var onOff = true;
	$('#org-form').each(function(i, elem) {
		if (onOff) {
			var t = $(elem);
			t.removeClass('redBorder');
			var num = $('.l-checkbox-checked');
			if (num == '') {
				$.ligerDialog.warn('请选择一个机构!');
				onOff = false;
			}
		}
	});
	if (!onOff) {
		return;
	}
	// 触发提交表单事件
	form.trigger('submit');
});

// 绑定区域
$('#org-form').submit(
		function(e) {
			$('#orgSubmit').attr('disabled', 'disabled');
			e.preventDefault();
			var vehicleAll = [];
			var SelectData = mainGrid.getSelectedRows();
			var agencyId = $("#org-dialog input[name='agency_id']").val();
			var agencyName = $("#org-dialog input[name='agency_name']").val();
			var ruleName = $(
					"#org-dialog select[name='areaRule2'] option:checked")
					.text();
			var ruleId = $("#org-dialog select[name='areaRule2']")
					.prop("value");
			var areaId = SelectData[0].areaId;
			if (ruleId == null || ruleId === "") {
				$.ligerDialog.warn("请选定区域规则!");
				$('#orgSubmit').removeAttr('disabled');
				return;
			}
			if (agencyId == null || agencyId === "") {
				$.ligerDialog.warn("请选择其中一个机构!");
				$('#orgSubmit').removeAttr('disabled');
				return;
			}
			if (agencyId == "1") {
				$.ligerDialog.warn("请选择除赛格导航外的其它机构!");
				$('#orgSubmit').removeAttr('disabled');
				return;
			}
			var bindedVehicleJson = {
				"areaId" : areaId,
				"ruleId" : ruleId,
				"agencyId" : agencyId
			};
			$.ligerDialog.confirm('您确定给 [ ' + agencyName + ' ] 机构下的所有车辆绑定 [ '
					+ ruleName + ' ] 区域规则吗？', function(yes) {
				if (yes === false) {
					$('#orgSubmit').removeAttr('disabled');
					return;
				}

				$.ajax({
					type : "POST",
					url : D2V.areaManageURL.bindAgencyVehicle,
					contentType : 'application/json;charset=utf-8',
					dataType : 'json',
					data : JSON.stringify(bindedVehicleJson),
					error : function(xhr, status, err) {
						$.ligerDialog.error('绑定失败,网络故障!');
						$('#orgSubmit').removeAttr('disabled');
						return;
					},
					success : function(data) {
						if (data.success === true) {
							$.ligerDialog.success(data.message);
							closeOrg();
						} else {
							$.ligerDialog.error(data.message);
						}
						$('#orgSubmit').removeAttr('disabled');
					},
				});
			});
		});

function closeOrg() {
	closeDialog('org-dialog');
	mainGrid.reload();
}

// 修改机构填充机构树数据
$(function() {
	var onOff = true;
	window.tree = $("#org-tree")
			.ligerTree(
					{
						url : '../twgAgency/queryTreeRootNode',
						nodeWidth : 300,
						checkbox : false,
						// single:true,
						slide : false,
						btnClickToToggleOnly : false,
						isExpand : true,
						idFieldName : 'treedataindex',
						onSelect : function(node) {
							var data = node.data;
							// if (data.delay !== undefined)return;
							// changeURL(node.data.params.id);
							$("#org-dialog input[name='agency_id']").prop(
									'value', data.params.id);
							// saveSearch();
							$("#org-dialog input[name='agency_name']").prop(
									'value', data.params.text);
						},
						isLeaf : function(data) {
							return !(data.delay !== undefined || data.children !== undefined);
						},
						onExpand : function(e) {
							this._parentNode = e.target;
						},
						onError : function(XMLHttpRequest, textStatus,
								errorThrown) {
							console.log(arguments);
						},
						onSuccess : function(newdata) {
							var parentNode = this._parentNode;
							var menuData = [];
							var parent = $(parentNode);
							var parentData = this.getDataByID(parent
									.attr('treedataindex'));
							if (parentData === null) {
								// 父节点为空时（即该节点为根节点时）
								if (onOff) {
									$(newdata.datas)
											.each(
													function() {
														menuData
																.push({
																	text : this.text,
																	delay : {
																		url : '../ twgAgency/queryTreeNodeList'
																				+ '?&nodeId='
																				+ this.id
																	},
																	params : this
																});
													});
									// if(newdata.datas){
									// changeURL(newdata.datas.attributesPath);
									// }
									if (menuData.length <= 0)
										return;
									this.append(this._parentNode, menuData);
									if (parentData)
										delete parentData.delay;
									onOff = false;
									function a() {
										$('.l-body').trigger('click');
									}
									a();
								} else {
									$(newdata.datas.list)
											.each(
													function() {
														var leaf = {
															text : this.text,
															delay : {
																url : '../ twgAgency/queryTreeNodeList'
																		+ '?&nodeId='
																		+ this.id
															},
															params : this
														};
														if (this.isLeaf) {
															delete leaf.delay;
														}
														menuData.push(leaf);
													});
									if (menuData.length <= 0)
										return;
									this.append(this._parentNode, menuData);
									if (parentData)
										delete parentData.delay;
								}
							} else {
								// 该节点有父节点，不为根节点时
								$(newdata.datas.list)
										.each(
												function() {
													var leaf = {
														text : this.text,
														delay : {
															url : '../ twgAgency/queryTreeNodeList'
																	+ '?&nodeId='
																	+ this.id
														},
														params : this
													};
													if (this.isLeaf) {
														delete leaf.delay;
													}
													menuData.push(leaf);
												});
								if (menuData.length <= 0)
									return;
								this.append(this._parentNode, menuData);
								if (parentData)
									delete parentData.delay;
							}
						}
					});
});

