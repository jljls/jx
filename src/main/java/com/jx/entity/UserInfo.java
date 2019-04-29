package com.jx.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
/**
 * 管理员实体类
 * @author hongjiangfeng
 *
 */
public class UserInfo implements Serializable {
	
	private String  userId;
	private String  name;
	private String  password;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}