
openssl ecparam -genkey -name prime256v1 -out ec-private-key.pem \
&& \
openssl req -new -key ec-private-key.pem -x509 -out ec-self-signed-cert.pem \
&& \
openssl ec -in ec-private-key.pem -pubout -out ec-public-key.pem \
&& \
cat ec-private-key.pem ec-self-signed-cert.pem ec-public-key.pem > ec-cert-and-key.pem \
&& \ 
openssl pkcs12 -export -out ec-keypair.p12 -inkey ec-private-key.pem -in ec-cert-and-key.pem \
&& \
keytool -importkeystore -srckeystore ec-keypair.p12 -srcstoretype PKCS12 -destkeystore server.keystore -deststoretype JKS

