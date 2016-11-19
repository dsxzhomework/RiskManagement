package riskManager.dao;

import java.util.List;

import riskManager.model.Accede;

public interface AccedeDao {
	Accede save(Accede j);
	
	List<Accede> findByUid(int uid);
	
	List<Accede> findByPid(int Pid);
}
