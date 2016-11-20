package riskManager.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

import hibernate3.support.YeekuHibernateDaoSupport;
import riskManager.dao.BaseDao;
import riskManager.dao.RiskDao;
import riskManager.model.Risk;

public class RiskDaoImpl extends YeekuHibernateDaoSupport implements RiskDao{
	
	@Autowired
	private BaseDao baseDao;
	
	public BaseDao getBaseDao() {
		return baseDao;
	}
	
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public Risk save(Risk risk) {
		// TODO Auto-generated method stub
		String hql = "select max(r.rid) from riskManager.model.Risk r";
		Session session = baseDao.getNewSession();
		int id = 0;
		if(session.createQuery(hql).uniqueResult()!=null)
			id = (Integer)session.createQuery(hql).uniqueResult();
		risk.setRid(++id);
		Risk r = null;
		baseDao.save(risk);
		r = this.findByRid(risk.getRid());
		return r;
	}

	@Override
	public List<Risk> findByPid(int pid) {
		// TODO Auto-generated method stub
		String sql = "from riskManager.model.Risk where pid ='"+pid+"'";
		Session session = baseDao.getNewSession();
		List<Risk> rl = session.createQuery(sql).list();
		return rl;
	}


	@Override
	public Risk findByRid(int rid) {
		// TODO Auto-generated method stub
		Risk risk =null;
		String sql = "from riskManager.model.Risk where rid ='"+rid+"'";
		Session session = baseDao.getNewSession();
		List<Risk> rl = session.createQuery(sql).list();
		if(rl.size()>0){
			risk = rl.get(0);
		}
		return risk;
	}

	@Override
	public Risk update(Risk risk) {
		// TODO Auto-generated method stub
		baseDao.update(risk);
		Risk r = findByRid(risk.getRid());
		return r;
	}

	
	//?
	@Override
	public void delete(Risk risk) {
		// TODO Auto-generated method stub
		baseDao.delete(risk);
	}

	@Override
	public List<Integer> statisticIdentify(Date start, Date end) {
		// TODO Auto-generated method stub
		String sql = "select t.type from (select type,count(type) from riskManager.model.Risk where buildtime between '"+start+"' and '"+end+"' group by type order by count(type) desc) t";
		Session session = baseDao.getNewSession();
		List<Integer> result = session.createQuery(sql).list();
		return result;
	}

	@Override
	public List<Integer> statisticFault(Date start, Date end) {
		// TODO Auto-generated method stub
		String sql = "select t.type from (select type,count(type) from riskManager.model.Risk where changetime between '"+start+"' and '"+end+"' group by type order by count(type) desc) t";
		Session session = baseDao.getNewSession();
		List<Integer> result = session.createQuery(sql).list();
		return null;
	}

	@Override
	public List<Risk> findByType(int pid, int type) {
		// TODO Auto-generated method stub
		String sql="from riskManager.model.Risk where pid='"+pid+"' and type='"+type+"'";
		Session session=baseDao.getNewSession();
		List<Risk> result=session.createQuery(sql).list();
		return result;
	}

	@Override
	public List<Risk> findByState(int pid, int state) {
		// TODO Auto-generated method stub
		String sql="from riskManager.model.Risk where pid='"+pid+"' and state='"+state+"'";
		Session session=baseDao.getNewSession();
		List<Risk> result=session.createQuery(sql).list();
		return result;
	}

	@Override
	public List<Risk> findByTime(int pid, Date searchstarttime) {
		// TODO Auto-generated method stub
		String sql="from riskManager.model.Risk where pid='"+pid+"' and buildtime>'"+searchstarttime+"'";
		Session session=baseDao.getNewSession();
		List<Risk> result=session.createQuery(sql).list();
		return result;
	}

	@Override
	public List<Risk> findByPRid(int pid, int rid) {
		// TODO Auto-generated method stub
		String sql="from riskManager.model.Risk where pid='"+pid+"' and rid='"+rid+"'";
		Session session=baseDao.getNewSession();
		List<Risk> result=session.createQuery(sql).list();
		return result;
	}

	@Override
	public List<Risk> findByTracker(int pid, int tracker) {
		// TODO Auto-generated method stub
		String sql="from riskManager.model.Risk where pid='"+pid+"' and tracker='"+tracker+"'";
		Session session=baseDao.getNewSession();
		List<Risk> result=session.createQuery(sql).list();
		return result;
	}

	
	
}
