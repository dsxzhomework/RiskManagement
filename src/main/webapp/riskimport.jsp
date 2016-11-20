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
							<li><a href="/RiskManagement/loginout" target="_blank">登出</a></li>
						</ul>
					</div>
				<div class="clear"></div>
			</div>
	  	</div>
	  	
	</div>

<div id="subtop">
	<form method="post" action="">
		<script type="text/javascript" src="js/date.js"></script>
        <div style="float:left;">
             <span style="float:left;">开始日期：</span>        
             <input name="datebegin" type="text" value="" onClick="showcalendar(event,this);" onFocus="showcalendar(event, this);if(this.value=='0000-00-00')this.value=''">
			 
		</div>
        <div style="float:left;margin-left:40px;">
             <span style="float:left;">结束日期：</span>        
             <input name="dateend" type="text" value="" onClick="showcalendar(event,this);" onFocus="showcalendar(event, this);if(this.value=='0000-00-00')this.value=''">
			 
		</div>
		<div class="submit" style="float:left;margin-left:40px;">
			<input type="submit" value="确认" onClick="choose()">
		</div>
	</form>
	<div style="float:left;margin-left:20px;">
		<input type="submit" value="导入" onClick="importRid()">
	</div>
</div>
<div id="import_risklist" class="hidden">
	<div class="component">
	<script type="text/javascript">
	function choose(){
		document.getElementById('import_risklist').className="display";
	}
	function importRid(){
		var inputs = document.getElementById("rrisk").getElementsByTagName("input");
		for(var i=0;i < inputs.length;i++){
			if(inputs[i].type=="checkbox") {
				if(inputs[i].checked){
					var checkedRow=inputs[i];
					var tr = checkedRow.parentNode.parentNode;
	         		var tds = tr.cells;
	         		//loop column
	         		for(var j = 1;j < tds.length;j++){		 
						var str="";             
          				str += "title:"+tds[j].title+ "~ value:"+tds[j].value+"~innerHTML:"+tds[j].innerHTML ;
          				alert(str);                 		             
       				}
				}
			}
			
		}
	}
	</script>
	<table id="rrisk">
		<thead>
				<tr>
                    <th>类型</th>
					<th>危险程度</th>
					<th>状态</th>
					<th>查看</th>
					<th>删除</th>
					<th>是否导入</th>
				</tr>
			</thead>
				<tbody>
				<tr>
                    <td>1</td>
                    <td>2</td>
                    <td>3</td>
                    <td>4</td>
                   	<td>5</td>
 					<td><input type="checkbox"></td> 
                </tr>
                <tr>
                    <td>2-1</td>
                    <td>2-2</td>
                    <td>2-3</td>
                    <td>2-4</td>
                   	<td>2-5</td>
 					<td><input type="checkbox"></td> 
                </tr>
			</tbody>
		</table>
	</div>
</div>

</body>
</html>
