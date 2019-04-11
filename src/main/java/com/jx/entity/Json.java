
package com.jx.entity;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

public class Json {
	 
	public static byte[] readInputStream(InputStream inStream) throws Exception {
		          ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
		          byte[] buffer = new byte[1024];
		          boolean var3 = false;
		  
		          int len;
		         while((len = inStream.read(buffer)) != -1) {
		            outSteam.write(buffer, 0, len);
		        }
		
		        outSteam.close();
		        inStream.close();
		        return outSteam.toByteArray();
		     }
	public static List<Map<String,Object>> jsonToList(String data){
		Gson gson = new Gson();
		JsonParser parser = new JsonParser();
		//json 转对象
		JsonObject object=(JsonObject)parser.parse(data);
		//根据发过来的数据格式 需要再转一次json 看自己的数据格式，如果只嵌套一层是不用这一步的，如果嵌套多层 还需要多次转json
		JsonObject object1 = (JsonObject) parser.parse(object.get("json").toString());
		//获取json 对象的对象 里面的list集合
		List<Map<String, Object>> list = gson.fromJson(
				object1.get("list").toString(),
				new TypeToken<List<Map<String, Object>>>() {}.getType());
		return list;
	}
	
	public static byte[] file2Byte(String filePath){
	    ByteArrayOutputStream bos=null;
	    BufferedInputStream in=null;
	    try{
	        File file=new File(filePath);
	        if(!file.exists()){
	            throw new FileNotFoundException("file not exists");
	        }
	        bos=new ByteArrayOutputStream((int)file.length());
	        in=new BufferedInputStream(new FileInputStream(file));
	        int buf_size=1344;
	        byte[] buffer=new byte[buf_size];
	        int len=0;
	        while(-1 != (len=in.read(buffer,0,buf_size))){
	            bos.write(buffer,0,len);
	        }
	        return bos.toByteArray();
	    }
	    catch(Exception e){
	        System.out.println(e.getMessage());
	        e.printStackTrace();
	        return null;
	    }
	    finally{
	        try{
	            if(in!=null){
	                in.close();
	            }
	            if(bos!=null){
	                bos.close();
	            }
	        }
	        catch(Exception e){
	            System.out.println(e.getMessage());
	            e.printStackTrace();
	        }
	    }
	}
	public void base64ToByte(){
	/*BASE64Encoder encode = new BASE64Encoder(); 
	//将byte[]转换为base64
	String base64 = encode.encode(bytes); 
	 

	//新建一个BASE64Decoder 
	BASE64Decoder decode = new BASE64Decoder(); 
	//将base64转换为byte[]
	return decode.decodeBuffer(base64); */
		
	
	}
	
	
}
