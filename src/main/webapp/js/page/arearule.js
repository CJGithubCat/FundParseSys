      
      $(function () { 
    	
    	//设置表格参数
    	 window.gridParms = {
            url: D2V.areaManageURL.queryAreaRule,
            onLoaded: function (grid) {
              setTimeout(function () {
                 $('.l-grid-loading').hide();
                 $(".l-grid-row-cell-inner").each(function () {
                    var that = $(this);
                    that.attr('title', that.text());
                 })
              }, 200);
            },
            onSuccess : function(data) {
            	if(data.success==true){
            		var list=data.datas.list;
            		for(var k=0;k<list.length;k++){
            			var startTime=new Date(list[k]['startTime']);
            			var endTime=new Date(list[k]['endTime']);
            			var strStartTime=startTime.Format("yyyy-MM-dd");
            			var strEndTime=endTime.Format("yyyy-MM-dd");
            			list[k]['startTime']=strStartTime;
            			list[k]['endTime']=strEndTime;
            		}
            		data.list=data.datas.list;
            		data.count=data.datas.count;
            	}else{
            		$.ligerDialog.warn(data.message);
            	}
    		},
            //工具栏            
            toolbar: {
                items: [
                    { text: '添加', click: addRule, icon: 'add'},
                    { text: '删除', click: deleteRule, icon: 'delete'},
                    { text: '修改', click: updateRule, icon: 'xiugai'}
                ]
            },
            delayLoad:false,
            onError: function (XMLHttpRequest, textStatus, errorThrown) {
            	console.log(textStatus, errorThrown);
            },
           width: '100%',
           parms: {
             "ruleType":-1,//默认所有类型
             //"ruleDeelType":-1,//默认所有规则事件
             ///"state":-1  // 可否修改默认所有         
           },
           height: '100%',
           pageSizeOptions: [5,10,15,20],
           checkbox: false,
           rownumbers : false,
           /*onDblClickRow : function (data, rowindex, rowobj){                               
                               updateUser(rowindex,true);
                           },*/
           /*onRClickToSelect:false,*/
           /*onContextmenu : function (parm,e)
           {//param是个对象包含三个参数 {data,rowindex,row}
               menu.show({ top: e.pageY, left: e.pageX });
               rowIndex=parm.rowindex;
               return false;
           },*/
           whenRClickToSelect:true
         };
    	 //添加操作选项
         /*var optcol = [
              {show: '操作', field: 'zzz0', width: 180, align: 'center', render: 'renderOpt'}
           ]; 
    	 */
          //window.mainGrid = initMainGrid('main-grid', gridParms, optcol.concat(D2V.cldkdq)); 
         //初始化表格(根据url加载表格数据)
         window.mainGrid = initMainGrid('main-grid', gridParms,D2V.areaManageRuleGrid);          
        
    //添加规则
     $('#addSubmit').on('click',function(e){
    	var ruleNameObj=$("#add-dialog input[name='ruleName']");
    	var startTimeObj=$("#add-dialog input[name='startTime']");
    	var endTimeObj=$("#add-dialog input[name='endTime']");
        var ruleName=$.trim(ruleNameObj.val());
        var startTime=$.trim(startTimeObj.val());
        var endTime=$.trim(endTimeObj.val());
        if(ruleName==null||ruleName==''){
        	ruleNameObj.val("");
        	ruleNameObj.focus();
        	$.ligerDialog.warn("规则名不能为空");
            return; 
         }
        if(startTime==null||startTime==''){
        	startTimeObj.val("");
        	startTimeObj.focus();
            return; 
         }
        if(endTime==null||endTime==''){
        	endTimeObj.val("");
        	endTimeObj.focus();
            return; 
         }
        var d1 = new Date(startTime.replace(/\-/g, "\/")); 
        var d2 = new Date(endTime.replace(/\-/g, "\/")); 
        if(d1>=d2){
        	$.ligerDialog.warn("开始时间不能大于等于结束时间");
        	return;
        }
        if(ruleNameObj.val().length>20){
			$.ligerDialog.warn("规则名过长,请重新输入");
			return ;
		}
         $('#add-form').trigger("submit");
     });
     $('#add-form').submit(function(e){
        e.preventDefault();
        var areaRuleObj=getFormAjaxData('add-form');
        console.log(areaRuleObj);
        $.ajax({
           url:D2V.areaManageURL.addAreaRule,
           type:"POST",
           data:JSON.stringify(areaRuleObj),  
           contentType: "application/json; charset=utf-8",
           dataType:'json',
           success:function(rsp){
             if(rsp.success==true){
        		 $.ligerDialog.success(rsp.message);
                 closeDialog('add-dialog');
                 mainGrid.reload();
        	 }else{
        		if(rsp.errorCode===1){//规则名已经存在
         		   $.ligerDialog.warn(rsp.message+",规则名称已经存在!"); 
         		}else{
                   $.ligerDialog.warn(rsp.message);
         		}                         
             }
           },
           error:function(xhr,status,err){
              console.log(status,err);
              $.ligerDialog.warn('增加规则名称失败,网络故障!');
              return; 
           }
       	 })
     }); 
     
   //修改规则
    $('#updateSubmit').on('click',function(e){
       var ruleNameObj=$("#update-dialog input[name='ruleName']");
       var ruleName=$.trim(ruleNameObj.val());
       var startTimeObj=$("#update-dialog input[name='startTime']");
   	   var endTimeObj=$("#update-dialog input[name='endTime']");
   	   var startTime=$.trim(startTimeObj.val());
       var endTime=$.trim(endTimeObj.val());
       if(ruleName==null||ruleName==''){
    	  ruleNameObj.val(ruleName);
    	  ruleNameObj.focus();
    	  $.ligerDialog.warn("规则名不能为空");
          return; 
       }
       if(startTime==null||startTime==''){
       	   startTimeObj.val("");
       	   startTimeObj.focus();
           return; 
        }
       if(endTime==null||endTime==''){
       	   endTimeObj.val("");
       	   endTimeObj.focus();
           return; 
        }
       var d1 = new Date(startTime.replace(/\-/g, "\/")); 
       var d2 = new Date(endTime.replace(/\-/g, "\/")); 
       if(d1>=d2){
       	$.ligerDialog.warn("开始时间不能大于等于结束时间");
       	return;
       }
       if(ruleNameObj.val().length>20){
			$.ligerDialog.warn("规则名过长,请重新输入");
			return ;
		}
       $('#update-form').trigger("submit");
     });
    $('#update-form').submit(function (e){
       e.preventDefault();
       var areaRuleObj=getFormAjaxData('update-form');
       console.log(areaRuleObj);
       $.ajax({
           url:D2V.areaManageURL.updateAreaRule,
           type:"POST",
           data:JSON.stringify(areaRuleObj),  
           contentType: "application/json; charset=utf-8",
           dataType:'json',
           success:function(rsp){
             if(rsp.success==true){
        		 $.ligerDialog.success(rsp.message);
        		 closeDialog('update-dialog');
                 mainGrid.reload();
        	 }else{
        		if(rsp.errorCode===1){//更改规则名了,但更改的规则名已经存在
         		   $.ligerDialog.warn(rsp.message+",规则名称已经存在!"); 
         		}else{
                   $.ligerDialog.warn(rsp.message);
         		}                         
             }
           },
           error:function(xhr,status,err){
              console.log(status,err);
              $.ligerDialog.warn('修改规则名称失败,网络故障!');
              return; 
           }
       	 })
     });
   })
 	 	
//-------------------------------------------------------------------------------------------------
 	//查询规则
 	 function findAreaRule(){    	  
  	  var formParam = getFormDataAll('arearuleformid');
	      for ( var k in formParam) {
		   var newdata;
		   newdata = $.trim(formParam[k]);
		   if (newdata != '') {
			 mainGrid.setParm(k, newdata);
		   } else {
			 mainGrid.removeParm(k);
			 delete formParam[k];
		   }
		 }
		 formParam.page = 1;
		 var rp_value = $("select[name='rp']").val();
	     formParam.pagesize = rp_value;
		 mainGrid.changePage('first');	
		 $("#main-grid").ligerGetGridManager().loadServerData(formParam);
    }
 	//点击新增规则按钮
  	function addRule(){
       clearForm(
    		   'add-form',
    		   {attributesPath:true,agencyId:true}
    		  );  
       openDialog(
          'add-dialog',
           {
            title:'新增区域规则',
            width:440,height:390
           },
           function dialog(){}
         );
      }
    //点击修改规则按钮
     function updateRule(){    	
    	clearForm('update-form'); 
    	var selectedRow = mainGrid.getSelectedRow();
    	if(selectedRow==null){
   		    $.ligerDialog.warn('请选中要修改的规则!'); 
   	        return;
   	    }
    	/*if(selectedRow.state==1){
    		$.ligerDialog.warn('该规则不可修改'); 
   	        return;
    	}*/
    	$("#update-dialog input[name='ruleId']").prop('value',selectedRow.ruleId);
    	$("#update-dialog input[name='ruleName']").prop('value',selectedRow.ruleName); 
   	    $("#update-dialog input[name='startTime']").prop('value',selectedRow.startTime);
   	    $("#update-dialog input[name='endTime']").prop('value',selectedRow.endTime);
   	    $("#update-dialog select[name='ruleType'] option[value="+selectedRow.ruleType+"]").prop("selected", true);
   	   //$("#update-dialog select[name='state'] option[value="+selectedRow.state+"]").prop("selected", true);
        $("#update-dialog input[name='remark']").prop('value',selectedRow.remark);
        openDialog(
                'update-dialog',
                 {
                  title:'修改区域规则',
                  width:440,height:390
                 },
                 function dialog(){}
         );
      } 
    function deleteRule(){
      var selectedRow=mainGrid.getSelectedRow();
      selectedRow!=null ? $.ligerDialog.confirm('删除规则也会删除规则和车辆的绑定关系,确定是否删除?', function(yes){
    	 if(yes===false) return;
    	 if(selectedRow==null){
    		$.ligerDialog.warn('请选中要刪除的规则!'); 
   	        return;
    	 }
    	 $.ajax({
    	        type: "POST",
    	        url:D2V.areaManageURL.deleteAreaRule,
    	        //contentType:'application/json;charset=utf-8',
    	        dataType:'json',
    	        data:{"ruleId":selectedRow.ruleId},
    	        error:function(xhr,status,err){
    	            $.ligerDialog.error('删除规则名称失败,网络故障!');
    	            return;
    	        },
    	        success:function(data){
    	          if(data.success===true){
    	             $.ligerDialog.success(data.message);
    	             findAreaRule();
    	          }else{
    	             $.ligerDialog.error(data.message);
    	          }
    	       }            
    	    });	
       }) : $.ligerDialog.warn('请选择要刪除的规则!');
    }
    
