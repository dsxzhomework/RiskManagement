package riskManager.action;

import riskManager.service.ProjectService;
import riskManager.service.UserService;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import riskManager.model.Project;
import riskManager.model.User;
import riskManager.model.Statistic;

public class LoginAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private UserService userService;
	private ProjectService projectService;
	
	public UserService getUserService(){
		return userService;
	}
	
	public void setUserService(UserService userService){
		this.userService=userService;
	}
	
	public ProjectService getProjectService() {
		return projectService;
	}

	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}

	@Override
	public String execute() throws Exception {
		String success = "success";
		ServletContext sc=request.getServletContext();
		sc.setAttribute("newuserid", null);
		String id=(String)request.getParameter("id");
		if(id.equals("admin")){
			success="admin";
			List<User> alluser=userService.getAll();
			sc.setAttribute("alluser", alluser);
			return success;
		}
		String passwordin=(String)request.getParameter("ps");
		User user=userService.findByUid(Integer.parseInt(id));
		System.out.println(user);
		if(user==null){
			success="fail";
			return success;
		}
		sc.setAttribute("uid", user.getUid());
		sc.setAttribute("role", user.getRole());
		sc.setAttribute("uname", user.getName());
		if(!(passwordin.equals(user.getPassword()))){
			success="fail";
			return success;
		}
		List<Project> pl = projectService.getProjectsByUid(Integer.parseInt(id));
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
//		System.out.println(Integer.toString(pids.length));
		sc.setAttribute("pids", pids);
		sc.setAttribute("pnames",pnames);
		sc.setAttribute("mangers", mangers);
		
		List<Statistic> statisticresult=new ArrayList();
		sc.setAttribute("statisticresult", statisticresult);
		
		String[] developers = (String[])sc.getAttribute("developers");
		String[] developer_names = (String[])sc.getAttribute("developers_names");
		if(developers==null){
			List<User> ul = userService.findByRole("developer");
			//System.out.println("desuccess");
			if(ul.size()>0){
				//System.out.println("have");
				developers = new String[ul.size()];
				developer_names = new String[ul.size()];
				for(int i=0;i<ul.size();i++){
					developers[i] = ul.get(i).getUid()+"";
					developer_names[i] = ul.get(i).getName();
				}
				System.out.println(Integer.toString(developer_names.length));
				sc.setAttribute("developers", developers);
				sc.setAttribute("developer_names", developer_names);
			}
		}
		return success;
	}
}

