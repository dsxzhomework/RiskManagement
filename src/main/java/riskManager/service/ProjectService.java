package riskManager.service;

import java.util.List;

import riskManager.model.Project;

public interface ProjectService {

	Project save(Project project);
	
	Project findByPid(int pid);
	
	Project update(Project project);
	
	List<Project> getProjectsByUid(int uid);
	
	void addMember(int[] member,int uid);
}
