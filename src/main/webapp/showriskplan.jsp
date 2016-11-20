<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="./css/style.css" rel="stylesheet" type="text/css" media="all"/>
<link href="./css/component.css" rel="stylesheet" type="text/css" media="all"/>
<script language="javascript" type="text/javascript" src="./js/jquery-1.9.0.min.js"></script>
<style type="text/css">
    tr.hide,tr.hide td{display:none;}
</style>
<title>RiskManagement</title>
<style type="text/css">
	h1{
		font-size: 25px;
		padding: 20px 0px 10px 40px;
		display: block;
		border-bottom:1px solid #E4E4E4;
		margin: -10px -15px 30px -10px;
		color: dark;
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
						<% String rpname = (String)request.getServletContext().getAttribute("rpname");
						int rpid = (int)request.getServletContext().getAttribute("rpid"); 
						%>
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
<!-- header end -->

<div class="wrap">
<!-- title -->
<h1><%= (String)request.getServletContext().getAttribute("rpname")%></h1>
<!-- title -->
<!-- risklist -->
<div id="riskList">
	<div class="mybutton" >
		<a href="riskimport.jsp" style="color: #000">导入</a>
	</div>
	<div style="float:right;">      
    	<br>        
        <label>类型</label>
           <select class ="selectStyle" id="type">
            	<option value="">--</option>
            	<option value="人员变动">人员变动</option>
            	<option value="缺乏共识">缺乏共识</option>
                <option value="资金不足">资金不足</option>
                <option value="设备故障">设备故障</option>
                <option value="设计欠缺">设计欠缺</option>
                <option value="计划过于乐观">计划过于乐观</option>
                <option value="其他">其他</option>
            </select>
        <label>危险程度</label>
             <select class ="selectStyle" id="affect">
                <option value="">--</option>
                <option value="0">低</option>
                <option value="1">中</option>
                <option value="2">高</option>
            </select>  
        <label>状态</label>
           <select class ="selectStyle" id="state">
                <option value="">--</option>
                <option value="未发生">未发生</option>
                <option value="已发生">已发生</option>
                <option value="已解决">已解决</option>
            </select>  
        <input type="submit" id="search" value="搜索" onclick="search()"  />
    </div>
    <script type="text/javascript">
                function filter(fn) {
                    var list = document.getElementsByTagName('table')[0].getElementsByTagName('tbody')[0].rows;
                    var size = list.length;
                    var tr;
                    for(var i = 0; i < size; i++) {
                        tr = list[i];
                        tr.removeAttribute('class', 'hide')
                        if(!fn(tr)) {
                            tr.setAttribute('class', 'hide');
                        }
                    }
                }
                function value(id) {
                    return document.getElementById(id).value;
                }
                function search() {
                    var type = document.getElementById('type').value;
                    var affect = document.getElementById('affect').value;
                    var state = document.getElementById('state').value;
                    filter(function(tr) {		
                        if(type && tr.cells[0].innerHTML.indexOf(type) < 0) {
                            return false;
                        }
                        if(affect && tr.cells[1].innerHTML.indexOf(affect) < 0) {
                            return false;
                        }
                        if(state && tr.cells[2].innerHTML.indexOf(state) < 0) {
                            return false;
                        }
                        return true;
                    });
                } 
        </script>
	<br>
	<div class="component">
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
                    <td><a href="/RiskManagement/showRisk?rid=<%=rids[i]%>"><%=names[i] %></a></td>
                    <td><%=affects[i] %></td>
                    <td><%=states[i] %></td>
                    <td><a href="/RiskManagement/showRisk?rid=<%=rids[i]%>">查看</a></td>
                   	<td><a href="/RiskManagement/deleterr?rid=<%=rids[i]%>">删除</a></td>            
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