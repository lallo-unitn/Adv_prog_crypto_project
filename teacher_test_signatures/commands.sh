# Generate ECDSA private key (named private_key.pem)
openssl ecparam -genkey -name prime256v1 -out private_key.pem

# Extract the public key from the private key
openssl ec -in private_key.pem -pubout -out public_key.pem

# Convert the private key to PKCS#8 format
openssl pkcs8 -topk8 -inform PEM -outform PEM -in private_key.pem -out private_key_pkcs8.pem -nocrypt

