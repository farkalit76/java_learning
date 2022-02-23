package com.farkalit.test.security;

import java.util.Base64;

public class TestBase64Encryption {

	public static void main(String[] args) {
		
		System.out.println("\n---Encode decode Byte Array value--------");
     	encodeDecodeByteArr();  
      
        System.out.println("\n Encode decode String value--------");
        encodeDecodeString(); 
        
        System.out.println("\n Encode decode URL value--------");
        encodeDecodeURL();
        
        System.out.println("\n Encode decode MIME value--------");
        encodeDecodeMIME();

        System.out.println("\n Encode decode User&Password value--------");        
        encodeDecodeUsernamePassword();
        
        //System.out.println("\n Encode decode User/Password value--------");
        //encodeDecodeUsernamePassword2();
        encodeJsonHash();
	}

	private static void encodeDecodeByteArr() {
		// Getting encoder  
        Base64.Encoder encoder = Base64.getEncoder();  
        // Creating byte array  
        byte byteArr[] = {'a','b','c'};  
        // encoding byte array  
        byte byteArr2[] = encoder.encode(byteArr);  
        System.out.println("Encoded byte array: "+byteArr2 + "  --ByteArray Length is:"+byteArr2.length);  
        byte byteArr3[] = new byte[4];                // Make sure it has enough size to store copied bytes  
        int x = encoder.encode(byteArr,byteArr3);    // Returns number of bytes written  
        System.out.println("Encoded byte array written to another array: "+byteArr3);  
        System.out.println("Number of bytes written: "+x);
        
        byte[] decode = Base64.getDecoder().decode(byteArr2);
        System.out.println("Decoded Byte Array:"+decode.length);
        System.out.print( " Value: {");
        for (byte b : decode) {
        	System.out.print( b+",");			
		}
        System.out.print( "}");
	}

	private static void encodeDecodeString() {
		Base64.Encoder encoder = Base64.getEncoder(); 
		// Encoding string  
        String str = encoder.encodeToString("JavaTpoint".getBytes());  
        System.out.println("Encoded string: "+str);  
        // Getting decoder  
        Base64.Decoder decoder = Base64.getDecoder();  
        // Decoding string  
        String dStr = new String(decoder.decode(str));  
        System.out.println("Decoded string: "+dStr);
	}

	private static void encodeDecodeURL() {  
        // Getting encoder  
        Base64.Encoder encoder = Base64.getUrlEncoder();  
        // Encoding URL  
        String eStr = encoder.encodeToString("http://www.javatpoint.com/java-tutorial/".getBytes());  
        System.out.println("Encoded URL: "+eStr);  
        // Getting decoder  
        Base64.Decoder decoder = Base64.getUrlDecoder();  
        // Decoding URl  
        String dStr = new String(decoder.decode(eStr));  
        System.out.println("Decoded URL: "+dStr);  
    }  
	
	private static void encodeDecodeMIME() {  
        // Getting MIME encoder  
        Base64.Encoder encoder = Base64.getMimeEncoder();  
        String message = "Hello, \nYou are informed regarding your inconsistency of work";  
        String eStr = encoder.encodeToString(message.getBytes());  
        System.out.println("Encoded MIME message: "+eStr);  
          
        // Getting MIME decoder  
        Base64.Decoder decoder = Base64.getMimeDecoder();  
        // Decoding MIME encoded message  
        String dStr = new String(decoder.decode(eStr));  
        System.out.println("Decoded message: "+dStr);   
    }  
	
	private static void encodeDecodeUsernamePassword() {
		// Encoding string with string encoder  
        String str = Base64.getEncoder().encodeToString("Carrefour:53add4fadbf6984c0933b5263e3c52d".getBytes());
        System.out.println("Encoded string: "+str);  
        // Decoding string  with Getting decoder 
        String dStr = new String(Base64.getDecoder().decode(str));  
        System.out.println("Decoded string: "+dStr);
	}
	
	private static void encodeDecodeUsernamePassword2() {
		// Encoding string with string encoder  
        String str = Base64.getEncoder().encodeToString("anbansal@carrefour.com".getBytes());
        System.out.println("Encoded string: "+str);  
        // Decoding string  with Getting decoder 
        String dStr = new String(Base64.getDecoder().decode(str));  
        System.out.println("Decoded string: "+dStr);
        
        // Password
        String str2 = Base64.getEncoder().encodeToString("Maf@12345".getBytes());
        System.out.println("Encoded string2: "+str2);  
        // Decoding string  with Getting decoder 
        String dStr2 = new String(Base64.getDecoder().decode(str2));  
        System.out.println("Decoded string2: "+dStr2);
	}
	
	
	private static void encodeJsonHash() {
		String jsonHashValue= "ox0mcTnrIvF5Kg68nvOysjbJrSallms6/OB367UGLSQfY2yKN6ShS05rIwbHw3X2p4xEqAnBRxv2NVPzHoHLYfYcJR0uTzPKB9RmlUXzQfLFHhZeqHMIpxN1SnrUq+DiyHjfPpgTsb0xJxKflrrfRywXwsPuGCheCoPr4E/4asA/raFt4Ds+enZKMg6yKBx7Noggj6GhxnX0kYlXGEp71/jBjXGSyiESwTBwwR2JzovxpnJieLlwgSj5Ee9C3XQGI8O31u484U9Ul2m+stT2ADLCg2xL/zkZml6eskafwe8DZB4kWTim7cuQGpHE1SgSy6tUoHRmLUSZbRsvixoP3g==";
		// Encoding string with string encoder  
        String str = Base64.getEncoder().encodeToString(jsonHashValue.getBytes());
        System.out.println("Encoded json hash string: "+str);  
        // Decoding string  with Getting decoder 
        String dStr = new String(Base64.getDecoder().decode(str));  
        System.out.println("Decoded json hash string: "+dStr);
	}
}
