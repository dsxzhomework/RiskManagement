<%@ page language="java" import="java.util.*,riskManager.model.User" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>风险管理系统</title>
<link href="./css/admin.css" rel="stylesheet" type="text/css" media="all"/>
<link href="./css/bootstrap.min.css" rel="stylesheet" type="text/css" media="all"/>
</head>
<body>
<div class="main">
<div class="search">
<form action="admin" method="post">
<select name="condition" class="select" style="font-family: 'ambleregular'">
<option value="uid" style="font-family: 'ambleregular'">用户ID</option>
<option value="name" style="font-family: 'ambleregular'">姓名</option>
<option value="role" style="font-family: 'ambleregular'">角色</option>
<option value="alluser" style="font-family: 'ambleregular'">全部</option>
</select>
<input name="searchinput" type="text">
<input type="submit" value="查找">
<%if(request.getParameter("error")!=null && request.getParameter("error").equals("1")){
	%>
	<p style="color:red;">请按正确格式输入查找条件</p>
<% }%>
<%if(request.getParameter("error")!=null && request.getParameter("error").equals("2")){
	%>
	<p style="color:red;">该用户不存在</p>
<% }%>
</form>
</div>
<div class="tablearea">
<table class="table table-hover">
<thead>
<tr>
<th style="width:30%">ID</th>
<th style="width:30%">姓名</th>
<th style="width:30%">角色</th>
</tr>
</thead>
<tbody>
<%List<User> users=(List<User>)request.getServletContext().getAttribute("alluser");
for(int i=0;i<users.size();i++){
	String id=Integer.toString(users.get(i).getUid());
	String name=users.get(i).getName();
	String role=users.get(i).getRole();
%>
<tr>
<td><%=id %></td>
<td><%=name %></td>
<td><%=role %></td>
</tr>
<%} %>
</tbody>
</table>
</div>
</div>
</body>
</html>