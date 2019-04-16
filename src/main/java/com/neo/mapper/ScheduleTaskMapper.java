package com.neo.mapper;

import org.apache.ibatis.annotations.Param;


public interface ScheduleTaskMapper {
	
	Integer selectSchedule();
	void addTaskTime(@Param("time")Integer time);
	
}
