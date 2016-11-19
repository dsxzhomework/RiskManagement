package riskManager.dao;

import java.util.Date;
import java.util.List;

import riskManager.model.Risk;

public interface RiskDao {
	Risk save(Risk risk);
	
	List<Risk> findByPid(int pid);
	
	Risk findByRid(int rid);
	
	Risk update(Risk risk);
	
	void delete(Risk risk);
	
    List<Integer> statisticIdentify(Date start,Date end);
	
	List<Integer> statisticFault(Date start,Date end);
}
