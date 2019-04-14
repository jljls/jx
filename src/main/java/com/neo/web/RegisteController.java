package com.neo.web;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jx.entity.Json;
import com.jx.entity.MessageResult;
import com.neo.service.LoginService;
import com.neo.service.RegisteService;
import com.neo.service.UserService;

@Controller
@Async
public class RegisteController {
	@Resource
	private RegisteService registeService;
	
	@RequestMapping("/registe")
	public String login1(){
		//跳转到注册页面
		return "registe";
	}
	
	@RequestMapping(value="/registe1")
	@ResponseBody
	/**
	 * 
	 * @param userId 用户id
	 * @param F 指静脉特征
	 * @return 注册是否成功的消息
	 */
	public MessageResult registe(String userId,String groupId,String veinFeats){
		//根据查询返回的记录
			
			return	registeService.registeVein(userId,groupId,veinFeats.split(","));
		
				
	}


}
