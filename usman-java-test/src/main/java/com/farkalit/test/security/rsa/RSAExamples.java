package com.farkalit.test.security.rsa;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.util.Base64;

import javax.crypto.Cipher;

public class RSAExamples {

	/**
	 * Generating key pairs in Java
	 * 
	 * @return
	 * @throws Exception
	 */
	public static KeyPair generateKeyPair() throws Exception {
	    KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
	    generator.initialize(2048, new SecureRandom());
	    KeyPair pair = generator.generateKeyPair();

	    return pair;
	}
	
	/**
	 * ENCRYPTION 
	 * 
	 * @param plainText
	 * @param publicKey
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String plainText, PublicKey publicKey) throws Exception {
	    Cipher encryptCipher = Cipher.getInstance("RSA");
	    encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);

	    byte[] cipherText = encryptCipher.doFinal(plainText.getBytes("UTF-8"));

	    return Base64.getEncoder().encodeToString(cipherText);
	}
	
	/**
	 * DECRYPTION
	 * 
	 * @param cipherText
	 * @param privateKey
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String cipherText, PrivateKey privateKey) throws Exception {
	    byte[] bytes = Base64.getDecoder().decode(cipherText);

	    Cipher decriptCipher = Cipher.getInstance("RSA");
	    decriptCipher.init(Cipher.DECRYPT_MODE, privateKey);

	    return new String(decriptCipher.doFinal(bytes), "UTF-8");
	}
	
	
	private static void testEncryptDecryp̰t() {
		try {
			
			//First generate a public/private key pair
			KeyPair pair = generateKeyPair();
			
			//Our secret message
			String message = "the answer to life the universe and everything";
			
			//Encrypt the message
			String cipherText = encrypt(message, pair.getPublic());
			System.out.println("Encrypted:"+cipherText);
			//Now decrypt it
			String decipheredMessage = decrypt(cipherText, pair.getPrivate());
			
			System.out.println("Decrypted:"+decipheredMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Sign the text
	 * 
	 * @param plainText
	 * @param privateKey
	 * @return
	 * @throws Exception
	 */
	public static String sign(String plainText, PrivateKey privateKey) throws Exception {
	    Signature privateSignature = Signature.getInstance("SHA256withRSA");
	    privateSignature.initSign(privateKey);
	    privateSignature.update(plainText.getBytes("UTF-8"));

	    byte[] signature = privateSignature.sign();

	    return Base64.getEncoder().encodeToString(signature);
	}
	
	/**
	 * Verify signature
	 * 
	 * @param plainText
	 * @param signature
	 * @param publicKey
	 * @return
	 * @throws Exception
	 */
	public static boolean verifySign(String plainText, String signature, PublicKey publicKey) throws Exception {
	    Signature publicSignature = Signature.getInstance("SHA256withRSA");
	    publicSignature.initVerify(publicKey);
	    publicSignature.update(plainText.getBytes("UTF-8"));

	    byte[] signatureBytes = Base64.getDecoder().decode(signature);

	    return publicSignature.verify(signatureBytes);
	}
	
	
	private static void testSignature() {
		try {
			//First generate a public/private key pair
			KeyPair pair = generateKeyPair();
			//Our secret message
			String message = "{\"externalSystemId\":\"01\",\"storeId\":\"11191\",\"reversalAmount\":\"111.23\",\"orderId\":\"OR111\",\"orderDate\":\"27/10/2020\",\"cnic\":\"\"}";
			System.out.println(message);
			String signature = sign(message, pair.getPrivate());
			System.out.println("Signature:"+signature);
			
			//Let's check the signature
			boolean isCorrect = verifySign(message, signature, pair.getPublic());
			System.out.println("Is Signature correct: " + isCorrect);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Generating RSA key pair from java keystore
	 * 
	 * @return
	 * @throws Exception
	 */
	public static KeyPair getKeyPairFromKeyStore() throws Exception {
	    InputStream ins = RSAExamples.class.getResourceAsStream("/keystore.jks");

	    KeyStore keyStore = KeyStore.getInstance("JCEKS");
	    keyStore.load(ins, "s3cr3t".toCharArray());   //Keystore password
	    KeyStore.PasswordProtection keyPassword =       //Key password
	            new KeyStore.PasswordProtection("s3cr3t".toCharArray());

	    KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry) keyStore.getEntry("mykey", keyPassword);

	    java.security.cert.Certificate cert = keyStore.getCertificate("mykey");
	    PublicKey publicKey = cert.getPublicKey();
	    PrivateKey privateKey = privateKeyEntry.getPrivateKey();

	    return new KeyPair(publicKey, privateKey);
	}
	
	/**
	 * Test the key-store signature with RSA key pair.
	 */
	private static void testSignatureFromKeyStore() {
		
		try {
			KeyPair pair = getKeyPairFromKeyStore();

			String signature = sign("foobar", pair.getPrivate());
			System.out.println("signature from keystore:"+signature);

			//Let's check the signature with public key
			boolean isCorrect = verifySign("foobar", signature, pair.getPublic());
			System.out.println("Signature from keystore is correct: " + isCorrect);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 * @throws Exception
	 */
	private static void writeKeyPairInFile() {
		
		String outFile = "./RSA/keypair/mfac-";
		
		Writer out = null;
		try {
			KeyPair kp = getKeyPairFromKeyStore();
			if (outFile != null) {
				out = new FileWriter(outFile + "private.key");
				out = new FileWriter(outFile + "private.txt");
			}else {
				out = new OutputStreamWriter(System.out);
			}
			System.err.println("Private key format: " + kp.getPrivate().getFormat());
			out.write("-----BEGIN RSA PRIVATE KEY-----\n");
			writeBase64(out, kp.getPrivate());
			out.write("-----END RSA PRIVATE KEY-----\n");

			if (outFile != null) {
				out.close();
				//out = new FileWriter(outFile + "public.pub");
				out = new FileWriter(outFile + "public.pem");
			}

			System.err.println("Public key format: " + kp.getPublic().getFormat());
			out.write("-----BEGIN RSA PUBLIC KEY-----\n");
			writeBase64(out, kp.getPublic());
			out.write("-----END RSA PUBLIC KEY-----\n");
		}catch (Exception e) {
			System.err.println("Error in file creation...");
			e.printStackTrace();
		} finally {
			if (out != null)
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
	
	private static void writeBase64(Writer out, Key key) throws java.io.IOException {
		Base64.Encoder encoder = Base64.getEncoder();
		byte[] buf = key.getEncoded();
		out.write(encoder.encodeToString(buf));
		out.write("\n");
	}
	
	/**
	 * Main metjod to test
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		//testEncryptDecryp̰t();
		
		testSignature();

		//testSignatureFromKeyStore();
		
		//writeKeyPairInFile();
	}
}
