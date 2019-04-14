package com.neo.web;

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
import com.neo.service.UserInfoService;
import com.neo.service.UserService;

@Controller
@RequestMapping("/userInfo")
public class UserInfoController {
	@Resource
	private HttpServletRequest request ;
	@Resource
	private UserInfoService userInfoService;
	/*
	 * 添加管理员
	 */
	@RequestMapping(value="/insertUserInfo", method = RequestMethod.POST)
	@ResponseBody
	public MessageResult insertUserInfo( String userId,String name,String password) {
		
		if(userId==null||name==null||password==null){
			return new MessageResult(-1,"参数错误");
		}
		
		return userInfoService.insertUserInfo(userId, name, password);
	}
	/*
	 * 查询所有管理员
	 */
	@RequestMapping(value="/selectUInfoAll", method = RequestMethod.GET)
	@ResponseBody
	public MessageResult selectUInfoAll() {
		
		return userInfoService.selectUInfoAll();
	}
	/*
	 * 按userId删除管理员
	 */
	@RequestMapping(value="/deleteUInfoById", method = RequestMethod.POST)
	@ResponseBody
	public MessageResult deleteUInfoById( String userId) {
		
		if(userId==null){
			return new MessageResult(-1,"参数错误");
		}
		
		return userInfoService.deleteUInfoById(userId);
	}
	
	/*
	 * 按ids批量删除管理员
	 */
	@RequestMapping(value="/deleteUInfoByIds", method = RequestMethod.POST)
	@ResponseBody
	public MessageResult deleteUInfoByIds(String[] ids) {
		if(ids.length==0){
			return new MessageResult(-1,"参数错误");
		}
		return userInfoService.deleteUInfoByIds(ids);
	} 
	
	
	
	
}
