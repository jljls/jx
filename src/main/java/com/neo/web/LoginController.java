package com.neo.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jx.entity.Json;
import com.jx.entity.MessageResult;
import com.neo.service.LoginServic;

@Controller
public class LoginController {
	@Resource
	private LoginServic login;
	
	@RequestMapping("/login")
	public String login1(){
		return "/login";
	}
	
	@RequestMapping("/login1")
	@ResponseBody
	public MessageResult login(String userId,String jxCapFeat){
		String msg = login.login(userId,jxCapFeat);
		return new MessageResult<>(msg);
	}
}
