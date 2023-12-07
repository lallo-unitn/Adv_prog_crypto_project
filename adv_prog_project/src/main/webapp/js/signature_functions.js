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
    return bufView;
}

async function importPrivateKey() {
    const pemKey = "-----BEGIN PRIVATE KEY-----\n" +
        "MIGHAgEAMBMGByqGSM49AgEGCCqGSM49AwEHBG0wawIBAQQgr3Mf4CKBPfodhfGQ\n" +
        "xdRUJzK2xbF1VzGYibwjh3DflSuhRANCAAQma8CLQRY+CJrIX4rH8B3nDl7A8YZc\n" +
        "4WCWfkwlXcM1egQeeuBVzvBNuR64M9TyzJc5YL5uWi0g9RJ2ItQ5i9a6\n" +
        "-----END PRIVATE KEY-----";

    const pemHeader = "-----BEGIN PRIVATE KEY-----";
    const pemFooter = "-----END PRIVATE KEY-----";

    // Extract the key from PEM format
    const pemBody = pemKey
        .replace(pemHeader, '')
        .replace(pemFooter, '')
        .replace(/\s/g, '')
        .replace(/\n/g, '');

    console.log('Private key:', pemBody);

    // Convert from Base64 to binary
    const binaryDer = window.atob(pemBody);

    // Convert from binary to ArrayBuffer
    const binaryDerLength = binaryDer.length;
    const arrayBuffer = new ArrayBuffer(binaryDerLength);
    const uint8Array = new Uint8Array(arrayBuffer);

    // Fill the ArrayBuffer with data
    for (let i = 0; i < binaryDerLength; i++) {
        // Convert to integer in range [0,255]
        uint8Array[i] = binaryDer.charCodeAt(i);
    }

    console.log('Private key in DER format:', uint8Array);

    // Import private key from openssl format to WebCrypto format
    const importedKey = await window.crypto.subtle.importKey(
        "pkcs8",
        uint8Array,
        {
            name: "ECDSA",
            namedCurve: "P-256",
            hash: { name: "SHA-256" },
        },
        true,
        ["sign"],
    );

    console.log('Private key imported successfully:', importedKey);
    return importedKey;
}

async function sendGrade(courseId) {
    // Get user input values
    const studentId = document.getElementById("studentId").value;
    const grade = document.getElementById("grade").value;

    const jsonData = {
        message:{
            timestamp: Date.now(),
            studentId: studentId,
            courseId: courseId,
            grade: grade,
        }
        //challenge
        //signature
    };

    const concat = ""+
        jsonData.message.timestamp +
        jsonData.message.studentId +
        jsonData.message.courseId +
        jsonData.message.grade;

    jsonData.challenge = concat;

    console.log('Challenge:', new TextEncoder("utf-8").encode(concat));

    // Import private key
    const privateKey = await importPrivateKey();
    // Sign data
    const signature = await crypto.subtle.sign(
            {
                name: "ECDSA",
                namedCurve: "P-256",
                hash: { name: "SHA-256" },
            },
            privateKey,
            new TextEncoder("utf-8").encode(concat),
    );
    console.log('Signature:', signature);

    // Convert the signature to a Base64-encoded string
    const signatureArray = new Uint8Array(signature);
    jsonData.signature = btoa(String.fromCharCode.apply(null, signatureArray));

    console.log(jsonData);
    console.log(jsonData.challenge);
    console.log(jsonData.signature);

    const xhr = new XMLHttpRequest();
    const url = "AssignGradeServlet";
    xhr.open("POST", url, true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            const json = JSON.parse(xhr.responseText);
            alert(json.message);
        }
    };
    const data = JSON.stringify(jsonData);
    xhr.send(data);
}