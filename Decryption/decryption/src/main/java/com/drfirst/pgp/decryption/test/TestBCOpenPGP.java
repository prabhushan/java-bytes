package com.drfirst.pgp.decryption.test;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.SignatureException;

import javax.crypto.Cipher;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openpgp.PGPException;

import com.drfirst.pgp.decryption.PgpHelper;
import com.drfirst.pgp.decryption.RSAKeyPairGenerator;



public class TestBCOpenPGP {

	static private boolean isArmored = true;
	static private String id = "drfirsttestkey1";
	static private String passwd = "drfirst";
	//static private String passwd = "******";
	static private boolean integrityCheck = true;
	static private String keyPath = "/Users/prabhu/Desktop/prabhu/learning/java-bytes/java-bytes/Decryption/resources/";

	//static private String pubKeyFile = keyPath+"pub.dat";
	
	//static private String privKeyFile = keyPath+"secret.dat";
	
	static private String pubKeyFile = keyPath+"0xD45A6566-pub.asc";
	static private String privKeyFile = keyPath+"0xD45A6566-sec.gpg";
	
//	static private String pubKeyFile = keyPath+"0xD66DBCE6-pub.asc";
//	static private String privKeyFile = keyPath+"0xD66DBCE6-sec.gpg";
	
	static private String plainTextFile = keyPath+"plain-text.txt";
	//static private String cipherTextFile = keyPath+"plain-text-brickftp.txt.gpg";
	static private String cipherTextFile = keyPath+"queueTest.json";
	static private String decPlainTextFile = keyPath+"dec-plain-text-queueTest.txt";

	static public void genKeyPair() throws InvalidKeyException, NoSuchProviderException, SignatureException, IOException, PGPException, NoSuchAlgorithmException {

		RSAKeyPairGenerator rkpg = new RSAKeyPairGenerator();

		Security.addProvider(new BouncyCastleProvider());

		KeyPairGenerator    kpg = KeyPairGenerator.getInstance("RSA", "BC");

		kpg.initialize(1024);

		KeyPair                    kp = kpg.generateKeyPair();

		FileOutputStream    out1 = new FileOutputStream(privKeyFile);
		FileOutputStream    out2 = new FileOutputStream(pubKeyFile);

		rkpg.exportKeyPair(out1, out2, kp.getPublic(), kp.getPrivate(), id, passwd.toCharArray(), isArmored);


	}

	static public void encrypt() throws NoSuchProviderException, IOException, PGPException{
		FileInputStream pubKeyIs = new FileInputStream(pubKeyFile);
		FileOutputStream cipheredFileIs = new FileOutputStream(cipherTextFile);
		PgpHelper.getInstance().encryptFile(cipheredFileIs, plainTextFile, PgpHelper.getInstance().readPublicKey(pubKeyIs), isArmored, integrityCheck);
		cipheredFileIs.close();
		pubKeyIs.close();
	}

	static public void decrypt() throws Exception{

		FileInputStream cipheredFileIs = new FileInputStream(cipherTextFile);
		FileInputStream privKeyIn = new FileInputStream(privKeyFile);
		FileOutputStream plainTextFileIs = new FileOutputStream(decPlainTextFile);
		PgpHelper.getInstance().decryptFile(cipheredFileIs, plainTextFileIs, privKeyIn, passwd.toCharArray());
		cipheredFileIs.close();
		plainTextFileIs.close();
		privKeyIn.close();
	}
	
	public static void main(String[] args) throws NoSuchAlgorithmException 
	{
		try {
			Field field = Class.forName("javax.crypto.JceSecurity").
			getDeclaredField("isRestricted");
			field.setAccessible(true);
			field.set(null, java.lang.Boolean.FALSE);
			} catch (Exception ex) {
			ex.printStackTrace();
			}
//		System.out.println(Cipher.getMaxAllowedKeyLength("PGP"));
//		try {
////			genKeyPair();
//			if(args[0].equals("e"))
//			encrypt();
//			if(args[0].equals("d"))
//			decrypt();
//			else
//				return;
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}
}