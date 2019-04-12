package com.jx.entity;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class Base64ToByte {

	public String byteToString(byte[] a) throws UnsupportedEncodingException{
		// 编码
		String asB64 = Base64.getEncoder().encodeToString(a);
		
		return asB64;
		
	}
	public byte[]  baseStringToByte(String base64String){
		// 解码
		byte[] asBytes = Base64.getDecoder().decode(base64String);
		return asBytes;
	}
	
}
