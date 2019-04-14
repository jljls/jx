﻿package com.neo.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.couchbase.client.java.document.json.JsonObject;
import com.jx.entity.EmpLog;
import com.jx.entity.Json;
import com.jx.entity.MessageResult;
import com.neo.service.EmpLogService;
import com.neo.service.LoginService;
import com.neo.service.UserService;

@Controller
public class UserInfoController {
	@Resource
	private HttpServletRequest request ;
	@Resource
	private EmpLogService empLogServic;
	
	@RequestMapping(value="/deleteLog", method = RequestMethod.POST)
	@ResponseBody
	public MessageResult deleteLog(@RequestBody String jsonString) {
		JsonObject object = JsonObject.fromJson(jsonString);
		String startTime = (String) object.get("startTime");
		String endTime = (String) object.get("endTime");
		//删除日志
		empLogServic.deleteLog(startTime, endTime);
		//新增一条操作日志
		String userId = request.getSession().getAttribute("userId").toString();
		EmpLog empLog = new EmpLog(userId,"insert", userId+"删除了"+startTime+"到"+endTime+"的日志记录");
		empLogServic.insertEmpLog(empLog);
		return new MessageResult(0, "操作成功");
	}
	
	
}
