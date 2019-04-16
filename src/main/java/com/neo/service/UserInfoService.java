package com.neo.service;

import com.jx.entity.MessageResult;
import com.jx.entity.UserInfo;

public interface UserInfoService {

	public MessageResult insertUserInfo(String userId, String name, String password);

	public MessageResult selectUInfoAll(Integer pageCurrent);

	public MessageResult deleteUInfoById(String userId);

	public MessageResult deleteUInfoByIds(String[] ids);

	public MessageResult check(String userId,String password);
	
	public MessageResult upDatapws(String pws,String userId);
	
	public MessageResult selectUInfoNum();
	
}
