package riskManager.service;

import java.util.List;
import java.util.Date;

import riskManager.model.Risk;
import riskManager.model.Statistic;

public interface RiskService {
	Risk save(Risk risk);
	
	List<Risk> findByPid(int pid);
	
	Risk findByRid(int rid);
	
	List<Risk> findByType(int pid,int type);
	
	List<Risk> findByState(int pid,int state);
	
	List<Risk> findByTime(int pid,Date searchstarttime);
	
	List<Risk> findByPRid(int pid,int rid);
	
	List<Risk> findByTracker(int pid,int tracker);
	
	Risk update(Risk risk);
	
	boolean delete(Risk risk);
	
	List<Statistic> statisticIdentify(Date start,Date end);
	
	List<Statistic> statisticFault(Date start,Date end);
}
