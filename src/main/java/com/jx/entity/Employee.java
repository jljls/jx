package com.jx.entity;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;



public class Employee implements Serializable {
	
	@JsonProperty("userId")
	 private String userId;
	    
	   @JsonProperty("groupId") 
	    private String groupId;
	    
	   private VeinFeat VeinFeat;
	  
	    
	    public Employee(){
	    	super();
	    }
	    
	    
	    public Employee(String userId,String groupId){
			super();
			
			this.userId = userId;
			this.groupId = groupId;
		}
		
		
	   
		public VeinFeat getVeinFeat() {
			return VeinFeat;
		}

		public void setVeinFeat(VeinFeat veinFeat) {
			VeinFeat = veinFeat;
		}

		
		
		public String getUserId() {
			return userId;
		}


		public void setUserId(String userId) {
			this.userId = userId;
		}


		public String getGroupId() {
			return groupId;
		}


		public void setGroupId(String groupId) {
			this.groupId = groupId;
		}


		@Override
		public String toString() {
			return "Employee [id=" +userId + ", groupId=" + groupId + "]";
		}
}