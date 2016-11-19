<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>RiskManagement register</title>
    <link href="./css/style.css" rel="stylesheet" type="text/css" media="all"/>
	<link href="./css/register.css" rel='stylesheet' type='text/css' />
	<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
</head>
<body>
<div class="main">
<div class="wrap">
<div class="header_top">
<div class="header_top_right"><div class="clear"></div></div>
<div class="clear"></div>
</div>
</div>
<div class="register-form">
<h1>注册</h1>
<form action="register" method="post">
<% if (request.getParameter("error")!=null && request.getParameter("error").equals("1")){
%>
<p style="color:red">请将信息填写完整</p>
<%} %>
<%if(request.getParameter("error")!=null && request.getParameter("error").equals("2")){
%>
<p style="color:red">两次输入密码不一致</p>
<%} %>
<label>角色：<input name="role" type="radio" value="manager">项目经理   <input name="role" type="radio" value="developer">正常用户</label><br><br>
<label>姓名：<input name="name" type="text" onfocus="this.value = '';"></label>
<label>密码：<input name="pw" type="password" onfocus="this.value = '';"></label>
<label>确认：<input name="pwconfirm" type="password" onfocus="this.value = '';"></label>
<div class="submit"><input type="submit" value="注册"></div>
</form>
</div>
</div>
</body>
</html>