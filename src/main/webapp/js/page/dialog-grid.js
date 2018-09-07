var gridMap;
//初始化弹窗地图
function initGridMap(local,num) {
	gridMap = new BMap.Map("map_grid");
	gridMap.centerAndZoom(local,num);
	gridMap.enableScrollWheelZoom();
	gridMap.enableAutoResize();
}
function shareDialog(){
	var shareWidth = $(".data-list").width();
	var shareHeight = $(".data-list").height();
	initGridMap("中国",5);
	 openDialog(bindDialogId, {
         title: '共享区域下载',
         //width: 700,
         //height: 520,
         width:shareWidth,
         height:shareHeight,
         top:30,
         id:'shareid'
     },function dialog(){
    	 $("#publicAreaSearchForm select[name='gxprovince'] option:first").prop("selected", 'selected');
    	 $("#publicAreaSearchForm select[name='gxcity'] option:first").prop("selected", 'selected');
    	 $("#gxprovince").find("option:selected").text(""); 
    	 $("#gxcity").find("option:selected").text(""); 
     });
	 bindGrids.setOptions({ newPage: 1 }); 
	 bindGrids.reload();
}
function newDrawArea(areadata, shape) {
	clearAllOverlay();
	if (shape == 1) {
		//convertCircleGpsFromStandardToBd(areadata);
		drawCircle(areadata);
		console.log("画圆")
	} else if (shape == 2) {
		//convertRectangleGpsFromStandardToBd(areadata);
		drawRectangle(areadata);
		console.log("矩形")
	} else if (shape == 3) {
		//convertPolygonGpsFromStandardToBd(areadata);
		drawPoly(areadata);
		console.log("多边形")
	} else if (shape == 4) {
		//drawProvinceCityArea(areadata)
		drawProvinceCity(areadata);
		console.log("省区")
	}
}
/**
 * 把矩形形标准GPS坐标转换为百度坐标，并在地图上画出区域
 * 
 * @param areaData
 */
function drawRectangle(areaData) {
	var data = areaData.area;
	var lon1 = data.lon1;
	var lat1 = data.lat1;
	var lon2 = data.lon2;
	var lat2 = data.lat2;
	var pointArr = [];
	pointArr.push(new BMap.Point(lon1, lat1));
	pointArr.push(new BMap.Point(lon2, lat2));
	var convertor = new BMap.Convertor();
	convertor.translate(pointArr, 1, 5, function translateCb(datas) {
		if (datas.status === 0) {
			var convertor = new BMap.Convertor();
			  var ary = [];
			  var ggPoint2 = new BMap.Point(datas.points[0].lng,datas.points[0].lat);
			  var ggPoint3 = new BMap.Point(datas.points[1].lng,datas.points[1].lat);
			  ary.push(ggPoint2);
			  ary.push(ggPoint3);
			  convertor.translate(ary, 1, 5, function(cbdata){
				  var new_rect = new BMap.Polygon([
					                                new BMap.Point(cbdata.points[0].lng,cbdata.points[0].lat),
													new BMap.Point(cbdata.points[0].lng,cbdata.points[1].lat),
													new BMap.Point(cbdata.points[1].lng,cbdata.points[1].lat),
													new BMap.Point(cbdata.points[1].lng,cbdata.points[0].lat)],
													{strokeColor:"blue", strokeWeight:2, strokeOpacity:0.5}); 
					gridMap.addOverlay(new_rect);
					var centerLon = cbdata.points[0].lng
							+ (cbdata.points[1].lng - cbdata.points[0].lng) / 2;
					var centerLat = cbdata.points[0].lat
							+ (cbdata.points[1].lat - cbdata.points[0].lat) / 2;
					gridMap.centerAndZoom(new BMap.Point(centerLon, centerLat), data.mapZoom);
			  })			
		} else {
			console.log("地图坐标转换失败");
		}
	});
}
/**
 * 把圆形标准GPS坐标转换为百度坐标，并在地图上画出区域
 * 
 * @param areaData
 */
function drawCircle(areaData) {
	var data = areaData.area;
	var lon = data.point.split(',')[0];
	var lat = data.point.split(',')[1];
	var r = data.point.split(',')[2];
	var pointArr = [];
	pointArr.push(new BMap.Point(lon, lat));
	var convertor = new BMap.Convertor();
	convertor.translate(pointArr, 1, 5, function translateCb(datas) {
		if (datas.status === 0) {
			  var convertor = new BMap.Convertor();
			  var ary = [];
			  var ggPoint2 = new BMap.Point(datas.points[0].lng,datas.points[0].lat);
			  ary.push(ggPoint2);
			  convertor.translate(ary, 1, 5, function(cbdata){
				  var temp_circle = new BMap.Circle(new BMap.Point(cbdata.points[0].lng,
							cbdata.points[0].lat), r,{strokeColor:"blue", strokeWeight:2, strokeOpacity:0.5});
					gridMap.centerAndZoom(new BMap.Point(cbdata.points[0].lng, cbdata.points[0].lat),
							data.mapZoom);
					console.log(temp_circle)
					gridMap.addOverlay(temp_circle);
			  })
		} else {
			console.log("地图坐标转换失败");
		}
	});
}

/**
 * 多边形新画法
 * 
 * @param areaData
 */
function drawPoly(areaData) {
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
						var p = new BMap.Point(parseFloat(cbpoints[j].x),
								parseFloat(cbpoints[j].y));
						polygonPointArr.push(p);
					}
					
					var  isFinish = false;
					if(polygonPointArr.length>= 10){
						var flag =9
					}else{
						var flag =10
					}
					var allData = [],start = 0;
					//console.log(polygonPointArr);
					//console.log(polygonPointArr.length);
					//console.log(flag);
					if(polygonPointArr.length==18){
						polygonPointArr.push(polygonPointArr[polygonPointArr.length-1]);
					}
					changePoint(polygonPointArr,start);
					function  changePoint(polygonPointArr, start) {
					    var tempArr = polygonPointArr.slice(start, start+flag);
					    //console.log(tempArr.length);
						  if(tempArr.length !=9) {
						      isFinish = true;
						  }
						  var convertor = new BMap.Convertor();
						     console.log(tempArr);
							  convertor.translate(tempArr, 1, 5, function translateCb(datas) {
								allData.push(datas);
								if(isFinish){
								    var ary = [];
									for(var i=0,len=allData.length;i<len;i++){
										for(var j=0;j<allData[i].points.length;j++){
											var p = new BMap.Point(allData[i].points[j].lng,allData[i].points[j].lat);
											ary.push(p);
										}
									}
									var new_polygon = new BMap.Polygon(ary,{strokeColor:"blue", strokeWeight:2, strokeOpacity:0.5});
									gridMap.addOverlay(new_polygon);
									var centerLon = allData[0].points[0].lng
											+ (allData[0].points[1].lng - allData[0].points[0].lng) / 2;
									var centerLat = allData[0].points[0].lat
											+ (allData[0].points[1].lat - allData[0].points[0].lat) / 2;
									gridMap.centerAndZoom(new BMap.Point(centerLon, centerLat), data.mapZoom);
								    return;
								}else{
									changePoint(polygonPointArr, start+flag );
								}
							  })
					}  
					return false;					
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
function drawProvinceCity(areaData) {
	var data = areaData.pointlist;
	var polygone = areaData.polygone;
	for(var l = 0; l < data.length; l++){
		var points = [];
		var pointList = data[l];
		for (var i = 0; i < pointList.length; i++) {
			var point = pointList[i];
			var p = new BMap.Point(point.lon / Math.pow(10, 6), point.lat
					/ Math.pow(10, 6));
			points.push(p);
		}
		var new_polygon =new BMap.Polygon(points,{strokeColor:"blue", strokeWeight:2, strokeOpacity:0.5});
		gridMap.addOverlay(new_polygon);
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
	gridMap.centerAndZoom(new BMap.Point(centerLon, centerLat), mapZoom);
}
//清除地图上留下的所有图标
function clearAllOverlay() {
	gridMap.clearOverlays();
	gridMap.closeInfoWindow();
}
$("#gridClear").on("click",function(){
	clearAllOverlay();
})
function addClickHandler(target,childData){
 	var carIcon = new BMap.Icon("./images/car.png", new BMap.Size(43,21));
     target.addEventListener("click",function(){  //添加监听事件函数
     		var allOverlay = gridMap.getOverlays();
             allOverlay.forEach(function (marker) {
             	if(marker["meterId"]=="v12")
                 {
                 	marker.hide();
                 	$(".v1").hide();
                 }
                 if(marker["meterId"]=="v13")
                 {
                	 gridMap.removeOverlay(pt);
                 }
             })
     	for(var i=0,len=childData.area.length;i<len;i++){
     		var pt = new BMap.Point(childData.area[i].x, childData.area[i].y);
					var marker2 = new BMap.Marker(pt,{icon:carIcon});  // 创建标注
					marker2.meterId="v13";
					gridMap.addOverlay(marker2);              // 将标注添加到地图中
       	}
     });
 }
function distribution(){
	var gxprovinceVal = $("#gxprovince").find("option:selected").text();
	var gxcityVal = $("#gxcity").find("option:selected").text();
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
	if($("#gxcity").val()==""){
		if($("#gxprovince").val()==""){
			initGridMap("中国",5);
			$.ajax({
				type:"post",
				url:D2V.areaManageURL.showPublicAreaCount,
				data:formParam,
				success:function(data){
					var pt,marker,label,opts;
					var myIcon = new BMap.Icon("./images/red3.png", new BMap.Size(100,100));
					for(var i=0,len=data.datas.length;i<len;i++){
						pt = new BMap.Point(data.datas[i].lon, data.datas[i].lat);
						marker = new BMap.Marker(pt,{icon:myIcon});  // 创建标注
						marker.meterId="v12";
						opts = {
						  position : pt,    // 指定文本标注所在的地理位置
						  offset   : new BMap.Size(-45, -35)    //设置文本偏移量
						}
						label = new BMap.Label("<span class='v1 bg1'>"+data.datas[i].name+"<br/>"+data.datas[i].num+"</span>", opts);  // 创建文本标注对象
						label.setStyle({
							 color : "#fff",
							 fontSize : "14px",
							 height : "20px",
							 lineHeight : "20px",
							 fontFamily:"微软雅黑",
							 display:'block',
							 textAlign:'center'
						});
						gridMap.addOverlay(label);
						gridMap.addOverlay(marker);              // 将标注添加到地图中
						addClickHandler1(marker,data.datas[i]);
					}				
				}
			})
		}else{
			initGridMap(gxprovinceVal,8);
			$.ajax({
				type:"post",
				url:D2V.areaManageURL.showPublicAreaCount,
				data:formParam,
				success:function(data){
					var pt,marker,label,opts;
					var myIcon = new BMap.Icon("./images/red3.png", new BMap.Size(100,100));
					for(var i=0,len=data.datas.length;i<len;i++){
						pt = new BMap.Point(data.datas[i].lon, data.datas[i].lat);
						marker = new BMap.Marker(pt,{icon:myIcon});  // 创建标注
						marker.meterId="v12";
						opts = {
						  position : pt,    // 指定文本标注所在的地理位置
						  offset   : new BMap.Size(-45, -35)    //设置文本偏移量
						}
						label = new BMap.Label("<span class='v1 bg1'>"+data.datas[i].name+"<br/>"+data.datas[i].num+"</span>", opts);  // 创建文本标注对象
						label.setStyle({
							 color : "#fff",
							 fontSize : "14px",
							 height : "20px",
							 lineHeight : "20px",
							 fontFamily:"微软雅黑",
							 display:'block',
							 textAlign:'center'
						});
						gridMap.addOverlay(label);
						gridMap.addOverlay(marker);              // 将标注添加到地图中
						addClickHandler2(marker,data.datas[i]);
					}				
				}
			})
		}
		
	}else{
		$.ajax({
			type:"post",
			url:D2V.areaManageURL.showPublicCityArea,
			data:formParam,
			success:function(data){
				if(gxcityVal=="直辖市"){
					initGridMap(gxprovinceVal,11)
				}else{
					initGridMap(gxcityVal,11);
				}
				for(var i=0,len=data.datas.length;i<len;i++){
					if(data.datas[i].shapeType=="3"){
						addMap(data.datas[i],";");
					}else{
						addMap(data.datas[i],",");
					}					
				}
			}			
		})
	}
	
	
}
/*
 * 显示车辆具体的分布
 * 
 */
function addMap(data,semicolon){
	var carIcon = new BMap.Icon("./images/car.png", new BMap.Size(43,21));
	var marker;
	var gxprovinceVal = $("#gxprovince").find("option:selected").text();
	if(semicolon==","){
		var points = data.point.split(semicolon);
		var lon = points[0].split(',');
		var lat = points[1].split(',');
		var points = new BMap.Point(lon,lat);
	}else{
		var points = data.point.split(semicolon);
		var lonLat = points[0].split(',');
		var points = new BMap.Point(lonLat[0],lonLat[1]);
	}	
	marker = new BMap.Marker(points,{icon:carIcon});  // 创建标注
	marker.meterId="v13";
	gridMap.addOverlay(marker);              // 将标注添加到地图中
}
/*
 * 地图对象
 * 点击地图上的省份事件
 */
function addClickHandler1(target,data){
    target.addEventListener("click",function(){  //添加监听事件函数
    	checkCityName(data.name,data.id);
    	initGridMap(data.name,8);
        $("#parentId").val(data.id);
        $("#parentId").attr("parentName",data.name);
		$.ajax({
			type:"post",
			url:D2V.areaManageURL.showPublicAreaCount,
			data:{gxprovince:data.id},
			success:function(data){
				clearAllOverlay();
				var pt,marker,label,opts;
				var myIcon = new BMap.Icon("./images/red3.png", new BMap.Size(100,100));
				for(var i=0,len=data.datas.length;i<len;i++){
					pt = new BMap.Point(data.datas[i].lon, data.datas[i].lat);
					marker = new BMap.Marker(pt,{icon:myIcon});  // 创建标注
					marker.meterId="v12";
					opts = {
					  position : pt,    // 指定文本标注所在的地理位置
					  offset   : new BMap.Size(-45, -35)    //设置文本偏移量
					}
					label = new BMap.Label("<span class='v1 bg1'>"+data.datas[i].name+"<br/>"+data.datas[i].num+"</span>", opts);  // 创建文本标注对象
					label.setStyle({
						 color : "#fff",
						 fontSize : "14px",
						 height : "20px",
						 lineHeight : "20px",
						 fontFamily:"微软雅黑",
						 display:'block',
						 textAlign:'center'
					});
					gridMap.addOverlay(label);
					gridMap.addOverlay(marker);              // 将标注添加到地图中
					addClickHandler2(marker,data.datas[i]);
				}				
			}
		})
    });
}
/*
 * 对窗列表对象
 * 下拉框的省份也随之查询
 */
function checkCityName(name,thisId){
	$("#gxprovince").find("option:selected").text(); 
	$("#gxprovince").val(thisId);
   	 
        $.ajax({
          type: "POST",
          url:D2V.areaManageURL.queryCityByProvince,
          data:{province:thisId},
          dataType:'json',
          error:function(xhr,status,err){
              $.ligerDialog.error('网络故障!');
              return;
          },
          success:function(data){
            if(data.success===true){
              var d=data.datas;
              var SSLength=d.length;
              for(var i=0;i<SSLength;i++){
                var str='';
                str+='<option value="'+d[i].cityid+'" class="ss'+i+'">'+d[i].name+'</option>';
                $('#publicAreaSearchForm').find('select[name=gxcity]').append(str);
              }
            }else{
               $.ligerDialog.error(data.message);
            }
          }
        });
        searchShareArea(); 
        //$('#publicAreaSearchForm').find('select[name=gxcity]').find('option:not(.a)').remove();
}
/*
 * 地图对象
 * 点击地图上的市级事件
 */
function addClickHandler2(target,data){
    target.addEventListener("click",function(){  //添加监听事件函数
    	clearAllOverlay();
    	var parentId = $("#parentId").val();
        var gxprovinceVal = $("#parentId").attr("parentName");
        var gxcityVal = data.name;
        $("#childId").val(data.id);
        $("#childId").attr("parentName",data.name);
        checkCityName2(data.name,data.id);
    	if(gxcityVal=="直辖市"){
			initGridMap(gxprovinceVal,11)
		}else{
			initGridMap(gxcityVal,11);
		}
    	$.ajax({
			type:"post",
			url:D2V.areaManageURL.showPublicCityArea,
			data:{
				gxcity:data.id,
				gxprovince:parentId
			},
			success:function(data){
				for(var i=0,len=data.datas.length;i<len;i++){
					if(data.datas[i].shapeType=="3"){
						addMap(data.datas[i],";");
					}else{
						addMap(data.datas[i],",");
					}					
				}
			}			
		})
    });
}
/*
 * 对窗列表对象
 * 下拉框的城市也随之查询
 */
function checkCityName2(name,thisId){
	$("#gxcity").find("option:selected").text(); 
	$("#gxcity").val(thisId);
    searchShareArea();
    distribution();
}
