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
	
	/**
	 * 根据userId查询去用户的所有指静脉特征
	 * @param userId
	 * @return
	 */
	List<VeinFeat> selectVeinByUserId(@Param("userId") String userId);
	
	/**
	 * 查询出所有的指静脉特征
	 * @return
	 */
	List<VeinFeat> selectVein();
	
	/**
	 * 查询出分组里所有人的指静脉特征
	 * @param array
	 * @return
	 */
	List<VeinFeat> selectVeinByGroupId(String[] array);
}
