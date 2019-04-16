package com.neo.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jx.entity.Json;
import com.jx.entity.MessageResult;
import com.neo.service.LoginService;
import com.neo.service.UserInfoService;
import com.neo.service.UserService;

import freemarker.cache.StrongCacheStorage;
import net.sf.json.JSONObject;

@Controller
@Async
public class LoginController {
	@Resource
	private LoginService login;
	@Resource 
	private UserService userService;
	@Resource 
	private UserInfoService userInfoService;
	/**
	 * 跳转到管理员登录页面
	 * @return
	 */
	@RequestMapping(value="/login")
	public String login(){
		//跳转到管理员登录页面
		return "/login";
	}
	/**
	 * 校验管理员登录
	 * @param userId
	 * @param password
	 * @return
	 */
	@RequestMapping(value="checkUserInfo",method=RequestMethod.POST)
	@ResponseBody
	public MessageResult loginIndex(String userId,String password){
		if(userId==null||password==null){
			return new MessageResult(-1,"参数错误");
		}
		MessageResult mr = userInfoService.check(userId, password);
		return mr;
	}
	
	/**
	 * 跳转到用户登录演示
	 * @return
	 */
	@RequestMapping(value="/loginDemo")
	public String login1(){
		//跳转到用户登录演示
		return "/loginTo";
	}
	
	/**
	 * 
	 * @param userId 用户id
	 * @param jxCapFeat 指静脉特征
	 * @return 登录是否成功的消息
	 */
	@RequestMapping(value="/login1",method = RequestMethod.POST)
	@ResponseBody
	public MessageResult login(@RequestBody String jsonString, HttpServletRequest request){
		JSONObject object = JSONObject.fromObject(jsonString);
		String userId = (String) object.get("userId");
		String jxCapFeat = (String) object.get("jxCapFeat");
		if(userId==null||jxCapFeat==null){
			return new MessageResult(-1, "参数错误");
		}
		return userService.selectUserIdandVeinFeat(userId,jxCapFeat);
	}
	
	/**
	 * 跳转到主页面
	 * @return
	 */
	@RequestMapping("/index")
	public String index(){
		return "/home/home";
	}
	
	
}
