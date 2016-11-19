package riskManager.dao;


import java.util.List;

import riskManager.model.Project;

public interface ProjectDao {
	Project save(Project project);
	
	Project findByPid(int pid);
	
	Project update(Project project);
	
	List<Project> getAll(int uid);
}
