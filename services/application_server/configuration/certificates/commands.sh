openssl ecparam -genkey -name secp384r1 -out rootCA.key

openssl req -x509 -new -nodes -key rootCA.key -sha256 -days 3650 -out rootCA.crt -subj "/C=IT/ST=Italy/L=Trento/O=CRYPTOUNITN/CN=CRYPTO"

openssl req -new -nodes -keyout server.key -out server.csr -subj "/C=IT/ST=Italy/L=Trento/O=CRYPTOUNITN/CN=cryptoDomain.it"

openssl x509 -req -in server.csr -CA rootCA.crt -CAkey rootCA.key -CAcreateserial -out server.crt -days 365 -sha256 -extfile <(printf "subjectAltName=DNS:cryptoDomain.it")

#wildfly needs java keystore in format jks

openssl pkcs12 -export -inkey server.key -in server.crt -out server.p12 -name server -passout pass:reallyStrongPassword

#importing the pkcs12 keystore in a jks keystore

keytool -importkeystore -destkeystore server.keystore -srckeystore server.p12 -srcstoretype PKCS12 -alias server -deststorepass reallyStrongPassword -srcstorepass reallyStrongPassword

#in the server CLI, add the keystore to the security realm

/subsystem=elytron/key-store=demoKeyStore:add(path=certificates/server.keystore,relative-to=jboss.server.config.dir, credential-reference={clear-text=reallyStrongPassword},type=JKS)

#set credentials for demoKeyStore and add to server KeyStore Manager

/subsystem=elytron/key-manager=demoKeyManager:add(key-store=demoKeyStore,credential-reference={clear-text=reallyStrongPassword})

#set TLS version to use
/subsystem=elytron/server-ssl-context=demoSSLContext:add(key-manager=demoKeyManager,protocols=["TLSv1.3"])

/subsystem=undertow/server=default-server/https-listener=https:write-attribute(name=ssl-context,value=demoSSLContext)

/subsystem=elytron/server-ssl-context=demoSSLContext:write-attribute(name=cipher-suite-names,value=TLS_AES_256_GCM_SHA384:TLS_CHACHA20_POLY1305_SHA256:TLS_AES_128_GCM_SHA256)
