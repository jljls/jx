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
import org.springframework.util.StringUtils;
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
	public MessageResult insertEmp(String userId,String groupId) {
		if(groupId==null||userId==null){
			return new MessageResult(-1, "参数错误");
		}
		logger.info("---添加用户");

		try {
			if (userService.selectEmp() >= 10000) {
				return new MessageResult(-6, "用户数量已满");
			}
			if (userService.checkEmpId(userId) == 1) {
				return new MessageResult(-3, "用户已存在");
			}
			String uid = request.getSession().getAttribute("userId").toString();
			userService.insertEmpBYGroupId(userId, groupId,uid);
			//新增一条日志
			String logContent = uid+"新增了一个用户,userId="+userId+",groupId="+groupId;
			EmpLog empLog = new EmpLog(request.getSession().getAttribute("userId").toString(),"insert",logContent);
			empLogMapper.insertLog(empLog);
		} catch (Exception e) {
			e.printStackTrace();
			return new MessageResult(-100, "未知错误");
		}

		return new MessageResult(0, "操作成功");

	}
	
	/**
	 * 查询已注册的用户数
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws Exception
	 */
	@RequestMapping(value = "selectRegisteEmp", method = RequestMethod.GET)
	@ResponseBody
	public MessageResult selectRegisteEmp(){

		logger.info("---查询已经注册的用户");
		//已注册的用户数
		Integer a;
		try {
			a = userService.selectRegisteEmp();

		} catch (Exception e) {
			e.printStackTrace();
			return new MessageResult(-100, "未知错误");
		}

		return new MessageResult(0, "操作成功", a);

	}
	
	/**
	 * 查询某一分组下已经注册的用户数
	 * @param jsonString
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws Exception
	 */
	@RequestMapping(value = "selectEmpByGroupId", method = RequestMethod.POST)
	@ResponseBody
	public MessageResult selectEmpByGroupId(@RequestBody String jsonString){
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
	
	/**
	 * 查询已经注册的手指数
	 * @param model
	 * @param session
	 * @return
	 */
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
	
	/**
	 * 查询某一分组下已经注册的手指数
	 * @param jsonString
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws Exception
	 */
	@RequestMapping(value = "selectVeinNumByGroupId", method = RequestMethod.POST)
	@ResponseBody
	public MessageResult selectVeinNumByGroupId(@RequestBody String jsonString){
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
	
	/**
	 * 删除所有用户及相关的指静脉
	 * @return
	 */
	@RequestMapping(value = "deleteAll", method = RequestMethod.DELETE)
	@ResponseBody
	public MessageResult deleteAll() {
		logger.info("---删除所用用户及相关静脉");

		try {
			userService.deleteAll();
			//新增一条日志
			String logContent = request.getSession().getAttribute("userId")+"删除了所有用户的相关静脉";
			EmpLog empLog = new EmpLog(request.getSession().getAttribute("userId").toString(),"delete",logContent);
			empLogMapper.insertLog(empLog);

		} catch (Exception e) {
			e.printStackTrace();
			return new MessageResult(-100, "未知错误");
		}

		return new MessageResult(0, "操作成功");

	}
	
	/**
	 * 删除某一用户及相关的指静脉
	 * @param jsonString
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws Exception
	 */
	@RequestMapping(value = "deleteById", method = RequestMethod.DELETE)
	@ResponseBody
	public MessageResult deleteById(@RequestBody String jsonString){
		JSONObject object = JSONObject.fromObject(jsonString);
		String userId = (String) object.get("userId");

		logger.info("---删除某一用户及相关静脉");

		try {
			if (userId == null) {
				return new MessageResult(-1, "参数错误");
			} else {
				userService.deleteById(userId);
				//新增一条日志
				String uid = request.getSession().getAttribute("userId").toString();
				String logContent = uid+"删除了用户"+userId+"的相关静脉";
				EmpLog empLog = new EmpLog(request.getSession().getAttribute("userId").toString(),"delete",logContent);
				empLogMapper.insertLog(empLog);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new MessageResult(-100, "未知错误");
		}

		return new MessageResult(0, "操作成功");

	}
	
	/**
	 * 删除某一分组下所有的用户及相关指静脉
	 * @param jsonString
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws Exception
	 */
	@RequestMapping(value = "deleteVeinByGroupId", method = RequestMethod.DELETE)
	@ResponseBody
	public MessageResult deleteVeinByGroupId(@RequestBody String jsonString){
		JSONObject object = JSONObject.fromObject(jsonString);
		String groupId = (String) object.get("groupId");

		logger.info("---删除用户分组及相关静脉");

		try {
			if (groupId == null) {
				return new MessageResult(-1, "参数错误");
			} else {
				userService.deleteVeinByGroupId(groupId);
				//新增一条日志
				String uid = request.getSession().getAttribute("userId").toString();
				String logContent = uid+"删除了"+groupId+"分组下所有用户的相关静脉";
				EmpLog empLog = new EmpLog(request.getSession().getAttribute("userId").toString(),"delete",logContent);
				empLogMapper.insertLog(empLog);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new MessageResult(-100, "未知错误");
		}

		return new MessageResult(0, "操作成功");

	}
	
	/**
	 * 删除某用户所有指静脉特征
	 * @param jsonString
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws Exception
	 */
	@RequestMapping(value = "deleteVeinByEmpId", method = RequestMethod.DELETE)
	@ResponseBody
	public MessageResult deleteVeinByEmpId(@RequestBody String jsonString){
		JSONObject object = JSONObject.fromObject(jsonString);
		String userId = (String) object.get("userId");
		logger.info("---删除某用户的所有静脉特征");
		try {
			if (userId == null) {
				return new MessageResult(-1, "参数错误");
			} else {
				userService.deleteVeinByEmpId(userId);
				//新增一条日志
				String uid = request.getSession().getAttribute("userId").toString();
				String logContent = uid+"删除了"+userId+"用户的相关静脉";
				EmpLog empLog = new EmpLog(request.getSession().getAttribute("userId").toString(),"delete",logContent);
				empLogMapper.insertLog(empLog);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new MessageResult(-100, "未知错误");
		}

		return new MessageResult(0, "操作成功");

	}
	
	/**
	 * 向数据库某用户添加一根手指的静脉特征
	 * @param jsonString
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws Exception
	 */
	@RequestMapping(value = "insertVeinFeat", method = RequestMethod.POST)
	@ResponseBody
	public MessageResult insertVeinFeat(@RequestBody String jsonString){
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
				
				for (String veinFeats : veinFeat) {
					userService.insertEmpVein(userId, veinFeats);
					//新增一条日志
					String uid = request.getSession().getAttribute("userId").toString();
					String logContent = uid+"新增了"+userId+"用户的一条静脉信息";
					EmpLog empLog = new EmpLog(uid,"insert",logContent);
					empLogMapper.insertLog(empLog);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new MessageResult(-100, "未知错误");
		}

		return new MessageResult(0, "操作成功");
	}
	/**
	 * 查询所有用户 返回所有用户
	 *
	 */
	@RequestMapping(value = "selectUser", method = RequestMethod.POST)
	@ResponseBody
	public MessageResult selectUser() {
		MessageResult mr = userService.selectUser();
		System.out.println(mr.toString());
		return mr;
	}
	@RequestMapping(value = "selectUserByuserId", method = RequestMethod.POST)
	@ResponseBody
	public MessageResult selectUserByuserId(String userId) {
		MessageResult mr;
		if (userId==null) {
			mr = userService.selectUserByUserId(userId);
		}else {
			mr = userService.selectUserByUserId(userId);
		}
		return mr;
	}
	
	/**
	 * 删除指定用户
	 *
	 */
	@RequestMapping(value = "deleteUsers", method = RequestMethod.POST)
	@ResponseBody
	public MessageResult deleteUsers(String ids) {
		if(StringUtils.isEmpty(ids)){
			return new MessageResult(-1, "参数异常");
		}
		System.out.println("ids:"+ids);
		String[] userIds = ids.split(",");
		try{
			for(String userId:userIds){
				userService.deleteById(userId);
			}
		}catch(Exception e){
			e.printStackTrace();
			return new MessageResult(-100, "未知错误");
		}
		//新增一条日志
		String uid = request.getSession().getAttribute("userId").toString();
		String logContent = uid+"删除了"+ids+"用户";
		EmpLog empLog = new EmpLog(uid,"delete",logContent);
		empLogMapper.insertLog(empLog);
		return new MessageResult(0, "操作成功"); 
	}
	/**
	 * 检索指定用户
	 *
	 */
	@RequestMapping(value = "checkUser", method = RequestMethod.POST)
	@ResponseBody
	public MessageResult checkUser(String userId) {
		if(userId==null){
			return new MessageResult(-1, "参数异常");
		}
		try{
			
				Integer count=userService.checkEmpId(userId);
				if(count==0){
					new MessageResult(-9, "检索失败"); 
				}
			
		}catch(Exception e){
			e.printStackTrace();
			return new MessageResult(-100, "未知错误");
		}
		
		return new MessageResult(0, "操作成功"); 
	}
	
	
}
