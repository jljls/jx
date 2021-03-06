package com.neo.service;

import java.util.Map;

import com.jx.entity.MessageResult;
import com.jx.entity.UserInfo;

public interface UserInfoService {

	public MessageResult insertUserInfo(String userId, String name, String password);

	public Map<String, Object> selectUInfoAll(String userId,Integer pageCurrent);

	public MessageResult deleteUInfoById(String userId);

	public MessageResult deleteUInfoByIds(String[] ids);

	public MessageResult check(String userId,String password);
	
	public MessageResult upDatapws(String pws,String userId);
	
	public Integer selectUInfoNum();
	
}
