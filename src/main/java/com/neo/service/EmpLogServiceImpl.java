package com.neo.service;

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
}
