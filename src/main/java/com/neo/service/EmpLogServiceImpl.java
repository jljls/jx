package com.neo.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jx.entity.EmpLog;
import com.neo.mapper.EmpLogMapper;


@Service
public class EmpLogServiceImpl implements EmpLogServic{
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
