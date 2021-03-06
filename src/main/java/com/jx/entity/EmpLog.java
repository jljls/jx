package com.jx.entity;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class EmpLog {
	private Integer id;
	private String userId;
	private String type;
	private String logContent;
	private String createBy;
	private Date createTime;
	private String updateBy;
	private Date updateTime;
	private String time;
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public EmpLog() {
		// TODO Auto-generated constructor stub
	}
	public EmpLog(String userId,String type,String logContent){
		this.userId = userId;
		this.type = type;
		this.logContent = logContent;
		this.createBy = userId;
		this.updateBy = userId;
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
	@JsonSerialize(using = JsonDateTypeConvert.class)
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
	@JsonSerialize(using = JsonDateTypeConvert.class)
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
		return "EmpLog [id=" + id + ", userId=" + userId + ", type=" + type + ", logContent=" + logContent
				+ ", createBy=" + createBy + ", createTime=" + createTime + ", updateBy=" + updateBy + ", updateTime="
				+ updateTime + ", time=" + time + "]";
	}
	
}
