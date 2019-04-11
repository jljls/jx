package com.neo.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jx.entity.Base64ToByte;
import com.jx.entity.MessageResult;
import com.jx.entity.MessageResultGenerator;
import com.jx.entity.VeinFeat;
import com.neo.service.UserService;

import jx.vein.javajar.JXVeinJavaSDK_T910;
import net.sf.json.JSONObject;




@Controller
	@RequestMapping("/")
	public class VeinController {
		
		private static Logger logger = LoggerFactory
				.getLogger(ClientController.class);
		
	
		@Resource
		private UserService userService;
		
		private  JXVeinJavaSDK_T910 jx=new JXVeinJavaSDK_T910();
		//private  JXVeinJavaSDK_T910 jx1=new JXVeinJavaSDK_T910();

		private Base64ToByte btb=new Base64ToByte();

		@RequestMapping(value="check",method=RequestMethod.POST)
		@ResponseBody
		public MessageResult check(@RequestBody String  jsonString)
		throws UnsupportedEncodingException, IOException, Exception{
			 JSONObject object =JSONObject.fromObject(jsonString);
			 String userId=(String) object.get("userId");
			 String veinFeat=(String) object.get("veinFeat");
			 System.out.println(userId+" "+veinFeat);
			 List<VeinFeat> vein=userService.selectVein();
			 System.out.println(vein);
			/*int ref;
			String b="";
			
			logger.info("---1:1验证");
			if(userId==null||veinFeat==null){
				return MessageResultGenerator.genResult1(-1,"参数错误");
			}else{
				try{
					List<VeinFeat> vein=userService.selectVeinByUserId(userId);
					for(VeinFeat s:vein){
					    if(s.getVeinFeat()==null){
					    return MessageResultGenerator.genResult1(-7,"该用户未注册");
					    }else{
					    b=s.getVeinFeat();
					    byte[] a=btb.baseStringToByte(veinFeat);
					    byte[] data=btb.baseStringToByte(b);
					    ref=jx.jxVericateTwoVeinFeature(a,data);	
						if(ref==1){
							return MessageResultGenerator.genResult1(2,"静脉指纹通过");
						}}
					}
				}catch(Exception  e){
					e.printStackTrace();
					return MessageResultGenerator.genResult1(-100,"未知错误");
				}
				
*/					return MessageResultGenerator.genResult1(1,"静脉指纹失败");
		//	}

	}
		@RequestMapping(value="checkAllToN",method=RequestMethod.POST)
		@ResponseBody
		public MessageResult checkAllToN(@RequestBody String jsonString) 
				throws UnsupportedEncodingException, IOException, Exception{
			JSONObject object =JSONObject.fromObject(jsonString);
			 String veinFeat=(String) object.get("veinFeat");
			String b="";
			int ref;
			logger.info("---全局1:N验证");
			if(veinFeat==null){
				return MessageResultGenerator.genResult1(-1,"参数错误");
			}else{
				try{
					List<VeinFeat> vein=userService.selectVein();
					if(vein==null){
						return MessageResultGenerator.genResult1(-7,"该用户没有注册");

					}
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
		public MessageResult checkToNByGroupId(
				@RequestBody String jsonString) throws UnsupportedEncodingException, IOException, Exception{
			JSONObject object =JSONObject.fromObject(jsonString);
			 String groupId=(String) object.get("groupId");
			 String veinFeat=(String) object.get("veinFeat");

			int ref;
			String b="";
			logger.info("---分组1:N验证");
			if(groupId==null||veinFeat==null){
				return MessageResultGenerator.genResult1(-1,"参数错误");
			}else{
				try{
					List<VeinFeat> vein=userService.selectVeinByGroupId(groupId);
					if(vein==null){
				    	return MessageResultGenerator.genResult1(-7,"该用户没有注册");
					}
					for(VeinFeat s:vein){
					    System.out.println(s);
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
