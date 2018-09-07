var SEGMAPHOOK = {
	getVehicleBackgroundXIndex: function(course){
		var _course = parseInt((course % 360) / 10);
		return _course;
	},
	
	getVehicleBackgroundYIndex: function(speed, gpsTime, isAlarm, status, isHistory){
		// 警情-长时间未上报-熄火-低速-正常
		var _isAlarm = parseInt(isAlarm);
		if (_isAlarm) {
			return 2;
		}

		// 不是历史回放才判断是否为长时间未上报
		if (!isHistory) {
			if(!gpsTime) return;
			var idx = gpsTime.indexOf(".");
			if (idx > 0) {
				gpsTime = gpsTime.substring(0, idx);
			}
			var date = Date.parse(gpsTime.replace(/-/g, "/"));
			var curdate = new Date().getTime();
			
			var dtime = (curdate - date) / 60000; // 分钟
//			if (dtime > 30 || dtime < -10000) {
//				return 3;
//			}
			var dhour = dtime / 60;
			if (dhour > 30 || dhour < -10000) {
				return 3;
			}
		}
		
		if (status) {
			if(typeof(status) == "string"){
				status = status.split(",");
			}
			
			if(SEGUtil.indexOfArray(status, 23) != -1){
				return 4;
			}
		}

		if (typeof speed != "undefined") {
			if (speed <= 10) {
				return 1;
			}

			return 0;
		}
		
		return 3;
	}
};

SEGUtil.getVehicleBackground = function(course, speed, time, isAlarm, status, isHistory){
	var x_index = SEGMAPHOOK.getVehicleBackgroundXIndex(course);
	var y_index = SEGMAPHOOK.getVehicleBackgroundYIndex(speed, time, isAlarm, status, isHistory);
	var x = x_index * 25;
	var y = y_index * 25;
	var bg = "url("+SEGUtil.imageRootDir+"/cars_d36_25.png) no-repeat -" + x + "px -" + y + "px";
	return bg;
};

function getRefPosition(rowIndex){//百度api获取参考位置
    var datas = $("#main-grid").ligerGetGridManager().getRow(rowIndex);
	    var ggPoint = new BMap.Point(datas.lon,datas.lat);
	    var pointArr = [];
	    pointArr.push(ggPoint);
	    new BMap.Convertor().translate(pointArr, 1, 5, function(data){
	      	new BMap.Geocoder().getLocation(new BMap.Point(data.points[0].lng,data.points[0].lat), function(rs){
	      		var addComp = rs.addressComponents;
	      		address = addComp.province + addComp.city + addComp.district + addComp.street + addComp.streetNumber;
	   		    if(address == ''){
	   			   address = '参考位置为空';
	   		    } 	   		    
	       	    var id = '#position'+ rowIndex;
	      	    $(id).html(address);
	      	})
	    });
  }

SEGUtil.getVehicleMarkerHoverInfo = function(opts,callback){
	var numberPlate = SEGUtil.parseNull(opts.numberPlate);
	var gpsTime = SEGUtil.parseNull(opts.gpsTime);
	var speed = SEGUtil.parseNull(opts.speed);
	var driverName = SEGUtil.parseNull(opts.driver_name);
	var imei = SEGUtil.parseNull(opts.imei);
	
	GPSUtil.gpsToBaiduGps(SEGUtil.parseNull(opts.lon),SEGUtil.parseNull(opts.lat),1,5);
	var lon = SEGUtil.parseNull(opts.lon);
	var lat = SEGUtil.parseNull(opts.lat);
	var lon1 = lon.toPrecision(8);
	var lat1 = lat.toPrecision(8);
	
	
	var totalDistance;
	if(opts.totalDistance==null){
		totalDistance=0;
	}else{
		totalDistance=opts.totalDistance;
	}
//	var referencePosition = SEGUtil.parseNull(opts.referencePosition);
	var status = "";
	var addressArray =[];
	//通过百度api获取参考位置
	var ggPoint = new BMap.Point(lon,lat);
    var pointArr = [];
    pointArr.push(ggPoint);
    new BMap.Convertor().translate(pointArr, 1, 5, function(data){
    	if(data.status == 0){
    		new BMap.Geocoder().getLocation(new BMap.Point(data.points[0].lng,data.points[0].lat), function(rs){
    			//2016-08-12 刘杰 调用参考位置采用别的方式
//    			var addComp = rs.addressComponents;
//          	address = addComp.province + addComp.city + addComp.district + addComp.street + addComp.streetNumber;
//       		if(address == ''){
//       			address = '参考位置为空';
//       		} 	   		    
//       		callback(getHTML(address));
       		    
    			var lonlat=data.points[0].lat+','+data.points[0].lng;
 	      		var lonlaturl="http://api.map.baidu.com/geocoder/v2/?ak=oRTEhIarHzXK52qPGuFKQGAPqKIrnmdn&callback=renderReverse&location="+lonlat+"&output=json&pois=1";
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
		  	   		    callback(getHTML(address));
 	 				},
 	 				error:function(error){
 	 					console.log('鼠标移上地图图标请求参考位置出错!');
 	 				},
 	 				dataType:'jsonp'
 	 			});
          	});
		}else{
			address = '参考位置为空';
    		callback(getHTML(address));
		}
    });
   // var geoc = new BMap.Geocoder();   
   // geoc.getLocation(point, function(rs){
   //	var addComp = rs.addressComponents;
	//	address = addComp.province + addComp.city + addComp.district + addComp.street + addComp.streetNumber;
	//	if(address == ''){
	//		address = '参考位置为空';
	//	}
	//	callback(getHTML(address));
	//});
    function getHTML(address){
    	//状态特殊处理，只显示点火、熄火状态
    	var statusArr = new Array();
    	for ( var status in opts.status) {
    		if(opts.status[status] ==23){
    			statusArr.push(23);
    			break;
    		}else if(opts.status[status] ==33){
    			statusArr.push(33);
    			break;
    		}
    	}
    	status = SEGUtil.parseVehicleStatus(statusArr);
    	var val = Date.parse(gpsTime);
    	var newDate = new Date(val);
    	var today = new Date;
        var accTimeLen=opts.accTimeLen;
//    	var time =((today-newDate)/60000).toFixed(0);
        var beginTime=new Date(val-accTimeLen*1000).Format('yyyy-MM-dd hh:mm:ss');
        var time =(accTimeLen/60).toFixed(0);
    	if(time>=60){
    		var min =time%60;
    		var hour=time/60;
    		hour =parseInt(hour);
    		if(hour>=24){
    		    var H=hour%24;
    			var day =parseInt(hour/24);
    			if(H==0){
    				if(min==0){
    					time=day+"天"
    				}else{
    					time=day+"天"+min+"分"
    				}
    			}else{
    				if(min==0){
    					time=day+"天"+H+"小时"
    				}else{
    					time=day+"天"+H+"小时"+min+"分"
    				}
    			}
    		}else{
    			if(min==0){
    				time=hour+"小时";
    			}else{
    				time=hour+"小时"+min+"分";
    			}
    		}
    	}else{
    		time=time+"分";
    	}
    	var referencePosition = SEGUtil.parseNull(opts.referencePosition);
    	var html = "<div style='width:100%;text-align:center '>" + numberPlate + "</div>";
    	html += "<hr size=1 style='margin:2px 0 5px 0;width:200px'>";
    	html += "<p>车主:" + driverName  +"</p>";
    	html += "<p>设备号:" + imei + "</p>";
    	html += "<p>状态: " + status +"("+time+")"+ "</p>";
    	html += "<p>开始时间: " + beginTime + "</p>";
    	html += "<p>最后定位时间: " + gpsTime + "</p>";
    	html += "<p>速度: " + speed + "千米/小时</p>";
    	html += "<p>总里程: " +totalDistance + "千米</p>";
    	//2016-08-12 刘杰 应聂总要求去掉经纬度
    	//html += "<p>经纬度:" + lon1+","+lat1 + "</p>";
    	html += "<p>设备位置:<span id='copyContent'> " + address + "</span><a class='copybtn' style='cursor: pointer;color:#0062CC'>复制</a></p>";
    	for(var i=0;i<addressArray.length;i++){
			html += "<p class='hideP' style='display:none '><span style='color:blue'>位置"+(i+1)+"</span>: " + addressArray[i] + "</p>";
		}
    	var more = "<a onclick='moreAddress()' class='moreA' style='color:#28c328'>更多...</a>";
    	var packUp = "<a onclick='packUpAdd()' class='packUp' style='display:none'>收起</a>";
    	
    	return html+more+packUp;
    }
	return "...请求中...";
};
function moreAddress(){
	$(".hideP").show();
	$(".moreA").hide();
	$(".packUp").show();
};
function packUpAdd(){
	$(".hideP").hide();
	$(".moreA").show();
	$(".packUp").hide();
};
