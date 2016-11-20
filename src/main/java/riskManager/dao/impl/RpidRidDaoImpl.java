package riskManager.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

import hibernate3.support.YeekuHibernateDaoSupport;
import riskManager.dao.BaseDao;
import riskManager.dao.RpidRidDao;
import riskManager.model.Risk;
import riskManager.model.RpidRid;

public class RpidRidDaoImpl extends YeekuHibernateDaoSupport implements RpidRidDao{

	@Autowired
	private BaseDao baseDao;
	
	public BaseDao getBaseDao() {
		return baseDao;
	}
	
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	
	@Override
	public RpidRid save(RpidRid rpidrid) {
		// TODO Auto-generated method stub
		baseDao.save(rpidrid);
		return rpidrid;
	}

	@Override
	public boolean delete(RpidRid rpidrid) {
		// TODO Auto-generated method stub
		baseDao.delete(rpidrid);
		return true;
	}

	@Override
	public List<RpidRid> findByRpid(int rpid) {
		// TODO Auto-generated method stub
		String sql = "from riskManager.model.RpidRid where rpid ='"+rpid+"'";
		Session session = baseDao.getNewSession();
		List<RpidRid> rrl = session.createQuery(sql).list();
		return rrl;
	}

}
