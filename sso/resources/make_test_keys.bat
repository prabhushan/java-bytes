REM generate first test key
keytool -genkeypair -keyalg rsa -keystore /Users/prabhu/Desktop/prabhu/learning/homework/sso/resources/testkeys.jks -alias dfsamltest1 -validity 1080 -keysize 2048 -storepass pass123 -keypass pass123

REM export certificte into file
keytool -export -keystore /Users/prabhu/Desktop/prabhu/learning/homework/sso/resources/testkeys.jks -storepass pass123 -alias dfsamltest1 -rfc -file /Users/prabhu/Desktop/prabhu/learning/homework/sso/resources/dfsamltest1.rfc

REM generate first test key
keytool -genkeypair -keyalg rsa -keystore /Users/prabhu/Desktop/prabhu/learning/homework/sso/resources/testkeys.jks -alias dfsamltest2 -validity 1080 -keysize 2048 -storepass pass123 -keypass pass123

REM export certificte into file
keytool -export -keystore /Users/prabhu/Desktop/prabhu/learning/homework/sso/resources/testkeys.jks -storepass pass123 -alias dfsamltest2 -rfc -file /Users/prabhu/Desktop/prabhu/learning/homework/sso/resources/dfsamltest2.rfc