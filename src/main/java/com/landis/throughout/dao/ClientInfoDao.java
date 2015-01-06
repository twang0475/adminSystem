package com.landis.throughout.dao;

import org.apache.ibatis.annotations.Param;
import com.landis.throughout.model.ClientInfo;

public interface ClientInfoDao {
	
	int save(@Param("client") ClientInfo client);
	
	ClientInfo loadClientInfo(@Param("clientId") long clientId);
	
	ClientInfo loadClientInfoByShortName(@Param("shortName") String shortName);
}
