package com.neo.service.impl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.jx.entity.EmpLog;
import com.jx.entity.MessageResult;
import com.neo.mapper.EmpLogMapper;
import com.neo.mapper.RegisteMapper;
import com.neo.service.RegisteService;
import com.neo.web.ClientController;

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
				return new MessageResult(-1, "参数错误");
			}
			if (!(groupId == null)) {
				int count = registeMapper.checkEmpByGroupId(groupId, userId);
				System.out.println(count);
				if (count == 0) {
					return new MessageResult(-8, "该分组下没有该用户");
				}
			}
			if (registeMapper.checkEmpId(userId) == 0) {
				return new MessageResult(-4, "用户不存在");
			}
			if (registeMapper.selectEmpVeinEum(userId) >= 8) {
				return new MessageResult(-5, "用户静脉特征已满");
			} else {
				System.out.println(veinFeats);

				for (String veinFeat : veinFeats) {
					registeMapper.insertEmpVein(userId, veinFeat);
					
				}
				// 新增一条日志
				String uid = request.getSession().getAttribute("userId").toString();
				String logContent = "新增" + userId + "用户的三枚指静脉信息";
				EmpLog empLog = new EmpLog(uid,"新增",logContent);
				empLogMapper.insertLog(empLog);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new MessageResult(-100, "未知错误");
		}

		return new MessageResult(0, "操作成功");
	}

}
