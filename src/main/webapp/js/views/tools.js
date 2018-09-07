//播放报警音效
function playSound(src) {
	var _s;
	if (navigator.appName == "Microsoft Internet Explorer") {
		_s = document.getElementById('snd');
	} else {
		_s = document.getElementById('sndXXX');
	}    
	_s.src = src;
}


function closeWarningPanel() {
	playSound("");
	$(".LowRightCornerWarning").css("bottom", "-160px").hide();
}


//跳到指定选项卡
function showTab(tabid){
	$("#"+tabid).click();
	$("#"+tabid).parent().show();
}

//隐藏指定选项卡,并跳到主页
function hideTab(tabid){
	$("li a[href='#home']").click();
	$("#"+tabid).parent().hide();
}

//关闭指定选项卡,并跳到主页
function closeTab(tabid){
	$("li a[href='#home']").click();
	$("#"+tabid).parent().hide();
	$("#indexTabs li:eq(1)").css("display", "none");//隐藏选项卡标签
	if(gpsTaskInterval)	{
		clearInterval(gpsTaskInterval);//清除gpstask轮询
		gpsTaskInterval = null;
		historyid = 0;
	}
}



//将浮点数四舍五入，取小数点后2位，如果不足2位则补0,这个函数返回的是字符串的格式
function changeTwoDecimal(floatvar){
	var f_x = parseFloat(floatvar);
	if (isNaN(f_x)){
		return false;
	}
	f_x = Math.round(f_x*100)/100;
	var s_x = f_x.toString();
	var pos_decimal = s_x.indexOf('.');
	if (pos_decimal < 0){
		pos_decimal = s_x.length;
		s_x += '.';
	}
	while (s_x.length <= pos_decimal + 2){
		s_x += '0';
	}
	return s_x;
}

//JS获取n至m随机整数
function rd(n,m){
    var c = m-n+1;  
    return Math.floor(Math.random() * c + n);
}

//设置圆圈里面警情的数量
function setWarningCount(divId,count){
	var divEle = $("#" + divId);
	var currCount = divEle.find('.num').html();
	if (currCount != count) {//报警数量改变时才处理
		divEle.find('.num').html(count);
	}
}

var keys = [37, 38, 39, 40];

function preventDefault(e) {
  e = e || window.event;
  if (e.preventDefault)
      e.preventDefault();
  e.returnValue = false;  
}

function keydown(e) {
    for (var i = keys.length; i--;) {
    	var code = e.keyCode?e.keyCode:e.which;
        if (code === keys[i]) {
            preventDefault(e);
            return;
        }
    }
}

function wheel(e) {
  preventDefault(e);
}

//禁用滚动条,但显示出来
function disable_scroll() {
  if (window.addEventListener) {
      window.addEventListener('DOMMouseScroll', wheel, false);
  }
  window.onmousewheel = document.onmousewheel = wheel;
  document.onkeydown = keydown;
}

//启用滚动条
function enable_scroll() {
    if (window.removeEventListener) {
        window.removeEventListener('DOMMouseScroll', wheel, false);
    }
    window.onmousewheel = document.onmousewheel = document.onkeydown = null;  
}

/**
 * 根据经纬度计算地球上两点之间的距离
 */

function getFlatternDistance(lat1,lng1,lat2,lng2){
    var f = getRad((lat1 + lat2)/2);
    var g = getRad((lat1 - lat2)/2);
    var l = getRad((lng1 - lng2)/2);
    var sg = Math.sin(g);
    var sl = Math.sin(l);
    var sf = Math.sin(f);

    var s,c,w,r,d,h1,h2;
    var a = EARTH_RADIUS;
    var fl = 1/298.257;

    sg = sg*sg;
    sl = sl*sl;
    sf = sf*sf;
    s = sg*(1-sl) + (1-sf)*sl;
    c = (1-sg)*(1-sl) + sf*sl;
    w = Math.atan(Math.sqrt(s/c));
    r = Math.sqrt(s*c)/w;
    d = 2*w*a;
    h1 = (3*r -1)/2/c;
    h2 = (3*r +1)/2/s;
    return d*(1 + fl*(h1*sf*(1-sg) - h2*(1-sf)*sg));
}

//获取指定位数的随机数的随机整数
function RndNum(n){
	var rnd="";
	for(var i=0;i<n;i++)
		rnd+=Math.floor(Math.random()*10);
	return rnd;
}

function getNewGpsTime(gpstime){
	var h1 = gpstime.substring(11,12);
	var h2 = gpstime.substring(12,13);
	var m1 = gpstime.substring(14,15);
	var m2 = gpstime.substring(15,16);
	var s1 = gpstime.substring(17,18);
	var s2 = gpstime.substring(18,19);
	
	var s2_new = s2<5?s2+5:s2+5-10;
	if(s2<5){
		s2 = parseInt(s2)+5;
		return gpstime.substring(0,18)+s2;
	}else{
		s2 = s2-5;
		if(s1!=5){
			s1 = parseInt(s1)+1;
			return gpstime.substring(0,17)+s1+s2;
		}else{
			s1 = 0;
			if(m2<9){
				m2 = parseInt(m2)+1;
				return gpstime.substring(0,15)+m2+":"+s1+s2;
			}else{
				m2 = 0;
				if(m1<5){
					m1 = parseInt(m1)+1;
					return gpstime.substring(0,14)+m1+m2+":"+s1+s2;
				}else{
					m1=0;
					if(h1==0){
						if(h2==9){
							h1 = 1;
							h2 = 0;
						}else{
							h1 = 0;
							h2 = parseInt(h2)+1;
						}
						
					}else if(h1==1){
						if(h2==9){
							h1 = 2;
							h2 = 0;
						}else{
							h1 = 1;
							h2 = parseInt(h2)+1;
						}
					}else if(h1==2){
						if(h2==3){
							h1 = 0;
							h2 = 0;
						}else{
							h1 = 2;
							h2 = parseInt(h2)+1
						}
					}
					return gpstime.substring(0,11)+h1+h2+":"+m1+m2+":"+s1+s2;
				}
			}
		}
	}
	
}
