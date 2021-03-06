package com.neo.web;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jx.entity.MessageResult;
import com.neo.service.ScheduleTaskService;
import com.neo.service.UserInfoService;

@Controller
@Async
public class scheduleTaskController {
	@Resource
	private ScheduleTaskService scheduleTaskService;
	
	@RequestMapping(value="useSchedule",method =RequestMethod.POST)
	@ResponseBody
	public MessageResult addTaskTime(String time){
		try{
			scheduleTaskService.addTaskTime(Integer.parseInt(time));
			scheduleTaskService.configureTasks();
		}catch(Exception e){
			e.printStackTrace();
			return MessageResult.getInstance(-100,"未知错误",null);
		}
		
		return MessageResult.getInstance(0,"操作成功",null);
	}
	
	@RequestMapping(value="selectTime",method =RequestMethod.POST)
	@ResponseBody
	public Integer selectTime(){
	
		return scheduleTaskService.selectTime();
	}
}
