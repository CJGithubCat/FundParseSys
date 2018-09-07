/********************************************************
 *文件功能：实现定时刷新任务对首页的数据更新； 
 ********************************************************/
var indexIntvel=null;
/**
 * 函数功能：刷新首页显示数据
 * **/
function refreshInfo(){
	//定时的请求首页统计数据
	$.ajax({
		url:'../home/count',
		success:function(data){
			setIndexCountData(data);
		}
	});
	if(null==indexIntvel){
		count = 0;
		indexIntvel = setInterval(function(){refreshInfo()},300000);
	}
}

/***
 * 函数功能：处理首页超链接的onclick函数
 * */
function dealIndexOnClick(data){
	var carData=data.datas;
	console.log(carData);
	var companyType = carData.companyType;
	var xgs_gzd_daishenhe = carData.xgs_gzd_daishenhe;
	var jyz_gzd_daishenhe = carData.jyz_gzd_daishenhe;
	var xgs_khtzd_weixifa = carData.xgs_khtzd_weixifa;
	var jyz_khtzd_weixifa = carData.jyz_khtzd_weixifa;
	var jyz_khtzd_daiqueren= carData.jyz_khtzd_daiqueren;
	var xgs_khtzd_daiqueren= carData.xgs_khtzd_daiqueren;
	var xgs_gzd_weitongguo = carData.xgs_gzd_weitongguo;
	var jyz_gzd_weitongguo = carData.jyz_gzd_weitongguo;
	
	if(parseInt(companyType) < 0){
		console.log("The companyType is error!");
	}else if(parseInt(companyType) == 0){
		if(parseInt(xgs_gzd_daishenhe) != 0){
			$('#xgsGzdDaishenheFlag').attr('onclick','newTab(\'bmsalarynoticemanage.html\',\'部门工资单信息\',0,true)');
		}else{
			$('#xgsGzdDaishenheFlag').removeAttr('onclick');
		}
		
		if(parseInt(jyz_gzd_daishenhe) != 0){
			$("#jyzGzDaishenheFlag").attr('onclick','newTab(\'jyzsalarynoticemanage.html\',\'加油站工资单信息\',0,true)');
		}else{
			$("#jyzGzDaishenheFlag").removeAttr('onclick');
		}
		
		if(parseInt(xgs_khtzd_weixifa) != 0){
			$("#xgsKhtzdWeixifaFlag").attr('onclick','newTab(\'bmkhtzdmanage.html\',\'部门考核通知单信息\',0,true)');
		}else{
			$("#xgsKhtzdWeixifaFlag").removeAttr('onclick');
		}
		
		if(parseInt(jyz_khtzd_weixifa) != 0){
			$("#jyzKhtzdWeixifaFlag").attr('onclick','newTab(\'jyzkhtzdmanage.html\',\'加油站考核通知单信息\',0,true)');
		}else{
			$("#jyzKhtzdWeixifaFlag").removeAttr('onclick');
		}
	}else if(parseInt(companyType) == 1 || parseInt(companyType) == 2 || parseInt(companyType) == 3 || parseInt(companyType) == 4){
		if(parseInt(jyz_gzd_daishenhe) != 0){
			$("#jyzGzDaishenheFlag").attr('onclick','newTab(\'jyzsalarynoticemanage.html\',\'加油站工资单信息\',0,true)');
		}else{
			$("#jyzGzDaishenheFlag").removeAttr('onclick');
		}
		
		if(parseInt(jyz_khtzd_daiqueren) != 0){
			$("#jyzKhtzdDaiquerenFlag").attr('onclick','newTab(\'jyzkhtzdmanage.html\',\'加油站考核通知单信息\',0,true)');
		}else{
			$("#jyzKhtzdDaiquerenFlag").removeAttr('onclick');
		}
		
		if(parseInt(xgs_khtzd_daiqueren) != 0){
			$("#xgsKhtzdDaiquerenFlag").attr('onclick','newTab(\'bmkhtzdmanage.html\',\'部门考核通知单信息\',0,true)');
		}else{
			$("#xgsKhtzdDaiquerenFlag").removeAttr('onclick');
		}
		
		if(parseInt(xgs_gzd_weitongguo) != 0){
			$("#xgsGzdWeitongguoFlag").attr('onclick','newTab(\'bmsalarynoticemanage.html\',\'部门工资单信息\',0,true)');
		}else{
			$("#xgsGzdWeitongguoFlag").removeAttr('onclick');
		}
	}else if(parseInt(companyType) == 5){
		if(parseInt(jyz_khtzd_daiqueren) != 0){
			$("#jyzKhtzdDaiquerenFlag").attr('onclick','newTab(\'jyzkhtzdmanage.html\',\'加油站考核通知单信息\',0,true)');
		}else{
			$("#jyzKhtzdDaiquerenFlag").removeAttr('onclick');
		}
		
		if(parseInt(jyz_gzd_weitongguo) != 0){
			$("#jyzGzdWeitongguoFlag").attr('onclick','newTab(\'jyzsalarynoticemanage.html\',\'加油站工资单信息\',0,true)');
		}else{
			$("#jyzGzdWeitongguoFlag").removeAttr('onclick');
		}
	}
}

/***
 * 函数功能：设置统计数据
 * 入参：包含所有统计数据的对象；
 * */
function setIndexCountData(data){
	//处理点击的跳转内容
	dealIndexOnClick(data);
	//刷新页面数据
	var carData=data.datas;
	console.log(carData);
	var companyType = carData.companyType;
	var xgs_gzd_daishenhe = carData.xgs_gzd_daishenhe;
	var jyz_gzd_daishenhe = carData.jyz_gzd_daishenhe;
	var xgs_khtzd_weixifa = carData.xgs_khtzd_weixifa;
	var jyz_khtzd_weixifa = carData.jyz_khtzd_weixifa;
	var jyz_khtzd_daiqueren= carData.jyz_khtzd_daiqueren;
	var xgs_khtzd_daiqueren= carData.xgs_khtzd_daiqueren;
	var xgs_gzd_weitongguo = carData.xgs_gzd_weitongguo;
	var jyz_gzd_weitongguo = carData.jyz_gzd_weitongguo;
	
	if(parseInt(companyType) < 0){
		console.log("The companyType is error!");
	}else if(parseInt(companyType) == 0){
		if(parseInt(xgs_gzd_daishenhe) < 0){
			$("#xgs_gzd_daishenhe").html(getNumStr("0"));
		}else{
			var str1 = getNumStr(xgs_gzd_daishenhe);
			$("#xgs_gzd_daishenhe").html(str1);
		}
		
		if(parseInt(jyz_gzd_daishenhe) < 0){
			$("#jyz_gzd_daishenhe").html(getNumStr("0"));
		}else{
			var str1 = getNumStr(jyz_gzd_daishenhe);
			$("#jyz_gzd_daishenhe").html(str1);
		}
		
		if(parseInt(xgs_khtzd_weixifa) < 0){
			$("#xgs_khtzd_weixifa").html(getNumStr("0"));
		}else{
			var str1 = getNumStr(xgs_khtzd_weixifa);
			$("#xgs_khtzd_weixifa").html(str1);
		}
		
		if(parseInt(jyz_khtzd_weixifa) < 0){
			$("#jyz_khtzd_weixifa").html(getNumStr("0"));
		}else{
			var str1 = getNumStr(jyz_khtzd_weixifa);
			$("#jyz_khtzd_weixifa").html(str1);
		}
	}else if(parseInt(companyType) == 1 || parseInt(companyType) == 2 || parseInt(companyType) == 3 || parseInt(companyType) == 4){
		if(parseInt(jyz_gzd_daishenhe) < 0){
			$("#jyz_gzd_daishenhe").html(getNumStr("0"));
		}else{
			var str1 = getNumStr(jyz_gzd_daishenhe);
			$("#jyz_gzd_daishenhe").html(str1);
		}
		
		if(parseInt(jyz_khtzd_daiqueren) < 0){
			$("#jyz_khtzd_daiqueren").html(getNumStr("0"));
		}else{
			var str1 = getNumStr(jyz_khtzd_daiqueren);
			$("#jyz_khtzd_daiqueren").html(str1);
		}
		
		if(parseInt(xgs_khtzd_daiqueren) < 0){
			$("#xgs_khtzd_daiqueren").html(getNumStr("0"));
		}else{
			var str1 = getNumStr(xgs_khtzd_daiqueren);
			$("#xgs_khtzd_daiqueren").html(str1);
		}
		
		if(parseInt(xgs_gzd_weitongguo) < 0){
			$("#xgs_gzd_weitongguo").html(getNumStr("0"));
		}else{
			var str1 = getNumStr(xgs_gzd_weitongguo);
			$("#xgs_gzd_weitongguo").html(str1);
		}
	}else if(parseInt(companyType) == 5){
		if(parseInt(jyz_khtzd_daiqueren) < 0){
			$("#jyz_khtzd_daiqueren").html(getNumStr("0"));
		}else{
			var str1 = getNumStr(jyz_khtzd_daiqueren);
			$("#jyz_khtzd_daiqueren").html(str1);
		}
		$("#jyzKhtzdDaiquerenLi").css("width","50%");
		
		if(parseInt(jyz_gzd_weitongguo) < 0){
			$("#jyz_gzd_weitongguo").html(getNumStr("0"));
		}else{
			var str1 = getNumStr(jyz_gzd_weitongguo);
			$("#jyz_gzd_weitongguo").html(str1);
		}
		$("#jyzGzdWeitongguoLi").css("width","50%");
	}
}
/***
 * 函数功能：解析统计数字为一位一位的存到数组里面
 * 入参：数字
 * */
function parseCountNum(countNum){
	var count = countNum+'';
	var numList = [];
	for(var i = 0;i<count.length;i++){
		numList.push(count.substring(i,i+1));
	}
//	while(numList.length < 4){
//		numList.unshift('0');
//	}
	return numList;
}
/**
 * 函数功能：组装需要设置的数字信息；
 * 输入参数：数组
 * */
function makeImgNum(numList){
	var imgStr ='';
	$.each(numList,function(i,n){
		imgStr +='<img src="../images/shouye/'+n+'.png">';
	});
	return imgStr;
}
/**
 *函数功能：设置统计数据字符串;
 *输入参数：获取的统计数据；
 * */
function getNumStr(countNum){	
	var numlist = parseCountNum(countNum);
	var str = makeImgNum(numlist);
	str = '<span>'+ str + '</span>';
	return str;
}
/**
 * 函数功能：定时刷新首页显示数量
 * */
(function($) {
	setTimeout('',300000);
	refreshInfo();
})(jQuery);