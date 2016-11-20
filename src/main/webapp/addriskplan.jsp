<%@ page language="java" import="java.util.*,riskManager.model.Project" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link href="./css/style.css" rel="stylesheet" type="text/css" media="all"/>
	<title>RiskManagement</title></head>
	<meta name="author" content="annuus"/>
	<style>
		.left{
			float:left;
			margin:10%;
		}
	</style>
</head>

<body>
<div class="header">
    <div class="headertop_desc">
	    <div class="wrap">
			<div class="nav_list">
				<ul>
					<li><a href="home.jsp">主页</a></li>
					<li><a href="/RiskManagement/showrplist">RA列表</a></li>
				</ul>
			</div>
			<div class="account_desc">
				<ul>
					<%
					String uname = (String)request.getServletContext().getAttribute("uname");
					%>
					<li><a href="#" target="_blank"><%=uname %></a></li>
					<li><a href="/RiskManagement/loginout" target="_blank">登出</a></li>
				</ul>
			</div>
			<div class="clear"></div>
		</div>
	</div>
</div>

<div class="main">
	<form action="/RiskManagement/addrp">
		计划名称：<input type="text" class="text" name="rpname" placeholder="风险管理计划" onfocus="this.value = '';"  >
		<div class="submit">
			<input type="submit" value="添加" >
		</div>	
	</form>
</div>
</body>
</html>