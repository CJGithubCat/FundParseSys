//---车辆监控页面地图-------
var map;// 地图对象
var latestGPSInfo = {};// 最新的gpsinfo
var vehicleMarker;// 车辆图标
var gpsTaskInterval;// gps信息更新轮询
var historyid  = 1;  
var baiduMap;//原始地图对象

var marker=null;
var drawingManager=null;
var myDis =null;
function initMap() {
	SEGUtil.imageRootDir = "resources/images/map";
	map = new SEGMap("map_canvas", "baidu");
	map.centerAndZoom(113.940301, 22.535572, 16);
	baiduMap = map.getOriMap();
	myDis = new BMapLib.DistanceTool(baiduMap);
	
	var size = new BMap.Size(55, 10);
	/*baiduMap.addControl(new BMap.CityListControl({
	    anchor: BMAP_ANCHOR_TOP_LEFT,
	    offset: size,
	    // 切换城市之间事件
	    // onChangeBefore: function(){
	    //    alert('before');
	    // },
	    // 切换城市之后事件
	    // onChangeAfter:function(){
	    //   alert('after');
	    // }
	}));*/
	
   /**
	*右键菜单,添加标注操作----start------
	*/	
	var menu=new BMap.ContextMenu();
	var txtMenuItem = [
	  //标注位置菜单项                 
	  {
	     text:'标注位置',
	     callback:function(point){	    	
	    	map.clearNonStaticMarkers();
	    	baiduMap.removeOverlay(marker);	
	    	
	        marker = new BMap.Marker(point), px = baiduMap.pointToPixel(point);	
	        
	    	var options={width:200,hegith:80,title:"新增位置标注"};
	    	var domStr="<div>标注名称:<input type='text' id='mapLabelName' style='width:100px'>";
	    	domStr+="<input type='button' onclick='addMapLabel()' style='width:40px' value='标注'>";
	    	domStr+="<input type='hidden' id='mapLabelLat' value='"+point.lat+"'>";
	    	domStr+="<input type='hidden' id='mapLabelLng' value='"+point.lng+"'></div>";
	    	var pointLabelInfoWindow=new BMap.InfoWindow(domStr,options);
	    	pointLabelInfoWindow.disableCloseOnClick();
	    	pointLabelInfoWindow.addEventListener("clickclose",function(e){
	    	   baiduMap.removeOverlay(marker);
	    	});	
            	    	
	    	baiduMap.addOverlay(marker);
	    	marker.openInfoWindow(pointLabelInfoWindow);
	     },
	     opts:{width:120}	
	 },
	 //地图区域内车辆菜单项
	 {
		 text:'拉框选择',
		 callback:function(point){
		       map.clearNonStaticMarkers();
		       var callback=function(lon1,lat1,lon2,lat2,marker){		    	   
		    	   var new_rect = map.newRectangle(lon1, lat1, lon2, lat2);
				   map.addMarker(new_rect);
				   $.ligerDialog.confirm('查询此区域内的车辆?', function(yes){
					  if(yes==false){
						  map.clearNonStaticMarkers();
						  return;
					  };			    	
					  var lonMin,lonMax,latMin,latMax;
		   		      lon1<=lon2?(lonMin=lon1,lonMax=lon2):(lonMin=lon2,lonMax=lon1);
					  lat1<=lat2?(latMin=lat1,latMax=lat2):(lonMin=lat2,lonMax=lat1);
					  var params=[];
			   		  params.push({name:'lonMin',value:lonMin});
			   		  params.push({name:'lonMax',value:lonMax});
			   		  params.push({name:'latMin',value:latMin});
			   		  params.push({name:'latMax',value:latMax});
			   		  console.log(lonMin,lonMax,latMin,latMax);
					  map.clearNonStaticMarkers();	
		   		      //跳转TAB页
					  var liSelected=$("#left-tab li");  
					 if(liSelected.hasClass("l-selected")){
						 liSelected.removeClass("l-selected");
						 $("#left-tab li[tabid='vehicle-query']").addClass("l-selected");
						 $("div[tabid='enterprise-vehicle']").hide();
						 $("div[tabid='monitor-list']").hide();
						 $("div[tabid='vehicle-query']").show();
						 $("#vehicle-query-layout .l-layout-center").css("width","290px");
						 $("#areavehicle-query-grid .l-grid2").css("width","260px");
						 $("#vehicle-query-grid").hide();
						 $("#areavehicle-query-grid").show();
					 }
					 params.forEach(function(item){
						 CamionView.areavehiclequerygrid.setParm(item.name,item.value);
					 });
					CamionView.areavehiclequerygrid.loadServerData(params);	   
		   	   });				   
		     }
		   	 map.drawRectangle(callback);
		    },
	     /*callback:function(point){
	       map.clearNonStaticMarkers();
	       var callback=function(lon1,lat1,lon2,lat2,marker){	    	   
	    	   console.log("百度地图坐标",lon1,lat1,lon2,lat2);		    	   
	    	   var new_rect = map.newRectangle(lon1, lat1, lon2, lat2);
			   map.addMarker(new_rect);
			   $.ligerDialog.confirm('查询此区域内的车辆?', function(yes){
				  if(yes==false){
					  map.clearNonStaticMarkers();
					  return;
				  };
				  var pointArr = [];
				  pointArr.push(new BMap.Point(lon1,lat1));
				  pointArr.push(new BMap.Point(lon2,lat2));
				  new BMap.Convertor().translate(pointArr, 1, 5, function translateCallback(data){
				    if(data.status === 0) {
				    	var lon1Cov=data.points[0].lng;
				    	var lon2Cov=data.points[1].lng;
				    	var lat1Cov=data.points[0].lat; 
				    	var lat2Cov=data.points[1].lat;
				    	var gpsLon1=2*lon1-lon1Cov;
				    	var gpsLon2=2*lon2-lon2Cov
				    	var gpsLat1=2*lat1-lat1Cov;
				    	var gpsLat2=2*lat2-lat2Cov;				    	
				    	console.log("转换后的gps坐标",gpsLon1,gpsLat1,gpsLon2,gpsLat2);				    	
				    	var lonMin,lonMax,latMin,latMax;
				    	gpsLon1<=gpsLon2?(lonMin=gpsLon1,lonMax=gpsLon2):(lonMin=gpsLon2,lonMax=gpsLon1);
				    	gpsLat1<=gpsLat2?(latMin=gpsLat1,latMax=gpsLat2):(lonMin=gpsLat2,lonMax=gpsLat1);
				    	$("#lonMin").val(lonMin);
				    	$("#lonMax").val(lonMax);
				    	$("#latMin").val(latMin);
				    	$("#latMax").val(latMax);
				    	var params=[];
	   		            params.push({name:'lonMin',value:lonMin});
	   		            params.push({name:'lonMax',value:lonMax});
	   		            params.push({name:'latMin',value:latMin});
	   		            params.push({name:'latMax',value:latMax});
						map.clearNonStaticMarkers();	
	   		            //跳转TAB页
						var liSelected=$("#left-tab li");  
						if(liSelected.hasClass("l-selected")){
							 liSelected.removeClass("l-selected");
							 $("#left-tab li[tabid='vehicle-query']").addClass("l-selected");
							 $("div[tabid='enterprise-vehicle']").hide();
							 $("div[tabid='monitor-list']").hide();
							 $("div[tabid='vehicle-query']").show();
							 $("#vehicle-query-layout .l-layout-center").css("width","290px");
							 $("#areavehicle-query-grid .l-grid2").css("width","260px");
							 $("#vehicle-query-grid").hide();
							 $("#areavehicle-query-grid").show();
						 }
						 params.forEach(function(item){
							 CamionView.areavehiclequerygrid.setParm(item.name,item.value);
						 });
						 CamionView.areavehiclequerygrid.loadServerData(params);						 
						 //CamionView.areavehiclequerygrid.reload();
				    }
			   });
	   	   });
			   
	       }
	   	   map.drawRectangle(callback);
	    },*/
	    opts:{width:120}
	 }
    ];
	
	for(var i=0; i < txtMenuItem.length; i++){
	  menu.addItem(new BMap.MenuItem(txtMenuItem[i].text,txtMenuItem[i].callback,txtMenuItem[i].opts));
	  if(i!=txtMenuItem.length-1){
	     menu.addSeparator();
	  }
	}
	
	baiduMap.addContextMenu(menu);	
	
	/**
	*右键菜单，添加标注操作 end-----
	*/
	//--显示用户标注的所有位置
	queryUserMapLabel();
	
}
/**
 * 地图上加载所有用户标注的位置
 */
function queryUserMapLabel(){
	$.ajax({
        type:"POST",
	     dataType:"json",
	     url:'../vehicle/queryUserMapLabel',
	     success:function(data){
			if(data.success){
			   var labelList=data.datas;
			   if(labelList==null||labelList.length!=0){
				 for(i=0;i<labelList.length;i++){					  
				   /*addMarkerWithId(labelList[i].lon,labelList[i].lat,labelList[i].labelName,labelList[i].labelId,markerClickHandler);*/
					var tempMarker=new BMap.Marker(new BMap.Point(labelList[i].lon,labelList[i].lat));
					tempMarker.disableMassClear();
					baiduMap.addOverlay(tempMarker);					
					markerMenuHandler(labelList[i].labelId,tempMarker);	
					markerMouseEventHandler(labelList[i].labelId,tempMarker);
				 }				
			   }
		    }else{
				console.log(data.message);
		    }
		 },
		 error:function(xhr,status,err){
			 console.log(status,err);
			 return;
		 }		
	 });
}
//给位置标注添加右键菜单
function markerMenuHandler(labelId,marker){
	//删除标注菜单
	var removeMarker=function(e,ee,marker){
		removeLabel(labelId,marker);
	}
	var updateMarker=function(e,ee,marker){
		var options={width:200,hegith:80,title:"修改位置标注"};
    	var domStr="<div>标注名称:<input type='text' id='updateLabelName' style='width:100px'>";
    	domStr+="<input type='button' onclick='updateMapLabel()' style='width:40px' value='修改'>";
    	domStr+="<input type='hidden' id='updateLabelId' value='"+labelId+"'></div>";
    	var updateLabelInfoWindow=new BMap.InfoWindow(domStr,options);
    	updateLabelInfoWindow.disableCloseOnClick();
    	marker.openInfoWindow(updateLabelInfoWindow);
	}
	var markerMenu=new BMap.ContextMenu();
	markerMenu.addItem(new BMap.MenuItem("删除标注",removeMarker.bind(marker)));
	markerMenu.addSeparator();
	markerMenu.addItem(new BMap.MenuItem("修改标注",updateMarker.bind(marker)));
	marker.addContextMenu(markerMenu);	       
}
//给位置标注添加鼠标时间
function markerMouseEventHandler(labelId,marker){
	marker.addEventListener("mouseover", function(e){		
	   $.ajax({
		 type:"POST",
		 dataType:"json",
		 url:'../vehicle/findMapLabelById',
		 data:{"labelId":labelId},
		 success:function(data){
		   if(data.success){
			   var infoWindowContent="位置名称："+data.datas.labelName+"<br>"+"位置经度："+e.point.lng+"<br>";
			   infoWindowContent+="位置纬度："+e.point.lat+"<br>"+"标注时间："+new Date(data.datas.stamp).Format("yyyy-MM-dd hh:mm:ss");
			   var infoWindow=new BMap.InfoWindow(infoWindowContent,{width:0,height:0});
			   marker.openInfoWindow(infoWindow);
		   }else{
			   console.log(data.message);
		   }
		 },
		 error:function(xhr,status,err){
			 
		    return ;
		 }		
		});
	});
	marker.addEventListener("mouseout", function(e){
		marker.openInfoWindow();
	});
}

//删除一个标注位置
  function removeLabel(labelId,marker){
    $.ligerDialog.confirm('确定要删除此标注?', function(yes){
       if(yes===false) return;
       $.ajax({
	      type:"POST",
		  dataType:"json",
		  url:'../vehicle/deleteMapLabel',
		  data:{"labelId":labelId},
		  success:function(data){
		     if(data.success){
			    baiduMap.removeOverlay(marker);
		     }else{
			    console.log(data.message);
			    $.ligerDialog.error("操作失败");
		     }
		  },
		  error:function(xhr,status,err){
			 console.log(status,err);
			 $.ligerDialog.error("操作失败,请联系客服人员");
			 return;
		  }		
	   });    	
    })
  }
 //更新标注位置
  function updateMapLabel(){
	console.log(labelId);
	var labelId=$("#updateLabelId").val();
	var labelName=$("#updateLabelName").val();
	console.log(labelId+"  "+labelName);
	$.ligerDialog.confirm('确定要修改此标注?', function(yes){
		if(yes==false) return;
			$.ajax({
			  type:"POST",
			  dataType:"json",
			  url:'../vehicle/updateMapLabel',
			  data:{"labelId":labelId,"labelName":labelName},
			  success:function(data){
			    if(data.success){
			    	baiduMap.closeInfoWindow();
			    }else{
				  console.log(data.message);
				  $.ligerDialog.error("操作失败");
			    }
			  },
			  error:function(xhr,status,err){
			     console.log(status,err);
			   	 $.ligerDialog.error("操作失败,请联系客服人员");
			   	 return;
			  }		
		    });	
	});
 }

//地图上添加一个带id的marker
/*
function addMarkerWithId(lon,lat,title,id,clickHandler){
	var marker=new BMap.Marker(new BMap.Point(lon,lat));
	marker.setTitle(title);
	if(clickHandler){		
		marker.addEventListener("dblclick",function(e){
		  console.log(id);
		  deleteMapLabel(e,id); 
		});
	}
	baiduMap.addOverlay(marker);
}
function markerClickHandler(e, id){
} 
*/



/**
 * 添加一个位置标注到数据库
 */
function addMapLabel(){
	var mapLabelName=$.trim($("#mapLabelName").val());
	if(mapLabelName==null||mapLabelName==""){
		return;
	};
	$.ligerDialog.confirm("确定标注此位置?",function(yes){
	   	 if(yes===false){
	   		baiduMap.removeOverlay(marker);
	   		return;
	   	 }   	 
	   	 var mapLabelLat=$("#mapLabelLat").val();
	   	 var mapLabelLng=$("#mapLabelLng").val();
   	     $.ajax({
             type:"POST",
    	     dataType:"json",
    	     url:'../vehicle/addMapLabel',
    	     data:{"mapLabelName":mapLabelName,"mapLabelLat":mapLabelLat,"mapLabelLng":mapLabelLng},
    	     success:function(data){
    			if(data.success){
    				var labelId=data.datas;
    				marker.closeInfoWindow();
    				marker.disableMassClear();
    				/*
    				 var label = new BMap.Label(mapLabelName,{offset:new BMap.Size(20,-10)});
    		    	 label.setStyle({color : "black",border:"none",background:"none",fontSize : "14px",fontFamily:"微软雅黑"});	   		    	
    		    	 marker.setLabel(label);
    		    	*/
    		    	//marker.setTitle(marker);
    		    	markerMenuHandler(labelId,marker);	
					markerMouseEventHandler(labelId,marker);
					
    				marker=null;
    			}else{
    				console.log(data.message);
    				$.ligerDialog.error("操作失败");
    			}
    		 },
    		 error:function(xhr,status,err){
    			 console.log(status,err);
    			 $.ligerDialog.error("操作失败,请联系客服人员");
    			 return;
    		 }		
    	 });
    })
}


/**
 * 车辆管理界面双击列表及右键发送指令查询最后位置
 * */
function camionQueryFinalPosition(unitid) {
	$.ajax({
			url : 'VehicleServlet',
			type : 'GET',
			async : false,
			data : 'method=queryFinalPosition&unitid=' + unitid,
			success : function(txt) {
				var json = eval("(" + txt + ")");
				if (json.success) {
					if (!json.data.lon) {
							$.ligerDialog.warn("对不起,没有查询到最后位置信息!");
						return;
					}
					map.addOrUpdateVehicleMarkerById({
								id : parseInt(json.data.unitid),
								callLetter : json.data.call_letter,
								numberPlate : json.data.number_plate,
								label : json.data.number_plate,
								isLoc : true,
								referencePosition : json.data.position,
								lon : json.data.lon,
								lat : json.data.lat,
								speed : json.data.speed,
								course : json.data.coursenumber,
								gpsTime : json.data.gpstime,
								isAlarm : json.data.isAlarm,
								status :  json.data.state
							});
					if(CamionView) CamionView.gpsInfoGrid.addRow(json.data,CamionView.gpsInfoGrid.rows[0],true);
					if (!map.isPointInView(json.data.lon,
							json.data.lat)) {
						map.centerAndZoom(json.data.lon, json.data.lat, 16);
					}
				}
			},
			failure : function(response) {
				// $.ligerDialog.warn("加载失败!");
			}
		});
}
/**
 * 监控列表刷新定位数据
 */
function camionGpsTask() {
	var selectLength = CamionView.monitorGrid.currentData.list.length;
	var selectUnitIDs = "";
	if (selectLength != 0) {
		for (var i = 0; i < selectLength; i++) {
			selectUnitIDs += CamionView.monitorGrid.currentData.list[i].unitid+ ',';
		}
		$.ajax({
					url : 'StandardReturnGPS',
					type : 'GET',
					async : false,
					data : 'method=getGpsInfo&historyid=' + historyid
							+ '&unitid=' + selectUnitIDs + "&randnum="
							+ RndNum(8),
					success : function(txt) {
						var json = eval(txt);
						if (json.success) {
							var data = json.data;
							historyid = data.historyid;
							// 最新的gps定位信息
							var gpsinfo = [];
							if (data.timeing) {
								gpsinfo = data.timeing.carlsit || [];
							}
							var onegps;
							var gpsmarker;
							for (var i = 0, b = gpsinfo.length; i < b; i++) {
								onegps = gpsinfo[i];
								onegps["loc_state"]=1;
								onegps["distance"]=onegps["distance"]/1000;
								map.addOrUpdateVehicleMarkerById({
											id : parseInt(onegps["unitid"]),
											callLetter : onegps["call_letter"],
											numberPlate : onegps["number_plate"],
											label : onegps["number_plate"],
											isLoc : true,
											referencePosition : onegps["position"],
											lon : onegps["lon"],
											lat : onegps["lat"],
											speed : onegps["speed"],
											course : onegps["coursenumber"],
											gpsTime : onegps["gpstime"],
											isAlarm : onegps["isalarm"],
											status : onegps["state"]
										});
								if (CamionView)
									CamionView.gpsInfoGrid.addRow(onegps,CamionView.gpsInfoGrid.rows[0],true);
								if (!map.isPointInView(onegps["lon"],onegps["lat"])) {
									map.centerAndZoom(onegps["lon"],onegps["lat"], 16);
								}
							}
						}
					},
					failure : function(response) {
						
					}
				});
	}
	setTimeout(camionGpsTask, 5000);
}
/**
 * 读取GPS信息
 */
function gpsTask() {
	$.ajax({
		url : '../StandardReturnGPS',
		type : 'GET',
		async : false,
		data : 'method=getGpsInfo&historyid=' + historyid + '&unitid=' + unitId
				+ "&randnum=" + RndNum(8),
		success : function(json) {
			if (json.success) {
				var data = json.data;
				historyid = data.historyid;
				var gpsinfo = [];
				if (data.timeing)
					gpsinfo = data.timeing.carlsit || [];
				if (gpsinfo.length < 1) {// 没有从memcache获取到数据,取最后位置，并在非熄火时制造假数据
					if (!latestGPSInfo.lon)
						queryFinalPosition();
					if (!latestGPSInfo.lon) {
						$.ligerDialog.warn("没有查询到最后位置信息!");
						return;
					}
					if (latestGPSInfo.state.indexOf("23") < 0) {// 非熄火
						latestGPSInfo.gpstime = getNewGpsTime(latestGPSInfo.gpstime);
						latestGPSInfo.speed = rd(2, 5);
					}
				} else {
					emptyGPSInfo();
					latestGPSInfo = gpsinfo[0];
				}
				addNewPoint();
			}
		},
		dataType : 'json'
	});
	if (!gpsTaskInterval) {
		gpsTaskInterval = setInterval(gpsTask, 5000);
	}
}

function emptyGPSInfo() {
	latestGPSInfo.unitid = null;
	latestGPSInfo.call_letter = null;
	latestGPSInfo.number_plate = null;
	latestGPSInfo.position = null;
	latestGPSInfo.lon = null;
	latestGPSInfo.lat = null;
	latestGPSInfo.speed = null;
	latestGPSInfo.coursenumber = null;
	latestGPSInfo.gpstime = null;
	latestGPSInfo.state = null;
}
function createOrUpdateVehicleMarker() {
	vehicleMarker = map.addOrUpdateVehicleMarkerById({
				id : parseInt(latestGPSInfo.unitid),
				callLetter : latestGPSInfo.call_letter,
				numberPlate : latestGPSInfo.number_plate,
				label : latestGPSInfo.number_plate,
				isLoc : true,
				referencePosition : latestGPSInfo.position,
				lon : latestGPSInfo.lon,
				lat : latestGPSInfo.lat,
				speed : latestGPSInfo.speed,
				course : latestGPSInfo.coursenumber,
				gpsTime : latestGPSInfo.gpstime,
				stamp : latestGPSInfo.gpstime,
				isAlarm : isAlarm,
				status : latestGPSInfo.state
			});
	// 速度显示
	if (latestGPSInfo.speed >= 50) {
		$("#vehicleSpeed").css("font-weight", "bold");
		$("#vehicleSpeed").css("color", "red");
	} else {
		$("#vehicleSpeed").css("font-weight", "normal");
		$("#vehicleSpeed").css("color", "green");
	}
	$("#vehicleSpeed").html(latestGPSInfo.speed + "&nbsp;&nbsp;km/h");
	return vehicleMarker;
}

// 画圆形区域
function loadCircle() {
	var lon = 113.947116;
	var lat = 22.532861;
	var r = 419;
	var temp_circle = map.newCircle(lon, lat, r);
	map.addMarker(temp_circle);
}

function validateHistoryOperate(){
	var searchData = {};
	var carNum = $('#carNum').val();
	var txt_startTime = $('#txt_startTime').val();
	var txt_endTime = $('#txt_endTime').val();
	var speed = $('#speed').val();
	var d1=new Date(txt_startTime);
	var d2=new Date(txt_endTime);
	var distance=(d2-d1)/(1000*60*60);
	if (carNum == '') {
		$.ligerDialog.warn('请填写或选择车牌号码!');
		return false;
	} else if (txt_startTime == '') {
		$.ligerDialog.warn('请选择开始时间!');
		return false;
	} else if (txt_endTime == '') {
		$.ligerDialog.warn('请选择结束时间!');
		return false;
	} else if (speed == '') {
		$.ligerDialog.warn('请填写最小速度!');
		return false;
	}else if(distance>72){
		$.ligerDialog.warn('时间长度不能大于3天!');
		return false;
	}else if(distance<=0){
		$.ligerDialog.warn('结束时间不能早于开始时间!');
		return false;
	}else{
		return true;
	}
}

$(function(){
	$('.controll').on('click','button',function(e){
		var list=$('.xxNum');
		var cx=$('.chaxunNum').html();
		var i=$(this);
		if(i.hasClass('qingk')){
			CamionView.gpsInfoGrid.reload();
	      	list.html('总共查询到 <span style="color:red;" class="chaxunNum">0</span> 条信息');
	      	$('.controll button').addClass('controllAct');
			$('#lon').html('');
			$('#lat').html('');
			$('#gpstime').html('');
			$('#nowSpeed').html('');
			clearAllMarker();
			return;
		}
		if(cx==0){
			$.ligerDialog.warn('当前无历史轨迹数据!');
		}
	})
})

//清除所有标注
function clearAllMarker(){
	baiduMap.clearOverlays();
	map.clearNonStaticMarkers();
	map.clearVehicleMarkers();
	map.closeAllInfoWindow();
}
function ceju(){
	myDis.open();
}