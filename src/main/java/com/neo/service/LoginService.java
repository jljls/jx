package com.neo.service;

public interface LoginService {
	/**
	 * 
	 * @param userId 用户id
	 * @param jxCapFeat 指静脉特征
	 * @return 登录消息
	 */
	public String login(String userId,String jxCapFeat);

}
