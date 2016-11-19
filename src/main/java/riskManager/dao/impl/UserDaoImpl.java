package riskManager.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

import hibernate3.support.YeekuHibernateDaoSupport;
import riskManager.dao.BaseDao;
import riskManager.dao.UserDao;
import riskManager.model.User;

public class UserDaoImpl extends YeekuHibernateDaoSupport implements UserDao{

	@Autowired
	private BaseDao baseDao;
	
	public BaseDao getBaseDao() {
		return baseDao;
	}
	
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	
	@Override
	public User save(User user) {
		// TODO Auto-generated method stub
		User u = null;
		baseDao.save(user);
		u = this.findByUid(user.getUid());
		return u;
	}

	@Override
	public User findByUid(int uid) {
		// TODO Auto-generated method stub
		User user=null;
		String sql="from riskManager.model.User where uid='"+uid+"'";
		Session session=baseDao.getNewSession();
		List<User> result=session.createQuery(sql).list();
		if(result.size()>0){
			user=result.get(0);
		}
		return user;
	}

	@Override
	public User update(User user) {
		// TODO Auto-generated method stub
		//User previous=user;
		baseDao.update(user);
		User u=this.findByUid(user.getUid());
		return u;
	}

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		Session session=baseDao.getNewSession();
		String sql="from riskManager.model.User";
		List<User> result=session.createQuery(sql).list();
		return result;
	}

	@Override
	public List<User> findByName(String name) {
		// TODO Auto-generated method stub
		String sql="from riskManager.model.User where name='"+name+"'";
		Session session=baseDao.getNewSession();
		List<User> result=session.createQuery(sql).list();
		return result;
	}

	@Override
	public List<User> findByRole(String role) {
		// TODO Auto-generated method stub
		String sql="from riskManager.model.User where role='"+role+"'";
		Session session=baseDao.getNewSession();
		List<User> result=session.createQuery(sql).list();
		return result;
	}

}
