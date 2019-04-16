package com.neo.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.jx.entity.EmpLog;
import com.jx.entity.MessageResult;
import com.jx.entity.UserInfo;
import com.neo.mapper.EmpLogMapper;
import com.neo.mapper.UserInfoMapper;
import com.neo.service.UserInfoService;

@Service
@Async
public class UserInfoServiceImpl implements UserInfoService{
	@Resource
	private UserInfoMapper  userInfoMapper;
	
	@Resource
	private HttpServletRequest request;
	
	@Resource
	private EmpLogMapper empLogMapper;
	
	@Override
	public MessageResult insertUserInfo(String userId, String name, String password) {
		
		 try{
			 userInfoMapper.insertUserInfo(userId, name, password);
			 EmpLog empLog = new EmpLog();
		 }catch(Exception e){
			 e.printStackTrace();
			 return new MessageResult(-1,"参数错误");
		 }
		 
		 return new  MessageResult(0,"操作成功");
	}
	


	@Override
	public MessageResult selectUInfoAll() {
		List<UserInfo> list;
		try{
			  list=userInfoMapper.selectUInfoAll();
		 }catch(Exception e){
			 e.printStackTrace();
			 return new MessageResult(-1,"参数错误");
		 }
		 
		 return new  MessageResult(0,"操作成功",list);
	}


	@Override
	public MessageResult deleteUInfoById(String userId) {
		try{
			userInfoMapper.deleteUInfoById(userId);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return new  MessageResult(0,"操作成功");
	}



	@Override
	public MessageResult deleteUInfoByIds(String[] ids) {
		try{
		for(String userId:ids){
			userInfoMapper.deleteUInfoById(userId);
			//新增一条日志
			String uid = request.getSession().getAttribute("userId").toString();
			String logContent = uid+"删除了"+userId+"用户";
			EmpLog empLog = new EmpLog(uid,"insert",logContent);
			empLogMapper.insertLog(empLog);
		}
		
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return new  MessageResult(0,"操作成功");
	}



	@Override
	public MessageResult check(String userId, String password) {
		List<UserInfo> list = userInfoMapper.check(userId, password);
		if(list.size()==0){
			return new MessageResult(-11,"密码错误");
		}
		HttpSession session = request.getSession();
		session.setAttribute("userId", userId);
		return new MessageResult(0, "登录成功");
	}



	





}
