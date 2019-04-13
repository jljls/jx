package com.neo.service.impl;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.jx.entity.Base64ToByte;
import com.jx.entity.MessageResult;
import com.jx.entity.VeinFeat;
import com.neo.mapper.LoginMapper;
import com.neo.mapper.UserMapper;
import com.neo.service.UserService;

import jx.vein.javajar.JXVeinJavaSDK_T910;

@Service
public class UserServiceImpl  implements UserService{
	@Resource
	private HttpSession session;
	@Resource
	private UserMapper userMapper;
	@Resource
	private LoginMapper loginMapper;
	private Base64ToByte btb = new Base64ToByte();
	private JXVeinJavaSDK_T910 jx = new JXVeinJavaSDK_T910();
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
		System.out.println(Arrays.toString(array));
		if(!(array.length==0)){
			System.out.println(Arrays.toString(array));
			
			userMapper.deleteVeinByGroupId(array);
			
		}
		userMapper.deleteGroupId(groupId);
		
		
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
		
		return loginMapper.selectVeinByUserId(userId);
	}

	@Override
	public List<VeinFeat> selectVein() {
		
		return loginMapper.selectVein();
	}

	@Override
	public List<VeinFeat> selectVeinByGroupId(String groupId) {
		String[] array=userMapper.selectIdBYGroupId(groupId);
		return loginMapper.selectVeinByGroupId(array);
	}

	@Override
	public MessageResult selectUserIdandVeinFeat(String userId, String veinFeat) {
		String feat;
		int ref;
		try{
			//获得userId下的所有指静脉特征
			List<VeinFeat> vein = loginMapper.selectVeinByUserId(userId);
			for(VeinFeat s:vein){ 
				System.out.println(s);
				if(s.getVeinFeat()==null){
					return new MessageResult(-7,"该用户未注册");
				}else {
					feat = s.getVeinFeat();
					//将当前的指静脉特征转为byte
					byte[] a=btb.baseStringToByte(veinFeat);
					//将用户已有的指静脉特征转为byte
					byte[] data=btb.baseStringToByte(feat);
					//对比指静脉特征
					ref= jx.jxVericateTwoVeinFeature(a,data);
					if(ref==1){
						session.setAttribute("userId", userId);
						return new MessageResult(2,"静脉指纹通过");
					}
				}
			}
		} catch(Exception e){
			e.printStackTrace(); 
			//捕获异常
			return new MessageResult(-100,"未知错误");
		}
		return new MessageResult(1, "静脉指纹失败");
	}



}
