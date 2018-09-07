/*!
 * 韦念富
 * Copyright 2017.08.02
 * 表单验证
 */
//验证不为空 notnull
function isNotNull(obj) {
    obj = $.trim(obj);
    if (obj.length == 0 || obj == null || obj == undefined) {
        return true;
    }
    else
        return false;
}
//验证IP地址
function isIP(obj) {
    reg =/^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/;
    if (!reg.test(obj)) {
        return false;
    } else {
        return true;
    }
}
//验证数字 num
function isInteger(obj) {
    reg = /^[-+]?\d+$/;
    if (!reg.test(obj)) {
        return false;
    } else {
        return true;
    }
}
//验证是否电话号码 phone
function isTelephone(obj) {
    reg = /^(\d{3,4}\-)?[1-9]\d{6,7}$/;
    if (!reg.test(obj)) {
        return false;
    } else {
        return true;
    }
}
//验证是否为手机号码 phone
function isPhone(obj) {
    reg =  /^1[3|4|5|7|8][0-9]{9}$/; //验证规则
    if (!reg.test(obj)) {
        return false;
    } else {
        return true;
    }
}
//验证车架号为17位
function vin(obj) {
    reg =  /^[a-zA-Z0-9]{17}$/; //验证规则
    if (!reg.test(obj)) {
        return false;
    } else {
        return true;
    }
}
//脚本中 checkvalue 验证函数  err 属性表示提示【中文名称】
function JudgeValidate(obj) {
    var Validatemsg = "";
    var Validateflag = true;
    $(obj).find("[datacol=yes]").each(function () {
    	//长度验证
        if ($(this).attr("checkexpession") != undefined) {
            switch ($(this).attr("checkexpession")) {
                case "default":
                    {
                        if (isNotNull($(this).val())) {
                            Validatemsg = $(this).attr("err") + "\n";
                            Validateflag = false;
                            ChangeCss($(this), Validatemsg); return false;
                        }
                        break;
                    }
                case "NotNull":
                    {
                        if (isNotNull($(this).val())) {
                            Validatemsg = $(this).attr("err") + "不能为空！\n";
                            Validateflag = false;
                            ChangeCss($(this), Validatemsg); return false;
                        }
                        break;
                    }
                case "Num":
                    {
                        if (!isInteger($(this).val())) {
                            Validatemsg = $(this).attr("err") + "必须为数字！\n";
                            Validateflag = false;
                            ChangeCss($(this), Validatemsg); return false;
                        }
                        break;
                    }                
                case "Phone":
                    {
                        if (!isTelephone($(this).val())) {
                            Validatemsg = $(this).attr("err") + "必须电话格式！\n";
                            Validateflag = false;
                            ChangeCss($(this), Validatemsg); return false;
                        }
                        break;
                    }
                case "isIp":
                {
                    if (!isIP($(this).val())) {
                        Validatemsg = $(this).attr("err") + "不正确！\n";
                        Validateflag = false;
                        ChangeCss($(this), Validatemsg); return false;
                    }
                    break;
                }
                case "isPhone":
                {
                    if (!isPhone($(this).val())) {
                        Validatemsg = $(this).attr("err");
                        Validateflag = false;
                        ChangeCss($(this), Validatemsg); return false;
                    }
                    break;
                }
                case "vin":
                {
                    if (!vin($(this).val())) {
                        Validatemsg = $(this).attr("err")+"必须为17位";
                        Validateflag = false;
                        ChangeCss($(this), Validatemsg); return false;
                    }
                    break;
                }
                default:
                    break;
            }
        }
        if($(this).attr("lengthValid") != undefined){
	        	if($(this).val().length > $(this).attr("lengthValid")){
		    		 Validatemsg = $(this).attr("err") + "超过限定长度"+$(this).attr("lengthValid")+"！\n";
		             Validateflag = false;
		             ChangeCss($(this), Validatemsg,$(this).attr("lengthValid")); 
		             return false;
	        	}
    	}
        
    });
    if (Validatemsg.length > 0) {
        return Validateflag;
    }
    return Validateflag;
}
//修改出错的input的外观
function ChangeCss(obj, Validatemsg, length) {
    $(obj).focus(); //焦点
    if(length!=undefined){
    	var validId = $(obj).attr('name')+"_length";
    }else{
    	var validId = $(obj).attr('name')+"_value";
    }
    if(!$("#"+validId).html()){
    	$.ligerDialog.warn(Validatemsg)
    }

    $(obj).change(function () {
    	  if ($(obj).val() != ""&&validId.indexOf("_value")>-1) {
          	$("#"+validId).remove();
          }
          if($(obj).val().length<=length&&validId.indexOf("_length")>-1){
          	$("#"+validId).remove();
          }
    });
    $(obj).blur(function () {
        if ($(obj).val() != ""&&validId.indexOf("_value")>-1) {
        	$("#"+validId).remove();
        }
        if($(obj).val().length<=length&&validId.indexOf("_length")>-1){
        	$("#"+validId).remove();
        }
    });
}


