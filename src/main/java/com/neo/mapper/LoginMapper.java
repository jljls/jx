package com.neo.mapper;

import org.apache.ibatis.annotations.Param;

public interface LoginMapper {
	/**
	 * 
	 * @param userId 用户id
	 * @param jxCapFeat 指静脉特征
	 * @return 查询的结果数
	 */
	Integer login(@Param("userId")String userId,@Param("jxCapFeat")String jxCapFeat);
}
