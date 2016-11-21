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
						
						<% String pname = (String)request.getServletContext().getAttribute("pname"); 
						String rname = (String)request.getServletContext().getAttribute("rname");
						%>
						<li><a href="showprojectrisk"><%=pname %></a></li>	
						<li><a href="showrisk"><%=rname %></a></li>	
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

        <div class="basic-grey">
        <% 	String[] mids = (String[])request.getServletContext().getAttribute("mids");
			String[] mnames = (String[])request.getServletContext().getAttribute("mnames");
        	if(rname!=null){
        		int uid = (int)request.getServletContext().getAttribute("uid");
        		int rid = (int)request.getServletContext().getAttribute("rid");
        		String content = (String)request.getServletContext().getAttribute("content");
        		int possibility = (int)request.getServletContext().getAttribute("possibility");
        		int affect = (int)request.getServletContext().getAttribute("affect");
        		String trigger = (String)request.getServletContext().getAttribute("trigger");
        		int state = (int)request.getServletContext().getAttribute("state");//
        		int provider = (int)request.getServletContext().getAttribute("provider");//uid
        		int tracker = (int)request.getServletContext().getAttribute("tracker");//uid
        		String tracker_name = (String)request.getServletContext().getAttribute("tracker_name");
        		String provider_name = (String)request.getServletContext().getAttribute("provider_name");
        		String buildtime = (String)request.getServletContext().getAttribute("buildtime");
        		String changetime = (String)request.getServletContext().getAttribute("changetime");
        %>
        <form action="updateRisk" method="post">
        	<div>
            <span>风险类型</span>
            <span style="color:black;"><%=rname %></span>
            </div>
            <div style="clear:both;">
            <span>内容</span>
            <textarea class="text" name="content" rows="5" cols="60"><%=content %></textarea>
            </div>
            <div style="clear:both;">
            <span>可能性</span>
              <select class ="selectStyle" name="possibility">  
              	<%if(possibility==0){%>
              		<option value="0">低</option>
         		<%}if(possibility==1){%>
         			<option value="1">中</option>
         		<%}if(possibility==2){%>
         			<option value="2">高</option>
         		<%} %>  	
                <option value="0">低</option>
                <option value="1">中</option>
                <option value="2">高</option>
            </select>   
            </div>
            <div style="clear:both;">
            <span>影响程度</span>
            <select class ="selectStyle" name="affect">
               <%if(affect==0){%>
              		<option value="0">低</option>
         		<%}if(affect==1){%>
         			<option value="1">中</option>
         		<%}if(affect==2){%>
         			<option value="2">高</option>   
         		<%} %>     
                <option value="0">低</option>
                <option value="1">中</option>
                <option value="2">高</option>
            </select>  
           		
            </div>
            <div style="clear:both;">
            <span>触发值/值域</span>
            <textarea class="text" name="trigger" rows="2" cols="30"><%=trigger %></textarea>
            </div>
            <div style="clear:both;">
            <span>提交者</span>            
            <span style="color:black;"><%=provider_name%></span>
            </div>
            <div style="clear:both;">
            <span>状态</span>
            <% 
            	if(state==0){%><span style="color:black;">未触发</span>
            	<%if(uid==tracker) {%>
            	 <a href="/RiskManagement/changeState">已发生 </a>	
            	 <%}}
            	else if(state==1){%><span style="color:red;">已发生</span>
            	<%if(uid==tracker) {%>
            	<a href="/RiskManagement/changeState">已解决</a><%}}
            	else{%><span style="color:black;">已解决</span> <%}           
            %>
            </div>
            <div style="clear:both;">
            <span>跟踪者</span>
            <select name="tracker">
            	<option value=<%=tracker %>><%=tracker_name %></option>
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
	         <div style="clear:both;">
            <span>创建时间 </span> <span><%=buildtime %></span>
            </div>
            <% if(changetime!=null){%>
             <div style="clear:both;">
            <span>修改时间 </span> <span><%=changetime %></span>
            </div>
            <%}%>
	        <input type="submit" value="保存"  class="button" style="clear:both;margin-left:80px;margin-top:30px;">
        </form>
        <%} %>
        </div>
        <!-- test
        <div class="basic-grey">
        <form action="/RiskManagement/showRisk" method="post">
        	<div>
            <span>风险名称</span>
            <span style="color:black;">rname</span>
            </div>
            <div style="clear:both;">
            <span>内容</span>
            <span style="color:black;">content</span>
           	</div>
            <div style="clear:both;">
            <span>可能性</span>
           	<span style="color:black;">低</span>
           	<span style="color:blue;">中</span>
           	<span style="color:red;">高</span>       
            
            </div>
            <div style="clear:both;">
            <span>影响程度</span>
            <span style="color:black;">低</span>
            <span style="color:blue;">中</span>
            <span style="color:red;">高</span>         
            </div>
            <div style="clear:both;">
            <span>触发值/值域</span>
            <span style="color:black;">trigger</span>
            </div>
            <div style="clear:both;">
            <span>提交者</span>
            <span style="color:black;">provider</span>
            </div>
            <div style="clear:both;">
            <span>状态</span>
            <span style="color:black;">未触发</span> <a href="/RiskManagement/changeState">已发生</a>
           	<br><span style="color:red;">已发生</span><a href="/RiskManagement/changeState">已解决</a>
            <br><span style="color:black;">已解决</span>
            </div>
            <div style="clear:both;">
            <span>跟踪者</span>
            <span style="color:black;">tracker</span>
        </form>
        </div>
         -->
    </body>
</html>