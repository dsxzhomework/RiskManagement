package riskManager.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//import sun.security.util.Password;

@Entity
@Table(name="users")
public class User implements Serializable{

	@Id
	private int uid;
	private String role;
	private String password;
	private String name;
	//private boolean projectcreate;
	//private boolean developermanage;
	
	public User(){
		super();
	}
	
	public User(int uid,String role,String password,String name){
		super();
		this.uid=uid;
		this.role=role;
		this.password=password;
		this.name=name;
	}
	
	public int getUid(){
		return uid;
	}
	
	public void setUid(int uid){
		this.uid=uid;
	}
	
	public String getRole(){
		return role;
	}
	
	public void setRole(String role){
		this.role=role;
	}
	
	public String getPassword(){
		return password;
	}
	
	public void setPassword(String password){
		this.password=password;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name=name;
	}
	/**
	public boolean getProjectcreate(){
		return projectcreate;
	}
	
	public void setProjectcreate(boolean projectcreate){
		this.projectcreate=projectcreate;
	}
	
	public boolean getDevelopermanage(){
		return developermanage;
	}
	
	public void setDevelopermanage(boolean developermanage){
		this.developermanage=developermanage;
	}*/
}
