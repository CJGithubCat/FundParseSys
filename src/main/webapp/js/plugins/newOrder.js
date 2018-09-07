$(function(){
	 //点击添加按钮事件,主动触发。
            $('#addSubmit').on('click',function(e){
            	var callback =function(){
            		closeAdd();
            	}
            	addOrder("add-form",D2V.orderURL.addOrder,callback)
            });
            $('#addRepairSubmit').on('click',function(e){
            	var callback =function(){
            		closeRepairAdd();
            	}
            	addOrder("add-repair-form",D2V.orderURL.addServiceOrder,callback)
            });
            $('#UpdateSubmit').on('click',function(e){
            	var callback =function(){
            		closeUpdate();
            	}
            	var isRepairs = $("#isRepairs").val();
            	if(isRepairs==1){
            		addOrder("update-form",D2V.orderURL.updateOrder,callback)
            	}else if(isRepairs==2){
            		addOrder("update-form",D2V.orderURL.updateServiceOrder,callback)
            	}
            });
            function addOrder(addform,addurl,callback){
            	if (!JudgeValidate('#'+addform)) {
					return false;
				} 
            	var formParam = getFormDataAll(addform);
            	if(addform=="add-repair-form"  || formParam.isRepairs=="2"){
            		addOrdersFn();
            		return;
            	}            
                $.ajax({
                    type: "POST",
                    url: D2V.orderURL.checkVinRepeat,
                    contentType : "application/json ; charset=utf-8", 
                    data:JSON.stringify(formParam),
                    error:function(xhr,status,err){
                        console.log(status,err)
                        $.ligerDialog.error('网络故障!');
                        return;
                    },
                    success:function(data){
                    	if(data.success){
                    		if(formParam.orderStatus==4){
                    			statuOrder();
                    		}else{
                    			addOrdersFn();
                    		}
                    		
                    	}else{
                    		$.ligerDialog.warn(data.message);
                    	}
                    	console.log(data)
                    }
                })
                function statuOrder(){
                	$.ajax({
                        type: "POST",
                        contentType : "application/json ; charset=utf-8", 
                        data:JSON.stringify(formParam),
                        url: D2V.orderURL.checkVinBandVehicle,
                        error:function(xhr,status,err){
                            console.log(status,err)
                            $.ligerDialog.error('提交失败,网络故障!');
                            return;
                        },
                        success:function(data){
                            console.log(data);
                            if(!data.success){
                            	if("查询异常!"==data.message){
                            		$.ligerDialog.warn('操作失败,请联系客服人员!');
                            	}else{
                            		$.ligerDialog.open({
    					            	width: 280,
    					            	type: 'question',
    					            	title:'提示',
    					            	content: data.message,
    					            	buttons: [{ text: '继续', onclick: function(item, dialog){
    					            						addOrdersFn();
    			    					            		dialog.close();} 
    				    					            	}, 
    					            	     { text: '暂停', onclick: function(item, dialog){dialog.close();}}]
    					            
    					            })
                            	}
                            }else{
                            	addOrdersFn();
                            }
                        },
                         dataType:'json'
                    });
                }
                function addOrdersFn(){
                	 $.ajax({
                         type: "POST",
                         contentType : "application/json ; charset=utf-8", 
                         data:JSON.stringify(formParam),
                         url: addurl,
                         error:function(xhr,status,err){
                             console.log(status,err)
                             $.ligerDialog.warn('提交失败,网络故障!');
                             return;
                         },
                         success:function(data){
                             if(data.success===true){
                             	mainGrid.reload();
                             	$.ligerDialog.success(data.message);
                             	callback()
                             }else{
                                 $.ligerDialog.warn(data.message);
                             }
                         },
                          dataType:'json'
                     });
                }
               
            }
            
		 $(".groupLine3 button").on("click",function(){
			$(this).addClass("active").siblings("button").removeClass("active")
			var index =$(this).index()-1
			$("#orderStatus").val(index)
			saveSearch();
		 })
})