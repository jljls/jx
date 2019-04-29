package com.jx.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;



public class VeinFeat implements Serializable {
	    
	    private Integer veinFeatId;
	    
	    private String veinFeat;
	    private String userId;
	    private Date createTime;
		private String createBy;
		private Date updateTime;
		private String updateBy;
	    
	    public Date getCreateTime() {
			return createTime;
		}
	    
	    @JsonSerialize(using = JsonDateTypeConvert.class)
		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}

		public String getCreateBy() {
			return createBy;
		}

		public void setCreateBy(String createBy) {
			this.createBy = createBy;
		}
		
		@JsonSerialize(using = JsonDateTypeConvert.class)
		public Date getUpdateTime() {
			return updateTime;
		}

		public void setUpdateTime(Date updateTime) {
			this.updateTime = updateTime;
		}

		public String getUpdateBy() {
			return updateBy;
		}

		public void setUpdateBy(String updateBy) {
			this.updateBy = updateBy;
		}

		public VeinFeat(){
	    	super();
	    }
	    
	    public VeinFeat(Integer veinFeatId,String veinFeat){
			super();
			this.veinFeatId = veinFeatId;
			this.veinFeat=veinFeat;
			
		}
	   
	   

		public Integer getVeinFeatId() {
			return veinFeatId;
		}

		public void setVeinFeatId(Integer veinFeatId) {
			this.veinFeatId = veinFeatId;
		}

		public String getVeinFeat() {
			return veinFeat;
		}

		public void setVeinFeat(String veinFeat) {
			this.veinFeat = veinFeat;
		}

		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		
}