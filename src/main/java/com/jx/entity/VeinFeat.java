package com.jx.entity;

import java.io.Serializable;

import javax.validation.constraints.Pattern;



public class VeinFeat implements Serializable {

	
	    
	    private Integer veinFeatId;
	    
	    private String veinFeat;
	    
	    private String userId;
	  
	    
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