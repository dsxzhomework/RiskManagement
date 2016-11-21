<%@ page language="java" import="java.util.*,riskManager.model.User" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>风险管理系统</title></head>
	<link href="./css/style.css" rel="stylesheet" type="text/css" media="all"/>
	<meta name="author" content="annuus"/>
		<style>
		select{width:155px;}
		input[type="submit"]{
	width: 80px;
	height:30px;
	top:5px;
	background: #FC7D01;
	font-family: 'ambleregular';
	color: #00000;
	outline: none;
	border: none;
	cursor: pointer;
	transition: 0.5s;
}
		#top {
			margin-top:40px;
			margin-left:320px;
		}
		#mainContainer {
			margin-top:20px;
			margin-left:300px;
			
			background:#eee;
			width: 40%;
 			position: relative;
		}
		#newpro {
			list-style:none;
			padding-top:25px;
			padding-left:50px;
			line-height:50px;
		}

        #forb {
        	margin-left:270px;
        }
        #forbb {
        	height:30px;
        	
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
					</ul>
				</div>
					<div class="account_desc">
						<ul>
						<%
						    String uname = (String)request.getServletContext().getAttribute("uname");
						%>
							<li><a href="home.jsp" target="_blank"><%=uname %></a></li>
							<li><a href="loginout" target="_blank">登出</a></li>
						</ul>
					</div>
				<div class="clear"></div>
			</div>
	  	</div>
	  	
	</div>
	
	<div id="top">
		<p  style="color:black;font-size:27px;font-weight:bold;">新建项目</p>
	</div>
	<div id="mainContainer">
	<form action="addproject">
	
	<ul id="newpro">
		<li>项目名称：<input type="text" name="projectname"></li>
		<%
		String[] developers = (String[])request.getServletContext().getAttribute("developers");
		String[] developer_names = (String[])request.getServletContext().getAttribute("developer_names");
		if(developers!=null){
			//System.out.println(Integer.toString(developer_names.length));
		%>
		<li>开发者一：<select name="developer1">
			<%
			for(int i=0;i<developers.length;i++){
	   		%>
	    	<option value="<%=developers[i] %>"><%=developer_names[i] %></option>
	    	<%
	    	}
	    	%>
	        </select></li>
		<li>开发者二：<select name="developer2">
	        <%
	        for(int i=0;i<developers.length;i++){
	        %>
	        	 <option value="<%=developers[i] %>"><%=developer_names[i] %></option>
	        <%
	        }
	        %>
	        </select></li>
		<li>开发者三：<select name="developer3">
	        <%
	        for(int i=0;i<developers.length;i++){
	        %>
	        	 <option value="<%=developers[i] %>"><%=developer_names[i] %></option>
	        <%
	        }
	        %>
	        </select></li>
	    <%
	    }
	    %>
		<li id="forb"><input type="submit" value="确定" ></li>
		<li id="forbb">&nbsp;&nbsp;&nbsp;&nbsp;</li>
	</ul>
	</form>
	</div>

</body>

</html>