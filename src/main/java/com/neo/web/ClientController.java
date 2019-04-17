package com.neo.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
	public MessageResult selectEmpByGroupId( String groupId){
		//JSONObject object = JSONObject.fromObject(jsonString);
		//String groupId = (String) object.get("groupId");

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
	public MessageResult selectVeinNumByGroupId( String groupId){
		//JSONObject object = JSONObject.fromObject(jsonString);
		//String groupId = (String) object.get("groupId");
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
	@RequestMapping(value = "deleteAll", method = RequestMethod.POST)
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
	@RequestMapping(value = "deleteById", method = RequestMethod.POST)
	@ResponseBody
	public MessageResult deleteById(String userId){
		//JSONObject object = JSONObject.fromObject(jsonString);
		//String userId = (String) object.get("userId");

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
	@RequestMapping(value = "deleteVeinByGroupId", method = RequestMethod.POST)
	@ResponseBody
	public MessageResult deleteVeinByGroupId( String groupId){
		//JSONObject object = JSONObject.fromObject(jsonString);
		//String groupId = (String) object.get("groupId");

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
	@RequestMapping(value = "deleteVeinByEmpId", method = RequestMethod.POST)
	@ResponseBody
	public MessageResult deleteVeinByEmpId(String userId){
		
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
	public MessageResult insertVeinFeat( String groupId,String userId ,String veinFeats){
		//JSONObject object = JSONObject.fromObject(jsonString);
		//String groupId = (String) object.get("groupId");
		//String userId = (String) object.get("userId");
		String[] veinFeat = veinFeats.split(",");
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
				
				for (String vf : veinFeat) {
					userService.insertEmpVein(userId, vf);
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
	public MessageResult selectUser(String userId,Integer pageCurrent) {
		Map<String, Object> map = userService.selectUser(userId,pageCurrent);
		MessageResult mr = new MessageResult(0,"操作成功",map);
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
		Integer count;
		try{	
				count=userService.checkEmpId(userId);
				System.out.println(count);
				
			
		}catch(Exception e){
			e.printStackTrace();
			return new MessageResult(-100, "未知错误");
		}
		if(count==1){
			//查询某一个人的注册的静态特征
			Integer num=userService.selectEmpVeinEum(userId);
			String s="该用户已经注册,注册指静脉数："+num;
			return new MessageResult(0, "操作成功",s);
			
			
		}
		return	new MessageResult(-9,"检索失败");
		
		
	}
	
	//判断登陆的人
	@RequestMapping(value = "people", method = RequestMethod.GET)
	@ResponseBody
	public MessageResult people() {
		HttpSession session = request.getSession();
		String uid = session.getAttribute("userId").toString(); 
		System.out.println(uid);
		return new MessageResult(0, "操作成功",uid); 
	}
	/**
	 * 查询已经注册用户和已注册手指数
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "selectUAndV", method = RequestMethod.GET)
	@ResponseBody
	public MessageResult selectUAndV(Model model, HttpSession session) {
		logger.info("---查询手指数");
		
			Integer num = userService.selectVeinNum();
			Integer b=userService.selectRegisteEmp();
			String str = "当前已注册用户:"+b+"已注册手指数:"+num;
		return new MessageResult(0, "操作成功", str);

	}
}
