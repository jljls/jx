package com.neo.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jx.entity.Json;
import com.jx.entity.MessageResult;
import com.jx.entity.MessageResultGenerator;
import com.neo.service.UserService;

import jx.vein.javajar.JXVeinJavaSDK_T910;



@Controller
	@RequestMapping("/")
	public class ClientController {
		
		private static Logger logger = LoggerFactory
				.getLogger(ClientController.class);
		
		@Resource	
		protected HttpServletRequest request;
		
		@Autowired
		private UserService userService;
		
		
		@RequestMapping(value="selectVeinByUserId",method=RequestMethod.GET)
		@ResponseBody
		public MessageResult selectVeinByUserId(String userId) throws Exception{
			System.out.println(userService.selectVeinByUserId(userId));
			 
			return MessageResultGenerator.genResult1(0,"操作成功") ;
		
	
	}
		@RequestMapping(value="insertEmp",method=RequestMethod.POST)
		@ResponseBody
		public MessageResult insertEmp(String userId,String groupId) throws Exception{
			/*String data = "";
			String userId="";
			String groupId="";
			data = new String(Json.readInputStream(request.getInputStream()), "UTF-8");
			List<Map<String,Object>> list=Json.jsonToList(data);
			for(Map<String,Object> map :list){
				userId=map.get("userId").toString();
				groupId=map.get("groupId").toString();
			}*/
			logger.info("---添加员工");
			
			try{
			if(userService.selectEmp()>=10000){
				return MessageResultGenerator.genResult1(-6,"用户数量已满");
			}			
			userService.insertEmpBYGroupId(userId,groupId);		
			}catch(Exception e){
				e.printStackTrace();
				return MessageResultGenerator.genResult1(-100,"未知错误");
			}
			 
			return MessageResultGenerator.genResult1(0,"操作成功") ;
		
	
	}
		@RequestMapping(value="selectRegisteEmp",method=RequestMethod.GET)
		@ResponseBody
		public MessageResult selectRegisteEmp() throws UnsupportedEncodingException, IOException, Exception{
			
			logger.info("---查询已经注册的用户");
			
			Integer a;
			try{
			a=userService.selectRegisteEmp();
			
			}catch(Exception e){
				e.printStackTrace();
				return MessageResultGenerator.genResult1(-100,"未知错误");
			}
			 
			return MessageResultGenerator.genResult2(0,"操作成功",a) ;
		
	}
		
		@RequestMapping(value="selectEmpByGroupId",method=RequestMethod.GET)
		@ResponseBody
		public MessageResult selectEmpByGroupId() throws UnsupportedEncodingException, IOException, Exception{
			String data = "";
			String groupId="";
			data = new String(Json.readInputStream(request.getInputStream()), "UTF-8");
			List<Map<String,Object>> list=Json.jsonToList(data);
			for(Map<String,Object> map :list){
				
				groupId=map.get("groupId").toString();
			}
			logger.info("---查询分组用户数");
			
			Integer num = null;
			try{
				if(groupId==null){
					return MessageResultGenerator.genResult1(-1,"参数错误");
				}else{
					num=userService.selectEmpByGroupId(groupId);
				}
			}catch(Exception e){
				e.printStackTrace();
				return MessageResultGenerator.genResult1(-100,"未知错误");
			}
			 
			return MessageResultGenerator.genResult2(0,"操作成功",num) ;
		
	}
		
		@RequestMapping(value="selectVeinNum",method=RequestMethod.GET)
		@ResponseBody
		public MessageResult selectVeinNum(Model model,HttpSession session){
			logger.info("---查询手指数");
			Integer num;
			
			try{
				
				num=userService.selectVeinNum();
				
			}catch(Exception e){
				e.printStackTrace();
				return MessageResultGenerator.genResult1(-100,"未知错误");
			}
			 
			return MessageResultGenerator.genResult2(0,"操作成功",num) ;
		
	}
		@RequestMapping(value="selectVeinNumByGroupId",method=RequestMethod.GET)
		@ResponseBody
		public MessageResult selectVeinNumByGroupId(Model model,HttpSession session) throws UnsupportedEncodingException, IOException, Exception{
			String data = "";
			String groupId="";
			data = new String(Json.readInputStream(request.getInputStream()), "UTF-8");
			List<Map<String,Object>> list=Json.jsonToList(data);
			for(Map<String,Object> map :list){
				groupId=map.get("groupId").toString();
			}
			logger.info("---查询分组手指数");
			
			Integer num = null;
			try{
				if(groupId==null){
					return MessageResultGenerator.genResult1(-1,"参数错误");
				}else{
					num=userService.selectVeinNumByGroupId(groupId);
				}
			}catch(Exception e){
				e.printStackTrace();
				return MessageResultGenerator.genResult1(-100,"未知错误");
			}
			 
			return MessageResultGenerator.genResult2(0,"操作成功",num) ;
		
	}
		@RequestMapping(value="deleteAll",method=RequestMethod.DELETE)
		@ResponseBody
		public MessageResult deleteAll(){
			logger.info("---删除所用用户及相关静脉");
			
			
			try{
				userService.deleteAll();
				
			}catch(Exception e){
				e.printStackTrace();
				return MessageResultGenerator.genResult1(-100,"未知错误");
			}
			 
			return MessageResultGenerator.genResult1(0,"操作成功") ;
		
	}
		
		@RequestMapping(value="deleteById",method=RequestMethod.DELETE)
		@ResponseBody
		public MessageResult deleteById() throws UnsupportedEncodingException, IOException, Exception{
			String data = "";
			String userId="";
			data = new String(Json.readInputStream(request.getInputStream()), "UTF-8");
			List<Map<String,Object>> list=Json.jsonToList(data);
			for(Map<String,Object> map :list){
				userId=map.get("userId").toString();
			}
			logger.info("---删除某一用户及相关静脉");
			
		try{
				if(userId==null){
					return MessageResultGenerator.genResult1(-1,"参数错误");
				}else{
					userService.deleteById(userId);
				}
				
				
			}catch(Exception e){
				e.printStackTrace();
				return MessageResultGenerator.genResult1(-100,"未知错误");
			}
			 
			return MessageResultGenerator.genResult1(0,"操作成功") ;
		
	}
		

		@RequestMapping(value="deleteVeinByGroupId",method=RequestMethod.DELETE)
		@ResponseBody
		public MessageResult deleteVeinByGroupId(String groupId) throws UnsupportedEncodingException, IOException, Exception{
			/*String data = "";
			String groupId="";
			data = new String(Json.readInputStream(request.getInputStream()), "UTF-8");
			List<Map<String,Object>> list=Json.jsonToList(data);
			for(Map<String,Object> map :list){
				groupId=map.get("groupId").toString();
			}*/

			
			logger.info("---删除用户分组及相关静脉");
	
			try{
				if(groupId==null){
					return MessageResultGenerator.genResult1(-1,"参数错误");
				}else{
					userService.deleteVeinByGroupId(groupId);				
				}
			}catch(Exception e){
				e.printStackTrace();
				return MessageResultGenerator.genResult1(-100,"未知错误");
			}
			 
			return MessageResultGenerator.genResult1(0,"操作成功") ;
		
	}
		@RequestMapping(value="deleteVeinByEmpId",method=RequestMethod.DELETE)
		@ResponseBody
		public MessageResult deleteVeinByEmpId(Model model,HttpSession session,Integer empId) throws UnsupportedEncodingException, IOException, Exception{
			String data = "";
			String userId="";
			data = new String(Json.readInputStream(request.getInputStream()), "UTF-8");
			List<Map<String,Object>> list=Json.jsonToList(data);
			for(Map<String,Object> map :list){
				userId=map.get("userId").toString();
			}
			logger.info("---删除某用户的所有静脉特征");
			try{
				if(empId==null){
					return MessageResultGenerator.genResult1(-1,"参数错误");
				}else{
					userService.deleteVeinByEmpId(userId);
				}
				
				
			}catch(Exception e){
				e.printStackTrace();
				return MessageResultGenerator.genResult1(-100,"未知错误");
			}
			 
			return MessageResultGenerator.genResult1(0,"操作成功") ;
		
	}
		
		
		
		@RequestMapping(value="insertVeinFeat",method=RequestMethod.POST)
		@ResponseBody
		public MessageResult insertVeinFeat(Model model,HttpSession session
				) throws UnsupportedEncodingException, IOException, Exception{
			String data = "";
			String userId="";
			String groupId="";
			String veinFeat1 = null;
			String veinFeat2=null;
			String veinFeat3=null;
			
			data = new String(Json.readInputStream(request.getInputStream()), "UTF-8");
			List<Map<String,Object>> list=Json.jsonToList(data);
			for(Map<String,Object> map :list){
				userId=map.get("userId").toString();
				veinFeat1=map.get("veinfeat1").toString();
				veinFeat2=map.get("veinfeat2").toString();
				veinFeat3=map.get("veinfeat3").toString();
			}
			logger.info("---存静脉特征");
			
			try{
			if(userId==null){
				return MessageResultGenerator.genResult1(-1,"参数错误");	
				}
			 if(userService.checkEmpId(userId)==0){
				return MessageResultGenerator.genResult1(-4,"用户不存在");
			}
			 if(userService.selectEmpVeinEum(userId)>=8){
				return MessageResultGenerator.genResult1(-5,"用户静脉特征已满");
			}
			else{
				if(!(groupId==null)){	
					userService.updateGroupByEmpId(userId);
				}
				
				userService.insertEmpVein(userId, veinFeat1);
				userService.insertEmpVein(userId, veinFeat2);
				userService.insertEmpVein(userId, veinFeat3);
			}
			}catch(Exception e){
				e.printStackTrace();
				return MessageResultGenerator.genResult1(-100,"未知错误");
			}
			 
			return MessageResultGenerator.genResult1(0,"操作成功") ;
	}
		
}

	
