package com.neo.service;

import com.jx.entity.MessageResult;
import com.jx.entity.UserInfo;

public interface UserInfoService {

	public MessageResult insertUserInfo(String userId, String name, String password);

	public MessageResult selectUInfoAll();

	public MessageResult deleteUInfoById(String userId);

	public MessageResult deleteUInfoByIds(String[] ids);



}
