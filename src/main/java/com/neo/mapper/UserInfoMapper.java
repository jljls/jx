package com.neo.mapper;

import java.sql.Array;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.jx.entity.Employee;
import com.jx.entity.UserInfo;
import com.jx.entity.VeinFeat;


public interface UserInfoMapper {
	/**
	 * 新增userInfo
	 * @param userId
	 * @param name
	 * @param password
	 */
	void insertUserInfo
	(@Param("userId") String userId,@Param("name") String name,@Param("password")String password);
	/*
	 * 查询所有userInfo
	 */
	List<UserInfo>  selectUInfoAll();
	/*
	 * 删除userInfo
	 */
	void deleteUInfoById(String userId);
	/**
	 * 批量删除userInfo
	 * @param ids
	 */
	void deleteUInfoByIds(String[]  ids);
	
	List<UserInfo> check(@Param("userId") String userId, @Param("password")String password);
	}