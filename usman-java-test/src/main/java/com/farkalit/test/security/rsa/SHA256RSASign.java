package com.farkalit.test.security.rsa;

import java.io.FileReader;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import org.bouncycastle.util.io.pem.PemReader;

import com.farkalit.test.security.ReversalResVO;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SHA256RSASign {

	private static String INPUT_STR = "{\"externalSystemId\":\"01\",\"storeId\":\"11191\",\"reversalAmount\":\"111.23\",\"orderId\":\"OR111\",\"orderDate\":\"27/10/2020\",\"cnic\":\"\"}";

	private static String strPk = "-----BEGIN PRIVATE KEY-----\n"
				+ "MIIEpgIBAAKCAQEAkL1vGOC4sEvPucEikqoqG2qDaW3NUV0xCqEXbvRWpQs2d4ml\n"
				+ "B7x/DCnXlcieqsVB64LV783hyEHFmVLt17mMzZzkRtBo3bkRVjdLHMhS1mueOcR1\n"
				+ "SGuMAe9olQYWIZX2kMZWK8qA5oeQi8avQZDJZPJkWy64TL2C4+GfJDwGTWmkAV1x\n"
				+ "XbNjOepdp9m6l/6t3tJwPFzbxTP74hlx+7cFhUFvP2irRsywg/QFxU5+YLg8db1N\n"
				+ "rKEXa7Nkt4iIbG/2O7RtsTo5GfdHHbT5mnPMJfsCKJupFad9qXeXyxn1uVzF/oCN\n"
				+ "gWz0PFXbHzbLn7GkZkZZgwj4e6fIWvTILbAtrwIDAQABAoIBAQCGP96zEYTcqIS0\n"
				+ "w4oAxWqm4UWgXWMKK8kYFdD5bhKLpPnRKE90/IZOzuSSnnFk7e+1tTYf5jOcnjzc\n"
				+ "eMn4xLbv46zsbdRGi6ij8kduG/SRw2IQLMOn93D2PowfZXTTlBgNspkspvimWmlc\n"
				+ "7F2UD+ptAlLJLG1vaHvmSFhmsHIcDBIFLO7iCtRSsXtMh+9/YcpLto8WtzFo3c3H\n"
				+ "BF7eCRYF3jdmsjr/G2SbpYQn5+oJ+ioihXzRvfQO3EI+0uSawkj74de8YuYcp5zm\n"
				+ "XZyfIJCVYbv0u3oCTS90+3sn4Sxiz4yxI7XXhjX1K/4P0u6piKTLVKJY9C6hNT2Z\n"
				+ "EWZMuVKZAoGBAMXU+e99LDfFiJNFfFmkoGEg8uOLtqbNQPHlj3wMw/idAx5qG4nZ\n"
				+ "FaVeKe1/80QZpG/OAZlNPoDZtfSqyQyb/k0fUr14jlM4jNQNEclWYFDWG2icy/PG\n"
				+ "RA24XZx30pZFW8WV3Y97DQm7SO6dyS9vew3rvuzMnakwV+3etURD9RnjAoGBALtM\n"
				+ "Kxc/gEhTcfvIwqnVD8zVlqEqMsEblNOhXouqLVC1CA36KDOt8jClX8P6OWXBwnJj\n"
				+ "iPOEjDXl01Xq5EkWzPCsyKJTsHcb9+vtxv6AJrzQA1/bJgsoIgWuvgdjuATcd/fT\n"
				+ "wKyr+eaOb0J6eaG2Ngwf/FkNGLZ49cr8ahqY61bFAoGBAIXnRRDj8bUCQBImuydV\n"
				+ "9bQogB6iZ4v5a9cPYZI+dbrmU8EXYvJn/G3FEa4Op9LVZGbE0nI4RXEjazRjENg3\n"
				+ "EeaTqAZU/ki4AyJYgkx76Y9tjHm/f5DOByweOUZ6GBDce2R+rsu6JzYKDTBtnko8\n"
				+ "M9dVVRKk4KYu8aUw1BKTyOevAoGBAKcGyfa1fmUBZUiwREjH5Is6flXDF/f17Ma9\n"
				+ "xV6vqDGs/z3XtDpCCRUwpTJT1xPTst/nu53OumrmRYz9KA+18ZU/wLxh/aB6pKEV\n"
				+ "LryO8/y1VA0LleWlKJIAvx4/4z8SjTnI8K4Lv5PHuZiDOCzqHVC6xYN0lnFDdY5L\n"
				+ "gyOs81IxAoGBAIiOLdj+cW7F1ipzlVwNNlrjtZTIar6BTuTWN8VEkSK6l4PPKHoq\n"
				+ "43hmtQ59KqP5DESyGm9gRRshDq7bF/nxzR/ditg+zsbV+58xcXO7gym6i+93ave/\n"
				+ "6yg4CcOdbHStpitCt6nLuD0prhRNQ7gRQt9iXNH4vziZ/4/Zc0HHFCTs" 
				+ "-----END PRIVATE KEY-----\n";
	
	private static String myStrPk = "-----BEGIN PRIVATE KEY-----\n"
			+ "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDWSxL2Nh0R8KtD\n"
			+ "unTl3nNNTUXDRaWvOmp003XNrB/XXXcme2LRYfrf2JQZXiIlrjOK3B6ACWN0wQ8L\n"
			+ "5I65/WcVjHS+wfs50QY+fsiaUyXkdbgfk4ImIN/a/nqNN3pLfzZh597HwYhhUQSn\n"
			+ "+gtafluxfMVeLXSiRB0AaHAjvU2FzAE4s1Z/1H1ewZ4Aecya9L6tXmdzQmeyyoNl\n"
			+ "lP4Adhap9AT8DFXW473qMFKVEWGVEQzAdq3w7DpquURPw7xMeEdhudBYvw87VBue\n"
			+ "ikfExpTWyyP9NP9E5mpo8qx/BrXoEzVuKdYymzN17JRnBR6BSECvmRwYWhuqcmbQ\n"
			+ "UYTTyrmJAgMBAAECggEAESnIJgIlh+dPwEfxzutnLrzOppowb/1sw1igac2sazJo\n"
			+ "NaPSQVToo/8TtYhtkbkHFcsVgkm+Nn6z9s7ig5EBLZ+nRhQBTBsqtdyvsnSQZ2Dq\n"
			+ "U1UcKnOdmkuhgyf7Woqgz2yj0jn0815q0g1b1W6jZ27H3Agl/E0aSYsT2Dj0OY/8\n"
			+ "s2rvVrEmk3Dzkx9pMsIbTmY9Oq5h2l5KUoURMsAG6YKnRwUD330x+LqVSMjZljuC\n"
			+ "jSDQF8uCFFyDlGwYqnzV8o8z5qClFoESduKglZO6Q+HqImQfb98rHoZofJa8bD7Z\n"
			+ "DEG0SYZ5kG1WxbgnBEqW32C3j3U6dSTMV0HNzktGEQKBgQD9EaO4DeXMi9fDCVZ9\n"
			+ "mSD0We20Ke0a/4DACENh8QvdbL3pxil57oFpOFg61d4OQCGoY8G7N2JtZRfY1QKK\n"
			+ "T+K21dUGBzgOTTqs8P9p6TkSLkl51TPSOoUQGQZMtPqd3egsGEMk8by8RoosHcPG\n"
			+ "lE4jiiId/AQyGEA+Yo7B4IIVXQKBgQDYxnaJvEjbB+btpCFLGOGW5Xu9oTzjUWW5\n"
			+ "YC1i6Mb0QBwrnRJeDbDUepuqI9uRadpuVEBsSB2uZmmUZtKzJy+QFpd3K/7lqOKd\n"
			+ "c5kJQQfJly8YHtK0k3JTWlWrrWheUeW34e9x1vMXZztt8NY0vXG92Vpd8ANMUImU\n"
			+ "vzOdcMSmHQKBgQDVs4WbMTEyr4x9yG7fFOY9MYuHLjhpKgVhJBkypcitLNyagpoX\n"
			+ "uw5Q+iGXXW5sfj9t4P+Bxt05H7spgYbpoVgDqUKAGqx9dz3rOH+WwjlNXwTw+tcN\n"
			+ "2hjvZRJqyBEyKmeahMp+chw8qRyY8HGYSThKzqTMyToJy2zEwZlnotx2iQKBgFl5\n"
			+ "2bXShNP7uAMBC0j/FSZ+xIuqQQKnN3DD1RgNcwmgaMBLzigKSKH7lcMtl9S2t5Vw\n"
			+ "q0LGpZG17gB2TKPsGcLEwHJNM6JCCn/pFhLux7Ma9zJijqBAeCEVmWUKZ5j+/u3e\n"
			+ "kUIQ8jgHwU0I6rHMPGWbkKRTjFj0qUU/gqOKx3C1AoGAPho29iN+NG5e5x1ItsBj\n"
			+ "VrVMqbwylx/tuFLD/CrfedMeiuRbYP5ZrCdp6kseOPW9wqIJywCTaMQ9Xnvtws7k\n"
			+ "d1aC1ywU03VUP3d7JFKeVG3bFGq7Ut5i9tjDcI4NkQsNKfbRrTiNku0rivEdBE+W\n"
			+ "9EslPqL+5iiLq7HKV64+ekc="
			+ "-----END PRIVATE KEY-----\n";
		
	public static void main(String[] args) throws Exception {

		//String base64Signature = signSHA256RSA(INPUT_STR, myStrPk);
		//System.out.println("Signature=" + base64Signature);
		//signSHA256RSA2(myStrPk);
		
		
//		String base64Signature = signSHA256RSA(INPUT_STR, myStrPk);
//		System.out.println("Signature=" + base64Signature);
//		signSHA256RSA2(myStrPk);
		
		ReversalResVO res = new ReversalResVO();
		res.setResponseCode("007");
		res.setResponseDesc("SYSTEM ERROR");
		verifySignatureJson(res, "");
	}

	// Create base64 encoded signature using SHA256/RSA.
	private static String signSHA256RSA(String input, String strPk) {
		String base64Signature = "";
		try {
			java.security.Security.addProvider(
			         new org.bouncycastle.jce.provider.BouncyCastleProvider()
			);
			// Remove markers and new line characters in private key
			String realPK = strPk.replaceAll("-----END PRIVATE KEY-----", "")
					.replaceAll("-----BEGIN PRIVATE KEY-----", "").replaceAll("\n", "");

			System.out.println("realPK:" + realPK);
			byte[] b1 = Base64.getDecoder().decode(realPK);
			PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(b1);
			KeyFactory kf = KeyFactory.getInstance("RSA");

			Signature privateSignature = Signature.getInstance("SHA256withRSA");
			privateSignature.initSign(kf.generatePrivate(spec));
			privateSignature.update(input.getBytes("UTF-8"));
			byte[] s = privateSignature.sign();
			base64Signature = Base64.getEncoder().encodeToString(s);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return base64Signature;
	}

	private static void signSHA256RSA2(String strPk) {
		try {

			java.security.Security.addProvider(
			         new org.bouncycastle.jce.provider.BouncyCastleProvider()
			);
			Signature signature = Signature.getInstance("SHA256withRSA");

			String realPK = strPk.replaceAll("\\n", "").replaceAll("-----BEGIN PRIVATE KEY-----", "")
					.replaceAll("-----END PRIVATE KEY-----", "");

			PKCS8EncodedKeySpec encodedPrivateKeySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(realPK));
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PrivateKey privateKey = keyFactory.generatePrivate(encodedPrivateKeySpec);
			signature.initSign(privateKey);

			signature.update(INPUT_STR.getBytes("UTF-8"));
			byte[] signatureValue = signature.sign();
			String encode = Base64.getEncoder().encodeToString(signatureValue);
			System.out.println("Signature:"+encode);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean verifySignature(ReversalResVO reversal, String signature) {

		boolean verify = false;
		try {
			// 1. Get the Json Format response values
			String jsonFormat = getJsonStringReversalRes(reversal);
			// Creating the MessageDigest object
			MessageDigest md = MessageDigest.getInstance("SHA-256");

			// Passing data to the created MessageDigest Object
			md.update(jsonFormat.getBytes());

			// Compute the message digest
			byte[] digest = md.digest();
			String digestStr= new String(digest);
			System.out.println("message digest:"+digestStr);
			
			
			//3.Use the public key to decrypt the signature to a message digest
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return verify;
	}
//	public boolean verifySignature(ReversalResVO reversal, String  signature) {
//
//		boolean verify = false;
//		try {
//			java.security.Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
//			String realPub = publicKey.replace("\\n", "");
//			//byte[] b1 = Base64.getDecoder().decode(realPub);
//			//PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(b1);
//			byte[] encodedPublicKey = realPub.getBytes( "UTF-8" );
//
//			// 2 - loading public key in a relevant object :
//			X509EncodedKeySpec spec = new X509EncodedKeySpec( encodedPublicKey );
//			KeyFactory kf = KeyFactory.getInstance("RSA");
//			
//			//1. Get the Json Format response values
//			String jsonFormat = getJsonStringReversalRes(reversal);
//			Signature publicSignature = Signature.getInstance("SHA256withRSA");
//			publicSignature.initVerify(kf.generatePublic(spec));
//			publicSignature.update(jsonFormat.getBytes("UTF-8"));
//
//			byte[] signatureBytes = Base64.getDecoder().decode(signature);
//
//			verify = publicSignature.verify(signatureBytes);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return verify;
//	}

	/**
	 * 
	 * @param reversal
	 * @return
	 */
	private static String getJsonStringReversalRes(ReversalResVO reversal) {

		String jsonString = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			// Converting the Object to JSONString
			jsonString = mapper.writeValueAsString(reversal);
			System.out.println("Response jsonString:" + jsonString);
		} catch (Exception e) {
			System.err.println("Error is converting java object to json:" + e.getMessage());
			e.printStackTrace();
		}
		return jsonString;
	}
	
	
	public static boolean verifySignatureJson(ReversalResVO reversal, String signature) {

		boolean verify = false;
		PemReader reader = null;
		signature = "GoqwFLZ4xWl8gZJooaQo1AykFWh37NpGE9UhG6tkGLYuFw9schMPYdQGapxO64Ob9KBZRBmTXUyewwleuMLFR4MaF0n3C25tSq/MDFWr4UoXRWnng+6J+ohDm12ADk6RbTOFeYZsKxTfi1+gY9I44KPXzYzL/YuOGrjgWOtiiR0gSZx03zrD2zS+Rzwd6KzrIN71eFA3kzQycQTMo9pEYUSmCbUvMb5WnU064JL2aiHB9G3FTfzMqR9a+pblmgXTy0UmZZqhppiyKiTqVh7rAirRg4Zkk3PltBrhfoCUcRDndKuhfbSlHudZIr3x6PrU+nJWGl6v/oL3MdDZtvsBeg==";
		try {
			// 1. Get the JSON Format response values
			String jsonFormat = getJsonStringReversalRes(reversal);
			
			//2. Get MessageDigest by using SHA256 algorithm
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			// Passing data to the created MessageDigest Object
			md.update(jsonFormat.getBytes("UTF-8"));
			byte[] digest = md.digest();
			System.out.println(" digest byte[]:"+digest);
			System.out.println(" digest byte[]:"+digest.length);
			String digestStr= new String(digest);
			System.out.println("message digest string:"+digestStr);
			
			
			//3.Use the public key to decrypt the <b>signature</b> to a message digest
			java.security.Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
			//Get the public key
			reader = new PemReader( new FileReader( "RSA/id_rsa.pem" ) );
	        final byte[] pubKey = reader.readPemObject().getContent();
	        System.out.println("Public key:"+new String(pubKey));
			KeyFactory kf = KeyFactory.getInstance("RSA");
			X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(pubKey);
			PublicKey generatePublic = kf.generatePublic(publicKeySpec);
			
			
			//Decrypt the signature with public key
			Signature publicSignature = Signature.getInstance("SHA256withRSA");
			publicSignature.initVerify(generatePublic);
			publicSignature.update(jsonFormat.getBytes("UTF-8"));
			
			boolean test = publicSignature.verify(signature.getBytes());
			System.out.println("signatureBytes:"+test);
			
			byte[] signatureBytes = Base64.getDecoder().decode(signature);
			System.out.println("signatureBytes:"+signatureBytes);
			System.out.println("signatureBytes:"+signatureBytes.length);
			verify = publicSignature.verify(signatureBytes);
			System.out.println("verify signature:"+verify);
			
			//Or decrypt the signature by Cipher
//			Cipher asymmetricCipher = Cipher.getInstance("RSA/NONE/NoPadding", "BC");
//			//Cipher asymmetricCipher = Cipher.getInstance("RSA", "BC");
//			// initialize your cipher
//			asymmetricCipher.init(Cipher.DECRYPT_MODE, generatePublic);
//			// asuming, cipherText is a byte array containing your encrypted message
//			byte[] plainText = asymmetricCipher.doFinal(signature.getBytes());
//			System.out.println("signatureBytes:"+plainText.length);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				reader.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return verify;
	}
	
}
