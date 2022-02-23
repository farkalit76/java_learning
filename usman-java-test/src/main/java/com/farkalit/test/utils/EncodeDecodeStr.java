package com.farkalit.test.utils;

import java.util.Base64;

public class EncodeDecodeStr {

  // id:channel:username:password
  private final static String hybrisString = "101:hybris:hybris-dev:hybris@1234";
  private final static String cityplusString = "102:cityplus:cityplus-dev:cityplus@1234";
  private final static String omscallString = "103:omscall:omscall-dev:omscall@123";

  public static void main(String[] args) {
    String[] orgStr = {hybrisString, cityplusString, omscallString};
    for (String string : orgStr) {
      withPadding(string);
    }
    withWithoutPadding();
  }

  public static void withPadding(String originalString) {

    // Encoding string using simple encode
    String encodedString = Base64.getEncoder().encodeToString(originalString.getBytes());
    System.out.println("Encoded string : " + encodedString);

    // Deconding the encoded string using decoder
    String decodedString = new String(Base64.getDecoder().decode(encodedString.getBytes()));
    System.out.println("Decoded String : " + decodedString);
  }

  public static void withWithoutPadding() {

    String originalString = "Welcome to javaprogramto.com";
    System.out.println("\nOriginal String : " + originalString);

    // Encoding string using simple encode
    String encodedString = Base64.getEncoder().encodeToString(originalString.getBytes());
    System.out.println("Encoded string with padding ...: " + encodedString);

    // encode without padding
    Base64.Encoder withoutPaddingEncoder = Base64.getEncoder().withoutPadding();
    String encodeWithoutPadding = withoutPaddingEncoder.encodeToString(originalString.getBytes());
    System.out.println("Encoded string without padding : " + encodeWithoutPadding);

    // Deconding the encoded string using decoder
    String decodedString = new String(Base64.getDecoder().decode(encodeWithoutPadding.getBytes()));
    System.out.println("Decoded String : " + decodedString);
  }
}
