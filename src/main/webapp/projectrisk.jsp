<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<div><p><%= (String)request.getServletContext().getAttribute("pname")%></p></div>
<div>
<ul id = "risklist">
<a href="/RiskManagement/addrisk.jsp">添加风险</a>
					<% 
					String[] rids = (String[])request.getServletContext().getAttribute("rids");
					String[] names=(String[])request.getServletContext().getAttribute("rnames");
					if (rids.length > 0) {
						for (int i=0;i < rids.length;i++) {
							String rid=rids[i];
							String name=names[i];
							//String rid=Integer.toString(risklist.get(i).getRid());
					%>
						
							<li><a href="/RiskManagement/showRisk?rid=<%=rid %>"><%=name %></a></li>
						
					<%
						}
					} else {
					%>
						<li>test1</li>
						<li>test1</li>
						<li>test1</li>
						<li>test1</li>
						<li>test1</li>
						<li>test1</li>
						<li>test1</li>
					<%	
					}	  
					%>
					</ul>
				</div>
				<script>
				window.onload = function(){
				    var obj_lis = document.getElementById("risklist").getElementsByTagName("li");
				    for(i=0;i<obj_lis.length;i++){
				        obj_lis[i].onclick = function(){
				            request.setAttribute("rid",rid);
				        }
				    }
				}
				</script>
</div>
</body>
</html>