package com.neo.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.neo.mapper.LoginMapper;

@Service
public class LoginServiceImpl implements LoginServic{

	@Resource
	private LoginMapper loginMapper;
	
	@Override
	public String login(String userId,String jxCapFeat) {
		Integer status = loginMapper.login(userId);
		if(status==0){
			return "登录失败";
		}
		return "登录成功";
	}
	
}
