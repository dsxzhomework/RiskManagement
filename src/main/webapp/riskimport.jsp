<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link href="./css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <link href="./css/component.css" rel="stylesheet" type="text/css" media="all"/>
	<title>recommend and import risk</title>
	<style>
	#subtop {
		width:100%;
		height:20%;
		margin-top:40px;
		margin-left:100px;
		float:left;
	}
	#import_risklist {
		width:800px;
		height:70%;
		margin-left:100px;
		margin-top:-20px;
		float:left;
	}
	.hidden {
		display:none;
	}
	.display{
	    widows: inherit;
	    height: inherit;
	}
	</style>
</head>
<body>
<div class="header">
  	  	<div class="headertop_desc">
			<div class="wrap">
				<div class="nav_list">
					<ul>
					<%String rpname = (String)request.getServletContext().getAttribute("rpname"); 
					int rpid = (int)request.getServletContext().getAttribute("rpid"); 
					%> 
						<li><a href="/RiskManagement/login">主页</a></li>
						<li><a href="/RiskManagement/showrplist">RA列表</a></li>	
						<li><a href="/RiskManagement/showrp?rpid=<%=rpid%>"><%=rpname %></a></li>	
					</ul>
				</div>
					<div class="account_desc">
						<ul>
						<%
						    String uname = (String)request.getServletContext().getAttribute("uname");
						%>
							<li><a href="home.jsp" target="_blank"><%=uname %></a></li>
							<li><a href="/RiskManagement/loginout" target="_blank">登出</a></li>
						</ul>
					</div>
				<div class="clear"></div>
			</div>
	  	</div>
	  	
	</div>

<div id="subtop" style="margin:5px;">
	<form method="post" action="importrisk">
		<script type="text/javascript" ></script>
        <div style="float:left;">
             <span style="float:left;">开始日期：</span>        
			 <input type="text" class="text" name="datebegin" placeholder="YYYY-MM-dd" onfocus="this.value = '';"  >
		</div>
        <div style="float:left;margin-left:40px;">
             <span style="float:left;">结束日期：</span>        
			 <input type="text" class="text" name="dateend" placeholder="YYYY-MM-dd" onfocus="this.value = '';"  >
		</div>
		<div style="float:left;margin-left:40px;">
			类型：<select name="importtype">
				<option value="quesmost">问题最多</option>
				<option value="idenmost">识别最多</option>
			</select>
		</div>
		<div class="submit" style="float:left;margin-left:40px;">
			<input type="submit" value="确认" onClick="choose()">
		</div>
	</form>
	<div style="float:left;margin-left:20px;">
		<input type="submit" value="导入" onClick="importRid()">
	</div>
</div>
<div id="import_risklist"  style="margin:10px;">
	<div class="component">

	<script type="text/javascript">
	
	function choose(){
		document.getElementById('import_risklist').className="display";
		
	}
	function importRid(){
		var inputs = document.getElementById("rrisk").getElementsByTagName("input");
		
		var riskids = [""];
		var countline = 0;
		for(var i=0;i < inputs.length;i++){
			if(inputs[i].type=="checkbox") {
				if(inputs[i].checked){
	         		//loop column

	         		riskids[countline] = i;
	         		countline++;
				}
			}

		}
		 window.location.href = "/RiskManagement/import?index="+riskids;
	}
	</script>
	<table id="rrisk">
		<thead>
				<tr>
					<th>导入</th>
                    <th>类型</th>
					<th>危险程度</th>
					<th>状态</th>
					<th>查看</th>
				</tr>
			</thead>
				<% 
					String[] rids = (String[])request.getServletContext().getAttribute("rids");
					String[] names=(String[])request.getServletContext().getAttribute("rnames");
					String[] affects=(String[])request.getServletContext().getAttribute("affects");
					String[] states=(String[])request.getServletContext().getAttribute("states");
				%>
				<tbody>		
				<tr>
					<td><input type="checkbox"></td> 
                    <td>ggg</a></td>
                    <td>dd</td>
                    <td>ddd</td>
                    <td>>查看</a></td>
                </tr>		
				
			<% 
				if (rids.length > 0) {
					for (int i=0;i < rids.length;i++) {
						String rid=rids[i];
						String name=names[i];
							//String rid=Integer.toString(risklist.get(i).getRid());
			%>		
				<tr>
					<td><input type="checkbox"></td> 
                    <td><a href="/RiskManagement/showRisk?rid=<%=rids[i]%>"><%=names[i] %></a></td>
                    <td><%=affects[i] %></td>
                    <td><%=states[i] %></td>
                    <td><a href="/RiskManagement/showRisk?rid=<%=rids[i]%>">查看</a></td>
                </tr>
						
			<%
					}
				} 
			%>
			</tbody>
		</table>
	</div>
</div>

</body>
</html>
