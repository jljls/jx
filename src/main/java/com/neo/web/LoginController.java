package com.neo.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jx.entity.Json;
import com.jx.entity.MessageResult;
import com.neo.service.LoginService;
import com.neo.service.UserService;

import net.sf.json.JSONObject;

@Controller
public class LoginController {
	@Resource
	private LoginService login;
	@Resource UserService userService;
	
	@RequestMapping(value="/login")
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
	public MessageResult login(@RequestBody String jsonString,HttpServletRequest request){
		JSONObject object = JSONObject.fromObject(jsonString);
		String userId = (String) object.get("userId");
		String jxCapFeat = (String) object.get("jxCapFeat");
		return userService.selectUserIdandVeinFeat(userId,jxCapFeat);
	}
	
	/**
	 * 跳转到主页面
	 * @return
	 */
	@RequestMapping(value="/index",method = RequestMethod.POST)
	public String index(){
		return "index";
	}
	
	
}
