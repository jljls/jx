package com.neo.service;

import java.util.List;

import com.jx.entity.EmpLog;

public interface EmpLogService {
	public void insertEmpLog(EmpLog empLog);
	public void deleteLog(String startTime,String endTime);
	public List<EmpLog> selectLog(String startTime,String endTime);
}
