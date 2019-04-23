package com.neo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ScheduleTaskMapper {
	
	Integer selectSchedule();
	void addTaskTime(@Param("time")Integer time);
	
}
