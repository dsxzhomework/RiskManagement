package riskManager.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;

import riskManager.model.Risk;
import riskManager.model.User;
import riskManager.service.RiskService;
import riskManager.service.UserService;

public class RiskAction extends BaseAction{
	
	@Autowired
	private RiskService riskService;
	
	String success = "success";
	String fail = "fail";
	int rid;
	int pid;
	int type;	
	String content;
	int possibility;
	int affect;
	String trigger;
	int state;//
	int provider;//uid
	int tracker;//uid
	String advice;
	String[] typename = {"人员变动","缺乏共识","资金不足","设备故障","设计欠缺","计划过于乐观","其他"};
	public RiskService getRiskService() {
		return riskService;
	}

	public void setRiskService(RiskService riskService) {
		this.riskService = riskService;
	}

	public String add(){
		if(request.getParameter("type")==""||request.getParameter("content")==""||request.getParameter("trigger")==""
				||request.getParameter("possibility")==""||request.getParameter("affect")==""
				||request.getParameter("advice")==""){
			return fail;
		}
		
		type = Integer.parseInt(request.getParameter("type"));
		content = request.getParameter("content");
		trigger = request.getParameter("trigger");
		possibility = Integer.parseInt(request.getParameter("possibility"));
		affect = Integer.parseInt(request.getParameter("affect"));
		tracker =  Integer.parseInt(request.getParameter("tracker"));
		advice = request.getParameter("advice");
		
		ServletContext sc = request.getServletContext();
		pid = (int) sc.getAttribute("pid");//
		provider =  (int) sc.getAttribute("uid");//
		
		Risk risk = new Risk(pid,type, content, possibility, affect, trigger,provider,tracker,advice);
		risk = riskService.save(risk);
		sc.setAttribute("rid",risk.getRid());
		System.out.println("add.action"+risk.getRid());
		return success;
	}
	
	public String change(){
		ServletContext sc = request.getServletContext();
		rid =(int) sc.getAttribute("rid");
		Risk risk = riskService.findByRid(rid);
		state = risk.getState();
		if(state==2){
			return fail;
		}
		risk.setState(++state);
		risk = riskService.update(risk);
		sc.setAttribute("state", risk.getState());
		return success;	
	}
	
	public String show(){
		ServletContext sc = request.getServletContext();
		if(request.getParameter("rid")!=null){
			rid = Integer.parseInt(request.getParameter("rid"));
		}
		else{
			rid =(int)sc.getAttribute("rid");
		}
		System.out.println(rid);
		Risk risk = riskService.findByRid(rid);
		tracker = risk.getTracker();
		provider = risk.getProvider();
		String tracker_name = null;
		String provider_name = null;
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String buildtime =  formatter.format(risk.getBuildtime());
		
		String[] mids = (String[]) sc.getAttribute("mids");
		String[] mnames = (String[]) sc.getAttribute("mnames");
		if(mids!=null){
			for(int i=0;i<mids.length;i++){
				if(mids[i].equals(tracker+"")){
					tracker_name = mnames[i];
				}
				if(mids[i].equals(provider+"")){
					provider_name = mnames[i];
				}
			}
		}
		sc.setAttribute("rid", rid);
		sc.setAttribute("type", risk.getType());
		sc.setAttribute("rname", typename[risk.getType()]);
		sc.setAttribute("content", risk.getContent());
		sc.setAttribute("possibility", risk.getPossibility());
		sc.setAttribute("affect", risk.getAffect());
		sc.setAttribute("advice", risk.getAdvice());
		sc.setAttribute("trigger", risk.getThetrigger());
		sc.setAttribute("state",risk.getState());
		sc.setAttribute("provider", risk.getProvider());
		sc.setAttribute("provider_name", provider_name);//add
		sc.setAttribute("tracker", risk.getTracker());
		sc.setAttribute("tracker_name", tracker_name);//add
		sc.setAttribute("buildtime",buildtime);
		
		return success;	
	}
	
	public String delete(){
		rid = Integer.parseInt(request.getParameter("rid"));
		Risk risk = riskService.findByRid(rid);
		if(riskService.delete(risk))
			return success;
		else
			return fail;
	}

	
	public String update(){
		content = request.getParameter("content");
		trigger = request.getParameter("trigger");
		possibility = Integer.parseInt(request.getParameter("possibility"));
		affect = Integer.parseInt(request.getParameter("affect"));
		tracker =  Integer.parseInt(request.getParameter("tracker"));
		advice = request.getParameter("advice");
		
		ServletContext sc = request.getServletContext();
		rid = (int) sc.getAttribute("rid");
		Risk risk = riskService.findByRid(rid);
		if(advice!=null)
			risk.setAdvice(advice);
		if(content!=null)
			risk.setContent(content);
		if(trigger!=null)
			risk.setThetrigger(trigger);
		risk.setTracker(tracker);
		risk.setAffect(affect);
		risk.setPossibility(possibility);		
		riskService.update(risk);
		return success;
	}
	
	public String importRisk(){
		//获得ridlist
		ServletContext sc = request.getServletContext();
		pid = (int) sc.getAttribute("pid");
		provider =  (int) sc.getAttribute("uid");//
		
		int[] rids = null;
		for(int id:rids){
			Risk risk = riskService.findByRid(id);
			risk.setPid(pid);
			risk.setProvider(provider);
			risk.setState(0);
			risk.setTracker(provider);
			riskService.save(risk);
		}
		return success;
	}
	
	public String findinproject(){
		ServletContext sc=request.getServletContext();
		pid=(int)sc.getAttribute("pid");
		SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
		
		List<Risk> searchresult=new ArrayList();
		List<Risk> projectrisk=riskService.findByPid(pid);
		
		int conditiontype=Integer.parseInt(request.getParameter("searchtype"));
		int conditionstate=Integer.parseInt(request.getParameter("searchstate"));
	//	int search=0;
		
		if(conditiontype==-1&&conditionstate==-1){
			searchresult=projectrisk;
		}
		if(conditiontype==-1&&conditionstate!=-1){
			for(int i=0;i<projectrisk.size();i++){
				Risk r=projectrisk.get(i);
				if(r.getState()==conditionstate){
					searchresult.add(r);
				}else{
					continue;
				}
			}
		}
		if(conditiontype!=-1&&conditionstate==-1){
			for(int i=0;i<projectrisk.size();i++){
				Risk r=projectrisk.get(i);
				if(r.getType()==conditiontype){
					searchresult.add(r);
				}else{
					continue;
				}
			}
		}
		if(conditiontype!=-1&&conditionstate!=-1){
			for(int i=0;i<projectrisk.size();i++){
				Risk r=projectrisk.get(i);
				if(r.getType()==conditiontype&&r.getState()==conditionstate){
					searchresult.add(r);
				}else{
					continue;
				}
			}
		}
		
		/**
		 * //搜索条件类型为空
		if(condition==null){
			searchresult=riskService.findByPid(pid);
		}
		if(condition.equals("searchptype0")){
			search=0;
			searchresult=riskService.findByType(pid, search);
		}
		if(condition.equals("searchptype1")){
			search=1;
			searchresult=riskService.findByType(pid, search);
		}
		if(condition.equals("searchptype2")){
			search=2;
			searchresult=riskService.findByType(pid, search);
		}
		if(condition.equals("searchptype3")){
			search=3;
			searchresult=riskService.findByType(pid, search);
		}
		if(condition.equals("searchptype4")){
			search=4;
			searchresult=riskService.findByType(pid, search);
		}
		if(condition.equals("searchptype5")){
			search=5;
			searchresult=riskService.findByType(pid, search);
		}
		if(condition.equals("searchptype6")){
			search=6;
			searchresult=riskService.findByType(pid, search);
		}
		if(condition.equals("searchpstate1")){
			search=1;
			searchresult=riskService.findByState(pid, search);
		}
		if(condition.equals("searchpstate2")){
			search=1;
			
		}
		 */
		/**
		 * String search=request.getParameter("searchinplaninput");
		
		
		//搜索条件类型不为空且不为全部，但搜索具体条件为空
		if(condition!=null&&(!condition.equals("all"))&&search==null){
			success="incomplete condition";
			return success;
		}
		//按风险类型
		if(condition.equals("searchptype")){
			searchresult=riskService.findByType(pid, Integer.parseInt(search));
		}
		//按建立时间
		if(condition.equals("searchpstarttime")){
			try {
				searchresult=riskService.findByTime(pid, f.parse(search));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//按id
		if(condition.equals("searchprid")){
			searchresult=riskService.findByPRid(pid, Integer.parseInt(search));
		}
		//按跟踪者
		if(condition.equals("searchptracker")){
			int searchtype=0;
			if(search.equals("人员变动")){
				searchtype=0;
			}else{
				if(search.equals("缺乏共识")){
					searchtype=1;
				}else{
					if(search.equals("资金不足")){
						searchtype=2;
					}else{
						if(search.equals("设备故障")){
							searchtype=3;
						}else{
							if(search.equals("设计欠缺")){
								searchtype=4;
							}else{
								if(search.equals("计划过于乐观")){
									searchtype=5;
								}else{
									if(search.equals("")){
										searchtype=6;
									}else{
										success="uncompatible input";
										return success;
									}
								}
							}
						}
					}
				}
			}
			searchresult=riskService.findByTracker(pid, Integer.parseInt(search));
		}
		//按状态
		if(condition.equals("searchpstate")){
			int searchstate=0;
			if(search.equals("未触发")){
				searchstate=0;
			}else{
				if(search.equals("已发生")){
					searchstate=1;
				}else{
					if(search.equals("已解决")){
						searchstate=2;
					}else{
						success="uncompatible input";
						return success;
					}
				}
			}
			searchresult=riskService.findByState(pid,searchstate);
		}
		//项目风险管理计划全部风险
		if(condition.equals("searchall")){
			searchresult=riskService.findByPid(pid);
		 */
		
		//所得结果为空
		if(searchresult.size()==0){
			success="empty";
			return success;
		}
		
		sc.setAttribute("searchinplanresult", searchresult);
		
		return success;
	}
}
