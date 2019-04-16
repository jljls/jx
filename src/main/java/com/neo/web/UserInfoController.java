package com.neo.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jx.entity.MessageResult;
import com.neo.service.UserInfoService;

@Controller
@Async
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
		HttpSession session = request.getSession();
		String uid = session.getAttribute("userId").toString(); 
		if(!"admin".equals(uid)){
			return new  MessageResult(-100,"权限不足");
		}
		if(userId==null||name==null||password==null){
			return new MessageResult(-1,"参数错误");
		}
		
		return userInfoService.insertUserInfo(userId, name, password);
	}
	/*
	 * 查询所有管理员
	 */
	@RequestMapping(value="/selectUInfoAll", method = RequestMethod.POST)
	@ResponseBody
	public MessageResult selectUInfoAll(String userId,Integer pageCurrent) {
		
		return userInfoService.selectUInfoAll(userId,pageCurrent);
	}
	/*
	 * 按userId删除管理员
	 */
	@RequestMapping(value="/deleteUInfoById", method = RequestMethod.POST)
	@ResponseBody
	public MessageResult deleteUInfoById( String userId) {
		HttpSession session = request.getSession();
		String uid = session.getAttribute("userId").toString(); 
		if(!"admin".equals(uid)){
			return new MessageResult(-100,"权限不足");
		}
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
		HttpSession session = request.getSession();
		String uid = session.getAttribute("userId").toString(); 
		if(!"admin".equals(uid)){
			return new MessageResult(-100,"权限不足");
		}
		if(ids.length==0){
			return new MessageResult(-1,"参数错误");
		}
		
		return userInfoService.deleteUInfoByIds(ids);
	} 
	
	/*
	 * 查询当前管理员数
	 */
	@RequestMapping(value="/selectUInfoNum", method = RequestMethod.GET)
	@ResponseBody
	public MessageResult selectUInfoNum() {
		return userInfoService.selectUInfoNum();
	}
	
	//修改管理员密码
	@RequestMapping(value="/upDatapws", method = RequestMethod.POST)
	@ResponseBody
	public MessageResult upDatapws(String pws) {
		String userId = request.getSession().getAttribute("userId").toString();
		if(pws==null){
			return new MessageResult(-1,"参数错误");
		}
		return userInfoService.upDatapws(pws,userId);
	} 
	
	
}
