(function($) {
	$("#viewVideoBtn").click(function(e){
		$.ajax({
			url:'VehicleServlet',
			data : 'method=queryCameraVehicle&attributesPath=,1,2,3,84,6961,',
			success : function(data) {
				if(!data) return;
				var dataJson = eval("(" + data + ")");
				if (dataJson.success) {//打开车辆列表弹窗
					var html = '<table class="table" ><thead><tr>\
									<th>序号</th><th>车牌号</th><th>所属企业</th><th>企业联系电话</th>\
									<th>司机</th><th>司机联系电话</th><th>操作</th>\
								</tr></thead><tbody>';
					var vehicleArr = dataJson.data.list;
					if(vehicleArr.length<1){
						html += '<tr><td colspan=7>暂时没有车辆在线!</td></tr>';
					}else{
						for(var i=0;i<vehicleArr.length;i++){
							var vehicleInfo = vehicleArr[i];
							var dealTd = '<td><a class="view-video-btn btn btn-primary btn-xs" href="#">观看</a>\
												<input type="hidden" data-name="vehicleid" value="'+vehicleInfo.vehicleid+'"/>\
												<input type="hidden" data-name="unitid" value="'+vehicleInfo.unitid+'"/>\
												<input type="hidden" data-name="call_letter" value="'+vehicleInfo.call_letter+'"/>\
												<input type="hidden" data-name="areaRoadId" value="'+vehicleInfo.area_road_id+'"/>\
										   </td>';
							html += '<tr><th scope="row">'+(i+1)+'</th>\
							          	<td>'+vehicleInfo.number_plate+'</td>\
							          	<td>'+vehicleInfo.company_name+'</td>\
							          	<td>'+vehicleInfo.linkman_mobile+'</td>\
							          	<td>'+vehicleInfo.driver_name+'</td>\
							          	<td>'+vehicleInfo.driver_mobile+'</td>'+dealTd+
						          	'</tr>';
						}
					}
					html +="</tbody></table>";
											openPop(
													'videoVehicleList',
													e,
													{
														title : '车辆列表',
														top : document.documentElement.scrollTop + (document.documentElement.clientHeight+navHeight-480)/2,
														width : 900,
														height : 480,
														htmltxt : 'videoVehicleList',
														resize : 2
													});
					$("#videoVehicleList").append(html);
					$("#videoVehicleList").css("overflow-y","scroll");//显示滚动条
					$("#videoVehicleList").find("th").css("text-align","center");//table内容居中
					$("#videoVehicleList").find("td").css("text-align","center");
					$(".view-video-btn").click(function(e){//加载按钮的点击事件
						isVideo = true;
						jumpToWarningTab(this);
					});
				} else {
					//alert("加载失败!");
				}
			},
			error : function() {
				//alert("error!");
			}
		});
	});
})(jQuery);                   
