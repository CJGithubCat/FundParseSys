var map;
function initMap(){
	map = new SEGMap("map_baidu", "baidu");
	map.centerAndZoom(112.978129,28.198198, 12);
}

/**
$(document).ready(function() {
	initMap();
});


 * 周伟
 */


function drawingManager(){
	clearOverlays();
	var lon1;
	var lat1;
	var lon2;
	var lat2;
	var styp=$("#shapeType").val();
	if(styp==1){
		map.drawCircle(function(slon, slat, radius, segCircle){
			$("#map_zoom").val(segCircle.zoom);
			$("#centre").val(slon+","+slat);
			$("#rad").val(radius);
		});
		
	}else if(styp==2){
		//矩形
		map.drawRectangle(function(lon_sw, lat_sw, lon_ne, lat_ne,segRectangle){
			$("#map_zoom").val(segRectangle.zoom);
			$("#lat1").val(lat_sw);
		 	$("#lon1").val(lon_sw);
		 	$("#lat2").val(lat_ne);
		 	$("#lon2").val(lon_ne);
		});
	}else if(styp==3){
		map.drawPolygon(function(ps, segPolygon){
			var poins="";
			for(var i=0;i<ps.length;i++){
				if(i==0){
					poins+=ps[i].lon+",";
					poins+=ps[i].lat;
				}else{
					poins+=";"+ps[i].lon+",";
					poins+=ps[i].lat;
				}
			}
			var i;
			if(ps.length%2==0){
				i=ps.length/2
			}else{
				i=(ps.length-1)/2
			}
			
			$("#lat1").val(ps[0].lat);
		 	$("#lon1").val(ps[0].lon);
		 	
		 	$("#lat2").val(ps[i].lat);
		 	$("#lon2").val(ps[i].lon);
			$("#point").val(poins);
			$("#map_zoom").val(segPolygon.zoom);
		});
	}else if(styp==4){
		var temp=map.getBoundary($("#areaName").val(),1,"red",2,1,'white',0.65);
		$("#map_zoom").val(temp.zoom);
	}
}

function updateArea(item){
	  initMap();
	if(item.shapeType=="圆形"){
		var cent=item.centre;
		var pos= new Array(); 
		pos=cent.split(","); 
		map.newCircle(pos[0],pos[1],item.rad,item.areaId,"blue",2,1,'white',0.65);
		map.centerAndZoom(pos[0],pos[1],item.mapZoom);
	}else if(item.shapeType=="长方形"){
		map.newRectangle(item.lon1,item.lat1,item.lon2,item.lat2,item.areaId,"blue",2,1,'white',0.65);
		map.centerAndZoom(item.lon1,item.lat2,item.mapZoom);
	}else if(item.shapeType=="多边形"){
		map.newPolygon(item.ps, item.areaId,"blue",2,1,'white',0.65);
		var i=0;
		if(!(item.ps).length%2){
			i=item.ps.length/2;
		}else{
			i=(item.ps.length-1)/2;
		}
		map.centerAndZoom(item.ps[i].lon,item.ps[i].lat,item.mapZoom);
	}else if(item.shapeType=="省市区域"){
		
		map.getBoundary(item.areaName,item.areaId,"red",2,1,'white',0.65);
		map.centerAndZoom_str(item.areaName,item.mapZoom);
	}
	  
}

//百度清楚覆盖物
function clearOverlays(){
	 $("#lat1").val("");
	 $("#lon1").val("");
	 $("#lat2").val("");
	 $("#lon2").val("");
	 $("#map_zoom").val("");
	 map.clearOverlays();
	 
}


