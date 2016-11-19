package riskManager.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

import hibernate3.support.YeekuHibernateDaoSupport;
import riskManager.dao.BaseDao;
import riskManager.dao.ProjectDao;
import riskManager.model.Accede;
import riskManager.model.Project;

public class ProjectDaoImpl extends YeekuHibernateDaoSupport implements ProjectDao {

	@Autowired
	private BaseDao baseDao;
	
	public BaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public Project save(Project project) {
		// TODO Auto-generated method stub
		String hql = "select max(p.pid) from riskManager.model.Project p";
		Session session = baseDao.getNewSession();
		int id=0;
		if(session.createQuery(hql).uniqueResult()!=null)
		 id = (Integer)session.createQuery(hql).uniqueResult();
		project.setPid(++id);
		baseDao.save(project);
		Project p = this.findByPid(project.getPid());
		System.out.println(p.getPid());
		return p;
	}

	@Override
	public Project findByPid(int pid) {
		// TODO Auto-generated method stub
		Project project = null;
		String sql = "from riskManager.model.Project where pid ='"+pid+"'";
		Session session = baseDao.getNewSession();
		List<Project> pl = session.createQuery(sql).list();
		if(pl.size()>0){
			project = pl.get(0);
		}
		return project;
	}

	@Override
	public Project update(Project project) {
		// TODO Auto-generated method stub
		baseDao.update(project);
		Project p = this.findByPid(project.getPid());
		return p;
	}

	@Override
	public List<Project> getAll(int uid) {
		// TODO Auto-generated method stub
				List<Project> pl = new ArrayList<Project>();
				String sql = "from riskManager.model.Accede where uid='"+uid+"'";
				Session session = baseDao.getNewSession();
				List<Accede> jl = session.createQuery(sql).list();
				if(jl.size()>0){
					pl = new ArrayList<Project>();
					for(Accede j: jl){
						pl.add(this.findByPid(Integer.parseInt(j.getAccede().split(",")[1])));
					}
				}
				return pl;
	}

}
