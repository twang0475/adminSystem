package com.landis.throughout.model;

import java.util.Date;
import java.util.List;

public class ClientInfo {
	
	private long id;
	private String shortName;
	private String fullName;
	private Date createTime;
	private Date updateTime;
	private List<MachineInfo> machines;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public List<MachineInfo> getMachines() {
		return machines;
	}
	public void setMachines(List<MachineInfo> machines) {
		this.machines = machines;
	}
}
