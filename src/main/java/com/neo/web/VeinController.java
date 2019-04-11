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

import com.jx.entity.Base64ToByte;
import com.jx.entity.Json;
import com.jx.entity.MessageResult;
import com.jx.entity.MessageResultGenerator;
import com.jx.entity.VeinFeat;
import com.neo.service.UserService;

import jx.vein.javajar.JXVeinJavaSDK_T910;




@Controller
	@RequestMapping("/")
	public class VeinController {
		
		private static Logger logger = LoggerFactory
				.getLogger(ClientController.class);
		
		@Resource	
		protected HttpServletRequest request;
		
		@Autowired
		private UserService userService;
		
		private  JXVeinJavaSDK_T910 jx=new JXVeinJavaSDK_T910();
		private Base64ToByte btb=new Base64ToByte();

		

		@RequestMapping(value="check",method=RequestMethod.POST)
		@ResponseBody
		public MessageResult check(Model model,HttpSession session) throws UnsupportedEncodingException, IOException, Exception{
			String data = "";
			String userId="";
			String groupId="";
			//logger.info(Json.file2Byte("C:/Users/jl/Documents/WeChat Files/jln1886/FileStorage/File/2019-04/feat_sample.bin").toString());
			String veinFeat ="";
			String b="";
			//logger.info(request.getInputStream().toString());
			data = new String(Json.readInputStream(request.getInputStream()), "UTF-8");
			List<Map<String,Object>> list=Json.jsonToList(data);
			for(Map<String,Object> map :list){
				userId=map.get("userId").toString();
				groupId=map.get("groupId").toString();
				veinFeat=map.get("veinfeat").toString();
			}
			int ref;
			logger.info("---1:1验证");
			if(userId==null||veinFeat==null){
				return MessageResultGenerator.genResult1(-1,"参数错误");
			}else{
				try{
					List<VeinFeat> vein=userService.selectVeinByUserId(userId);
					for(VeinFeat s:vein){
					    b=s.getVeinFeat();
					    byte[] a=btb.baseStringToByte(veinFeat);
					    byte[] date=btb.baseStringToByte(b);
					    ref=jx.jxVericateTwoVeinFeature(a,date);	
						if(ref==1){
							return MessageResultGenerator.genResult1(2,"静脉指纹通过");
						}
					}
				}catch(Exception  e){
					e.printStackTrace();
					return MessageResultGenerator.genResult1(-100,"未知错误");
				}
				
					return MessageResultGenerator.genResult1(1,"静脉指纹失败");
			}

	}
		@RequestMapping(value="checkAllToN",method=RequestMethod.POST)
		@ResponseBody
		public MessageResult checkAllToN(Model model,HttpSession session) throws UnsupportedEncodingException, IOException, Exception{
			String data = "";
			String userId="";
			String veinFeat ="";
			String b="";
			data = new String(Json.readInputStream(request.getInputStream()), "UTF-8");
			List<Map<String,Object>> list=Json.jsonToList(data);
			for(Map<String,Object> map :list){
				veinFeat=map.get("veinfeat").toString();
			}
			int ref;
			logger.info("---全局1:N验证");
			if(veinFeat==null){
				return MessageResultGenerator.genResult1(-1,"参数错误");
			}else{
				try{
					List<VeinFeat> vein=userService.selectVein();
					for(VeinFeat s:vein){
					    b=s.getVeinFeat();
					    byte[] a=btb.baseStringToByte(veinFeat);
					    byte[] date=btb.baseStringToByte(b);
					    ref=jx.jxVericateTwoVeinFeature(a,date);	
						if(ref==1){
							return MessageResultGenerator.genResult1(2,"静脉指纹通过");
						}
					}
				}catch(Exception  e){
					e.printStackTrace();
					return MessageResultGenerator.genResult1(-100,"未知错误");
				}
				
					return MessageResultGenerator.genResult1(1,"静脉指纹失败");
			}

	}
		@RequestMapping(value="checkToNByGroupId",method=RequestMethod.POST)
		@ResponseBody
		public MessageResult checkToNByGroupId(Model model,HttpSession session) throws UnsupportedEncodingException, IOException, Exception{
			String data = "";
			String groupId="";
			String veinFeat ="";
			String b="";
			data = new String(Json.readInputStream(request.getInputStream()), "UTF-8");
			List<Map<String,Object>> list=Json.jsonToList(data);
			for(Map<String,Object> map :list){
				groupId=map.get("groupId").toString();
				veinFeat=map.get("veinfeat").toString();
			}
			int ref;
			logger.info("---分组1:N验证");
			if(groupId==null||veinFeat==null){
				return MessageResultGenerator.genResult1(-1,"参数错误");
			}else{
				try{
					userService.selectVeinByGroupId(groupId);
					List<VeinFeat> vein=userService.selectVein();
					for(VeinFeat s:vein){
					    b=s.getVeinFeat();
					    byte[] a=btb.baseStringToByte(veinFeat);
					    byte[] date=btb.baseStringToByte(b);
					    ref=jx.jxVericateTwoVeinFeature(a,date);	
						if(ref==1){
							return MessageResultGenerator.genResult1(2,"静脉指纹通过");
						}
					}
				}catch(Exception  e){
					e.printStackTrace();
					return MessageResultGenerator.genResult1(-100,"未知错误");
				}
				
					return MessageResultGenerator.genResult1(1,"静脉指纹失败");
			}

	}


}
