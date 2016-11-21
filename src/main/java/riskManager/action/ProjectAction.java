package riskManager.action;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;

import riskManager.model.Project;
import riskManager.model.Risk;
import riskManager.model.User;
import riskManager.service.ProjectService;
import riskManager.service.RiskService;
import riskManager.service.UserService;

public class ProjectAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private ProjectService projectService;
	private RiskService riskService;
	private UserService userService;
	

	String success = "success";
	String fail = "fail";
	String repeat = "repeat";
	String[] typename = {"人员变动","缺乏共识","资金不足","设备故障","设计欠缺","计划过于乐观","其他"};
	public ProjectService getProjectService() {
		return projectService;
	}

	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}

	public RiskService getRiskService() {
		return riskService;
	}

	public void setRiskService(RiskService riskService) {
		this.riskService = riskService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	//�����գ�action��addProject
	public String add(){
		if(request.getParameter("projectname")==""||request.getParameter("developer1")==""
				||request.getParameter("developer2")==""||request.getParameter("developer4")==""){
			return fail;
		}
		String name = request.getParameter("projectname");
		int developer1 = Integer.parseInt(request.getParameter("developer1"));
		int developer2 = Integer.parseInt(request.getParameter("developer2"));
		int developer3 = Integer.parseInt(request.getParameter("developer3"));
		if(developer1==developer2||developer1==developer3||developer2==developer3){
			return repeat;
		}
		ServletContext sc = request.getServletContext();
		int uid = (int) sc.getAttribute("uid");//
		//�����Ƿ���Ŀ�鳤
		Project project = new Project(name,uid);
		project = projectService.save(project);
		System.out.println((project.getPid()));
		sc.setAttribute("pid", project.getPid());
		int[] member = new int[]{developer1,developer2,developer3,uid};
		projectService.addMember(member, project.getPid());
		List<Project> pl = projectService.getProjectsByUid(uid);
		System.out.println(Integer.toString(pl.size()));
		String[] pids = null;
		String[] pnames = null;
		String[] mangers = null;
		if(pl.size()>0){
			pids = new String[pl.size()];
			pnames = new String[pl.size()];
			mangers = new String[pl.size()];
			for(int i=0;i<pl.size();i++){
				Project p = pl.get(i);
				pids[i] = p.getPid()+"";
				pnames[i] = p.getName();
				mangers[i] = p.getManager()+"";
			}		
		}
	//	System.out.println(Integer.toString(pids.length));
		sc.setAttribute("pids", pids);
		sc.setAttribute("pnames",pnames);
		sc.setAttribute("mangers", mangers);
		return success;
	}
	
	//��Ŀ��ϸ��Ϣ��action��showAction
	public String show(){
		ServletContext sc = request.getServletContext();
		int pid = 1;
		if(request.getParameter("pid")==null){
			pid = (int)sc.getAttribute("pid");
		}else{
			pid = Integer.parseInt(request.getParameter("pid"));
		}
		//int pid = Integer.parseInt((String) sc.getAttribute("pid"));//
		
		Project project = projectService.findByPid(pid);
		List<Risk> rl = riskService.findByPid(pid);
		
		
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
		sc.setAttribute("pid", pid);
		sc.setAttribute("pname", project.getName());
		sc.setAttribute("rids", rid);
		sc.setAttribute("rnames", rname);
		sc.setAttribute("contents", content);
		sc.setAttribute("states", state);
		sc.setAttribute("affects", affect);
		
		List<User> ul = userService.getMember(pid);
		String[] mids = new String[ul.size()];
		String[] mnames = new String[ul.size()];
		String[] mroles = new String[ul.size()];
		for(int i=0;i<ul.size();i++){
			User u = ul.get(i);
			mids[i] = u.getUid()+"";
			mnames[i] = u.getName();
			//System.out.println(mids[i]+" "+mnames[i]);
			mroles[i] = u.getRole();
		}
		sc.setAttribute("mids", mids);
		sc.setAttribute("mnames", mnames);
		sc.setAttribute("mroles", mroles);
		return success;
	}
	
	
	public String addMember(){
		String memberid = (String) request.getParameter("memberid");
		int mid = Integer.parseInt(memberid);
		int[] member = new int[]{mid};
		ServletContext sc = request.getServletContext();
		int pid = (int) sc.getAttribute("pid");
		projectService.addMember(member,pid);
		return success;
	}
}
