package com.farkalit.test.utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import org.junit.Test;


public class AESUtilUnitTest {

  @Test
  public void givenString_whenEncrypt_thenSuccess()
      throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException,
      BadPaddingException, InvalidAlgorithmParameterException, NoSuchPaddingException {
    // given
    String input = "baeldung";
    SecretKey key = AESUtil.generateKey(128);
    IvParameterSpec ivParameterSpec = AESUtil.generateIv();
    String algorithm = "AES/CBC/PKCS5Padding";

    // when
    String cipherText = AESUtil.encrypt(algorithm, input, key, ivParameterSpec);
    System.out.println("cipherText:" + cipherText);
    String plainText = AESUtil.decrypt(algorithm, cipherText, key, ivParameterSpec);
    System.out.println("plainText:" + plainText);

    // then
    assertEquals(input, plainText);
    System.out.println();
  }


  // @Test
  // public void givenFile_whenEncrypt_thenSuccess()
  // throws NoSuchAlgorithmException, IOException, IllegalBlockSizeException, InvalidKeyException,
  // BadPaddingException, InvalidAlgorithmParameterException, NoSuchPaddingException {
  // // given
  // SecretKey key = AESUtil.generateKey(128);
  // String algorithm = "AES/CBC/PKCS5Padding";
  // IvParameterSpec ivParameterSpec = AESUtil.generateIv();
  // File inputFile = Paths.get("src/test/resources/baeldung.txt").toFile();
  // File encryptedFile = new File("classpath:baeldung.encrypted");
  // File decryptedFile = new File("document.decrypted");
  //
  // // when
  // AESUtil.encryptFile(algorithm, key, ivParameterSpec, inputFile, encryptedFile);
  // AESUtil.decryptFile(algorithm, key, ivParameterSpec, encryptedFile, decryptedFile);
  //
  // // then
  // assertThat(inputFile).hasSameTextualContentAs(decryptedFile);
  // encryptedFile.delete();
  // decryptedFile.delete();
  // }


  @Test
  public void givenObject_whenEncrypt_thenSuccess() throws NoSuchAlgorithmException,
      IllegalBlockSizeException, InvalidKeyException, InvalidAlgorithmParameterException,
      NoSuchPaddingException, IOException, BadPaddingException, ClassNotFoundException {
    // given
    Student student = new Student("Baeldung", 20);
    SecretKey key = AESUtil.generateKey(128);
    IvParameterSpec ivParameterSpec = AESUtil.generateIv();
    String algorithm = "AES/CBC/PKCS5Padding";

    // when
    SealedObject sealedObject = AESUtil.encryptObject(algorithm, student, key, ivParameterSpec);
    System.out.println("Student sealedObject:" + sealedObject.toString());
    Student object = (Student) AESUtil.decryptObject(algorithm, sealedObject, key, ivParameterSpec);
    System.out.println("Student object:" + object.toString());

    // then
    assertThat(student).isEqualTo(object);
    System.out.println();
  }

  @Test
  public void givenObjectStr_whenEncrypt_thenSuccess() throws NoSuchAlgorithmException,
      IllegalBlockSizeException, InvalidKeyException, InvalidAlgorithmParameterException,
      NoSuchPaddingException, IOException, BadPaddingException, ClassNotFoundException {
    // given
    Student student = new Student("Baeldung", 20);
    SecretKey key = AESUtil.generateKey(128);
    IvParameterSpec ivParameterSpec = AESUtil.generateIv();
    String algorithm = "AES/CBC/PKCS5Padding";

    // when
    String sealedObject =
        AESUtil.encryptObjectStr(algorithm, student.toString(), key, ivParameterSpec);
    System.out.println("Student string:" + sealedObject.toString());
    String object = AESUtil.decryptObjectStr(algorithm, sealedObject, key, ivParameterSpec);
    System.out.println("Student string Object:" + object.toString());

    // then
    assertThat(student.toString()).isEqualTo(object);
    System.out.println();
  }

  @Test
  public void givenPassword_whenEncrypt_thenSuccess() throws InvalidKeySpecException,
      NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException,
      InvalidAlgorithmParameterException, NoSuchPaddingException {
    // given
    String plainText = "www.baeldung.com";
    String password = "baeldung";
    String salt = "12345678";
    IvParameterSpec ivParameterSpec = AESUtil.generateIv();
    SecretKey key = AESUtil.getKeyFromPassword(password, salt);

    // when
    String cipherText = AESUtil.encryptPasswordBased(plainText, key, ivParameterSpec);
    System.out.println("cipherText:" + cipherText);
    String decryptedCipherText = AESUtil.decryptPasswordBased(cipherText, key, ivParameterSpec);
    System.out.println("decryptedCipherText:" + decryptedCipherText);

    // then
    assertEquals(plainText, decryptedCipherText);
    System.out.println();
  }
}
