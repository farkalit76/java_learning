package com.farkalit.test.utils;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

public class CipherDecryption {

  private final static Logger LOG = Logger.getLogger(CipherDecryption.class);

  private static final String HMAC_SHA256 = "HmacSHA256";
  private static final String AES = "AES";
  private static final String AES_CBC_PKCS5_PADDING = "AES/CBC/PKCS5Padding";


  private static String AES_IV = "16704132440336";
  private static String AES_KEY = "19FDCCB1-3F5C-4B43-94FA-6D3509BC0336";

  // public static final String encryptString =
  // "{\"card_number\":\"4111111111111111\",\"bill_to_email\":\"c4d3uae3@gmail.com\",\"card_expiry_date\":\"12-2021\",\"card_cvn\":\"123\",\"Amount\":\"0.0\",\"card_type\":\"001\"}";

  public static final String commonStr =
      "{\"card_number\":\"5199992312641465\",\"bill_to_email\":\"fausman@mafcarrefour.com\",\"card_expiry_date\":\"08-2025\",\"card_cvn\":\"010\",\"Amount\":\"0\",\"card_type\":\"001\"}";
  // "{\"card_number\":\"4111111111111111\",\"bill_to_email\":\"c4d3uae3@gmail.com\",\"card_expiry_date\":\"12-2021\",\"card_cvn\":\"123\",\"Amount\":\"0.0\",\"card_type\":\"001\"}";
  // "{\"card_number\":\"5199992312641465\",\"bill_to_email\":\"fausman@mafcarrefour.com\",\"card_expiry_date\":\"08-2025\",\"card_cvn\":\"010\",\"Amount\":\"9.0\",\"card_type\":\"001\"}";

  public static final String encodedStr =
      "9B284E1FC3ED23440A49BD56C0685138E1F76B01D3947BD2F99DAD7B08420797E8DEE0EFEBA2659529B1F5F8932990927471ACF204C2C6F29A15AB1FD1D368F5F3A2DE2D5FD7FFFD435B529AB5C37371885085BE03F165A213727D4C315FF8FB81783FA05129325AFF7BF912B18A2E4BAACA2A5060EAE32E0D7D1636842C3257436880077E522480B9E4621309A71DA919730C66E142673D5433B59B634C9058";

  public static void main(String[] args) throws Exception {
    System.out.println("Encrypted:" + encryptAES(commonStr, AES_IV, AES_KEY));
    // System.out.println("Decrypted:" + decryptAES(encodedStr, AES_IV, AES_KEY));
  }



  /**
   * 
   * @param encryptString
   * @param AESIV
   * @param AESKEY
   * @return
   * @throws Exception
   */
  public static String encryptAES(String encryptString, String AESIV, String AESKEY)
      throws Exception {
    byte[] encryptedText = null;
    try {
      String aesIV = signDataWithSecretKey(AESIV, AESIV).substring(0, 16);
      String aesKey = signDataWithSecretKey(AESKEY + AESIV, AESIV).substring(0, 32);

      IvParameterSpec ivspec =
          new IvParameterSpec(aesIV.getBytes(StandardCharsets.UTF_8.toString()));
      SecretKeySpec skeySpec =
          new SecretKeySpec(aesKey.getBytes(StandardCharsets.UTF_8.toString()), "AES");
      Cipher cipher = Cipher.getInstance(AES_CBC_PKCS5_PADDING);
      cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivspec);
      byte[] text = encryptString.getBytes(StandardCharsets.UTF_8.toString());
      encryptedText = cipher.doFinal(text);
      String str = toHexString(encryptedText);
      return str.toUpperCase();
    } catch (Exception e) {
      LOG.error("Error in benefitpay encryption ", e);
    }
    return null;
  }

  /**
   * @param encodedString
   * @param aesIVIn
   * @param aesKeyIn
   * @return
   *
   */
  public static String decryptAES(String encodedString, String aesIVIn, String aesKeyIn) {
    try {
      String aesIV = signDataWithSecretKey(aesIVIn, aesIVIn).substring(0, 16);
      String aesKey = signDataWithSecretKey(aesKeyIn + aesIVIn, aesIVIn).substring(0, 32);
      byte[] bytearray = convertHexStringTOByteArray(encodedString);
      SecretKeySpec secretKeySpec = new SecretKeySpec(aesKey.getBytes(), AES);
      Cipher cipher = Cipher.getInstance(AES_CBC_PKCS5_PADDING);
      IvParameterSpec ivSpec = new IvParameterSpec(aesIV.getBytes());
      cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivSpec);
      String data = Base64.getEncoder().encodeToString(cipher.doFinal(bytearray));
      return new String(DatatypeConverter.parseBase64Binary(data));
    } catch (Exception e) {
      LOG.error("Error in decryption :" + e.getMessage());
    }
    return StringUtils.EMPTY;
  }


  private static String signDataWithSecretKey(String data, String secretKey)
      throws InvalidKeyException, NoSuchAlgorithmException {
    Mac mac = Mac.getInstance(HMAC_SHA256);
    mac.init(new SecretKeySpec(secretKey.getBytes(), HMAC_SHA256));

    return Base64.getEncoder().encodeToString(mac.doFinal(data.getBytes()));
  }

  private static byte[] convertHexStringTOByteArray(String hexString) {
    if (hexString.length() % 2 == 1) {
      throw new IllegalArgumentException("Invalid hexadecimal String supplied.");
    }
    byte[] bytes = new byte[hexString.length() / 2];
    for (int i = 0; i < hexString.length(); i += 2) {
      bytes[i / 2] = hexToByte(hexString.substring(i, i + 2));
    }
    return bytes;
  }

  private static byte hexToByte(String hexString) {
    int firstDigit = toDigit(hexString.charAt(0));
    int secondDigit = toDigit(hexString.charAt(1));
    return (byte) ((firstDigit << 4) + secondDigit);
  }

  private static int toDigit(char hexChar) {
    int digit = Character.digit(hexChar, 16);
    if (digit == -1) {
      throw new IllegalArgumentException("Invalid Hexadecimal Character: " + hexChar);
    }
    return digit;
  }

  public static String toHexString(byte[] array) {
    return DatatypeConverter.printHexBinary(array);
  }

}
