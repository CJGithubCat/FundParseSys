//指令
var history_list_markers = [];
var history_data = [];
var history_head = {
	label: "",
	numberPlate: "",
	callLetter: ""
};

function clearHistoryList(){
	for(var i = 0; i < history_list_markers.length; i++){
		map.removeMarker(history_list_markers[i]);
	}
	
	history_list_markers.splice(0, history_list_markers.length);
}

function initCmd(){
	var timeFormat = "yyyy-MM-dd HH:mm:ss";
	$('#play_history_start_time').datepicker({
		format: timeFormat//"yyyy-mm-dd"//
	});
	
	$('#play_history_end_time').datepicker({
		format: timeFormat//"yyyy-mm-dd"//
	});
	
	$("#cmd_play_history").click(function(e){
		$('#play_history_dlg .modal-title').text("历史回放查询(粤B.123456)");
		
		//$("#play_history_dlg_search_result").css("display", "none");
		//$("#play_history_dlg_play_btn").addClass("disabled");
		//$("#play_history_dlg_list_btn").addClass("disabled");
		
		$('#play_history_dlg').modal({
			show: true,
			backdrop: "static" 
		});
		
		//var now = new Date();
		//now.setHours(now.getHours() + 1);
		//$('#play_history_start_time').val(Util.formatDate(Util.getTodayStart(), timeFormat));
		//$('#play_history_end_time').val(Util.formatDate(now, timeFormat));
		if($('#play_history_start_time').val().length == 0){
			$('#play_history_start_time').val(Util.formatDate(Util.getTodayStart(), timeFormat));
		}
		
		if($('#play_history_end_time').val().length == 0){
			var now = new Date();
			now.setHours(now.getHours() + 1);
			$('#play_history_end_time').val(Util.formatDate(now, timeFormat));
		}
	});
	
	/**
	 * 查询
	 */


	$("#play_history_dlg_query_btn").click(function(e){
		var start = $('#play_history_start_time').val();
		var end = $('#play_history_end_time').val();
		$("#play_history_dlg_search_result").html('共0条数据');
		history_data =[];//清空
		if(start.length == 0){
			$('#play_history_start_time').focus();
			return;
		}
		
		if(end.length == 0){
			$('#play_history_end_time').focus();
			return;
		}
		
		if(start >= end){
			//alert("结束时间不能大于起始时间");
			$("#play_history_dlg_search_result").css("display", "block");
			$("#play_history_dlg_search_result").css("color", "#d2322d");
			$("#play_history_dlg_search_result").text("结束时间必须大于起始时间");
			return;
		}

		//正式用:
		GetHistoryGPS(start, end);

		//load data
		// var startTime = Util.parseDate(start, timeFormat);
		// var endTime = Util.parseDate(end, timeFormat);
		// var count = parseInt((endTime.getTime() - startTime.getTime()) / 30000);
		// var count = 0;

		// history_data = loadTestHistoryData(startTime, count);//这里可能要替换掉，替换成从通信中心取

		// history_head.numberPlate = "粤B.123451";//改掉
		// history_head.callLetter = "13912345001";//改掉
		
		//ok
		// $("#play_history_dlg_search_result").css("display", "block");
		
		// $("#play_history_dlg_play_btn").removeClass("disabled");
		// $("#play_history_dlg_list_btn").removeClass("disabled");
		
		// $("#play_history_dlg_search_result").css("color", "#47a447");
		// $("#play_history_dlg_search_result").text("共" + count + "条数据");
		history_head.numberPlate = $("#historyCallback_pla").val();//"粤B.123451";
		history_head.label = $("#historyCallback_pla").val();//"粤B.123451";
		history_head.callLetter = $("#historyCallback_callLetter").val();//"13912345001";
	});
	
	
	/**
	 * 播放
	 */
	$("#play_history_dlg_play_btn").click(function(){
		if(!history_data){
			$("#play_history_dlg_search_result").css("display", "block");
			$("#play_history_dlg_search_result").css("color", "#d2322d");
			$("#play_history_dlg_search_result").text("请先查询历史数据");
			return;
		}
		
		$('#play_history_dlg').modal('hide');
		map.playHistory(history_head, history_data, newHistoryCallback, playingHistoryCallback, closeHistoryCallback);
	});
	
	/**
	 * 列点
	 */
	$("#play_history_dlg_list_btn").click(function(){
		if(!history_data){
			$("#play_history_dlg_search_result").css("display", "block");
			$("#play_history_dlg_search_result").css("color", "#d2322d");
			$("#play_history_dlg_search_result").text("请先查询历史数据");
			return;
		}
		
		$('#play_history_dlg').modal('hide');
		startHistoryList();
	});
	
	//var history_list_task = null;
	var cancel_history_list_task = false;
	
	var history_list_point_icon = {
		url: "images/circle_18.png",
		width: 18,
		height: 18,
		left: 0,
		top: 0,
		anchorx: -9,
		anchory: -9
	};
	
	var progressbar = $("#play_history_list_dlg .progress-bar");
	var progressbar_txt = $("#play_history_list_dlg .history-list-progress-info");
	
	function startNextTask(startIndex, endIndex){
		window.setTimeout(function(){
			for(var i = startIndex; i < endIndex; i++){
				if(cancel_history_list_task){
					break;
				}
				
				var data = history_data[i];
				var show = i + 1;
				var ax = -4;
				if(show >= 10000){
					ax = -20;
				}else if(show >= 1000){
					ax = -16;
				}else if(show >= 100){
					ax = -12;
				}else if(show >= 10){
					ax = -8;
				}
				
				var m = map.newSimpleMarker({
					lon: data.lon,
					lat: data.lat,
					title: data.gpsTime,
					icon: history_list_point_icon,
					label: {
						text: show,
						anchorx: ax,
						anchory: -8,
						style: {
							fontSize: "12px",
							cursor: "pointer",
							color: "#000000"
						}
					}
				});
				
				map.addMarker(m);
				history_list_markers.push(m);
			}
			
			if(cancel_history_list_task){
				clearHistoryList();
				progressbar.css("width", "0%");
				window.setTimeout(function(){
					$('#play_history_list_dlg').modal('hide');
				}, 500);
				return;
			}
			
			var p = parseInt(100 * endIndex / history_data.length);
			var per = p + "%";
			progressbar.css("width", per);
			progressbar_txt.text(per + "(" + endIndex + "/" + history_data.length + ")");
			
			//var data = history_data[endIndex - 1];
			//map.setCenter(data.lon, data.lat);
			
			if(endIndex < history_data.length){
				var next_start = endIndex;
				var next_end = Math.min(history_data.length, next_start + 200);
				startNextTask(next_start, next_end);
			}else{
				if(history_data.length > 0){
					var data = history_data[0];
					map.setCenter(data.lon, data.lat);
				}
				$('#play_history_list_dlg').modal('hide');
			}
		}, 200);
	}
	
	function startHistoryList(){
		clearHistoryList();
		progressbar.css("width", "0%");
		$('#play_history_list_dlg').modal({
			show: true,
			backdrop: "static" 
		});
		
		cancel_history_list_task = false;
		var start = 0;
		var end = Math.min(history_data.length, 200);
		startNextTask(start, end);
	}
	
	function cancelHistoryList(){
		cancel_history_list_task = true;
	}
	
	$("#play_history_dlg_list_cancel_btn").click(function(){
		cancelHistoryList();
	});
}

function loadTestHistoryData(startTime, count){
	var gpsTime = null;
	var stamp = null;
	
	var time = startTime.getTime();
	
	var tIndex = 0;
	function reloadTime(){
		//var _t1 = time + parseInt(Math.random() * 10000 + 10000 * tIndex);
		var _t1 = time + 30000 * tIndex;
		var _t2 = _t1 + parseInt(Math.random() * 10000 + 10000);
		var d1 = new Date(_t1);
		var d2 = new Date(_t2);
		
		gpsTime = d1.getFullYear() + "-" + getTStr(d1.getMonth() + 1) + "-" + getTStr(d1.getDate()) + " " + getTStr(d1.getHours()) + ":" + getTStr(d1.getMinutes()) + ":" + getTStr(d1.getSeconds());
		stamp = d2.getFullYear() + "-" + getTStr(d2.getMonth() + 1) + "-" + getTStr(d2.getDate()) + " " + getTStr(d2.getHours()) + ":" + getTStr(d2.getMinutes()) + ":" + getTStr(d2.getSeconds());
		tIndex++;
	}
	
	var temp_lon = 116.391453;
	var temp_lat = 39.903146;
	
	var lonlat_status = 0; //0:>   1: ^  
	var status_index = 0;
	function reloadLonlat(){
		status_index++;
		if(status_index >= 10){
			status_index = 1;
			lonlat_status = 1 - lonlat_status;
		}
		
		if(lonlat_status == 0){
			var dlon = (Math.random() * 10 + 1) / 10000;
			temp_lon += dlon;
		}else{
			var dlat = (Math.random() * 10 + 1) / 10000;
			temp_lat += dlat;
		}
	}
	
	var data = [];
	for(var i = 1; i <= count; i++){
		var status = [22,25,60];
		if(i % 80 == 0){
			status.push(23);
		}else{
			status.push(33);
		}
		
		var isAlarm = 0;
		if(i % 120 == 0){
			isAlarm = 1;
			status.push(6);
		}
		
		var isLoc = 1;
		if(i % 113 == 0){
			isLoc = 0;
		}
		
		var oil = Math.round(100 * (95 - (90 / count) * i)) / 100 + "%";
		
		reloadTime();
		
		var opts = {
			isHistory: 1,
			referencePosition: '北京市',
			isLoc: isLoc,
			oil: oil,
			temperature: Math.round(100 * Math.random()) / 10 + 20,
			lon: Math.round(temp_lon * 100000) / 100000,
			lat: Math.round(temp_lat * 100000) / 100000,
			speed: parseInt(Math.random() * 30),
			course: lonlat_status == 0? 90: 0,
			gpsTime: gpsTime,
			stamp: stamp,
			isAlarm: isAlarm,
			status: status	
		};
		data.push(opts);
		
		reloadLonlat();
	}
	
	return data;
}

/**
 * 将历史位置信息写入history_data
 * gpses	@array
 */
function loadHistoryData(gpses, alarmid, alarmname){
	var callLetter="";
    if(gpses!=null && gpses!=""){
	   callLetter= gpses[0].callLetter;
    }
    var unitType = GetUnitTypeId(callLetter);
	for(var i=0; i<gpses.length; i++) {
		var gps = gpses[i];
		var gpsTime = new Date(parseInt(gps.baseInfo.gpsTime));
		var gpsTimeF = DateTranslate(gpsTime);
		var stamp = SEGUtil_WS.date_2_string(new Date, "yyyy-MM-dd hh:mm:ss");
		var _lon = parseFloat(gps.baseInfo.lng)/1000000;
		var _lat = parseFloat(gps.baseInfo.lat)/1000000;
		var flag = 0;
		if (gps.baseInfo.loc) {
			flag = 1;
		} else {
			var BaseStationAddress = gps.baseInfo.address;
			/*if(BaseStationAddress==null||BaseStationAddress.lng==null||BaseStationAddress.lat==null||BaseStationAddress.lng==0||BaseStationAddress.lat==0){
				flag = 0;
			}else{
				_lon = BaseStationAddress.lng/1000000;
				_lat = BaseStationAddress.lat/1000000;
				flag = 2;
			}*/
			if(BaseStationAddress==null||BaseStationAddress.lng==null||BaseStationAddress.lat==null||BaseStationAddress.lng==0||BaseStationAddress.lat==0){
				if(gps.baseInfo.baseStations.length>0){
					$.ajax({
		    			url:'../lbs/getMes',
		    			method:"POST",
		    			async: false,
		    			dataType:"json",
		    			data:{'baseGps':JSON.stringify(gps.baseInfo.baseStations)},
		    			success: function(data){
		    				console.log(data.datas);
		    				if(data.datas==null){
		    					flag = 0;
		    				}else{
			    				var baseLon=data.datas.lon;
			    				var baseLat=data.datas.lat;
			    				if(baseLon==0 || baseLon==null||baseLat==0 || baseLat==null){
			    					flag = 0;
			    				}else{
			    					_lon = baseLon;
			    					_lat = baseLat;
			    					flag = 2;
			    				}
		    				}
		    			},
		    			error: function(XMLHttpRequest, textStatus, errorThrown) {
		    				console.log(XMLHttpRequest.status);
		    			}
					});
				}
			}else{
				_lon = BaseStationAddress.lng/1000000;
				_lat = BaseStationAddress.lat/1000000;
				flag = 2;
			}
		}
		var speed = typeof(gps.baseInfo.speed)=='undefined' ? '' : gps.baseInfo.speed/10;
		var course = gps.baseInfo.course;
		var status = gps.baseInfo.status;	
		var isLoc = gps.baseInfo.loc;
		//var oil = gps.baseInfo.obdInfo ? gps.baseInfo.obdInfo.oilPercent + '%' : '';
		//总里程   liujie  2017-05-23
		var totalDistance = (gps.baseInfo.totalDistance/1000).toFixed(2);
		
		//剩余油量 修改为获取升 liujie  2017-05-23
		var oil="";
		
		if(gps.baseInfo.obdInfo!=null){
			speed = (gps.baseInfo.obdInfo.speed/10);
			if(gps.baseInfo.obdInfo.dashboardDistance!= null &&gps.baseInfo.obdInfo.dashboardDistance != undefined && gps.baseInfo.obdInfo.dashboardDistance !=0){
				totalDistance = (gps.baseInfo.obdInfo.dashboardDistance/1000).toFixed(2);
			}else if(gps.baseInfo.obdInfo.totalDistance!= null &&gps.baseInfo.obdInfo.totalDistance != undefined && gps.baseInfo.obdInfo.totalDistance !=0){
				totalDistance = (gps.baseInfo.obdInfo.totalDistance/1000).toFixed(2);
			}
			oil=(gps.baseInfo.obdInfo.remainOil/100).toFixed(2);
		}
		var referencePosition = gps.referPosition ? gps.referPosition.city : '';
		var temperature = gps.baseInfo.temperature1;//TODO:这里应该是1还是2？
		var alarmidE;
		if (alarmid==0||alarmid==null) {alarmidE=0;}else{alarmidE=1;};
		var isAlarm = alarmidE;
		//2016-08-16 刘杰 新版本终端状态位中存储了acc时长
		var accTimeLen=gps.baseInfo.AccTimeLen;
		var electricity=0;
		if(unitType==true){
			electricity=100
		}else{
		if(gps.baseInfo.beijingInfo!=null){
			if(gps.baseInfo.beijingInfo.electricity!=null){
				electricity = gps.baseInfo.beijingInfo.electricity;
			}
		 }
		}
		// var isAlarm = typeof(gps.baseInfo.isAlarm)!='undefined' ? gps.baseInfo.isAlarm : 0;	//TODO:没看到该属性！！！
		//而且这么取也取不到！！！
		//var alarmidE;
		//if (alarmid==0||alarmid==null) {alarmidE=0;}else{alarmidE=1;};
		
		var opts = {
			callLetter: history_head.callLetter,
			numberPlate : history_head.numberPlate,
			isHistory: 1,
			referencePosition: referencePosition, 
			isLoc: isLoc,
			oil: oil,
			temperature: temperature,
			lon: _lon,
			flag: flag,
			lat: _lat,
			speed:parseFloat(speed),
			course: course,
			gpsTime: gpsTimeF,
			stamp: stamp,
			isAlarm: isAlarm, 
			status: status,		
			electricity:electricity,
			accTimeLen:accTimeLen,
			totalDistance:totalDistance,
		};
		history_data.push(opts);
	};
}