package riskManager.service;

import riskManager.model.Project;
import riskManager.model.User;

import java.util.*;

public interface UserService {

	User save(User user);
	
	User findByUid(int uid);
	
	List<User> findByName(String name);
	
	List<User> findByRole(String role);
	
	User update(User user);
	
	List<User> getAll();
	
	List<User> getMember(int pid);
	
}
