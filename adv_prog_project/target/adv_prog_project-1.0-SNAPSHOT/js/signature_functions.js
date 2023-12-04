function init() {
    document.getElementById('inputfile')
        .addEventListener('change', function () {

            let fr = new FileReader();
            fr.onload = function () {
                document.getElementById('output')
                    .textContent = fr.result;
                localStorage.setItem('privateKey', fr.result);
                document.getElementById('outputKey')
                    .textContent = localStorage.getItem('privateKey');
            }

            fr.readAsText(this.files[0]);
        })
}

async function sendGrade() {
    // Get user input values
    const studentId = document.getElementById("studentId").value;
    const grade = document.getElementById("grade").value;
    const jsonData = {
        timestamp: Date.now(),
        studentId: studentId,
        grade: grade,
    };
    const privateKeyPEM = localStorage.getItem("privateKey");
    if (!privateKeyPEM) {
        console.error("Private key not found in local storage");
        return;
    }
    const cleanedBase64 = privateKeyPEM.replace(/\n/g, '');
    const match = cleanedBase64.match(/-----BEGIN EC PRIVATE KEY-----(.+)-----END EC PRIVATE KEY-----/s);
    console.log(match);
    let decodedPrivateKey
    if (match && match[1]) {
        const base64Encoded = match[1].trim();
        console.log(base64Encoded);
        // Decode the Base64-encoded string
        decodedPrivateKey = atob(base64Encoded);
        console.log(decodedPrivateKey);
    } else {
        console.error("Invalid base64 key format");
    }

    // Import private key from openssl format to WebCrypto format
    const privateKey = await crypto.subtle.importKey(
        "pkcs8",
        new TextEncoder().encode(decodedPrivateKey).buffer,
        {
            name: "ECDSA",
            namedCurve: "P-256",
        },
        false,
        ["sign"]
    );
    const jsonDataBytes = new TextEncoder().encode(
            JSON.stringify(jsonData)
        );
    // Sign data
    const signature = await crypto.subtle.sign(
        {
            name: "ECDSA",
            hash: { name: "SHA-256" },
        },
        privateKey,
        jsonDataBytes
    );
    jsonData.signature = new Uint8Array(signature);
    const response = await fetch("/AssignGradeServlet", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(jsonData),
    });
    const responseData = await response.json();
    console.log('Server Response:', responseData);
}