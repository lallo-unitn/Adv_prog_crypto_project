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

function str2ab(str) {
    const buf = new ArrayBuffer(str.length);
    const bufView = new Uint8Array(buf);
    for (let i = 0, strLen = str.length; i < strLen; i++) {
        bufView[i] = str.charCodeAt(i);
    }
    return buf;
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
    const pem = privateKeyPEM.replace(/\n/g, '');
    const pemHeader = "-----BEGIN EC PRIVATE KEY-----";
    const pemFooter = "-----END EC PRIVATE KEY-----";
    const pemContents = pem.substring(
        pemHeader.length,
        pem.length - pemFooter.length,
    );
    // base64 decode the string to get the binary data
    const binaryDerString = window.atob(pemContents);
    // convert from a binary string to an ArrayBuffer
    const binaryDer = str2ab(binaryDerString);

    // Import private key from openssl format to WebCrypto format
    const privateKey = await crypto.subtle.importKey(
        "pkcs8",
        binaryDer,
        {
            name: "ECDSA",
            namedCurve: "P-256",
        },
        true,
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