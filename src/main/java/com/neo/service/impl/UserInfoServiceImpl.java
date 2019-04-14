package com.neo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jx.entity.MessageResult;
import com.jx.entity.UserInfo;
import com.neo.mapper.UserInfoMapper;
import com.neo.service.UserInfoService;

@Service
public class UserInfoServiceImpl implements UserInfoService{
	@Resource
	private UserInfoMapper  userInfoMapper;
	
	
	@Override
	public MessageResult insertUserInfo(String userId, String name, String password) {
		
		 try{
			 userInfoMapper.insertUserInfo(userId, name, password);
		 }catch(Exception e){
			 e.printStackTrace();
			 return new MessageResult(-100,"参数错误");
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
			 return new MessageResult(-100,"参数错误");
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
		}
		
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return new  MessageResult(0,"操作成功");
	}



	





}
