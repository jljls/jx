package com.neo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jx.entity.VeinFeat;

public interface LoginMapper {
	/**
	 * 
	 * @param userId
	 *            用户id
	 * @param jxCapFeat
	 *            指静脉特征
	 * @return 查询的结果数
	 */
	Integer login(@Param("userId") String userId, @Param("jxCapFeat") String jxCapFeat);

	List<VeinFeat> selectVeinByUserId(@Param("userId") String userId);
}
