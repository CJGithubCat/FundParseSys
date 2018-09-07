$(function (){
	var CamionView = window.CamionView = {};
	CamionView.centerLayout = $("#center-layout").ligerLayout({
      	rightWidth:260,
      	isRightCollapse:true,
      	onEndResize:function(){
        	//resizePage();
      	}
  	});
	CamionView.mainLayout = $("#main-layout").ligerLayout({
    	leftWidth:290,
      	rightWidth:0,
    	centerBottomHeight:300,
    	onEndResize:function(){
    		resizePage();
    	}
  	});
	CamionView.leftTab = $("#left-tab").ligerTab({
		contextmenu:false,
		onAfterSelectTabItem:function(tabid){
			resizePage();
		}
	});
	var sreccWidth= screen.width;
	//var defaultWidth=393;
	var defaultWidth=293;
	sreccWidth<=1366?defaultWidth:defaultWidth=493;
	CamionView.enterpriseVehicleLayout = $("#enterprise-vehicle-layout").ligerLayout({
		bottomHeight:defaultWidth,
		onEndResize:function(){
			resizePage();
		}
	});
	CamionView.vehicleQueryLayout = $("#vehicle-query-layout").ligerLayout({
		topHeight:320,
		onEndResize:function(){
			resizePage();
		}
	});
  	CamionView.ajaxToMenuParams = function(data) {
		var params = {};
		for (var key in data) {
			if (data.hasOwnProperty(key))
				params[key] = data[key];
		}
		return params;
	};
	var dataPath;
	CamionView.enterpriseTree = $('#enterprise-tree').ligerTree({
			url:'../company/queryTreeRootNode',
			nodeWidth:200,
			checkbox: false,
			slide: false,
			btnClickToToggleOnly: false,
			isExpand: false,
			idFieldName : 'treedataindex',
			delay:true,
			onSelect : function(node) {
					var data = node.data;
					dataPath = data.params.attributesPath;;
					if(!data) return;
					CamionView.vehicleInfoGrid.search({
						agencyAttributesPath: data.params.attributesPath,
						//page:1,
						//pagesize:20
					})
					$("#vehicle-info-grid").find(".l-bar-btnfirst").trigger("click");
			},
			isLeaf: function(data) {
					return !(data.delay !== undefined || data.children !== undefined);
			},
			onExpand : function(e) {
					this._parentNode = e.target;
			},
			onError:function(XMLHttpRequest, textStatus, errorThrown){
			},
			onSuccess : function(newdata) {
					var parentNode = this._parentNode;
					var menuData = [];
					var parent = $(parentNode);
					var parentData = this.getDataByID(parent.attr('treedataindex'));
					
					if(!parentData){
							// 父节点为空时（即该节点为根节点时）
							$(newdata.datas).each(function() {
								var data = {
													text:this.text,
													delay:{url:'../ twgAgency/queryTreeNodeList'+'?&nodeId='+this.id},
													params : this,
													ischecked:true
											};
									menuData.push(data);
							});
							
							//选中父节点，  
							//且搜索 车辆信息
							setTimeout(function(){
								var data = menuData[0];
								var node = $(CamionView.enterpriseTree.getNodeDom(data));
								node.find('.l-body').addClass('l-selected');
								CamionView.vehicleInfoGrid.search({
										agencyAttributesPath: data.params.attributesPath
										// page:1,
										// pagesize:20
									});
							},0);
					}else{
						//该节点有父节点，不为根节点时
						$(newdata.datas.list).each(function() {
									var leaf={text:this.text,delay:{url:'../ twgAgency/queryTreeNodeList'+'?&nodeId='+this.id},params : this};
									if(this.isLeaf){
											delete leaf.delay;
									}
									menuData.push(leaf);
							});
					}
					if (menuData.length <= 0)return;
					this.append(this._parentNode, menuData);
					if(parentData) delete parentData.delay;
			}
	});
  	CamionView.enterpriseTree._parentNode = null;
  	CamionView.enterpriseTree.getDataByID = _getDataByID;
    
    
  	$("input[name*='date']").css("cursor", "pointer").attr("readonly", true).click(function() {
            $(this).siblings(".l-trigger").find(".l-trigger-icon").click();
  	});

	//车牌颜色
	var vehicleColors = {
		0:"#fff|#030303",// 底色白色，车牌字体黑色，
		1:"#1E90FF|#030303",// 底色蓝色，车牌字体黑色，
		2:"#FFFF00|#030303",// 底色黄色，车牌字体黑色，
		3:"#030303|#FFFFFF",// 底色黑色，车牌字体白色，
		4:"#FFFFFF|#030303",// 底色白色，车牌字体黑色，
		9:"#FF0000|#030303"// 底色红色，车牌字体黑色，
	};
	//车辆风险状态
	var vehicleRiskStateColor = {
			0:"transparent|#030303", // 默认
			2:"#F8E944|#030303",// 高风险
			3:"#F36843|#030303", //逾期
			4:"#89dae6|#030303",//出险
			5:"#ff0000|#030303"//入库
	};
	
  	//车辆信息表格
  	(function(){
	    var gridParms={
		    url:'../vehicle/queryVehiclePage',
		    onSuccess : function(data) {
				data.list = data.datas.list;
				data.count = data.datas.count;
				$(data.list).each(function(index){
					data.list[index]['id'] = data.list[index]['vehicleId'].toString();
				});	
			},
			onDblClickRow: function (data, rowindex, rowobj){
				$("#infocarnum").html("");//车牌号码
				$("#sslc").html("");//实时里程数
				$("#cyyl").html("");//剩余油量
				$("#dbdy").html("");//电瓶电压
				$("#ssfdjzs").html("");//实时发动机转速
				$("#ssfdjwd").html("");//实时发动机温度
				$("#sssd").html("");//实时速度
				console.log("176行企业车辆表");
				console.log(data)
				//家庭住址  工作住址 常住住址
				$(".addressBox").show();
				if(data.home_address!="null"){
					$(".home_address").attr("home",data.home_address)
				}else{
					$(".home_address").attr("home","")
				}
				if(data.work_address!="null"){
					$(".work_address").attr("work",data.work_address)
				}else{
					$(".work_address").attr("work","")
				}
				if(data.regis_address!="null"){
					$(".often_address").attr("often",data.regis_address)
				}else{
					$(".often_address").attr("often","")
				}
				if(data.unitType=="TGO03-01"){
					//data.obdCode=="030A"?$("#balanceCar").html("剩余电量(%):"):$("#balanceCar").html("剩余油量(L、%):");
					if(data.obdCode=="030A"){
						$("#balanceCar").html("剩余电量(%):");
						$(".infohide").hide();
					}else{
						$("#balanceCar").html("剩余油量(L、%):")
					};
					openDialog(
					          'obd-car-info',
					           {
					            title:'OBD车辆信息',
					            width:400,
					            left:300,
					            top:0
					           },function dialog(){
					        	   $("#obd-car-info").parent().parent().css("width","388px");
					           });
					liger.win.wnf_unmask();
				}else{
					closeDialog('obd-car-info');
				}
				var callLetters=[];
				callLetters.push(data.callLetter);
				GetLastPosition(callLetters);
			},
			headerRowHeight:25,
			rowHeight:25,
			width: '100%',
			pageSizeOptions: [5,10,15,20,100],
			height: '100%',
			checkbox: true,
			rownumbers:false,
			delayLoad: true      
		};
  		//添加首列操作选项
	  	var optcol=[
	  		{show:'车牌号码',field:'plateNo',width:150,align:'center',render:function(rowdata,index,value){	  			
		  			var picIco = '';
		  			if(rowdata.unitType.indexOf('TRG90') != -1){
		  				picIco = 'l-icon-wu';
		  			}else if(rowdata.unitType.indexOf('TGO03-01') != -1){
		  				picIco = 'l-icon-obd';
		  			}else if(rowdata.isMotherUnit){
		  				picIco = 'l-icon-motherunit-yes ';
		  			}else{
		  				picIco = 'l-icon-you';
		  			}
		  			
		  			//车牌颜色
		  			//var colors = (vehicleColors[rowdata.vehiclecolor] ? vehicleColors[rowdata.vehiclecolor] : vehicleColors[0] ).split('|');
		  			//车辆风险状态    
		  			var colors = (vehicleRiskStateColor[rowdata.vehicleRiskState] ? vehicleRiskStateColor[rowdata.vehicleRiskState] : vehicleRiskStateColor[0] ).split('|');
		   			var str='';
		    		str+='<i style="text-align:center;" class="l-icon '+(rowdata.isOnline === 0 ? 'l-icon-L2 ':'l-icon-L1')+'"></i>';
		    		str+='<span style="width:61px;white-space:nowrap;text-overflow:ellipsis;overflow:hidden;text-align:left;padding:0px;line-height: 16px;display:inline-block;margin:-3px 2px;background:'+colors[0]+';background-clip:padding-box;color:'+colors[1]+';'+'">'+rowdata.plateNo+'</span>';
		    		str+='<i style="text-align:center" class="l-icon monitor '+(CamionView.monitorGrid.hasRow(rowdata.id)? 'l-icon-R1 ':'l-icon-R2')+'" data-monitor-id="'+rowdata.id+'"></i>';
		    		str+='<i style="text-align:center" class="l-icon sonunit '+ picIco +'" data-index="'+index+'"></i>';
					str+='<i style="text-align:center" class="l-icon rent '+(rowdata.leaseStatus === 0 ?'l-icon-rent-no ':rowdata.leaseStatus === 1 ?'l-icon-rent-yes':rowdata.leaseStatus === 2 ?'l-icon-rent-operate':rowdata.leaseStatus === 3 ?'l-icon-rent-repair':'l-icon-rent-stop')+'"></i>';
		   			return str;
		  		}
	  		},
	  		{show:'所属机构',field:'agencyName',width:120,align:'center'}
	  	];
  		CamionView.vehicleInfoGrid = initMainGrid('vehicle-info-grid',gridParms,optcol);
	  	CamionView.vehicleInfoGrid.search = function(_params){
	  		var params = [];
				for(var key in _params){
					var param = {};
					param['name'] = key;
					param['value'] = _params[key];
					params.push(param);
					this.setParm(key,_params[key]);
				}
				params.push({name:'page',value:1});
				params.push({name:'pagesize',value:10});
	  		CamionView.vehicleInfoGrid.loadServerData(params);
	  	};
		CamionView.vehicleInfoGrid._addNewRecord = _addNewRecord;
		CamionView.vehicleInfoGrid._getSelecteds = _getSelecteds;
		CamionView.vehicleInfoGrid.getDataById = _getDataByGridId;
		$("#vehicle-info-grid").on("contextmenu",'.l-grid-row', function (e){
			var g = this, p = this.options;
			e.preventDefault();
			menu.show({ top: e.pageY-78.6, left: e.pageX });
			menu._id = this.id.split('|')[2];
			CamionView.vehicleInfoGrid.select(this.id.split('|')[2]);
			return false;
		});
		$("#vehicle-info-grid").on("click",'.monitor', function (e){
			var i = $(this);
			var data = CamionView.vehicleInfoGrid.getRow(i.attr('data-monitor-id'));
			
			if(i.hasClass('l-icon-R1')){
				removeMonitorGrid([data]);
			}else{
				addMonitorGrid([data]);
			}
			return false;
		});
		var command_menu = null;
		window.cmmdItems = new Array();
		var fItems = new Array();
		//发送请求获取命令菜单
  		$.ajax({
			url:'../twgPermission/queryPermissions',
			method:"POST",
			async: false,
			dataType:"json",
			success:function(resp){
				//1.命令菜单
				command_menu = resp.datas.commond_items;
				//分离命令
				$.each(command_menu,function(i,n){
					var cmm = command_menu[i];
					if(cmm.categoryid == 3 && cmm.isvalid == 1){
						if(cmm.parentid == 0){//指令类别 ：监控指令，设置指令，加入监控列表
							fItems.push(cmm);
						}
					}
				});
				//排序
				fItems.sort(function(a,b){
					return a.orderid - b.orderid;
				});
				
				$.each(fItems,function(i,n){
					var cmmdItem ={};
					var sItemArr = new Array();
					$.each(command_menu,function(j,k){
						if(command_menu[j].categoryid == 3 && command_menu[j].isvalid == 1){
							if(command_menu[j].parentid == fItems[i].permissionid){//查找一级菜单的子菜单
								sItemArr.push(command_menu[j]);
						}
						}
					});
					sItemArr.sort(function(a,b){
						return a.orderid - b.orderid;
				});
					cmmdItem.fItem = fItems[i];
					cmmdItem.sItemArr = sItemArr;
					cmmdItems.push(cmmdItem);
				});
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(XMLHttpRequest.status);
				$("#username").text("赛格导航用户");
			}
		});
  		//组装menu指令菜单
  		var menuArr = new Array();
		$.each(cmmdItems,function(i,n){
			var item = cmmdItems[i];
			var menuItem = {};
			if(item.fItem.havsubitems >0){
				var subItemArr = item.sItemArr;//子指令数组
				var childArr = new Array();
				menuItem.text = item.fItem.description;
				$.each(subItemArr,function(i,n){
					var childObj = {
						text:subItemArr[i].description,
						click:function(){
							if(subItemArr[i].url == 'getPosition'){
								CamionCMD.getPosition(CamionView.vehicleInfoGrid);
							}
							if(subItemArr[i].url == 'playHistory'){
								CamionCMD.playHistory(CamionView.vehicleInfoGrid);
							}
							if(subItemArr[i].url == 'getLastPos'){
								CamionCMD.getLastPos(CamionView.vehicleInfoGrid);
							}
							if(subItemArr[i].url == 'reset'){
								CamionCMD.reset(CamionView.vehicleInfoGrid);
							}
							if(subItemArr[i].url == 'timingReport'){
								CamionCMD.timingReport(CamionView.vehicleInfoGrid);
							}
							if(subItemArr[i].url == 'setSpeed'){
								CamionCMD.setSpeed(CamionView.vehicleInfoGrid);
							}
							if(subItemArr[i].url == 'misfireTimingReport'){
								CamionCMD.misfireTimingReport(CamionView.vehicleInfoGrid);
							}
							if(subItemArr[i].url == 'markCar'){
								CamionCMD.markCar(CamionView.vehicleInfoGrid);
							}
							if(subItemArr[i].url == 'setVehicleLeaseStatus'){
								CamionCMD.setVehicleLeaseStatus(CamionView.vehicleInfoGrid);
							}
							if(subItemArr[i].url == 'lockPowerAndOil'){
								CamionCMD.LockPowerAndOil(CamionView.vehicleInfoGrid);
							}
							if(subItemArr[i].url == 'unLockPowerAndOil'){
								CamionCMD.UnLockPowerAndOil(CamionView.vehicleInfoGrid);
							}
							if(subItemArr[i].url == 'CPGJ'){
								CamionCMD.CPGJ(CamionView.vehicleInfoGrid);
							}
							if(subItemArr[i].url == 'hotSpotAnalysis'){
								CamionCMD.hotSpotAnalysis(CamionView.vehicleInfoGrid);
							}
							if(subItemArr[i].url == 'allVehiclePosition'){
								CamionCMD.allVehiclePosition();
							}
							if(subItemArr[i].url == 'OffLineCommandPosition'){
								CamionCMD.OffLineCommandPosition(CamionView.vehicleInfoGrid);
								//CamionCMD.OffLineCommandAllEighty(dataPath);
							}
							if(subItemArr[i].url == 'OBDCentralControlOperation'){
								CamionCMD.OBDCentralControlOperation(CamionView.vehicleInfoGrid);
							}
							if(subItemArr[i].url == 'ClearFirtonlineTime'){
								CamionCMD.ClearFirtonlineTime(CamionView.vehicleInfoGrid);
							}
							if(subItemArr[i].url == 'unitTransferNetwork'){
								CamionCMD.unitTransferNetwork(CamionView.vehicleInfoGrid);
							}
							if(subItemArr[i].url == 'batchClearFirtonlineTime'){
								CamionCMD.batchClearFirtonlineTime(CamionView.vehicleInfoGrid);
							}
							if(subItemArr[i].url == 'unitUpgrade'){
								CamionCMD.unitUpgrade(CamionView.vehicleInfoGrid);
							}
							if(subItemArr[i].url == 'setAlarmClock'){
								CamionCMD.setAlarmClock(CamionView.vehicleInfoGrid);
							}
							if(subItemArr[i].url == 'closeWeekClock'){
								CamionCMD.closeWeekClock(CamionView.vehicleInfoGrid);
							}
							if(subItemArr[i].url == 'setWeekClock'){
								CamionCMD.setWeekClock(CamionView.vehicleInfoGrid);
							}
		        		}
					};
					childArr.push(childObj);
				});
				menuItem.children = childArr;
				menuArr.push(menuItem);
			}
		});
		//加入监控列表项
		var monItem = {
			text: '加入监控列表', 
			click: function(){
        		var selects = CamionView.vehicleInfoGrid.getSelecteds();
        		$("#vehicle-info-grid .l-grid-loading").show(addMonitorGrid(selects));
				$("#vehicle-info-grid .l-grid-loading").delay(2000).hide(0);
        	}
		};
		menuArr.push(monItem);
  		var menu = $.ligerMenu({
  			top: 80,
  			left: 100,
  			width: 160,
  			items:menuArr,
		});
  	})();
    
  	//子机操作
  	!function(){
    	var gridParms={
    	  	url:'../twgVehicle/querySonUnit',
    	  	onSuccess : function(data) {
				data.list = data.datas;
				data.count = data.datas.length;
			},
			headerRowHeight:25,
			rowHeight:25,
			width: '100%',
			height: 400,
			rownumbers:false,
			delayLoad: true,
			onDblClickRow: function (data, rowindex, rowobj){
				var callLetters=[];
				callLetters.push(data.callLetter);
				GetLastPosition(callLetters);
				closeDialog('sonunit-dialog');
			}
    	};
    	//添加首列操作选项
	  	var optcol=[
	  		{show:'操作',field:'sonUnitId',width:100,align:'center',render:function(rowdata,index,value){
	  		  		return '<a class="timing-report" data-index="'+index+'">定时报告</a>';
	  		    }
	      	},  		   
			{show:'蓝牙操作',field:'sonUnitId',width:100,align:'center',render:function(rowdata,index,value){
					return '<a class="timing-report1" data-index="'+index+'">蓝牙断开上报频率</a>';
			}},  		    
	  		{show:'车载号码',field:'callLetter',width:100,align:'center'},
	  		{show:'子机编号',field:'sonUnitNumber',width:100,align:'center'},
	  		{show:'子机ID',field:'sonUnitId',width:100,align:'center'}
	  	];
  		CamionView.sonUnitGrid = initMainGrid('sonunit-grid',gridParms,optcol);
		$("#sonunit-grid").on('click','.timing-report',function(e){
			var i = $(this);
			var data = CamionView.sonUnitGrid.getRow(parseInt(i.attr('data-index')));
			CamionCMD.timingReportForSonUnit(motherUnitData.plateNo,data);
		});
		$("#sonunit-grid").on('click','.timing-report1',function(e){
			var i = $(this);
			var data = CamionView.sonUnitGrid.getRow(parseInt(i.attr('data-index')));
			CamionCMD.timingReportBlueTooth(motherUnitData.plateNo,data);
		});
		$("#sonunit-grid").on('click','.bluetooth-report',function(e){
			var i = $(this);
			var data = CamionView.sonUnitGrid.getRow(parseInt(i.attr('data-index')));
			CamionCMD.bluetoothReport(motherUnitData.plateNo,data);
		});
  		var motherUnitData = null;
  		$("#vehicle-info-grid,#monitor-grid,#vehicle-query-grid").on("click",'.sonunit', function (e){
			e.preventDefault();
			var i = $(this);
			var gridId = i.parents('[ligeruiid]').attr('id');
			var gridLigerName = '';
			if(gridId === 'vehicle-info-grid') gridLigerName = 'vehicleInfoGrid';
			if(gridId === 'monitor-grid') gridLigerName = 'monitorGrid';
			if(gridId === 'vehicle-query-grid') gridLigerName = 'vehicleQueryGrid';
			
			var data = motherUnitData = CamionView[gridLigerName].getRow(parseInt(i.attr('data-index')));
			if(!data.isMotherUnit) return;
			openDialog('sonunit-dialog',{title:'子机定时报告',width:430,height:464,isResize:false},function(dialog){
				CamionView.sonUnitGrid.setParm('motherUnitId',data.unitId);
				CamionView.sonUnitGrid.setParm('plateNo',data.plateNo);
				CamionView.sonUnitGrid.reload();

				/*CamionView.bluetoothGrid.setParm('motherUnitId',data.unitId);
					CamionView.bluetoothGrid.setParm('plateNo',data.plateNo);
					CamionView.bluetoothGrid.reload();*/
			});
		});
  	}();
    //页面加载后判断是否已添加监控
    function monitorSearch(){
    	var vehicleQueryGrid = CamionView.vehicleQueryGrid;
		var vehicleInfoGrid = CamionView.vehicleInfoGrid;
		var areavehiclequerygrid = CamionView.areavehiclequerygrid;
    	var monitorGrid = CamionView.monitorGrid;
		var vehicleGridDOM = $([CamionView.vehicleInfoGrid.element,CamionView.vehicleQueryGrid.element,CamionView.areavehiclequerygrid.element]);
		var callLetters=[];
    	$.ajax({
			url:'../vehicle/monitor/search',
			method:"POST",
			dataType:"json",
			success:function(resp){
				$(resp.datas.list).each(function(index){
					resp.datas.list[index]['id'] = resp.datas.list[index]['vehicleId'].toString();
					if(monitorGrid.getRow(this.id)) return;
					vehicleGridDOM.find('[data-monitor-id='+this.id+']').removeClass('l-icon-R2').addClass('l-icon-R1');
					monitorGrid.addRow(this);
					callLetters.push(this.callLetter);
				});
				AddMonitor(callLetters);
			}
    	});
    }
    setTimeout(function(){monitorSearch()},10000);
  	//从监控列表移除
  	function removeMonitorGrid(rowdatas){
    	var vehicleQueryGrid = CamionView.vehicleQueryGrid;
		var vehicleInfoGrid = CamionView.vehicleInfoGrid;
		var areavehiclequerygrid = CamionView.areavehiclequerygrid;
		var vehicleGridDOM = $([CamionView.vehicleInfoGrid.element,CamionView.vehicleQueryGrid.element,CamionView.areavehiclequerygrid.element]);
		var callLetters=[];
		var unitArr=[];
		$(rowdatas).each(function(index){
			CamionView.monitorGrid.remove(this);
			vehicleGridDOM.find('[data-monitor-id='+this.id+']').addClass('l-icon-R2').removeClass('l-icon-R1');
			try{
				vehicleQueryGrid.unselect(this);
				vehicleInfoGrid.unselect(this);
				areavehiclequerygrid.unselect(this);
			}catch(e){}
			callLetters.push(this.callLetter);
			unitArr.push(this.unitId);
		});
		var unitId=JSON.stringify(unitArr);
		//车辆移出监控后将unitId传给后台保存
    	$.ajax({
			url:'../vehicle/monitor/del',
			method:"POST",
			data:{unitId:unitId},
			dataType:"json",
			success:function(resp){
			}

    	});
		RemoveMonitor(callLetters);
  	}
  	//添加到监控列表
  	function addMonitorGrid(rowdatas){
    	var monitorGrid = CamionView.monitorGrid;
		var vehicleGridDOM = $([CamionView.vehicleInfoGrid.element,CamionView.vehicleQueryGrid.element,CamionView.areavehiclequerygrid.element]);
		var callLetters=[];
		var unitArr=[];
		console.log(rowdatas);
		$(rowdatas).each(function(e){
			if(monitorGrid.getRow(this.id)) return;
			vehicleGridDOM.find('[data-monitor-id='+this.id+']').removeClass('l-icon-R2').addClass('l-icon-R1');
			monitorGrid.addRow(this);
			callLetters.push(this.callLetter);
			unitArr.push(this.unitId);
		});
		var unitId=JSON.stringify(unitArr);
		//车辆添加监控后将unitId传给后台保存
    	$.ajax({
			url:'../vehicle/monitor/add',
			method:"POST",
			data:{unitId:unitId},
			dataType:"json",
			success:function(resp){
			}
    	});
		AddMonitor(callLetters);
  	}
    
  	//监控列表表格
  	(function(){
    	var gridParms={
	    	onDblClickRow: function (data, rowindex, rowobj){
	    		$("#infocarnum").html("");//车牌号码
				$("#sslc").html("");//实时里程数
				$("#cyyl").html("");//剩余油量
				$("#dbdy").html("");//电瓶电压
				$("#ssfdjzs").html("");//实时发动机转速
				$("#ssfdjwd").html("");//实时发动机温度
				$("#sssd").html("");//实时速度
				console.log("589行监控列表");
				console.log(data)
				//家庭住址  工作住址 常住住址
				$(".addressBox").show();
				if(data.home_address!="null"){
					$(".home_address").attr("home",data.home_address)
				}else{
					$(".home_address").attr("home","")
				}
				if(data.work_address!="null"){
					$(".work_address").attr("work",data.work_address)
				}else{
					$(".work_address").attr("work","")
				}
				if(data.regis_address!="null"){
					$(".often_address").attr("often",data.regis_address)
				}else{
					$(".often_address").attr("often","")
				}
				if(data.unitType=="TGO03-01"){
					//data.obdCode=="030A"?$("#balanceCar").html("剩余电量(%):"):$("#balanceCar").html("剩余油量(L、%):");
					if(data.obdCode=="030A"){
						$("#balanceCar").html("剩余电量(%):");
						$(".infohide").hide();
					}else{
						$("#balanceCar").html("剩余油量(L、%):")
					};
					openDialog(
					          'obd-car-info',
					           {
					            title:'OBD车辆信息',
					            width:400,
					            left:300,
					            top:0
					           },function dialog(){
					        	   $("#obd-car-info").parent().parent().css("width","388px");
					           });
					liger.win.wnf_unmask();
				}else{
					closeDialog('obd-car-info');
				}
				var callLetters=[];
				callLetters.push(data.callLetter);
				GetLastPosition(callLetters);
	    	},
			width: '100%',
			pageSizeOptions: [5, 10, 15, 20],
			height: '100%',
			headerRowHeight:25,
			rowHeight:25,
			checkbox: true,
			rownumbers:false
		};
	  
	  	//添加首列操作选项
	  	var optcol=[
	  		{show:'车牌号码',field:'plateNo',width:150,align:'center',render:function(rowdata,index,value){
	  			var picIco = '';
 	  			if(rowdata.unitType.indexOf('TRG90') != -1){
 	  				picIco = 'l-icon-wu';
 	  			}else if(rowdata.unitType.indexOf('TGO03-01') != -1){
 	  				picIco = 'l-icon-obd';
 	  			}else if(rowdata.isMotherUnit){
 	  				picIco = 'l-icon-motherunit-yes ';
 	  			}else{
 	  				picIco = 'l-icon-you';
 	  			}
	  			//车牌颜色
	  			//var colors = (vehicleColors[rowdata.vehiclecolor] ? vehicleColors[rowdata.vehiclecolor] : vehicleColors[0] ).split('|');
	  			//车辆风险状态
	  			var colors = (vehicleRiskStateColor[rowdata.vehicleRiskState] ? vehicleRiskStateColor[rowdata.vehicleRiskState] : vehicleRiskStateColor[0] ).split('|');
	   			var str='';
	    		str+='<i style="text-align:center;" class="l-icon '+(rowdata.isOnline === 0 ? 'l-icon-L2 ':'l-icon-L1')+'"></i>';
	    		str+='<span style="width:61px;white-space:nowrap;text-overflow:ellipsis;overflow:hidden;text-align:left;padding:0px;line-height: 18px;display:inline-block;margin:-3px 2px;background:'+colors[0]+';background-clip: padding-box;color:'+colors[1]+';'+'">'+rowdata.plateNo+'</span>';
	    		//str+='<i style="text-align:center" class="l-icon monitor '+(CamionView.monitorGrid.hasRow(rowdata.id)? 'l-icon-R1 ':'l-icon-R2')+'" data-monitor-id="'+rowdata.id+'"></i>';
	    		//str+='<i style="text-align:center;" class="l-icon sonunit '+(rowdata.isMotherUnit? 'l-icon-motherunit-yes ':'l-icon-motherunit-no')+'" data-index="'+index+'"></i>';
	    		//str+='<i  style="text-align:center" class="l-icon sonunit '+(rowdata.unitType.indexOf('TRG90') != -1 ? 'l-icon-wu':(rowdata.isMotherUnit? 'l-icon-motherunit-yes ':'l-icon-you'))+'" data-index="'+index+'"></i>';
	    		str+='<i style="text-align:center" class="l-icon sonunit '+ picIco +'" data-index="'+index+'"></i>';
				str+='<i style="text-align:center;" class="l-icon rent '+(rowdata.leaseStatus === 0 ?'l-icon-rent-no ':rowdata.leaseStatus === 1 ?'l-icon-rent-yes':rowdata.leaseStatus === 2 ?'l-icon-rent-operate':rowdata.leaseStatus === 3 ?'l-icon-rent-repair':'l-icon-rent-stop')+'"></i>';
	   			return str;
	  		 }
	  		},
	  		{show:'车载号码',field:'callLetter',width:115,align:'center'}
	  	];
		CamionView.monitorGrid = initMainGrid('monitor-grid',gridParms,optcol);
		$('#monitor-grid').find('.l-panel-bar').height(0);
		CamionView.monitorGrid._addNewRecord = _addNewRecord;
		CamionView.monitorGrid._getSelecteds = _getSelecteds;
		CamionView.monitorGrid.hasRow = _hasRow;
		CamionView.monitorGrid._removeData = _removeData;
		
		$("#monitor-grid").on("contextmenu",'.l-grid-row', function (e){
			e.preventDefault();
			if(sreccWidth<=1366){
				//console.log("是14寸电脑");
				if(e.pageY>463){
					menu.show({ top: e.pageY-98, left: e.pageX });
				}else{
					menu.show({ top: e.pageY-8, left: e.pageX });
				}
				
			}else{
				//console.log("不是14");
				menu.show({ top: e.pageY-80, left: e.pageX });
			}			
				menu._id = this.id.split('|')[2];
			CamionView.monitorGrid.select(this.id.split('|')[2]);
			return false;
		});
  		
  		var menuArr = new Array();
		$.each(cmmdItems,function(i,n){
			var item = cmmdItems[i];
			var menuItem = {};
			if(item.fItem.havsubitems >0){
				var subItemArr = item.sItemArr;//子指令数组
				var childArr = new Array();
				menuItem.text = item.fItem.description;
				$.each(subItemArr,function(i,n){
					var childObj = {
						text:subItemArr[i].description,
						click:function(){
							if(subItemArr[i].url == 'getPosition'){
								CamionCMD.getPosition(CamionView.monitorGrid);
							}
							if(subItemArr[i].url == 'playHistory'){
								CamionCMD.playHistory(CamionView.monitorGrid);
							}
							if(subItemArr[i].url == 'getLastPos'){
								CamionCMD.getLastPos(CamionView.monitorGrid);
							}
							if(subItemArr[i].url == 'reset'){
								CamionCMD.reset(CamionView.monitorGrid);
							}
							if(subItemArr[i].url == 'timingReport'){
								CamionCMD.timingReport(CamionView.monitorGrid);
							}
							if(subItemArr[i].url == 'setSpeed'){
								CamionCMD.setSpeed(CamionView.monitorGrid);
							}
							if(subItemArr[i].url == 'misfireTimingReport'){
								CamionCMD.misfireTimingReport(CamionView.monitorGrid);
							}
							if(subItemArr[i].url == 'markCar'){
								CamionCMD.markCar(CamionView.monitorGrid);
							}
							if(subItemArr[i].url == 'setVehicleLeaseStatus'){
								CamionCMD.setVehicleLeaseStatus(CamionView.monitorGrid);
							}
							if(subItemArr[i].url == 'lockPowerAndOil'){
								CamionCMD.LockPowerAndOil(CamionView.monitorGrid);
							}
							if(subItemArr[i].url == 'unLockPowerAndOil'){
								CamionCMD.UnLockPowerAndOil(CamionView.monitorGrid);
							}
							if(subItemArr[i].url == 'CPGJ'){
								CamionCMD.CPGJ(CamionView.monitorGrid);
							}
							if(subItemArr[i].url == 'hotSpotAnalysis'){
								CamionCMD.hotSpotAnalysis(CamionView.monitorGrid);
							}
							if(subItemArr[i].url == 'OBDCentralControlOperation'){
								CamionCMD.OBDCentralControlOperation(CamionView.monitorGrid);
							}
							if(subItemArr[i].url == 'ClearFirtonlineTime'){
								CamionCMD.ClearFirtonlineTime(CamionView.monitorGrid);
							}
							if(subItemArr[i].url == 'unitTransferNetwork'){
								CamionCMD.unitTransferNetwork(CamionView.monitorGrid);
							}
							if(subItemArr[i].url == 'batchClearFirtonlineTime'){
								CamionCMD.batchClearFirtonlineTime(CamionView.monitorGrid);
							}
							if(subItemArr[i].url == 'unitUpgrade'){
								CamionCMD.unitUpgrade(CamionView.monitorGrid);
							}
							if(subItemArr[i].url == 'setAlarmClock'){
								CamionCMD.setAlarmClock(CamionView.monitorGrid);
							}
							if(subItemArr[i].url == 'closeWeekClock'){
								CamionCMD.closeWeekClock(CamionView.monitorGrid);
							}
							if(subItemArr[i].url == 'setWeekClock'){
								CamionCMD.setWeekClock(CamionView.monitorGrid);
							}
		        		}
					};
					childArr.push(childObj);
				});
				menuItem.children = childArr;
				menuArr.push(menuItem);
			}
		});
		//移出监控列表项
		var monItem = {
			text: '移出监控列表', 
			click: function(){
        		var selects = CamionView.monitorGrid.getSelecteds();
        		$("#monitor-grid .l-grid-loading").show(0,removeMonitorGrid(selects));
				$("#monitor-grid .l-grid-loading").delay(2000).hide(0);
        	}
		};
		menuArr.push(monItem);
  			
  		var menu = $.ligerMenu({
  			top: 80,
  			left: 110,
  			width: 160,
  			items:menuArr
		});
  	})();
    
	//车辆查询结果表格
	(function(){
    	var gridParms={
	    	url:'../vehicle/searchVehicleList',
	    	onSuccess : function(data) {
				data.list = data.datas.list;
				data.count = data.datas.count;
				$(data.list).each(function(index){
					data.list[index]['id'] = data.list[index]['vehicleId'].toString();
				});
				
			},
			onDblClickRow: function (data, rowindex, rowobj){
				$("#infocarnum").html("");//车牌号码
				$("#sslc").html("");//实时里程数
				$("#cyyl").html("");//剩余油量
				$("#dbdy").html("");//电瓶电压
				$("#ssfdjzs").html("");//实时发动机转速
				$("#ssfdjwd").html("");//实时发动机温度
				$("#sssd").html("");//实时速度
				console.log("797行车辆查询表格");
				console.log(data)
				//家庭住址  工作住址 常住住址
				$(".addressBox").show();
				if(data.home_address!="null"){
					$(".home_address").attr("home",data.home_address)
				}else{
					$(".home_address").attr("home","")
				}
				if(data.work_address!="null"){
					$(".work_address").attr("work",data.work_address)
				}else{
					$(".work_address").attr("work","")
				}
				if(data.regis_address!="null"){
					$(".often_address").attr("often",data.regis_address)
				}else{
					$(".often_address").attr("often","")
				}
				if(data.unitType=="TGO03-01"){
					//data.obdCode=="030A"?$("#balanceCar").html("剩余电量(%):"):$("#balanceCar").html("剩余油量(L、%):");
					if(data.obdCode=="030A"){
						$("#balanceCar").html("剩余电量(%):");
						$(".infohide").hide();
					}else{
						$("#balanceCar").html("剩余油量(L、%):")
					};
					openDialog(
					          'obd-car-info',
					           {
					            title:'OBD车辆信息',
					            width:400,
					            left:300,
					            top:0
					           },function dialog(){
					        	   $("#obd-car-info").parent().parent().css("width","388px");
					           });
					liger.win.wnf_unmask();
				}else{
					closeDialog('obd-car-info');
				}
				var callLetters=[];
				callLetters.push(data.callLetter);
				GetLastPosition(callLetters);
    		},
			headerRowHeight:25,
				rowHeight:25,
			width: '100%',
			pageSizeOptions: [5, 10, 15, 20,100],
			height: '100%',
			checkbox: true,
			rownumbers:false,
			delayLoad: true
		};
	  	
  		//添加首列操作选项
   		var optcol=[
 	  		{show:'车牌号码',field:'plateNo',width:150,align:'center',render:function(rowdata,index,value){
 	  			var picIco = '';
 	  			if(rowdata.unitType.indexOf('TRG90') != -1){
 	  				picIco = 'l-icon-wu';
 	  			}else if(rowdata.unitType.indexOf('TGO03-01') != -1){
 	  				picIco = 'l-icon-obd';
 	  			}else if(rowdata.isMotherUnit){
 	  				picIco = 'l-icon-motherunit-yes ';
 	  			}else{
 	  				picIco = 'l-icon-you';
 	  			}
  				//车牌颜色
		  		//var colors = (vehicleColors[rowdata.vehiclecolor] ? vehicleColors[rowdata.vehiclecolor] : vehicleColors[0] ).split('|');
		  		//车辆风险状态
		  		var colors = (vehicleRiskStateColor[rowdata.vehicleRiskState] ? vehicleRiskStateColor[rowdata.vehicleRiskState] : vehicleRiskStateColor[0] ).split('|');
		   		var str='';
				str+='<i style="text-align:center;" class="l-icon '+(rowdata.isOnline === 0 ? 'l-icon-L2 ':'l-icon-L1')+'"></i>';
				str+='<span style="width:61px;white-space:nowrap;text-overflow:ellipsis;overflow:hidden;text-align:left;padding:0px;line-height:16px;display:inline-block;margin:-3px 2px;background:'+colors[0]+';background-clip: padding-box;color:'+colors[1]+';'+'">'+rowdata.plateNo+'</span>';
				str+='<i style="text-align:center;" class="l-icon monitor '+(CamionView.monitorGrid.hasRow(rowdata.id)? 'l-icon-R1 ':'l-icon-R2')+'" data-monitor-id="'+rowdata.id+'"></i>';
				//str+='<i style="text-align:center;" class="l-icon sonunit '+(rowdata.isMotherUnit? 'l-icon-motherunit-yes ':'l-icon-motherunit-no')+'" data-index="'+index+'"></i>';
				str+='<i style="text-align:center" class="l-icon sonunit '+ picIco +'" data-index="'+index+'"></i>';
				str+='<i style="text-align:center;" class="l-icon rent '+(rowdata.leaseStatus === 0 ?'l-icon-rent-no ':rowdata.leaseStatus === 1 ?'l-icon-rent-yes':rowdata.leaseStatus === 2 ?'l-icon-rent-operate':rowdata.leaseStatus === 3 ?'l-icon-rent-repair':'l-icon-rent-stop')+'"></i>';
				return str;
			  }
		  	},
    	  {show:'车载号码',field:'callLetter',width:120,align:'center'}
    	];
  		CamionView.vehicleQueryGrid = initMainGrid('vehicle-query-grid',gridParms,optcol);
  		CamionView.vehicleQueryGrid._addNewRecord = _addNewRecord;
  		CamionView.vehicleQueryGrid._getSelecteds = _getSelecteds;
  		CamionView.vehicleQueryGrid.getDataById = _getDataByGridId;
  		var vehicleQueryForm = $('#vehicle-query-form');
  		vehicleQueryForm.on('submit',function(e){
  			e.preventDefault();
  			var searchData={};
    		var formParam = getFormDataAll('vehicle-query-form');
    		for(var k in formParam){
       			var newdata;
       			newdata=$.trim(formParam[k]);
       			if(newdata!=''){
           			searchData[k]=newdata;
           			CamionView.vehicleQueryGrid.setParm(k,newdata);
       			}else{
           			CamionView.vehicleQueryGrid.removeParm(k);
           			delete formParam[k];
       			}
   			}
    		formParam.page = 1;
    		var rp_value = $("select[name='rp']").val();
    	     formParam.pagesize = rp_value;
    		//暂时先传1，查询所有车
    		//formParam.agency_id = 1;
   			CamionView.vehicleQueryGrid.loadServerData(formParam);
   			$("#vehicle-query-layout .l-layout-center").css("width","290px");
			$("#vehicle-query-grid .l-grid2").css("width","260px");
			$("#areavehicle-query-grid").hide();
			$("#vehicle-query-grid").show();
			$("#vehicle-query-grid").find(".l-bar-btnfirst").trigger("click");
  		});
  		
  		$("#vehicle-query-grid").on("contextmenu",'.l-grid-row', function (e){
  			e.preventDefault();
  			if(sreccWidth<=1366){
				//console.log("是14寸电脑");
				if(e.pageY>463){
					menu.show({ top: e.pageY-8, left: e.pageX });
				}else{
					menu.show({ top: e.pageY-80, left: e.pageX });
				}
				
			}else{
				//console.log("不是14");
				menu.show({ top: e.pageY-80, left: e.pageX });
			}
  			//menu.show({ top: e.pageY-62, left: e.pageX });
  			menu._id = this.id.split('|')[2];
  			CamionView.vehicleQueryGrid.select(this.id.split('|')[2]);
  			return false;
  		});
  		$("#vehicle-query-grid").on("click",'.monitor', function (e){
  			var i = $(this);
  			var data = CamionView.vehicleQueryGrid.getRow(i.attr('data-monitor-id'));
  			if(i.hasClass('l-icon-R1')){
  				removeMonitorGrid([data]);
  			}else{
  				addMonitorGrid([data]);
  			}
  			return false;
  		});
  		
  		//组装指令
  		var menuArr = new Array();
		$.each(cmmdItems,function(i,n){
			var item = cmmdItems[i];
			var menuItem = {};
			if(item.fItem.havsubitems >0){
				var subItemArr = item.sItemArr;//子指令数组
				var childArr = new Array();
				menuItem.text = item.fItem.description;
				$.each(subItemArr,function(i,n){
					var childObj = {
						text:subItemArr[i].description,
						click:function(){
							if(subItemArr[i].url == 'getPosition'){
								CamionCMD.getPosition(CamionView.vehicleQueryGrid);
							}
							if(subItemArr[i].url == 'playHistory'){
								CamionCMD.playHistory(CamionView.vehicleQueryGrid);
							}
							if(subItemArr[i].url == 'getLastPos'){
								CamionCMD.getLastPos(CamionView.vehicleQueryGrid);
							}
							if(subItemArr[i].url == 'reset'){
								CamionCMD.reset(CamionView.vehicleQueryGrid);
							}
							if(subItemArr[i].url == 'timingReport'){
								CamionCMD.timingReport(CamionView.vehicleQueryGrid);
							}
							if(subItemArr[i].url == 'setSpeed'){
								CamionCMD.setSpeed(CamionView.vehicleQueryGrid);
							}
							if(subItemArr[i].url == 'misfireTimingReport'){
								CamionCMD.misfireTimingReport(CamionView.vehicleQueryGrid);
							}
							if(subItemArr[i].url == 'markCar'){
								CamionCMD.markCar(CamionView.vehicleQueryGrid);
							}
							if(subItemArr[i].url == 'setVehicleLeaseStatus'){
								CamionCMD.setVehicleLeaseStatus(CamionView.vehicleQueryGrid);
							}
							if(subItemArr[i].url == 'lockPowerAndOil'){
								CamionCMD.LockPowerAndOil(CamionView.vehicleQueryGrid);
							}
							if(subItemArr[i].url == 'unLockPowerAndOil'){
								CamionCMD.UnLockPowerAndOil(CamionView.vehicleQueryGrid);
							}
							if(subItemArr[i].url == 'CPGJ'){
								CamionCMD.CPGJ(CamionView.vehicleQueryGrid);
							}
							if(subItemArr[i].url == 'hotSpotAnalysis'){
								CamionCMD.hotSpotAnalysis(CamionView.vehicleQueryGrid);
							}
							if(subItemArr[i].url == 'OffLineCommandPosition'){
								CamionCMD.OffLineCommandPosition(CamionView.vehicleQueryGrid);
							}
							if(subItemArr[i].url == 'OBDCentralControlOperation'){
								CamionCMD.OBDCentralControlOperation(CamionView.vehicleQueryGrid);
							}
							if(subItemArr[i].url == 'ClearFirtonlineTime'){
								CamionCMD.ClearFirtonlineTime(CamionView.vehicleQueryGrid);
							}
							if(subItemArr[i].url == 'unitTransferNetwork'){
								CamionCMD.unitTransferNetwork(CamionView.vehicleQueryGrid);
							}
							if(subItemArr[i].url == 'batchClearFirtonlineTime'){
								CamionCMD.batchClearFirtonlineTime(CamionView.vehicleQueryGrid);
							}
							if(subItemArr[i].url == 'unitUpgrade'){
								CamionCMD.unitUpgrade(CamionView.vehicleQueryGrid);
							}
							if(subItemArr[i].url == 'setAlarmClock'){
								CamionCMD.setAlarmClock(CamionView.vehicleQueryGrid);
							}
							if(subItemArr[i].url == 'closeWeekClock'){
								CamionCMD.closeWeekClock(CamionView.vehicleQueryGrid);
							}
							if(subItemArr[i].url == 'setWeekClock'){
								CamionCMD.setWeekClock(CamionView.vehicleQueryGrid);
							}
						}
					};
					childArr.push(childObj);
				});
				menuItem.children = childArr;
				menuArr.push(menuItem);
			}
		});
  		//加入监控列表项
		var monItem = {
			text: '加入监控列表', 
			click: function(){
        		var selects = CamionView.vehicleQueryGrid.getSelecteds();
        		$("#vehicle-query-grid .l-grid-loading").show(0,addMonitorGrid(selects)); 		
				$("#vehicle-query-grid .l-grid-loading").delay(2000).hide(0);
        	}
		};
		menuArr.push(monItem);	
		var menu = $.ligerMenu({
  			top: 100,
  			left: 100,
  			width: 160,
  			items:menuArr
		});
  	})();
    
    //加载表格数据
	(function(){
	    var gridParms={
		    url:'../vehicle/queryVehicleByGspPos',
		    onSuccess : function(data) {
				data.list = data.datas.list;
				data.count = data.datas.count;
				$(data.list).each(function(index){
					data.list[index]['id'] = data.list[index]['vehicleId'].toString();
				});
			},
			onDblClickRow: function (data, rowindex, rowobj){
				var callLetters=[];
				callLetters.push(data.callLetter);
				GetLastPosition(callLetters);
		    },
		    headerRowHeight:25,
		    rowHeight:25,
		   	width: '100%',
		    pageSizeOptions: [5,10,15,20,100],
		    height: '100%',
		    checkbox: true,
		    rownumbers:false,
		    delayLoad: true      
		};	  	
  		//添加首列操作选项
  		var optcol=[
	  		{show:'车牌号码',field:'plateNo',width:150,align:'center',render:function(rowdata,index,value){
	  			//车辆风险状态
	  			var colors = (vehicleRiskStateColor[rowdata.vehicleRiskState] ? vehicleRiskStateColor[rowdata.vehicleRiskState] : vehicleRiskStateColor[0] ).split('|');
	   		  	var str='';
					str+='<i style="text-align:center;" class="l-icon '+(rowdata.isOnline === 0 ? 'l-icon-L2 ':'l-icon-L1')+'"></i>';
					str+='<span style="width:61px;white-space:nowrap;text-overflow:ellipsis;overflow:hidden;text-align:left;padding:0px;line-height: 16px;display:inline-block;margin:-4px 2px;background:'+colors[0]+';background-clip: padding-box;color:'+colors[1]+';'+'">'+rowdata.plateNo+'</span>';
					str+='<i style="text-align:center;" class="l-icon monitor '+(CamionView.monitorGrid.hasRow(rowdata.id)? 'l-icon-R1 ':'l-icon-R2')+'" data-monitor-id="'+rowdata.id+'"></i>';
					//str+='<i style="text-align:center;" class="l-icon sonunit '+(rowdata.isMotherUnit? 'l-icon-motherunit-yes ':'l-icon-motherunit-no')+'" data-index="'+index+'"></i>';
					str+='<i style="text-align:center" class="l-icon sonunit '+(rowdata.unitType.indexOf('TRG90') != -1 ? 'l-icon-wu':(rowdata.isMotherUnit? 'l-icon-motherunit-yes ':'l-icon-you'))+'" data-index="'+index+'"></i>';
					str+='<i style="text-align:center;" class="l-icon rent '+(rowdata.leaseStatus === 0 ?'l-icon-rent-no ':rowdata.leaseStatus === 1 ?'l-icon-rent-yes':rowdata.leaseStatus === 2 ?'l-icon-rent-operate':rowdata.leaseStatus === 3 ?'l-icon-rent-repair':'l-icon-rent-stop')+'"></i>';
					return str;
	  		  }
	  		},
	  		{show:'车载号码',field:'callLetter',width:120,align:'center'}
	  	];
  		CamionView.areavehiclequerygrid = initMainGrid('areavehicle-query-grid',gridParms,optcol);
  		CamionView.areavehiclequerygrid.search = function(_params){
  			var params = [];
			for(var key in _params){
				var param = {};
				param['name'] = key;
				param['value'] = _params[key];
				params.push(param);
				this.setParm(key,_params[key]);
			}
			params.push({name:'page',value:1});
			params.push({name:'pagesize',value:20});
  		};
		CamionView.areavehiclequerygrid._addNewRecord = _addNewRecord;
  		CamionView.areavehiclequerygrid._getSelecteds = _getSelecteds;
  		CamionView.areavehiclequerygrid.getDataById = _getDataByGridId;
		$("#areavehicle-query-grid").on("contextmenu",'.l-grid-row', function (e){
			e.preventDefault();
			menu.show({ top: e.pageY-80, left: e.pageX });
			menu._id = this.id.split('|')[2];
			CamionView.areavehiclequerygrid.select(this.id.split('|')[2]);
			return false;
		});
		$("#areavehicle-query-grid").on("click",'.monitor', function (e){
			var i = $(this);
			var data = CamionView.areavehiclequerygrid.getRow(i.attr('data-monitor-id'));
			if(i.hasClass('l-icon-R1')){
				removeMonitorGrid([data]);
			}else{
				addMonitorGrid([data]);
			}
			return false;
		});
  		var menu = $.ligerMenu({
  			top: 100,
  			left: 100,
  			width: 140,
  			items:[
  			        {text:'监控指令', children:[
		  			    {text: '历史回放', click: function(){
		  			    	    CamionCMD.playHistory(CamionView.areavehiclequerygrid);
		            	}},
		            	{text: '车辆定位', click: function(){
		            		CamionCMD.getPosition(CamionView.areavehiclequerygrid);
		            	}},
		            	{text: '最后位置', click: function(){
		            		CamionCMD.getLastPos(CamionView.areavehiclequerygrid);
 		            	}},
		            	{text: '车台复位', click: function(){
		            		CamionCMD.reset(CamionView.areavehiclequerygrid);
		            	}},
		            	{text: '车辆分布', click: function(){
		            		CamionCMD.allVehiclePosition();
		            	}}
					]},
		            {text:'设置指令', children:[
			         	{text: '定时报告', click: function(){
			         			CamionCMD.timingReport(CamionView.areavehiclequerygrid);
		         		}},
						{ text: '超速设置', click: function(){
							CamionCMD.setSpeed(CamionView.areavehiclequerygrid);
						}},
						{ text: '熄火定时报告', click: function(){
							CamionCMD.misfireTimingReport(CamionView.areavehiclequerygrid);
						}},
						{ text: '车辆标注', click: function(){
							CamionCMD.markCar(CamionView.areavehiclequerygrid);
						}},
						{ text: '车辆在租状态', click: function(){
								CamionCMD.setVehicleLeaseStatus(CamionView.areavehiclequerygrid);
						}}
					]},
					{text:'控制指令', children:[
		            	{ text: '断油电', click: function(){
		            		CamionCMD.LockPowerAndOil(CamionView.vehicleQueryGrid);
 		            	}},
		            	{ text: '恢复油电', click: function(){
		            		CamionCMD.UnLockPowerAndOil(CamionView.vehicleQueryGrid);
		            	}},
 		            	{ text: '常跑轨迹统计', click: function(){
 		            		CamionCMD.CPGJ(CamionView.vehicleQueryGrid);
 		            	}},
 		            	{ text: '热点分析统计', click: function(){
 		            		CamionCMD.hotSpotAnalysis(CamionView.vehicleQueryGrid);
 		            	}},
 		            	{ text: 'OBD中控操作', click: function(){
 		            		CamionCMD.OBDCentralControlOperation(CamionView.vehicleQueryGrid);
 		            	}}
		            ]},
					{ text: '加入监控列表', click: function(){
						var selects = CamionView.areavehiclequerygrid.getSelecteds();
						$("#areavehicle-query-grid .l-grid-loading").show(0,addMonitorGrid(selects));
						$("#areavehicle-query-grid .l-grid-loading").delay(2000).hide(0);
					}}
            ]
		});
  	})();
   
    //监听列表
  	!function(){
    	var alarmTabDOM = $('#alarm-tab');
		CamionView.alarmTab = alarmTabDOM.ligerTab({
			changeHeightOnResize:true,
			contextmenu:false,
			onAfterSelectTabItem:function(tabid){
				resizePage();
			}
		});
    	var alarmGrids = alarmTabDOM.find('[class="alarm-grid"]');
    	var alarmHeader = CamionView.mainLayout.centerBottom.find('.l-layout-header');
	    //有喇叭，以后要用
	    // // alarmHeader.html('<div class="operate"><a id="online_vehicle_flush" title="点击刷新">实时在线<span id="index-middle-breadcrumb-online">0(0%)</span></a>'+
	    //                    '<a id="offline_vehicle_flush" title="点击刷新">实时不在线<span id="index-middle-breadcrumb-offline">0(0%)</span></a>'+
	    //                    '<a id="alarm_num">警情<span id="index-middle-breadcrumb-alarm">0</span></a>'+
	    //                    '<a id="controlMusic"><i class="glyphicon glyphicon-volume-up"></i></a><div>');

    	//无喇叭
    	alarmHeader.html('<div class="operate"><a id="online_vehicle_flush" title="点击刷新">实时在线<span id="index-middle-breadcrumb-online">0(0%)</span></a>'+
                        '<a id="offline_vehicle_flush" title="点击刷新">实时不在线<span id="index-middle-breadcrumb-offline">0(0%)</span></a>'+
                        '<a id="alarm_num">警情<span id="index-middle-breadcrumb-alarm">0</span></a><div>');
    	var onlineVehicleButton = alarmHeader.find('#online_vehicle_flush');
	    var offlineVehicleButton = alarmHeader.find('#offline_vehicle_flush');
	    var alarmButton = alarmHeader.find('#alarm_num');
    	
		onlineVehicleButton.on('click',function(){
			CamionView.alarmTab.selectTabItem('online');
			CamionView.onlineGrid.reload();
		});
		offlineVehicleButton.on('click',function(){
			CamionView.alarmTab.selectTabItem('offline');
			CamionView.offlineGrid.reload();
		});
		alarmButton.on('click',function(){
			CamionView.alarmTab.selectTabItem('alarm');
			CamionView.alarmGrid.reload();
		});
    	
    	var gridColumnsMap = {
    			gpsInfoGrid:[
				  	{show:'车牌号码',field:'numberPlate',width:145,align:'center'},
					{show:'车载号码',field:'callLetter',width:100,align:'center'},
					{show:'速度(km/h)',field:'speed',width:70,align:'center'},
					{show:'方向',field:'course',width:80,align:'center'},
					{show:'定位方式',field:'isLoc',width:100,align:'center'},
					{show:'状态',field:'status',width:170,align:'center',render:function(data){
				  		if(data.status.indexOf("、") != -1){
				  			return "<span style='color:red'>"+data.status+"</span>";
				  		}else{
				  			return data.status
				  		}
				  	}},
					{show:'电量(%)',field:'electricity',width:80,align:'center'},
					{show:'定位时间',field:'gpsTime',width:150,align:'center'},
					{show:'总里程(km)',field:'totalDistance',width:75,align:'center'},
					{show:'参考位置',field:'referencePosition',width:280,align:'center'},
					{show:'参考位置1',field:'referencePosition1',width:280,align:'center'},
					{show:'参考位置2',field:'referencePosition2',width:280,align:'center'},
					{show:'参考位置3',field:'referencePosition3',width:280,align:'center'},
					{show:'参考位置4',field:'referencePosition4',width:280,align:'center'},
					{show:'剩余油量(L)',field:'oil',width:75,align:'center'}
				],
    			instructionsInfoGrid:[
    				{show:'车牌号码',field:'numberPlate',width:100,align:'center'},
	              	{show:'车载号码',field:'callLetter',width:100,align:'center'},
	              	{show:'指令名称',field:'cmdName',width:160,align:'center'},
	              	{show:'指令状态',field:'cmdStatus',width:160,align:'center',render:function(data){
	            	  	if(data.cmdStatus=="终端未登录"){
	            		  	return "<span style='color:red' class='cmd-status' data-id='"+data.callLetter+","+data.sn+"'>"+"终端未应答"+"</span>";
	            	  	}else{
	            		  	return "<span style='color:red' class='cmd-status' data-id='"+data.callLetter+","+data.sn+"'>"+data.cmdStatus+"</span>";
	            	  	}
		            }},
		            {show:'发送时间',field:'sendTime',width:150,align:'center'},
	              	{show:'返回时间',field:'returnTime',width:150,align:'center',render:function(data){
	            	  	return "<span style='color:red' class='return-time' data-id='"+data.callLetter+","+data.sn+"'>"+data.returnTime+"</span>";
	              	}},
					/*{show:'指令编号',field:'sn',width:100,align:'center'}*/
				],
    		 	trgStatusGrid:[
    		 		{show:'车牌号码',field:'number_plate',width:100,align:'center'},
		            {show:'车载号码',field:'call_letter',width:100,align:'center'},
		            {show:'指令名称',field:'agency_name',width:100,align:'center'},
		            {show:'预存时间',field:'call_letter',width:100,align:'center'},
		            {show:'响应时间',field:'call_letter',width:100,align:'center'}
          		],
	    		alarmGrid:[{show:'操作',field:'handleAlarm',width:60,align:'center',render: showHandleAlarm},
    		        {show:'所属机构',field:'agencyName',width:150,align:'center'},
			        {show:'车牌号码',field:'plateNo',width:150,align:'center'},
		            {show:'速度(km/h)',field:'speed',width:100,align:'center'},
		            {show:'警情类型',field:'alarmTypeName',width:120,align:'center'},
		            {show:'方向1',field:'course1',width:100,align:'center'},
		            {show:'方向2',field:'course2',width:100,align:'center'},
		            {show:'定位时间1',field:'gpstime1',width:150,align:'center'},
		            {show:'定位时间2',field:'gpstime2',width:150,align:'center'},
		            {show:'开始时间',field:'beginstamp',width:150,align:'center'},
		            {show:'结束时间',field:'endstamp',width:150,align:'center'}
			    ],
			    onlineGrid:[
			    	{show:'车牌号码',field:'plateNo',width:150,align:'center'},
		            {show:'车载号码',field:'callLetter',width:150,align:'center'},
		            {show:'速度(km/h)',field:'speed',width:80,align:'center'},
		            {show:'方向',field:'course',width:80,align:'center',render:'showCourse'},
		            {show:'定位方式',field:'isLoc',width:80,align:'center',render:'showLoc'},
		            {show:'状态',field:'statusIds',width:150,align:'center',render:'showStatus'},
		            {show:'定位时间',field:'gpsTime',width:150,align:'center'},
		            {show:'参考位置',field:'referencePosition',width:250,align:'center',render: showReferencePosition},
		            {show:'所属机构',field:'agencyName',width:150,align:'center'}
			    ],
			    offlineGrid:[
			    	{show:'车牌号码',field:'plateNo',width:150,align:'center'},
		            {show:'车载号码',field:'callLetter',width:150,align:'center'},
	             	{show:'速度(km/h)',field:'speed',width:80,align:'center'},
            	 	{show:'方向',field:'course',width:80,align:'center',render:'showCourse'},
	             	{show:'定位方式',field:'isLoc',width:80,align:'center',render:'showLoc'},
	             	{show:'状态',field:'statusIds',width:150,align:'center',render:'showStatus'},
	             	{show:'定位时间',field:'gpsTime',width:150,align:'center'},
	             	{show:'参考位置',field:'referencePosition',width:250,align:'center',render: showReferencePosition},
	             	{show:'所属机构',field:'agencyName',width:150,align:'center'}
    			]
    	};
     
   		$("#gpsInfoGrid").on("contextmenu",'.l-grid-row', function (e){
	      	var menu = $.ligerMenu({
	        	top: 100,
	        	left: 100,
	        	width: 140,
	        	items:[
	                {text:'清除已选', click:function(){
	                  	// var selectCount=CamionView['gpsInfoGrid']._getSelecteds();
	                  	// console.log(selectCount);
	                  	// CamionView['gpsInfoGrid'].deleteRow(selectCount[0]['_index']);
	                  	CamionView['gpsInfoGrid'].deleteSelectedRow();
	                }}
	        	]
      		});
      		e.preventDefault();
      		menu.show({ top: e.pageY, left: e.pageX });
      		menu._id = this.id.split('|')[2];
      		CamionView['gpsInfoGrid'].select(this.id.split('|')[2]);
      		return false;
   		});
		function showReferencePosition(data){
				return "<a class='queryLocaltion' data-id='"+data.id+"'>"+data.referencePosition+"</a>";
		}
		function showHandleAlarm(data){
				return "<a class='queryLocaltion' style='cursor:pointer' data-id='"+data.id+"'>"+data.handleAlarm+"</a>";
		}
    	alarmGrids.each(function(index,grid){ 
    		var grid = $(grid);
    		var gridId = grid.attr('id');
    		var gridParms={
				onSuccess: function(data) {
					data.list = data.datas;
					data.count = data.datas.length;
					$("#gpsInfoGrid .l-panel-bar").remove();
					$("#instructionsInfoGrid .l-panel-bar").remove();
				},
        		onDblClickRow: function (data, rowindex, rowobj){
       				window.nowGridId = gridId;
					if(gridId==='onlineGrid'){
						var callLetters=[];
						callLetters.push(data.callLetter);
						GetLastPosition(callLetters);
					}
           			if(gridId =='gpsInfoGrid'){
						//var callLetters=[];
						//callLetters.push(data.callLetter);
						//GetLastPosition(callLetters);
						var a=CamionView.gpsInfoGrid.getRow(rowindex);
						var numberPlate=a.numberPlate;
						var callLetter=a.callLetter;
						var gpsTimeF=a.gpsTime;
						var lon;
						var lat;
						var isLoc=a.isLoc;
						var speed=a.speed*10;
						speed=parseFloat(speed)/10.0;
						var isloc=a.isloc;
						var electricity=a.electricity;
						var totalDistance=a.totalDistance;
						var status=a.status1;
						var course;
						if(a.course=='向北'){
							course=0;
						}else if(a.course=='向东'){
							course=90;
						}else if(a.course=='向南'){
							course=180;
						}else if(a.course=='向西'){
							course=270;
						}else if(a.course=='北偏西'){
							course=315;
						}else if(a.course=='北偏东'){
							course=45;
						}else if(a.course=='南偏西'){
							course=225;
						}else if(a.course=='南偏东'){
							course=135;
						}
						if(a.lat1==undefined){
							lat=a.lat;
						}else{
							lat=a.lat1;
						}
						if(a.lon1==undefined){
							lon=a.lon;
						}else{
							lon=a.lon1;
						}
						var accTimeLen=a.accTimeLen;
						var opts = {
								id: numberPlate,
								callLetter: callLetter,
								label: numberPlate + " " + gpsTimeF,
								numberPlate: numberPlate,
								lon: lon,
								lat: lat,
								isLoc: isLoc,
								speed: speed,
								course:course,
								gpsTime: gpsTimeF,
								electricity:electricity,
								totalDistance:totalDistance,
								status:status,
								accTimeLen:accTimeLen
						};
						var m = map.addOrUpdateVehicleMarkerById(opts);
						map.centerAndZoom(opts.lon, opts.lat, 16);
						window.nowGridId = null;
					}
					if(gridId==='offlineGrid'){
						var callLetters=[];
						callLetters.push(data.callLetter);
						GetLastPosition(callLetters);
					}
					if(gridId==='alarmGrid'){
						var callLetters=[];
							callLetters.push(data.callLetter);
							GetLastPosition(callLetters);
					}
          		},
				delayLoad: true,
				headerRowHeight:25,
				rowHeight:25,
				width: '100%',
				height: '100%',
				//heightDiff: 24,
				usePager: false,
				rownumbers:false,
				usePager:true,
				pageSizeOptions:[5,10,15,20], 
				page:1,
				pageSize:10,
				checkbox:false
			};
			if(index=='0'){
				gridParms.checkbox=true;
			}
			CamionView[gridId] = initMainGrid(gridId,gridParms,gridColumnsMap[gridId]);
			CamionView[gridId]._getSelecteds = _getSelecteds;
			CamionView[gridId].hasRow = _hasRow;
			CamionView[gridId]._removeData = _removeData;
			CamionView[gridId].getDataById = _getDataByGridId;
			CamionView[gridId]._addNewRecord = _addNewRecord;
		});

		CamionView.onlineGrid.setOptions('url','../gps/data/queryVehicleOnline');
		CamionView.onlineGrid.setOptions('onSuccess',referencePositionSuccess);
		CamionView.offlineGrid.setOptions('url','../gps/data/queryVehicleOffline');
		CamionView.offlineGrid.setOptions('onSuccess',referencePositionSuccess);
		function referencePositionSuccess(data){
			data.list = data.datas;
			data.count = data.datas.length;
			$(data.list).each(function(index,row){
				var startTime = index;
				row.id = row.callLetter +','+ startTime;
				row.startTime = index;
				row.startRequest = false;
				row.referencePosition = '查看参考位置';
			});
		}
				
		CamionView.alarmGrid.setOptions('url','../alarm/data/queryCurrentAlarmList');
		CamionView.alarmGrid.setOptions('onSuccess',function(data){
			data.list = data.datas;
			data.count = data.datas.length;
			//更新警情数量
			alarmButton.find('#index-middle-breadcrumb-alarm').text(data.count);
			$(data.list).each(function(index,row){
				var startTime = index;
					row.id = row.callLetter +','+ startTime;
					row.startTime = startTime;
					row.handleAlarm = '处警';
			});    	
		});
		
    	var timerTask = new TimerTask({
    		tick:300000, //60s
    		task:[
				//在线 and 不在线  数量更新
				function(){
					$.ajax({
					  type: 'POST',
					  url:'../gps/data/countOnOrOffline',
					  dataType:'json',
					  success: function(data){
						  if(!data.success) return;	 
						  var offCount = data.datas.off;
						  var onCount = data.datas.on;
						  var total = offCount + onCount;
						  onlineVehicleButton.find('#index-middle-breadcrumb-online').text(onCount+'('+(total ? (onCount/total*100).toFixed(2) : 0)+'%)');
						  offlineVehicleButton.find('#index-middle-breadcrumb-offline').text(offCount+'('+(total ? (offCount/total*100).toFixed(2) : 0)+'%)');
					  }
					});
				},
				function(){
					CamionView.alarmGrid.reload();
				}
    		]
    	});
    	timerTask.run();
  	}();
  
  	function _addNewRecord(o, previd, pid) {
    	var g = this, p = this.options;
    	g.recordNumber++;
    	o['__id'] = o.id;
    	o['__previd'] = previd;
    	if (previd && previd != -1){
      		var prev = g.records[previd];
      		if(prev['__nextid'] && prev['__nextid'] != -1){
        		var prevOldNext = g.records[prev['__nextid']];
    			if (prevOldNext)prevOldNext['__previd'] = o['__id'];
      		}
     	 	prev['__nextid'] = o['__id'];
      		o['__index'] = prev['__index'] + 1;
    	}else{
         	o['__index'] = 0;
    	}
    	if (p.tree){
      		if(pid && pid != -1){
        		var parent = g.records[pid];
        		o['__pid'] = pid;
        		o['__level'] = parent['__level'] + 1;
      		}else{
        		o['__pid'] = -1;
        		o['__level'] = 1;
      		}
      		o['__hasChildren'] = o[p.tree.childrenName] ? true : false;
    	}
    	o[p.statusName] = o[p.statusName] || "nochanged";
	    g.rows[o['__index']] = o;
	    g.records[o['__id']] = o;
	    return o;
  	}
  	//ligerGrid 获取所选 data 数组
  	function _getSelecteds(){
	    var that = this;
	    var gridDOM = $(this.element);
	    var selectRows = gridDOM.find('.l-grid2 .l-grid-body-table .l-grid-row.l-selected');
	    var datas = [];
		$(selectRows).each(function(index){
			datas.push(that.getRow(this.id.split('|')[2]));
		});
		return datas;
	}
  	//ligerGrid 按  data['id'] 判断是否存在
  	function _hasRow(id){
		var that = this;
		var gridDOM = $(this.element);
		var rows = gridDOM.find('.l-grid2 .l-grid-body-table .l-grid-row');
		var rowid = this.id+'|2|'+id;
		var flag = false;
		$(rows).each(function(){
			if(this.id === rowid) flag = true; 
		});
		return flag;
	}
 	// ligerGrid 按  data['id'] 移除
  	function  _removeData(rowdata){
			var g = this, p = this.options;
			var listdata = g.currentData[p.root];
			for(var i = listdata.length;i--;){
				if(listdata[i]['id'] === rowdata.id){
					listdata.splice(i, 1);
					break;
				}
			}
			g._removeSelected(rowdata);
	}
  	// ligerGrid 按  data['id'] 获取 data
  	function  _getDataByGridId(id){
			var g = this, p = this.options;
			var listdata = g.currentData[p.root];
			for(var i = listdata.length;i--;){
				if(listdata[i]['id'] === id){
					return listdata[i];
				}
			}
	}
  	// ligerTree 按 id 获取 data
  	// 修复 bug
  	function _getDataByID(id) {
		var g = this, p = this.options;
		var data = null;
		if (g.data && g.data.length) {
			return find(g.data);
		}
		function find(items) {
			var list = [];
			for (var i = 0; i < items.length; i++) {
				var dataItem = items[i];
				if (dataItem[p.idFieldName] == id) {
					var r = [];
					r.push(dataItem);
					return r;
				}
				if (dataItem.children && dataItem.children.length) {
					var a = find(dataItem.children);
					if (a.length >= 0)
						list = list.concat(a);
				}
			}
			return list;
		}
	};
  	function resizePage(){
    	clearTimeout(resizePage.timer);
    	resizePage.timer = setTimeout(function(){
    		$(window).trigger('resize');
	    	for(var i = resizePage.tasks.length;i--;){
	    		resizePage.tasks[i]();
	    	}
    	},100);
  	}

    function _getSrcElementByEvent(e){
		var g = this;
		var obj = (e.target || e.srcElement);
		var tag = obj.tagName.toLowerCase();
		var jobjs = $(obj).parents().add(obj);
		var fn = function (parm)
		{
			for (var i = jobjs.length - 1; i >= 0; i--)
			{
				if ($(jobjs[i]).hasClass(parm)) return jobjs[i];
			}
			return null;
		};
		if (jobjs.index(this.element) == -1) return { out: true };
		var r = {
			tree: fn("l-tree"),
			node: fn("l-body"),
			checkbox: fn("l-checkbox"),
			icon: fn("l-tree-icon"),
			text: tag == "span"
		};
		if (r.node)
		{
			var treedataindex = parseInt($(r.node).parent().attr("treedataindex"));
			r.data = g._getDataNodeByTreeDataIndex(g.data, treedataindex);
		}
		return r;
	}

  	resizePage.timer = null;
  	resizePage.tasks = [];
  	CamionView.resizePage = resizePage;
	~function(){
		resizePage.tasks.push(function(){
			var innerappend=$('#GPSMAX').parent().parent().parent();
			var GPSHeight=innerappend.height();
				$('#center-layout').height(GPSHeight);
				$('#map_canvas').height(GPSHeight);
		});		
	}();
	~function(){
		resizePage.tasks.push(function(){
			var innerappend=$('#GPSTR').parent().parent().parent();
			var GPSHeight=innerappend.height();
				$('#center-layout').height(GPSHeight);
				$('#map_canvas').height(GPSHeight);
		});		
	}();
});
$(window).on('load',function(){
  	$('.l-layout-collapse-right-toggle').remove();
  	$('.l-panel-bbar-inner .l-bar-group .l-bar-text').remove();
  	var trotl='<div class="l-icon l-icon-tl" id="trtl"></div>';
  	var tbott='<div class="l-icon l-icon-tb" id="tbtt"></div>';
  	// var grogl='<div class="l-icon l-icon-rl" id="tblr"></div>';
  	var str1='';
  		str1+='<div class="mapSmallicon" style="right:170px"; id="GPSTR"><img src="../images/carGIS/GPS7.png" />';
  		str1+='<p href="javascript:;" style="display:inline-block;line-height:20px;font-size:13px;" class="qingkongtb">清除标注</p></div>';
  		str1 += '<div class="mapSmallicon" style="right:252px"; id="GPSMAX"><img src="../images/carGIS/GPST7.png" />';
  		str1 += '<p href="javascript:;" id="baiduMap" style="display:inline-block;font-size:13px;line-height:20px;align:center;" class="maxMap">全屏</p></div>';
  		str1 += '<div class="mapSmallicon" id="GPSCEJU" onclick="ceju();return false;"><img style="width:16px;height:16px;margin-right: 2px;" src="../images/carGIS/ceju.png" />';
  		str1 += '<p  id="ceju" style="display:inline-block;line-height:20px;font-size:13px;align:center;">测距</p></div>';
  	var innerappend=$('#GPSTR1').parent().parent().parent();
  	//地图最上面的图表等。
  	var str2='';
  		str2+='<div id="GPSTop"><img src="../images/carGIS/GPS7.png" />';
  		str2+='<a href="javascript:;" style="display:inline-block;line-height:20px;" class="qingkongtb">清除标注</a></div>';
  	// $('#map_canvas').append(trotl+tbott+grogl+str1);
  	$('#map_canvas').append(trotl+tbott+str1);
  	$('#trtl').css({'position':'absolute','top':'50%','left':0,'z-index':10001,'cursor':'pointer'});
  	$('#tbtt').css({'position':'absolute','bottom':0,'left':'50%','z-index':10001,'cursor':'pointer'});
  	// $('#tblr').css({'position':'absolute','top':'50%','right':0,'z-index':1000});
  	var rtl=true;
  	var ttb=true;
  	var rrttll=true;
  	var maxFlag = false;
  	function getGPSHeight(){
    	getGPSHeight.timer = setTimeout(function(){
    		var GPSHeight=innerappend.height();
      		$('#center-layout').height(GPSHeight);
      		$('#map_canvas').height(GPSHeight);
    	},110);
  	}
  	getGPSHeight.timer = null; 
  	$(window).on('resize',getGPSHeight);
  	CamionView.resizePage();
  	$('#trtl').click(function(){
    	if(CamionView.resizePage) CamionView.resizePage();
		if($(this).hasClass('l-icon-tl')){
				$(this).attr('class','l-icon l-icon-tr');
				CamionView.mainLayout.setLeftCollapse(true);
		}else{
				$(this).attr('class','l-icon l-icon-tl');
				CamionView.mainLayout.setLeftCollapse(false);
		}
  	});
	$('#tbtt').click(function(){
		if(CamionView.resizePage) CamionView.resizePage();
		if($(this).hasClass('l-icon-tb')){
				$(this).attr('class','l-icon l-icon-tt');
				CamionView.mainLayout.centerBottomHeight = 1;
				CamionView.mainLayout.options.centerBottomHeight = 1;
				CamionView.mainLayout._onResize();
		}else{
				$(this).attr('class','l-icon l-icon-tb');
				CamionView.mainLayout.centerBottomHeight = 300;
				CamionView.mainLayout.options.centerBottomHeight = 300;
				CamionView.mainLayout._onResize();
		}
	});
  	// $('#tblr').click(function(){
  	//    if(CamionView.resizePage) CamionView.resizePage();
  	//      if($(this).hasClass('l-icon-rl')){
  	//        $(this).attr('class','l-icon l-icon-lr');
  	//          CamionView.centerLayout.setRightCollapse(false);
  	//      }else{
  	//          $(this).attr('class','l-icon l-icon-rl');
  	//          CamionView.centerLayout.setRightCollapse(true);
  	//      }
  	//  });
  	$('.qingkongtb').click(function(){
		clearAllMarker();
	});
  
  	$('.maxMap').click(function(){
	  	var parents = $(window.parent.document.body);
	  	if(!maxFlag){
		  	parents.addClass('hide-tree');
		  	$('#tbtt').addClass('l-icon-tb');
		  	$("#baiduMap").text("恢复");
		  	if(parents.hasClass("hide-tree")){
		  		$(".hide-tree .site-main .main-menu").css("width","0");
		  	}
		  	maxFlag = true;
	  	}else{
		  	parents.removeClass('hide-tree');
		  	$('#tbtt').removeClass('l-icon-tb');
		  	$("#baiduMap").text("全屏");
		  	maxFlag = false;
	  	}
	  	
	  	$('#tbtt').click();
	});
  	//车辆状态 鼠标移入移出
  	$('.statusName').hover(function(){$('.status').show();},function(){$('.status').hide();});
  	//地图最上部分声音按钮调控，有静音和有声的图片，需要切换。
  	$('.dituTopRight img').eq(7).click(function(){
    	var t=$(this);
	    if(t.hasClass('ys')){
	      t.attr('src','../images/carGIS/GPST9.png');
	      t.removeClass('ys');
	    }else{
	       t.attr('src','../images/carGIS/GPST8.png');
	       t.addClass('ys');
	    }
  	});
  	//鼠标移入地图，最上面的图表出现。移开，隐藏。
  	$('#center-layout').hover(function(){$('.dituTop').slideDown();},function(){$('.dituTop').slideUp();});
  	//车辆信息 图示说明
  	var str='';
  	str+='<div style="position:absolute;top:0;right:54px;" class="tsAll"><div class="tssm">';
	str+='<div><i class="l-icon l-icon-L1"></i> <span>在线车辆</span></div>';
  	str+='<div><i class="l-icon l-icon-L2"></i> <span>不在线车辆</span></div>';
  	str+='<div><i class="l-icon l-icon-R1"></i> <span>已在监控列表</span></div>';
  	str+='<div><i class="l-icon l-icon-R2"></i> <span>未在监控列表</span></div>';
  	str+='<div><i class="l-icon l-icon-rent-no"></i> <span>未出租</span></div>';
  	str+='<div><i class="l-icon l-icon-rent-yes"></i> <span>已出租</span></div>';
  	str+='<div><i class="l-icon l-icon-yellow_show"></i> <span>高风险车辆</span></div>';
  	str+='<div><i class="l-icon l-icon-red_show"></i> <span>逾期车辆</span></div>';
  	str+='<div><i class="l-icon l-icon-red_show2"></i> <span>出险</span></div>';
  	str+='<div><i class="l-icon l-icon-blue_show2"></i> <span>入库</span></div>';
  	str+='<div><i class="l-icon l-icon-you"></i> <span>有线设备</span></div>';
  	str+='<div><i class="l-icon l-icon-wu"></i> <span>无线设备</span></div>';
  	str+='<div><i class="l-icon l-icon-obd"></i> <span>OBD设备</span></div>';
  	str+='<div><i class="l-icon l-icon-ycxzj"></i> <span>子母机</span></div>';
  	str+='<div><i class="l-icon l-icon-rent-repair"></i> <span>维修</span></div>';
  	str+='<div><i class="l-icon l-icon-rent-stop"></i> <span>停运</span></div>';
  	str+='<div><i class="l-icon l-icon-rent-operate"></i> <span>准运营</span></div>';
  	str+='</div><div class="tssmZhu">图示说明</div></div>';
  	$('.l-layout-bottom .l-layout-header').append(str); 
  	$('.tssmZhu').hover(function(){
  		$('.tssm').show();
	},function(){
  		$('.tssm').hide();
	});
});


function showStatus(data){
	if(data.statusIds=='undefined'||data.statusIds==null){
		
	}else{
		var status = "";
		//状态特殊处理，只显示点火、熄火状态
		var statusArr = new Array();
		var tempArr = new Array();
		tempArr = data.statusIds.split(";");
		for ( var index in tempArr) {
			if(tempArr[index] ==23){
				statusArr.push(23);
				break;
			}else if(tempArr[index] ==33){
				statusArr.push(33);
				break;
			}
		}
		return status = SEGUtil.parseVehicleStatus(statusArr);
	}
}

function showCourse(data){
	return SEGUtil.getCourseDesc(data.course);
}

function showLoc(data){
	if(data.isLoc){
		return "卫星定位";
	}else{
		return "正在定位";
	}
}

//常跑轨迹
(function(){
     var targetCPGJ = $('#targetCPGJ');
     var dateStart = $("#txt_startTimeCPGJ").ligerDateEditor({
         format : "yyyy-MM-dd hh:mm:ss",
		 showTime:true,
         label : '',
         labelWidth : 80,
         labelAlign : 'center',
         cancelable : false
     });
     var dateEnd = $("#txt_endTimeCPGJ").ligerDateEditor({
         format : "yyyy-MM-dd hh:mm:ss",
		 showTime:true,
         label : '',
         labelWidth : 80,
         labelAlign : 'center',
         cancelable : false
     });
     var date = new Date();
	 var dateMonths=date.getMonth()+1;
	 var dateDate=date.getDate();
	 if(dateMonths<10){
		dateMonths="0"+dateMonths;
	 }
	 if(dateDate<10){
		dateDate="0"+dateDate;
	 }
     // var dataStart = date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate()+" 00:00:00";
	 var dataStart = date.getFullYear()+'-'+dateMonths+'-'+dateDate+" 00:00:00";
     function timeHMS(){	 
         var dateHours = date.getHours();
         var dateMinutes = date.getMinutes();
         var dateSeconds = date.getSeconds();
         if(dateHours<10){
             dateHours="0"+dateHours;
         }
         if(dateMinutes<10){
             dateMinutes="0"+dateMinutes;
         }
         if(dateSeconds<10){
             dateSeconds="0"+dateSeconds;
         }
         return dateHours+':'+dateMinutes+':'+dateSeconds;
     }
     var nowTime =timeHMS();
     var dataEnd = date.getFullYear()+'-'+dateMonths+'-'+dateDate+' '+nowTime;
     $("#txt_startTimeCPGJ").val(dataStart);
     $("#txt_endTimeCPGJ").val(dataEnd);
})();

//热点分析
(function(){
	var targetCPGJ = $('#targetHotSpot');
	var dateStart = $("#txt_startTimeHotSpot").ligerDateEditor({
		format : "yyyy-MM-dd hh:mm:ss",
		showTime:true,
		label : '',
		labelWidth : 80,
		labelAlign : 'center',
		cancelable : false
	});
	var dateEnd = $("#txt_endTimeHotSpot").ligerDateEditor({
		format : "yyyy-MM-dd hh:mm:ss",
		showTime:true,
		label : '',
		labelWidth : 80,
		labelAlign : 'center',
		cancelable : false
	});
	var date = new Date();
	var dateMonths=date.getMonth()+1;
	var dateDate=date.getDate();
	if(dateMonths<10){
		dateMonths="0"+dateMonths;
	}
	if(dateDate<10){
		dateDate="0"+dateDate;
	}
	// var dataStart = date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate()+" 00:00:00";
	var dataStart = date.getFullYear()+'-'+dateMonths+'-'+dateDate+" 00:00:00";
	function timeHMS(){	 
		var dateHours = date.getHours();
		var dateMinutes = date.getMinutes();
		var dateSeconds = date.getSeconds();
		if(dateHours<10){
			dateHours="0"+dateHours;
		}
		if(dateMinutes<10){
			dateMinutes="0"+dateMinutes;
		}
		if(dateSeconds<10){
			dateSeconds="0"+dateSeconds;
		}
		return dateHours+':'+dateMinutes+':'+dateSeconds;
	}
	var nowTime =timeHMS();
	var dataEnd = date.getFullYear()+'-'+dateMonths+'-'+dateDate+' '+nowTime;
	$("#txt_startTimeHotSpot").val(dataStart);
	$("#txt_endTimeHotSpot").val(dataEnd);
})();
