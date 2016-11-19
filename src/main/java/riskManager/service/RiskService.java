package riskManager.service;

import java.util.List;
import java.util.Date;

import riskManager.model.Risk;

public interface RiskService {
	Risk save(Risk risk);
	
	List<Risk> findByPid(int pid);
	
	Risk findByRid(int rid);
	
	Risk update(Risk risk);
	
	boolean delete(Risk risk);
	
	List<Integer> statisticIdentify(Date start,Date end);
	
	List<Integer> statisticFault(Date start,Date end);
}
