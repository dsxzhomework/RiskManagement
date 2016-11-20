package riskManager.model;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class Statistic implements Serializable{

	private int type;
	private int countresult;
	
	public Statistic(){
		super();
	}
	
	public Statistic(int type,int countresult){
		super();
		this.type=type;
		this.countresult=countresult;
	}
	
	public int getType(){
		return type;
	}
	
	public void setType(int type){
		this.type=type;
	}
	
	public int getCountresult(){
		return countresult;
	}
	
	public void setCountresult(int countresult){
		this.countresult=countresult;
	}
}
