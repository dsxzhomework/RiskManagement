package riskManager.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

import hibernate3.support.YeekuHibernateDaoSupport;
import riskManager.dao.BaseDao;
import riskManager.dao.AccedeDao;
import riskManager.model.Accede;

public class AccedeDaoImpl extends YeekuHibernateDaoSupport implements AccedeDao {
	@Autowired
	private BaseDao baseDao;
	
	public BaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	
	@Override
	public Accede save(Accede j) {
		// TODO Auto-generated method stub
		baseDao.save(j);
		return null;
	}

	@Override
	public List<Accede> findByUid(int uid) {
		// TODO Auto-generated method stub
		String sql = "from riskManager.model.Accede where uid ='"+uid+"'";
		Session session = baseDao.getNewSession();
		List<Accede> jl = session.createQuery(sql).list();
		return jl;
	}

	@Override
	public List<Accede> findByPid(int pid) {
		// TODO Auto-generated method stub
		String sql = "from riskManager.model.Accede where pid ='"+pid+"'";
		Session session = baseDao.getNewSession();
		List<Accede> jl = session.createQuery(sql).list();
		return jl;
	}

}
