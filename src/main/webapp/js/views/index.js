var historyid = 0;

(function($) {
	//获取天气数据
	(function findWeather() {
		var cityUrl = 'http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=js';
		$.getScript(cityUrl, function(script, textStatus, jqXHR) {
			var citytq = remote_ip_info.city;// 获取城市

			var url = "http://php.weather.sina.com.cn/iframe/index/w_cl.php?code=js&city="
					+ citytq + "&day=0&dfc=3";
			$.ajax({
				url : url,
				dataType : "script",
				scriptCharset : "gbk",
				success : function(data) {
					var _w = window.SWther.w[citytq][0];
					var _f = _w.f1 + "_0.png";
					if (new Date().getHours() > 17) {
						_f = _w.f2 + "_1.png";
					}
					var img = "<img width='24px' height='24px' src='http://i2.sinaimg.cn/dy/main/weather/weatherplugin/wthIco/20_20/"
							+ _f + "' />";
					var temperature = "<span style='font-size: 16px'>" + _w.t1
							+ "-" + _w.t2 + "℃  " + "</span>";
					var wea = "<span style='font-size: 12px'>" + _w.s1
							+ "</span>"
					var tq = img + temperature + wea;
					$('#weather').html(tq);
				}
			});

		});
	})();
	//获取今天的日期
	var nowday = getNowDay();
	$('#nowday').text("今天(" + nowday + ")");
})(jQuery);                   

// 查询首页显示数据
function queryIndexData() {
	var reqMethod = getStatisticsInfoByType("reqMethod", statisticsType);
	var spanId = getStatisticsInfoByType("spanId", statisticsType);
	if (!reqMethod || !spanId)
		return;
	var url = 'gps/data/indexCount';// url
	var params = {};// 参数
	$.getJSON(api_url, params, function(result) {
		$.each(result.datas, function(i, item) {
			var vehicleTotalCount = 0;// 车辆总数
			var vehicleOnlineCount = 0;// 在线车辆数
			var vehicleOfflineCount = 0;// 不在线车辆数
			var theMonthAddCount = 0;// 当月新增车辆数
			var theMonthAddOnlineCount = 0;// 当月新增上线车辆数
			var theMonthJieQingCount = 0;// 当月结清车辆数
			var highRiskCount = 0;// 高风险车辆数
			var overTimeCount = 0;// 逾期车辆数
			var fiveDaysNotOnlineCount = 0;// 五天无数据车辆数
			vehicleTotalCount = item.vehicleTotalCount;
			vehicleOnlineCount = item.vehicleOnlineCount;
			vehicleOfflineCount = item.vehicleOfflineCount;
			theMonthAddCount = item.theMonthAddCount;
			theMonthAddOnlineCount = item.theMonthAddOnlineCount;
			theMonthJieQingCount = item.theMonthJieQingCount;
			highRiskCount = item.highRiskCount;
			overTimeCount = item.overTimeCount;
			fiveDaysNotOnlineCount = item.overTimeCount;

		});
	});
};


// 获取当天日期
function getNowDay() {
	var now = new Date();
	var year = now.getFullYear();
	var month = now.getMonth() + 1;
	var day = now.getDate();
	var hour = now.getHours();
	var minute = now.getMinutes();
	if (month < 10) {
		month = "0" + month;
	}
	if (day < 10) {
		day = "0" + day;
	}
	if (hour < 10) {
		hour = "0" + hour;
	}
	if (minute < 10) {
		minute = "0" + minute;
	}
	var nowday = year + "-" + month + "-" + day;
	return nowday;
}
