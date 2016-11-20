<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>RiskManagement risk group</title>
    <link href="./css/style.css" rel="stylesheet" type="text/css" media="all"/>
   <link href="./css/component.css" rel="stylesheet" type="text/css" media="all"/>
	<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
	<style>
	#subtop {
		width:100%;
		height:20%;
		margin-top:40px;
		margin-left:140px;
	}
	#diagram {
		width:100%;
		height:70%;
		margin-top:40px;
		margin-left:40px;
	}
	#choose {
		margin-left:620px;
	}
	</style>
	<script src="Chart.js"></script>
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

</div>
<div id="diagram">
	<div style="margin-left:100px;margin-top:40px">
        <canvas id="a_canvas" width="700" height="400"></canvas>    
		<script>    
    	function choose(){    
     
        //window.addEventListener("load", function(){    
     	//get data here
          var data = [1000,1300,2000,1200,2000,3000];    
          var xinforma = ['人员变动','缺乏共识','资金不足','设备故障','设计欠缺','计划过于乐观'];    
          //0：人员变动；1：缺乏共识；2：资金不足；3：设备故障；4：设计欠缺；5：计划过于乐观；6：其他

          // 获取上下文    
          var a_canvas = document.getElementById('a_canvas');    
          var context = a_canvas.getContext("2d");    
     
     
          // 绘制背景    
          var gradient = context.createLinearGradient(0,0,0,300);    

          //gradient.addColorStop(0,"#B0E2FF");    
          //gradient.addColorStop(1,"#ffffff");
     
     
          context.fillStyle = gradient;    
     
          context.fillRect(0,0,a_canvas.width,a_canvas.height);    
     
          var realheight = a_canvas.height-15;    
          var realwidth = a_canvas.width-40;    
          // 描绘边框    
          var grid_cols = data.length + 1;    
          var grid_rows = 4;    
          var cell_height = realheight / grid_rows;    
          var cell_width = realwidth / grid_cols;    
          context.lineWidth = 1;    
          context.strokeStyle = "#a0a0a0";    
     
          // 结束边框描绘    
          context.beginPath();    
          // 准备画横线    
          for(var row = 1; row <= grid_rows; row++){    
            var y = row * cell_height;    
            context.moveTo(0,y);    
            context.lineTo(a_canvas.width, y);    
          }  
              
          //划横线    
          context.moveTo(0,realheight);    
          context.lineTo(realwidth,realheight);    
                    
                 
          //画竖线    
          context.moveTo(0,20);    
          context.lineTo(0,realheight);    
          context.lineWidth = 1;    
          context.strokeStyle = "black";    
          context.stroke();    
                  
     
          var max_v =0;    
              
          for(var i = 0; i<data.length; i++){    
            if (data[i] > max_v) { max_v =data[i]};    
          }    
          max_vmax_v = max_v * 1.1;    
          // 将数据换算为坐标    
          var points = [];    
          for( var i=0; i < data.length; i++){    
            var v= data[i];    
            var px = cell_width*(i + 1);    
            var py = realheight - realheight*(v / max_vmax_v);    
            //alert(py);    
            points.push({"x":px,"y":py});    
          }    
     
          //绘制坐标图形    
          for(var i in points){    
            var p = points[i];    
            context.beginPath();    
            context.fillStyle="#FFC0CB";    
            context.fillRect(p.x,p.y,15,realheight-p.y);    
                 
            context.fill();    
          }    
          //添加文字    
          for(var i in points)    
          {  var p = points[i];    
             context.beginPath();    
             context.fillStyle="black";    
             context.fillText(data[i], p.x + 1, p.y - 15);    
             context.fillText(xinforma[i],p.x + 1,realheight+12);    
             context.fillText('风险类型',realwidth,realheight+12);    
             context.fillText('资金量',0,10);    
             }    
        //},false);    
      };    
           
	</script>
	</div>
</div>
</body>
</html>