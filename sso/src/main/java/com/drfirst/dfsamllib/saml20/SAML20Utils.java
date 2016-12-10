package com.drfirst.dfsamllib.saml20;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import java.security.cert.Certificate;
import javax.xml.namespace.QName;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.opensaml.Configuration;
import org.opensaml.DefaultBootstrap;
import org.opensaml.xml.ConfigurationException;
import org.opensaml.xml.XMLObject;

import org.w3c.dom.Node;

public class SAML20Utils {
	public static boolean CHECK_CERT=true;
	public static boolean DONT_CHECK_CERT=false;
	static Logger logger = Logger.getLogger(SAML20Utils.class);
	
	static {
		try {
			DefaultBootstrap.bootstrap();
		} catch (ConfigurationException e) {
			logger.fatal("cannot bootstrap SAML20 ", e);
		}
	}

	static public XMLObject createSamlObject(QName qname) {
		return Configuration.getBuilderFactory().getBuilder(qname)
				.buildObject(qname);
	}

	static private TransformerFactory createTransformerFactory() {

		TransformerFactory tfactory = TransformerFactory.newInstance();
		return tfactory;

	}

	static public String nodeToString(Node node, boolean indent)
			throws Exception {

		StringWriter sw = new StringWriter();
		TransformerFactory tfactory = createTransformerFactory();
		Transformer transformer = tfactory.newTransformer();
		if (indent) {
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");

			transformer.setOutputProperty(
					"{http://xml.apache.org/xslt}indent-amount", "2");
		}
		transformer.transform(new DOMSource(node), new StreamResult(sw));
		sw.close();
		return sw.toString();
	}

	static public X509Certificate loadCertificate(String base64CertString,
			boolean checkCert) throws CertificateException, IOException {
		CertificateFactory cf = CertificateFactory.getInstance("X.509");
		ByteArrayInputStream in = new ByteArrayInputStream(
				base64CertString.getBytes());
		try {
			X509Certificate cert = (X509Certificate) cf.generateCertificate(in);
			if (checkCert)
				cert.checkValidity();
			return cert;
		} finally {
			in.close();
		}
	}

	static public String readFile(String filename) throws IOException {
		String content = null;
		File file = new File(filename); // for ex foo.txt
		FileReader reader = null;
		try {
			reader = new FileReader(file);
			char[] chars = new char[(int) file.length()];
			reader.read(chars);
			content = new String(chars);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
		return content;
	}

	public static X509Certificate readCertFromFile(String certFileFullPath,
			boolean checkCert) throws Exception {
		String certData = readFile(certFileFullPath);
		return readCertificate(new File(certFileFullPath));
		//return readCertFromString(certData, checkCert);
	}
	public static X509Certificate readCertFromString(String base64CertString,
			boolean checkCert) throws IOException, CertificateException {
		CertificateFactory cf = CertificateFactory.getInstance("X509");
		X509Certificate cert = (X509Certificate) cf
				.generateCertificate(new ByteArrayInputStream(base64CertString.getBytes()));
		if (checkCert)
			cert.checkValidity();
		return cert;
	}
	
	public static X509Certificate readCertificate(File f) throws Exception {
		CertificateFactory cf = CertificateFactory.getInstance("X.509");

		// Use BufferedInputStream (which supports mark and reset) so that each 
		// generateCertificate call consumes one certificate.
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(f));
		//while (in.available() > 0) {
			X509Certificate cert = (X509Certificate) cf.generateCertificate(in);
		  //  System.out.println("Cert:\n===================\n" + cert.toString() + "\n");
		//}
		in.close();
		return cert;
	}
}
