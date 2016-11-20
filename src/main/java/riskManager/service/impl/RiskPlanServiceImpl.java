package riskManager.service.impl;

import java.util.ArrayList;
import java.util.List;

import riskManager.dao.RiskDao;
import riskManager.dao.RiskPlanDao;
import riskManager.dao.RpidRidDao;
import riskManager.model.Risk;
import riskManager.model.RiskPlan;
import riskManager.model.RpidRid;
import riskManager.service.RiskPlanService;

public class RiskPlanServiceImpl implements RiskPlanService {
	private RiskPlanDao riskplanDao;
	private RpidRidDao rpidridDao;
	private RiskDao riskDao;

	public RiskPlanDao getRiskplanDao() {
		return riskplanDao;
	}

	public void setRiskplanDao(RiskPlanDao riskplanDao) {
		this.riskplanDao = riskplanDao;
	}

	public RpidRidDao getRpidridDao() {
		return rpidridDao;
	}

	public void setRpidridDao(RpidRidDao rpidridDao) {
		this.rpidridDao = rpidridDao;
	}

	public RiskDao getRiskDao() {
		return riskDao;
	}

	public void setRiskDao(RiskDao riskDao) {
		this.riskDao = riskDao;
	}

	@Override
	public List<RiskPlan> findRPByUid(int uid) {
		// TODO Auto-generated method stub
		
		return riskplanDao.findByUid(uid);
	}

	@Override
	public RiskPlan addPlan(RiskPlan riskplan) {
		// TODO Auto-generated method stub
		return riskplanDao.save(riskplan);
	}

	@Override
	public List<Risk> findRiskByRpid(int rpid) {
		// TODO Auto-generated method stub
		List<RpidRid> rrl = rpidridDao.findByRpid(rpid);
		List<Risk> rl = new ArrayList<Risk>();
		if(rrl!=null){
			for(RpidRid rr:rrl){
				rl.add(riskDao.findByRid(rr.getRid()));
			}
		}
		return rl;
	}

	@Override
	public RpidRid addRisk(RpidRid rpidrid) {
		// TODO Auto-generated method stub
		return rpidridDao.save(rpidrid);
	}

	@Override
	public boolean deleteRisk(RpidRid rpidrid) {
		// TODO Auto-generated method stub
		return rpidridDao.delete(rpidrid);
	}

}
