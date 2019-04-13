package com.neo.service;

import javax.annotation.Resource;

import com.jx.entity.MessageResult;
import com.neo.mapper.LoginMapper;


public interface RegisteService {
	
	
	/**
	 * 注册 向数据库插入静脉特征
	 * @param userId
	 * @param feat_list
	 * @return
	 */


	MessageResult registeVein(String userId, String groupId, String[] feat_list);

}
