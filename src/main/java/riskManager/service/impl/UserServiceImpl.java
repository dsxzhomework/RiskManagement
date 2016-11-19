package riskManager.service.impl;

import java.util.*;

import riskManager.model.Accede;
import riskManager.model.Project;
import riskManager.model.User;
import riskManager.dao.AccedeDao;
import riskManager.dao.ProjectDao;
import riskManager.dao.UserDao;
import riskManager.service.UserService;

public class UserServiceImpl implements UserService{

	private UserDao userDao;
	private AccedeDao accedeDao;
	
	public UserDao getUserDao(){
		return userDao;
	}
	
	public void setUserDao(UserDao userDao){
		this.userDao=userDao;
	}

	public AccedeDao getAccedeDao() {
		return accedeDao;
	}

	public void setAccedeDao(AccedeDao accedeDao) {
		this.accedeDao = accedeDao;
	}

	@Override
	public User save(User user) {
		// TODO Auto-generated method stub
		return userDao.save(user);
	}

	@Override
	public User findByUid(int uid) {
		// TODO Auto-generated method stub
		return userDao.findByUid(uid);
	}

	@Override
	public User update(User user) {
		// TODO Auto-generated method stub
		return userDao.update(user);
	}

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return userDao.getAll();
	}

	@Override
	public List<User> findByName(String name) {
		// TODO Auto-generated method stub
		return userDao.findByName(name);
	}

	@Override
	public List<User> findByRole(String role) {
		// TODO Auto-generated method stub
		return userDao.findByRole(role);
	}

	@Override
	public List<User> getMember(int pid) {
		// TODO Auto-generated method stub
		List<User> ul = new ArrayList<User>();
		List<Accede> al = accedeDao.findByPid(pid);
		if(al.size()>0){
			for(Accede a:al){
				ul.add(userDao.findByUid(a.getUid()));
			}
		}
		return ul;
	}

	

}
