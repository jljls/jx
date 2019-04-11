package com.jx.entity;

import com.fasterxml.jackson.annotation.JsonInclude;


public class MessageResultGenerator {
	public static <Employee> MessageResult<Employee> genResult(
			Employee user,Integer code,String message){
		MessageResult<Employee> result = new MessageResult<Employee>();
		result.setUser(user);
		result.setCode(code);
		result.setMsg(message);
		
		return result;
	}
	public static <Employee> MessageResult<Employee> genResult1(
		Integer code,String message){
		MessageResult<Employee> result = new MessageResult<Employee>();
		result.setCode(code);
		result.setMsg(message);	
		return result;
	}
	public static <Employee> MessageResult<Employee> genResult2(
			Integer code,String message,Integer num){
		MessageResult<Employee> result = new MessageResult<Employee>();
		result.setCode(code);
		result.setMsg(message);	
		result.setNum(num);
		return result;
	}
	public static <Employee> MessageResult<Employee> genResult3(
			Integer code,String message,Integer num,String userId){
		MessageResult<Employee> result = new MessageResult<Employee>();
		result.setCode(code);
		result.setMsg(message);	
		result.setNum(num);
		result.setUserId(userId);
		return result;
	}
	
	
	

}
