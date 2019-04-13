package com.neo.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jx.entity.EmpLog;
import com.jx.entity.MessageResult;
import com.neo.mapper.EmpLogMapper;
import com.neo.service.UserService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/")
public class ClientController {

	private static Logger logger = LoggerFactory.getLogger(ClientController.class);

	@Resource
	protected HttpServletRequest request;
	@Resource
	private EmpLogMapper empLogMapper;

	@Autowired
	private UserService userService;
	
	/**
	 * 新增用户
	 * @param jsonString
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "insertEmp", method = RequestMethod.POST)
	@ResponseBody
	public MessageResult insertEmp(@RequestBody String jsonString) throws Exception {
		JSONObject object = JSONObject.fromObject(jsonString);
		String groupId = (String) object.get("groupId");
		String userId = (String) object.get("userId");

		logger.info("---添加用户");

		try {
			if (userService.selectEmp() >= 10000) {
				return new MessageResult(-6, "用户数量已满");
			}
			userService.insertEmpBYGroupId(userId, groupId);
			//新增一条日志
			String logContent = "新增一个用户,userId="+userId+",groupId="+groupId;
			EmpLog empLog = new EmpLog("insert",logContent);
			empLogMapper.insertLog(empLog);
		} catch (Exception e) {
			e.printStackTrace();
			return new MessageResult(-100, "未知错误");
		}

		return new MessageResult(0, "操作成功");

	}

	@RequestMapping(value = "selectRegisteEmp", method = RequestMethod.GET)
	@ResponseBody
	public MessageResult selectRegisteEmp() throws UnsupportedEncodingException, IOException, Exception {

		logger.info("---查询已经注册的用户");

		Integer a;
		try {
			a = userService.selectRegisteEmp();

		} catch (Exception e) {
			e.printStackTrace();
			return new MessageResult(-100, "未知错误");
		}

		return new MessageResult(0, "操作成功", a);

	}

	@RequestMapping(value = "selectEmpByGroupId", method = RequestMethod.POST)
	@ResponseBody
	public MessageResult selectEmpByGroupId(@RequestBody String jsonString)
			throws UnsupportedEncodingException, IOException, Exception {
		JSONObject object = JSONObject.fromObject(jsonString);
		String groupId = (String) object.get("groupId");

		logger.info("---查询分组用户数");

		Integer num = null;
		try {
			if (groupId == null) {
				return new MessageResult(-1, "参数错误");
			} else {
				num = userService.selectEmpByGroupId(groupId);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new MessageResult(-100, "未知错误");
		}

		return new MessageResult(0, "操作成功", num);

	}

	@RequestMapping(value = "selectVeinNum", method = RequestMethod.GET)
	@ResponseBody
	public MessageResult selectVeinNum(Model model, HttpSession session) {
		logger.info("---查询手指数");
		Integer num;

		try {

			num = userService.selectVeinNum();

		} catch (Exception e) {
			e.printStackTrace();
			return new MessageResult(-100, "未知错误");
		}

		return new MessageResult(0, "操作成功", num);

	}

	@RequestMapping(value = "selectVeinNumByGroupId", method = RequestMethod.POST)
	@ResponseBody
	public MessageResult selectVeinNumByGroupId(@RequestBody String jsonString)
			throws UnsupportedEncodingException, IOException, Exception {
		JSONObject object = JSONObject.fromObject(jsonString);
		String groupId = (String) object.get("groupId");
		logger.info("---查询分组手指数");

		Integer num = null;
		try {
			if (groupId == null) {
				return new MessageResult(-1, "参数错误");
			} else {
				num = userService.selectVeinNumByGroupId(groupId);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new MessageResult(-100, "未知错误");
		}

		return new MessageResult(0, "操作成功", num);

	}

	@RequestMapping(value = "deleteAll", method = RequestMethod.DELETE)
	@ResponseBody
	public MessageResult deleteAll() {
		logger.info("---删除所用用户及相关静脉");

		try {
			userService.deleteAll();
			//新增一条日志
			String logContent = "删除所有用户的相关静脉";
			EmpLog empLog = new EmpLog("delete",logContent);
			empLogMapper.insertLog(empLog);

		} catch (Exception e) {
			e.printStackTrace();
			return new MessageResult(-100, "未知错误");
		}

		return new MessageResult(0, "操作成功");

	}

	@RequestMapping(value = "deleteById", method = RequestMethod.DELETE)
	@ResponseBody
	public MessageResult deleteById(@RequestBody String jsonString)
			throws UnsupportedEncodingException, IOException, Exception {
		JSONObject object = JSONObject.fromObject(jsonString);
		String userId = (String) object.get("userId");

		logger.info("---删除某一用户及相关静脉");

		try {
			if (userId == null) {
				return new MessageResult(-1, "参数错误");
			} else {
				userService.deleteById(userId);
				//新增一条日志
				String logContent = "删除用户"+userId+"的相关静脉";
				EmpLog empLog = new EmpLog("delete",logContent);
				empLogMapper.insertLog(empLog);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new MessageResult(-100, "未知错误");
		}

		return new MessageResult(0, "操作成功");

	}

	@RequestMapping(value = "deleteVeinByGroupId", method = RequestMethod.DELETE)
	@ResponseBody
	public MessageResult deleteVeinByGroupId(@RequestBody String jsonString)
			throws UnsupportedEncodingException, IOException, Exception {
		JSONObject object = JSONObject.fromObject(jsonString);
		String groupId = (String) object.get("groupId");

		logger.info("---删除用户分组及相关静脉");

		try {
			if (groupId == null) {
				return new MessageResult(-1, "参数错误");
			} else {
				userService.deleteVeinByGroupId(groupId);
				//新增一条日志
				String logContent = "删除"+groupId+"分组下所有用户的相关静脉";
				EmpLog empLog = new EmpLog("delete",logContent);
				empLogMapper.insertLog(empLog);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new MessageResult(-100, "未知错误");
		}

		return new MessageResult(0, "操作成功");

	}

	@RequestMapping(value = "deleteVeinByEmpId", method = RequestMethod.DELETE)
	@ResponseBody
	public MessageResult deleteVeinByEmpId(@RequestBody String jsonString)
			throws UnsupportedEncodingException, IOException, Exception {
		JSONObject object = JSONObject.fromObject(jsonString);
		String userId = (String) object.get("userId");
		logger.info("---删除某用户的所有静脉特征");
		try {
			if (userId == null) {
				return new MessageResult(-1, "参数错误");
			} else {
				userService.deleteVeinByEmpId(userId);
				//新增一条日志
				String logContent = "删除"+userId+"用户的相关静脉";
				EmpLog empLog = new EmpLog("delete",logContent);
				empLogMapper.insertLog(empLog);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new MessageResult(-100, "未知错误");
		}

		return new MessageResult(0, "操作成功");

	}

	@RequestMapping(value = "insertVeinFeat", method = RequestMethod.POST)
	@ResponseBody
	public MessageResult insertVeinFeat(@RequestBody String jsonString)
			throws UnsupportedEncodingException, IOException, Exception {
		JSONObject object = JSONObject.fromObject(jsonString);
		String groupId = (String) object.get("groupId");
		String userId = (String) object.get("userId");
		String[] veinFeat = object.get("veinFeat").toString().split(",");
		logger.info("---存静脉特征");

		try {
			if (userId == null) {
				return new MessageResult(-1, "参数错误");
			}
			if (userService.checkEmpId(userId) == 0) {
				return new MessageResult(-4, "用户不存在");
			}
			if (userService.selectEmpVeinEum(userId) >= 8) {
				return new MessageResult(-5, "用户静脉特征已满");
			} else {
				if (!(groupId == null)) {
					userService.updateGroupByEmpId(userId);
				}
				for (String veinFeats : veinFeat) {
					userService.insertEmpVein(userId, veinFeats);
					//新增一条日志
					String logContent = "新增"+userId+"用户的一条静脉信息";
					EmpLog empLog = new EmpLog("insert",logContent);
					empLogMapper.insertLog(empLog);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new MessageResult(-100, "未知错误");
		}

		return new MessageResult(0, "操作成功");
	}

}
