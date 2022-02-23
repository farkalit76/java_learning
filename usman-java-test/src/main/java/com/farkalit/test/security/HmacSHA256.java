package com.farkalit.test.security;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

//import java.util.Base64;
//import java.util.Base64.Encoder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

public class HmacSHA256 {

	private static final String HMAC_SHA256 = "HmacSHA256";
	private static String SECRET_KEY = "rG56thbV0p@2q";//"demoCHANGED";

	public static void main(String[] args) {
		try {

			// generateHashkey();
			// generateSignature();
			//generateHashKey();
			generateHashKeyForSTK();
			//generateHashKeyForMM();
			//confirmHashKey();
			//testHashkeyByBase64();
			//generateHexaBytes();
		} catch (Exception e) {
			System.out.println("Error");
		}
	}

	private static void testHashkeyByBase64() {
		try {

			//SecretKeySpec secret_key = new SecretKeySpec(SECRET_KEY.getBytes(), HMAC_SHA256);
			Mac sha256_HMAC = Mac.getInstance(HMAC_SHA256);
			sha256_HMAC.init(new SecretKeySpec(SECRET_KEY.getBytes(), HMAC_SHA256));

			String encodeStr = java.util.Base64.getEncoder()
					.encodeToString(sha256_HMAC.doFinal(getDataToHash().getBytes()));
			System.out.println("javaaa encoded key:" + encodeStr);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private static void testHashkeyByApache() {
		try {

			//SecretKeySpec secret_key = new SecretKeySpec(SECRET_KEY.getBytes(), HMAC_SHA256);
			Mac sha256_HMAC = Mac.getInstance(HMAC_SHA256);
			sha256_HMAC.init(new SecretKeySpec(SECRET_KEY.getBytes(), HMAC_SHA256));

			String hashvalue = Base64.encodeBase64String(sha256_HMAC.doFinal(getDataToHash().getBytes()));
			System.out.println("apache encoded key:" + hashvalue);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private static void generateHexaBytes() {
		try {

			Mac HMAC = Mac.getInstance(HMAC_SHA256);
			HMAC.init(new SecretKeySpec(SECRET_KEY.getBytes("UTF-8"), HMAC_SHA256));

			byte[] sequence = getDataToHash().getBytes("UTF-8");
			byte[] hash = HMAC.doFinal(sequence);

			byte[] hexBytes = new Hex().encode(hash);
			System.out.println("hexaBytes string: " + new String(hexBytes));
		} catch (Exception e) {
			e.printStackTrace();
		}
		// System.out.println(encoded);
		// return encoded;
	}



	public static void generateHashKey() {
		try {
			Mac hasher = Mac.getInstance(HMAC_SHA256);
			hasher.init(new SecretKeySpec(SECRET_KEY.getBytes(), HMAC_SHA256));

			byte[] hash = hasher.doFinal(getDataToHash().getBytes());

			// to lowercase hexits
			String generatedHex = DatatypeConverter.printHexBinary(hash);
			System.out.println("generatedHex:" + generatedHex.toLowerCase());

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
	}
	
	public static void generateHashKeyForSTK() {
		try {
			Mac hasher = Mac.getInstance(HMAC_SHA256);
			hasher.init(new SecretKeySpec(SECRET_KEY.getBytes(), HMAC_SHA256));

			byte[] hash = hasher.doFinal(getSTKDataToHash().getBytes());

			// to lowercase hexits
			String generatedHex = DatatypeConverter.printHexBinary(hash);
			System.out.println("STK generatedHex:" + generatedHex.toLowerCase());

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
	}
	
	public static void generateHashKeyForMM() {
		try {
			Mac hasher = Mac.getInstance(HMAC_SHA256);
			hasher.init(new SecretKeySpec(SECRET_KEY.getBytes(), HMAC_SHA256));

			byte[] hash = hasher.doFinal(getMMDataToHash().getBytes());

			// to lowercase hexits
			String generatedHex = DatatypeConverter.printHexBinary(hash);
			System.out.println("MM generatedHex:" + generatedHex.toLowerCase());

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
	}
	


	private static void confirmHashKey() {

		// $account.$amount.$oid.$sid.$mpesa.$paybill.$airtel.$airtel_paybill;

		String datastring = "OM0292140525Q123.00maf123MAFDEM3151A1603105525101611588DEMOMPESA510800AIRTEL510800";

		try {
			Mac hasher = Mac.getInstance(HMAC_SHA256);
			hasher.init(new SecretKeySpec(SECRET_KEY.getBytes(), HMAC_SHA256));

			byte[] hash = hasher.doFinal(datastring.getBytes());

			// to lowercase hexits
			String generatedHex = DatatypeConverter.printHexBinary(hash);
			System.out.println("datastring confirmation:" + generatedHex.toLowerCase());
			if ("2e68509ed883e9c0496b563d66bd6b4c10c04e8f07e51fc3bae92b5c9dffce1e".equals(generatedHex.toLowerCase())) {
				System.out.println("Hash response is validated...!!!");
			}

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}

	}

	/**
	 * //$live.$oid.$inv.$amount.$tel.$eml.$vid.$curr.$p1.$p2.$p3.$p4.$cst.$cbk;
	 * 
	 * 0maf123demo123.50700000000fausman@mafcarrefour.comdemoKES1http://localhost:8080/mafc/retail
	 * 
	 * @param hash
	 * @return
	 */

	private static String getDataToHash() {
		HashData hash = new HashData(0, "maf123", "maf123", "123.00", "0700000000", "fausman@mafcarrefour.com", "carrefour",
				"KES", "", "", "", "", 1, "http://localhost:8080/mafc/retail");

		String messageData = hash.getLive() + hash.getOid() + hash.getInv() + hash.getAmount() + hash.getTel()
				+ hash.getEml() + hash.getVid() + hash.getCurr() + hash.getP1() + hash.getP2() + hash.getP3()
				+ hash.getP4() + hash.getCst() + hash.getCbk();

		System.out.println("message datastring:" + messageData);
		return messageData;
	}
	
	/**
	 * $phone.$vid.$sid;
	 * 
	 * @return
	 */
	
	private static String getSTKDataToHash() {

		String messageData = "0700000000"+"carrefour"+"MAFCAR3151A1603259070201188792CARREFOUR";

		System.out.println("message datastring:" + messageData);
		return messageData;
	}
	
	/**
	 * $sid.$vid
	 * 
	 * @return
	 */
	
	private static String getMMDataToHash() {

		String messageData = "MAFCAR3151A1603259070201188792CARREFOUR"+"carrefour";

		System.out.println("message datastring:" + messageData);
		return messageData;
	}
	

	
	
}
