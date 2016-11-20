package riskManager.service;

import java.util.List;

import riskManager.model.Risk;
import riskManager.model.RiskPlan;
import riskManager.model.RpidRid;

public interface RiskPlanService {

	List<RiskPlan> findRPByUid(int uid);
	
	RiskPlan addPlan(RiskPlan riskPlan);
	
	List<Risk> findRiskByRpid(int rpid);
	
	RpidRid addRisk(RpidRid rpidrid);
	
	boolean deleteRisk(RpidRid rpidrid);
	
	
}
