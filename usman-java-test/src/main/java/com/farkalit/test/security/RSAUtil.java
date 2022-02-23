package com.farkalit.test.security;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RSAUtil {

	private static String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAo7iqbf5wfWdprKWILyi7qjr7xTdlbJ0TBJZLa0tQlkCCal/HYUIXMsEm+BkiXXDgF7E/gbsAedjAgDw2Q8KzKOsTH8SA6rIbBOTvSeSVF4dGwtEsYlOk1du9wVR8g6o+XKEEIt02NMKGKTj7TGoaOkLzA2NPbAkdvpVQXVyXhAF1kgPJuGTo+9ykcyUPy0ViTaAfQEpbpxM0iMzS3ZzuoInPORIvFqe0rrmKpDyWsKzXQKthL9A73/HGVwuvvIV0weQh85bs+w//y5yvFx9hmE5jPE3iVAzpnxmoNf8oX7v+CifDMCoDe9BMQ2Ghw4mMWXq+AQl0xuRxgRRfEuER2wIDAQAB";
	private static String privateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCjuKpt/nB9Z2mspYgvKLuqOvvFN2VsnRMElktrS1CWQIJqX8dhQhcywSb4GSJdcOAXsT+BuwB52MCAPDZDwrMo6xMfxIDqshsE5O9J5JUXh0bC0SxiU6TV273BVHyDqj5coQQi3TY0woYpOPtMaho6QvMDY09sCR2+lVBdXJeEAXWSA8m4ZOj73KRzJQ/LRWJNoB9ASlunEzSIzNLdnO6gic85Ei8Wp7SuuYqkPJawrNdAq2Ev0Dvf8cZXC6+8hXTB5CHzluz7D//LnK8XH2GYTmM8TeJUDOmfGag1/yhfu/4KJ8MwKgN70ExDYaHDiYxZer4BCXTG5HGBFF8S4RHbAgMBAAECggEADmY7dxkdw6nIvYqBg8RNXRd69Ul9/iOCfLDLSVCCcfSSg9CQtJkfeTNLvh5Xy2gWDX9lWn+b9xt9MsoY67TAFSU3tV+PBxVLEw8fjJwyELOxIYlMIgnBQZ3l/o7ztJDQk00GFBronZNOQWnlkD70t5MT1IRzg8j5eL2hJ9Tw7ocduXG43w0U6lm1jzTRbkDiptqYCkrjyPQG1DLuL1UU27eXJLgv4Hejkf5vwfIcLVJtQJfMg6bohErkAaYuH099aHsiUa1OXnL2a95qZWu4wIpk5e6PmCA4gO68iqdL0MRzfzhvFD6bQoMFnPnxna/SAhTom+ufRqPtsoX9orrJoQKBgQD1XV9L+z4q9xaOleRTQIYKSynGVy1FToTsiVBPCjvnviuuPBRu+ClzVgaaJMHsZg2tzzYtLeRoPAbpojnu35wBygqmV+6ZwgpqPxSj2eoQ4+/zpBDpItM4wRwUBq95NJCdOV9I5+JUt/8S53DQhxYodDyz9QYQ3VhFuxYGGvPQHQKBgQCq0Vu6SODs8dNp2qj9yEpoMBBsPE2sHZU4LT464wuREFw5qx9M+FGpjp3l/9cg/WdxFNpqv9wCCyk0nwxjtLC+0/B0HEvD3dNybN8r700zvDk+A74879tUFJBu/kkt1Vm794PNCgau5JT+YMHNKp8bU/gZ/76sePMyD2DwTEs4VwKBgQC/mTIZOw/IrTnIDfRFrlT9JMRyRRDs1eumaXN0xSESTcX+QJwUK2Ty03JEW/+ygd6covpDPVrmqjkbp7plded03jiCvJdtKcDIRonviPLmTZfKQYuTAca+ws0SNkY/93QV0f8+BxUN4sAkGuZVb6c1386nF+astKuBiB4illldYQKBgEOZDDLBCnnaDS7zwDKGI6PteWJLzvUQ0+9iQCEvnnEux0BvDrj3/+EYtjXBtBGzFk9y0Ci0ek04u3q+gjmm1Nb/qkVYuUburvG2Ue7iCWwyzXhUJOGajfJNHQc/jBgeOR+giMEIebKWdLvh8G/PsR0wcptfUMaSKrOh0Fvn5VCLAoGATR7yDV2N0hV7IkWGNBcPmYiAC4eUnw9kLjfcLD9hVLnfibMqpvbMTK+mpYriywT7bHsPbIuMFKWXodAl0WzCjiWjEty4zs1dZMVJVq19dsfIeMPqMPISQJDunfB09JvqF7oYvYb6yAEsRnMAoO3mFo9YRofeUAnRElrljNFU5OA=";
	
	
	public static PublicKey getPublicKey(String base64PublicKey) {
		PublicKey publicKey = null;
		try {
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(base64PublicKey.getBytes()));
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			publicKey = keyFactory.generatePublic(keySpec);
			return publicKey;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return publicKey;
	}

	public static PrivateKey getPrivateKey(String base64PrivateKey) {
		PrivateKey privateKey = null;
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(base64PrivateKey.getBytes()));
		KeyFactory keyFactory = null;
		try {
			keyFactory = KeyFactory.getInstance("RSA");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		try {
			privateKey = keyFactory.generatePrivate(keySpec);
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return privateKey;
	}

	public static byte[] encrypt(String data, String publicKey) throws BadPaddingException, IllegalBlockSizeException,
			InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(publicKey));
		return cipher.doFinal(data.getBytes());
	}

	public static String decrypt(byte[] data, PrivateKey privateKey) throws NoSuchPaddingException,
			NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		return new String(cipher.doFinal(data));
	}

	public static String decrypt(String data, String base64PrivateKey) throws IllegalBlockSizeException,
			InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
		return decrypt(Base64.getDecoder().decode(data.getBytes()), getPrivateKey(base64PrivateKey));
	}

	public static void main(String[] args)
			throws IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, BadPaddingException {
		try {
			// String encryptedString = Base64.getEncoder().encodeToString(encrypt("Usman is
			// the author", publicKey));
			String encryptedString = Base64.getEncoder().encodeToString(encrypt(getDataToEncrypt(getReversalData()), publicKey));
			System.out.println("Encrypted String:" + encryptedString);
			String decryptedString = decrypt(encryptedString, privateKey);
			System.out.println("Dec:" + decryptedString);
		} catch (NoSuchAlgorithmException e) {
			System.err.println(e.getMessage());
		}
	}

	private static String getDataToEncrypt(ReversalVO revers) {

		String jsonString = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			// Converting the Object to JSONString
			jsonString = mapper.writeValueAsString(revers);
			System.out.println(jsonString);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return jsonString;

	}

	public static ReversalVO getReversalData() {

		String externalSystemId = "01";
		String storeId = "11191";
		String reversalAmount = "4.50";
		String orderId = "OR111";
		String orderDate = "26/10/2020";
		String cnic = "";
		ReversalVO revers = new ReversalVO();
		revers.setExternalSystemId(externalSystemId);
		revers.setStoreId(storeId);
		revers.setReversalAmount(reversalAmount);
		revers.setOrderId(orderId);
		revers.setOrderDate(orderDate);
		revers.setCnic(cnic);

		return revers;

	}

}
