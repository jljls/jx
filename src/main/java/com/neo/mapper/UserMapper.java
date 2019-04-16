package com.neo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jx.entity.Employee;


public interface UserMapper {
	
	Integer selectEmp();
	void insertEmpBYGroupId(@Param("userId")String userId,@Param("groupId")String groupId,@Param("uid")String uid);
	void insertEmp();
	
	Integer selectRegisteEmp();
	
	Integer selectEmpVeinEum(String userId);
	
	Integer selectEmpByGroupId( String groupId);
	void insertEmpVein(@Param("userId")String userId,@Param("veinFeat")String veinFeat);

	void update(Employee user);

	void delete(Long id);


	Integer selectVeinNum();

	Integer selectVeinNumByGroupId(String groupId);

	void deleteAll();
	void deleteVeinAll();
	
	void deleteById(String userId);

	void deleteVeinById(String userId);

	void deleteVeinByGroupId(String[] array);

	String[] selectIdBYGroupId(String groupId);
	
	void updateGroupByEmpId(String groupId);
	void deleteVeinByEmpId(String userId);


	void deleteGroupId(String groupId);

	Integer checkEmpId(String userId);
	List<Employee> selectUser(@Param("startIndex")Integer startIndex,@Param("pageSize")Integer pageSize);
	List<Employee> selectUserByUserId(String userId);

}