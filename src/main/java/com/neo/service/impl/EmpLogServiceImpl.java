package com.neo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.jx.entity.EmpLog;
import com.neo.mapper.EmpLogMapper;
import com.neo.service.EmpLogService;


@Service
@Async
public class EmpLogServiceImpl implements EmpLogService{
	@Resource
	private EmpLogMapper empLogMapper;
	
	@Override
	public void insertEmpLog(EmpLog empLog) {
		empLogMapper.insertLog(empLog);
	}

	@Override
	public void deleteLog(String startTime, String endTime) {
		empLogMapper.deleteLog(startTime, endTime);
	}

	@Override
	public List<EmpLog> selectLog(String startTime, String endTime) {
		List<EmpLog> list = empLogMapper.selectLog(startTime, endTime);
		return list;
	}
}
