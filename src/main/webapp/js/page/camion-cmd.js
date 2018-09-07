var CamionCMD={};
var trg90_min_interval = 8*60*60;
var trg90_unit_type='TRG90';
$(function(){
	//开启定时报告
   /*
	$("#cmd_IntervalDeliver_start").click(function () {
        var cars = $("#timingReportCarlist li");
        var callletters = [];
        var cmd_list = [];//资料
        var flag = 1;//1为true 0为false
        var hour, min, sec, t;//t为秒
        var trg90_datas = [];//trg90导航专发

        if ($("#timingReport_hour").val() == "") {
            hour = 0;
        } else {
            hour = parseInt($("#timingReport_hour").val());
        }

        if ($("#timingReport_min").val() == "") {
            min = 0;
        } else {
            min = parseInt($("#timingReport_min").val());
        }

        if ($("#timingReport_sec").val() == "") {
            sec = 0;
        } else {
            sec = parseInt($("#timingReport_sec").val());
        }
        t = hour * 60 * 60 + min * 60 + sec;
        var isHaveTrg90 =false;
        $.each(cars, function (i, item) {
        	if($(item).find(".unitType").html().indexOf(trg90_unit_type)!=-1){
        		isHaveTrg90=true;
        	}
            if ($(item).find(".preCmd").html() == 1) {//预存
                trg90_datas.push({
                    "unit_id": $(item).find(".unitID").html(),
                    "call_letter": $(item).find(".callLetter").html(),
                    "cmd_id": "0x0038",
                    "send_params": t
                });
            } else {
                callletters.push($(item).find(".callLetter").html());
                cmd_list.push({
                    "pla": $(item).find("p").html(),
                    "callLetter": $(item).find(".callLetter").html(),
                    "cmdName": "开启定时报告"
                });
            }
        });
        //如果有trg90终端，则进行判断
        if(isHaveTrg90){
        	if(t<trg90_min_interval){
        		alert("选择项中含TRG90终端，且时间设置小于8小时");
        		return;
        	}
        }

        IntervalDeliver(callletters, cmd_list, flag, t);
        sendCMD_TRG90(trg90_datas);//发送trg指令
        //弹窗消失
        closeDialog('timingReport');
    });
	*/
	//停止定时报告
    /*$("#cmd_IntervalDeliver_stop").click(function () {
        var cars = $("#timingReportCarlist li");
        var callletters = [];
        var cmd_list = [];//资料
        var flag = 0;//1：开启定时报告 0：停止定时报告
        var t;
        var trg90_datas = [];//trg90导航专发

        $.each(cars, function (i, item) {
            if ($(item).find(".preCmd").html() == 1) {//预存
                trg90_datas.push({
                    "unit_id": $(item).find(".unitID").html(),
                    "call_letter": $(item).find(".callLetter").html(),
                    "cmd_id": "0x0039"
                });
            } else {
                callletters.push($(item).find(".callLetter").html());
                cmd_list.push({
                    "pla": $(item).find(".plateNo").html(),
                    "callLetter": $(item).find(".callLetter").html(),
                    "cmdName": "停止定时报告"
                });
            }
        });

        IntervalDeliver(callletters, cmd_list, flag, t);
        sendCMD_TRG90(trg90_datas);//发送trg指令
        //弹窗消失
        
        closeDialog('timingReport');
    });*/
	$("#cmd_IntervalDeliver_start").click(function () {
        var cars = $("#timingReportCarlist li");
        var callletters = [];
        var cmd_list = [];//资料
        var flag = 1;//1为true 0为false
        var trg90_datas = [];//trg90导航专发
        
        var hourStr=$.trim($("#timingReport_hour").val());
        var minStr=$.trim($("#timingReport_min").val());
        var secStr=$.trim($("#timingReport_sec").val());
        if(hourStr==""&&minStr==""&&secStr==""){
        	$.ligerDialog.warn("请填写时长!");
        	return;        	
        }
        var regexNum=/^[0-9]*$/;
        if(!regexNum.test(hourStr)||!regexNum.test(minStr)||!regexNum.test(secStr)){
        	$.ligerDialog.warn("时间格式错误!");
        	return; 
        }
        var hour=hourStr==""?0:parseInt(hourStr);
        var min=minStr==""?0:parseInt(minStr);
        var sec=secStr==""?0:parseInt(secStr);
        var t=hour*60*60+min*60+sec;
        
        if(t==0){
        	$.ligerDialog.warn("请填写时长!");
        	return; 
        }        
        
        var TRG90CallLetterArr=[];
        var TRG90UnitIdArr=[];
        $.each(cars, function (i, item) {
        	if($(item).find(".unitType").html().indexOf(trg90_unit_type)!=-1){
        		var callLetter=$(item).find(".callLetter").html();
                var unitId=$(item).find(".unitID").html();
                TRG90CallLetterArr.push(callLetter);
                TRG90UnitIdArr.push(unitId);
        	} else {
                callletters.push($(item).find(".callLetter").html());
                cmd_list.push({
                    "pla": $(item).find("p").html(),
                    "callLetter": $(item).find(".callLetter").html(),
                    "cmdName": "开启定时报告"
                });
            }
        });    
        
//        if(TRG90CallLetterArr.length>0&&t<8*60*60){
//            $.ligerDialog.warn("选择的车辆中包含无源设备车辆,无源设备车辆定时报告时长不能小于8小时");
//            return;
//        }
       
        if(callletters.length>0){
           IntervalDeliver(callletters, cmd_list, flag, t);
	    }
        if(TRG90CallLetterArr.length>0){
        	var cmdId=56;
            var datas={
            		"callLetter":TRG90CallLetterArr,
            		"unitId":TRG90UnitIdArr,
            		"sendParams":t,
            		"cmdId":cmdId
             };
            savePreCmd(datas);
        }
        //弹窗消失
        closeDialog('timingReport');
    });
	
	//停止定时报告
    $("#cmd_IntervalDeliver_stop").click(function () {
        var cars = $("#timingReportCarlist li");
        var callletters = [];
        var cmd_list = [];//资料
        var flag = 0;//1：开启定时报告 0：停止定时报告
        var t;
        var trg90_datas = [];//trg90导航专发
        
        var TRG90CallLetterArr=[];
        var TRG90UnitIdArr=[];
        $.each(cars, function (i, item) {
        	if($(item).find(".unitType").html().indexOf(trg90_unit_type)!=-1){
        		var callLetter=$(item).find(".callLetter").html();
                var unitId=$(item).find(".unitID").html();
                TRG90CallLetterArr.push(callLetter);
                TRG90UnitIdArr.push(unitId);
        	}else {
                callletters.push($(item).find(".callLetter").html());
                cmd_list.push({
                    "pla": $(item).find(".plateNo").html(),
                    "callLetter": $(item).find(".callLetter").html(),
                    "cmdName": "停止定时报告"
                });
            }
        });        
        if(callletters.length>0){
            IntervalDeliver(callletters, cmd_list, flag, t);
 	    }
        if(TRG90CallLetterArr.length>0){
         	var cmdId=57;
            var datas={
             		"callLetter":TRG90CallLetterArr,
             		"unitId":TRG90UnitIdArr,
             		"sendParams":null,
             		"cmdId":cmdId,
             };
             savePreCmd(datas);
         }
         //弹窗消失
         closeDialog('timingReport');
    });
	
	//开启子机定时报告
	$("#cmd_timingReportForSonUnit_start").click(function () {
		var cars = $("#timingReportCallLetterlist li");
        var hourStr=$.trim($("#timingReportForSonUnit_hour").val());
        var minStr=$.trim($("#timingReportForSonUnit_min").val());
        var secStr=$.trim($("#timingReportForSonUnit_sec").val());
        if(hourStr==""&&minStr==""&&secStr==""){
        	$.ligerDialog.warn("请填写时长!");
        	return;        	
        }
        var regexNum=/^[0-9]*$/;
        if(!regexNum.test(hourStr)||!regexNum.test(minStr)||!regexNum.test(secStr)){
        	$.ligerDialog.warn("时间格式错误!");
        	return; 
        }
        var hour=hourStr==""?0:parseInt(hourStr);
        var min=minStr==""?0:parseInt(minStr);
        var sec=secStr==""?0:parseInt(secStr);
        var sendParams=hour*60*60+min*60+sec;
        
        if(sendParams==0){
        	$.ligerDialog.warn("请填写时长!");
        	return; 
        }
//        if(sendParams<8*60*60){
//        	$.ligerDialog.warn("子机定时报告时长不能小于8小时");
//        	return;
//        }
        
        var callLetterArr=[];
        var unitIdArr=[];
        $.each(cars, function (i, item) {
           var callLetter=$(item).find(".callLetter").html();
           var unitId=$(item).find(".unitID").html();
           callLetterArr.push(callLetter);
           unitIdArr.push(unitId);
        });
        
        var cmdId=56;
        var datas={"callLetter":callLetterArr,'unitId':unitIdArr,"sendParams":sendParams,"cmdId":cmdId};
        savePreCmd(datas);
        closeDialog('timingReportForSonUnit');
	});
	
	//停止子机定时报告
	$("#cmd_timingReportForSonUnit_stop").click(function () {
        var cars = $("#timingReportCallLetterlist li");
        var callLetterArr=[];
        var unitIdArr=[];
        $.each(cars, function (i, item) {
           console.log(item);
           var callLetter=$(item).find(".callLetter").html();
           var unitId=$(item).find(".unitID").html();
           callLetterArr.push(callLetter);
           unitIdArr.push(unitId);
        });
        console.log(callLetterArr,unitIdArr);
        var cmdId=57;
        var datas={"callLetter":callLetterArr,'unitId':unitIdArr,"cmdId":cmdId};
        savePreCmd(datas);
        closeDialog('timingReportForSonUnit');
    });	
   
   /*	
	$("#cmd_timingReportForSonUnit_start").click(function () {
		console.log(1);
        var cars = $("#timingReportCallLetterlist li");
        var callletters = [];
        var cmd_list = [];//资料
        var flag = 1;//1为true 0为false
        var hour, min, sec, t;//t为秒
        var trg90_datas = [];//trg90导航专发

        if ($("#timingReportForSonUnit_hour1").val() == "") {
            hour = 0;
        } else {
            hour = parseInt($("#timingReportForSonUnit_hour1").val());
        }

        if ($("#timingReportForSonUnit_min1").val() == "") {
            min = 0;
        } else {
            min = parseInt($("#timingReportForSonUnit_min1").val());
        }

        if ($("#timingReportForSonUnit_sec1").val() == "") {
            sec = 0;
        } else {
            sec = parseInt($("#timingReportForSonUnit_sec1").val());
        }
        t = hour * 60 * 60 + min * 60 + sec;
        var isHaveTrg90 =false;
        $.each(cars, function (i, item) {
        	if($(item).find(".unitType").html().indexOf(trg90_unit_type)!=-1){
        		isHaveTrg90=true;
        	}
        	
            if ($(item).find(".preCmd").html() == 1) {//预存
                trg90_datas.push({
                    "unit_id": $(item).find(".unitID").html(),
                    "call_letter": $(item).find(".callLetter").html(),
                    "cmd_id": "0x0038",
                    "send_params": t
                });
            } else {
                callletters.push($(item).find(".callLetter").html());
                cmd_list.push({
                    "pla": $(item).find(".plateNo").html(),
                    "callLetter": $(item).find(".callLetter").html(),
                    "cmdName": "开启定时报告"
                });
            }
        });
        
      //如果有trg90终端，则进行判断
        if(isHaveTrg90){
        	if(t<trg90_min_interval){
        		alert("子机为TRG90终端，时间设置需大于等于8小时");
        		return;
        	}
        }

        IntervalDeliver(callletters, cmd_list, flag, t);
        sendCMD_TRG90(trg90_datas);//发送trg指令
        //弹窗消失
        closeDialog('timingReportForSonUnit');
    });	
	*/
	
	//停止子机定时报告
    /*$("#cmd_timingReportForSonUnit_stop").click(function () {
        var cars = $("#timingReportCallLetterlist li");
        var callletters = [];
        var cmd_list = [];//资料
        var flag = 0;//1：开启定时报告 0：停止定时报告
        var t;
        var trg90_datas = [];//trg90导航专发

        $.each(cars, function (i, item) {
            if ($(item).find(".preCmd").html() == 1) {//预存
                trg90_datas.push({
                    "unit_id": $(item).find(".unitID").html(),
                    "call_letter": $(item).find(".callLetter").html(),
                    "cmd_id": "0x0039"
                });
            } else {
                callletters.push($(item).find(".callLetter").html());
                cmd_list.push({
                    "pla": $(item).find("p").html(),
                    "callLetter": $(item).find(".callLetter").html(),
                    "cmdName": "停止定时报告"
                });
            }
        });

        IntervalDeliver(callletters, cmd_list, flag, t);
        sendCMD_TRG90(trg90_datas);//发送trg指令
        //弹窗消失
        closeDialog('timingReportForSonUnit');
    });*/
	
	//开启蓝牙定时报告
	$("#cmd_bluetoothReport_start").click(function () {
        var cars = $("#bluetoothReportCarlist li");
        var callletters = [];
        var cmd_list = [];//资料
        var flag = 1;//1为true 0为false
        var hour, min, sec, t;//t为秒
        var trg90_datas = [];//trg90导航专发

        if ($("#bluetoothReport_hour").val() == "") {
            hour = 0;
        } else {
            hour = parseInt($("#bluetoothReport_hour").val());
        }

        if ($("#bluetoothReport_min").val() == "") {
            min = 0;
        } else {
            min = parseInt($("#bluetoothReport_min").val());
        }

        if ($("#bluetoothReport_sec").val() == "") {
            sec = 0;
        } else {
            sec = parseInt($("#bluetoothReport_sec").val());
        }
        t = hour * 60 * 60 + min * 60 + sec;
        var isHaveTrg90 =false;
        $.each(cars, function (i, item) {
        	if($(item).find(".unitType").html().indexOf(trg90_unit_type)!=-1){
        		isHaveTrg90=true;
        	}
        	
            if ($(item).find(".preCmd").html() == 1) {//预存
                trg90_datas.push({
                    "unit_id": $(item).find(".unitID").html(),
                    "call_letter": $(item).find(".callLetter").html(),
                    "cmd_id": "0x0038",
                    "send_params": t
                });
            } else {
                callletters.push($(item).find(".callLetter").html());
                cmd_list.push({
                    "pla": $(item).find(".plateNo").html(),
                    "callLetter": $(item).find(".callLetter").html(),
                    "cmdName": "开启蓝牙报告"
                });
            }
        });
        
      //如果有trg90终端，则进行判断
//        if(isHaveTrg90){
//        	if(t<trg90_min_interval){
//        		alert("子机为无源终端,时间设置需大于等于8小时!");
//        		return;
//        	}
//        }

        IntervalblueTooth(callletters, cmd_list,t);
        sendCMD_TRG90(trg90_datas);//发送trg指令
        //弹窗消失
        closeDialog('bluetoothReport');
    });	
    
    
    
    //开启熄火定时报告
	$("#cmd_misfire_start").click(function () {
        var cars = $("#misfireTimingReportCarlist li");
        var callletters = [];
        var cmd_list = [];//资料
        var flag = 1;//1为true 0为false
        var hour, min, sec, t;//t为秒
        var trg90_datas = [];//trg90导航专发

        if ($("#misfireTimingReport_hour").val() == "") {
            hour = 0;
        } else {
            hour = parseInt($("#misfireTimingReport_hour").val());
        }

        if ($("#misfireTimingReport_min").val() == "") {
            min = 0;
        } else {
            min = parseInt($("#misfireTimingReport_min").val());
        }

        if ($("#misfireTimingReport_sec").val() == "") {
            sec = 0;
        } else {
            sec = parseInt($("#misfireTimingReport_sec").val());
        }
        t = hour * 60 * 60 + min * 60 + sec;
        var isHaveTrg90=false;
        $.each(cars, function (i, item) {
        	if($(item).find(".unitType").html().indexOf(trg90_unit_type)!=-1){
        		isHaveTrg90=true;
        	}
            if ($(item).find(".preCmd").html() == 1) {//预存
                trg90_datas.push({
                    "unit_id": $(item).find(".unitID").html(),
                    "call_letter": $(item).find(".callLetter").html(),
                    "cmd_id": "0x0038",
                    "send_params": t
                });
            } else {
                callletters.push($(item).find(".callLetter").html());
                cmd_list.push({
                    "pla": $(item).find("p").html(),
                    "callLetter": $(item).find(".callLetter").html(),
                    "cmdName": "开启熄火定时报告"
                });
            }
        });
        
        //如果有trg90终端，则进行判断
        if(isHaveTrg90){
        	$.ligerDialog.warn("无源终端无熄火定时报告,请去掉该选项!");
    		return;
//        	if(t<trg90_min_interval){
//        		alert("选择项中包含无源终端,且时间设置小于8小时!");
//        		return;
//        	}
        }

        misfireIntervalDeliver(callletters, cmd_list, flag, t);
        sendCMD_TRG90(trg90_datas);//发送trg指令
        //弹窗消失
        closeDialog('misfireTimingReport');
    });

	//停止熄火定时报告
    $("#cmd_misfire_stop").click(function () {
        var cars = $("#misfireTimingReportCarlist li");
        var callletters = [];
        var cmd_list = [];//资料
        var flag = 0;//1：开启定时报告 0：停止定时报告
        var t;
        var trg90_datas = [];//trg90导航专发
        var isHaveTrg90=false;
        $.each(cars, function (i, item) {
        	if($(item).find(".unitType").html().indexOf(trg90_unit_type)!=-1){
        		isHaveTrg90=true;
        	}
            if ($(item).find(".preCmd").html() == 1) {//预存
                trg90_datas.push({
                    "unit_id": $(item).find(".unitID").html(),
                    "call_letter": $(item).find(".callLetter").html(),
                    "cmd_id": "0x0039"
                });
            } else {
                callletters.push($(item).find(".callLetter").html());
                cmd_list.push({
                    "pla": $(item).find("p").html(),
                    "callLetter": $(item).find(".callLetter").html(),
                    "cmdName": "停止熄火定时报告"
                });
            }
        });
        
        //如果有trg90终端，则进行判断
        if(isHaveTrg90){
        	alert("无源终端无停止熄火定时报告,请去掉该选项!");
    		return;
//        	if(t<trg90_min_interval){
//        		alert("选择项中包含无源终端,且时间设置小于8小时!");
//        		return;
//        	}
        }

        misfireIntervalDeliver(callletters, cmd_list, flag, t);
        sendCMD_TRG90(trg90_datas);//发送trg指令
        //弹窗消失
        closeDialog('misfireTimingReport');
    });
    
    
    //超速设置
    $("#cmd_speedSet_start").click(function () {
        var cars = $("#speedSetCarlist li");
        var callletters = [];
        var cmd_list = [];//资料
        var flag = 1;//1为true 0为false
        var speed;//speed为车速
        var trg90_datas = [];//trg90导航专发

        if ($("#carSpeed").val() == "") {
        	speed = 0;
        } else {
        	speed = parseInt($("#carSpeed").val());
        }

		if (speed > 180) {
			alert("速度不能大于180km/h!");
			return; 
		}

        $.each(cars, function (i, item) {
            if ($(item).find(".preCmd").html() == 1) {//预存
                trg90_datas.push({
                    "unit_id": $(item).find(".unitID").html(),
                    "call_letter": $(item).find(".callLetter").html(),
                    "cmd_id": "0x001D",
                    "send_params": speed
                });
            } else {
                callletters.push($(item).find(".callLetter").html());
                cmd_list.push({
                    "pla": $(item).find("p").html(),
                    "callLetter": $(item).find(".callLetter").html(),
                    "cmdName": "开启超速设置"
                });
            }
        });

        setSpeed(callletters, cmd_list, flag, speed);
        sendCMD_TRG90(trg90_datas);//发送trg指令
        //弹窗消失
        closeDialog('speedSet');
    });
    
    //取消超速设置
    $("#cmd_speedSet_stop").click(function () {
        var cars = $("#speedSetCarlist li");
        var callletters = [];
        var cmd_list = [];//资料
        var flag = 0;//1为true 0为false
        var speed=0;//speed为车速
        var trg90_datas = [];//trg90导航专发

        $.each(cars, function (i, item) {
            if ($(item).find(".preCmd").html() == 1) {//预存
                trg90_datas.push({
                    "unit_id": $(item).find(".unitID").html(),
                    "call_letter": $(item).find(".callLetter").html(),
                    "cmd_id": "0x001E",
                    "send_params": speed
                });
            } else {
                callletters.push($(item).find(".callLetter").html());
                cmd_list.push({
                    "pla": $(item).find("p").html(),
                    "callLetter": $(item).find(".callLetter").html(),
                    "cmdName": "取消超速设置"
                });
            }
        });

        setSpeed(callletters, cmd_list, flag, speed);
        sendCMD_TRG90(trg90_datas);//发送trg指令
        //弹窗消失
        closeDialog('speedSet');
    });
    
    //标注为高风险
    $("#cmd_carMark_risk").click(function () {
    	var cars = $("#markCarlist li");
        var vehicleIds = [];
        var risk=$("#vehicle-query-grid .l-grid2 .l-selected span");
        var risk2=$("#vehicle-info-grid .l-grid2 .l-selected span");
        var risk3=$("#monitor-grid .l-grid2 .l-selected span");
        $.each(cars, function (i, item) {
        	vehicleIds.push($(item).find(".vehicleId").html());
        });
        if(vehicleIds.length>0){
        	$.post("../vehicle/updateMultiVehicleState",{
        		vehicleIds:vehicleIds.join(","),
        		vehicleState:2
        	},function(data){
        		if(data.success){
        			//console.log(data);
        			//关闭弹窗
        			closeDialog('carMark');
        			risk.css("background","#f8e944");
        			risk2.css("background","#f8e944");
        			risk3.css("background","#f8e944");
        		}
        	});
        }
    });
    
    //标注为逾期
    $("#cmd_carMark_overdue").click(function () {
    	var cars = $("#markCarlist li");
        var vehicleIds = [];
        var risk=$("#vehicle-query-grid .l-grid2 .l-selected span");
        var risk2=$("#vehicle-info-grid .l-grid2 .l-selected span");
        var risk3=$("#monitor-grid .l-grid2 .l-selected span");
        $.each(cars, function (i, item) {
        	vehicleIds.push($(item).find(".vehicleId").html());
        });
        if(vehicleIds.length>0){
        	$.post("../vehicle/updateMultiVehicleState",{
        		vehicleIds:vehicleIds.join(","),
        		vehicleState:3
        	},function(data){
        		if(data.success){
        			//关闭弹窗
        			closeDialog('carMark');
        			risk.css("background","#F36843");
        			risk2.css("background","#F36843");
        			risk3.css("background","#F36843");
        		}
        	});
        }
    });
    
  //标注为入库
    $("#cmd_carMark_storage").click(function () {
    	var cars = $("#markCarlist li");
        var vehicleIds = [];
        var risk=$("#vehicle-query-grid .l-grid2 .l-selected span");
        var risk2=$("#vehicle-info-grid .l-grid2 .l-selected span");
        var risk3=$("#monitor-grid .l-grid2 .l-selected span");
        $.each(cars, function (i, item) {
        	vehicleIds.push($(item).find(".vehicleId").html());
        });
        if(vehicleIds.length>0){
        	$.post("../vehicle/updateMultiVehicleState",{
        		vehicleIds:vehicleIds.join(","),
        		vehicleState:4
        	},function(data){
        		if(data.success){
        			//关闭弹窗
        			closeDialog('carMark');
        			risk.css("background","#89dae6");
        			risk2.css("background","#89dae6");
        			risk3.css("background","#89dae6");
        		}
        	});
        }
    });
    
  //标注为出险
    $("#cmd_carMark_out_of_danger").click(function () {
    	var cars = $("#markCarlist li");
        var vehicleIds = [];
        var risk=$("#vehicle-query-grid .l-grid2 .l-selected span");
        var risk2=$("#vehicle-info-grid .l-grid2 .l-selected span");
        var risk3=$("#monitor-grid .l-grid2 .l-selected span");
        $.each(cars, function (i, item) {
        	vehicleIds.push($(item).find(".vehicleId").html());
        });
        if(vehicleIds.length>0){
        	$.post("../vehicle/updateMultiVehicleState",{
        		vehicleIds:vehicleIds.join(","),
        		vehicleState:5
        	},function(data){
        		if(data.success){
        			//关闭弹窗
        			closeDialog('carMark');
        			risk.css("background","#ff0000");
        			risk2.css("background","#ff0000");
        			risk3.css("background","#ff0000");
        		}
        	});
        }
    });
    
    //恢复正常
    $("#cmd_carMark_normal").click(function () {
    	var cars = $("#markCarlist li");
        var vehicleIds = [];
        var risk=$("#vehicle-query-grid .l-grid2 .l-selected span");
        var risk2=$("#vehicle-info-grid .l-grid2 .l-selected span");
        var risk3=$("#monitor-grid .l-grid2 .l-selected span");
        $.each(cars, function (i, item) {
        	vehicleIds.push($(item).find(".vehicleId").html());
        });
        if(vehicleIds.length>0){
        	$.post("../vehicle/updateMultiVehicleState",{
        		vehicleIds:vehicleIds.join(","),
        		vehicleState:1
        	},function(data){
        		if(data.success){
        			//关闭弹窗
        			closeDialog('carMark');
        			risk.css("background","transparent");
        			risk2.css("background","transparent");
        			risk3.css("background","transparent");
        		}
        	});
        }
    });
    
	//设置车辆在租状态  
    $("#cmd_carLeaseStatus_Set").click(function(){
       var leaseStatus=$("#leaseStatus option:selected").val();
       var vehicleIds=[];
       var cars=$("#leaseStatusSetCarList li");
       $.each(cars,function(i,item){
    	   vehicleIds.push($(item).find(".vehicleId").html());           
       });
	   var rent=$(".l-selected .rent");
	   //console.log(rent);
	   var rows=CamionView.vehicleInfoGrid;
       if(vehicleIds.length>0){
         $.ligerDialog.confirm("确定修改车辆在租状态?",function(yes){
        	 if(yes===false){
        		 return;
        	 }
        	 $.ajax({
                 type:"POST",
         	     dataType:"json",
         	     url:'../vehicle/modifyLeaseStatusBatch',
         	     data:{"leaseStatus":leaseStatus,"vehicleIds":vehicleIds},
         	     success:function(data){
         			if(data.success){
         				//关闭弹窗
             			closeDialog('carLeaseStatusSet');
             			//修改状态
						if(leaseStatus==0){
             				rent.hasClass("l-icon-rent-yes")?rent.removeClass("l-icon-rent-yes"):rent.removeClass("l-icon-rent-no");
							rent.addClass("l-icon-rent-no");	
						}
						if(leaseStatus==1){
             				rent.hasClass("l-icon-rent-yes")?rent.removeClass("l-icon-rent-yes"):rent.removeClass("l-icon-rent-no");
							rent.addClass("l-icon-rent-yes");
						}
         			}else{
         				console.log(data.message);
         				$.ligerDialog.error("操作失败");
         				return;
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
    });
    
    //设置锁油电
    $("#cmd_LockPowerAndOil_start").click(function () {
        var cars = $("#LockPowerAndOilCarList li");
        var callletters = [];
        var cmd_list = [];//资料
        var trg90_datas = [];//trg90导航专发
		$(".l-dialog-win").addClass("stack");
		$.ligerDialog.confirm('确定要发送指令?',function(yes){
			if(yes === false) return;
			$.each(cars, function (i, item) {
				if ($(item).find(".preCmd").html() == 1) {//预存
					trg90_datas.push({
						"unit_id": $(item).find(".unitID").html(),
						"call_letter": $(item).find(".callLetter").html(),
						"cmd_id": "0x0006"
					});
				} else {
					callletters.push($(item).find(".callLetter").html());
					cmd_list.push({
						"pla": $(item).find("p").html(),
						"callLetter": $(item).find(".callLetter").html(),
						"cmdName": "设置锁油电"
					});
				}
			});
			setLockPowerAndOil(callletters, cmd_list);
			sendCMD_TRG90(trg90_datas);//发送trg指令
			//弹窗消失
			closeDialog('LockPowerAndOil');
		});
		$(".l-dialog-btn-inner").click(function(){$(".l-dialog-win").removeClass("stack");});
		$(".l-dialog-close").click(function(){$(".l-dialog-win").removeClass("stack");});
    });
    
  //恢复供油电
    $("#cmd_UnLockPowerAndOil_start").click(function () {
        var cars = $("#UnLockPowerAndOilCarList li");
        var callletters = [];
        var cmd_list = [];//资料
        var trg90_datas = [];//trg90导航专发
		$(".l-dialog-win").addClass("stack");
		$.ligerDialog.confirm('确定要发送指令?',function(yes){
			if(yes === false) return;
			$.each(cars, function (i, item) {
				if ($(item).find(".preCmd").html() == 1) {//预存
					trg90_datas.push({
						"unit_id": $(item).find(".unitID").html(),
						"call_letter": $(item).find(".callLetter").html(),
						"cmd_id": "0x0007"
					});
				} else {
					callletters.push($(item).find(".callLetter").html());
					cmd_list.push({
						"pla": $(item).find("p").html(),
						"callLetter": $(item).find(".callLetter").html(),
						"cmdName": "设置恢复供油电"
					});
				}
			});

			setUnLockPowerAndOil(callletters, cmd_list);
			sendCMD_TRG90(trg90_datas);//发送trg指令
			//弹窗消失
			closeDialog('UnLockPowerAndOil');
		});
		$(".l-dialog-btn-inner").click(function(){$(".l-dialog-win").removeClass("stack");});
		$(".l-dialog-close").click(function(){$(".l-dialog-win").removeClass("stack");});
    });
    
    //历史轨迹查询
    $("#cmd_history_search").click(function () {
    	history_data =[];//清空
    	$(".chaxunNum1").html("0");
    	var carNum = $('#carNum2').val();
    	var txt_startTime = $('#txt_startTime1').val();
    	var txt_endTime = $('#txt_endTime1').val();
    	var speed = $('#speed1').val();
    	var d1=new Date(txt_startTime);
    	var d2=new Date(txt_endTime);
    	var orderPoints = $('#orderPoints').val();
    	var distance=(d2-d1)/(1000*60*60);
    	/**2017-05-03  查询时间90不能大于60天 其它设备不能大于5天**/
    	var cars = $("#playHistoryCarlist li");
     	var isHaveTrg90 =false;
        $.each(cars, function (i, item) {
          if($(item).find(".unitType").html().indexOf(trg90_unit_type) !=-1){
          	isHaveTrg90=true;
          }
         });
    	if (carNum == '') {
    		$.ligerDialog.warn('请填写或选择车牌号码!');
    		return false;
    	} else if (txt_startTime == '') {
    		$.ligerDialog.warn('请选择开始时间!');
    		return false;
    	} else if (txt_endTime == '') {
    		$.ligerDialog.warn('请选择结束时间!');
    		return false;
    	}else if(speed == ''){

    	}
    	/*else if(distance>72){
    		$.ligerDialog.warn('时间长度不能大于3天!');
    		return false;
    	}*/else if(distance<=0){
    		$.ligerDialog.warn('结束时间不能早于开始时间!');
    		return false;
    	}
    	var dis180 = 24*30*6;//演示6个月每个月按30天计算
    	if(isHaveTrg90){
        	if(distance>dis180){
        		$.ligerDialog.warn('无线设备查询时间不能大于6个月!');
        		return false;
        	}else if(orderPoints=="true"){
        		$.ligerDialog.warn('显示轨迹顺序点只针对有线设备!');
        		return false;
        	}
    	}else{
    		if(distance>dis180){
        		$.ligerDialog.warn('有线设备查询时间不能大于6个月!');
        		return false;
        	}
    	}
        /*if(isHaveTrg90){
        	if(distance>1440){
        		$.ligerDialog.warn('无线设备查询时间不能大于60天!');
        		return false;
        	}else if(orderPoints=="true"){
        		$.ligerDialog.warn('显示轨迹顺序点只针对有线设备!');
        		return false;
        	}
    	}else{
    		if(distance>360){
        		$.ligerDialog.warn('有线设备查询时间不能大于15天!');
        		return false;
        	}
    	}*/
        var formParam = getFormDataAll('history-form2');
        var gb=$('gb');
        var list=$('.xxNum1');
        
		GetHistoryGPS(txt_startTime, txt_endTime);

		history_head.numberPlate = carNum;//"粤B.123451";
		history_head.label = carNum;//"粤B.123451";
		history_head.callLetter = $("#cmd_history_callLetter").val();//"13912345001";
    });
    
    function addMarker(point,label){
    	baiduMap.centerAndZoom(point,16);
		var marker = new BMap.Marker(point);
//		baiduMap.addOverlay(marker);
		marker.setLabel(label);
	}  
     /*--------------------TRG90历史回放打点---------------------------*/
     function gpsMes(history_data){
    		    var callLetter 	= history_data.callLetter;
    		    var numberPlate = history_data.numberPlate;
    			var speed       = history_data.speed;
    			var gpsTime     = history_data.gpsTime;
    			var flag 		= history_data.flag;
    			var lon 		= history_data.lon;
    			var lat 		= history_data.lat;
    			var course 		= history_data.course;	
    			var stamp 		= history_data.stamp;
    			var isAlarm 	= history_data.isAlarm;
    			var status 		= history_data.status;
    			var isLoc       = history_data.isLoc;
    			var course      = history_data.course;
    			var accTimeLen  = history_data.accTimeLen;
    			var electricity = history_data.electricity;
    			if (flag == 1) {
    				isLoc="卫星定位";
    			} else if(flag == 2){
    				isLoc="基站定位";
    			}else{
    				isLoc="正在定位";
    			};
    			var opts = {
    					callLetter: callLetter,
    					numberPlate: numberPlate,
    					lon: lon,
    					lat: lat,
    					isLoc: isLoc,
    					speed: speed,
    					course: parseInt(course),
    					gpsTime: gpsTime,
    					status: status,
    					electricity:electricity
    				};
    			
    				converToGridRow(opts,function(rowData){
    					if(rowData != undefined){
    					CamionView.gpsInfoGrid.addRow(rowData,CamionView.gpsInfoGrid.rows[0],true);
    					}
        			});
    			
    	  
      }
     var isContinue = false;
     
     function addMarker(point,label){
    	 baiduMap.centerAndZoom(point, 15);
    	 var marker = new BMap.Marker(point);
    	 baiduMap.addOverlay(marker);
    	 marker.setLabel(label);
     }
     $("#car_stop").click(function () {
    	 clearTimeout(timer);
     })
     $("#car_continue").click(function () {
    	 isContinue = true;
    	 dd();
     })
     var timer = null;
     var tickCount = 0;
     $("#cmd_history_play").click(function() {
    	var cars = $("#playHistoryCarlist li");
      	var isHaveTrg90 =false;
      	var orderPoints = $('#orderPoints').val();
         $.each(cars, function (i, item) {
           if($(item).find(".unitType").html().indexOf(trg90_unit_type) !=-1){
           	isHaveTrg90=true;
           }
          });
    	 if(isHaveTrg90&&orderPoints=="true"){
     		$.ligerDialog.warn('显示轨迹顺序点只针对有线设备!');
     		return false;
     	}
        $("#closewindow").click();
        clearAllMarker();
    	dd();
     });
      function dd() {
    	 if(!isContinue){
    		 tickCount = 0;
    	 }
    	 isContinue = false;
    	 console.log("**--history_data--**");
//    	 console.log(history_data);
    	 baiduMap.centerAndZoom(new BMap.Point(history_data[0].lon,history_data[0].lat), 13);
    	 var i=0;
     	 var cars = $("#playHistoryCarlist li");
     	 var isHaveTrg90 =false;
          $.each(cars, function (i, item) {
          	if($(item).find(".unitType").html().indexOf(trg90_unit_type) !=-1){
          		isHaveTrg90=true;
          	}
          });
          if(isHaveTrg90){ 
        	  $("#car_stop").show();
        	  $("#car_continue").show();
        	  console.log("isHaveTrg90",isHaveTrg90);
        	  var tickTime = 150;
        	  var runCount = history_data.length;
        	  console.log("length",runCount);
        	  timer = setTimeout(tick,tickTime);
        	 function tick(){
        		 console.log(tickCount)
        		 GPSUtil.gpsToBaiduGps(
        				 parseFloat(history_data[tickCount].lon),
        				 parseFloat(history_data[tickCount].lat),
        				 1,5,
        				 function(gps){
        					 var point = new BMap.Point(gps.baiduGps.lon,gps.baiduGps.lat);
        		          		var label = new BMap.Label(tickCount,{offset:new BMap.Size(20,-10)});
        		          		label.setStyle({
        		         			 color : "red",
        		         			 fontSize : "15px",
        		         			 height : "0px",
        		                      width:"0px",
        		         			 border:"0px"
        		         		 });
        		          		addMarker(point,label);
        		          		gpsMes(history_data[tickCount]);
        		          		tickCount++;
        		          		if(tickCount<runCount) timer = setTimeout(tick,tickTime);
        		          		if(tickCount==runCount){
        		          			$("#car_stop").hide();
        		               	    $("#car_continue").hide();
        		          		}
        		          	
        				 })
        	 }
          }else{
        	  map.playHistory(history_head, history_data, newHistoryCallback, playingHistoryCallback, closeHistoryCallback);
        	  //播放完毕后，进行停车时长的标注
        	  console.log("**准备开始停车时长的标注**");
        	  var gpsLatLoninfo = history_data;
        	  for(var i=0;i<gpsLatLoninfo.length-1;i++){
        			//结束时间  
            		  end_str = gpsLatLoninfo[i+1].gpsTime.replace(/-/g,"/");//一般得到的时间的格式都是：yyyy-MM-dd hh24:mi:ss 
            		  var end_date = new Date(end_str);//将字符串转化为时间  
            		  //开始时间  
            		  sta_str = gpsLatLoninfo[i].gpsTime.replace(/-/g,"/");  
            		  var sta_date = new Date(sta_str);  
            		  var plateTime = (end_date-sta_date)/(1000 * 60);//求出两个时间的时间差(分钟)
//            		  console.log(plateTime);
            		//两点之间时间差大于30 and lat lon 不相等
            		  var flagLatLon = ((gpsLatLoninfo[i].lon != gpsLatLoninfo[i+1].lon) && (gpsLatLoninfo[i].lat != gpsLatLoninfo[i+1].lat));
//            		  var  numgpsstatus23 = gpsLatLoninfo[i].status.contains('23');
//            		  console.log(numgpsstatus23);
            		  //相邻gpstime大于30分钟 and lat lon 不相等
            		  var time = parseInt(plateTime);
      				  var min = time;
      				  var hour = parseInt(time / 60);
                  	  var day =parseInt(hour / 24);
            		  if(plateTime > 30 && flagLatLon){
	            				if(time >= 60){
	                	    		if(hour>=24){
	                	    			var H = hour%24;
	                	    			if(H==0){
	                	    				//console.log(day+"天"+hour+"小时"+min%60+"分")
	                	    				min%60==0?time=day+"天":time=day+"天"+(min%60)+"分";
	                	    			}else{
	                	    				min%60==0?time=day+"天"+H+"小时":time=day+"天"+H+"小时"+(min%60)+"分"
	                	    			}
	                	    		}else{
	                	    			min%60==0?time=hour+"小时":time=hour+"小时"+(min%60)+"分";
	                	    		}
	            			}else{
	            			    	time=time+"分";
	            			   }
            			  	
          			    	var label = gpsLatLoninfo[i].numberPlate +"   "+ gpsLatLoninfo[i].gpsTime + "  时长(" + time + ")";
          			    	var label_style = {
								fontSize : "12px",
								backgroundColor : "white",
								border : "1px solid red",
								padding : "1px"
							};
         				    var marker = new map.newSimpleMarker({
  							lon : gpsLatLoninfo[i].lon,
  							lat : gpsLatLoninfo[i].lat,
//  						title : 'gpsTime',
  							icon : {
  								url: "../resources/images/map/marker.png",
  								left : 0,
  								top : 0,
  								width : 12,
  								height : 20,
  								anchorx : -6,
  								anchory : -20
  							},
  							label : {
  								text : label,
  								style : label_style
  							}
  						});
         				   map.addMarker(marker);
            		  }
        	  }
           }
          closeDialog('target1');
          
     }
    //历史轨迹播放关闭
    $("#cmd_history_close").click(function () {
    	closeDialog('target1');
    });
    
});

//判断数组中是否包含元素的方法
//Array.prototype.contains = function ( needle ) {
//	  for (i in this) {
//		if (this[i] == needle)
//			return true;
//	}
//	return false;
//}

//最后位置
CamionCMD.getLastPos=function(datagrid){
	var callletters = [];//呼号
    var data = datagrid._getSelecteds();
    var trg90_datas = [];
    if (data != null && data.length > 0) {
        $.each(data, function (i, item) {
        	/*if(data.preCmd ==1){
        		trg90_datas.push({
                    "unit_id": item.unitId,
                    "call_letter": item.callLetter,
                    "cmd_id": ""
                });
        	}*/
            callletters.push(item.callLetter);
        });
        GetLastPosition(callletters);
    }
    else {
        alert('请先选择车辆!');
        return false;
    }
}

//车辆定位
CamionCMD.getPosition=function(datagrid){
	var callletters = [];//呼号
    var cmd_list = [];//资料
    var trg90_datas = [];
    var data = datagrid._getSelecteds();
    console.log(data);//////////////
    if (data != null && data.length > 0) {
        $.each(data, function (i, item) {
            callletters.push(item.callLetter);
            cmd_list.push({
                "pla": item.plateNo,
                "callLetter": item.callLetter,
                "cmdName": "车辆定位"
            });
        });
        Position(callletters, cmd_list);
        // sendCMD_TRG90(trg90_datas);//发送trg指令
    }
    else {
        alert('请先选择车辆!');
        return false;
    }
}

//车辆定位2(传入data)
CamionCMD.getPosition2=function(data){
	var callletters = [];//呼号
    var cmd_list = [];//资料
    var trg90_datas = [];
    console.log(data);//////////////
    if (data != null && data.count > 0) {
    	var carData = data.list[0];
            callletters.push(carData.callLetter);
            cmd_list.push({
                "pla": carData.plateNo,
                "callLetter": carData.callLetter,
                "cmdName": "车辆定位"
            });
        Position(callletters, cmd_list);
        // sendCMD_TRG90(trg90_datas);//发送trg指令
    }
    else {
        alert('请先选择车辆!');
        return false;
    }
}

//车辆分布
CamionCMD.allVehiclePosition=function(){
	$.ligerDialog.confirm('确定要查询所有车辆位置吗?', function(yes){
		if(yes === false) 
			return;
		 $.ajax({
			   url:"../gps/data/allVehiclePosition",
			   type:"POST",
			   dataType:"json",
			   success:function (data){
			     if(data.success===true){
			    	 clearAllMarker();
			    	 var length=data.datas.length;
			    	 for(var i=0;i<length;i++){
			    		 var opts = {
	    					id: data.datas[i].plateNo,
	    					label: data.datas[i].plateNo,
	    					lon: data.datas[i].lon,
	    					lat: data.datas[i].lat,
	    					speed: data.datas[i].speed,
	    					course: parseInt(data.datas[i].course),
	    					gpsTime: data.datas[i].gpsTime.substring(0,19),
	    					status: data.datas[i].statsIds.split(';'),
	    					totalDistance:data.datas[i].totalMile
	    				};
	    				var m = map.addOrUpdateVehicleMarkerById(opts);
			    	 }
			    	 map.centerAndZoom(data.datas[length-1].lon, data.datas[length-1].lat, 15);
			         $.ligerDialog.success("查询所有车辆最后位置成功!");
				 }else{
					 console.log(data.message);
					 $.ligerDialog.error("查询所有车辆最后位置失败!");
				 }
			   },		   
			   error:function(xhr,status,err){
			      console.log(status,err);
			      $.ligerDialog.error("查询所有车辆最后位置失败!");
		       }
		   });
	});
}

//function moresleep(n) {
//
//    var start = new Date().getTime();
//
//    while(true)  if(new Date().getTime()-start > n) break;
//
//    }

//离线指令(全80)
//CamionCMD.OffLineCommandAllEighty=function(dataPath){
//	console.log("---**----");
//	console.log(dataPath);
//	$.ligerDialog.confirm('确定要发送所有离线指令(全80)吗?', function(yes){
//		if(yes === false) 
//			return;
//		 $.ajax({
//			   url:"../gps/data/OffLineCommandAllEighty",
//			   type:"POST",
//			   data : {'agencyAttributesPath':dataPath},
//			   dataType:"json",
//			   async:true,
//			   success:function (data){
//				   console.log(data);
//					var callletters = [];//呼号
//				    var cmd_list = [];//资料
//				    
//				    if (data.datas != null && data.datas.length > 0) {
//				        $.each(data.datas, function (i, item) {
//				            callletters.push(item.callLetter);
//				            cmd_list.push({
//				                "pla": item.plateNo,
//				                "callLetter": item.callLetter,
//				                "cmdName": "离线指令(全80)"
//				            });
//				        });
//				        Position(callletters, cmd_list);
//				        
//				        // sendCMD_TRG90(trg90_datas);//发送trg指令
//				    }
//				    else {
//				    	$.ligerDialog.error("该机构下无离线80车辆!");
//				        return false;
//				    }
//			   },		   
//			   error:function(xhr,status,err){
//			      console.log(status,err);
//			      $.ligerDialog.error("发送离线指令(全80)失败!");
//		       }
//		   });
//	});
//}
//离线指令(全80)
CamionCMD.OffLineCommandPosition=function(datagrid){
	var callletters = [];//呼号
    var cmd_list = [];//资料
    var trg90_datas = [];
    var data = datagrid._getSelecteds();
    console.log(data);//////////////
    if (data != null && data.length > 0) {
        $.each(data, function (i, item) {
        	if((item.unitType.indexOf('TRG80-03')!=-1) || (item.unitType.indexOf('TRG80-08')!=-1)){
        		if(item.isOnline == 0){
        			 callletters.push(item.callLetter);
                     cmd_list.push({
                    	 "pla": item.plateNo,
    		                "callLetter": item.callLetter,
    		                "cmdName": "不在线定位"
                     });
        		}
        	}
        });
        Position(callletters, cmd_list);
    }
    else {
    	alert('请先选择车辆!');
        return false;
    }
}

//车台复位
CamionCMD.reset=function(datagrid){
	var callletters = [];//呼号
    var cmd_list = [];//资料
    // var trg90_datas = [];//trg90导航专发
    var data = datagrid._getSelecteds();
    if (data != null && data.length > 0) {
    	$.ligerDialog.confirm('确定要复位所选的车辆吗?', function(yes){
    		if(yes === false) 
    			return;
    		$.each(data, function (i, item) {
                callletters.push(item.callLetter);
                cmd_list.push({
                    "pla": item.plateNo,
                    "callLetter": item.callLetter,
                    "cmdName": "车台复位"
                });
            });
            Reback(callletters, cmd_list);
            //sendCMD_TRG90(trg90_datas);//发送trg指令
    	});
    }
    else {
        alert('请先选择车辆!');
        return false;
    }
} 

//定时报告
CamionCMD.timingReport=function(datagrid){
	var callletters = [];//呼号
    var cmd_list = [];//资料
    var data = datagrid._getSelecteds();
    if (data != null && data.length > 0) {
        var sentMassageHTML = "";
        $.each(data, function (i, item) {
            sentMassageHTML += "<li class='label label-info'><span class='scBtn'></span><p>" + item.plateNo + "</p>" + "<b class='hide callLetter'>" 
            + item.callLetter + "</b>" + "<b class='hide unitID'>" + item.unitId + "</b>" + "<b class='hide preCmd'>" 
            + item.preCmd + "</b>" + "<b class='hide unitType'>" + item.unitType+ "</b>"+ "</li>";
        });
        $("#timingReportCarlist").html("").append(sentMassageHTML);//清空写入
        $('#timingReportCarlist li').each(function(i,elem){
            if($(elem).find(".unitType").html().indexOf(trg90_unit_type)!=-1){
                $(elem).css('background-color','#F36843');
            }
        });
        $("#timingReport_hour").val("");
        $("#timingReport_min").val("");
        $("#timingReport_sec").val("");
        openDialog('timingReport',{
        	title:'定时报告',width:450,height:310,isResize:false
        },function(dialog){
        		
        });//弹窗显示
    }
    else {
        alert('请先选择车辆!');
        return false;
    }
}

//定时报告2(传入data)
CamionCMD.timingReport2=function(data){
	var callletters = [];//呼号
    var cmd_list = [];//资料
    if (data != null && data.count > 0) {
        var sentMassageHTML = "";
        var carData = data.list[0];
            sentMassageHTML += "<li class='label label-info'><span class='scBtn'></span><p>" + carData.plateNo + "</p>" + "<b class='hide callLetter'>" 
            + carData.callLetter + "</b>" + "<b class='hide unitID'>" + carData.unitId + "</b>" + "<b class='hide preCmd'>" 
            + carData.preCmd + "</b>" + "<b class='hide unitType'>" + carData.unitType+ "</b>"+ "</li>";
        $("#timingReportCarlist").html("").append(sentMassageHTML);//清空写入
        $('#timingReportCarlist li').each(function(i,elem){
            if($(elem).find(".unitType").html().indexOf(trg90_unit_type)!=-1){
                $(elem).css('background-color','#F36843');
            }
        });
        $("#timingReport_hour").val("");
        $("#timingReport_min").val("");
        $("#timingReport_sec").val("");
        openDialog('timingReport',{
        	title:'定时报告',width:502,height:250,isResize:false
        },function(dialog){
        		
        });//弹窗显示
    }
    else {
        alert('请先选择车辆!');
        return false;
    }
}


//蓝牙连接断开上报频率设置
CamionCMD.bluetoothReport=function(plateNo,data){
	var callletters = [];//呼号
    var cmd_list = [];//资料
        var sentMassageHTML = "";
        sentMassageHTML += "<li class='label label-info'><p class='callLetter'>" 
    	+ data.callLetter + "</p>" + "<b class='hide plateNo'>" 
    	+ plateNo + "</b>" + "<b class='hide unitID'>" 
    	+ data.sonUnitId + "</b>" + "<b class='hide preCmd'>" 
    	+ data.preCmd + "</b>"+"<b class='hide unitType'>" 
    	+ data.unitType+ "</b>" + "</li>";
        $("#bluetoothReportCarlist").html("").append(sentMassageHTML);//清空写入
        $("#bluetoothReport_hour").val("");
        $("#bluetoothReport_min").val("");
        $("#bluetoothReport_sec").val("");
        openDialog('bluetoothReport',{
        	title:'蓝牙连接断开上报频率间隔',width:502,height:250,isResize:false
        },function(dialog){
        		
        });//弹窗显示
}

//子机定时报告
CamionCMD.timingReportForSonUnit=function(plateNo,data){
	var callletters = [];//呼号
    var cmd_list = [];//资料
    
    var sentMassageHTML = "";
    sentMassageHTML += "<li class='label label-info'><p class='callLetter'>" 
    	+ data.callLetter + "</p>" + "<b class='hide plateNo'>" 
    	+ plateNo + "</b>" + "<b class='hide unitID'>" 
    	+ data.sonUnitId + "</b>" + "<b class='hide preCmd'>" 
    	+ data.preCmd + "</b>"+"<b class='hide unitType'>" 
    	+ data.unitType+ "</b>" + "</li>";
    $("#timingReportCallLetterlist").html("").append(sentMassageHTML);//清空写入
    $("#timingReportForSonUnit_hour").val("");
    $("#timingReportForSonUnit_min").val("");
    $("#timingReportForSonUnit_sec").val("");
    openDialog('timingReportForSonUnit',{
    	title:'子机定时报告',width:502,height:250,isResize:false
    },function(dialog){
    		
    });//弹窗显示
}


//熄火定时报告
CamionCMD.misfireTimingReport=function(datagrid){
	var callletters = [];//呼号
    var cmd_list = [];//资料
    var data = datagrid._getSelecteds();
    if (data != null && data.length > 0) {
        var sentMassageHTML = "";
        $.each(data, function (i, item) {
            sentMassageHTML += "<li class='label label-info'><span class='scBtn'></span><p>" + item.plateNo + "</p>" + "<b class='hide callLetter'>" 
            + item.callLetter + "</b>" + "<b class='hide unitID'>" + item.unitId + "</b>" + "<b class='hide preCmd'>" 
            + item.preCmd + "</b>"+ "<b class='hide unitType'>" + item.unitType+ "</b>" + "</li>";
        });
        $("#misfireTimingReportCarlist").html("").append(sentMassageHTML);//清空写入
        $('#misfireTimingReport li').each(function(i,elem){
            if($(elem).find(".unitType").html().indexOf(trg90_unit_type)!=-1){
                $(elem).css('background-color','#F36843');
            }
        });
        $("#misfireTimingReport_hour").val("");
        $("#misfireTimingReport_min").val("");
        $("#misfireTimingReport_sec").val("");
        openDialog('misfireTimingReport',{
        	title:'熄火定时报告',width:435,height:235,isResize:false
        },function(dialog){
        		
        });//弹窗显示
    }
    else {
        alert('请先选择车辆!');
        return false;
    }
}

CamionCMD.setSpeed=function(datagrid){
	var callletters = [];//呼号
    var cmd_list = [];//资料
    var data = datagrid._getSelecteds();
    if (data != null && data.length > 0) {
        var sentMassageHTML = "";
        $.each(data, function (i, item) {
            sentMassageHTML += "<li class='label label-info'><p>" + item.plateNo + "</p>" + "<b class='hide callLetter'>" 
            + item.callLetter + "</b>" + "<b class='hide unitID'>" + item.unitId + "</b>" + "<b class='hide preCmd'>" 
            + item.preCmd + "</b>" + "<b class='hide unitType'>" + item.unitType+ "</b>"+ "</li>";
        });
        $("#speedSetCarlist").html("").append(sentMassageHTML);//清空写入
        $('#speedSetCarlist li').each(function(i,elem){
            if($(elem).find(".unitType").html().indexOf(trg90_unit_type)!=-1){
                $(elem).css('background-color','#F36843');
            }
        });
        $("#carSpeed").val("");
        openDialog('speedSet',{
        	title:'超速设置',width:400,height:315,isResize:false
        },function(dialog){
        		
        });//弹窗显示
    }
    else {
        alert('请先选择车辆!');
        return false;
    }
}
//清除首次上线时间
CamionCMD.ClearFirtonlineTime=function(datagrid){
	var unitId = "";//需要传的参数
    var data = datagrid._getSelecteds();
    if (data != null && data.length > 0) {
    	$.ligerDialog.confirm('是否确定清除所选车辆的首次上线时间?', function(yes){
    		if(yes === false) 
    			return;
    		$.each(data, function (i, item) {
    			unitId+=item.unitId+",";
            });
         	$.ajax({
 	 				type:'post',
 	 				url:'../twgVehicle/clearFirstonlieTime',
 	 				//contentType : "application/json; charset=utf-8",
 	 				data:{unitIds:unitId},
 	 				success:function(data){
 	 					if(data.success){
 	 	                    $.ligerDialog.success(data.message);
 	 	                }else{
 	 	                    $.ligerDialog.error(data.message);
 	 	                }
 	 				},
 	 				error:function(error){
 	 					console.log('服务器出错了!');
 	 				}
 	 			});
    	});
    }
    else {
        alert('请先选择车辆!');
        return false;
    }
}
//终端转网
CamionCMD.unitTransferNetwork=function(datagrid){
	 var data = datagrid._getSelecteds();
	if (data != null && data.length > 0) {
		 var sentMassageHTML = "";
	        $.each(data, function (i, item) {
	            sentMassageHTML += "<li class='label label-info'><span class='scBtn'></span><p>" + item.plateNo + "</p>" + "<b class='hide callLetter'>" 
	            + item.callLetter + "</b>" + "<b class='hide unitID'>" + item.unitId + "</b>" + "<b class='hide preCmd'>" 
	            + item.preCmd + "</b>" + "<b class='hide unitType'>" + item.unitType+ "</b>"+ "</li>";
	        });
	        $("#unitTransferFormCar").html("").append(sentMassageHTML);//清空写入
	        $('#unitTransferFormCar li').each(function(i,elem){
	            if($(elem).find(".unitType").html().indexOf(trg90_unit_type)!=-1){
	                $(elem).css('background-color','#F36843');
	            }
	        });
	        $("#apn").val("");
	        $("#port").val("");
	        $("#ip").val("");
		openDialog('unitTransferNetwork',{
	    	title:'终端转网',width:380,height:360,isResize:false
	    },function(dialog){});//弹窗显示
	}else{	
		alert('请先选择车辆!');
        return false;	
			
	}	
}
//终端升级
CamionCMD.unitUpgrade = function(datagrid){
	$("#add_userNames").val("");
	$("#add_passwords").val("");
	openDialog('unitUpgrade',{
    	width: 550,
    	height:277,
    	type: 'none',
    	title:'终端升级'
	})
};

$("#unitUpgradeSubmit").click(function(){
	if (!JudgeValidate('#unitUpgrade-form')){
		return false;
	}
	closeDialog('unitUpgrade');
});
//批量清除首次上线时间.
CamionCMD.batchClearFirtonlineTime = function(datagrid){
	var data = datagrid._getSelecteds();
	$("#batchClearContent").val("")
	openDialog('batchClearContents',{
    	width: 530,
    	height:337,
    	type: 'none',
    	title:'批量清除首次上线时间',
    	buttons: [{ text: '确定', onclick: function(item, dialog){
			            	var callLetters = $("#batchClearContent").val();
			            	var callLetters = callLetters.replace(/(^\,+)|(\,$)/g,"");
			            	var batchLength = $("#batchClearContent").val();
			            	if(batchLength.length>10000){
			            		$.ligerDialog.warn("输入卡号过长！",'提示');
			            		return;
			            	}			            	
			            	$.ajax({
		    					type : 'post',
		    					url:"../twgVehicle/batchClearFirstonlieTime",
		    					data :{
		    						callLetters:callLetters
		    					},
		    					success : function(data) {
		    						  if(!callLetters){
		    							  $.ligerDialog.warn("车载号码不能为空！",'提示');
		    							  return;
		    						  }
		    						  if(data.success){
		    							  $.ligerDialog.success(data.message,'提示',function(){
		    								  $("#batchsuccess textarea").val(data.datas);
		    								  if(data.datas){
		    									  openDialog('batchsuccess',{
			    								    	width: 530,
			    								    	height:337,
			    								    	type: 'none',
			    								    	title:'未成功数据（以下卡号不存在）',
			    								    	content:"",
			    								    	buttons: [{ text: '复制',cls:'mycopy', onclick: function(item, dialog){
			    								    		var copycontent = $("#batchsuccess textarea").val();
			    								    		var trike=true;
		    								    		   var clipboard2 = new Clipboard('.mycopy', {
		    								    				text: function() {
		    								    					var content = copycontent;
		    								    					return content;
		    								    				}
		    								    			});
		    								    			clipboard2.on('success', function(e) {
		    								    				closeDialog('batchsuccess');
		    								    				if(trike){
		    								    					$.ligerDialog.success("车载号码复制成功");
		    								    					console.log("susscess")
		    								    				}
		    								    				trike=false;
		    								    			});
		    								    		 
			    								    		}
			    								    	}]
			    								  })
			    							  }
		    							  });
		    							  closeDialog('batchClearContents');
		    						  }else{
		    							  $.ligerDialog.warn(data.message,'提示',function(){
		    								  $("#batcherror textarea").val(data.datas);
		    								  openDialog('batcherror',{
			    								  width: 500,
			    							    	height:337,
		    								    	type: 'none',
		    								    	title:'未成功数据（以下卡号不存在）',
		    								    	buttons: [{ text: '复制',cls:'mycopy', onclick: function(item, dialog){
		    								    		var copycontent = $("#batcherror textarea").val();
		    								    		var trike=true;
	    								    		   var clipboard2 = new Clipboard('.mycopy', {
	    								    				text: function() {
	    								    					var content = copycontent;
	    								    					return content;
	    								    				}
	    								    			});
	    								    			clipboard2.on('success', function(e) {
	    								    				closeDialog('batcherror');
	    								    				if(trike){
	    								    					$.ligerDialog.success("车载号码复制成功");
	    								    					console.log("susscess")
	    								    				}
	    								    				trike=false;
	    								    			});
		    								    		}
		    								    	}]
		    								  })
		    							  })
		    							  closeDialog('batchClearContents');
		    						  }
		    					},
		    					error : function(xhr,status,err) {
		    						$.ligerDialog.error(data.message);
		    					},
		    					dataType:'json',
			            	})
    						} 
			            	}, 
    	     { text: '取消', onclick: function(item, dialog){closeDialog('batchClearContents');}}]
    
    })
}
//设置闹钟
CamionCMD.setAlarmClock=function(datagrid){
	
	 var data = datagrid._getSelecteds();
	if (data != null && data.length > 0) {
		 var sentMassageHTML = "";
	        $.each(data, function (i, item) {
	        	if(item.unitType.indexOf(trg90_unit_type)!=-1){
	        		 sentMassageHTML += "<li class='label label-info'><span class='scBtn'></span><p>" + item.plateNo + "</p>" + "<b class='hide callLetter'>" 
	 	            + item.callLetter + "</b>" + "<b class='hide unitID'>" + item.unitId + "</b>" + "<b class='hide preCmd'>" 
	 	            + item.preCmd + "</b>" + "<b class='hide unitType'>" + item.unitType+ "</b>"+ "</li>";
	        	}
	        });
	        $("#setAlarmClockFormCar").html("").append(sentMassageHTML);//清空写入
	        $('#setAlarmClockFormCar li').each(function(i,elem){
	        	if($(elem).find(".unitType").html().indexOf(trg90_unit_type)!=-1){
	                $(elem).css('background-color','#F36843');
	            }
	        });
	        if(sentMassageHTML==""){
	        	$.ligerDialog.warn("所选择车辆不支持闹钟设置!");
	        }else{
	        	openDialog('setAlarmClockBox',{
	     	    	title:'设置闹钟',
	     	    	width:380,
	     	    	cls:"setAlarmClass",
	     	    	height:260,
	     	    	isResize:false
	     	    },function(dialog){});//弹窗显示
		        $(".alarmClockHour").val("");
		        $(".alarmClockMinute").val("");
	        }
	}else{	
		alert('请先选择车辆!');
        return false;	
			
	}	
}
//点击闹钟增加按钮事件
$('#setAlarmClockForm').find('.zjzjzl').click(function(){
    if($('#setAlarmClockForm .u-line-row').length==4){
        $.ligerDialog.warn('<span style="color:red;padding-top:6px;font-size:14px;display:inline-block;">闹钟数量</span>'+' 不能超过4个!');
        return;
    }
    var clone=$(this).parent().clone();
    clone.find("input").val("");
    $(this).parent().parent().append(clone);
    var t=$('#setAlarmClockForm').find('.u-line-row');
    changeRow(t);
    //jifeixinxi();
});
//重置闹钟增加和删除按钮的内容
function changeRow(node){
    node.each(function(i,elem){
        if(i>0){
            $(elem).find('img').attr('src','../images/deleteZJZL.png');
            $(elem).find('img').attr('class','sczjzl').attr('title','删除');
        }
    });
}
//事件委托，点击删除闹钟按钮事件
$('#setAlarmClockForm').on('click','.sczjzl',function(){
    $(this).parent().remove();
    var t=$('#setAlarmClockForm .u-line-row');
    changeRow(t);
});
//验证设置闹钟时
function checkhour(obj){
	var objVal = $(obj).val();
	var Hour = /^(0?[0-9]|0[0-9]|1[0-9]|2[0-3])$/;
	if(!Hour.test(objVal)){
		$.ligerDialog.warn("请填写正确的小时格式");
		return false;
	}
}
//验证设置闹钟分钟
function checkmin(obj){
	var objVal = $(obj).val();
	var Min = /^(0?[0-9]||[0-5][0-9])$/;
	if(!Min.test(objVal)){
		$.ligerDialog.warn("请填写正确的分钟格式");
		return false;
	}
}
$("#set_clock_start").on("click",function(){
	var d = "";
	var t = $("#setAlarmClockForm").serializeArray();
	for(var i=0,len=t.length;i<len;i++){
		if(t[i].value.length<2){
			t[i].value = "0"+t[i].value;
		}
		d += t[i].value
	}
	for(var i=0,len=t.length;i<len;i++){
		if(t[i].name=="hour"){
			if(t[i].value=="0"){
				$.ligerDialog.warn("小时不能为空!");
				return false;
			}
			var Hour = /^(0?[0-9]|0[0-9]|1[0-9]|2[0-3])$/;
			if(!Hour.test(t[i].value)){
				$.ligerDialog.warn("请填写正确的小时格式");
				return false;
			}
		}
		if(t[i].name=="minute"){
			if(t[i].value=="0"){
				$.ligerDialog.warn("分钟不能为空!");
				return false;
			}
			var Min = /^(0?[0-9]||[0-5][0-9])$/;
			if(!Min.test(t[i].value)){
				$.ligerDialog.warn("请填写正确的分钟格式");
				return false;
			}
		}
	}
	var cars = $("#setAlarmClockFormCar li");
	var callletters = [];
	var cmd_list = [];//资料
    var TRG90CallLetterArr=[];
    var TRG90UnitIdArr=[];
	$.each(cars, function (i, item) {
		if($(item).find(".unitType").html().indexOf(trg90_unit_type)!=-1){
			var callLetter=$(item).find(".callLetter").html();
            var unitId=$(item).find(".unitID").html();
            TRG90CallLetterArr.push(callLetter);
            TRG90UnitIdArr.push(unitId);
		}
	});
     if(TRG90CallLetterArr.length>0){
     	var cmdId=0x00C3;
         var datas={
         		"callLetter":TRG90CallLetterArr,
         		"unitId":TRG90UnitIdArr,
         		"sendParams":d,
         		"cmdId":cmdId
          };
         savePreCmd(datas);
     }
	//弹窗消失
    closeDialog('setAlarmClockBox');
})
//设置星期闹钟
CamionCMD.setWeekClock=function(datagrid){
	var data = datagrid._getSelecteds();
	if (data != null && data.length > 0) {
		 var sentMassageHTML = "";
	        $.each(data, function (i, item) {
	        	if(item.unitType.indexOf(trg90_unit_type)!=-1){
	        		sentMassageHTML += "<li class='label label-info'><span class='scBtn'></span><p>" + item.plateNo + "</p>" + "<b class='hide callLetter'>" 
		            + item.callLetter + "</b>" + "<b class='hide unitID'>" + item.unitId + "</b>" + "<b class='hide preCmd'>" 
		            + item.preCmd + "</b>" + "<b class='hide unitType'>" + item.unitType+ "</b>"+ "</li>";
	        	}
	        });
	        
	        $("#setWeekClockFormCar").html("").append(sentMassageHTML);//清空写入
	        $('#setWeekClockFormCar li').each(function(i,elem){
	            if($(elem).find(".unitType").html().indexOf(trg90_unit_type)!=-1){
	                $(elem).css('background-color','#F36843');
	            }
	        });
	        $("#alarmClockHour").val("");
	        $("#alarmClockMinute").val("");
	        if(sentMassageHTML==""){
	        	$.ligerDialog.warn("所选择车辆不支持闹钟设置!");
	        }else{
	        	 document.getElementById("usertype").options.selectedIndex = -1;
	 	    	 $("#usertype").selectpicker('refresh');
	 	    	 $("#usertype").selectpicker({noneSelectedText:'请选择星期'});
	 		    openDialog('setWeekClockBox',{
	 	    	title:'设置星期闹钟',width:380,height:290,isResize:false
	 	        },function(dialog){});//弹窗显示
	        }
	}else{	
		alert('请先选择车辆!');
        return false;	
			
	}	
}

//关闭星期闹钟
CamionCMD.closeWeekClock=function(datagrid){
	var TRG90unitIdAry=[];
	var TRG90callLetterAry=[];
	var data = datagrid._getSelecteds();
	var sentMassageHTML = "";
	if (data != null && data.length > 0) {
	    $.each(data, function (i, item) {
	    	if(item.unitType.indexOf(trg90_unit_type)!=-1){
	    		sentMassageHTML += "<li class='label label-info'><span class='scBtn'></span><p>" + item.plateNo + "</p>" + "<b class='hide callLetter'>" 
	            + item.callLetter + "</b>" + "<b class='hide unitID'>" + item.unitId + "</b>" + "<b class='hide preCmd'>" 
	            + item.preCmd + "</b>" + "<b class='hide unitType'>" + item.unitType+ "</b>"+ "</li>";
	    	}
	    });
	    if(sentMassageHTML==""){
        	$.ligerDialog.warn("所选择车辆不支持闹钟设置!");
        }else{
    		$.ligerDialog.confirm('是否确定关闭所选车辆的星期闹钟?', function(yes){
        		if(yes === false) 
        			return;
        		$.each(data, function (i, item) {
        			if(item.unitType.indexOf(trg90_unit_type)!=-1){
        				TRG90unitIdAry.push(item.unitId);
        				TRG90callLetterAry.push(item.callLetter);
        			}
                });
        		 var cmdId=0x00C6;
        		 var datas={
                  		"callLetter":TRG90callLetterAry,
                  		"unitId":TRG90unitIdAry,
                  		"sendParams":"",
                  		"cmdId":cmdId
                   };
        		if(TRG90callLetterAry.length>0){
        			savePreCmd(datas);   
        		} 
    	    });
        }

	}else{	
		alert('请先选择车辆!');
        return false;	
			
	}	
}
$("#set_week_start").on("click",function(){
	var usertype =$("#usertype").val();
	if(usertype===null){
		$.ligerDialog.warn("请选择星期!");
		return false;
	}
	var weekValue="";
	for(var i=0,len=usertype.length;i<len;i++){
		weekValue+=usertype[i];
	}
	var d = "";
	var t = $("#setWeekClockForm").serializeArray();
	for(var i=0,len=t.length;i<len;i++){
		if(t[i].name !="usertype"){
			if(t[i].value.length<2){
				t[i].value = "0"+t[i].value;
			}
			d += t[i].value
		}		
	}
	for(var i=0,len=t.length;i<len;i++){
		if(t[i].name=="hour"){
			if(t[i].value=="0"){
				$.ligerDialog.warn("小时不能为空!");
				return false;
			}
			var Hour = /^(0?[0-9]|0[0-9]|1[0-9]|2[0-3])$/;
			if(!Hour.test(t[i].value)){
				$.ligerDialog.warn("请填写正确的小时格式");
				return false;
			}
		}
		if(t[i].name=="minute"){
			if(t[i].value=="0"){
				$.ligerDialog.warn("分钟不能为空!");
				return false;
			}
			var Min = /^(0?[0-9]||[0-5][0-9])$/;
			if(!Min.test(t[i].value)){
				$.ligerDialog.warn("请填写正确的分钟格式");
				return false;
			}
		}
	}
	var cars = $("#setWeekClockFormCar li");
	var callletters = [];
	var cmd_list = [];//资料
    var TRG90CallLetterArr=[];
    var TRG90UnitIdArr=[];
    var params = weekValue+"~"+d;
	$.each(cars, function (i, item) {
		if($(item).find(".unitType").html().indexOf(trg90_unit_type)!=-1){
			var callLetter=$(item).find(".callLetter").html();
            var unitId=$(item).find(".unitID").html();
            TRG90CallLetterArr.push(callLetter);
            TRG90UnitIdArr.push(unitId);
		}
	});
     if(TRG90CallLetterArr.length>0){
     	var cmdId=0x00C5;
         var datas={
         		"callLetter":TRG90CallLetterArr,
         		"unitId":TRG90UnitIdArr,
         		"sendParams":params,
         		"cmdId":cmdId
          };
         savePreCmd(datas);
     }
	//弹窗消失
    closeDialog('setWeekClockBox');
})
$("#cmd_update_start").click(function(){
		var cars = $("#unitTransferFormCar li");
		var callletters = [];
		var cmd_list = [];//资料
		var serviceIp = $("#ip").val();//服务端IP
		var servicePort = $("#port").val();//服务端端口
		var apn = $("#apn").val();//APN值
		var params=["",apn,serviceIp,servicePort,"","T","6","0"];
		var trg90params=""+"~"+apn+"~"+serviceIp+"~"+servicePort+"~"+""+"~"+"T"+"~"+"6"+"~"+"0";
	    var trg90_datas = [];//trg90导航专发
	    var TRG90CallLetterArr=[];
	    var TRG90UnitIdArr=[];
	    if(serviceIp==""&&servicePort==""){
	    	$.ligerDialog.warn("请填写ip或者端口!");
	    	return;        	
	    }
	    var regexIp=/^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/;
	    if(!regexIp.test(serviceIp)){
	    	$.ligerDialog.warn("ip格式错误!");
	    	return; 
	    }
		$.each(cars, function (i, item) {
			if($(item).find(".unitType").html().indexOf(trg90_unit_type)!=-1){
				var callLetter=$(item).find(".callLetter").html();
                var unitId=$(item).find(".unitID").html();
                TRG90CallLetterArr.push(callLetter);
                TRG90UnitIdArr.push(unitId);
			}else{
				callletters.push($(item).find(".callLetter").html());
				cmd_list.push({
					"pla": $(item).find("p").html(),
					"callLetter": $(item).find(".callLetter").html(),
					"cmdName": "转网"
				});
			}
		});
		if(callletters.length>0 || TRG90CallLetterArr.length>0){
			$.ligerDialog.confirm('是否确定转网?', function(yes){
	    		if(yes === false) 
	    			return;
	    		if(callletters.length>0){
	    			unitTransferNetwork(callletters, cmd_list,params);
	    		}
		    		
		    	if(TRG90CallLetterArr.length>0){
		    		var cmdId=0x0057;
		             var datas={
		             		"callLetter":TRG90CallLetterArr,
		             		"unitId":TRG90UnitIdArr,
		             		"sendParams":trg90params,
		             		"cmdId":cmdId
		              };
		             savePreCmd(datas);
		    	 }
			});
		}
        
         	
        
		//弹窗消失
	    closeDialog('unitTransferNetwork');
});

//车辆标注
CamionCMD.markCar=function(datagrid){
	var callletters = [];//呼号
    var cmd_list = [];//资料
    var data = datagrid._getSelecteds();
    if (data != null && data.length > 0) {
        var sentMassageHTML = "";
        $.each(data, function (i, item) {
            sentMassageHTML += "<li class='label label-info'>" +
            		"<p>" + item.plateNo + "</p>" + "<b class='hide vehicleId'>" + item.vehicleId + "</b>" + 
            		"<b class='hide unitID'>" + item.unitId + "</b>" + 
            		"<b class='hide preCmd'>" + item.preCmd + "</b>" + 
            		"<b class='hide unitType'>" + item.unitType+ "</b>"+
            		"</li>";
        });
        $("#markCarlist").html("").append(sentMassageHTML);//清空写入
        $('#markCarlist li').each(function(i,elem){
            if($(elem).find(".unitType").html().indexOf(trg90_unit_type)!=-1){
                $(elem).css('background-color','#F36843');
            }
        });
        openDialog('carMark',{
        	title:'设置车辆风险状态',width:410,height:310
        },function(dialog){
        		
        });//弹窗显示
    }
    else {
        alert('请先选择车辆!');
        return false;
    }
}

CamionCMD.setVehicleLeaseStatus=function(datagrid){
	var callletters = [];//呼号
    var data = datagrid._getSelecteds();
    if (data != null && data.length > 0) {
        var sentMassageHTML = "";
        $.each(data, function (i, item) {
            sentMassageHTML += "<li class='label label-info'>" +
            		"<p>" + item.plateNo + "</p>" + 
            		"<b class='hide vehicleId'>" + item.vehicleId + "</b>" +
            		"<b class='hide unitType'>" + item.unitType+ "</b>"+
            		"</li>";
        });
        $("#leaseStatusSetCarList").html("").append(sentMassageHTML);//清空写入
        $('#leaseStatusSetCarList li').each(function(i,elem){
            if($(elem).find(".unitType").html().indexOf(trg90_unit_type)!=-1){
                $(elem).css('background-color','#F36843');
            }
        });
        openDialog('carLeaseStatusSet',{
        	title:'设置车辆租赁状态',width:315,height:230
          },function(dialog){
        		
        });//弹窗显示
    }
    else {
        alert('请先选择车辆!');
        return false;
    }
}

function clearGPS(a){
    var selectCount=a._getSelecteds();
    console.log(selectCount);
}
//播放历史记录
CamionCMD.playHistory=function(datagrid){
	var data = datagrid._getSelecteds();
    if (data != null && data.length > 0) {
        var sentMassageHTML = "";
        $.each(data, function (i, item) {
            sentMassageHTML += "<li class='label label-info'><span class='scBtn'></span><p>" + item.plateNo + "</p>" + "<b class='hide callLetter'>" 
            + item.callLetter + "</b>" + "<b class='hide unitID'>" + item.unitId + "</b>" + "<b class='hide preCmd'>" 
            + item.preCmd + "</b>" + "<b class='hide unitType'>" + item.unitType+ "</b>"+ "</li>";
        });
        $("#playHistoryCarlist").html("").append(sentMassageHTML);//清空写入
    }
	var selectCount=datagrid._getSelecteds().length;
	var data=datagrid._getSelecteds()[0];
	if(selectCount>1){
		$.ligerDialog.warn('对不起,请选择一台车进行操作!');
	}else{
		$(".chaxunNum1").html("0");
		var numberPlateInput = $('#history-form2').find('[name=number_plate]');
		 $("#cmd_history_callLetter").val(data.callLetter);
		numberPlateInput.prop('value',data.plateNo);
		//$('#history-form2').find('[name=vehiclecolor][value='+data.vehiclecolor+']').prop('checked',true);
		var chaxunText=$('.chaxunBtn1');
        if(!chaxunText.hasClass('btn-primary')){
            chaxunText.addClass('btn-primary');
            chaxunText.val('查询');
        }
        clearInterval(countdown);
        $("#exportHistoryInfo").val("导出");

		var bofangText=$("#cmd_history_play");
		var daochuText=$("#exportHistoryInfo");
		var chaxunText=$('.chaxunBtn1');
		chaxunText.removeClass('controllAct');
        bofangText.addClass('controllAct');
        daochuText.addClass('controllAct');
        $("#callLetter_data").val(data.callLetter);
		openDialog('target1',{title:'历史回放查询',width:370,height:378},function(dialog){
			if($("#exportHistoryInfo").hasClass("exporth")){
	        	$("#exportHistoryInfo").removeClass("exporth");
	        }
		});
//		clearAllMarker();
		// if($('#tblr').hasClass('l-icon-lr')){
		// 	if(CamionView.resizePage) CamionView.resizePage();
		// 	$('#tblr').attr('class','l-icon l-icon-rl');
		// 	CamionView.centerLayout.setRightCollapse(true);
		// }
		//closeDialog("target1");
	}
}

//播放历史记录(传入data)
CamionCMD.playHistory2=function(data){
    if (data != null && data.length > 0) {
        var sentMassageHTML = "";
            sentMassageHTML += "<li class='label label-info'><span class='scBtn'></span><p>" + data.plateNo + "</p>" + "<b class='hide callLetter'>" 
            + data.callLetter + "</b>" + "<b class='hide unitID'>" + data.unitId + "</b>" + "<b class='hide preCmd'>" 
            + data.preCmd + "</b>" + "<b class='hide unitType'>" + data.unitType+ "</b>"+ "</li>";
        $("#playHistoryCarlist").html("").append(sentMassageHTML);//清空写入
    }
		$(".chaxunNum1").html("0");
		var numberPlateInput = $('#history-form2').find('[name=number_plate]');
		 $("#cmd_history_callLetter").val(data.callLetter);
		numberPlateInput.prop('value',data.plateNo);
		//$('#history-form2').find('[name=vehiclecolor][value='+data.vehiclecolor+']').prop('checked',true);
		var chaxunText=$('.chaxunBtn1');
        if(!chaxunText.hasClass('btn-primary')){
            chaxunText.addClass('btn-primary');
            chaxunText.val('查询');
        }
		openDialog('target1',{title:'历史回放查询',width:370,height:312},function(dialog){});
}

function sendCMD_TRG90(datas) {//下发导航指令
    console.log(datas);
    if (datas.length > 0) {
        var api_url = 'cmd/send';//url
        var params = JSON.stringify(datas);//参数

        $.ajax({
            type: "POST",
            traditional: true,
            url: api_url,
            data: params,//将对象序列化成JSON字符串 
            dataType: "json",
            contentType: 'application/json;charset=utf-8', //设置请求头信息  
            success: function (data) {
                if (result.success) {
                    alert("下发成功!");
                } else {
                    alert("下发失败!");
                }
            },
            error: function (res) {

            }
        });

    }
}

//预存指令
function savePreCmd(datas){
   $.ajax({
	   url:"../cmd/insertPreCmd",
	   type:"POST",
	   dataType:"json",
	   data:datas,
	   success:function (data){
	     if(data.success===true){	
	    	 
	         $.ligerDialog.success("操作成功");
	         
		 }else{
			 console.log(data.message);
			 $.ligerDialog.error("操作失败");
		 }
	   },		   
	   error:function(xhr,status,err){
	      console.log(status,err);
	      $.ligerDialog.error("操作失败");
       }
   })
}

//蓝牙子机连接断开上报频率设置
CamionCMD.timingReportBlueTooth=function(plateNo,data){
	var callletters = [];//呼号
    var cmd_list = [];//资料
    var sentMassageHTML = "";
    sentMassageHTML += "<li class='label label-info'><p class='callLetter'>" 
    	+ data.callLetter + "</p>" + "<b class='hide plateNo'>" 
    	+ plateNo + "</b>" + "<b class='hide unitID'>" 
    	+ data.sonUnitId + "</b>" + "<b class='hide preCmd'>" 
    	+ data.preCmd + "</b>"+"<b class='hide unitType'>" 
    	+ data.unitType+ "</b>" + "</li>";
    $("#timingReportCallLetterlist2").html("").append(sentMassageHTML);//清空写入
    $("#timingReportForSonUnit_hour2").val("");
    $("#timingReportForSonUnit_min2").val("");
    $("#timingReportForSonUnit_sec2").val("");
    openDialog('timingReportBlueTooth',{
    	title:'蓝牙子机连接断开频率设置',width:502,height:250,isResize:false
    },function(dialog){
    		
    });//弹窗显示
}

//设置子机蓝牙连接断开频率
$("#cmd_blueTooth_start").click(function () {
    var cars = $("#timingReportCallLetterlist2 li");
    var hourStr=$.trim($("#timingReportForSonUnit_hour2").val());
    var minStr=$.trim($("#timingReportForSonUnit_min2").val());
    var secStr=$.trim($("#timingReportForSonUnit_sec2").val());
    if(hourStr==""&&minStr==""&&secStr==""){
    	$.ligerDialog.warn("请填写时长!");
    	return;        	
    }
    var regexNum=/^[0-9]*$/;
    if(!regexNum.test(hourStr)||!regexNum.test(minStr)||!regexNum.test(secStr)){
    	$.ligerDialog.warn("时间格式错误!");
    	return; 
    }
    var hour=hourStr==""?0:parseInt(hourStr);
    var min=minStr==""?0:parseInt(minStr);
    var sec=secStr==""?0:parseInt(secStr);
    var sendParams=hour*60*60+min*60+sec;
    if(sendParams==0){
    	$.ligerDialog.warn("请填写时长!");
    	return; 
    }
    var callLetterArr=[];
    var unitIdArr=[];
    $.each(cars, function (i, item) {
       var callLetter=$(item).find(".callLetter").html();
       var unitId=$(item).find(".unitID").html();
       callLetterArr.push(callLetter);
       unitIdArr.push(unitId);
    });
    var cmdId=227;
    var datas={"callLetter":callLetterArr,'unitId':unitIdArr,"sendParams":sendParams,"cmdId":cmdId};
    savePreCmd(datas);
    closeDialog('timingReportBlueTooth');
})

//设置锁油电
CamionCMD.LockPowerAndOil=function(datagrid){
	var selectCount=datagrid._getSelecteds().length;
	if(selectCount>1){
		$.ligerDialog.warn('对不起,请选择一台车进行操作!');
	}else {
		var callletters = [];//呼号
		var cmd_list = [];//资料
		var data = datagrid._getSelecteds();
		if (data != null && data.length > 0) {
			var sentMassageHTML = "";
			$.each(data, function (i, item) {
				sentMassageHTML += "<li class='label label-info'><span class='scBtn'></span><p>" + item.plateNo + "</p>" + "<b class='hide callLetter'>" 
				+ item.callLetter + "</b>" + "<b class='hide unitID'>" + item.unitId + "</b>" + "<b class='hide preCmd'>" 
				+ item.preCmd + "</b>" + "<b class='hide unitType'>" + item.unitType+ "</b>"+ "</li>";
			});
			$("#LockPowerAndOilCarList").html("").append(sentMassageHTML);//清空写入
			$('#LockPowerAndOilCarList li').each(function(i,elem){
	            if($(elem).find(".unitType").html().indexOf(trg90_unit_type)!=-1){
	                $(elem).css('background-color','#F36843');
	            }
	        });
			openDialog('LockPowerAndOil',{
				title:'锁油电指令',width:300,height:180,isResize:false
			},function(dialog){
					
			});//弹窗显示
		}
		else {
			alert('请先选择车辆!');
			return false;
		}
	}
}

//设置锁油电2(传入data)
CamionCMD.LockPowerAndOil2=function(data){
		var callletters = [];//呼号
		var cmd_list = [];//资料
		if (data != null && data.count > 0) {
			var sentMassageHTML = "";
			var carData = data.list[0];
				sentMassageHTML += "<li class='label label-info'><span class='scBtn'></span><p>" + carData.plateNo + "</p>" + "<b class='hide callLetter'>" 
				+ carData.callLetter + "</b>" + "<b class='hide unitID'>" + carData.unitId + "</b>" + "<b class='hide preCmd'>" 
				+ carData.preCmd + "</b>" + "<b class='hide unitType'>" + carData.unitType+ "</b>"+ "</li>";
			$("#LockPowerAndOilCarList").html("").append(sentMassageHTML);//清空写入
			openDialog('LockPowerAndOil',{
				title:'锁油电指令',width:350,height:222,isResize:false
			},function(dialog){
					
			});//弹窗显示
		}
		else {
			alert('请先选择车辆!');
			return false;
		}
}

//设置恢复供油电
CamionCMD.UnLockPowerAndOil=function(datagrid){
	var selectCount=datagrid._getSelecteds().length;
	if(selectCount>1){
		$.ligerDialog.warn('对不起,请选择一台车进行操作!');
	}else {
		var callletters = [];//呼号
		var cmd_list = [];//资料
		var data = datagrid._getSelecteds();
		if (data != null && data.length > 0) {
			var sentMassageHTML = "";
			$.each(data, function (i, item) {
				sentMassageHTML += "<li class='label label-info'><span class='scBtn'></span><p>" + item.plateNo + "</p>" + "<b class='hide callLetter'>" 
				+ item.callLetter + "</b>" + "<b class='hide unitID'>" + item.unitId + "</b>" + "<b class='hide preCmd'>" 
				+ item.preCmd + "</b>" + "<b class='hide unitType'>" + item.unitType+ "</b>"+ "</li>";
			});
			$("#UnLockPowerAndOilCarList").html("").append(sentMassageHTML);//清空写入
			$('#UnLockPowerAndOilCarList li').each(function(i,elem){
	            if($(elem).find(".unitType").html().indexOf(trg90_unit_type)!=-1){
	                $(elem).css('background-color','#F36843');
	            }
	        });
			openDialog('UnLockPowerAndOil',{
				title:'恢复供油电指令',width:300,height:180,isResize:false
			},function(dialog){
					
			});//弹窗显示
		}
		else {
			alert('请先选择车辆!');
			return false;
		}
	}
}


//设置恢复供油电2(传入data)
CamionCMD.UnLockPowerAndOil2=function(data){
		var callletters = [];//呼号
		var cmd_list = [];//资料
		if (data != null && data.count > 0) {
			var sentMassageHTML = "";
			var carData = data.list[0];
				sentMassageHTML += "<li class='label label-info'><span class='scBtn'></span><p>" + carData.plateNo + "</p>" + "<b class='hide callLetter'>" 
				+ carData.callLetter + "</b>" + "<b class='hide unitID'>" + carData.unitId + "</b>" + "<b class='hide preCmd'>" 
				+ carData.preCmd + "</b>" + "<b class='hide unitType'>" + carData.unitType+ "</b>"+ "</li>";
			$("#UnLockPowerAndOilCarList").html("").append(sentMassageHTML);//清空写入
			openDialog('UnLockPowerAndOil',{
				title:'恢复供油电指令',width:350,height:222,isResize:false
			},function(dialog){
					
			});//弹窗显示
		}
		else {
			alert('请先选择车辆!');
			return false;
		}
}

//设置常跑轨迹统计
CamionCMD.CPGJ=function(datagrid){
	var data = datagrid._getSelecteds();
    if (data != null && data.length > 0) {
        var sentMassageHTML = "";
        $.each(data, function (i, item) {
            sentMassageHTML += "<li class='label label-info'><span class='scBtn'></span><p>" + item.plateNo + "</p>" + "<b class='hide callLetter'>" 
            + item.callLetter + "</b>" + "<b class='hide unitID'>" + item.unitId + "</b>" + "<b class='hide preCmd'>" 
            + item.preCmd + "</b>" + "<b class='hide unitType'>" + item.unitType+ "</b>"+ "</li>";
        });
        $("#playCPGJCarlist").html("").append(sentMassageHTML);//清空写入
    }
	var selectCount=datagrid._getSelecteds().length;
	var data=datagrid._getSelecteds()[0];
	console.log(data);
	if(selectCount>1){
		$.ligerDialog.warn('对不起,请选择一台车进行操作!');
	}else{
		//$(".chaxunNum1").html("0");
		var numberPlateInput = $('#CPGJ-form2').find('[name=number_plate]');
		 $("#cmd_CPGJ_callLetter").val(data.callLetter);
		 $("#cmd_CPGJ_unittype").val(data.unitType);
		numberPlateInput.prop('value',data.plateNo);
		var chaxunText=$('.chaxunBtn1');
        if(!chaxunText.hasClass('btn-primary')){
            chaxunText.addClass('btn-primary');
            chaxunText.val('开始分析');
        }
		openDialog('targetCPGJ',{title:'常跑轨迹查询',width:350,height:240},function(dialog){});
		clearAllMarker();
	}
}
//设置热点分析统计
CamionCMD.hotSpotAnalysis=function(datagrid){
	var data = datagrid._getSelecteds();
	if (data != null && data.length > 0) {
		var sentMassageHTML = "";
		$.each(data, function (i, item) {
			sentMassageHTML += "<li class='label label-info'><span class='scBtn'></span><p>" + item.plateNo + "</p>" + "<b class='hide callLetter'>" 
			+ item.callLetter + "</b>" + "<b class='hide unitID'>" + item.unitId + "</b>" + "<b class='hide preCmd'>" 
			+ item.preCmd + "</b>" + "<b class='hide unitType'>" + item.unitType+ "</b>"+ "</li>";
		});
		$("#playHotSpotCarlist").html("").append(sentMassageHTML);//清空写入
	}
	var selectCount=datagrid._getSelecteds().length;
	var data=datagrid._getSelecteds()[0];
	if(selectCount>1){
		$.ligerDialog.warn('对不起,请选择一台车进行操作!');
	}else{
		//$(".chaxunNum1").html("0");
		var numberPlateInput = $('#HotSpot-form2').find('[name=number_plate]');
		$("#cmd_HotSpot_callLetter").val(data.callLetter);
		$("#cmd_HotSpot_unittype").val(data.unitType);
		numberPlateInput.prop('value',data.plateNo);
		
		var chaxunText=$('.chaxunBtnHotSpot');
		if(!chaxunText.hasClass('btn-primary')){
			chaxunText.addClass('btn-primary');
			chaxunText.val('开始分析');
		}
		openDialog('targetHotSpot',{title:'热点分析查询',width:350,height:240},function(dialog){
			//$('#cmd_HotSpot_search').attr('disabled','disabled');
		});
		clearAllMarker();
	}
}
//OBD中控操作
CamionCMD.OBDCentralControlOperation = function(datagrid){
	var data = datagrid._getSelecteds();
	if (data != null && data.length > 0) {
		/*var sentMassageHTML = "";
		$.each(data, function (i, item) {
			sentMassageHTML += "<li class='label label-info'><span class='scBtn'></span><p>" + item.plateNo + "</p>" + "<b class='hide callLetter'>" 
			+ item.callLetter + "</b>" + "<b class='hide unitID'>" + item.unitId + "</b>" + "<b class='hide preCmd'>" 
			+ item.preCmd + "</b>" + "<b class='hide unitType'>" + item.unitType+ "</b>"+ "</li>";
		});
		$("#oboCarlist").html("").append(sentMassageHTML);//清空写入*/
	}

	var selectCount=datagrid._getSelecteds().length;
	var data=datagrid._getSelecteds()[0];
	if(selectCount>1){
		$.ligerDialog.warn('对不起,请选择一台车进行操作!');
	}else{
		//alert(data.unitType);
		/*if(data.unitType=="TGO03-01"){
			openDialog('oboCentralControlOperation',{title:'OBD中控操作',width:350,height:240},function(dialog){});
		}*/
		//console.log(data.plateNo);
		if(data.unitType=="TGO03-01"){
			openDialog('oboCentralControlOperation',{title:'OBD中控操作',width:460,height:555},function(dialog){
				//向后台发送请求
				 $.ajax({
			            type: "POST",
			            url: '../twgVehicle/queryOBDInfo',
			            data:{
			            	plateNo:data.plateNo,
			            },
			            error:function(xhr,status,err){
			                $.ligerDialog.error('设置失败，网络故障!');
			                return;
			            },
			            success:function(data){
			                console.log(data);
			                var callletters = data.datas.call_letter;			                
			                var vehicleType = data.datas.vehicleType;
			                var vehicleTypeValue = data.datas.vehicleTypeValue;
			                var displacement = data.datas.displacement;
			                var engineType = data.datas.engine_type;
			                var oil_tank_capacity  = data.datas.oil_tank_capacity;
			                
			                $("#sg-qclx").val(vehicleType);
			                $("#sg-yxrj").val(oil_tank_capacity);
			                $("#sg-pl").val(displacement);
			                if(engineType=="1"){
			                	 $("#sg-dllx").val("内燃机车");
			                }else if(engineType=="2"){
			                	$("#sg-dllx").val("油电混合");
			                }else if(engineType=="3"){
			                	$("#sg-dllx").val("纯电动车");
			                }
			              //休眠唤醒设置			        		
			                xmxxsz = function(){
			                	var xms=Number($("#xms").val());
				                var t=xms;
				                var cmd_list = [];//指令内容
				                cmd_list.push({
				                    "pla": datagrid.getSelecteds()[0].plateNo,
				                    "callLetter": datagrid.getSelecteds()[0].callLetter,
				                    "cmdName": "OBD设备休眠唤醒设置"
				                });
				               /* if(t<60){
				                	$.ligerDialog.warn('设置时间不能小于60分钟!');
				                	return;
				                }*/
				                console.log('时长:'+t+'小时');
			        			IntervalDeliverOBD(callletters, cmd_list, t); 
			        			closeDialog('oboCentralControlOperation');
			                }
			        		//OBD适配参数
			                obdspcs = function(){
			                	var cmd_list = [];//指令内容
				                cmd_list.push({
				                    "pla": datagrid.getSelecteds()[0].plateNo,
				                    "callLetter": datagrid.getSelecteds()[0].callLetter,
				                    "cmdName": "OBD适配参数设置"
				                });
				                setParamsOBD(callletters, cmd_list, vehicleTypeValue, displacement, engineType);
			        			closeDialog('oboCentralControlOperation');
			        			console.log('vehicleTypeValue:'+vehicleTypeValue+';displacement:'+';engineType:'+engineType);
			                }
			        		//总里程 和油耗调用的是同一个方法
			        		zlcyh = function(){
			        			var totalDistance=Number($("#zlcval").val());
			        			var oil=Number($("#yhval").val());
			        			var cmd_list = [];//指令内容
			        			cmd_list.push({
				                    "pla": datagrid.getSelecteds()[0].plateNo,
				                    "callLetter": datagrid.getSelecteds()[0].callLetter,
				                    "cmdName": "OBD设备总里程、油耗设置"
				                });
			        			if(totalDistance==0){
			        				$.ligerDialog.confirm('总里程为0,确定要设置吗?', function(yes){
			        	                if(yes === false) return;
			        	                setTotalDistanceAndOilOBD(callletters, cmd_list, totalDistance, oil);
			        	                closeDialog('oboCentralControlOperation');
			        	            })
			        			}else if(oil==0){
			        				$.ligerDialog.confirm('油耗为0,确定要设置吗?', function(yes){
			        	                if(yes === false) return;
			        	                setTotalDistanceAndOilOBD(callletters, cmd_list, totalDistance, oil);
			        	                closeDialog('oboCentralControlOperation');
			        	            })
			        			}else{
				        			setTotalDistanceAndOilOBD(callletters, cmd_list, totalDistance, oil);
			        	            closeDialog('oboCentralControlOperation');
			        			}
		        	            console.log('totalDistance:'+totalDistance+';oil:'+oil);
			        		}
			        		//GPS回传函数
			        		gpshchs = function(){
			        			var cmd_list = [];//指令内容
			        			cmd_list.push({
				                    "pla": datagrid.getSelecteds()[0].plateNo,
				                    "callLetter": datagrid.getSelecteds()[0].callLetter,
				                    "cmdName": "OBD设备GPS回传设置"
				                });
			        			var isBack = $('input:radio:checked').val();
			        			var backMode = $("#sg-hc-select").val();
			        			var backValue =$("#sg-hc-input").val();
			        			
			        			if(isBack=="0"){
			        				backMode = 0;
			        				backValue = 0;
			        			}
			        			if(isBack=="1"){
			        				if(backMode=="1"){
			        					if(backValue==""||backValue==0){
			        						$.ligerDialog.warn('定时回传时长不能为空或为0!');
						                	return;
			        					}
			        				}else if(backMode=="2"){
			        					if(backValue==""||backValue==0){
			        						$.ligerDialog.warn('回传距离不能为空或为0!');
						                	return;
			        					}
			        				}
			        			}
			        			setGpsBackOBD(callletters, cmd_list, isBack, backMode, backValue);
			        			closeDialog('oboCentralControlOperation');
			        			console.log('isBack:'+isBack+';backMode:'+backMode+';backValue'+backValue);
			        		}
			        		
			            },
			            dataType:'json' 
			        });
			});
		}else{
			$.ligerDialog.warn('此设备非OBD设备,不能进行操作!');
		}
		
		clearAllMarker();
	}
}
//点击休眠唤醒设置
$("#sg-xx-sz").click(function(){
	//console.log(callletters);
	xmxxsz();
});
//点击OBD适配参数
$("#sg-spcs").click(function(){
	obdspcs();
})
//点击总里程 和油耗调用的是同一个方法
$(".youhao").click(function(){
	zlcyh();
})
//是否回传
$(":radio").click(function(){
	var radios = document.getElementsByName("hc-ra");
	 for(var i=0;i<radios.length;i++)
      {
        if(radios[i].checked==true)
        {
       	 if(radios[i].value==1){
       		 //alert("是")
       		 $("#sg-hc-select").removeAttr("disabled");
      		 $("#sg-hc-input").removeAttr("disabled");
       	 }else{
       		 //alert("否")
       		 $("#sg-hc-select").attr("disabled","disabled");
       		 $("#sg-hc-input").attr("disabled","disabled");
       	 }
        }
       }
});
//点击GPS回传
$("#xms").change(function(){
	$("#xms").val($(this).val());
});
$("#sg-hc-select").change(function(){
	$("#sg-hc-select").val($(this).val());
});
$("#sg-gps-hc").click(function(){
	gpshchs();
})
//子母机蓝牙连接断开上报频率设置
/*
$("#cmd_blueTooth_start").click(function () {
	alert(1);
    var cars = $("#timingReportCarlist2 li");
    var callletters = [];
    var cmd_list = [];//资料
    var flag = 1;//1为true 0为false
    var hour, min, sec, t;//t为秒
    var trg90_datas = [];//trg90导航专发

    if ($("#timingReportForSonUnit_hour2").val() == "") {
        hour = 0;
    } else {
        hour = parseInt($("#timingReportForSonUnit_hour2").val());
    }

    if ($("#timingReportForSonUnit_min2").val() == "") {
        min = 0;
    } else {
        min = parseInt($("#timingReportForSonUnit_min2").val());
    }

    if ($("#timingReportForSonUnit_sec2").val() == "") {
        sec = 0;
    } else {
        sec = parseInt($("#timingReportForSonUnit_sec2").val());
    }
    t = hour * 60 * 60 + min * 60 + sec;
    var isHaveTrg90 =false;
    $.each(cars, function (i, item) {
    	if($(item).find(".unitType").html().indexOf(trg90_unit_type)!=-1){
    		isHaveTrg90=true;
    	}
    	
        if ($(item).find(".preCmd").html() == 1) {//预存
            trg90_datas.push({
                "unit_id": $(item).find(".unitID").html(),
                "call_letter": $(item).find(".callLetter").html(),
                "cmd_id": "0x00E3",
                "send_params": t
            });
        } else {
            callletters.push($(item).find(".callLetter").html());
            cmd_list.push({
                "pla": $(item).find(".plateNo").html(),
                "callLetter": $(item).find(".callLetter").html(),
                "cmdName": "设置蓝牙上报频率"
            });
        }
    });
    IntervalBlueTooth(callletters, cmd_list,t);
    sendCMD_TRG90(trg90_datas);//发送trg指令
    //弹窗消失
    closeDialog('timingReportBlueTooth');
});
*/
/*
//删除定时报告
$(function(){
    $('#timingReportCarlist').on('click','li .scBtn',function(){
        $(this).parent().remove();
    });
    $('#misfireTimingReport').on('click','li .scBtn',function(){
        $(this).parent().remove();
    })
})
*/



