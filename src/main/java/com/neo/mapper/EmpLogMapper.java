package com.neo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jx.entity.EmpLog;

public interface EmpLogMapper {
	//添加管理员
	int insertLog(EmpLog empLog);
	//查询日志数
	int selectLogNum();
	//删除日志
		void deleteLog(@Param("datetime")String datetime);
	//根据日期查询日志
	List<EmpLog> selectLog(@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("startIndex")Integer startIndex,@Param("pageSize")Integer pageSize);
}
