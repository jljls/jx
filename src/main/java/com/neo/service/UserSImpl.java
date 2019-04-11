package com.neo.service;






import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jx.entity.VeinFeat;
import com.neo.mapper.UserMapper;

@Service
public class UserSImpl  implements UserService{
	@Autowired
	private UserMapper userMapper;

	@Override
	public void insertEmpBYGroupId(String empId,String groupId) {
		userMapper.insertEmpBYGroupId(empId,groupId);
		
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
	public int selectEmpVeinEum(String empId) {
		
		return userMapper.selectEmpVeinEum(empId);
	}

	@Override
	public void insertEmpVein(String empId, String veinFeat) {
		 userMapper.insertEmpVein(empId,veinFeat);
		
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
	public void deleteById(String empId) {
		userMapper.deleteById(empId);
		userMapper.deleteVeinByEmpId(empId);
		
	}

	@Override
	public void deleteVeinById(String empId) {
		userMapper.deleteVeinById(empId);
		
	}

	@Override
	public void deleteVeinByGroupId(String groupId) {
		String[] array=userMapper.selectIdBYGroupId(groupId);
		userMapper.deleteGroupId(groupId);
		userMapper.deleteVeinByGroupId(array);
		
		
	}

	@Override
	public void deleteVeinByEmpId(String empId) {
		userMapper.deleteVeinByEmpId(empId);
		
	}

	@Override
	public byte[] selectVein(String empId, String groupId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateGroupByEmpId(String empId) {
		userMapper.updateGroupByEmpId(empId);
		
	}

	@Override
	public Integer checkEmpId(String empId) {
		
		return userMapper.checkEmpId(empId);
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
