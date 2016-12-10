package com.drfirst.dfsamllib.saml20;

import java.util.HashMap;

public class SAMLData {
	String subject;
	String issuer;
	String audience;
	HashMap<String, String> assertedData;

	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getIssuer() {
		return issuer;
	}
	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}
	public String getAudience() {
		return audience;
	}
	public void setAudience(String audience) {
		this.audience = audience;
	}
	public HashMap<String, String> getAssertedData() {
		return assertedData;
	}
	public void setAssertedData(HashMap<String, String> assertedData) {
		this.assertedData = assertedData;
	}
}
