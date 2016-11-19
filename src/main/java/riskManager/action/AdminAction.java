package riskManager.action;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;

import riskManager.service.UserService;
import riskManager.model.User;

import java.util.*;

public class AdminAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private UserService userService;
	
	private List<User> alluser=new ArrayList();
	
	public UserService getUserService(){
		return userService;
	}
	
	public void setUserService(UserService userService){
		this.userService=userService;
	}
	
	@Override
	public String execute() throws Exception {
		String success = "success";
		ServletContext sc=request.getServletContext();
		if(request.getParameter("condition")==null){
			success="fail";
			return success;
		}
		String search=(String) request.getParameter("searchinput");
		if(request.getParameter("condition").equals("uid")){
			char c=search.charAt(0);
			if(c<'0'||c>'9'){
				success="fail";
				return success;
			}
			User user=userService.findByUid(Integer.parseInt(search));
			if(user==null){
				success="failnone";
				return success;
			}
			alluser.add(user);
		}
		if(request.getParameter("condition").equals("name")){
			alluser=userService.findByName(search);
		}
		if(request.getParameter("condition").equals("role")){
			if((!search.equals("manager"))&&(!search.equals("developer"))){
				success="fail";
				return success;
			}
			alluser=userService.findByRole(search);
		}
		if(request.getParameter("condition").equals("alluser")){
			alluser=userService.getAll();
		}
		if(alluser.size()==0){
			success="fail";
			return success;
		}
		sc.setAttribute("alluser", alluser);
		return success;
	}
}
