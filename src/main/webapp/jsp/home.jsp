<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head lang="en">
        <meta charset="UTF-8">
        <title>首页</title>
        <link rel="stylesheet" href="../vendor/bootstrap/dist/css/bootstrap.css"/>
        <style>
            /*下级机构列表树状*/
            .tree {
                min-height:20px;
                /*background-color:#fbfbfb;
                border:1px solid #999;
                -webkit-border-radius:4px;
                -moz-border-radius:4px;
                border-radius:4px;*/
                -webkit-box-shadow:inset 0 1px 1px rgba(0, 0, 0, 0.05);
                -moz-box-shadow:inset 0 1px 1px rgba(0, 0, 0, 0.05);
                box-shadow:inset 0 1px 1px rgba(0, 0, 0, 0.05)
            }
            .tree li {
                list-style-type:none;
                margin:0;
                padding:10px 5px 0 30px;
                position:relative

            }
            .tree li::before, .tree li::after {
                content:'';
                left:6px;
                position:absolute;
                right:auto
            }
            .tree li::before {
                border-left:1px dotted #99c8f0;
                height:100%;
                top:0;
                width:1px
            }
            .tree li::after {
                border-top:1px dotted #99c8f0;
                height:20px;
                top:25px;
                width:25px
            }
            .tree li span {
                -moz-border-radius:5px;
                -webkit-border-radius:5px;
                border:1px solid #999;
                border-radius:5px;
                display:inline-block;
                padding:3px 8px;
                text-decoration:none;
                color:#fffefe;
                white-space: nowrap;
                cursor:pointer;
            }
            .tree li.parent_li>span {
                cursor:pointer
            }
            .tree>ul>li::before, .tree>ul>li::after {
                border:0
            }
            .tree li:last-child::before {
                height:26px
            }
            /*.tree li.parent_li>span:hover, .tree li.parent_li>span:hover+ul li span {
                background:#eee;
                border:1px solid #94a0b4;
                color:#000
            }*/
            .tree li.parent_li>span:hover{
                /*background:#eee;*/
                border:1px solid #94a0b4;
                color:#000
            }
            .active{
                background:#23b8ab;
                color:#000
            }
            .tree li.parent_li>span+ul li span+ul li span:hover{
                /*background:#eee;*/
                color:#000
            }
            .well{
                border: none;
                border-radius: 0;
                padding: 0;
                margin-bottom: 0px;
            }
            .jglbUl >li{
                padding-left: 6px;
            }
            .zhankai{
                background: url(../images/shouye/zhankai.png) no-repeat;
                width:16px;
                height: 14px;
                left: -4px;
            }
            .shousuo{
                background: url(../images/shouye/shousuo.png) no-repeat;
                width:16px;
                height: 14px;
                left: -4px;
            }
            .zhanshi{
                background: url(../images/shouye/zhanshi.png) no-repeat;
                width:15px;
                height: 12px;
                left: -6px;
            }

			.ztop{
				width:100%;
				height:30px;
			}
			#refresh {
				background: url(../images/shouye/index_contents.png) no-repeat 0 0px;
				height: 15px;
				display: inline-block;
				padding-left: 20px;
				outline: none;
				font-size: 18px;
				cursor: pointer;
				color: rgb(51, 122, 183);
				line-height:18px;
				float:right;
			}
        </style>
        <link rel="stylesheet" href="../css/public/page_home1.css"/>
        <script src="../js/libs/jquery-1.11.2.min.js"></script>
	    <script src="../vendor/ligerUI/js/ligerui.all.js"></script>
	    <script src="../js/plugins/ligerui.other.js"></script>
        <script src="../vendor/highcharts/highcharts.js"></script>
        <script src="../vendor/highcharts/highcharts-3d.js"></script>
        <script	type="text/javascript" src="../js/views/timerTask.js" defer='defer'></script>
    </head>
    <body>
    	<%
 			String companyType = request.getParameter("companyType");   //获取url中的参数值
 			//out.println("aaaaaaaa:companyType:" + companyType);
 		%>
    
        <div class="home">
            <div class="center">
				<div class="ztop">
					<span id="refresh">刷新</span>
				</div>
                <div class="top">
                    <ul class="list">
                        <li class="listLi active topL" data-carNum='' id="xgsGzdDaishenheLi" style="display:<%=companyType.equals("0")?"inline":"none"%>;">
                        	<a id="xgsGzdDaishenheFlag">
	                            <span class="listName">县公司工资单待审核</span>
	                            <span id="xgs_gzd_daishenhe" class="listNum">
	                            	<c:forEach var="num" items="${data.bm_gzd_daishenhe}">
	                            		<img src="../images/shouye/${num}.png">	
	                            	</c:forEach>                                
	                            </span>
                            </a>
                        </li>
                        <li class="listLi topL" data-carNum='' id="jyzGzDaishenheLi" style="display:<%=!companyType.equals("5")?"inline":"none"%>;">
                        	<a id="jyzGzDaishenheFlag">
	                            <span class="listName">加油站工资单待审核</span>
	                            <span id="jyz_gzd_daishenhe" class="listNum">
	                            	<c:forEach var="num" items="${data.jyz_gzd_daishenhe}">
	                            		<img src="../images/shouye/${num}.png">	
	                            	</c:forEach>
	                            </span>
                          	</a>
                        </li>
                        <li class="listLi topL" data-carNum='' id="xgsKhtzdWeixifaLi" style="display:<%=companyType.equals("0")?"inline":"none"%>;">
	                        <a id="xgsKhtzdWeixifaFlag">
	                            <span class="listName">县公司考核通知单未下发</span>
	                            <span id="xgs_khtzd_weixifa" class="listNum">
	                            	<c:forEach var="num" items="${data.xgs_khtzd_weixifa}">
	                            		<img src="../images/shouye/${num}.png">	
	                            	</c:forEach>                                
	                            </span>
	                        </a>
                        </li>
                         <li class="listLi topL" data-carNum='' id="jyzKhtzdWeixifaLi" style="display:<%=companyType.equals("0")?"inline":"none"%>;">
                        	<a id="jyzKhtzdWeixifaFlag">
	                            <span class="listName">加油站考核通知单未下发</span>
	                            <span id="jyz_khtzd_weixifa" class="listNum">
	                            	<c:forEach var="num" items="${data.jyz_khtzd_weixifa}">
	                            		<img src="../images/shouye/${num}.png">	
	                            	</c:forEach>                                
	                            </span>
                            </a>
                        </li>
                        <li class="listLi topL" data-carNum='' id="jyzKhtzdDaiquerenLi" style="display:<%= !companyType.equals("0")?"inline":"none"%>;">
	                        <a id="jyzKhtzdDaiquerenFlag">
	                            <span class="listName">加油站考核通知单待确认</span>
	                            <span id="jyz_khtzd_daiqueren" class="listNum">
	                            	<c:forEach var="num" items="${data.jyz_khtzd_daiqueren}">
	                            		<img src="../images/shouye/${num}.png">	
	                            	</c:forEach>                                
	                            </span>
	                        </a>
                        </li>
                         <li class="listLi topL" data-carNum='' id="xgsKhtzdDaiquerenLi" style="display:<%=companyType.equals("1")||companyType.equals("2")||companyType.equals("3")||companyType.equals("4")?"inline":"none"%>;">
                        	<a id="xgsKhtzdDaiquerenFlag">
	                            <span class="listName">县公司考核通知单待确认</span>
	                            <span id="xgs_khtzd_daiqueren" class="listNum">
	                            	<c:forEach var="num" items="${data.xgs_khtzd_daiqueren}">
	                            		<img src="../images/shouye/${num}.png">	
	                            	</c:forEach>                                
	                            </span>
                            </a>
                        </li>
                        <li class="listLi topL" data-carNum='' id="xgsGzdWeitongguoLi" style="display:<%=companyType.equals("1")||companyType.equals("2")||companyType.equals("3")||companyType.equals("4")?"inline":"none"%>;">
                        	<a id="xgsGzdWeitongguoFlag">
	                            <span class="listName">县公司工资单未通过</span>
	                            <span id="xgs_gzd_weitongguo" class="listNum">
	                            	<c:forEach var="num" items="${data.xgs_gzd_weitongguo}">
	                            		<img src="../images/shouye/${num}.png">	
	                            	</c:forEach>
	                            </span>
                          	</a>
                        </li>
                        <li class="listLi topL" data-carNum='' id="jyzGzdWeitongguoLi" style="display:<%= companyType.equals("5")?"inline":"none"%>;">
	                        <a id="jyzGzdWeitongguoFlag">
	                            <span class="listName">加油站工资单未通过</span>
	                            <span id="jyz_gzd_weitongguo" class="listNum">
	                            	<c:forEach var="num" items="${data.jyz_gzd_weitongguo}">
	                            		<img src="../images/shouye/${num}.png">	
	                            	</c:forEach>                                
	                            </span>
	                        </a>
                        </li>
                    </ul>
                </div>
        </div>
        <script>
            //body的overflow-y调整
            $(function(){
                var windowHeight=$(window).height();
                if(windowHeight<=790){
                    $('body').addClass('flow');
                }
                window.onresize=function(){
                    var windowHeightChange=$(window).height();
                    if(windowHeightChange<=790){
                        $('body').addClass('flow');
                    }else if(windowHeightChange>790){
                        $('body').removeClass('flow');
                    }
                }
            })
			//单击刷新按钮刷新数据
			$(function(){
				var TimeFn = null;
				$("#refresh").on("click",function(e){
					clearTimeout(TimeFn);
					TimeFn = setTimeout(function(){
						$.ajax({
							type:'POST',
                         	url:'../home/count',
							success : function(data) {
                         		var datas = data.datas;
                         		//修改页面上数据
                         		setIndexCountData(data);
                         	},
                         	error:function(xhr,status,err){
                         		console.log(xhr,status,err);
                                return;
                             },
                         	dataType:'json'
						});
					},300);
				});
			});
        </script>
    </body>
</html>