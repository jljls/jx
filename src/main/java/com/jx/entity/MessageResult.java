package com.jx.entity;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MessageResult<Employee> {
	
	/**对应状态的消息*/
	private String msg;

	private Employee user;
	
	/**状态码*/
	private Integer code;
	
	/**返回的数字*/
	private Integer num;
	
	/**userId*/
	private String userId;
	
	public MessageResult(){
		
	}
	
	public MessageResult(String msg){
		this.msg = msg;
	}

	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getMsg() {
		return msg;
	}
	
	
	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Employee getUser() {
		return user;
	}

	public void setUser(Employee user) {
		this.user = user;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}


	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	
	

}
