package com.neo.web;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neo.service.ScheduleTaskService;
import com.neo.service.UserInfoService;

@Controller
@Async
public class scheduleTaskController {
	@Resource
	private ScheduleTaskService scheduleTaskService;
	
	@RequestMapping(value="useSchedule",method =RequestMethod.POST)
	@ResponseBody
	public void addTaskTime(String time){
		scheduleTaskService.addTaskTime(Integer.parseInt(time));
		
	}
}
