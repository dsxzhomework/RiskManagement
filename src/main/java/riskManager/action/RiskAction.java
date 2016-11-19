package riskManager.action;

import java.text.SimpleDateFormat;

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
}
