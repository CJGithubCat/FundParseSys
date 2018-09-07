/** 
 * 终端类型
 * 全部 有线 无线
 * **/
function unittype_id(obj,unittype){
		$.ajax({
	         type:"POST",
		     dataType:"json",
		     url:'../twgCount/queryUnittypeList',
		     data:{memo:unittype},
		     success:function(data){
		    	 var str = "";
		    	 var ary =[];
		    	 for(var i=0;i<data.datas.length;i++){
		    		 ary.push(data.datas[i].split(","));
		    	 }
		    	 for(var j=0;j<ary.length;j++){
		    		str+='<option value="'+ary[j][0]+'">'+ary[j][1]+'</option>';
		    	 }
		    	 obj.append(str);
			 },
			 error:function(xhr,status,err){
				alert("网络加载失败！")
			 }		
		 });
}