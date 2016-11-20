package riskManager.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

import hibernate3.support.YeekuHibernateDaoSupport;
import riskManager.dao.BaseDao;
import riskManager.dao.RiskPlanDao;
import riskManager.model.Risk;
import riskManager.model.RiskPlan;

public class RiskPlanDaoImpl extends YeekuHibernateDaoSupport implements RiskPlanDao {

	@Autowired
	private BaseDao baseDao;
	
	public BaseDao getBaseDao() {
		return baseDao;
	}
	
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	
	@Override
	public RiskPlan save(RiskPlan riskplan) {
		// TODO Auto-generated method stub
		String hql = "select max(rp.rpid) from riskManager.model.RiskPlan rp";
		Session session = baseDao.getNewSession();
		int id = 0;
		if(session.createQuery(hql).uniqueResult()!=null)
			id = (Integer)session.createQuery(hql).uniqueResult();
		riskplan.setRpid(++id);
		baseDao.save(riskplan);
		riskplan = findByRpid(riskplan.getRpid());
		return riskplan;
	}

	@Override
	public List<RiskPlan> findByUid(int uid) {
		// TODO Auto-generated method stub
		String sql = "from riskManager.model.RiskPlan where uid ='"+uid+"'";
		Session session = baseDao.getNewSession();
		List<RiskPlan> rpl = session.createQuery(sql).list();
		return rpl;
	}
	
	public RiskPlan findByRpid(int rpid) {
		// TODO Auto-generated method stub
		RiskPlan rp = null;
		String sql = "from riskManager.model.RiskPlan where rpid ='"+rpid+"'";
		Session session = baseDao.getNewSession();
		List<RiskPlan> rpl = session.createQuery(sql).list();
		if(rpl.size()>0)
			rp = rpl.get(0);
		return rp;

	}

}
