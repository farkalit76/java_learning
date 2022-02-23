package com.farkalit.test.security;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class SHA256RSA {

	private static String INPUT_STR = "{\"externalSystemId\":\"01\",\"storeId\":\"11191\",\"reversalAmount\":\"4.50\",\"orderId\":\"OR111\",\"orderDate\":\"26/10/2020\",\"cnic\":\"\"}";

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
			+ "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCjuKpt/nB9Z2mspYgvKLuqOvvFN2VsnRMElktrS1CWQIJqX8dhQhcywSb4GSJdcOAXsT+BuwB52MCAPDZDwrMo6xMfxIDqshsE5O9J5JUXh0bC0SxiU6TV273BVHyDqj5coQQi3TY0woYpOPtMaho6QvMDY09sCR2+lVBdXJeEAXWSA8m4ZOj73KRzJQ/LRWJNoB9ASlunEzSIzNLdnO6gic85Ei8Wp7SuuYqkPJawrNdAq2Ev0Dvf8cZXC6+8hXTB5CHzluz7D//LnK8XH2GYTmM8TeJUDOmfGag1/yhfu/4KJ8MwKgN70ExDYaHDiYxZer4BCXTG5HGBFF8S4RHbAgMBAAECggEADmY7dxkdw6nIvYqBg8RNXRd69Ul9/iOCfLDLSVCCcfSSg9CQtJkfeTNLvh5Xy2gWDX9lWn+b9xt9MsoY67TAFSU3tV+PBxVLEw8fjJwyELOxIYlMIgnBQZ3l/o7ztJDQk00GFBronZNOQWnlkD70t5MT1IRzg8j5eL2hJ9Tw7ocduXG43w0U6lm1jzTRbkDiptqYCkrjyPQG1DLuL1UU27eXJLgv4Hejkf5vwfIcLVJtQJfMg6bohErkAaYuH099aHsiUa1OXnL2a95qZWu4wIpk5e6PmCA4gO68iqdL0MRzfzhvFD6bQoMFnPnxna/SAhTom+ufRqPtsoX9orrJoQKBgQD1XV9L+z4q9xaOleRTQIYKSynGVy1FToTsiVBPCjvnviuuPBRu+ClzVgaaJMHsZg2tzzYtLeRoPAbpojnu35wBygqmV+6ZwgpqPxSj2eoQ4+/zpBDpItM4wRwUBq95NJCdOV9I5+JUt/8S53DQhxYodDyz9QYQ3VhFuxYGGvPQHQKBgQCq0Vu6SODs8dNp2qj9yEpoMBBsPE2sHZU4LT464wuREFw5qx9M+FGpjp3l/9cg/WdxFNpqv9wCCyk0nwxjtLC+0/B0HEvD3dNybN8r700zvDk+A74879tUFJBu/kkt1Vm794PNCgau5JT+YMHNKp8bU/gZ/76sePMyD2DwTEs4VwKBgQC/mTIZOw/IrTnIDfRFrlT9JMRyRRDs1eumaXN0xSESTcX+QJwUK2Ty03JEW/+ygd6covpDPVrmqjkbp7plded03jiCvJdtKcDIRonviPLmTZfKQYuTAca+ws0SNkY/93QV0f8+BxUN4sAkGuZVb6c1386nF+astKuBiB4illldYQKBgEOZDDLBCnnaDS7zwDKGI6PteWJLzvUQ0+9iQCEvnnEux0BvDrj3/+EYtjXBtBGzFk9y0Ci0ek04u3q+gjmm1Nb/qkVYuUburvG2Ue7iCWwyzXhUJOGajfJNHQc/jBgeOR+giMEIebKWdLvh8G/PsR0wcptfUMaSKrOh0Fvn5VCLAoGATR7yDV2N0hV7IkWGNBcPmYiAC4eUnw9kLjfcLD9hVLnfibMqpvbMTK+mpYriywT7bHsPbIuMFKWXodAl0WzCjiWjEty4zs1dZMVJVq19dsfIeMPqMPISQJDunfB09JvqF7oYvYb6yAEsRnMAoO3mFo9YRofeUAnRElrljNFU5OA="
			+ "-----END PRIVATE KEY-----\n";
		
	public static void main(String[] args) throws Exception {

		String base64Signature = signSHA256RSA(INPUT_STR, strPk);
		System.out.println("Signature=" + base64Signature);
		signSHA256RSA2(strPk);
		
		
//		String base64Signature = signSHA256RSA(INPUT_STR, myStrPk);
//		System.out.println("Signature=" + base64Signature);
//		signSHA256RSA2(myStrPk);
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
}
