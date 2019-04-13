package com.neo.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jx.entity.EmpLog;
import com.jx.entity.MessageResult;
import com.neo.mapper.RegisteMapper;
import com.neo.web.ClientController;


@Service
public class RegisteServiceImpl implements RegisteService {
	/**
	 * 注册 向数据库插入静脉特征
	 * @param userId
	 * @param feat_list
	 * @return
	 */
	private static Logger logger = LoggerFactory.getLogger(ClientController.class);

	@Resource
	private RegisteMapper registeMapper;
	
	
	@Override
	public MessageResult registeVein
	(String userId, String groupId,String[] veinFeats) {
		
		
		logger.info("---存静脉特征");

		try {
			if (userId == null) {
				return new MessageResult(-1, "参数错误");
			}
			if(!(groupId==null)){
				int count=registeMapper.checkEmpByGroupId(groupId,userId);
				System.out.println(count);
				if(count==0){
					return new MessageResult(-8,"该分组下没有该用户");
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
					//新增一条日志
					String logContent = "新增"+userId+"用户的一条静脉信息";
					//EmpLog empLog = new EmpLog("insert",logContent);
					//empLogMapper.insertLog(empLog);
				
						}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new MessageResult(-100, "未知错误");
		}

		return new MessageResult(0, "操作成功");
	}

}


