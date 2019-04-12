package com.jx.entity;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class EmpLog {
	@Resource
	private HttpSession session;
	private Integer id;
	private String userId;
	private String type;
	private String logContent;
	private String createBy;
	private Date createTime;
	private String updateBy;
	private Date updateTime;
	public EmpLog() {
		// TODO Auto-generated constructor stub
	}
	public EmpLog(String type,String logContent){
		this.userId = (String) session.getAttribute("userId");
		this.createBy = (String) session.getAttribute("userId");
		this.updateBy = (String) session.getAttribute("userId");
	}
	public Integer getId() {
		return id;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLogContent() {
		return logContent;
	}
	public void setLogContent(String logContent) {
		this.logContent = logContent;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "EmpLog [id=" + id + ", empId=" + userId + ", type=" + type + ", logContent=" + logContent + ", createBy="
				+ createBy + ", createTime=" + createTime + ", updateBy=" + updateBy + ", updateTime=" + updateTime
				+ "]";
	}
	
}
