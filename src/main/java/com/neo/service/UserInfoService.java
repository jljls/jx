package com.neo.service;

import com.jx.entity.MessageResult;
import com.jx.entity.UserInfo;

public interface UserInfoService {

	public MessageResult insertUserInfo(String userId, String name, String password);
}
