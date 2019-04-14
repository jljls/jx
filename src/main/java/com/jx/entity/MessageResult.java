package com.jx.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MessageResult {
	
	/**对应状态的消息*/
	private String msg;
	
	/**具体数据*/
	private Object data;
	
	/**状态码*/
	private int code;
	
	public MessageResult(int code,String msg){
		super();
		this.code=code;
		this.msg=msg;
	}
	
	/**有具体业务数据返回时,使用此构造方法*/
	public MessageResult(int code,String msg,Object data){
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	
	/**出现异常以后要调用此方法封装异常信息*/
	public MessageResult(Throwable t){
		this.msg=t.getMessage();
	}
	public MessageResult(String msg){
		this.msg = msg;
	}

	

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}
