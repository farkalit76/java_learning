package com.farkalit.test.security.rsa;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

//import org.apache.log4j.Logger;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class PemKeyPairGenerator {

	//protected final static Logger LOGGER = Logger.getLogger(Main.class);

	public static final int KEY_SIZE = 2048;

	public static void main(String[] args)
			throws FileNotFoundException, IOException, NoSuchAlgorithmException, NoSuchProviderException {
		Security.addProvider(new BouncyCastleProvider());
		System.out.println("BouncyCastle provider added.");

		KeyPair keyPair = generateRSAKeyPair();
		RSAPrivateKey priv = (RSAPrivateKey) keyPair.getPrivate();
		RSAPublicKey pub = (RSAPublicKey) keyPair.getPublic();

		writePemFile(priv, "RSA PRIVATE KEY", "id_rsa");
		writePemFile(pub, "RSA PUBLIC KEY", "id_rsa.pem");

	}

	private static KeyPair generateRSAKeyPair() throws NoSuchAlgorithmException, NoSuchProviderException {
		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA", "BC");
		generator.initialize(KEY_SIZE);

		KeyPair keyPair = generator.generateKeyPair();
		System.out.println("RSA key pair generated.");
		return keyPair;
	}

	private static void writePemFile(Key key, String description, String filename)
			throws FileNotFoundException, IOException {
		PemFile pemFile = new PemFile(key, description);
		pemFile.write(filename);

		System.out.println(String.format("%s successfully writen in file %s.", description, filename));
	}

}
