package com.neo.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjuna.ats.internal.jdbc.drivers.modifiers.list;
import com.jx.entity.Base64ToByte;
import com.jx.entity.EmpLog;
import com.jx.entity.Employee;
import com.jx.entity.MessageResult;
import com.jx.entity.Page;
import com.jx.entity.VeinFeat;
import com.neo.mapper.EmpLogMapper;
import com.neo.mapper.LoginMapper;
import com.neo.mapper.UserMapper;
import com.neo.service.UserService;

import jx.vein.javajar.JXVeinJavaSDK_T910;

@Transactional
@Service
public class UserServiceImpl implements UserService {
	@Resource
	private HttpServletRequest request;
	@Resource
	private UserMapper userMapper;
	@Resource
	private LoginMapper loginMapper;
	@Resource
	private EmpLogMapper empLogMapper;
	private Base64ToByte btb = new Base64ToByte();
	private JXVeinJavaSDK_T910 jx = new JXVeinJavaSDK_T910();

	@Override
	public void insertEmpBYGroupId(String userId, String groupId, String uid) {
		userMapper.insertEmpBYGroupId(userId, groupId, uid);

	}

	@Override
	public void insertEmp() {
		userMapper.insertEmp();

	}

	@Override
	public Integer selectEmp() {
		return userMapper.selectEmp();
	}

	@Override
	public int selectEmpVeinEum(String userId) {

		return userMapper.selectEmpVeinEum(userId);
	}

	@Override
	public void insertEmpVein(String userId, String veinFeat) {
		userMapper.insertEmpVein(userId, veinFeat);

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
		String[] array = userMapper.selectIdBYGroupId(groupId);
		System.out.println(Arrays.toString(array));
		if (!(array.length == 0)) {
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
		String[] array = userMapper.selectIdBYGroupId(groupId);
		return loginMapper.selectVeinByGroupId(array);
	}

	@Override
	public MessageResult selectUserIdandVeinFeat(String userId, String veinFeat) {
		String feat;
		int ref;
		try {
			// 获得userId下的所有指静脉特征
			List<VeinFeat> vein = loginMapper.selectVeinByUserId(userId);
			for (VeinFeat s : vein) {
				if (s.getVeinFeat() == null) {
					return MessageResult.getInstance(-7, "该用户未注册",null);
				} else {
					feat = s.getVeinFeat();
					// 将当前的指静脉特征转为byte
					byte[] a = btb.baseStringToByte(veinFeat);
					// 将用户已有的指静脉特征转为byte
					byte[] data = btb.baseStringToByte(feat);
					// 对比指静脉特征
					ref = jx.jxVericateTwoVeinFeature(a, data);
					if (ref == 1) {
						HttpSession session = request.getSession();
						session.setAttribute("userId", userId);
						System.out.println(session.getAttribute("userId").toString());
						return MessageResult.getInstance(2, "静脉指纹通过",null);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			// 捕获异常
			return MessageResult.getInstance(-100, "未知错误",null);
		}
		return MessageResult.getInstance(1, "静脉指纹失败",null);
	}

	@Override
	public Map<String, Object> selectUser(String userId, Integer pageCurrent) {
		int pageSize = 25;
		if (pageCurrent == null)
			pageCurrent = 1;
		int startIndex = (pageCurrent - 1) * pageSize;
		int rowCount = userMapper.empRowCount(userId);
		Page pageObject = new Page();
		pageObject.setPageCurrent(pageCurrent);
		pageObject.setPageSize(pageSize);
		pageObject.setRowCount(rowCount);
		pageObject.setStartIndex(startIndex);
		List<Employee> list = userMapper.selectUser(userId,startIndex, pageSize);
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("pageObject", pageObject);
		return map;
	}

	@Override
	public MessageResult deleteByIds(String[] ids) {
		try {
			for (String userId : ids) {
				userMapper.deleteById(userId);
			}
			// 新增一条日志
			String uid = request.getSession().getAttribute("userId").toString();
			String logContent = uid + "删除了"+ ids.length +"个用户";
			EmpLog empLog = new EmpLog(uid, "删除", logContent);
			empLogMapper.insertLog(empLog);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return MessageResult.getInstance(0, "操作成功",null);
	}

	@Override
	public MessageResult selectUserByUserId(String userId) {
		List<Employee> list;
		try {
			list = userMapper.selectUserByUserId(userId);
		} catch (Exception e) {
			e.printStackTrace();
			return MessageResult.getInstance(-100, "未知错误",null);
		}
		return MessageResult.getInstance(0, "操作成功", list);
	}

}
