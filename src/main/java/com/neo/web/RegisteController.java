package com.neo.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jx.entity.Json;
import com.jx.entity.MessageResult;
import com.neo.service.LoginServic;
import com.neo.service.UserService;

@Controller
public class RegisteController {
	@Resource
	private UserService userService;
	
	@RequestMapping("/registe")
	public String login1(){
		//跳转到注册页面
		return "registe";
	}
	
	@RequestMapping("/registe1")
	@ResponseBody
	/**
	 * 
	 * @param userId 用户id
	 * @param F 指静脉特征
	 * @return 注册是否成功的消息
	 */
	public MessageResult registe(String userId,String[] feat_list){
		
		return userService.registeVein(userId,feat_list);
	}


}
