package com.neo.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jx.entity.EmpLog;
import com.jx.entity.Json;
import com.jx.entity.MessageResult;
import com.neo.service.EmpLogServic;
import com.neo.service.LoginServic;
import com.neo.service.UserService;

@Controller
public class EmpLogController {
	@Resource
	private EmpLogServic empLogServic;
	
	@RequestMapping(value="/deleteLog", method = RequestMethod.POST)
	@ResponseBody
	public MessageResult deleteLog(String startTime,String endTime) {
		empLogServic.deleteLog(startTime, endTime);
		return new MessageResult(0, "操作成功");
	}
	
	@RequestMapping(value="selectLog",method=RequestMethod.POST)
	@ResponseBody
	public MessageResult selectLog(String startTime,String endTime){
		List<EmpLog> list = empLogServic.selectLog(startTime, endTime);
		return new MessageResult(0, "操作成功", list);
	}
}
