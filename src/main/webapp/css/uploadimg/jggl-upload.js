var imgSrc = []; //图片路径
var imgFile = []; //文件流
var imgName = []; //图片名字
var formData = new FormData();
//选择图片
function imgUpload(obj) {
	var oInput = '#' + obj.inputId;
	var imgBox = '#' + obj.imgBox;
	var btn = '#' + obj.buttonId;
	//var formData = new FormData();
	$(oInput).on("change", function() {
		var fileImg = $(oInput)[0];
		var fileList = fileImg.files;
		for(var i=0;i<fileList.length;i++){
			var f = fileList[i].name;
			if(!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test(f)){
				$.ligerDialog.warn('图片类型必须是.gif,jpeg,jpg,png中的一种！');
				return;
			}
		}
		//console.log(fileList)
		var agencyId = $("#orderModal").val();
		imgSrc=[];
		formData = new FormData();
		imgName=[];
		imgFile=[];
		formData.append('agencyId',agencyId);
		formData.append('flags',obj.flags);
		for(var i = 0; i < fileList.length; i++) {
			 formData.append(fileList[i].name, $('#file')[0].files[i]);
			var imgSrcI = getObjectURL(fileList[i]);
			imgName.push(fileList[i].name);
			imgSrc.push(imgSrcI);
			imgFile.push(fileList[i]);
		}
		console.log(imgFile);
		addNewContent(imgBox);
	})
	$(btn).one('click', function() {
		$(this).text("上传中...");
		$(this).removeAttr("id");
		console.log(formData);
		submitPicture(obj.upUrl, formData);
	})
}
//图片展示
function addNewContent(obj) {
	$(imgBox).html("");
	var imgLeng = $("#allPic").html();
	//console.log(imgSrc)
	for(var a = 0; a < imgSrc.length; a++) {
		var oldBox = $(obj).html();
		$(obj).html(oldBox + '<div class="imgContainer"><img title=' + imgName[a] + ' alt=' + imgName[a] + ' src=' + imgSrc[a] + '></div>');
	}
	
}
//删除
function removeImg(obj, index) {
	console.log(imgName[index]);
	formData.delete(imgName[index]);
	imgSrc.splice(index, 1);
	imgFile.splice(index, 1);
	imgName.splice(index, 1);	
	var boxId = "#" + $(obj).parent('.imgContainer').parent().attr("id");
	addNewContent(boxId);
}
//上传(将文件流数组传到后台)
function submitPicture(url,data) {
	if(url&&data){
		$.ajax({
			type: "post",
			url: url,
			async: true,
			dataType:"json",
	        cache: false,
	        data: data,
	        processData: false,
	        contentType: false,
			success: function(data) {
				//console.log(dat);
				if(data.success){
					 $.ligerDialog.success('上传成功!');
					 $("#imgBox").html("");
					 $("#orderModal").val();
					 imgSrc=[];
					$('#myModal').modal('hide');
					$(".uploadbtn").text("上传");
					$(".uploadbtn").attr("id","btn");
				}else{
					$.ligerDialog.error('上传失败!');
					$('#myModal').modal('hide');
					$(".uploadbtn").text("上传");
					$(".uploadbtn").attr("id","btn");
				}				
			}
		});
	}
}
//图片灯箱
function imgDisplay(obj) {
	var src = removeAllSpace($(obj).attr("src"));
	console.log(src)
	var imgHtml = '<div style="width: 100%;height: 100vh;overflow: auto;background: rgba(0,0,0,0.5);text-align: center;position: fixed;top: 0;left: 0;z-index: 100000;"><img style="margin-top: 100px;width: 70%;margin-bottom: 100px;" src=' + src + ' /><p style="font-size: 50px;position: fixed;top: 30px;right: 30px;color: white;cursor: pointer;" onclick="closePicture(this)">×</p></div>'
	$('body').append(imgHtml);
}
function removeAllSpace(str) {
    return str.replace(/\s/g, "%20");
}

//关闭
function closePicture(obj) {
	$(obj).parent("div").remove();
}

//图片预览路径
function getObjectURL(file) {
	var url = null;
	if(window.createObjectURL != undefined) { // basic
		url = window.createObjectURL(file);
	} else if(window.URL != undefined) { // mozilla(firefox)
		url = window.URL.createObjectURL(file);
	} else if(window.webkitURL != undefined) { // webkit or chrome
		url = window.webkitURL.createObjectURL(file);
	}
	return url;
}