package riskManager.action;

import riskManager.service.UserService;
import riskManager.model.User;

import java.util.*;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;

public class RegisterAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private UserService userService;
	
	public UserService getUserService(){
		return userService;
	}
	
	public void setUserService(UserService userService){
		this.userService=userService;
	}
	
	public int getUserId(){
		int result=0;
		List<User> theall=userService.getAll();
		if(theall.size()>0){
			result=theall.size()+1;
		}else{
			result=1;
		}
		return result;
	}
	
	public String execute() throws Exception{
		String success="success";
		
		String userrole=(String)request.getParameter("role");
		String username=(String)request.getParameter("name");
		String userpw=(String)request.getParameter("pw");
		String userpwconfirm=(String)request.getParameter("pwconfirm");
		
		ServletContext sc=request.getServletContext();
		
		if(userrole==null||username==null||userpw==null||userpwconfirm==null){
			success="incomplete";
			return success;
		}
		if(!(userpw.equals(userpwconfirm))){
			success="incompatible";
			return success;
		}
		
		int userid=getUserId();
		User user=new User(userid,userrole,userpw,username);
		System.out.println("yes"+userid);
		userService.save(user);
		sc.setAttribute("newuserid", userid);
		
		return success;
	}
}
