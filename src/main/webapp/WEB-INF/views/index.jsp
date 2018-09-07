<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>欢迎页面</title>

<script type="text/javascript">
function logout(){
	location.href = "logout";
}
</script>
</head>
<body>
<h1>登陆成功</h1>
<div style="width:200px;height:100px;">
<a href="javascript:logout();">退出<!-- <img
					src="images/logout.png"
					 title="退出"
					alt="退出"> --></a>
					</div>
</body>
</html>