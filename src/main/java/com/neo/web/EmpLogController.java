package com.neo.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.couchbase.client.java.document.json.JsonObject;
import com.jx.entity.EmpLog;
import com.jx.entity.MessageResult;
import com.neo.service.EmpLogService;

@Controller
@Async
public class EmpLogController {
	@Resource
	private HttpServletRequest request;
	@Resource
	private EmpLogService empLogServic;

	@RequestMapping(value = "/deleteLog", method = RequestMethod.POST)
	@ResponseBody
	public MessageResult deleteLog(@RequestBody String jsonString) {
		JsonObject object = JsonObject.fromJson(jsonString);
		String startTime = (String) object.get("startTime");
		String endTime = (String) object.get("endTime");
		// 删除日志
		//empLogServic.deleteLog(startTime, endTime);
		// 新增一条操作日志
		String userId = request.getSession().getAttribute("userId").toString();
		EmpLog empLog = new EmpLog(userId, "insert", userId + "删除了" + startTime + "到" + endTime + "的日志记录");
		empLogServic.insertEmpLog(empLog);
		return new MessageResult(0, "操作成功");
	}

	@RequestMapping(value = "selectLog", method = RequestMethod.POST)
	@ResponseBody
	public MessageResult selectLog(String startTime,String endTime,Integer pageCurrent) {
		List<EmpLog> list = empLogServic.selectLog(startTime, endTime,pageCurrent);
		return new MessageResult(0, "操作成功", list);
	}

	@RequestMapping(value = "logNum", method = RequestMethod.GET)
	@ResponseBody
	public MessageResult logNum() {
		return empLogServic.selectLogNum();
	}
	
	@RequestMapping(value = "/deleteLogById", method = RequestMethod.POST)
	@ResponseBody
	public MessageResult deleteLogById(Integer id) {
		// 删除日志
		MessageResult mr = empLogServic.deleteLogById(id);
		// 新增一条操作日志
		String userId = request.getSession().getAttribute("userId").toString();
		EmpLog empLog = new EmpLog(userId, "删除", userId + "删除了一条日志记录");
		empLogServic.insertEmpLog(empLog);
		return mr;
	}
	
}
