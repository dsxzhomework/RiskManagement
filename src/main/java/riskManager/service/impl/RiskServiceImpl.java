package riskManager.service.impl;

import java.util.Date;
import java.util.List;

import riskManager.dao.RiskDao;
import riskManager.model.Risk;
import riskManager.service.RiskService;

public class RiskServiceImpl implements RiskService {
	private RiskDao riskDao;
	
	public RiskDao getRiskDao() {
		return riskDao;
	}

	public void setRiskDao(RiskDao riskDao) {
		this.riskDao = riskDao;
	}

	@Override
	public Risk save(Risk risk) {
		// TODO Auto-generated method stub
		return riskDao.save(risk);
	}

	@Override
	public List<Risk> findByPid(int pid) {
		// TODO Auto-generated method stub
		return riskDao.findByPid(pid);
	}
	
	@Override
	public Risk findByRid(int rid) {
		// TODO Auto-generated method stub
		return riskDao.findByRid(rid);
	}

	@Override
	public Risk update(Risk risk) {
		// TODO Auto-generated method stub
		return riskDao.update(risk);
	}

	@Override
	public boolean delete(Risk risk) {
		// TODO Auto-generated method stub
		if(risk.getState()!=0)
			return false;
		else{
			riskDao.delete(risk);
			return true;
		}
	}

	@Override
	public List<Integer> statisticIdentify(Date start, Date end) {
		// TODO Auto-generated method stub
		return riskDao.statisticIdentify(start, end);
	}

	@Override
	public List<Integer> statisticFault(Date start, Date end) {
		// TODO Auto-generated method stub
		return riskDao.statisticFault(start, end);
	}

}
