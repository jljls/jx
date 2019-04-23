package com.neo.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
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
			return MessageResult.getInstance(-1, "参数错误",null);
		}
		logger.info("---添加用户");

		try {
			if (userService.selectEmp() >= 10000) {
				return MessageResult.getInstance(-6, "用户数量已满",null);
			}
			if (userService.checkEmpId(userId) == 1) {
				return MessageResult.getInstance(-3, "用户已存在",null);
			}
			String uid = request.getSession().getAttribute("userId").toString();
			userService.insertEmpBYGroupId(userId, groupId,uid);
			//新增一条日志
			String logContent = uid+"添加了用户"+userId;
			EmpLog empLog = new EmpLog(request.getSession().getAttribute("userId").toString(),"新增",logContent);
			empLogMapper.insertLog(empLog);
		} catch (Exception e) {
			e.printStackTrace();
			return MessageResult.getInstance(-100, "未知错误",null);
		}

		return MessageResult.getInstance(0, "操作成功",null);

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
		Map <String,Object> map =new HashMap<String, Object>();
		try {
			a =  userService.selectEmp();
			
			map.put("num", a);

		} catch (Exception e) {
			e.printStackTrace();
			return MessageResult.getInstance(-100, "未知错误",null);
			
		}

		return MessageResult.getInstance(0, "操作成功", map);

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
		Map<String,Object> map=new HashMap<String,Object>();
		Integer num = null;
		try {
			if (groupId == null) {
				return MessageResult.getInstance(-1, "参数错误",null);
			} else {
				num = userService.selectEmpByGroupId(groupId);
				map.put("num", num);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return MessageResult.getInstance(-100, "未知错误",null);
		}
		return MessageResult.getInstance(0, "操作成功", map);
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
		Map<String,Object> map =new HashMap<String,Object>();
		try {
			num = userService.selectVeinNum();
			map.put("num", num);
		} catch (Exception e) {
			e.printStackTrace();
			return MessageResult.getInstance(-100, "未知错误",null);
		}

		return MessageResult.getInstance(0, "操作成功", map);

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
		Map<String,Object> map =new HashMap<String,Object>();
		Integer num = null;
		try {
			if (groupId == null) {
				return MessageResult.getInstance(-1, "参数错误",null);
			} else {
				num = userService.selectVeinNumByGroupId(groupId);
				map.put("num", num);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return MessageResult.getInstance(-100, "未知错误",null);
		}

		return MessageResult.getInstance(0, "操作成功", map);

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
			String logContent = request.getSession().getAttribute("userId")+"删除了所有用户";
			EmpLog empLog = new EmpLog(request.getSession().getAttribute("userId").toString(),"删除",logContent);
			empLogMapper.insertLog(empLog);

		} catch (Exception e) {
			e.printStackTrace();
			return MessageResult.getInstance(-100, "未知错误",null);
		}

		return MessageResult.getInstance(0, "操作成功",null);

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
				return MessageResult.getInstance(-1, "参数错误",null);
			} else {
				userService.deleteById(userId);
				//新增一条日志
				String uid = request.getSession().getAttribute("userId").toString();
				String logContent = uid+"删除了用户"+userId;
				EmpLog empLog = new EmpLog(request.getSession().getAttribute("userId").toString(),"删除",logContent);
				empLogMapper.insertLog(empLog);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return MessageResult.getInstance(-100, "未知错误",null);
		}

		return MessageResult.getInstance(0, "操作成功",null);

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
				return MessageResult.getInstance(-1, "参数错误",null);
			} else {
				userService.deleteVeinByGroupId(groupId);
				//新增一条日志
				String uid = request.getSession().getAttribute("userId").toString();
				String logContent = uid+"删除了"+groupId+"分组下所有用户";
				EmpLog empLog = new EmpLog(request.getSession().getAttribute("userId").toString(),"删除",logContent);
				empLogMapper.insertLog(empLog);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return MessageResult.getInstance(-100, "未知错误",null);
		}

		return MessageResult.getInstance(0, "操作成功",null);

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
				return MessageResult.getInstance(-1, "参数错误",null);
			} else {
				int i =userService.checkEmpId(userId);
				if(i==0){
					return MessageResult.getInstance(-4, "用户不存在",null);
				}
				userService.deleteVeinByEmpId(userId);
				//新增一条日志
				String uid = request.getSession().getAttribute("userId").toString();
				String logContent = uid+"删除了用户"+userId+"的静脉特征";
				EmpLog empLog = new EmpLog(request.getSession().getAttribute("userId").toString(),"删除",logContent);
				empLogMapper.insertLog(empLog);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return MessageResult.getInstance(-100, "未知错误",null);
		}

		return MessageResult.getInstance(0, "操作成功",null);

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
				return MessageResult.getInstance(-1, "参数错误",null);
			}
			if (userService.checkEmpId(userId) == 0) {
				return MessageResult.getInstance(-4, "用户不存在",null);
			}
			if (userService.selectEmpVeinEum(userId) >= 8) {
				return MessageResult.getInstance(-5, "用户静脉特征已满",null);
			} else {
				
				for (String vf : veinFeat) {
					userService.insertEmpVein(userId, vf);
					//新增一条日志
					String uid = request.getSession().getAttribute("userId").toString();
					String logContent = uid+"给用户"+userId+"添加了一枚指静脉";
					EmpLog empLog = new EmpLog(uid,"新增",logContent);
					empLogMapper.insertLog(empLog);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return MessageResult.getInstance(-100, "未知错误",null);
		}

		return MessageResult.getInstance(0, "操作成功",null);
	}
	/**
	 * 查询所有用户 返回所有用户
	 *
	 */
	@RequestMapping(value = "selectUser", method = RequestMethod.POST)
	@ResponseBody
	public MessageResult selectUser(String userId,Integer pageCurrent) {
		Map<String, Object> map = userService.selectUser(userId,pageCurrent);
		MessageResult mr = MessageResult.getInstance(0,"操作成功",map);
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
			return MessageResult.getInstance(-1, "参数异常",null);
		}
		System.out.println("ids:"+ids);
		String[] userIds = ids.split(",");
		try{
			for(String userId:userIds){
				userService.deleteById(userId);
			}
		}catch(Exception e){
			e.printStackTrace();
			return MessageResult.getInstance(-100, "未知错误",null);
		}
		//新增一条日志
		String uid = request.getSession().getAttribute("userId").toString();
		String logContent = uid+"删除了"+userIds.length+"个用户";
		EmpLog empLog = new EmpLog(uid,"删除",logContent);
		empLogMapper.insertLog(empLog);
		return MessageResult.getInstance(0, "操作成功",null); 
	}
	/**
	 * 检索指定用户
	 *
	 */
	@RequestMapping(value = "checkUser", method = RequestMethod.POST)
	@ResponseBody
	public MessageResult checkUser(String userId) {
		if(userId==null){
			return MessageResult.getInstance(-1, "参数异常",null);
		}
		Integer count;
		try{	
				count=userService.checkEmpId(userId);
				System.out.println(count);
				
			
		}catch(Exception e){
			e.printStackTrace();
			return MessageResult.getInstance(-100, "未知错误",null);
		}
		if(count==1){
			//查询某一个人的注册的静态特征
			Integer num=userService.selectEmpVeinEum(userId);
			String s="该用户已经注册,注册指静脉数："+num;
			return MessageResult.getInstance(0, "操作成功",s);
			
			
		}
		return	MessageResult.getInstance(-9,"检索失败",null);
		
		
	}
	
	//判断登陆的人
	@RequestMapping(value = "people", method = RequestMethod.GET)
	@ResponseBody
	public MessageResult people() {
		HttpSession session = request.getSession();
		String uid = session.getAttribute("userId").toString(); 
		System.out.println(uid);
		return MessageResult.getInstance(0, "操作成功",uid); 
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
			Integer b=userService.selectEmp();
			String str = "当前已注册用户:"+b+"人    已注册手指数:"+num;
		return MessageResult.getInstance(0, "操作成功", str);

	}
}
