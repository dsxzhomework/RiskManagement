package riskManager.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="project")
public class Project implements Serializable{
	@Id
	private int pid;
	private String name;
	private int manager;//uid
	
	public Project() {
		super();
	}

	public Project(String name,int manager) {
		super();
		this.name = name;
		this.manager = manager;
	}
	
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getManager() {
		return manager;
	}

	public void setManager(int manager) {
		this.manager = manager;
	}
	
}

