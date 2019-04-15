package com.neo.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jx.entity.Base64ToByte;
import com.jx.entity.MessageResult;
import com.jx.entity.VeinFeat;
import com.neo.service.UserService;

import jx.vein.javajar.JXVeinJavaSDK_T910;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/")
@Async
public class VeinController {

	private static Logger logger = LoggerFactory.getLogger(VeinController.class);
	
	@Resource
	private UserService userService;

	private JXVeinJavaSDK_T910 jx = new JXVeinJavaSDK_T910();

	private Base64ToByte btb = new Base64ToByte();
	
	/**
	 * 指静脉1：1验证
	 * @param jsonString
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws Exception
	 */
	@RequestMapping(value = "check", method = RequestMethod.POST)
	@ResponseBody
	public MessageResult check(@RequestBody String jsonString){
		//从参数中获取userId和veinFeat
		JSONObject object = JSONObject.fromObject(jsonString);
		String userId = (String) object.get("userId");
		String veinFeat = (String) object.get("veinFeat");
		System.out.println(userId + " " + veinFeat);
		if(userId==null||veinFeat==null){ 
			return new MessageResult(-1,"参数错误");
		}
		MessageResult mr = userService.selectUserIdandVeinFeat(userId, veinFeat);
		return mr;
	}
	
	/**
	 * 全局进行指静脉1：N验证
	 * @param jsonString
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws Exception
	 */
	@RequestMapping(value = "checkAllToN", method = RequestMethod.POST)
	@ResponseBody
	public MessageResult checkAllToN(@RequestBody String jsonString){
		
		JSONObject object = JSONObject.fromObject(jsonString);
		String veinFeat = (String) object.get("veinFeat");
		String b = "";
		int ref;
		logger.info("---全局1:N验证");
		if (veinFeat == null) {
			return new MessageResult(-1, "参数错误");
		} else {
			try {
				List<VeinFeat> vein = userService.selectVein();
				System.out.println(vein);
				if (vein.isEmpty()) {
					return new MessageResult(-7, "没有用户注册");

				}else{
					for (VeinFeat s : vein) {
						b = s.getVeinFeat();
						
						byte[] a = btb.baseStringToByte(veinFeat);
						byte[] date = btb.baseStringToByte(b);
						ref = jx.jxVericateTwoVeinFeature(a, date);
						if (ref == 1) {
						
							String userId=s.getUserId();
							 Map<String,String> map =new HashMap<String,String>();
							map.put("userId", userId);
							return new MessageResult(2, "静脉指纹通过",map);
						}
					}
	
				}
			} catch (Exception e) {
				e.printStackTrace();
				return new MessageResult(-100, "未知错误");
			}

			return new MessageResult(1, "静脉指纹失败");
		}

	}
	
	/**
	 * 在分组内进行1：N验证
	 * @param jsonString
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws Exception
	 */
	@RequestMapping(value = "checkToNByGroupId", method = RequestMethod.POST)
	@ResponseBody
	public MessageResult checkToNByGroupId(@RequestBody String jsonString){
		JSONObject object = JSONObject.fromObject(jsonString);
		String groupId = (String) object.get("groupId");
		String veinFeat = (String) object.get("veinFeat");

		int ref;
		String b = "";
		logger.info("---分组1:N验证");
		if (groupId == null || veinFeat == null) {
			return new MessageResult(-1, "参数错误");
		} else {
			try {
				List<VeinFeat> vein = userService.selectVeinByGroupId(groupId);
				if (vein == null) {
					return new MessageResult(-10, "该分组下没有用户没有注册");
				}
				for (VeinFeat s : vein) {
					System.out.println(s);
					b = s.getVeinFeat();
					byte[] a = btb.baseStringToByte(veinFeat);
					byte[] date = btb.baseStringToByte(b);
					ref = jx.jxVericateTwoVeinFeature(a, date);
					if (ref == 1) {
					String	userId=s.getUserId();
						Map<String,String> map =new HashMap<String,String>();
						map.put("userId", userId);
			
						return new MessageResult(2, "静脉指纹通过",map);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				return new MessageResult(-100, "未知错误");
			}

			return new MessageResult(1, "静脉指纹失败");
		}

	}

}
