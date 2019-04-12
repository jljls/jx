package com.neo.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jx.entity.Json;
import com.jx.entity.MessageResult;
import com.neo.service.LoginServic;
import com.neo.service.UserService;

@Controller
public class LoginController {
	@Resource
	private LoginServic login;
	@Resource UserService userService;
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login1(){
		//跳转到登录页面
		return "/loginTo";
	}
	
	@RequestMapping(value="/login1",method = RequestMethod.POST)
	@ResponseBody
	/**
	 * 
	 * @param userId 用户id
	 * @param jxCapFeat 指静脉特征
	 * @return 登录是否成功的消息
	 */
	public MessageResult login(String userId,String jxCapFeat){
		return userService.selectUserIdandVeinFeat(userId,jxCapFeat);
	}
}
