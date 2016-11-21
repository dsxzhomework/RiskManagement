package riskManager.action;

import java.util.*;
import javax.servlet.ServletContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;

import riskManager.model.Risk;
import riskManager.service.RiskService;
import riskManager.model.Statistic;

public class RiskstatisticAction extends BaseAction{

	@Autowired
	private RiskService riskService;
	
	SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
	String success="success";
	
	public RiskService getRiskService(){
		return riskService;
	}
	
	public void setRiskService(RiskService riskService){
		this.riskService=riskService;
	}
	
	public String statistic(){
		
		ServletContext sc=request.getServletContext();
		
		String sorttype=request.getParameter("statictype");
		String timestart=request.getParameter("searchtimestart");
		String timeend=request.getParameter("searchtimeend");
		
		System.out.println(timestart);
		System.out.println(timeend);
		/*
		Date start=null;
		Date end=null;
		try {
			start=f.parse(timestart);
			end=f.parse(timeend);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		List<Risk> listtime=new ArrayList();
		
		if(sorttype.equals("identifymost")){
			listtime=riskService.statisticIdentify(timestart, timeend);
		}
		if(sorttype.equals("questionmost")){
			listtime=riskService.statisticFault(timestart, timeend);
		}
		
	//	System.out.println(listtime.size()+"");
		
		int[][] typecount=new int[7][2];
		typecount[0][0]=0;
		typecount[1][0]=1;
		typecount[2][0]=2;
		typecount[3][0]=3;
		typecount[4][0]=4;
		typecount[5][0]=5;
		typecount[6][0]=6;
		int size=listtime.size();
		for(int i=0;i<size;i++){
			Risk r=listtime.get(i);
			int rt=r.getType();
			System.out.println(rt+"");
			switch(rt){
			case 0:typecount[0][1]++;;break;
			case 1:typecount[1][1]++;break;
			case 2:typecount[2][1]++;break;
			case 3:typecount[3][1]++;break;
			case 4:typecount[4][1]++;break;
			case 5:typecount[5][1]++;break;
			default:typecount[6][1]++;
			}
		}
		/*
		for(int i=0;i<7;i++){
			int t=typecount[i][0];
			int c=typecount[i][1];
			for(int j=i+1;j<7;j++){
				if(c<typecount[j][1]){
					typecount[i][0]=typecount[j][0];
					typecount[i][1]=typecount[j][1];
					typecount[j][0]=t;
					typecount[j][1]=c;
					t=typecount[i][0];
					c=typecount[i][1];
				}else{
					continue;
				}
			}
		}*/
		
		List<Statistic> result=new ArrayList();
		for(int i=0;i<7;i++){
			Statistic s=new Statistic(typecount[i][0],typecount[i][1]);
			System.out.println(typecount[i][0]+","+typecount[i][1]);
			result.add(s);
		}
	//	System.out.println(result.size()+"");
		
	//	Statistic s=resultidentify.get(0);
	//	System.out.println(s.getType()+","+s.getCountresult());
		
		/*
		 * int sizeidentify=resultidentify.size();
		String[] identifyrisklist=new String[sizeidentify];
		for(int i=0;i<sizeidentify;i++){
			identifyrisklist[i]=Integer.toString(resultidentify.get(i));
		}
		
		int sizefault=resultfault.size();
		String[] faultrisklist=new String[sizefault];
		for(int i=0;i<sizefault;i++){
			faultrisklist[i]=Integer.toString(resultfault.get(i));
		}
		sc.setAttribute("identifyrisklist", identifyrisklist);
		sc.setAttribute("faultrisklist", faultrisklist);
		 */
		
		/*
		if(sorttype.equals("identifymost")){
			sc.setAttribute("statisticresult", resultidentify);
		}else{
			if(sorttype.equals("questionmost")){
				sc.setAttribute("statisticresult", resultfault);
			}else{
				success="incomplete condition";
			}
		}*/
		sc.setAttribute("statisticresult", result);
		
		return success;
	}
	
	public String importrisk(){
        ServletContext sc=request.getServletContext();
		
		String sorttype=request.getParameter("statictype");
		String timestart=request.getParameter("searchtimestart");
		String timeend=request.getParameter("searchtimeend");
		
		System.out.println(timestart);
		System.out.println(timeend);
		
        List<Risk> listtime=new ArrayList();
		
		if(sorttype.equals("identifymost")){
			listtime=riskService.statisticIdentify(timestart, timeend);
			System.out.println("identify");
		}
		if(sorttype.equals("questionmost")){
			listtime=riskService.statisticFault(timestart, timeend);
		}
		
		int[][] typecount=new int[7][2];
		typecount[0][0]=0;
		typecount[1][0]=1;
		typecount[2][0]=2;
		typecount[3][0]=3;
		typecount[4][0]=4;
		typecount[5][0]=5;
		typecount[6][0]=6;
		int size=listtime.size();
		for(int i=0;i<size;i++){
			Risk r=listtime.get(i);
			int rt=r.getType();
			System.out.println(rt+"");
			switch(rt){
			case 0:typecount[0][1]++;;break;
			case 1:typecount[1][1]++;break;
			case 2:typecount[2][1]++;break;
			case 3:typecount[3][1]++;break;
			case 4:typecount[4][1]++;break;
			case 5:typecount[5][1]++;break;
			default:typecount[6][1]++;
			}
		}
		for(int i=0;i<7;i++){
			int t=typecount[i][0];
			int c=typecount[i][1];
			for(int j=i+1;j<7;j++){
				if(c<typecount[j][1]){
					typecount[i][0]=typecount[j][0];
					typecount[i][1]=typecount[j][1];
					typecount[j][0]=t;
					typecount[j][1]=c;
					t=typecount[i][0];
					c=typecount[i][1];
				}else{
					continue;
				}
			}
		}
		
		List<Risk> result=new ArrayList();
		for(int i=0;i<listtime.size();i++){
			Risk rl=listtime.get(i);
			int tt=rl.getType();
			if(tt==typecount[0][0])
				result.add(rl);
		}
		for(int i=0;i<listtime.size();i++){
			Risk rl=listtime.get(i);
			int tt=rl.getType();
			if(tt==typecount[1][0])
				result.add(rl);
		}
		for(int i=0;i<listtime.size();i++){
			Risk rl=listtime.get(i);
			int tt=rl.getType();
			if(tt==typecount[2][0])
				result.add(rl);
		}
		for(int i=0;i<listtime.size();i++){
			Risk rl=listtime.get(i);
			int tt=rl.getType();
			if(tt==typecount[3][0])
				result.add(rl);
		}
		for(int i=0;i<listtime.size();i++){
			Risk rl=listtime.get(i);
			int tt=rl.getType();
			if(tt==typecount[4][0])
				result.add(rl);
		}
		for(int i=0;i<listtime.size();i++){
			Risk rl=listtime.get(i);
			int tt=rl.getType();
			if(tt==typecount[5][0])
				result.add(rl);
		}
		for(int i=0;i<listtime.size();i++){
			Risk rl=listtime.get(i);
			int tt=rl.getType();
			if(tt==typecount[6][0])
				result.add(rl);
		}
		
		String[] typename = {"人员变动","缺乏共识","资金不足","设备故障","设计欠缺","计划过于乐观","其他"};
		int resultsize=result.size();
		String[] rids=new String[resultsize];
		String[] rnames=new String[resultsize];
		String[] affects=new String[resultsize];
		String[] states=new String[resultsize];
		
		for(int i=0;i<resultsize;i++){
			Risk r=result.get(i);
			rids[i]=Integer.toString(r.getRid());
			rnames[i]=typename[r.getType()];
			int s = r.getState();
			int a = r.getAffect();
			switch(s){
				case 0:states[i] = "未发生";break;
				case 1:states[i] = "已发生";break;
				default:states[i] = "已解决";
			}
			switch(a){
				case 0:affects[i] = "低";break;
				case 1:affects[i] = "中";break;
				default:affects[i] = "高";
			}
		}
		
		sc.setAttribute("rids", rids);
		sc.setAttribute("rnames", rnames);
		sc.setAttribute("affects", affects);
		sc.setAttribute("states", states);
		return success;
	}
}
