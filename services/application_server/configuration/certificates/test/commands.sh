keytool -genkeypair -alias localhost -keyalg EC -groupname secp256r1 -sigalg SHA256withECDSA -validity 365 -keystore server.keystore -dname "cn=localhost,o=Acme,c=IT" -keypass reallyStrongPassword -storepass reallyStrongPassword
