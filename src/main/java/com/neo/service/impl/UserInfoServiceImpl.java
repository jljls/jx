package com.neo.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.jx.entity.EmpLog;
import com.jx.entity.MessageResult;
import com.jx.entity.Page;
import com.jx.entity.UserInfo;
import com.neo.mapper.EmpLogMapper;
import com.neo.mapper.UserInfoMapper;
import com.neo.service.UserInfoService;

@Transactional
@Service
@Async
public class UserInfoServiceImpl implements UserInfoService {
	@Resource
	private UserInfoMapper userInfoMapper;

	@Resource
	private HttpServletRequest request;

	@Resource
	private EmpLogMapper empLogMapper;

	@Override
	public MessageResult insertUserInfo(String userId, String name, String password) {

		try {
			Integer aa=userInfoMapper.checkUserInfo(userId);
			if(aa==1){
				return MessageResult.getInstance(-11, "管理员已存在",null);
			}
			userInfoMapper.insertUserInfo(userId, name, password);
			HttpSession session = request.getSession();
			String uid = session.getAttribute("userId").toString();
			String logContent = uid + "新增了一名管理员" + userId;
			EmpLog empLog = new EmpLog(uid, "新增", logContent);
			empLogMapper.insertLog(empLog);
		} catch (Exception e) {
			e.printStackTrace();
			return MessageResult.getInstance(-1, "参数错误",null);
		}

		return MessageResult.getInstance(0, "操作成功",null);
	}

	@Override
	public Map<String, Object> selectUInfoAll(String userId, Integer pageCurrent) {
		int pageSize = 25;
		if (pageCurrent == null)
			pageCurrent = 1;
		int startIndex = (pageCurrent - 1) * pageSize;
		int rowCount = userInfoMapper.userInfoRowCount(userId);
		Page pageObject = new Page();
		pageObject.setPageCurrent(pageCurrent);
		pageObject.setPageSize(pageSize);
		pageObject.setRowCount(rowCount);
		pageObject.setStartIndex(startIndex);
		Map<String, Object> map = new HashMap<String, Object>();
		List<UserInfo> list = userInfoMapper.selectUInfoAll(userId, startIndex, pageSize);
		map.put("list", list);
		map.put("pageObject", pageObject);
		return map;
	}

	@Override
	public MessageResult deleteUInfoById(String userId) {
		try {
			if("admin".equals(userId)){
				return MessageResult.getInstance(-2, "超级管理员不允许删除!",null);
			}
			userInfoMapper.deleteUInfoById(userId);
			HttpSession session = request.getSession();
			String uid = session.getAttribute("userId").toString();
			String logContent = uid + "删除了"+"管理员" +userId ;
			EmpLog empLog = new EmpLog(uid, "删除", logContent);
			empLogMapper.insertLog(empLog);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return MessageResult.getInstance(0, "操作成功",null);
	}

	@Override
	public MessageResult deleteUInfoByIds(String[] ids) {
		try {
			for (String userId : ids) {
				if("admin".equals(userId)){
					continue;
				}
				userInfoMapper.deleteUInfoById(userId);
			}
			// 新增一条日志
			String uid = request.getSession().getAttribute("userId").toString();
			String logContent = uid + "删除了" + ids.length + "个管理员";
			EmpLog empLog = new EmpLog(uid, "删除", logContent);
			empLogMapper.insertLog(empLog);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return MessageResult.getInstance(0, "操作成功",null);
	}

	@Override
	public MessageResult check(String userId, String password) {
		List<UserInfo> list = userInfoMapper.check(userId, password);
		if (list.size() == 0) {
			return MessageResult.getInstance(-11, "密码错误",null);
		}
		HttpSession session = request.getSession();
		session.setAttribute("userId", userId);
		return MessageResult.getInstance(0, "登录成功",null);
	}

	@Override
	public MessageResult upDatapws(String pws, String userId) {
		HttpSession session = request.getSession();
		String uid = session.getAttribute("userId").toString();
		String logContent = uid + "新增了一名管理员" + userId;
		EmpLog empLog = new EmpLog(uid, "新增", logContent);
		try {
			int i = userInfoMapper.upDatapws(pws, userId);
			if (i != 0) {
				empLogMapper.insertLog(empLog);
				return MessageResult.getInstance(0, "操作成功!",null);
			} else {
				return MessageResult.getInstance(-1, "参数错误!",null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return MessageResult.getInstance(-100, "未知错误!",null);
		}
	}

	@Override
	public Integer selectUInfoNum() {
		try {
			Integer num = userInfoMapper.selectUInfoNum();
			return num;
		} catch (Exception e) {
			return null;
		}
	}

}
