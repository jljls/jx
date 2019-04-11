package com.jx.entity;

import java.io.Serializable;

import javax.validation.constraints.Pattern;



public class Employee implements Serializable {

	 private String userId;
	    @Pattern(regexp="(^[A-Za-z0-9]{6,16}$)|(^[\\u2E80-\\u9FFF]{2,5}$)"
	    		,message="名字必须是2-5个中文或者6-16位英文数字组合")
	    
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