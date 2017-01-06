package com.drfirst.pgp.decrypt;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.util.Iterator;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openpgp.PGPCompressedData;
import org.bouncycastle.openpgp.PGPEncryptedDataList;
import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.openpgp.PGPLiteralData;
import org.bouncycastle.openpgp.PGPObjectFactory;
import org.bouncycastle.openpgp.PGPOnePassSignatureList;
import org.bouncycastle.openpgp.PGPPrivateKey;
import org.bouncycastle.openpgp.PGPPublicKeyEncryptedData;
import org.bouncycastle.openpgp.PGPSecretKey;
import org.bouncycastle.openpgp.PGPSecretKeyRingCollection;
import org.bouncycastle.openpgp.operator.PBESecretKeyDecryptor;
import org.bouncycastle.openpgp.operator.PublicKeyDataDecryptorFactory;
import org.bouncycastle.openpgp.operator.jcajce.JcaKeyFingerprintCalculator;
import org.bouncycastle.openpgp.operator.jcajce.JcaPGPDigestCalculatorProviderBuilder;
import org.bouncycastle.openpgp.operator.jcajce.JcePBESecretKeyDecryptorBuilder;
import org.bouncycastle.openpgp.operator.jcajce.JcePublicKeyDataDecryptorFactoryBuilder;

public class Crypto {

	public static void main(String... args) throws FileNotFoundException {
		String keyPath = "/Users/prabhu/Desktop/prabhu/learning/java-bytes/java-bytes/Decryption/resources/";
		String privKeyFile = keyPath + "0xD45A6566-sec.gpg";
		String passPhrase = "drfirst";
		String outputFilePath1 = keyPath + "decrypt1.txt";
		String outputFilePath2 = keyPath + "decrypt2.txt";
		String testFile = "/Users/prabhu/Desktop/prabhu/learning/java-bytes/java-bytes/sso/resources/dfsamltest1.rfc";
		String fullPath = keyPath + "queueTest.json";

		FileInputStream fileInputStream = new FileInputStream(testFile);

		 System.out.println(readStringFromInputStream(fileInputStream));
		//System.out.println(Crypto.decryptwithKeyAsString(fullPath, readBytesFromInputStream(fileInputStream),passPhrase, outputFilePath1));
		//System.out.println(Crypto.decryptwithKeyAsFile(fullPath, privKeyFile, passPhrase, outputFilePath2));
	}
	
	
	private static String readStringFromInputStream(FileInputStream fileInputStream) {
		byte[] buffer = null;
		try {

			while (fileInputStream.available() > 0) {
				buffer = new byte[fileInputStream.available()];
				fileInputStream.read(buffer);
				
			}
			
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
		return new String(buffer);
	}


	private static byte[] readBytesFromInputStream(FileInputStream fileInputStream) {
		byte[] buffer = null;
		try {

			while (fileInputStream.available() > 0) {
				buffer = new byte[fileInputStream.available()];
				fileInputStream.read(buffer);

			}
			
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
		return buffer;
	}

	public static String decryptwithKeyAsFile(String fullPath, String privateKeyFile, String passPhrase,
			String outputFilePath) {
		try {
			InputStream fileInputStream = new FileInputStream(privateKeyFile);
			return decrypt(fullPath, fileInputStream, passPhrase, outputFilePath);
		} catch (Exception ex) {
			return "ERROR -" + ExceptionUtils.getRootCauseMessage(ex);

		}

	}

	public static String decryptwithKeyAsString(String fullPath, byte[] bs, String passPhrase, String outputFilePath) {
		try {
			return decrypt(fullPath, new ByteArrayInputStream(bs), passPhrase, outputFilePath);
		} catch (Exception ex) {
			return "ERROR -" + ExceptionUtils.getRootCauseMessage(ex);

		}

	}

	private static String decrypt(String fullPath, InputStream privateKeyStream, String passPhrase,
			String outputFilePath) throws Exception {
		FileInputStream cipheredFileIs = null;
		FileOutputStream plainTextFileIs = null;
		try {
			cipheredFileIs = new FileInputStream(fullPath);
			plainTextFileIs = new FileOutputStream(outputFilePath);
			//

			decryptFile(cipheredFileIs, plainTextFileIs, privateKeyStream, passPhrase.toCharArray());
		} finally {
			if (cipheredFileIs != null) {
				cipheredFileIs.close();
			}

			if (plainTextFileIs != null) {
				plainTextFileIs.close();
			}

		}
		return "OK -" + outputFilePath;

	}

	private static void decryptFile(InputStream in, OutputStream out, InputStream keyIn, char[] passwd)
			throws Exception {
		Security.addProvider(new BouncyCastleProvider());
		in = org.bouncycastle.openpgp.PGPUtil.getDecoderStream(in);
		PGPObjectFactory pgpF = new PGPObjectFactory(in, new JcaKeyFingerprintCalculator());
		PGPEncryptedDataList enc;
		Object o = pgpF.nextObject();
		//
		// the first object might be a PGP marker packet.
		//
		if (o instanceof PGPEncryptedDataList) {
			enc = (PGPEncryptedDataList) o;
		} else {
			enc = (PGPEncryptedDataList) pgpF.nextObject();
		}

		//
		// find the secret key
		//
		Iterator<PGPPublicKeyEncryptedData> it = enc.getEncryptedDataObjects();
		PGPPrivateKey sKey = null;
		PGPPublicKeyEncryptedData pbe = null;
		while (sKey == null && it.hasNext()) {
			pbe = it.next();
			sKey = findSecretKey(keyIn, pbe.getKeyID(), passwd);
		}

		if (sKey == null) {
			throw new IllegalArgumentException("Secret key for message not found.");
		}

		PublicKeyDataDecryptorFactory b = new JcePublicKeyDataDecryptorFactoryBuilder().setProvider("BC")
				.setContentProvider("BC").build(sKey);

		InputStream clear = pbe.getDataStream(b);

		PGPObjectFactory plainFact = new PGPObjectFactory(clear, new JcaKeyFingerprintCalculator());

		Object message = plainFact.nextObject();

		if (message instanceof PGPCompressedData) {
			PGPCompressedData cData = (PGPCompressedData) message;
			PGPObjectFactory pgpFact = new PGPObjectFactory(cData.getDataStream(), new JcaKeyFingerprintCalculator());

			message = pgpFact.nextObject();
		}

		if (message instanceof PGPLiteralData) {
			PGPLiteralData ld = (PGPLiteralData) message;
			InputStream unc = ld.getInputStream();
			int ch;
			while ((ch = unc.read()) >= 0) {
				out.write(ch);
			}
		} else if (message instanceof PGPOnePassSignatureList) {
			throw new PGPException("Encrypted message contains a signed message - not literal data.");
		} else {
			throw new PGPException("Message is not a simple encrypted file - type unknown.");
		}

		if (pbe.isIntegrityProtected()) {
			if (!pbe.verify()) {
				throw new PGPException("Message failed integrity check");
			}
		}
	}

	private static PGPPrivateKey findSecretKey(InputStream keyIn, long keyID, char[] pass)
			throws IOException, PGPException, NoSuchProviderException {
		PGPSecretKeyRingCollection pgpSec = new PGPSecretKeyRingCollection(
				org.bouncycastle.openpgp.PGPUtil.getDecoderStream(keyIn), new JcaKeyFingerprintCalculator());

		PGPSecretKey pgpSecKey = pgpSec.getSecretKey(keyID);

		if (pgpSecKey == null) {
			return null;
		}

		PBESecretKeyDecryptor a = new JcePBESecretKeyDecryptorBuilder(
				new JcaPGPDigestCalculatorProviderBuilder().setProvider("BC").build()).setProvider("BC").build(pass);

		return pgpSecKey.extractPrivateKey(a);
	}
}
