var historyid = '';

// 获取当天日期
function getNowDay() {
	var now = new Date();
	var year = now.getFullYear();
	var month = now.getMonth() + 1;
	var day = now.getDate();
	var hour = now.getHours();
	var minute = now.getMinutes();
	if (month < 10)
		month = "0" + month;
	if (day < 10)
		day = "0" + day;
	if (hour < 10)
		hour = "0" + hour;
	if (minute < 10)
		minute = "0" + minute;
	var nowday = year + "-" + month + "-" + day;
	return nowday;
}

// 获取页面上点击统计数据的类型
function getCountName(countType) {
	switch (countType) {
	case '10':
		return '车辆总数';
	case '11':
		return '在线车辆数';
	case '12':
		return '离线车辆数';
	case '13':
		return '当月新增车辆数';
	case '14':
		return '当月结清车辆数';
	case '15':
		return '高风险车辆数';
	case '16':
		return '逾期车辆数';
	case '17':
		return '5天不在线车辆数';
	case '18':
		return '当月新增上线车辆数';
	}
}

// 点击页面数量显示后跳到相应的列表(总数、在线、离线、当月新增、当月新增上线、当月结清、高风险、逾期、5天不在线)
$(function() {
	$("").click(
			function() {
				var count = parseInt($(this).find(".num").html());// 获取点击的页面的统计数据
				if (count > 0) {
					var countType = $(this).attr('');// 获取点击的是哪个统计数据
					var countName = getCountName(countType);
					var nowDate = getNowDay();
					newTab('server/indexCountInfo.html?countType=' + countType
							+ '&startDate=' + nowDate, countName, '0');
				}
			});
});

// 刷新统计数据
function setStatisticInfo(data) {
	$("#车辆总数").html(data.vehicleTotalCount);
	$("#在线车辆数").html(data.vehicleOnlineCount);
	$("#离线车辆数").html(data.vehicleOfflineCount);
	$("#当月新增车辆数").html(data.theMonthAddCount);
	$("#当月新增上线车辆数").html(data.theMonthAddOnlineCount);
	$("#当月结清车辆数").html(data.theMonthJieQingCount);
	$("#高风险车辆数").html(data.highRiskCount);
	$("#逾期车辆数").html(data.overTimeCount);
	$("#5天不在线车辆数").html(data.fiveDaysNotOnlineCount);
}

$(function() {
	$("nowDate").html(getNowDay());
	// 定时刷新
	var count = 1;
	function refreshData() {
		$.ajax({
			url : 'index',
			data : 'method=timerData',
			success : function(data) {
				setStatisticInfo(data.statisticInfo);
				setTimeout(refreshData, 5000);
			},
			dataType : 'json'
		});
		count++;
	}
	setTimeout(refreshData, 5000);
});

function closeDialogMe(obj) {
	var dialogId = $(obj).parents(".dn[id$='dialog']").attr("id");
	closeDialog(dialogId);
}