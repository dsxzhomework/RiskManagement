package riskManager.dao;

import java.util.List;

import riskManager.model.RiskPlan;

public interface RiskPlanDao {
	RiskPlan save(RiskPlan riskplan);
	
	List<RiskPlan> findByUid(int uid);
}
