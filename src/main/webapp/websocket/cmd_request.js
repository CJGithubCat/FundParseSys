/*
 * 测试大平台通信中心协议
 * Copyright 2014 chinagps, Inc.
 */
//通信客户端
"use strict";
var client = null;
// var txtcallletters = document.getElementById("b_carPhone");
// var txtcalllettersAdd = document.getElementById("addMonitor_carPhone");
// var txtcalllettersMo = document.getElementById("Monitor_carPhone");
var txtseatname = document.getElementById("loginname");
var txtseatid = document.getElementById("loginid");
var txtseatpwd = document.getElementById("loginpwd");
var txtsn = document.getElementById("sn");
// var txtstarttime = document.getElementById("starttime");
// var txtstoptime = document.getElementById("stoptime");

var historytype = null;		//读历史记录时（下一页要用到上次是读那种信息）
var monitor_lenth = 10;
var SentList_lenth = 10;

function onbodyload(){
	txtstarttime.value = SEGUtil_WS.date_2_string(new Date, "yyyy-MM-dd");
	txtstoptime.value = SEGUtil_WS.date_2_string(new Date, "yyyy-MM-dd hh:mm:ss");
	txtsn.value = Math.uuid();
};

/********************************************************************************************
 * 登录通信中心
 */
var host = location.host;//通信中心ip:端口，多个通信中心服务器，用分号隔开
function Connect() {
	try {
		//多个通信中心服务器，用分号隔开
		//var host = location.host;
		if (client == null) {
			//segseatlib中同时可以有多个客户端
			/* 1: 每个客户端有不同ID, 客户端自定义，事件中可区分
			 * 
			 * 登录用户名(坐席操作员名称)、密码、坐席编号
			 */
			host = "202.105.139.92:8070"; //可多个，用;隔开，（暂时用座席测试地址）90.0.12.135:18070;
			// host = "90.0.12.203:8070";//座席专用
			// host = "90.0.12.203:8070";//APP链接
			client = new segseatlib.jsclient(1, host, txtseatname.value, txtseatpwd.value, txtseatid.value, "seatclient", "1.0");
			segseatlib.addClient(client);
		}
		//废黜通信过程中压缩报文，默认不压缩
		client.setCompress(true);
		//废黜通信过程中加密报文，默认不加密
		client.setEncrypt();
		//连接服务器, 连接成功后自动登录
		client.connect();
	} catch (err) {
		alert(err);
	}
};

/********************************************************************************************
 * 退出通信中心
 */
function LogoutCC() {
	try {
		if (client == null) {
			return;
		}
		//退出
		client.close();
	} catch (err) {
		alert(err);
	}
};

/********************************************************************************************
 * 添加和取消监控列表
 */

function AddMonitor(callletters) {
	try {
		if (client == null) {
			return;
		}
		//ALLUNIT表示全部终端，请参考《大平台通信中心通信协议》
		//var callletters = ["18922819616", "13912345678", "13922222222", "1393333333", "ALLUNIT"];
		var infotype = [-1];
		//var infotype = txtinfotypes.value.split(";");
		client.addMonitor(callletters, infotype);
	} catch (err) {
		alert(err);
	}
};

function RemoveMonitor(callletters) {
	try {
		if (client == null) {
			return;
		}
		//ALLUNIT表示全部终端，请参考《大平台通信中心通信协议》
		//var callletters = ["1393333333", "ALLUNIT"];
		// var callletters = txtcalllettersMo.value.split(";");
		var infotype = [-1];
		//var infotype = txtinfotypes.value.split(";");
		client.removeMonitor(callletters, infotype);
	} catch (err) {
		alert(err);
	}
};

//指令部分

//查车---------------------------------------------

function Position(callletters,cmdList){
	try {
		if (client == null) {
			return;
		}
		//var sn = "0x0001" + new Date().getTime();
		txtsn.value = "0x0001-" + Math.uuid(16)+new Date().getTime();
		// var callletters = txtcallletters.value.split(";");
		//命令字请参考大平台指令及应答参数说明书, 查车，默认添加到
		client.sendUnitCommand(txtsn.value, callletters, 0x0001, null, true);
		if (cmdList && cmdList.length>0) {
			beenSent(cmdList,txtsn.value);
		};
	} catch (err) {
		alert(err);
	}

};


//最后位置---------------------------------------------

function GetLastPosition(callletters) {
	try {
		if (client == null) {
			return;
		}
		//var callletters = ["18922819616", "13912345678", "13922222222", "1393333333", "13612345678", "13112345678", "13212345678", "13312345678", "13412345678"];
		// var callletters = txtcallletters.value.split(";");
		txtsn.value = Math.uuid();
		client.getLastInfo(segseatlib.message_type.DeliverGPS, callletters, txtsn.value);
		// beenSent(cmdList,txtsn.value);
	} catch (err) {
		alert(err);
	}
};

//发送到终端的短信---------------------------------------------------------
function UnitSMS(callletters,content,cmdList){//暂定为这个
	try {
		if (client == null) {
			return;
		}
		//var sn = "0x0035" + new Date().getTime();
		txtsn.value = "0x0035-" + Math.uuid(16)+new Date().getTime();
		// var callletters = txtcallletters.value.split(";");
		var params = [content,'0'];		//发送到终端的短信
		//命令字请参考大平台指令及应答参数说明书
		client.sendUnitCommand(txtsn.value, callletters, 0x0035, params);
		beenSent(cmdList,txtsn.value);
	} catch (err) {
		alert(err);
	}
};

//发送到手机的短信---------------------------------------------------------
// function SendSMS(callletters,content,cmdList) {
// 	try {
// 		if (client == null) {
// 			return;
// 		}
// 		//var sn = "0x4035" + new Date().getTime();
// 		txtsn.value = "0x4035-" + Math.uuid(16)+new Date().getTime();
// 		// var callletters = txtcallletters.value.split(";");
// 		//var callletters = ["18922819616", "13912345678", "13922222222", "1393333333"];
// 		var params = [content,'0'];		//发送到手机的短信
// 		client.sendUnitCommand(txtsn.value, callletters, 0x4035, params);
// 		// beenSent('发短信')
// 	} catch (err) {
// 		alert(err);
// 	}
// };

// 车台复位------------------------------------------------------

function Reback(callletters,cmdList){
	try {
		if (client == null) {
			return;
		}
		//var sn = "0x0004" + new Date().getTime();
		txtsn.value = "0x0002-" + Math.uuid(16)+new Date().getTime();
		// var callletters = txtcallletters.value.split(";");
		//命令字请参考大平台指令及应答参数说明书
		client.sendUnitCommand(txtsn.value, callletters, 0x0002);
		beenSent(cmdList,txtsn.value);
	} catch (err) {
		alert(err);
	}
};

//定时报告---------------------------------------------
function IntervalDeliver(callletters, cmdList, flag, t){
	try {
		if (client == null) {
			return;
		}		
		// var callletters = txtcallletters.value.split(";");
		var code = null;
		// var name = '';
		if(flag){
			var params = [t]; //t秒一次
			code = 0x0038;
			// name = '开启定时报告';
			txtsn.value = "0x0038-" + Math.uuid(16)+new Date().getTime();
			client.sendUnitCommand(txtsn.value, callletters, code, params);
		}else{
			code = 0x0039;
			// name = '停止定时报告';
			txtsn.value = "0x0039-" + Math.uuid(16)+new Date().getTime();
			// txtsn.value = code + "-" + Math.uuid(16)+new Date().getTime();
			client.sendUnitCommand(txtsn.value, callletters, code);
		}
		beenSent(cmdList,txtsn.value);
	} catch (err) {
		alert(err);
	}
};

//超速设置---------------------------------------------
function setSpeed(callletters, cmdList, flag, speed){
	try {
		if (client == null) {
			return;
		}		
		var code = null;
		if(flag){
			var params = [speed]; //
			code = 0x001D;
			txtsn.value = "0x001D-" + Math.uuid(16)+new Date().getTime();
			client.sendUnitCommand(txtsn.value, callletters, code, params);
		}else{
			code = 0x001E;
			txtsn.value = "0x001E-" + Math.uuid(16)+new Date().getTime();
			client.sendUnitCommand(txtsn.value, callletters, code);
		}
		beenSent(cmdList,txtsn.value);
	} catch (err) {
		alert(err);
	}
};


//设置通信参数---------------------------------------------
function GPRS_parameter(callletters, cmdList, S_APN, S_IP, S_NUM01,S_NUM02,S_type,S_mode,S_space){
	try {
		if (client == null) {
			return;
		}		
		// var callletters = txtcallletters.value.split(";");
		var params = [S_APN, S_IP,S_NUM01,S_NUM02,S_type,S_mode,S_space];
		var code = 0x0057;
		// var name = '设置通信参数';
		txtsn.value = "0x0057-" + "-" + Math.uuid(16)+new Date().getTime();
		client.sendUnitCommand(txtsn.value,callletters,code,params);
		beenSent(cmdList,txtsn.value);
	} catch (err) {
		alert(err);
	}
};


// 查询通信参数------------------------------------------------------

function GPRS_inquiry(){
	try {
		if (client == null) {
			return;
		}
		//var sn = "0x0004" + new Date().getTime();
		txtsn.value = "0x0058-" + Math.uuid(16)+new Date().getTime();
		var callletters = txtcallletters.value.split(";");
		//命令字请参考大平台指令及应答参数说明书
		client.sendUnitCommand(txtsn.value, callletters, 0x0058);
		beenSent('查询通信参数');
	} catch (err) {
		alert(err);
	}
};

//添加到已发送列表--------------------------------------------
function beenSent(cmdList,sn){//添加到“已发送指令列表”
	var cmdtime = SEGUtil_WS.date_2_string(new Date, "yyyy-MM-dd hh:mm:ss");      //指令发送时间
	var html="";
	$.each(cmdList,function(i,item){
		html = '<tr><td>'+item.pla+'</td><td>'+item.callLetter+'</td><td>'+item.cmdName+'<td><span class="fa-refresh color-warning"></span>等待回应</td><td>'+cmdtime+'</td><td class="returnTime"></td>'+'<td style="display:none;">'+sn+'</td></tr>'; 
		$("#cmd_table tbody").prepend(html);
	});
	$("#cmd_tab").tab("show");
}

/********************************************************************************************
 * 下面取历史轨迹
 */
function GetHistoryGPS(starttime, stoptime) {
	try {
		if (client == null) {
			return;
		}
		//var starttime = SEGUtil_WS.string_2_date(txtstarttime.value); 
		//var endtime = SEGUtil_WS.string_2_date(txtstoptime.value);
		var starttime = SEGUtil_WS.string_2_date(starttime); 
		var endtime = SEGUtil_WS.string_2_date(stoptime);
		// var callletters = txtcallletters.value.split(";");
		// if (callletters.length > 0) {
		historytype = segseatlib.message_type.DeliverGPS;
		txtsn.value = Math.uuid();
		client.getHistoryInfo($("#historyCallback_callLetter").val(), historytype, starttime, endtime, 300, 5000, false, txtsn.value);
		// }
	} catch (err) {
		alert(err);
	}
};

//读历史位置主要信息
function GetHistorySimpleGps(starttime, stoptime) {
	try {
		if (client == null) {
			return;
		}
		//var starttime = SEGUtil.string_2_date(txtstarttime.value); 
		//var endtime = SEGUtil.string_2_date(txtstoptime.value);
		var starttime = SEGUtil_WS.string_2_date(starttime); 
		var endtime = SEGUtil_WS.string_2_date(stoptime);
		var callletters = txtcallletters.value.split(";");
		if (callletters.length > 0) {
		  historytype = segseatlib.message_type.DeliverSimpleGPS;
		  client.getHistoryInfo(callletters[0], historytype, starttime, endtime, 300, 5000, false, txtsn.value);
		}
	} catch (err) {
		alert(err);
	}
};


function GetHistoryNextPage() {
	try {
		if (client == null) {
			return;
		}
		// var callletters = txtcallletters.value.split(";");
		// if (callletters.length > 0) {
			//txtsn的值不能变
    	client.getHistoryNextPage($("#historyCallback_callLetter").val(), historytype, txtsn.value);
		// }
	} catch (err) {
		alert(err);
	}
};

/**
*自定义
*/

// 通过车载号码查询车牌号码

function LetterPlate(CallLetter){
    var keyword = CallLetter;
    var GPS_Plate = "";
    if(keyword=='') return false;
    $.ajax({
        type : 'get',
        async : false,
        contentType : 'application/json',
        dataType : 'json',
        url : 'vehicle/data/vehicledetail',
        data :{type:2,param:keyword},
        success : function(result) {
            if(result && result.successs==true){
            	GPS_Plate = result.datas[0].plateNo;
				// $.each(result.datas, function(i,item){
				// 	GPS_Plate = item.plateNo;
				// });
				// alert(GPS_Plate);
            } else if(result.successs==false){
            	// alert(result.message);
            	GPS_Plate = result.message;
            }
        },
        error : function(res, error) {
            // alert(res.message);
            GPS_Plate = "请求错误";
        }
    })
    return GPS_Plate;
}

//日期格式转换
var DateFinal;
function DateTranslate(DateOrigin){
    var DateMonth;
    var DateDay;
    var DateHours;
    var DateMinutes;
    var DateSeconds;

    //月
    if ((DateOrigin.getMonth()+1)<10){
        DateMonth = '0'+(DateOrigin.getMonth()+1);
    }else{
        DateMonth =DateOrigin.getMonth()+1;
    };
    //日
    if (DateOrigin.getDate()<10){
        DateDay = '0'+DateOrigin.getDate();
    }else{
        DateDay =DateOrigin.getDate();
    };
    //时
    if (DateOrigin.getHours()<10){
        DateHours = '0'+DateOrigin.getHours();
    }else{
        DateHours =DateOrigin.getHours();
    };
    //分
    if (DateOrigin.getMinutes()<10){
        DateMinutes = '0'+DateOrigin.getMinutes();
    }else{
        DateMinutes = DateOrigin.getMinutes();
    };
    //秒
    if (DateOrigin.getSeconds()<10){
        DateSeconds = '0'+DateOrigin.getSeconds();
    }else{
        DateSeconds = DateOrigin.getSeconds();
    };
    DateFinal =DateOrigin.getFullYear()+ "-"+DateMonth+"-"+DateDay+" "+DateHours+":"+DateMinutes+":"+DateSeconds;
    return DateFinal;
}

/*获取用户信息*/
function search_custInfo(CallLetter){
	$("#custInfo_name,#custInfo_tel,#custInfo_address,#custInfo_subname").html("");
	var name;
	var tel;
	var address;
	var subname;
	$.ajax({
        type : 'get',
        async : false,
        contentType : 'application/json',
        dataType : 'json',
        url : 'vehicle/data/customerinfo',
        data :{type:1,param:CallLetter},
        success : function(result) {
            if(result && result.successs==true){
				name = result.datas[0].name;
				tel = result.datas[0].tel;
				address = result.datas[0].address;
				subname = result.datas[0].subname;
				$("#custInfo_name").html(name);
				$("#custInfo_tel").html(tel);
				$("#custInfo_address").html(address);
				$("#custInfo_subname").html(subname);
				$("#cmd_custInfo").modal("show");//弹窗
            } else if(result.successs==false){
            	// alert(result.message);
            	
            }
        },
        error : function(res, error) {
            // alert(res.message);
        }
    })
}

function getAddress(callLetter,lon,lat){//百度api获取参考位置
	console.log(2);///////
	var address = ""; 
	var point = new BMap.Point(lon,lat);
	var geoc = new BMap.Geocoder();   
	geoc.getLocation(point, function(rs){
		var addComp = rs.addressComponents;
		address = addComp.province + addComp.city + addComp.district + addComp.street + addComp.streetNumber;
		replaceAddress(callLetter,address);
	});
}

function replaceAddress(callLetter,address){//获取参考位置后替换
	var callLetter_array = $("#gps_table tbody tr");
    for(var i=0;i<callLetter_array.length;i++)
      {
        if(callLetter_array[i].getElementsByTagName("td")[1].innerHTML==callLetter) 
        {
            $(callLetter_array[i]).children("td").eq(6).html(address);
            break;  
        }
    }
}

function judgeGPSnum(){
	var gps_array = $("#gps_table tbody tr");
	if(gps_array.length>30){
		$(gps_array[30]).remove();
	}
}

function checkRepetition(callLetter){//删除相同车台的行
	var callLetter_array = $("#gps_table tbody tr");
    for(var i=0;i<callLetter_array.length;i++)
      {
        if(callLetter_array[i].getElementsByTagName("td")[1].innerHTML==callLetter) 
        {
            $(callLetter_array[i]).remove();
            break;  
        }
    }
}
