/*
 * 实现主页的长轮询操作
 * */

$(function () {
            
                (function longPolling() {
                
                    $.ajax({
                        url: "gps/data/onlinecount",
                        data: {"timed": new Date().getTime()},
                        dataType: "text",
                        timeout: 5000,
                        error: function (XMLHttpRequest, textStatus, errorThrown) {
                           
                            if (textStatus == "timeout") { // 请求超时
                                    longPolling(); // 递归调用
                                
                                // 其他错误，如网络错误等
                                } else { 
                                    longPolling();
                                }
                            },
                        success: function (data, textStatus) {
                        	data = JSON.parse(data);
                        	var datas = data.datas;
                        	var off = parseFloat(datas.off);
                        	var on =parseFloat(datas.on);
                        	var offP=0;
                        	var onP =0;
                        	
                        	offP = Math.round(off / (off+on) * 10000) / 100.00 ;
                        	onP = Math.round(on / (off+on) * 10000) / 100.00 ;
                        	
                        	$('#index-middle-breadcrumb-online').html(""+on+"("+onP+"%)");
                        	$('#index-middle-breadcrumb-offline').html(""+off+"("+offP+"%)");
                        	$('#index-middle-breadcrumb-24online').html(""+on+"("+onP+"%)");
                        	$('#index-middle-breadcrumb-24offline').html(""+off+"("+offP+"%)");
                        	
                        	
                            if (textStatus == "success") { // 请求成功
//                               // longPolling();
                            }
                        }
                    });
                })();
                
            });