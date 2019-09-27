<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
        <meta charset="UTF-8">
        <title>时间是最好的朋友</title>
        <link rel="shortcut icon" type="image/x-icon" href="favicon.ico"/>
		<link rel="bookmark" type="image/x-icon" href="favicon.ico"/>
        <link rel="stylesheet" href="vendor/bootstrap/dist/css/bootstrap.css"/>
        <link rel="stylesheet" href="vendor/ligerUI/skins/Aqua/css/ligerui-all.css"/>
        <script src="js/libs/jquery-1.11.2.min.js"></script>
        <script src="js/libs/jquery.cookie.js"></script>
        <script src="vendor/ligerUI/js/ligerui.all.js"></script>
        <script src="js/plugins/ligerui.other.js"></script>
        <script src="js/util/md5.js"></script>
       	<script>
       		 if(top !== window) top.location = location.href; 
       	</script>
        <style>
            body{
                /*background: url(images/denglu/loginBg.png) 0 90px no-repeat;*/
                height: 100%;
                font-family: '微软雅黑';
                /*background-size: cover;*/
            }
            html{
                height: 100%;
            }
            .center{
                background: url(images/denglu/loginBg.png) 0 90px no-repeat;
                background-size: cover;
                height: 100%;
                padding-top: 90px;
            }
            .top{
                width: 100%;
                background-color: #23262E;
                height: 90px;
                position: absolute;
                top:0;
                left: 0;
            }
            .topContent{
                width: 1146px;
                margin: 0 auto;
                height: 90px;
            }
            .logo{
                display: inline-block;
                line-height: 90px;
                margin-top: -12px;
                margin-left: 54px;
            }
            .loginName{
                font-size: 30px;
                color:#fff;
                line-height: 90px;
                display: inline-block;
                margin-right: 221px;
                float:right;
            }
            .main{
                background: url(images/denglu/mainBg.png) no-repeat;
                height: 607px;
                margin: 0 auto;
                width: 1146px;
                margin-top: 50px;
                position:relative;
            }
            .dl{
                padding-top: 250px;
                padding-left: 698px;
                width: 972px;
                height: 526px;
                font-size: 12px;
                position:relative;
            }
            .welcome{
                font-size: 18px;
                color: #0385dd;
                text-align: center;
                line-height: 18px;
            }
            .yhm{
                margin-top: 18px;
                height: 32px;
                line-height: 32px;
                color: #4c4c4c;
                position: relative;
                margin-bottom: 22px;
            }
            .yhm input,.mm input{
                width: 222px;
                height: 32px;
                border: 1px solid #e1e3e4;
                border-radius: 5px;
                padding-left: 6px;
            }
            .mm{
                position: relative;
                height: 32px;
                line-height: 32px;
                color: #4c4c4c;
                margin-bottom: 22px;
            }
            .rem{
                font-weight: normal;
            }
            .rem{
                margin-left: 9px;
            }
            input,button{
                outline: none;
            }
            input[type="checkbox"]{
                margin: 0px;
                display: inline-block;
                vertical-align: middle;
            }
            .forget{
                margin-left: 16px;
            }
            .loginBtn{
                width: 271px;
                height: 40px;
                background-color: #0385dd;
                color: #fff;
                font-size: 18px;
                line-height: 36px;
                text-align: center;
                border: 1px solid #0385dd;
                border-radius: 5px;
                margin-top: 18px;
            }
            input[type="text"]:focus,input[type="password"]:focus{
                border: 1px solid #a4bae8;
                box-shadow: 0 0 5px #a4bae8;
            }
            .ab{
                margin: 0 auto;
                width: 1146px;
                margin-top: 10px;
                text-align: center;
                font-size: 14px;
                color:#0b0b0b;
            }
            .a{
                margin-right: 30px;
            }
            .qsr{
                position: absolute;
                left: 50px;
                top:26px;
                color:red;
            }
            .qsryhm{
                display: none;
            }
            .qsrmm{
                display: none;
            }
            .code{
            	position:absolute;
            	left:1044px;
            	top:161px;
            	display: none;
            	width:228px;
            	height:443px;
            	border-radius: 20px;
            	box-shadow: 0px 1px 7px #acafb4;
            	background:url(images/denglu/QR.png) center no-repeat;
            	}
            	
            .code-text{
            	font-size:13px;
            	color:#0385dd;
            	position:absolute;
            	bottom:-60px;
            	right:-53px;
            	cursor: pointer;
            	animation:text 0.5s ;
            	animation-iteration-count:10;
            	-moz-animation:text 0.5s ;
            	-moz-animation-iteration-count:10;
            	-webkit-animation:text 0.5s ;
            	-webkit-animation-iteration-count:10;
            	-o-animation:text 0.5s ;
            	-o-animation-iteration-count:10;
            	-ms-animation:text 0.5s ;
            	-ms-animation-iteration-count:10;
            }
            @keyframes text{
            	from{color:#0385dd;}
            	to{color:green;font-size:14px}
            }
            @-moz-keyframes text{
            	from{color:#0385dd;}
            	to{color:green;font-size:14px}
            }
            @-o-keyframes text{
            	from{color:#0385dd;}
            	to{color:green;font-size:14px}
            }
            @-ms-keyframes text{
            	from{color:#0385dd;}
            	to{color:green;font-size:14px}
            }
            @-webkit-keyframes text{
            	from{color:#0385dd;}
            	to{color:green;font-size:14px}
            }
            .isClearCha{
            	width: 100%;
			    margin: 0 auto;
			    margin-top:24px;
			    text-align: center;
			    font-size: 14px;
			    display:none;
			    line-height:20px;
            }
            .isClearCha p{
            	margin-bottom: 20px;
            	color:red;
            }
            .ra_btn{display:inline-block;cursor: pointer;}
            .isClearCha input{
            	vertical-align: sub;
            }
            .isClearCha span{
            	margin-left: 3px;
    			margin-right: 10px;
            }
        </style>
        <script type="text/javascript">         
             $(document).ready(function () {
            	var cookie_login_name=$.cookie("cookie_login_name");
            	if(cookie_login_name!=''&&cookie_login_name!=null){
            		$("#ckbremembername").attr("checked", true);
            		$("#name").val(cookie_login_name);
            		$('#psw').focus();
            	}else{
            		$('#name').focus();
            	}            	
             });

             document.onkeydown=function(e){
            	 var keyCode;
            	 if(!e){
            		 keyCode = event.keyCode;
            	 }else{
            		 keyCode = e.which;
            	 }
            	 if(keyCode == 13){
            		 $("#loginSubmit").trigger("click")
            	 }
             };
             $(function(){
                  $('.yonghuming').focus(function(){
                	  $('.qsrmm').text("");
                	  $('.qsrmm').hide();
                   })
                  $('.mima').focus(function(){
                	  $('.qsrmm').text("");
                	  $('.qsrmm').hide();
                  })
                  $(".ra_btn span").click(function(){
                	  $(this).siblings().trigger("click");
                  });
                  $("#loginSubmit").click(function(){
                	  login();
                  });
             })
            
          function login(){
               var login_name=$.trim($('#name').val());
               var login_password=$.trim($('#psw').val());
              
               if(login_name==""||login_name==null){
            	   $('#name').focus();
            	   return ;
               }
               if(login_password==""||login_password==null){
            	   $('#psw').focus();
            	   return ;
               }
               $("#loginSubmit").text("正在登录中...").css("cursor","wait");
               $("input").attr("disabled",true);
			    $("body").css("cursor","wait");
               var encrypt=$.md5(login_password);
               $.ajax({
            	   url:"validatepwd",
            	   method:"POST", 
            	   contentType: "application/json; charset=utf-8",
            	   dataType:"json",
            	   data:"{'loginName':'"+login_name+"','loginPassword':'"+encrypt+"'}", 
            	   success: function (responseData) {
            		   if(responseData.errorCode==0){
            			   if ($("#ckbremembername").is(":checked")){
            				   $.cookie('cookie_login_name',login_name , { expires: 365, path: '/' });
            			   }else{
            				   $.removeCookie('cookie_login_name',{ path: '/'});
            			   }
            			   //location.href= "htmls/index.html";
            			   location.href= "webadmin/index.html";
            		   }else if(responseData.errorCode==5){//用户没有权限
            			   alert('当前用户没有权限,请联系管理员!');
        				   location.href= "login.jsp";
            		   }else if(responseData.errorCode==4){
            			   $(".qsrmm").text(responseData.message);
            			   $(".qsrmm").show();
						   //$("#psw").focus();
						   $("#loginSubmit").text("登录系统").css("cursor","pointer");
						   $("input").attr("disabled",false);
						   $("body").css("cursor","auto");						   
            		   }else{
            			   $(".qsrmm").text(responseData.message);
            			   $(".qsrmm").show();
            				//$("#name").focus();
            			   //console.log(responseData.message);/////
						   $("#loginSubmit").text("登录系统").css("cursor","pointer");
						   $("input").attr("disabled",false);
						   $("body").css("cursor","auto");
            		   }
                    },
                    error: function(XMLHttpRequest, textStatus, errorThrown) {
                    	console.log(XMLHttpRequest.status);
                    	console.log(textStatus, errorThrown);
                    	$('.qsrmm').text("登陆失败,网络故障!");
                    	$('.qsrmm').show();
						$("body").css("cursor","auto");
						$("#loginSubmit").text("登录系统").css("cursor","pointer");
						$("input").attr("disabled",false);						
                    }
               });
            }
        </script>
    </head>
    <body>
        <div class="top">
            <div class="topContent">
                <!-- <img src='images/denglu/logo.png' class="logo" /> -->
                <div class='loginName'>时间是最好的朋友</div>
            </div>
        </div>
        <div class="center">
          <form id="loginForm" >
            <div class="main">
            	<div class="code">
            	</div>
                <div class="dl">
                    <div class="welcome">欢迎登录</div>
                    <div class="yhm">
                        	用户名：<input type="text" name="login_name" id="name" class="yonghuming" />
                    </div>
                    <div class="mm">
                       		 密　码：<input type="password" name="login_password" id="psw" class="mima" />
                      		<span class="qsr qsrmm">请输入密码</span>  
                    </div>
                    
                    <div class="remText">
                        <label for='rem'>
                            <input type="checkbox" id="ckbremembername" name="remembername"/>
                            <span class="rem" onclick="document.getElementById('ckbremembername').click();">记住用户名</span>
                        </label>
                        <span class="forget">忘记密码请与系统管理员联系!</span>
                    </div>
                    <button type='button' class="loginBtn" id="loginSubmit">登录系统</button>                    
                </div>
            </div>
            </form>
        </div>
        <script type="text/javascript" >
        	$(".code-text").on("mouseover",function(){
        		$(".code").css("display","block")
        	});
        	$(".code-text").on("mouseleave",function(){
        		$(".code").css("display","none")
        	});
        </script>
    </body>
</html>