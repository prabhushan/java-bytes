package com.drfirst.dfsamllib.saml20;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.log4j.Logger;
import org.opensaml.saml2.core.Assertion;
import org.opensaml.saml2.core.Attribute;
import org.opensaml.saml2.core.AttributeStatement;
import org.opensaml.saml2.core.Response;
import org.opensaml.xml.Configuration;
import org.opensaml.xml.XMLObject;
import org.opensaml.xml.io.Unmarshaller;
import org.opensaml.xml.io.UnmarshallerFactory;
import org.opensaml.xml.io.UnmarshallingException;
import org.opensaml.xml.security.x509.BasicX509Credential;
import org.opensaml.xml.security.x509.X509Credential;
import org.opensaml.xml.signature.Signature;
import org.opensaml.xml.signature.SignatureValidator;
import org.opensaml.xml.validation.ValidationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class SAML20Validator {
	static Logger logger = Logger.getLogger(SAML20Validator.class);
	
	String samlXml;
	Response response;
	Assertion assertion; 
	String statusCode; // don't know how to use this	
	
	public SAML20Validator()
	{
		
	}
	private boolean checkInit()
	{
		return samlXml != null && response != null;
	}
	/**
	 * This must be called before anything
	 * @param samlXml
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @throws UnmarshallingException
	 */
    public void parseSAMLXml(String samlXml) throws ParserConfigurationException, SAXException, IOException, UnmarshallingException
    {
		this.samlXml = samlXml;
		ByteArrayInputStream is = new ByteArrayInputStream(this.samlXml.getBytes());

		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
				.newInstance();
		documentBuilderFactory.setNamespaceAware(true);
		DocumentBuilder docBuilder = documentBuilderFactory
				.newDocumentBuilder();

		Document document = docBuilder.parse(is);
		Element element = document.getDocumentElement();

		UnmarshallerFactory unmarshallerFactory = Configuration
				.getUnmarshallerFactory();
		Unmarshaller unmarshaller = unmarshallerFactory
				.getUnmarshaller(element);
		XMLObject responseXmlObj = unmarshaller.unmarshall(element);
		this.response = (Response) responseXmlObj;
		/* we assume there is only one assertion */ 
		this.assertion = response.getAssertions().get(0);
		// get status code, don't know how to deal with this
	    statusCode = response.getStatus().getStatusCode().getValue();
		logger.info("SAML 2 response status=" + statusCode);
    }
    
    /**
     * so the calling program can use issuer to find stored public key certificate for signature validation
     * The public key certificate is also embedded in the SAMl xml, 
     * it use the certificate in the SAML, the certificate must be validated
     * @return issuer string
     */
    public String getIssuer()
    {
    	return assertion.getIssuer().getValue();
    }
    
    public String getSignatureCertificate()
    {
    	/* assume we have only one certificate in the signature */
    	/* or else we need to match the SAML issuer name */ 
    	String certStr = response.getSignature().getKeyInfo().getX509Datas().get(0).getX509Certificates().get(0).getValue();
    	return certStr;
    }
 	// validate signature examples
	//http://www.programcreek.com/java-api-examples/index.php?api=org.opensaml.xml.signature.SignatureValidator
    private X509Credential getCredential(String keyStoreFullPath, String keyAlias, String  keyStorePassword) throws NoSuchAlgorithmException, CertificateException, FileNotFoundException, IOException, KeyStoreException
    {
        X509Certificate cert = null;
        KeyStore keyStore = KeyStore.getInstance("JKS");
        keyStore.load(new FileInputStream(new File(keyStoreFullPath)), keyStorePassword.toCharArray());
        cert = (X509Certificate) keyStore.getCertificate(keyAlias);
		if(logger.isDebugEnabled())
		{
			logCerificate(cert);
		}
        return makeCredential(cert);
    }

    private X509Credential makeCredential(X509Certificate cert)
    {
        BasicX509Credential credential = new BasicX509Credential();
        credential.setEntityCertificate(cert);
        return credential;    	
    }
    private void validateSignature(Response response, X509Credential credential) throws ValidationException
    {
		Signature sig = response.getSignature();
		SignatureValidator validator = new SignatureValidator(credential);
		validator.validate(sig);

    }
    private SAMLData extractData(Response response)
    {
		// get the assertions etc.
		Assertion assertion = response.getAssertions().get(0);
		SAMLData samlData = new SAMLData();
		samlData.setSubject(assertion.getSubject().getNameID().getValue());
		samlData.setIssuer(assertion.getIssuer().getValue());
		samlData.setAudience(assertion.getConditions().getAudienceRestrictions().get(0).getAudiences().get(0).getAudienceURI());
		List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
        if(attributeStatements.size()>0)
        {
		// assume we only has on attribute statement
		AttributeStatement attributeStatement = attributeStatements.get(0);
		HashMap<String, String> nvs = new HashMap<String, String>();
		List<Attribute> attributes = attributeStatement.getAttributes();
		samlData.setAssertedData(nvs);
		for(Attribute attribute:attributes) {
			String name = attribute.getName();
			// again assume we only have one value, in principle there can be a set of values
			String value = attribute.getAttributeValues().get(0).getDOM().getTextContent();
			if(name != null)
				nvs.put(name, value);
		}
        } else {
        	logger.warn("no asserted attributes");
        }
		return samlData;    	
    }
    static public void logCerificate(X509Certificate cert )
    {
	    logger.debug(cert.getVersion());
	    logger.debug(cert.getSerialNumber().toString(16));
	    logger.debug(cert.getSubjectDN());
	    logger.debug(cert.getIssuerDN());
	    logger.debug(cert.getNotBefore());
	    logger.debug(cert.getNotAfter());
	    logger.debug(cert.getSigAlgName());

    }
	public SAMLData doValidate(String keyStoreFullPath, String keyAlias, String  keyStorePassword)
			throws ParserConfigurationException, SAXException, IOException,
			UnmarshallingException, ValidationException, NoSuchAlgorithmException, CertificateException, KeyStoreException, ValidatorException {
		
        if(!this.checkInit())
        {
        	throw new ValidatorException("the validator has no data");
        }
		// get status code, don't know how to deal with this
		String statusCode = response.getStatus().getStatusCode().getValue();
		logger.info("SAML 2 response status=" + statusCode);
		// check the signature first
		validateSignature(response, getCredential(keyStoreFullPath, keyAlias, keyStorePassword));

		return extractData(response);
	}
	public SAMLData doValidateWithCertString(String base64CertString)
			throws ParserConfigurationException, SAXException, IOException,
			UnmarshallingException, ValidationException, NoSuchAlgorithmException, CertificateException, KeyStoreException, ValidatorException {
		X509Certificate cert = SAML20Utils.loadCertificate(base64CertString, false);
		if(logger.isDebugEnabled())
		{
			logCerificate(cert);
		}
		return doValidate(cert);
	
	}
	public SAMLData doValidate(String inputXml, String base64CertString, boolean checkCert)
			throws ParserConfigurationException, SAXException, IOException,
			UnmarshallingException, ValidationException, NoSuchAlgorithmException, CertificateException, KeyStoreException, ValidatorException {
		X509Certificate cert = SAML20Utils.loadCertificate(base64CertString,  checkCert);
		if(logger.isDebugEnabled())
		{
			logCerificate(cert);
		}
		return doValidate(cert);
	}
	public SAMLData doValidate(X509Certificate cert)
			throws ParserConfigurationException, SAXException, IOException,
			UnmarshallingException, ValidationException, NoSuchAlgorithmException, CertificateException, KeyStoreException, ValidatorException {

        if(!this.checkInit())
        {
        	throw new ValidatorException("the validator has no data");
        }
		
		// check the signature first
		validateSignature(response, makeCredential(cert));

		return extractData(response);
	}

}
