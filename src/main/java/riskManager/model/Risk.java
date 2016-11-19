package riskManager.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="risks")
public class Risk implements Serializable{
	@Id
	private int rid;
	private int pid;
	private int type;//0：人员变动；1：缺乏共识；2：资金不足；3：设备故障；4：设计欠缺；5：计划过于乐观；6：其他
	private String content;
	private int possibility;
	private int affect;
	private String advice;
	private String thetrigger;
	private int state;//未锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟�
	private int provider;//uid
	private int tracker;//uid
	private Date buildtime;
	private Date changetime;
	private Date solvetime;
	
	
	public Risk(){
		super();
	}
	
	public Risk(int pid, int type, String content, int possibility, int affect, String trigger,
			int provider,int tracker,String advice) {
		
		this.pid = pid;
		this.type = type;
		this.content = content;
		this.possibility = possibility;
		this.affect = affect;
		this.thetrigger = trigger;
		this.provider = provider;
		this.state = 0;
		this.tracker = tracker;
		this.buildtime = new Date();
		this.advice = advice;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getAdvice() {
		return advice;
	}

	public void setAdvice(String advice) {
		this.advice = advice;
	}

	public Date getBuildtime() {
		return buildtime;
	}

	public void setBuildtime(Date buildtime) {
		this.buildtime = buildtime;
	}

	public String getThetrigger() {
		return thetrigger;
	}
	public void setThetrigger(String thetrigger) {
		this.thetrigger = thetrigger;
	}
	public int getPossibility() {
		return possibility;
	}

	public void setPossibility(int possibility) {
		this.possibility = possibility;
	}

	public int getAffect() {
		return affect;
	}

	public void setAffect(int affect) {
		this.affect = affect;
	}

	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getProvider() {
		return provider;
	}

	public void setProvider(int provider) {
		this.provider = provider;
	}

	public int getTracker() {
		return tracker;
	}

	public void setTracker(int tracker) {
		this.tracker = tracker;
	}

	public Date getChangetime() {
		return changetime;
	}

	public void setChangetime(Date changetime) {
		this.changetime = changetime;
	}

	public Date getSolvetime() {
		return solvetime;
	}

	public void setSolvetime(Date solvetime) {
		this.solvetime = solvetime;
	}
	
}
