package riskManager.dao;

import java.util.Date;
import java.util.List;

import riskManager.model.Risk;

public interface RiskDao {
	Risk save(Risk risk);
	
	List<Risk> findByPid(int pid);
	
	Risk findByRid(int rid);
	
	List<Risk> findByType(int pid,int type);
	
	List<Risk> findByState(int pid,int state);
	
	List<Risk> findByTime(int pid,Date searchstarttime);
	
	List<Risk> findByPRid(int pid,int rid);
	
	List<Risk> findByTracker(int pid,int tracker);
	
	Risk update(Risk risk);
	
	void delete(Risk risk);
	
    List<Integer> statisticIdentify(Date start,Date end);
	
	List<Integer> statisticFault(Date start,Date end);
}
