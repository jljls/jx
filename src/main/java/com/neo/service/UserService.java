package com.neo.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.jx.entity.Employee;
import com.jx.entity.VeinFeat;

@Service
public interface UserService {
	
	Integer selectEmp();	
	 void insertEmpBYGroupId( String userId, String groupId);
	 void insertEmp();
	 
	 //查询已经注册的用户
	 Integer selectRegisteEmp();
	 
	 //查询分组用户数
	 Integer selectEmpByGroupId(String groupId);
	 
	 int selectEmpVeinEum(String userId);
	 void insertEmpVein(String userId,String veinFeat);
	//查询手指数
	 Integer selectVeinNum();
	 //查询分组手指数
	Integer selectVeinNumByGroupId(String groupId);
	void deleteAll();
	void deleteById(String userId);
	void deleteVeinById(String userId);
	void deleteVeinByGroupId(String groupId);
	void deleteVeinByEmpId(String userId);
	
	void updateGroupByEmpId(String userId);
	Integer checkEmpId(String userId);
	List<VeinFeat> selectVeinByUserId(String userId);
	List<VeinFeat> selectVein();
	List<VeinFeat> selectVeinByGroupId(String groupId);
}
