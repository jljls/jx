package com.neo.service;

import java.util.List;

import com.jx.entity.EmpLog;
import com.jx.entity.MessageResult;

public interface EmpLogService {
	public void insertEmpLog(EmpLog empLog);
	public MessageResult selectLogNum();
	public void deleteLog(String datetime);
	public List<EmpLog> selectLog(String startTime,String endTime,Integer pageCurrent);
}
