package com.jx.entity;

import com.fasterxml.jackson.annotation.JsonInclude;


public class MessageResultGenerator {
	public static MessageResult genResult(
			Employee user,Integer code,String message){
		MessageResult result = new MessageResult();
		result.setUser(user);
		result.setCode(code);
		result.setMsg(message);
		
		return result;
	}
	public static MessageResult genResult1(
		Integer code,String message){
		MessageResult result = new MessageResult();
		result.setCode(code);
		result.setMsg(message);	
		return result;
	}
	public static  MessageResult genResult2(
			Integer code,String message,Integer num){
		MessageResult result = new MessageResult();
		result.setCode(code);
		result.setMsg(message);	
		result.setNum(num);
		return result;
	}
	public static MessageResult genResult3(
			Integer code,String message,Integer num,String userId){
		MessageResult result = new MessageResult();
		result.setCode(code);
		result.setMsg(message);	
		result.setNum(num);
		result.setUserId(userId);
		return result;
	}
	
	
	

}
