package com.farkalit.test.utils;

import java.util.Base64;


public class AuthUtils {

	
	private static final String username="merchant";

	
	private static final String password="Carref0urPassw0rd032019";

	/**
	 * Encoding string ( with username & password ) with string encoder. 
	 * This encoded password is used to call the payment transaction with "Credential" header value.
	 * 
	 */
	public static String encodeCredential() {
		String credentials = username + ":" + password;
		String encodedStr = Base64.getEncoder().encodeToString(credentials.getBytes());
		System.out.println("Encoded string: " + encodedStr);
		// Decoding string with Getting decoder
		/**
		 * String deocdedStr = new String(Base64.getDecoder().decode(encodedStr));
		 */
		return encodedStr;
	}
	
	public static void main(String[] args) {
		encodeCredential();
	}
}
