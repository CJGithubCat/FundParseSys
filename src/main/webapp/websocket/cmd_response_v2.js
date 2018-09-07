/*!
 * 
 * Copyright 2014 chinagps, Inc. zhangxz
 * 
 */
"use strict";
/*
 * 
 */
var gpsLength=30;

//记录日志
var log = document.getElementById("log");

//websocket通信错误事件
function onerror(cli, event) {
    log.value += "websocket出现错误:" + cli.id + ", " + event.type + ", " + event.target.url + "\n";
};

//websocket连接成功事件
function onopen(cli, event) {
    log.value += "websocket连接成功:" + cli.id + ", " + event.type + ", " + event.target.url + "\n";
};

//websocket连接关闭事件
function onclose(cli, event) {
    log.value += "websocket断开连接:" + cli.id + ", " + event.type + ", " + event.target.url + "\n";
};

//websocket客户端登录(连接成功后自动登录，不用人工登录) 事件
function onlogin(cli, retcode, retmsg) {
	log.value += "通信中心返回登录结果: retcode=" + retcode  + ", retmsg=" + retmsg + "\n";
	if(retcode==0){//连接成功
		// var callletters = [];//呼号列
		// var data = $('#monitor-table').bootstrapTable('getData');//获取列表所有监控列表信息
		// $.each(data, function (i,item) {
	 //        callletters.push(item.callLetter);
	 //    });
	 //    // console.log(callletters);
	 //    AddMonitor(callletters);//通信中心加监控
	} else{//登录失败
		
	}
};

//添加监控列表结果事件
function onaddmonitor(cli, retcode, retmsg, callLetters) {
	log.value += "通信中心返回添加监控列表结果: retcode=" + retcode  + ", retmsg=" + retmsg + ", all callletter:\n";
	for(var i=0; i<callLetters.length; i++) {
		log.value += callLetters[i] + "\n";
	};
	
	if(retcode){//retcode=0为error
		alert(retmsg);
	}
	
};

//取消监控列表结果事件
function onremovemonitor(cli, retcode, retmsg, callLetters) {
	log.value += "通信中心返回取消监控列表结果: retcode=" + retcode  + ", retmsg=" + retmsg + ", remain callletter:\n";
	for(var i=0; i<callLetters.length; i++) {
		log.value += callLetters[i] + "\n";
	};

	if(retcode){//error
		alert(retmsg);
	}	
};

//指令发送结果
function onsendcommandsend(cli, sn, callLetter, cmdId, retcode, retmsg){//等待时
	log.value += "发送指令: " + callLetter + ", Id=" + cmdId + ", sn=" + sn;
	log.value += ", retcode=" + retcode  + ", retmsg=" + retmsg + "\n";
}

//车台回应
function onsendcommand(cli, sn, callLetter, cmdId, retcode, retmsg, params, gpsInfo){
	log.value += "车台回应: " + callLetter + ", Id=" + cmdId + ", sn=" + sn;
	log.value += ", retcode=" + retcode  + ", retmsg=" + retmsg + "\n";

	var cmdtime = SEGUtil_WS.date_2_string(new Date, "yyyy-MM-dd hh:mm:ss"); 
	var msg = "";//回应信息
	var tdHtml = "";

	if(retcode==0){//成功
		msg="成功";
		tdHtml = "<span class='fa-ok color-success'></span>"+msg;
	} else if(retcode==6){
		msg="终端执行指令失败";//终端执行指令失败
		tdHtml = "<span class='fa-remove color-error'></span>"+msg;
	} else if(retcode==16){
		msg="无此指令";//无此指令
		tdHtml = "<span class='fa-remove color-error'></span>"+msg;
	} else{
		msg=retmsg;//失败信息
		tdHtml = "<span class='fa-remove color-error'></span>"+msg;
	}
	/*var trNodes = $("#cmd_table tbody tr");
	var trNode = "";
	$.each(trNodes,function(i,item){
		// console.log($(item).children("td").eq(6).html());
		trNode = $(item);
		if(trNode.children("td").eq(6).html()==sn && trNode.children("td").eq(1).html()==callLetter){//对比sn和呼号，同则改
			trNode.children("td").eq(3).html(tdHtml);
			trNode.children("td").eq(5).html(cmdtime);
		}
	});*/
//	$('#instructionsInfoGrid').find('.cmd-status[data-id="'+callLetter+','+sn+'"]').html(msg);
//	$('#instructionsInfoGrid').find('.return-time[data-id="'+callLetter+','+sn+'"]').html(cmdtime);
	
	var rowData = CamionView.instructionsInfoGrid.getDataById(callLetter+','+sn)
	if(!rowData) return;
	rowData.cmdStatus = msg;
	rowData.returnTime = cmdtime;
	CamionView.instructionsInfoGrid.reRender();
	
}

//读最后位置信息

function ongetlastgps(cli, retcode, retmsg, gpses, alarmid, alarmname) {
	log.value += "通信中心返回最后位置: callLetter="+gpses[0].callLetter +" retcode=" + retcode  + ", retmsg=" + retmsg + "\n";
	console.log(gpses);
	for(var i=0; i<gpses.length; i++) {
		ondelivergpsWhole(gpses[i], alarmid, alarmname);
	}

}

//显示读最后信息错误
function ongetlasterror(cli, retcode, retmsg) {
	log.value += "*****通信中心返回取最后信息失败:(retcode=" + retcode  + ", retmsg=" + retmsg + "*****\n";
};


//读历史位置信息
function ongethistorygps(cli, retcode, retmsg, lastpage, gpses) {
	var chaxunText=$('.chaxunBtn1');
	chaxunText.addClass('controllAct');
	var position=$('#position').val();
	var newgpses = [];//筛选后的gps信息条目，过滤经纬度为0的点
	for(var i=0; i<gpses.length; i++) {
		var pushFlag = false;
		var gps = gpses[i];
		var _loc = gps.baseInfo.loc;
		var _lon = parseFloat(gps.baseInfo.lng)/1000000;//经度
		var _lat = parseFloat(gps.baseInfo.lat)/1000000;//纬度
		if(position=="1"){//卫星定位
			pushFlag = true;
			if(_lon!==0&&_lat!==0 && _loc==true ){
				newgpses.push(gps);//经纬度不为0则有效，加入新数组
			}
		}else if(position=="0"){//全部含基站
			if(_lon!==0&&_lat!==0 ){
				newgpses.push(gps);//经纬度不为0则有效，加入新数组
				pushFlag = true;
			}
		}else{//全部不含基站
		     pushFlag = true;
			if(_lon!==0&&_lat!==0 && _loc==true ){
				newgpses.push(gps);//经纬度不为0则有效，加入新数组
			}if(_lon!==0&&_lat!==0 && _loc==false ){
				gps.baseInfo.address = [];
				gps.baseInfo.baseStations= [];
				newgpses.push(gps);
			}
			
		}
		if(!pushFlag){
			var BaseStationAddress = gps.baseInfo.address;
			if(BaseStationAddress!=null&&BaseStationAddress.lng!=null&&BaseStationAddress.lat!=null&&BaseStationAddress.lng!=0&&BaseStationAddress.lat!=0){
				newgpses.push(gps);
			}
		}
	}
	loadHistoryData(newgpses);//该函数在js/cmd_v2.js
	var total = parseInt($(".chaxunNum1").html());//之前有多少条
	total += newgpses.length;//加上这一页的条数
	console.log("total:"+total);
	if(!lastpage){//如果不是最后一页
		$(".chaxunNum1").html(total);
		GetHistoryNextPage();//取下一页
	}else{//如果是最后一页
//		baiduMap.clearOverlays();
		$('#cmd_history_play').removeClass('controllAct');
		$("#exportHistoryInfo").hasClass("exporth")?$("#exportHistoryInfo").addClass("controllAct"):$("#exportHistoryInfo").removeClass("controllAct");
		chaxunText.val('查询');
        chaxunText.addClass('btn-primary');
        chaxunText.removeClass('controllAct');
        $(".chaxunNum1").html(total);
	}
}

//读历史位置主要信息
function ongethistorysimplegps(cli, callLetter, retcode, retmsg, lastpage, gpses) {
	log.value += "通信中心返回历史轨迹主要信息:(" + gpses.length + ") callletter=" + callLetter + ", retcode=" + retcode  + ", retmsg=" + retmsg + ", lastpage=" + lastpage + "\n";
	for(var i=0; i<gpses.length; i++) {
		var gpsTime = new Date(gpses[i].gpsTime.toNumber());
		log.value += gpsTime.toString() + ", " + gpses[i].lng + ", " + gpses[i].lat + "\n";
	};
	// alert(log.value);
}
					
//显示读历史信息错误
function ongethistoryerror(cli, retcode, retmsg) {
	log.value += "*****通信中心返回读历史信息失败:(retcode=" + retcode  + ", retmsg=" + retmsg + "*****\n";
	// alert(log.value);
	var chaxunText=$('.chaxunBtn1');
	chaxunText.val('查询');
    chaxunText.addClass('btn-primary')
    $(".chaxunNum1").html(0);
};

//结束读历史信息
function onstophistory(cli, retcode, retmsg){
	log.value += "通信中心返回结束读历史信息: retcode=" + retcode  + ", retmsg=" + retmsg + "\n";
	// alert(log.value);
};

//车辆上传实时位置信息
function ondelivergps(cli, gpsinfo, gatewayid, gatewaytype, alarmid, alarmname) {
	// console.log(gpsinfo);
	var gpsTime = new Date(gpsinfo.baseInfo.gpsTime.toNumber());
	log.value +="车辆上传实时位置信息: " + gpsinfo.callLetter + ", " + SEGUtil_WS.date_2_string(gpsTime, "yyyy-MM-dd hh:mm:ss") + ", " + gpsinfo.baseInfo.lng + ", " + gpsinfo.baseInfo.lat + ", " + gpsinfo.baseInfo.speed +"\n";
	
	ondelivergpsWhole(gpsinfo, alarmid, alarmname);
}

//车辆上传GPS信息
var ps = [];  //画线
var last_polyline = null; //画线
var show = 1;

function ondelivergpsWhole(gpsinfo, alarmid, alarmname) {
	var isloc;//是否定位
	var bsaeLon;//基站纬度
	var baseLat;//基站经度
	if (gpsinfo.baseInfo.loc) {
		isloc="卫星定位";
	} else {
		var BaseStationAddress = gpsinfo.baseInfo.address;
		if(BaseStationAddress==null||BaseStationAddress.lng==null||BaseStationAddress.lat==null||BaseStationAddress.lng==0||BaseStationAddress.lat==0){
			if(gpsinfo.baseInfo.baseStations.length>0){
				$.ajax({
	    			url:'../lbs/getMes',
	    			method:"POST",
	    			async: false,
	    			dataType:"json",
	    			data:{'baseGps':JSON.stringify(gpsinfo.baseInfo.baseStations)},
	    			success: function(data){
	    				console.log(data.datas);
	    				if(data.datas==null){
	    					isloc="正在定位";
	    				}else{
		    				bsaeLon=data.datas.lon*1000000;
		    				baseLat=data.datas.lat*1000000;
		    				if(bsaeLon==0 || bsaeLon==null||baseLat==0 || baseLat==null){
		    					isloc="正在定位";
		    				}else{
		    					isloc="基站定位";
		    				}
	    				}
	    			},
	    			error: function(XMLHttpRequest, textStatus, errorThrown) {
	    				console.log(XMLHttpRequest.status);
	    			}
				});
			}else{
				isloc="正在定位";
			}
		}else{
			bsaeLon = BaseStationAddress.lng;
			baseLat = BaseStationAddress.lat;
			isloc="基站定位";
		}

	};
	//在地图中显示并居中
	if(!gpsinfo.baseInfo) return; //如果缺少gps信息则忽略
	/**
	 * 
	 */
	var callLetter 	= gpsinfo.callLetter;
	var numberPlateAndId = LetterPlate($.trim(callLetter))[0];
	var driver_name = LetterPlate($.trim(callLetter))[1];
	var imei = LetterPlate($.trim(callLetter))[2];
	var oilTankCapacity = LetterPlate($.trim(callLetter))[3];
	var unitType = GetUnitTypeId(callLetter);
	var numberPlate ="";
	var vehicleId = "";
	if(numberPlateAndId.indexOf(",")!=-1){
		var data= numberPlateAndId.split(",");
		numberPlate = data[0];
		vehicleId = data[1];
	}else{
		numberPlate = numberPlateAndId;
	}
	
	//定义变量
	var _lon,_lat,speed,gpsTimeF,course,alarmidE,electricity=0,accTimeLen,isAlarm, status,
	    totalDistance,isloc;
	var stamp = SEGUtil_WS.date_2_string(new Date, "yyyy-MM-dd hh:mm:ss");
	
	speed = typeof(gpsinfo.baseInfo.speed)=='undefined' ? '' : gpsinfo.baseInfo.speed;
	var gpsTime = new Date(parseInt(gpsinfo.baseInfo.gpsTime));
	gpsTimeF 	= DateTranslate(gpsTime);
	if(isloc=="基站定位"){
		var lon 		= bsaeLon;
		var lat 		= baseLat;
	}else{
		 lon 		= gpsinfo.baseInfo.lng;
		 lat 		= gpsinfo.baseInfo.lat;
	}
	 course 		= gpsinfo.baseInfo.course;	
	var cn_course   = SEGUtil.getCourseDesc(course);
	if(gpsinfo.baseInfo.beijingInfo!=null){
		if(gpsinfo.baseInfo.beijingInfo.electricity!=null){
			electricity = gpsinfo.baseInfo.beijingInfo.electricity;
		}
	}
	//2016-08-16 刘杰 新版本终端状态位中存储了acc时长
    accTimeLen=gpsinfo.baseInfo.AccTimeLen;
	if (alarmid==0||alarmid==null) {alarmidE=0;}else{alarmidE=1;};
    isAlarm 	= alarmidE;
	status 		= gpsinfo.baseInfo.status;
	var cn_status   = SEGUtil.parseVehicleStatus(status);
	//发送查车指令的时候，返回里程值可能为空
	if(gpsinfo.baseInfo.totalDistance==null){
		totalDistance='';
	}else{
		totalDistance = (gpsinfo.baseInfo.totalDistance/1000).toFixed(2);
	}

	 _lon = parseFloat(lon)/1000000;
	 _lat = parseFloat(lat)/1000000;
	if(_lon==0 || _lat==0)
		return;
	//剩余油量L 、电瓶电压V、实时发动机转速(转/分)、实时发动机温度 ℃      liujie 2017-07-27
	var oil="";
	var batteryVoltage = "";
	var rotationSpeed = "";
	var waterTemperature = "";
	//var remainPercentOil ="";
	var obdObj = gpsinfo.baseInfo.obdInfo
	if(obdObj != null){
		if(obdObj.remainOil != null && obdObj.remainOil!= undefined ){
			oil=(obdObj.remainOil/100).toFixed(2);
		}
		/*2018-03-01通过 electricity 字段显示比亚迪e5电量
		 * if(obdObj.remainPercentOil != null && obdObj.remainPercentOil!= undefined ){
			remainPercentOil=(obdObj.remainPercentOil/10).toFixed(2);
		}*/
		if(obdObj.dashboardDistance != null && obdObj.dashboardDistance!= undefined && obdObj.dashboardDistance!= 0 ){
			totalDistance=(obdObj.dashboardDistance/1000).toFixed(2);
		}else if(obdObj.totalDistance!=null && obdObj.totalDistance != undefined && obdObj.totalDistance !=0){
			totalDistance=(obdObj.totalDistance/1000).toFixed(2);
		}
		if(obdObj.batteryVoltage != null && obdObj.batteryVoltage!= undefined ){
			batteryVoltage=(obdObj.batteryVoltage/100).toFixed(2);
		}
		if(obdObj.rotationSpeed != null && obdObj.rotationSpeed!= undefined ){
			rotationSpeed=obdObj.rotationSpeed
		}
		if(obdObj.waterTemperature != null && obdObj.waterTemperature!= undefined ){
			waterTemperature=obdObj.waterTemperature
		}
		if(obdObj.speed != null && obdObj.speed!= undefined ){
			speed = obdObj.speed;
		}
	}
	
    for(var i=0;i<status.length;i++)
    {
      if(status[i]==23) 
      {
          speed = 0;
          break;  
      };    
    };
  
	var opts = {
		id: numberPlate,
		callLetter: callLetter,
		label: numberPlate + " " + gpsTimeF,
		numberPlate: numberPlate,
		lon: _lon,
		lat: _lat,
		isLoc: isloc,
		speed: parseFloat(speed)/10.0,
		course: parseInt(course),
		gpsTime: gpsTimeF,
		stamp: stamp,
		isAlarm: parseInt(isAlarm),
		status: status,
		totalDistance:totalDistance,
		electricity:electricity,
		course1:course,
		status1:status,
		accTimeLen:accTimeLen,
		oil:oil,
		//remainPercentOil:remainPercentOil,
		batteryVoltage:batteryVoltage,
		rotationSpeed:rotationSpeed,
	 	waterTemperature:waterTemperature,
	 	unitType:unitType,
		driver_name:driver_name,
		imei:imei,
		oilTankCapacity:oilTankCapacity
	};
	 
	if(vehicleId!=""){
		opts.vehicleId = vehicleId;  
	}
	var m = map.addOrUpdateVehicleMarkerById(opts);
		if(CamionView){
			if(window.nowGridId != 'gpsInfoGrid'){
				converToGridRow(opts,function(rowData){
					if(rowData){
						CamionView.gpsInfoGrid.addRow(rowData,CamionView.gpsInfoGrid.rows[0],true);
						var optTime = Date.parse(new Date(opts.gpsTime));
						var nowTime = Date.parse(new Date()); 
						if(parseInt(Math.abs(nowTime  -  optTime) / 1000 / 60 / 60 /24)<30){
						}
					}
					
				});
				
			}
		}
			
		if (!map.isPointInView(opts.lon,opts.lat)) {
			map.centerAndZoom(opts.lon, opts.lat, 16);
		}
	};
//先拿到经纬度获取到位置，再注入表格中	
function converToGridRow(gpsInfo,callback){
	var address ;
	var addressArray =[];
	var lon = gpsInfo.lon;
	var lat = gpsInfo.lat;
	//通过百度api获取参考位置
	var ggPoint = new BMap.Point(lon,lat);
    var pointArr = [];
    pointArr.push(ggPoint);
    new BMap.Convertor().translate(pointArr, 1, 5, function(data){
    	//if(data.status == 0){
    		new BMap.Geocoder().getLocation(new BMap.Point(data.points[0].lng,data.points[0].lat), function(rs){
    			var lonlat=data.points[0].lat+','+data.points[0].lng;
 	      		var lonlaturl="http://api.map.baidu.com/geocoder/v2/?ak=oRTEhIarHzXK52qPGuFKQGAPqKIrnmdn&callback=renderReverse&location="+lonlat+"&output=json&pois=5";
 	 	    	$.ajax({
 	 				type:'get',
 	 				url:lonlaturl,
 	 				success:function(data){
 	 					address = data.result.formatted_address+','+data.result.sematic_description;
 	 					var   datas=data.result.addressComponent;
 	 					var   ADDRESS=datas.province+datas.city+datas.district;
 	 					for(var i=0;i<data.result.pois.length && i<=3;i++){
 	 						var address1 = ADDRESS+data.result.pois[i].addr+','+data.result.pois[i].direction+','+data.result.pois[i].distance+'米';
 	 						addressArray.push(address1);
 	 					}
		  	   		    if(address == ''){
		  	   			   address = '参考位置为空';
		  	   		    } 	   		    
		  	   		    callback(getRow(gpsInfo,address,callback,addressArray));
 	 				},
 	 				error:function(error){
 	 					console.log('请求参考位置出错!');
 	 				},
 	 				dataType:'jsonp'
 	 			});
          	});
    });
}	
//将参考位置注入表格
function getRow(gpsInfo,address,callback,addressArray){
	var row={};
	row.numberPlate = gpsInfo.numberPlate;
	row.callLetter = gpsInfo.callLetter;
	row.isLoc = gpsInfo.isLoc;
	row.status = "";
	//状态特殊处理，只显示点火、熄火状态
	var statusArr = new Array();
	for ( var status in gpsInfo.status) {
		if(gpsInfo.status[status] ==23){
			statusArr.push(23);
			break;
		}else if(gpsInfo.status[status] ==33){
			statusArr.push(33);
			break;
		}
	}
	
	row.status = SEGUtil.parseVehicleStatus(statusArr);
	
	for(var status in gpsInfo.status){
		if(gpsInfo.status[status] ==3){
			row.status += "、欠压";
			break;
		}else if(gpsInfo.status[status] ==4){
			row.status += "、主电被切断";
			break;
		}else if(gpsInfo.status[status] ==11){
			row.status += "、拆机";
			break;
		}
	};    
	//2016-08-16 刘杰 新版本终端状态位中存储了acc时长
	row.accTimeLen=gpsInfo.accTimeLen;
//	console.log('gpsInfo.accTimeLen:'+gpsInfo.accTimeLen);
	var startTime = Date.now();
	row.id = gpsInfo.callLetter +','+ startTime;
	row.startTime = startTime;
	row.referencePosition = address;
	for(var i=0;i<addressArray.length;i++){
		if(i==0){
			row.referencePosition1 = addressArray[i];
		}
		if(i==1){
			row.referencePosition2 = addressArray[i];
		}
		if(i==2){
			row.referencePosition3 = addressArray[i];
		}
		if(i==3){
			row.referencePosition4 = addressArray[i];
		}
		
	}
	row.lon = gpsInfo.lon;
	row.lat = gpsInfo.lat;
	row.speed = gpsInfo.speed;
	row.gpsTime = gpsInfo.gpsTime;
	row.course = SEGUtil.getCourseDesc(gpsInfo.course);
	row.startRequest = false;
	//trg80 电量显示为100  2017-08-04
	if(gpsInfo.unitType==true){
		row.electricity=100;
	}else{
		row.electricity=gpsInfo.electricity;
	}
	row.totalDistance=gpsInfo.totalDistance;
	row.lon1=gpsInfo.lon;
	row.lat1=gpsInfo.lat;
	row.status1=gpsInfo.status1;
	row.oil=gpsInfo.oil;//油量
	//row.remainPercentOil=gpsInfo.remainPercentOil;//电量%
	row.batteryVoltage=gpsInfo.batteryVoltage;
	row.rotationSpeed=gpsInfo.rotationSpeed;
	row.waterTemperatur=gpsInfo.waterTemperature;
	//2018-01-09 OBD增加油量百分比显示
	row.oilTankCapacity=gpsInfo.oilTankCapacity;
	$("#infocarnum").html(row.numberPlate);//车牌号码
	$("#sslc").html(row.totalDistance);//实时里程数
	if($("#balanceCar").html() == "剩余油量(L、%):"){
		if(row.oil==""){
			$("#cyyl").html("");//剩余油量
		}else{
			$("#cyyl").html(row.oil+"、"+(row.oil/row.oilTankCapacity*100).toFixed(2)+"%");//剩余油量
		}
	}else{
		if(row.electricity==""){
			$("#cyyl").html("");//剩余电量
		}else{
			$("#cyyl").html(row.electricity+"%");//剩余电量
		}
	}	
	
	$("#dbdy").html(row.batteryVoltage);//电瓶电压
	$("#ssfdjzs").html(row.rotationSpeed);//实时发动机转速
	$("#ssfdjwd").html(row.waterTemperatur);//实时发动机温度
	$("#sssd").html(row.speed);//实时速度
	var maxwidth = parseInt($("#infocarnum").width());
	if(maxwidth>304){
		$("#obd-car-info").parent().parent().css("width","550px");
	}
		
	callback(row);
}
//**********************************************************************************
/*function converToGridRow(gpsInfo,callback){
	var row={};
	row.numberPlate = gpsInfo.numberPlate;
	row.callLetter = gpsInfo.callLetter;
	row.isLoc = gpsInfo.isLoc;
	row.status = "";
	//状态特殊处理，只显示点火、熄火状态
	var statusArr = new Array();
	for ( var status in gpsInfo.status) {
		if(gpsInfo.status[status] ==23){
			statusArr.push(23);
			break;
		}else if(gpsInfo.status[status] ==33){
			statusArr.push(33);
			break;
		}
	}
	row.status = SEGUtil.parseVehicleStatus(statusArr);
	//2016-08-16 刘杰 新版本终端状态位中存储了acc时长
	row.accTimeLen=gpsInfo.accTimeLen;
	console.log('gpsInfo.accTimeLen:'+gpsInfo.accTimeLen);
	var startTime = Date.now();
	row.id = gpsInfo.callLetter +','+ startTime;
	row.startTime = startTime;
	row.referencePosition = "查看参考位置";
	row.lon = gpsInfo.lon;
	row.lat = gpsInfo.lat;
	row.speed = gpsInfo.speed;
	row.gpsTime = gpsInfo.gpsTime;
	row.course = SEGUtil.getCourseDesc(gpsInfo.course);
	row.startRequest = false;
	row.electricity=gpsInfo.electricity;
	row.totalDistance=gpsInfo.totalDistance;
	row.lon1=gpsInfo.lon;
	row.lat1=gpsInfo.lat;
	row.status1=gpsInfo.status1;
	callback(row);
}*/
$('#gpsInfoGrid').on('click','.queryLocaltion',function(e){
	var button = $(this);
	var data = CamionView.gpsInfoGrid.getDataById(button.attr('data-id'));
	if(data.startRequest) return;
	getAddress(data.callLetter,
			data.lon,
			data.lat,
			'gpsInfoGrid',
			data.startTime);
	data.startRequest = true;
});

$('#onlineGrid').on('click','.queryLocaltion',function(e){
	var button = $(this);
	var data = CamionView.onlineGrid.getDataById(button.attr('data-id'));
	if(data.startRequest) return;	
	//GPSUtil.gpsToBaiduGps(data.lon,data.lat,1,5);
	getAddress(data.callLetter,
			 data.lon,
			 data.lat,
			'onlineGrid',
			data.startTime);
	data.startRequest = true;
});

$('#alarmGrid').on('click','.queryLocaltion',function(e){
	var button = $(this);
	var data = CamionView.alarmGrid.getDataById(button.attr('data-id'));
	$.ligerDialog.confirm('确定处理警情吗?', function(yes){
        if(yes === false) return;
    	$.ajax({
 				type:'post',
 				url:'../alarm/data/handlealarm',
 				data:{
 					alarmId: data.alarmId,
 	            },
 				success:function(getdata){
 					if(getdata.success){
 	                    $.ligerDialog.success('处理警情成功!');
 	                   CamionView.alarmGrid.remove(data);
 	                }else{
 	                    $.ligerDialog.error('处理警情失败!');
 	                }
 				},
 				error:function(error){
 					console.log('处理警情出错!');
 				},dataType:'json'
 			});
	})
});

$('#offlineGrid').on('click','.queryLocaltion',function(e){
	var button = $(this);
	var data = CamionView.offlineGrid.getDataById(button.attr('data-id'));
	if(data.startRequest) return;
	//GPSUtil.gpsToBaiduGps(data.lon,data.lat,1,5);
	getAddress(data.callLetter,
			data.lon,
			data.lat,
			//GPSUtil.baiduGps.lon,
			//GPSUtil.baiduGps.lat,
			'offlineGrid',
			data.startTime);
	data.startRequest = true;
});

function getReferencePosition(row){
	getAddress(row.callLetter,row.lon,row.lat);
	//GPSUtil.gpsToBaiduGps(row.lon,row.lat,1,5);
	//getAddress(row.callLetter,GPSUtil.baiduGps.lon,GPSUtil.baiduGps.lat);
}


//终端登退录事件
function ondeliverunitloginout(selfclient, callLetter, inorout, gatewayid){
	log.value += "车台（终端）登退录事件: " + callLetter;
	log.value += inorout == 0 ? "退录" : "登录";
	log.value += ", gatewayid=" + gatewayid + "\n";
};

