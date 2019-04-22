package com.neo.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
		return MessageResult.getInstance(0, "操作成功",null);
	}

	@RequestMapping(value = "selectLog", method = RequestMethod.POST)
	@ResponseBody
	public MessageResult selectLog(String startTime,String endTime,Integer pageCurrent) {
		String endtime="";
		if(endTime!=null){
		   endtime = endTime+" 23:59:59";
		}
		Map<String, Object> map= empLogServic.selectLog(startTime, endtime,pageCurrent);
		return MessageResult.getInstance(0, "操作成功", map);
	}

	@RequestMapping(value = "logNum", method = RequestMethod.GET)
	@ResponseBody
	public MessageResult logNum() {
		return empLogServic.selectLogNum();
	}
	
	@RequestMapping(value = "/deleteLogById", method = RequestMethod.POST)
	@ResponseBody
	public MessageResult deleteLogById(Integer id) {
		HttpSession session = request.getSession();
		String userId = session.getAttribute("userId").toString();
		if(!"admin".equals(userId)){
			return MessageResult.getInstance(-2, "权限不足",null);
		}
		// 删除日志
		MessageResult mr = empLogServic.deleteLogById(id);
		return mr;
	}
	
}
