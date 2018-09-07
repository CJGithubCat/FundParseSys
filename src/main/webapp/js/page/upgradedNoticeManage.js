      
      $(function () { 
    	
    	//设置表格参数
    	 window.gridParms = {
            url: D2V.ggManageURL.query,
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
//            	console.log(data);
            	if(data.success==true){
            		var list=data.datas.list;
//            		console.log(list);
            		for(var k=0;k<list.length;k++){
//            			var startDate=new Date(list[k]['startDate']);
//            			var toEndDate=new Date(list[k]['toEndDate']);
//            			var strstartDate=startDate.Format("yyyy-MM-dd");
//            			var strtoEndDate=toEndDate.Format("yyyy-MM-dd");
//            			list[k]['startDate']=strstartDate;
//            			list[k]['toEndDate']=strtoEndDate;
            			//var createTime = new Date(list[k]['create_time']);
            			var createTime = list[k]['create_time'].substring(0,10);
            			//console.log(createTime);
            			//var createTimeStr=createTime.Format("yyyy-MM-dd");
            			//console.log(createTimeStr);
            			list[k]['create_time']=createTime;
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
                    { text: '添加', click: addNotice, icon: 'add'},
                    { text: '删除', click: deleteNotice, icon: 'delete'},
                    { text: '修改', click: updateNotice, icon: 'xiugai'},
                   // { text: '公告详情', click: NoticeDetail, icon: 'detail'}
                ]
            },
            delayLoad:false,
            onError: function (XMLHttpRequest, textStatus, errorThrown) {
            	console.log(textStatus, errorThrown);
            },
           width: '100%',
           parms: {
        	   
           },
           height: '100%',
           pageSizeOptions: [5,10,15,20],
           checkbox: false,
           rownumbers : false,
           onDblClickRow:function(data,rowid,rowdata) {
        	console.log(data);
           	var notice_title = data.notice_title;
           	var notice_content = data.notice_content;
           	var create_time = data.create_time;
           	
		     $('#detail-dialog h3').html(notice_title);
		     $('#gg_create_time').html(create_time);
		     $('#gg_notice_content').html(notice_content);
		     openDialog('detail-dialog',{title:'公告',width:600,height:450});
           	
           	
           },
           whenRClickToSelect:true
         };
    	 //添加操作选项
         /*var optcol = [
              {show: '操作', field: 'zzz0', width: 180, align: 'center', render: 'renderOpt'}
           ]; 
    	 */
          //window.mainGrid = initMainGrid('main-grid', gridParms, optcol.concat(D2V.cldkdq)); 
         //初始化表格(根据url加载表格数据)
         window.mainGrid = initMainGrid('main-grid', gridParms,D2V.ggManageInfo);          
        
    //添加公告
     $('#addSubmit').on('click',function(e){
    	 e.preventDefault();
     	var notice_title=$("#add-dialog input[name='notice_title']").val();
     	var notice_content = getEdit('add_notice_content').getData();
    	var create_time=$("#add-dialog input[name='create_time']").val();
//    	var notice_content=$("#add_notice_content").val();
//    	var noticeObj=getFormAjaxData('add-form');
//    	console.log(noticeObj);
//    	console.log(noticeObj.notice_content);
//    	var notice_title = noticeObj.notice_title;
//    	var notice_content = noticeObj.notice_content;
//    	var create_time = noticeObj.create_time;
        if(notice_title==null||notice_title==''){
      	  $.ligerDialog.warn("公告标题不能为空!"); 
            return; 
         }
         if(notice_content==null||notice_content==''){
      	   $.ligerDialog.warn("公告内容不能为空!"); 
             return; 
          }
         if(create_time==null||create_time==''){
      	   $.ligerDialog.warn("公告创建时间不能为空!"); 
             return; 
          }
         if(notice_title.length>25){
 			$.ligerDialog.warn("公告标题过长,请重新输入");
 			return ;
 		}
         $('#add-form').trigger("submit");
     });
     $('#add-form').submit(function(e){
        e.preventDefault();
        var noticeObj=getFormAjaxData('add-form');
        var notice_content = getEdit('add_notice_content').getData();
        var addObj = {
				"notice_title" : noticeObj.notice_title,
				"notice_content" : notice_content,
				"create_time" : noticeObj.create_time
			};
        console.log(noticeObj);
        $.ajax({
           url:D2V.ggManageURL.add,
           type:"POST",
           data:addObj,  
           dataType:'json',
           success:function(rsp){
             if(rsp.success==true){
        		 $.ligerDialog.success(rsp.message);
                 closeDialog('add-dialog');
                 mainGrid.reload();
        	 }else{
        		 $.ligerDialog.warn('新增公告失败,请重新添加!');
                 return; 
        	 }
           },
           error:function(xhr,status,err){
              console.log(status,err);
              $.ligerDialog.warn('增加公告失败,网络故障!');
              return; 
           }
       	 })
     }); 
    
     //	
     (function(){
			var edits = {};
			window.initEdit = function(textareaId){
				var textarea = $('#'+textareaId);
				if(textarea.css('visibility') !== 'hidden')edits[textareaId] = CKEDITOR.replace( textareaId);
			}
			window.getEdit = function(textareaId){
				return edits[textareaId];
			}
		})();
   //修改公告
    $('#updateSubmit').on('click',function(e){
    	e.preventDefault();
    	var selectedRow=mainGrid.getSelectedRow();
    	if(selectedRow==null){
    		$.ligerDialog.warn('请选中要刪除的公告!'); 
   	        return;
    	 }
    	var notice_id = selectedRow.notice_id;
    	var notice_title=selectedRow.notice_title;
    	var notice_content=selectedRow.notice_content;
    	var create_time=selectedRow.create_time;
       if(notice_title==null||notice_title==''){
    	  $.ligerDialog.warn("公告标题不能为空!"); 
          return; 
       }
       if(notice_content==null||notice_content==''){
    	   $.ligerDialog.warn("公告内容不能为空!"); 
           return; 
        }
       if(create_time==null||create_time==''){
    	   $.ligerDialog.warn("公告创建时间不能为空!"); 
           return; 
        }
       $('#update-form').trigger("submit");
     });
    $('#update-form').submit(function (e){
       e.preventDefault();
       var noticeObj=getFormAjaxData('update-form');
       var notice_content = getEdit('update_notice_content').getData();
       var addObj = {
    		    "notice_id":noticeObj.notice_id,
				"notice_title" : noticeObj.notice_title,
				"notice_content" : notice_content,
				"create_time" : noticeObj.create_time
			};
       
       console.log(addObj);
       
       $.ajax({
           url:D2V.ggManageURL.update,
           type:"POST",
           data:addObj, 
           dataType:'json',
           success:function(rsp){
             if(rsp.success==true){
        		 $.ligerDialog.success(rsp.message);
        		 closeDialog('update-dialog');
                 mainGrid.reload();
        	 }else{
        		 $.ligerDialog.warn('修改公告失败,请重新修改!');
                 return; 
        	 }
           },
           error:function(xhr,status,err){
              console.log(status,err);
              $.ligerDialog.warn('修改公告失败,网络故障!');
              return; 
           }
       	 })
     });
   })
 	 	
//-------------------------------------------------------------------------------------------------
 	//查询公告
 	 function findNotice(){
	    var searchData={};
	    var formParam = getFormDataAll('noticeformid');
  	  	console.log(formParam);/////////////
  	  	
  	  	 var startDate=formParam.startDate; 
		 var toEndDate=formParam.toEndDate; 
		 var d1 = new Date(startDate.replace(/-/g, "/")); 
		 var d2 = new Date(toEndDate.replace(/-/g, "/")); 
		  if(startDate!=""&&toEndDate!=""&& (d1 >=d2)){
			  $.ligerDialog.warn('请注意：查询的开始时间不能大于结束时间!'); 
	   	       return false;
		  }
  	 
		  for(var k in formParam){
             var newdata;
             newdata=$.trim(formParam[k]);
             if(newdata!=''){
            	 if(newdata.notice_title!=''){
            		 newdata.notice_title = encodeURIComponent($.trim($("#notice_titleID").val()));
            	 }
                 searchData[k]=newdata;
                 mainGrid.setParm(k,newdata);
             }else{
                 mainGrid.removeParm(k);
                 delete formParam[k];
             }
	      }
          formParam.page = 1;
          var rp_value = $("select[name='rp']").val();
          formParam.pagesize = rp_value;
          $(".l-panel-bar").find(".l-bar-btnfirst").trigger("click");
          $("#main-grid").ligerGetGridManager().loadServerData(formParam);
    }
 	//点击新增按钮
  	function addNotice(){
       clearForm('add-form');  
       initEdit('add_notice_content');
	    getEdit('add_notice_content').setData('');
       openDialog(
          'add-dialog',
           {
            title:'新增公告',
            width:550,height:500
           },
           function dialog(){}
         );
      }
    //点击修改按钮
     function updateNotice(){    	
    	clearForm('update-form'); 
    	var selectedRow = mainGrid.getSelectedRow();
    	if(selectedRow==null){
   		    $.ligerDialog.warn('请选中要修改的公告!'); 
   	        return;
   	    }
    	/*if(selectedRow.state==1){
    		$.ligerDialog.warn('该规则不可修改'); 
   	        return;
    	}*/
    	$("#update-dialog input[name='notice_id']").prop('value',selectedRow.notice_id);
    	$("#update-dialog input[name='notice_title']").prop('value',selectedRow.notice_title); 
    	$("#update_notice_content").prop('value',selectedRow.notice_content);
   	    $("#update-dialog input[name='create_time']").prop('value',selectedRow.create_time);
   	   // $("#update-dialog select[name='ruleType'] option[value="+selectedRow.ruleType+"]").prop("selected", true);
   	   //$("#update-dialog select[name='state'] option[value="+selectedRow.state+"]").prop("selected", true);
       // $("#update-dialog input[name='remark']").prop('value',selectedRow.remark);
        openDialog(
                'update-dialog',
                 {
                  title:'修改公告',
                  width:550,height:500
                 },
                 function dialog(){}
         );
        initEdit('update_notice_content');
       	getEdit('update_notice_content').setData(selectedRow.notice_content);
      } 
    function deleteNotice(){
      var selectedRow=mainGrid.getSelectedRow();
      selectedRow!=null ? $.ligerDialog.confirm('确定是否删除公告?', function(yes){
    	 if(yes===false) return;
    	 if(selectedRow==null){
    		$.ligerDialog.warn('请选中要刪除的公告!'); 
   	        return;
    	 }
    	 $.ajax({
    	        type: "POST",
    	        url:D2V.ggManageURL.deleteRow,
    	        //contentType:'application/json;charset=utf-8',
    	        dataType:'json',
    	        data:{"notice_id":selectedRow.notice_id},
    	        error:function(xhr,status,err){
    	            $.ligerDialog.error('删除公告失败,网络故障!');
    	            return;
    	        },
    	        success:function(data){
    	          if(data.success===true){
    	             $.ligerDialog.success(data.message);
    	             findNotice();
    	          }else{
    	             $.ligerDialog.error(data.message);
    	          }
    	       }            
    	    });	
       }) : $.ligerDialog.warn('请选择要刪除的公告!');
    }
    
