package com.neo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jx.entity.VeinFeat;

public interface RegisteMapper {
/*
 * 检查有没有该用户
 */
	Integer checkEmpId(String userId);
/*
 * 用户注册的手指数
 */
	Integer selectEmpVeinEum(String userId);
/*
 *检查该分组下有没有该员工 
 */
	Integer checkEmpByGroupId(@Param("groupId")String groupId,@Param("userId")String userId);
/*
 * 插入指纹
 */
	void insertEmpVein(@Param("userId")String userId,@Param("veinFeat") String veinFeat);


	}
