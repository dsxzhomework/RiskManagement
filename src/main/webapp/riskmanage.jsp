<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="./css/style.css" rel="stylesheet" type="text/css" media="all"/>
<link href="./css/component.css" rel="stylesheet" type="text/css" media="all"/>
<script language="javascript" type="text/javascript" src="./js/jquery-1.9.0.min.js"></script>
<title>RiskManagement</title>
<style type="text/css">
	#button {
		width:100px;
		font-size:1em;
		padding:10px 25px;
		font-family: 'ambleregular';
		background:#FC7D01;
		text-transform:uppercase;
		color: #FFF;
		border:none;
		text-decoration: none;
		outline: 0;
	}
	h1{
		font-size: 25px;
		padding: 20px 0px 10px 40px;
		display: block;
		border-bottom:1px solid #E4E4E4;
		margin: -10px -15px 30px -10px;
		color: dark;
	}
	.hidden{
    	display: none;
	}
	.display{
	    widows: inherit;
	    height: inherit;
	}
</style>
</head>
<body>

<!-- header start -->
<div class="header">
  	  	<div class="headertop_desc">
			<div class="wrap">
				<div class="nav_list">
					<ul>
						<li><a href="home.jsp">主页</a></li>
						
						<% String pname = (String)request.getServletContext().getAttribute("pname"); 
							String rname = (String)request.getServletContext().getAttribute("rname");
						%>
						<li><a href="showproject"><%=pname %></a></li>	
						<li><a href="showproject">项目成员</a></li>	
						<li><a href="showprojectrisk">风险列表</a></li>	
						
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
<!-- header end -->

<div class="wrap">
<!-- title -->
<h1><%= (String)request.getServletContext().getAttribute("pname")%></h1>
<!-- title -->
<!-- risklist -->
<div id="riskList">
	<div id="button">
		<a href="addrisk.jsp" style="color: #000">添加风险</a>
	</div>
	
	<div id="search" style="margin-left:400px;margin-top:-30px;float:left;">
	<form action="searchrisk">
		类型：<select name="searchtype">
		<%          
		//0：人员变动；1：缺乏共识；2：资金不足；3：设备故障；4：设计欠缺；5：计划过于乐观；6：其他
 		%>
 			<option value="-1">--</option>
			<option value="0">人员变动</option>
			<option value="1">缺乏共识</option>
			<option value="2">资金不足</option>
			<option value="3">设备故障</option>
			<option value="4">设计欠缺</option>
			<option value="5">计划过于乐观</option>
			<option value="6">其他</option>
		</select>
		状态：<select name="searchstate">
			<option value="-1">--</option>
			<option value="0">未发生</option>
			<option value="1">已发生</option>
			<option value="2">已解决</option>

		</select>
		<input type="submit" value="确定"> 
	</form>
	</div>
	<br>
	<div class="component" style="margin-top:-40px;">
	<table>
		<thead>
				<tr>
                    <th>类型</th>
					<th>危险程度</th>
					<th>状态</th>
					<th>查看</th>
					<th>删除</th>
				</tr>
			</thead>
			<% 
				String[] rids = (String[])request.getServletContext().getAttribute("rids");
				String[] names=(String[])request.getServletContext().getAttribute("rnames");
				String[] affects=(String[])request.getServletContext().getAttribute("affects");
				String[] states=(String[])request.getServletContext().getAttribute("states");
				%>
				<tbody>
				<% 
				if (rids.length > 0) {
					for (int i=0;i < rids.length;i++) {
						String rid=rids[i];
						String name=names[i];
							//String rid=Integer.toString(risklist.get(i).getRid());
			%>		
				<tr>
                    <td><a href="showRisk?rid=<%=rids[i]%>"><%=names[i] %></a></td>
                    <td><%=affects[i] %></td>
                    <td><%=states[i] %></td>
                    <td><a href="showRisk?rid=<%=rids[i]%>">查看</a></td>
                    <%if(states[i].equals("未发生")){ %>
                   	<td><a href="deleteRisk?rid=<%=rids[i]%>">删除</a></td>
                    <%}else{ %>
 					<td style="color: grey;">删除</td> 
                    <%} %>
                </tr>
						
			<%
					}
				} 
			%>
			</tbody>
		</table>
	</div>
</div>
<!-- risklist -->
 </div>
</body>
</html>