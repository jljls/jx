package com.neo.service.impl;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jx.entity.EmpLog;
import com.jx.entity.MessageResult;
import com.jx.entity.Page;
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
	public void deleteLog(String datetime) {
		empLogMapper.deleteLog(datetime);
	}

	@Override
	public Map<String, Object> selectLog(String startTime, String endTime,Integer pageCurrent) {
		String ends = endTime;
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		Map<String, Object> map = new HashMap<String, Object>();
		int pageSize = 25;
		if (pageCurrent == null)
			pageCurrent = 1;
		int startIndex = (pageCurrent - 1) * pageSize;
		int rowCount = empLogMapper.logRowCount(startTime,endTime);
		Page pageObject = new Page();
		pageObject.setPageCurrent(pageCurrent);
		pageObject.setPageSize(pageSize);
		pageObject.setRowCount(rowCount);
		pageObject.setStartIndex(startIndex);
		List<EmpLog> list = empLogMapper.selectLog(startTime, endTime,startIndex,pageSize);
		for (EmpLog empLog : list) {
			String time = sdf.format(empLog.getCreateTime());
			empLog.setTime(time);
		}
		map.put("list", list);
		map.put("pageObject", pageObject);
		return map;
	}
	
	@Override
	public MessageResult selectLogNum() {
		try {
			int num = empLogMapper.selectLogNum();
			return MessageResult.getInstance(0, "操作成功", num);
			
		} catch (Exception e) {
			e.printStackTrace();
			return MessageResult.getInstance(-100, "未知错误",null);
		}
	}

	@Override
	public MessageResult deleteLogById(Integer id) {
		try {
			empLogMapper.deleteLogById(id);
			return MessageResult.getInstance(0, "操作成功",null);
		} catch (Exception e) {
			e.printStackTrace();
			return MessageResult.getInstance(-100, "未知错误",null);
		}
	}
}
