package com.neo.service.impl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jx.entity.EmpLog;
import com.jx.entity.MessageResult;
import com.neo.mapper.EmpLogMapper;
import com.neo.mapper.RegisteMapper;
import com.neo.service.RegisteService;
import com.neo.web.ClientController;

@Transactional
@Service
@Async
public class RegisteServiceImpl implements RegisteService {
	@Resource
	private HttpServletRequest request;
	@Resource
	private EmpLogMapper empLogMapper;
	/**
	 * 注册 向数据库插入静脉特征
	 * 
	 * @param userId
	 * @param feat_list
	 * @return
	 */
	private static Logger logger = LoggerFactory.getLogger(ClientController.class);

	@Resource
	private RegisteMapper registeMapper;

	@Override
	public MessageResult registeVein(String userId, String groupId, String[] veinFeats) {

		logger.info("---存静脉特征");

		try {
			if (userId == null) {
				return MessageResult.getInstance(-1, "参数错误",null);
			}
			if (!(groupId == null)) {
				int count = registeMapper.checkEmpByGroupId(groupId, userId);
				System.out.println(count);
				if (count == 0) {
					return MessageResult.getInstance(-8, "该分组下没有该用户",null);
				}
			}
			if (registeMapper.checkEmpId(userId) == 0) {
				return MessageResult.getInstance(-4, "用户不存在",null);
			}
			if (registeMapper.selectEmpVeinEum(userId) >= 8) {
				return MessageResult.getInstance(-5, "用户静脉特征已满",null);
			} else {
				System.out.println(veinFeats);

				for (String veinFeat : veinFeats) {
					registeMapper.insertEmpVein(userId, veinFeat);
					
				}
				// 新增一条日志
				String uid = request.getSession().getAttribute("userId").toString();
				String logContent = uid+"给用户"+userId+"添加了一枚指静脉";
				EmpLog empLog = new EmpLog(uid,"新增",logContent);
				empLogMapper.insertLog(empLog);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return MessageResult.getInstance(-100, "未知错误",null);
		}

		return MessageResult.getInstance(0, "操作成功",null);
	}

}
