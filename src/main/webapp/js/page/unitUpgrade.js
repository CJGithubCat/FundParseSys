      
      $(function () { 
    	
    	//设置表格参数
    	 window.gridParms = {
            //工具栏            
            toolbar: {
                items: [
                    { text: '添加', click: addUpgrade, icon: 'add'},
                    { text: '删除', click: deleteUpgradeParams, icon: 'delete'},
                    { text: '修改', click: updateUpgrade, icon: 'xiugai'}
                ]
            },
            url:D2V.remoteUpgradeUnit.queryRemoteUpgrade,
            delayLoad:false,
            onError: function (XMLHttpRequest, textStatus, errorThrown) {
            	console.log(textStatus, errorThrown);
            },
           width: '100%',
           height: '100%',
           pageSizeOptions: [5,10,15,20],
           checkbox: false,
           rownumbers : false,
           whenRClickToSelect:true
         };
         //初始化表格(根据url加载表格数据)
         window.mainGrid = initMainGrid('main-grid', gridParms,D2V.queryUpgradeParams);          
        
    //添加升级参数
     $('#addSubmit').on('click',function(e){
    	 	if (!JudgeValidate('#add-form')) {
				return false;
			}    	
			//文件路径
			var filepathary =[];
			var filenameary=[];
			var add_filePath=$('input[name=add_filePath]');
			var add_fileName=$('input[name=add_fileName]');
			add_fileName.each(function(){
				filenameary.push($(this).val());
			});
			add_filePath.each(function(){
				filepathary.push($(this).val());
			});
			var  results = "";
			for(var o=0;o<add_filePath.length;o++){
				results += filepathary[o]+","+filenameary[o]+";";
			}
			 datas =JSON.stringify({
	        	   unitTypeId : $('#add_unittype_id').val(),
	        	   ip : $('#add_ip').val(),
	        	   port : $('#add_port').val(),
	        	   userName : $('#add_userName').val(),
	        	   upgradeVersion : $('#add_upgrade_version').val(),
	        	   realUpgradeVersion : $('#add_real_upgrade_version').val(),
	        	   password : $('#add_password').val(),
	        	   fileInfo:results//文件
	           })
         $('#add-form').trigger("submit");
     });
     $('#add-form').submit(function(e){
        e.preventDefault();
        $.ligerDialog.confirm('添加前请确认参数无误!', function(yes){
        $.ajax({
           url:D2V.remoteUpgradeUnit.addUpgradeParams,
           type:"POST",
           data:datas,             
           contentType: "application/json; charset=utf-8",
           dataType:'json',
           success:function(rsp){
             if(rsp.success==true){
        		 $.ligerDialog.success(rsp.message);
                 closeDialog('add-dialog');
                 mainGrid.reload();
        	 }else{
        		if(rsp.errorCode===1){//该终端类型，已存在相同的升级版本
         		   $.ligerDialog.warn(rsp.message+",升级版本已经存在!"); 
         		}else{
                   $.ligerDialog.warn(rsp.message);
         		}                         
             }
           },
           error:function(xhr,status,err){
              console.log(status,err);
              $.ligerDialog.warn('增加参数失败,网络故障!');
              return; 
           }
       	 })
        })
     }); 
     
   //修改升级参数
    $('#updateSubmit').on('click',function(e){
    	if (!JudgeValidate('#update-form')) {
			return false;
		}    
   	   //文件路径
		var filepathary =[];
		var filenameary=[];
		var add_filePath=$('input[name=update_filePath]');
		var add_fileName=$('input[name=update_fileName]');
		add_fileName.each(function(){
			filenameary.push($(this).val());
		});
		add_filePath.each(function(){
			filepathary.push($(this).val());
		});
		var  results = "";
		for(var o=0;o<add_filePath.length;o++){
			results += filepathary[o]+","+filenameary[o]+";";
		}
	   	 datas =JSON.stringify({
	   		remoteId:$("#remoteId").val(),
	  	   unitTypeId : $('#update_unittype_id').val(),
	  	   ip : $('#update_ip').val(),
	  	   port : $('#update_port').val(),
	  	   userName : $('#update_userName').val(),
	  	   upgradeVersion : $('#update_upgrade_version').val(),
	  	   realUpgradeVersion : $('#update_real_upgrade_version').val(),
	  	   beforeUpgradeVersion: $('#beforeUpgradeVersion').val(),
	  	   password : $('#update_password').val(),
	  	   fileInfo:results//文件
	     })
       $('#update-form').trigger("submit");
     });
    $('#update-form').submit(function (e){
    	
       e.preventDefault();
       $.ligerDialog.confirm('修改前请确认参数无误!', function(yes){
       $.ajax({
           url:D2V.remoteUpgradeUnit.updateUpgradeParams,
           type:"POST",
           data:datas,  
           contentType: "application/json; charset=utf-8",
           dataType:'json',
           success:function(rsp){
             if(rsp.success==true){
        		 $.ligerDialog.success(rsp.message);
        		 closeDialog('update-dialog');
                 mainGrid.reload();
        	 }else{
        		if(rsp.errorCode===1){//该终端类型存在同一升级版本
         		   $.ligerDialog.warn(rsp.message+",升级版本已经存在!"); 
         		}else{
                   $.ligerDialog.warn(rsp.message);
         		}                         
             }
           },
           error:function(xhr,status,err){
              console.log(status,err);
              $.ligerDialog.warn('修改升级参数,网络故障!');
              return; 
           }
       	 })
       })
     });
   })
    //删除按钮的内容
    	    function changeZJRow(node){
    	        node.each(function(i,elem){
    	            if(i>0){
    	                $(elem).find('img').attr('src','../images/deleteZJZL.png');
    	                $(elem).find('img').attr('class','sczjzl').attr('title','删除');
    	            }
    	        });
    	    }
 	 	
//-------------------------------------------------------------------------------------------------
 	//点击新增升级参数
  	function addUpgrade(){
       clearForm(
    		   'add-form',
    		   {attributesPath:true,agencyId:true}
    		  );  
       openDialog(
          'add-dialog',
           {
            title:'新增升级参数',
            width:716
           },
           function dialog(){}
         );
      }
    //点击修改升级参数
     function updateUpgrade(){    	
    	clearForm('update-form'); 
    	var selectedRow = mainGrid.getSelectedRow();
    	if(selectedRow==null){
   		    $.ligerDialog.warn('请选中要修改的参数!'); 
   	        return;
   	    }
    	console.log(selectedRow)
    	$('#update-form .onlyrow').next().remove();
    	var files = (selectedRow.filePathName).split(";");
    	var addNumber = files.length;
    	if(addNumber>=2){
    		for(var i =1;i<addNumber;i++){
        		$('#update-form .onzjzl').append($('#update-form .zjzlrow').eq(0).clone());
        		var zjzlRow=$('#update-form .zjzlrow');
    			changeZJRow(zjzlRow);
        	}
    	}
    	var selectedvalue =selectedRow.unittypeName;
    	switch(selectedvalue){
    		case 'TGO03-01':
    			selectedvalue=1055;
    			break;
    		case 'TRG90-03B':
    			selectedvalue=1056;
    			break;
    		case 'TRG90-03D':
    			selectedvalue=1057;
    			break;
    		case 'TRG80-03D':
    			selectedvalue=1058;
    			break;
    	}
    	$("#update-dialog input[name='update_ip']").prop('value',selectedRow.ip);//IP地址
    	$("#update-dialog input[name='update_port']").prop('value',selectedRow.port); //端口号
   	    $("#update-dialog input[name='update_upgrade_version']").prop('value',selectedRow.upgradeVersion);//升级版本号
   	    $("#update-dialog input[name='update_real_upgrade_version']").prop('value',selectedRow.realUpgradeVersion);//真实升级版本号
   	    $("#update-dialog input[name='update_userName']").prop('value',selectedRow.username);//用户名
   	    $("#update-dialog input[name='update_password']").prop('value',selectedRow.password);//密码
   	    $("#update-dialog select[name='update_unittype_id'] option[value="+selectedvalue+"]").prop("selected", true);//终端类型
   	    $("#beforeUpgradeVersion").val(selectedRow.upgradeVersion);//之前升级版本号
   	    $("#remoteId").val(selectedRow.remoteId);
	   	 var aryupdate = [];
	 	for(var i=0;i<files.length;i++){
	 		aryupdate.push(files[i].split(","));
	 	}
	 	var updatefilevalue = [];
	 	for(var kk=0;kk<aryupdate.length;kk++){
	 		$("#update-dialog input[name='update_filePath']").eq(kk).prop('value',aryupdate[kk][0]);//文件路径
	 		$("#update-dialog input[name='update_fileName']").eq(kk).prop('value',aryupdate[kk][1]);//文件扩展名
		}
        openDialog(
                'update-dialog',
                 {
                  title:'修改升级终端参数',
                  width:716
                 },
                 function dialog(){}
         );
      } 
     
     //搜索
     function findUpgradeParams(){
         var searchData={};
         var formParam = getFormDataAll('searchForm');
         for(var k in formParam){
            var newdata;
            newdata=$.trim(formParam[k]);
            if(newdata!=''){
                searchData[k]=newdata;
                mainGrid.setParm(k,newdata);
            }else{
                mainGrid.removeParm(k);
            }
        }
         formParam.unittype_id = $("#unittype_id").val();
         formParam.page = 1;
         var rp_value = $("select[name='rp']").val();
         formParam.pagesize = rp_value;
        $("#main-grid").ligerGetGridManager().loadServerData(formParam);
        $("#main-grid").find(".l-bar-btnfirst").trigger("click");
        
     }
     //删除
    function deleteUpgradeParams(){
      var selectedRow=mainGrid.getSelectedRow();
    	 if(selectedRow==null){
    		$.ligerDialog.warn('请选中要刪除的终端升级参数!'); 
   	        return;
    	 }
    	 $.ligerDialog.confirm('即将删除选中数据,请谨慎操作!', function(yes){
    	 $.ajax({
    	        type: "POST",
    	        url:D2V.remoteUpgradeUnit.deleteUpgradeParams,
    	        dataType:'json',
    	        data:{"remoteId":selectedRow.remoteId,
    	        	  "upgradeVersion":selectedRow.upgradeVersion
    	            },
    	        error:function(xhr,status,err){
    	            $.ligerDialog.error('删除终端升级参数失败,网络故障!');
    	            return;
    	        },
    	        success:function(data){
    	          if(data.success===true){
    	             $.ligerDialog.success(data.message);
    	             findUpgradeParams();
    	          }else{
    	             $.ligerDialog.error(data.message);
    	          }
    	       }            
    	    });	
    	 })
    }
    $(function(){
    	//点击增加文件路径和文件扩展名按钮事件
    	   $('#add-dialog .zjzjzl').click(function(){
    	        if($('#add-form .zjzlrow').length==4){
    	            $.ligerDialog.warn('<span style="color:red;padding-top:6px;font-size:14px;display:inline-block;">文件路径和文件扩展名</span>'+' 不能超过4个!');
    	            return;
    	        }
    	        var clone=$(this).parent().clone();
    	        $(this).parent().parent().append(clone);
    	        var t=$('#add-form').find('.zjzlrow');
    	        changeZJRow(t);
    	    });
    	  
    	 
    	  //事件委托，点击删除按钮事件
    	    $('#add-form .onzjzl').on('click','.sczjzl',function(){
                $(this).parent().remove();
                var t=$('#add-form .zjzlrow');
                changeZJRow(t);
            });
    	    //修改
     	   $('#update-form .zjzjzl').click(function(){
 	   	        if($('#update-form .zjzlrow').length==4){
 	   	            $.ligerDialog.warn('<span style="color:red;padding-top:6px;font-size:14px;display:inline-block;">文件路径和文件扩展名</span>'+' 不能超过4个!');
 	   	            return;
 	   	        }
 	   	        var clone=$(this).parent().clone();
 	   	        $(this).parent().parent().append(clone);
 	   	        var t=$('#update-form').find('.zjzlrow');
 	   	        changeZJRow(t);
 	   	    });
	     	//事件委托，点击删除按钮事件
	   	    $('#update-form .onzjzl').on('click','.sczjzl',function(){
	               $(this).parent().remove();
	               var t=$('#update-form .zjzlrow');
	               changeZJRow(t);
	           });
    })
  
