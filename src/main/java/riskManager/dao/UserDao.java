package riskManager.dao;

import riskManager.model.User;

import java.util.*;

public interface UserDao {

	User save(User user);
	User findByUid(int uid);
	List<User> findByName(String name);
	List<User> findByRole(String role);
	User update(User user);
	List<User> getAll();
}
