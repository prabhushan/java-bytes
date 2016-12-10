package com.drfirst.samltest;

import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;

import org.opensaml.DefaultBootstrap;
import org.opensaml.xml.ConfigurationException;
import org.opensaml.xml.io.UnmarshallingException;
import org.opensaml.xml.validation.ValidationException;
import org.xml.sax.SAXException;

import com.drfirst.dfsamllib.saml20.*;

public class SAMLTestMain {
	
//	static String samlCertFile="/Users/prabhu/Desktop/prabhu/learning/homework/sso/resources/dfsamltest1.rfc";
//	static String samlCertFile2="/Users/prabhu/Desktop/prabhu/learning/homework/sso/resources/dfsamltest2.rfc";
	static String samlCertFile="/Users/prabhu/Desktop/prabhu/learning/homework/sso/resources/dfsamltest1.cer";
	static String samlCertFile2="/Users/prabhu/Desktop/prabhu/learning/homework/sso/resources/dfsamltest2.cer";
	static String keyStore="/Users/prabhu/Desktop/prabhu/learning/homework/sso/resources/testkeys.jks";

static Logger logger = Logger.getLogger("SAMLTestMain");
// generate the SAML response
	static private String genSAML()
	{
		String storePass="pass123";
		String keyAlias="dfsamltest1";
		String keyPass="pass123";
		String issuerUrl="https://samltest1.drfirst.com/idp";
        String destinationUrl="https://samltest2.drfirst.com/sso";
        String audienceUrl="https://samltest2.drfirst.com/sso";
    	String assertionSubjectNameId="DrFirstSAMLTest1";
		SAMLResponseMaker srm = new SAMLResponseMaker();
		HashMap<String, String> attributes = new HashMap<String, String>();
		attributes.put("system_name","dfsamltest1");
		attributes.put("user","jwang");
		attributes.put("group","dev");
		
		
		String strSamlResponse =
				 srm.makeSaml20ResponseAuthn(keyStore, storePass, keyAlias, keyPass, issuerUrl, destinationUrl, audienceUrl,
						 assertionSubjectNameId, attributes); 
		return strSamlResponse;
	}
	public static void log4jInit()
	{
    Properties properties=new Properties();
	    properties.setProperty("log4j.rootLogger","INFO,stdout,MyFile");
	    properties.setProperty("log4j.rootCategory","INFO");

	    properties.setProperty("log4j.appender.stdout",     "org.apache.log4j.ConsoleAppender");
	    properties.setProperty("log4j.appender.stdout.layout",  "org.apache.log4j.PatternLayout");
	    properties.setProperty("log4j.appender.stdout.layout.ConversionPattern","%d{yyyy/MM/dd HH:mm:ss.SSS} [%5p] %t (%F) - %m%n");

	    properties.setProperty("log4j.appender.MyFile", "org.apache.log4j.RollingFileAppender");
	    properties.setProperty("log4j.appender.MyFile.File", "d:/test/samltest.log");
	    properties.setProperty("log4j.appender.MyFile.MaxFileSize", "100KB");
	    properties.setProperty("log4j.appender.MyFile.MaxBackupIndex", "1");
	    properties.setProperty("log4j.appender.MyFile.layout",  "org.apache.log4j.PatternLayout");
	    properties.setProperty("log4j.appender.MyFile.layout.ConversionPattern","%d{yyyy/MM/dd HH:mm:ss.SSS} [%5p] %t (%F) - %m%n");

	    //PropertyConfigurator.configure(properties);

	    Logger logger = Logger.getLogger("MyFile");
	}
	
	public static SAMLData  validateSAML(String samlXml, String certFileFullPath) throws Exception
	{
		SAML20Validator validator = new SAML20Validator();
		validator.parseSAMLXml(samlXml);
		logger.info("SAML response issuer="+validator.getIssuer());
		X509Certificate cert = SAML20Utils.readCertFromFile(certFileFullPath, SAML20Utils.DONT_CHECK_CERT);
		SAMLData samlData = validator.doValidate(cert);
		return samlData;
	}
	static X509Certificate getIssuerCertificate(String issuer) throws Exception
	{
		// we fake here should get it from some data base or caches				
	//	String samlCertFile="d:/dev/dfsamllib/trunk/src/main/resources/dfsamltest1.rfc";
		// we don't check validity assuming it already has been verified
		X509Certificate cert = SAML20Utils.readCertFromFile(samlCertFile, SAML20Utils.DONT_CHECK_CERT);
        return cert;
		
	}
	public static SAMLData  validateSAML(String samlXml) throws Exception
	{
		SAML20Validator validator = new SAML20Validator();
		validator.parseSAMLXml(samlXml);
		logger.info("SAML response issuer="+validator.getIssuer());
		logger.info(validator.getSignatureCertificate());
		
	    // use the issure to get certificate
		X509Certificate cert = getIssuerCertificate(validator.getIssuer());
		SAMLData samlData = validator.doValidate(cert);
		return samlData;
	}
	public static SAMLData  validateSAMLWithCertInSAML_NoCertCheck(String samlXml) throws CertificateException, IOException, NoSuchAlgorithmException, KeyStoreException, ParserConfigurationException, SAXException, UnmarshallingException, ValidationException, ValidatorException
	{
		SAML20Validator validator = new SAML20Validator();
		validator.parseSAMLXml(samlXml);
		logger.info("SAML response issuer="+validator.getIssuer());
		logger.info(validator.getSignatureCertificate());
		
		// BEGIN and END is needed
		String certStr= "-----BEGIN CERTIFICATE-----\n"+validator.getSignatureCertificate()+"\n-----END CERTIFICATE-----";
		X509Certificate cert = SAML20Utils.readCertFromString(certStr, SAML20Utils.DONT_CHECK_CERT);
		SAMLData samlData = validator.doValidate(cert);
		return samlData;
	}
	public static void main(String[] args) throws ConfigurationException, CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, ParserConfigurationException, SAXException, UnmarshallingException, ValidationException
	{
		
		log4jInit();
	   	logger.warning("start SAML test");
	    logger.warning("making saml response");
	    String strSamlResponse = genSAML();
	    logger.info(strSamlResponse);
	    logger.info("***************************************************************");
	    try {
		    logger.info("********************* good validation *************************");
	    	SAMLData samlData = validateSAML(strSamlResponse);
	      logger.info("!!saml attributes "+ samlData.getAssertedData().toString());
	    } catch(Exception e) {
	    	logger.log(Level.SEVERE, "validation fail", e);
	    }
	    try {
		    logger.info("********************* good validation with cert in SAML*************************");
	    	SAMLData samlData = validateSAMLWithCertInSAML_NoCertCheck(strSamlResponse);
	      logger.info("!!saml attributes "+ samlData.getAssertedData().toString());
	    } catch(Exception e) {
	    	logger.log(Level.SEVERE, "validation fail", e);
	    }
        // this should fail
	    try {
		    logger.info("************************ failed validation *************************");
	    	SAMLData samlData = validateSAML(strSamlResponse, samlCertFile);
	       logger.info("saml attributes "+ samlData.getAssertedData().toString());
	    } catch(Exception e) {
	    	logger.log(Level.SEVERE, "validation fail", e);
	    }
  }
	
}