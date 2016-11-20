package riskManager.action;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;

import riskManager.model.Risk;
import riskManager.model.RiskPlan;
import riskManager.model.RpidRid;
import riskManager.service.RiskPlanService;
import riskManager.service.RiskService;

public class RiskPlanAction extends BaseAction{
	@Autowired
	private RiskPlanService rpservice;
	
	String success = "success";
	String fail = "fail";
	String[] typename = {"人员变动","缺乏共识","资金不足","设备故障","设计欠缺","计划过于乐观","其他"};
	
	public RiskPlanService getRpservice() {
		return rpservice;
	}

	public void setRpservice(RiskPlanService rpservice) {
		this.rpservice = rpservice;
	}

	public String addRiskPlan(){
		String rpname = request.getParameter("rpname");
		if(rpname==null)
			return fail;
		ServletContext sc = request.getServletContext();
		int uid = (int) sc.getAttribute("uid");
		RiskPlan rp = new RiskPlan(uid,rpname);
		rpservice.addPlan(rp);
		return success;
	}
	
	public String showRPList(){
		ServletContext sc = request.getServletContext();
		int uid = (int) sc.getAttribute("uid");
		List<RiskPlan> rpl = rpservice.findRPByUid(uid);
		int[] rpids = new int[rpl.size()];
		String[] rpnames = new String[rpl.size()];
		for(int i=0;i<rpl.size();i++){
			rpids[i] = rpl.get(i).getRpid();
			rpnames[i] = rpl.get(i).getName();
		}
		sc.setAttribute("rpids", rpids);
		sc.setAttribute("rpnames", rpnames);
		return success;
	}
	
	public String showRiskList(){
		int rpid = Integer.parseInt(request.getParameter("rpid"));
		List<Risk> rl = rpservice.findRiskByRpid(rpid);
		ServletContext sc = request.getServletContext();

		String[] rid = new String[rl.size()];
		String[] rname = new String[rl.size()];
		String[] content = new String[rl.size()];
		String[] state = new String[rl.size()];
		String[] affect = new String[rl.size()];
		for(int i=0;i<rl.size();i++){
			Risk r = rl.get(i);
			rid[i] = r.getRid()+"";
			rname[i] = typename[r.getType()];
			content[i] = r.getContent();
			int s = r.getState();
			int a = r.getAffect();
			switch(s){
				case 0:state[i] = "未发生";break;
				case 1:state[i] = "已发生";break;
				default:state[i] = "已解决";
			}
			switch(a){
				case 0:affect[i] = "低";break;
				case 1:affect[i] = "中";break;
				default:affect[i] = "高";
			}
		}
		
		String rpname = "";
		int[] rpids = (int[])sc.getAttribute("rpids");
		String[] rpnames = (String[])sc.getAttribute("rpnames");
		for(int i=0;i<rpids.length;i++){
			if(rpids[i]==rpid){
				rpname = rpnames[i];
			}
		}
		
		sc.setAttribute("rpid", rpid);
		sc.setAttribute("rpname", rpname);
		sc.setAttribute("rids", rid);
		sc.setAttribute("rnames", rname);
		sc.setAttribute("contents", content);
		sc.setAttribute("states", state);
		sc.setAttribute("affects", affect);
		return success;
	}
	
	public String importRisk(){
		ServletContext sc = request.getServletContext();
		int rpid = (int) sc.getAttribute("rpid");
		
		int[] rids = null;
		for(int id:rids){
			RpidRid rr = new RpidRid(rpid,id);
			rpservice.addRisk(rr);
		}
		return success;
	}
	
	public String deleteRisk(){
		int rid = Integer.parseInt(request.getParameter("rid"));
		ServletContext sc = request.getServletContext();
		int rpid = (int) sc.getAttribute("rpid");
		RpidRid rr = new RpidRid(rpid,rid);
		rpservice.deleteRisk(rr);
		return success;
	}
}
