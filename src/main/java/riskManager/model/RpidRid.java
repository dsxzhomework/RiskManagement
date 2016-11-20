package riskManager.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="rpidrid")
public class RpidRid implements Serializable{
	@Id
	private String belong;//uid,pid
	private int rpid;
	private int rid;
	
	public RpidRid(){
		super();
	}
	
	public RpidRid(int rpid, int rid) {

		this.belong = rpid +","+rid;
		this.rpid = rpid;
		this.rid = rid;
	}

	public String getBelong() {
		return belong;
	}

	public void setBelong(String belong) {
		this.belong = belong;
	}

	public int getRpid() {
		return rpid;
	}

	public void setRpid(int rpid) {
		this.rpid = rpid;
	}

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}
	
	
}
