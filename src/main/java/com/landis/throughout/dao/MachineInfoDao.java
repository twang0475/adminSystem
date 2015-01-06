package com.landis.throughout.dao;

import org.apache.ibatis.annotations.Param;

import com.landis.throughout.model.MachineInfo;


public interface MachineInfoDao {

	int save(@Param("machine") MachineInfo machine);
	
	MachineInfo loadMachineInfo(@Param("machineId") long machineId);

}
