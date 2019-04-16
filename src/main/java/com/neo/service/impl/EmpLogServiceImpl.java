package com.neo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jx.entity.EmpLog;
import com.jx.entity.MessageResult;
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
	public List<EmpLog> selectLog(String startTime, String endTime,Integer pageCurrent) {
		int pageSize = 20;
		if(StringUtils.isEmpty(pageCurrent))
			pageCurrent = 1;
		int startIndex=(pageCurrent-1)*pageSize;
		List<EmpLog> list = empLogMapper.selectLog(startTime, endTime,startIndex,pageSize);
		return list;
	}
	
	@Override
	public MessageResult selectLogNum() {
		try {
			int num = empLogMapper.selectLogNum();
			return new MessageResult(0, "操作成功", num);
		} catch (Exception e) {
			e.printStackTrace();
			return new MessageResult(-100, "未知错误");
		}
	}
}
