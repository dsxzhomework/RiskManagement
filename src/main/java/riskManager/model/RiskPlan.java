package riskManager.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="riskplans")
public class RiskPlan implements Serializable{
	@Id
	private int rpid;
	private int uid;
	private String name;
	
	public RiskPlan(){
		super();
	}
	
	public RiskPlan(int uid, String name) {

		this.uid = uid;
		this.name = name;
	}
	public int getRpid() {
		return rpid;
	}
	public void setRpid(int rpid) {
		this.rpid = rpid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
