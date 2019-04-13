package com.jx.entity;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MessageResult {
	
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
	public MessageResult(Integer code,String msg){
		this.code=code;//1
		this.msg="msg";
	}
	/**出现异常以后要调用此方法封装异常信息*/
	public MessageResult(Throwable t){
		this.msg=t.getMessage();
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
