package com.drfirst.dfsamllib.saml20;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.opensaml.Configuration;
import org.opensaml.common.SAMLObjectBuilder;
import org.opensaml.common.SAMLVersion;
import org.opensaml.common.impl.SecureRandomIdentifierGenerator;
import org.opensaml.saml2.core.Assertion;
import org.opensaml.saml2.core.Attribute;
import org.opensaml.saml2.core.AttributeStatement;
import org.opensaml.saml2.core.AttributeValue;
import org.opensaml.saml2.core.Audience;
import org.opensaml.saml2.core.AudienceRestriction;
import org.opensaml.saml2.core.AuthnContext;
import org.opensaml.saml2.core.AuthnContextClassRef;
import org.opensaml.saml2.core.AuthnStatement;
import org.opensaml.saml2.core.Conditions;
import org.opensaml.saml2.core.Issuer;
import org.opensaml.saml2.core.NameID;
import org.opensaml.saml2.core.Response;
import org.opensaml.saml2.core.Status;
import org.opensaml.saml2.core.StatusCode;
import org.opensaml.saml2.core.Subject;
import org.opensaml.saml2.core.SubjectConfirmation;
import org.opensaml.saml2.core.SubjectConfirmationData;
import org.opensaml.security.SAMLSignatureProfileValidator;
import org.opensaml.xml.XMLObjectBuilder;
import org.opensaml.xml.XMLObjectBuilderFactory;
import org.opensaml.xml.io.Marshaller;
import org.opensaml.xml.io.MarshallerFactory;
import org.opensaml.xml.schema.XSString;
import org.opensaml.xml.security.keyinfo.KeyInfoHelper;
import org.opensaml.xml.security.x509.BasicX509Credential;
import org.opensaml.xml.signature.KeyName;
import org.opensaml.xml.signature.Signature;
import org.opensaml.xml.signature.SignatureConstants;
import org.opensaml.xml.signature.SignatureException;
import org.opensaml.xml.signature.SignatureValidator;
import org.opensaml.xml.signature.Signer;
import org.w3c.dom.Element;

public class SAMLResponseMaker {
	String issuerUrl;
	String destinationUrl;
	String audienceUrl;
	String assertionSubjectNameId;
	KeyStoreInfo ksInfo;
	HashMap attributes;
	Assertion signedAssertion;
	DateTime now;
	static final Logger logger = Logger.getLogger(SAMLResponseMaker.class);
	private static SecureRandomIdentifierGenerator idGenerator;

	static {
		try {
			// DefaultBootstrap.bootstrap ();
			idGenerator = new SecureRandomIdentifierGenerator();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public SAMLResponseMaker() {
	}

	/**
	 * Helper method to spawn a new Issuer element based on our issuer URL.
	 */
	public Issuer spawnIssuer() {
		Issuer result = null;
		if (issuerUrl != null) {
			result = (Issuer) SAML20Utils
					.createSamlObject(Issuer.DEFAULT_ELEMENT_NAME);
			result.setValue(issuerUrl);
		}

		return result;
	}

	/**
	 * for check and output for testing and debugging purposes
	 *
	 * @throws Exception
	 */
	public void checkSignedAssertion() throws Exception {
		MarshallerFactory marshallerFactory = Configuration
				.getMarshallerFactory();
		Marshaller marshaller1 = marshallerFactory
				.getMarshaller(signedAssertion);
		Element assertionElement = marshaller1.marshall(signedAssertion);
		// dfs.signSAMLObject(assertionElement);

		// verifyAssertion(assertionElement);

		String m2 = SAML20Utils.nodeToString(assertionElement, false);
		FileOutputStream out = new FileOutputStream(
				"/Users/prabhu/Desktop/prabhu/learning/homework/sso/resources/samlassertion1.txt");
		out.write(m2.getBytes());
		out.close();
	}

	public void signResponse(Response response, KeyStoreInfo ksInfo)
			throws Exception {

		// Namespace dsns = new
		// Namespace("http://www.w3.org/2000/09/xmldsig#", "ds");
		// response.addNamespace(dsns);
		// Namespace xsins = new
		// Namespace("http://www.w3.org/2001/XMLSchema-instance", "xsi");
		// response.addNamespace(xsins);

		/*
		 * Subject subj = (Subject)
		 * createSamlObject(Subject.DEFAULT_ELEMENT_NAME);
		 * response.setSubject(subj);
		 *
		 * NameID nameId = (NameID)
		 * createSamlObject(NameID.DEFAULT_ELEMENT_NAME);
		 * nameId.setValue("DrFirstRcopia"); subj.setNameID(nameId);
		 *
		 * SubjectConfirmation subjConf = (SubjectConfirmation)
		 * createSamlObject(SubjectConfirmation.DEFAULT_ELEMENT_NAME);
		 * subjConf.setMethod("urn:oasis:names:tc:2.0:cm:holder-of-key");
		 * subj.getSubjectConfirmations().add(subjConf);
		 *
		 * SubjectConfirmationData subjData = (SubjectConfirmationData)
		 * createSamlObject(SubjectConfirmationData.DEFAULT_ELEMENT_NAME);
		 * subjData.getUnknownAttributes().put(new
		 * QName("http://www.w3.org/2001/XMLSchema-instance", "type", "xsi"),
		 * "saml:KeyInfoConfirmationDataType");
		 */
		org.opensaml.xml.signature.KeyInfo keyInfo = (org.opensaml.xml.signature.KeyInfo) SAML20Utils
				.createSamlObject(org.opensaml.xml.signature.KeyInfo.DEFAULT_ELEMENT_NAME);
		// subjData.getUnknownXMLObjects().add(ki);

		KeyName kn = (KeyName) SAML20Utils
				.createSamlObject(KeyName.DEFAULT_ELEMENT_NAME);
		kn.setValue(ksInfo.getKeyAlias());
		keyInfo.getKeyNames().add(kn);

		KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
		char[] password = ksInfo.getKeyStorePassword().toCharArray();
		FileInputStream fis = new FileInputStream(ksInfo.getKeyStoreFile());
		ks.load(fis, password);
		fis.close();

		KeyStore.PrivateKeyEntry pkEntry = (KeyStore.PrivateKeyEntry) ks
				.getEntry(ksInfo.getKeyAlias(),
						new KeyStore.PasswordProtection(ksInfo.getKeyPassword()
								.toCharArray()));
		PrivateKey pk = pkEntry.getPrivateKey();
		X509Certificate certificate = (X509Certificate) pkEntry
				.getCertificate();
		KeyInfoHelper.addCertificate(keyInfo, certificate);

		BasicX509Credential credential = new BasicX509Credential();
		credential.setEntityCertificate(certificate);
		credential.setPrivateKey(pk);
		Signature signature = (Signature) SAML20Utils
				.createSamlObject(Signature.DEFAULT_ELEMENT_NAME);
		// signature.addNamespace(dsns); // jwang

		signature.setSigningCredential(credential);

		signature
				.setSignatureAlgorithm(SignatureConstants.ALGO_ID_SIGNATURE_RSA_SHA1);

		signature
				.setCanonicalizationAlgorithm(SignatureConstants.ALGO_ID_C14N_EXCL_OMIT_COMMENTS);

		org.opensaml.xml.signature.KeyInfo keyinfo = (org.opensaml.xml.signature.KeyInfo) SAML20Utils
				.createSamlObject(org.opensaml.xml.signature.KeyInfo.DEFAULT_ELEMENT_NAME);
		KeyInfoHelper.addCertificate(keyinfo, certificate);
		signature.setKeyInfo(keyinfo);

		response.setSignature(signature);

		// marshall Assertion Java class into XML
		MarshallerFactory marshallerFactory = Configuration
				.getMarshallerFactory();
		Marshaller marshaller = marshallerFactory.getMarshaller(response);
		Element responseElement = marshaller.marshall(response);

		try {
			Signer.signObject(signature);
		} catch (SignatureException e) {
			e.printStackTrace();
		}

	}

	public String makeSaml20ResponseAuthn(String keyStore,
			String keyStorePassword, String keyAlias, String keyPassword,
			String issuerUrl, String destinationUrl, String audienceUrl,
			String assertionSubjectNameId, HashMap attributes) {
		try {
			ksInfo = new KeyStoreInfo(keyStore, keyStorePassword, keyAlias,
					keyPassword);
			this.issuerUrl = issuerUrl;
			this.destinationUrl = destinationUrl;
			this.audienceUrl = audienceUrl;
			this.assertionSubjectNameId = assertionSubjectNameId;
			this.attributes = attributes;
			now = new DateTime();
			// m_assertion = makeAssertionAuthn(false);
			// now we build the response part
			Response response = (Response) SAML20Utils
					.createSamlObject(Response.DEFAULT_ELEMENT_NAME);
			response.setDestination(destinationUrl);
			response.setID(idGenerator.generateIdentifier());

			// if (inResponseTo != null)
			// response.setInResponseTo (inResponseTo);

			response.setIssueInstant(now);

			// Namespace dsns = new
			// Namespace("http://www.w3.org/2000/09/xmldsig#", "ds");
			// response.addNamespace(dsns);
			// Namespace xsins = new
			// Namespace("http://www.w3.org/2001/XMLSchema-instance", "xsi");
			// response.addNamespace(xsins);

			if (issuerUrl != null) {
				response.setIssuer(spawnIssuer());
			}
			StatusCode statusCodeElement = (StatusCode) SAML20Utils
					.createSamlObject(StatusCode.DEFAULT_ELEMENT_NAME);
			String statusCode = StatusCode.SUCCESS_URI;
			statusCodeElement.setValue(statusCode);

			Status status = (Status) SAML20Utils
					.createSamlObject(Status.DEFAULT_ELEMENT_NAME);
			status.setStatusCode(statusCodeElement);
			response.setStatus(status);
			// make the assertion
			signedAssertion = makeSignedAssertionAuthn(false);

			response.getAssertions().add(signedAssertion);
			MarshallerFactory marshallerFactory = Configuration
					.getMarshallerFactory();
			Marshaller marshaller1 = marshallerFactory
					.getMarshaller(signedAssertion);
			Element assertionElement = marshaller1.marshall(signedAssertion);

			signResponse(response, ksInfo);

			// dumpParserDetails();
			// Unmarshall new tree around DOM to avoid side effects and Apache
			// xmlsec bug.
			Response signedResponse = (Response) Configuration
					.getUnmarshallerFactory()
					.getUnmarshaller(response.getDOM())
					.unmarshall(response.getDOM());

			Marshaller marshaller = marshallerFactory
					.getMarshaller(signedResponse);
			Element responseElement = marshaller.marshall(signedResponse);

			// KeyStoreInfo ksInfoServer = new
			// KeyStoreInfo("D:\\DFDev\\WellCentiveSSO\\ProductionKey\\TestServer.jks",
			// "sspass", "dfssoprodcert", null);
			// verifyResponse(ksInfoServer, signedResponse);
			// dfs.signSAMLObject(responseElement);
			// verifyResponse(responseElement);
			// dfs.signResponse(response, this.keyAlias, this.keyStore);

			// Node node =
			// responseElement.getOwnerDocument().importNode(assertionElement,true);
			// responseElement.appendChild(node);
			// response =(Response)Configuration.getUnmarshallerFactory
			// ().getUnmarshaller(responseElement).unmarshall(responseElement);

			// Assertion assertion2 =
			// (Assertion)Configuration.getUnmarshallerFactory
			// ().getUnmarshaller(assertionElement).unmarshall(assertionElement);

			String m = SAML20Utils.nodeToString(responseElement, false);
			return m;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Assertion makeSignedAssertionAuthn(boolean fail) throws Exception {

		// dumpParserDetails();
		// DefaultBootstrap.bootstrap();
		Assertion assertion = (Assertion) SAML20Utils
				.createSamlObject(Assertion.DEFAULT_ELEMENT_NAME);
		assertion.setVersion(SAMLVersion.VERSION_20);
		assertion.setID(idGenerator.generateIdentifier()); // in reality, must
															// be unique for all
															// assertions
		assertion.setIssueInstant(now);

		Issuer issuer = (Issuer) SAML20Utils
				.createSamlObject(Issuer.DEFAULT_ELEMENT_NAME);
		issuer.setValue(issuerUrl);
		assertion.setIssuer(issuer);

		Subject subj = (Subject) SAML20Utils
				.createSamlObject(Subject.DEFAULT_ELEMENT_NAME);
		assertion.setSubject(subj);

		NameID nameId = (NameID) SAML20Utils
				.createSamlObject(NameID.DEFAULT_ELEMENT_NAME);
		nameId.setValue(assertionSubjectNameId);
		nameId.setNameQualifier("urn:saml20:transient");
		subj.setNameID(nameId);

		SubjectConfirmation subjConf = (SubjectConfirmation) SAML20Utils
				.createSamlObject(SubjectConfirmation.DEFAULT_ELEMENT_NAME);
		// subjConf.setMethod("urn:oasis:names:tc:2.0:cm:holder-of-key");
		subjConf.setMethod("urn:oasis:names:tc:SAML:2.0:cm:bearer"); // WE WANT
																		// BEAER
		subj.getSubjectConfirmations().add(subjConf);

		SubjectConfirmationData subjData = (SubjectConfirmationData) SAML20Utils
				.createSamlObject(SubjectConfirmationData.DEFAULT_ELEMENT_NAME);
		// subjData.getUnknownAttributes().put(new
		// QName("http://www.w3.org/2001/XMLSchema-instance", "type", "xsi"),
		// "saml:KeyInfoConfirmationDataType");
		subjData.setRecipient(destinationUrl);
		subjData.setNotOnOrAfter(now.plusSeconds(60));
		subjConf.setSubjectConfirmationData(subjData);

		KeyName kn = (KeyName) SAML20Utils
				.createSamlObject(KeyName.DEFAULT_ELEMENT_NAME);
		kn.setValue(ksInfo.getKeyAlias());
		// ki.getKeyNames().add(kn);

		Conditions conditions = (Conditions) SAML20Utils
				.createSamlObject(Conditions.DEFAULT_ELEMENT_NAME);
		conditions.setNotBefore(now.minusSeconds(60));
		conditions.setNotOnOrAfter(now.plusSeconds(60));
		assertion.setConditions(conditions);
		AudienceRestriction ar = (AudienceRestriction) SAML20Utils
				.createSamlObject(AudienceRestriction.DEFAULT_ELEMENT_NAME);
		Audience aa = (Audience) SAML20Utils
				.createSamlObject(Audience.DEFAULT_ELEMENT_NAME);
		aa.setAudienceURI(audienceUrl);
		ar.getAudiences().add(aa);
		conditions.getAudienceRestrictions().add(ar);
		AuthnContextClassRef ref = (AuthnContextClassRef) SAML20Utils
				.createSamlObject(AuthnContextClassRef.DEFAULT_ELEMENT_NAME);
		ref.setAuthnContextClassRef(AuthnContext.UNSPECIFIED_AUTHN_CTX);

		// we do authn
		AuthnContext authnContext = (AuthnContext) SAML20Utils
				.createSamlObject(AuthnContext.DEFAULT_ELEMENT_NAME);
		authnContext.setAuthnContextClassRef(ref);

		AuthnStatement authnStatement = (AuthnStatement) SAML20Utils
				.createSamlObject(AuthnStatement.DEFAULT_ELEMENT_NAME);
		authnStatement.setAuthnInstant(now);
		authnStatement.setSessionIndex(idGenerator.generateIdentifier());
		authnStatement.setAuthnContext(authnContext);
		assertion.getAuthnStatements().add(authnStatement);

		AttributeStatement attributeStatement = (AttributeStatement) SAML20Utils
				.createSamlObject(AttributeStatement.DEFAULT_ELEMENT_NAME);
		// now we go thorugh the attributes
		Set keys = attributes.keySet();
		Iterator ik = keys.iterator();
		while (ik.hasNext()) {
			String key = (String) ik.next();
			String value = (String) attributes.get(key);
			addAttribute(attributeStatement, key, value);
		}
		assertion.getAttributeStatements().add(attributeStatement);

		// now signed the assertion
		KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
		char[] password = ksInfo.getKeyStorePassword().toCharArray();
		FileInputStream fis = new FileInputStream(ksInfo.getKeyStoreFile());
		ks.load(fis, password);
		fis.close();

		KeyStore.PrivateKeyEntry pkEntry = (KeyStore.PrivateKeyEntry) ks
				.getEntry(ksInfo.getKeyAlias(),
						new KeyStore.PasswordProtection(ksInfo.getKeyPassword()
								.toCharArray()));
		PrivateKey pk = pkEntry.getPrivateKey();
		X509Certificate certificate = (X509Certificate) pkEntry
				.getCertificate();

		// KeyInfoHelper.addCertificate(ki, certificate);

		BasicX509Credential credential = new BasicX509Credential();
		credential.setEntityCertificate(certificate);
		credential.setPrivateKey(pk);
		Signature signature = (Signature) SAML20Utils
				.createSamlObject(Signature.DEFAULT_ELEMENT_NAME);
		// signature.addNamespace(dsns); // jwang

		signature.setSigningCredential(credential);

		signature
				.setSignatureAlgorithm(SignatureConstants.ALGO_ID_SIGNATURE_RSA_SHA1);

		signature
				.setCanonicalizationAlgorithm(SignatureConstants.ALGO_ID_C14N_EXCL_OMIT_COMMENTS);

		org.opensaml.xml.signature.KeyInfo keyinfo = (org.opensaml.xml.signature.KeyInfo) SAML20Utils
				.createSamlObject(org.opensaml.xml.signature.KeyInfo.DEFAULT_ELEMENT_NAME);
		KeyInfoHelper.addCertificate(keyinfo, certificate);
		signature.setKeyInfo(keyinfo);

		assertion.setSignature(signature);

		// marshall Assertion Java class into XML, for whatever reason this is
		// necessary
		MarshallerFactory marshallerFactory = Configuration
				.getMarshallerFactory();
		Marshaller marshaller = marshallerFactory.getMarshaller(assertion);
		Element assertionElement = marshaller.marshall(assertion);

		try {
			Signer.signObject(signature);
		} catch (SignatureException e) {
			e.printStackTrace();
		}
		return assertion;
	}

	private void addAttribute(AttributeStatement as, String name, String value) {
		// Attribute attr =
		// (Attribute)createSamlObject(Attribute.DEFAULT_ELEMENT_NAME);

		// attr.setAttributeName(name);

		// XMLObjectBuilder stringBuilder =
		// (XMLObjectBuilder)createSamlObject(XSString.TYPE_NAME);
		// XSString attrNewValue = (XSString)
		// createSamlObject(XSString.TYPE_NAME);
		// attrNewValue.setValue(value);

		// attr.getAttributeValues().add(attrNewValue);
		// as.getAttributes().add(attr);
		XMLObjectBuilderFactory builderFactory = Configuration
				.getBuilderFactory();
		SAMLObjectBuilder attrBuilder = (SAMLObjectBuilder) builderFactory
				.getBuilder(Attribute.DEFAULT_ELEMENT_NAME);
		Attribute attr = (Attribute) attrBuilder.buildObject();
		attr.setName(name);

		XMLObjectBuilder stringBuilder = builderFactory
				.getBuilder(XSString.TYPE_NAME);
		XSString attrNewValue = (XSString) stringBuilder.buildObject(
				AttributeValue.DEFAULT_ELEMENT_NAME, XSString.TYPE_NAME);
		attrNewValue.setValue(value);
		attr.getAttributeValues().add(attrNewValue);

		List<Attribute> la = as.getAttributes();
		la.add(attr);

	}

	private void verifyResponse(KeyStoreInfo ksInfo, Response samlResponse)
			throws Exception {
		KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
		char[] password = ksInfo.getKeyStorePassword().toCharArray();
		FileInputStream fis = new FileInputStream(ksInfo.getKeyStoreFile());
		ks.load(fis, password);
		fis.close();
		KeyStore.TrustedCertificateEntry tcEntry = (KeyStore.TrustedCertificateEntry) ks
				.getEntry(ksInfo.getKeyAlias(), null);
		X509Certificate certificate = (X509Certificate) tcEntry
				.getTrustedCertificate();

		// MarshallerFactory marshallerFactory =
		// Configuration.getMarshallerFactory();
		// Marshaller marshaller1 =
		// marshallerFactory.getMarshaller(assertion);
		// Element assertionElement = marshaller1.marshall(assertion);
		// Element assertionElement = (Element) result.item(0);
		samlResponse.validate(true);
		Signature signature = samlResponse.getSignature();
		SAMLSignatureProfileValidator pv = new SAMLSignatureProfileValidator();
		pv.validate(signature);
		BasicX509Credential credential = new BasicX509Credential();
		credential.setEntityCertificate(certificate);
		SignatureValidator sigValidator = new SignatureValidator(credential);
		sigValidator.validate(signature);
	}
}