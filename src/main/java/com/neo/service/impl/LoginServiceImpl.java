package com.neo.service.impl;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neo.mapper.LoginMapper;
import com.neo.service.LoginService;

@Transactional
@Service
@Async
public class LoginServiceImpl implements LoginService{

	@Resource
	private LoginMapper loginMapper;
	
	@Resource
	private HttpSession session;
	
	@Override
	/**
	 * 
	 * @param userId 用户id
	 * @param jxCapFeat 指静脉特征
	 * @return 登录消息
	 */
	public String login(String userId,String jxCapFeat) {
		//根据查询返回的记录数判断是否存在
		Integer status = loginMapper.login(userId,jxCapFeat);
		if(status==0){
			return "登录失败";
		}
		session.setAttribute("userId", userId);
		return "登录成功";
	}
	
}
