package com.neo.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.CurrentDateTimeProvider;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.config.TriggerTask;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.jx.entity.MessageResult;
import com.neo.mapper.EmpLogMapper;
import com.neo.mapper.ScheduleTaskMapper;

import com.neo.service.ScheduleTaskService;


@Service
@Configuration 
//@EnableScheduling
public class ScheduleTaskServiceImpl implements ScheduleTaskService{

	@Resource
	private ScheduleTaskMapper scheduleTaskMapper;
	
	@Resource
	private EmpLogMapper empLogMapper;
	

	//3.添加定时任务
    @Scheduled(cron = "0 0 23 * * ?")
    //或直接指定时间间隔，例如：5秒
//    @Scheduled(fixedDelay = 1000)
    public void configureTasks() {
    	Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, -scheduleTaskMapper.selectSchedule());
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String datetime = sdf.format(c.getTime());
		System.out.println(datetime);
        empLogMapper.deleteLog(datetime);
        System.out.println("定时任务完成");
    }




	@Override
	public void addTaskTime(Integer time) {
		
		scheduleTaskMapper.addTaskTime(time);
		
	}

	@Override
	public Integer selectTime() {
		Integer aa=scheduleTaskMapper.selectSchedule();
		
		return aa;
		
	}
	
	
}
