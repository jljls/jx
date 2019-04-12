package com.jx.entity;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;

public class Base64ToByte {

	public String byteToString(byte[] a) throws UnsupportedEncodingException{
		// 编码
		String asB64 = Base64.encodeBase64String(a);
		
		return asB64;
		
	}
	public byte[]  baseStringToByte(String base64String){
		// 解码
		byte[] asBytes = Base64.decodeBase64(base64String);
		return asBytes;
	}
	
}
