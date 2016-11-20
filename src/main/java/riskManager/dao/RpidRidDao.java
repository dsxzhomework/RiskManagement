package riskManager.dao;

import java.util.List;

import riskManager.model.RpidRid;

public interface RpidRidDao {
	RpidRid save(RpidRid rpidrid);
	
	boolean delete(RpidRid rpidrid);
	
	List<RpidRid> findByRpid(int rpid);
}
