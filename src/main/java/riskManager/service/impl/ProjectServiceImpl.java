package riskManager.service.impl;

import java.util.ArrayList;
import java.util.List;

import riskManager.dao.AccedeDao;
import riskManager.dao.ProjectDao;
import riskManager.model.Accede;
import riskManager.model.Project;
import riskManager.service.ProjectService;

public class ProjectServiceImpl implements ProjectService {
	private ProjectDao projectDao;
	private AccedeDao accedeDao;
	
	public ProjectDao getProjectDao() {
		return projectDao;
	}

	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}

	public AccedeDao getAccedeDao() {
		return accedeDao;
	}

	public void setAccedeDao(AccedeDao accedeDao) {
		this.accedeDao = accedeDao;
	}

	@Override
	public Project save(Project project) {
		// TODO Auto-generated method stub
		return projectDao.save(project);
	}

	@Override
	public Project findByPid(int pid) {
		// TODO Auto-generated method stub
		return projectDao.findByPid(pid);
	}

	@Override
	public Project update(Project project) {
		// TODO Auto-generated method stub
		return projectDao.update(project);
	}


	@Override
	public void addMember(int[] member,int pid) {
		// TODO Auto-generated method stub
		for(int a:member){
			Accede j = new Accede(a,pid);
			accedeDao.save(j);
		}
	}

	@Override
	public List<Project> getProjectsByUid(int uid) {
		// TODO Auto-generated method stub
		List<Project> pl = new ArrayList<Project>();; 
		List<Accede> al = accedeDao.findByUid(uid);
		if(al.size()>0){
			for(Accede a:al ){
				pl.add(projectDao.findByPid(a.getPid()));
			}
		}
		return pl;
	}

}
