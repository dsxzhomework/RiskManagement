package riskManager.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="accede")
public class Accede implements Serializable{
	@Id
	private String accede;//uid,pid
	private int uid;
	private int pid;
	
	public Accede(){
		super();
	}
	
	public Accede(int uid, int pid) {
		super();
		this.accede = uid+","+pid;
		this.uid=uid;
		this.pid=pid;
	}

	public String getAccede() {
		return accede;
	}

	public void setAccede(String accede) {
		this.accede = accede;
	}
	
	public int getUid(){
		return uid;
	}
	
	public void setUid(int uid){
		this.uid=uid;
	}
	
	public int getPid(){
		return pid;
	}
	
	public void setPid(int pid){
		this.pid=pid;
	}
	
	
}
