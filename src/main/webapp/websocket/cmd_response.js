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
	} else if(retcode==16){
		msg="无此指令";//无此指令
		tdHtml = "<span class='fa-remove color-error'></span>"+msg;
	} else{
		msg=retmsg;//失败信息
		tdHtml = "<span class='fa-remove color-error'></span>"+msg;
	}

	var trNodes = $("#cmd_table tbody tr");
	var trNode = "";
	$.each(trNodes,function(i,item){
		// console.log($(item).children("td").eq(6).html());
		trNode = $(item);
		if(trNode.children("td").eq(6).html()==sn && trNode.children("td").eq(1).html()==callLetter){//对比sn和呼号，同则改
			trNode.children("td").eq(3).html(tdHtml);
			trNode.children("td").eq(5).html(cmdtime);
		}
	});
	
}

//读最后位置信息

function ongetlastgps(cli, retcode, retmsg, gpses, alarmid, alarmname) {
	log.value += "通信中心返回最后位置: callLetter="+gpses[0].callLetter +" retcode=" + retcode  + ", retmsg=" + retmsg + "\n";
	console.log(gpses);
	for(var i=0; i<gpses.length; i++) {
		ondelivergpsWhole(gpses[i], alarmid, alarmname);
		// var gpsTime = new Date(gpses[i].baseInfo.gpsTime.toNumber());
		// log.value += SEGUtil_WS.date_2_string(gpsTime, "yyyy-MM-dd hh:mm:ss") + ", " + gpses[i].baseInfo.lng + ", " + gpses[i].baseInfo.lat + ", " + gpses[i].baseInfo.speed +"\n";
		
		// //在地图中显示并居中
		// if(!gpses[i].baseInfo) return; //如果缺少gps信息则忽略
		// var callLetter 	= gpses[i].callLetter;
		// var numberPlate = LetterPlate($.trim(callLetter));
		// var speed = typeof(gpses[i].baseInfo.speed)=='undefined' ? '' : gpses[i].baseInfo.speed;
		// //var speed 		= gpses[i].baseInfo.speed;
		// var gpsTime 	= new Date(parseInt(gpses[i].baseInfo.gpsTime));
		// var gpsTimeF 	= DateTranslate(gpsTime);
		// var lon 		= gpses[i].baseInfo.lng;
		// var lat 		= gpses[i].baseInfo.lat;
		// var course 		= gpses[i].baseInfo.course;	
		// var cn_course   = SEGUtil.getCourseDesc(course);
		// var stamp 		= SEGUtil_WS.date_2_string(new Date, "yyyy-MM-dd hh:mm:ss");
		// var alarmidE;
		// if (alarmid==0||alarmid==null) {alarmidE=0;}else{alarmidE=1;};
		// var isAlarm 	= alarmidE;
		// var status 		= gpses[i].baseInfo.status;	
		// var cn_status   = SEGUtil.parseVehicleStatus(status);
	 //    for(i=0;i<status.length;i++)
	 //      {
	 //        if(status[i]==23) 
	 //        {
	 //            speed = 0;
	 //            break;  
	 //        };    
	 //    };
		// var _lon = parseFloat(lon)/1000000;
		// var _lat = parseFloat(lat)/1000000;
		// var opts = {
		// 	id: numberPlate,
		// 	callLetter: callLetter,
		// 	label: numberPlate + " " + gpsTimeF,
		// 	numberPlate: numberPlate,
		// 	lon: _lon,
		// 	lat: _lat,
		// 	speed: parseFloat(speed),
		// 	course: parseInt(course),
		// 	gpsTime: gpsTimeF,
		// 	stamp: stamp,
		// 	isAlarm: parseInt(isAlarm),
		// 	status: status
		// };
		// // console.log(opts);

		// var m = map.addOrUpdateVehicleMarkerById(opts);
		
		// map.setCenter(_lon, _lat);

		// m.target.flicker();
		// Code_city();

	}

}

//显示读最后信息错误
function ongetlasterror(cli, retcode, retmsg) {
	log.value += "*****通信中心返回取最后信息失败:(retcode=" + retcode  + ", retmsg=" + retmsg + "*****\n";
};


//显示gps信息
// function displaygpsinfo(gpsinfo, showstatus) {
// 	var gpsTime = new Date(gpsinfo.baseInfo.gpsTime.toNumber());
// 	log.value += SEGUtil_WS.date_2_string(gpsTime, "yyyy-MM-dd hh:mm:ss") + ", " + gpsinfo.baseInfo.lng + ", " + gpsinfo.baseInfo.lat + ", " + gpsinfo.baseInfo.speed +"\n";
// 	for(var i=0; i<gpsinfo.baseInfo.status.length; i++) {
// 		if (showstatus) {
// 			log.value += getstatusname(gpsinfo.baseInfo.status[i]);
// 		} else {
// 			log.value += gpsinfo.baseInfo.status[i];
// 		}
// 		if (i < gpsinfo.baseInfo.status.length - 1) {
// 			log.value += ", ";
// 		} else {
// 			log.value += "\n";
// 		}
// 	}
// 	if (gpsinfo.baseInfo.obdInfo) {
// 		log.value += "总量程:" + gpsinfo.baseInfo.obdInfo.totalDistance + ",剩油:" + gpsinfo.baseInfo.obdInfo.remainOil + ",:" + gpsinfo.baseInfo.obdInfo.remainPercentOil;
// 		log.value +=  "%,水温:" + gpsinfo.baseInfo.obdInfo.waterTemperature + ",速度:" + gpsinfo.baseInfo.obdInfo.speed + ",续航:" + gpsinfo.baseInfo.obdInfo.remainDistance + "\n";
// 	}
// 	if (gpsinfo.referPosition != null) {
// 		log.value += gpsinfo.referPosition.province + ", " + gpsinfo.referPosition.city + ", " + gpsinfo.referPosition.county + "\n";
// 	};
// }

//读历史位置信息
function ongethistorygps(cli, retcode, retmsg, lastpage, gpses) {
	var newgpses = [];//筛选后的gps信息条目，过滤经纬度为0的点
	for(var i=0; i<gpses.length; i++) {
		var gps = gpses[i];
		var _lon = parseFloat(gps.baseInfo.lng)/1000000;//经度
		var _lat = parseFloat(gps.baseInfo.lat)/1000000;//纬度
		if(_lon!==0&&_lat!==0){
			newgpses.push(gpses[i]);//经纬度不为0则有效，加入新数组
		}
	}
	loadHistoryData(newgpses);//该函数在js/cmd.js
	// loadHistoryData(gpses); //该函数在js/cmd.js
	var total = parseInt($("#play_history_dlg_search_result").html().replace(/[^0-9]/ig,""));//之前有多少条
	total += newgpses.length;//加上这一页的条数
	// total += gpses.length;//加上这一页的条数
	if(!lastpage){//如果不是最后一页
		$("#play_history_dlg_search_result").html('共'+total+'条数据');
		GetHistoryNextPage();//取下一页
	}else{//如果是最后一页
		$("#play_history_dlg_play_btn").removeClass("disabled");
		$("#play_history_dlg_list_btn").removeClass("disabled");
		$("#play_history_dlg_search_result").html('共'+total+'条数据').css("color","#47a447").css("display","block");
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
	$("#play_history_dlg_search_result").html('共0条数据').css("display","block");
	$("#play_history_dlg_play_btn").addClass("disabled");
	$("#play_history_dlg_list_btn").addClass("disabled");
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
	// alert(gpsinfo.loc);
	var isloc;//是否定位
	if (gpsinfo.baseInfo.loc) {
		isloc="已定位";
	} else{
		isloc="否";
	};

	//在地图中显示并居中
	if(!gpsinfo.baseInfo) return; //如果缺少gps信息则忽略
	var callLetter 	= gpsinfo.callLetter;
	var numberPlate = LetterPlate($.trim(callLetter));
	var speed = typeof(gpsinfo.baseInfo.speed)=='undefined' ? '' : gpsinfo.baseInfo.speed;
	//var speed 		= gpsinfo.baseInfo.speed;
	var gpsTime = new Date(parseInt(gpsinfo.baseInfo.gpsTime));
	var gpsTimeF 	= DateTranslate(gpsTime);
	var lon 		= gpsinfo.baseInfo.lng;
	var lat 		= gpsinfo.baseInfo.lat;
	var course 		= gpsinfo.baseInfo.course;	
	var cn_course   = SEGUtil.getCourseDesc(course);
	var stamp 		= SEGUtil_WS.date_2_string(new Date, "yyyy-MM-dd hh:mm:ss");
	var alarmidE;
	if (alarmid==0||alarmid==null) {alarmidE=0;}else{alarmidE=1;};
	var isAlarm 	= alarmidE;
	var status 		= gpsinfo.baseInfo.status;	
	var cn_status   = SEGUtil.parseVehicleStatus(status);
    for(var i=0;i<status.length;i++)
      {
        if(status[i]==23) 
        {
            speed = 0;
            break;  
        };    
    };
	var _lon = parseFloat(lon)/1000000;
	var _lat = parseFloat(lat)/1000000;
	var opts = {
		id: numberPlate,
		callLetter: callLetter,
		label: numberPlate + " " + gpsTimeF,
		numberPlate: numberPlate,
		lon: _lon,
		lat: _lat,
		speed: parseFloat(speed),
		course: parseInt(course),
		gpsTime: gpsTimeF,
		stamp: stamp,
		isAlarm: parseInt(isAlarm),
		status: status
	};
	// console.log(opts);

	var m = map.addOrUpdateVehicleMarkerById(opts);
	
	map.setCenter(_lon, _lat);

	m.target.flicker();

	checkRepetition(callLetter);//删除相同车台的行

	var a = $('<a></a>').attr("href","javascript:void(0)").addClass("getAddress").html("[点击查看]").click(function(){
		var callLetter = $(this).parent().parent().children("td").eq(1).html();
		var lon = $(this).parent().parent().children("td").eq(9).html();
		var lat = $(this).parent().parent().children("td").eq(10).html();
		getAddress(callLetter,lon,lat);
		// $(this).parent().html(getAddress(lon,lat));//<a href='javascript:void(0)' class='getAddress'>[查看]</a>
	});
	var td0 = $('<td></td>').html(numberPlate);
	var td1 = $('<td></td>').html(callLetter);
	var td2 = $('<td></td>').html(parseFloat(speed));
	var td3 = $('<td></td>').html(cn_course);
	var td4 = $('<td></td>').html(cn_status);
	var td5 = $('<td></td>').html(isloc);
	var td6 = $('<td></td>').append(a);
	var td7 = $('<td></td>').html(_lon+","+_lat).addClass("hide");
	var td8 = $('<td></td>').html(gpsTimeF);
	var td9 = $('<td></td>').html(_lon).addClass("hide");
	var td10 = $('<td></td>').html(_lat).addClass("hide");
	var td11 = $('<td></td>').html(course).addClass("hide");
	var td12 = $('<td></td>').html(stamp).addClass("hide");
	var td13 = $('<td></td>').html(parseInt(isAlarm)).addClass("hide");
	var td14 = "<td class='hide'>"+status+"</td>";
	var tr = $('<tr></tr>').append(td0,td1,td2,td3,td4,td5,td6,td7,td8,td9,td10,td11,td12,td13,td14);
	$("#gps_table tbody").prepend(tr);
	judgeGPSnum();//超过30条删除
	// var tableHTML = "<tr><td>"+numberPlate+"</td><td>"+callLetter+"</td><td>"+isloc+"</td><td>"+cn_status+
	// "</td><td>"+a+"</td><td class='hide'>"+_lon+","+_lat+"</td><td>"+parseFloat(speed)+"</td><td>"+
	// cn_course+"</td><td>"+gpsTimeF+"</td><td class='hide'>"+_lon+"</td><td class='hide'>"+_lat+
	// "</td><td class='hide'>"+course+"</td><td class='hide'>"+stamp+"</td><td class='hide'>"+parseInt(isAlarm)+
	// "</td><td class='hide'>"+status+"</td></tr>";
	// $("#gps_table tbody").prepend(tableHTML);
	
};


//终端登退录事件
function ondeliverunitloginout(selfclient, callLetter, inorout, gatewayid){
	log.value += "车台（终端）登退录事件: " + callLetter;
	log.value += inorout == 0 ? "退录" : "登录";
	log.value += ", gatewayid=" + gatewayid + "\n";
};

