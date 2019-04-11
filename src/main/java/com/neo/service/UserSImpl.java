package com.neo.service;






import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jx.entity.VeinFeat;
import com.neo.mapper.UserMapper;

@Service
public class UserSImpl  implements UserService{
	@Resource
	private UserMapper userMapper;

	@Override
	public void insertEmpBYGroupId(String userId,String groupId) {
		userMapper.insertEmpBYGroupId(userId,groupId);
		
	}

	@Override
	public void insertEmp() {
		userMapper.insertEmp();
		
	}

	@Override
	public Integer selectEmp() {
		return userMapper.selectEmp() ;
	}

	@Override
	public int selectEmpVeinEum(String userId) {
		
		return userMapper.selectEmpVeinEum(userId);
	}

	@Override
	public void insertEmpVein(String userId, String veinFeat) {
		 userMapper.insertEmpVein(userId,veinFeat);
		
	}

	@Override
	public Integer selectRegisteEmp() {
		
		return userMapper.selectRegisteEmp();
		
	}

	@Override
	public Integer selectEmpByGroupId(String groupId) {
		
		return userMapper.selectEmpByGroupId(groupId);
	}

	@Override
	public Integer selectVeinNum() {
		
		return userMapper.selectVeinNum();
	}

	@Override
	public Integer selectVeinNumByGroupId(String groupId) {
		
		return userMapper.selectVeinNumByGroupId(groupId);
	}

	@Override
	public void deleteAll() {
		userMapper.deleteAll();
		userMapper.deleteVeinAll();
		
	}

	@Override
	public void deleteById(String userId) {
		userMapper.deleteById(userId);
		userMapper.deleteVeinByEmpId(userId);
		
	}

	@Override
	public void deleteVeinById(String userId) {
		userMapper.deleteVeinById(userId);
		
	}

	@Override
	public void deleteVeinByGroupId(String groupId) {
		String[] array=userMapper.selectIdBYGroupId(groupId);
		userMapper.deleteGroupId(groupId);
		userMapper.deleteVeinByGroupId(array);
		
		
	}

	@Override
	public void deleteVeinByEmpId(String userId) {
		userMapper.deleteVeinByEmpId(userId);
		
	}



	@Override
	public void updateGroupByEmpId(String userId) {
		userMapper.updateGroupByEmpId(userId);
		
	}

	@Override
	public Integer checkEmpId(String userId) {
		
		return userMapper.checkEmpId(userId);
	}

	@Override
	public List<VeinFeat> selectVeinByUserId(String userId) {
		
		return userMapper.selectVeinByUserId(userId);
	}

	@Override
	public List<VeinFeat> selectVein() {
		
		return userMapper.selectVein();
	}

	@Override
	public List<VeinFeat> selectVeinByGroupId(String groupId) {
		String[] array=userMapper.selectIdBYGroupId(groupId);
		return userMapper.selectVeinByGroupId(array);
	}

	


	
	
	
	

}
