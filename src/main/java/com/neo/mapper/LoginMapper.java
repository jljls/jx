package com.neo.mapper;

import org.apache.ibatis.annotations.Param;

public interface LoginMapper {
	Integer login(@Param("userId")String userId);
}
