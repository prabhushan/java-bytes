package com.drfirst.dfsamllib.saml20;

public class KeyStoreInfo {
  String keyStoreFile, keyStorePassword, keyAlias, keyPassword;
  public KeyStoreInfo(String keyStore, String keyStorePassword, String keyAlias, String keyPassword) {
    this.keyStoreFile = keyStore;
    this.keyStorePassword = keyStorePassword;
    this.keyAlias = keyAlias;
    this.keyPassword = keyPassword;
  }

  public void setKeyStoreFile(String keyStore) {
    this.keyStoreFile = keyStore;
  }

  public String getKeyStoreFile() {
    return keyStoreFile;
  }

  public void setKeyStorePassword(String keyStorePassword) {
    this.keyStorePassword = keyStorePassword;
  }

  public String getKeyStorePassword() {
    return keyStorePassword;
  }

  public void setKeyAlias(String keyAlias) {
    this.keyAlias = keyAlias;
  }

  public String getKeyAlias() {
    return keyAlias;
  }

  public void setKeyPassword(String keyPassword) {
    this.keyPassword = keyPassword;
  }

  public String getKeyPassword() {
    return keyPassword;
  }
}
