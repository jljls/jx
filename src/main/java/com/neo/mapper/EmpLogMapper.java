package com.neo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jx.entity.EmpLog;

public interface EmpLogMapper {
	int insertLog(EmpLog empLog);
	void deleteLog(@Param("startTime") String startTime,@Param("endTime")String endTime);
	List<EmpLog> selectLog(@Param("startTime")String startTime,@Param("endTime")String endTime);
}
