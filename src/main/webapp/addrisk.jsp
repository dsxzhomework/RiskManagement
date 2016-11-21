<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="./css/style.css" rel="stylesheet" type="text/css" media="all"/>
<title>RiskManagement</title>


</head>
<body>
	<div class="header">
  	  	<div class="headertop_desc">
			<div class="wrap">
				<div class="nav_list">
					<ul>
						<li><a href="home.jsp">主页</a></li>
						
						<% String pname = (String)request.getServletContext().getAttribute("pname"); %>
						<li><a href=""><%=pname %></a></li>	
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
	<div class="section group">
        <form action="addRisk" method="post" class="basic-grey">
        		<%
        		String[] mids = (String[])request.getServletContext().getAttribute("mids");
        		String[] mnames = (String[])request.getServletContext().getAttribute("mnames");
				if(request.getParameter("error") != null && request.getParameter("error").equals("1")){
				%>
				<p style="color: red;">所有选项必须填写</p>
				<%
				}
				%>
			<div>
            <span >风险类型</span>
            <select name="type">
            	<option value="">--</option>
            	<option value="0">人员变动</option>
            	<option value="1">缺乏共识</option>
                <option value="2">资金不足</option>
                <option value="3">设备故障</option>
                <option value="4">设计欠缺</option>
                <option value="5">计划过于乐观</option>
                <option value="6">其他</option>
            </select>
            </div>
 
            <div style="clear:both;">
            <span>内容</span>
            <textarea class="text" name="content" rows="5" cols="60" placeholder="风险内容描述" onfocus="this.value='';"></textarea>
            </div>
            
            <div style="clear:both;">
            <span>应对策略</span>
            <textarea class="text" name="advice" rows="5" cols="60" placeholder="应对策略描述" onfocus="this.value='';"></textarea>
            </div>
            
            <div style="clear:both;">
            <span>可能性</span>
            <select class ="selectStyle" name="possibility">
                <option value="">--</option>
                <option value="0">低</option>
                <option value="1">中</option>
                <option value="2">高</option>
            </select>      
           </div>
            <div style="clear:both;">
            <span>影响程度</span>
            <select class ="selectStyle" name="affect">
                <option value="">--</option>
                <option value="0">低</option>
                <option value="1">中</option>
                <option value="2">高</option>
            </select>  
           </div>
            <div style="clear:both;">
            <span>触发值/值域</span>
            <textarea class="text" name="trigger" rows="2" cols="30" placeholder="触发值/值域" onfocus="this.value='';"></textarea>
            </div>
            <br>
            <div>
            <span>跟踪者</span>
            <select name="tracker">
			<%
			if(mids!=null){
				for(int i=0;i<mids.length;i++){
	   		%>
	    		<option value="<%=mids[i] %>"><%=mnames[i] %></option>
	    	<%
	    		}
			}
	    	%>
	        </select>
            </div>
			<input type="submit" value="确定"  class="button" style="clear:both;margin-left:80px;margin-top:30px;">
          
        </form>
        </div>
    </body>
</html>