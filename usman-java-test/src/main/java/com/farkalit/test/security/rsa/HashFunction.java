package com.farkalit.test.security.rsa;

import java.security.MessageDigest;

import javax.xml.bind.DatatypeConverter;

public class HashFunction {
	 
	//MessageDigest Algorithm - MD2, MD5, SHA-1, SHA-256, SHA-512
	
	public static void main(String[] args) {
		String someText = "This is usman test";
		System.out.println("Hash:"+ getHash(someText, "SHA-512"));
	}

	
	public static String getHash(String inputText, String alorithm) {
		String hashValue = "";
		
		try {
			MessageDigest md = MessageDigest.getInstance(alorithm);
			md.update(inputText.getBytes());
			byte[] mdBytes= md.digest();
			System.out.println(DatatypeConverter.printString(mdBytes.toString()));
			System.out.println(new String(mdBytes));
			hashValue = DatatypeConverter.printHexBinary(mdBytes);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return hashValue;
	}
}
