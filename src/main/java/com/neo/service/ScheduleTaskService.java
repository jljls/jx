package com.neo.service;

import org.springframework.stereotype.Service;

import com.jx.entity.MessageResult;

@Service
public interface ScheduleTaskService {
	/**
	 *	定时任务
	 * @param userId
	 * @param feat_list
	 * @return
	 */
	public void configureTasks();
	
	public void addTaskTime(Integer time);

	public MessageResult selectTime();
}
