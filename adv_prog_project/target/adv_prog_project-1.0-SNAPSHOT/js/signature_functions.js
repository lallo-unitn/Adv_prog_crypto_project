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

function base64StringToArrayBuffer(b64str) {
    const byteStr = atob(b64str);
    const bytes = new Uint8Array(byteStr.length);
    for (let i = 0; i < byteStr.length; i++) {
        bytes[i] = byteStr.charCodeAt(i);
    }
    return bytes.buffer;
}

async function importPrivateKey() {
    const privateKeyString = localStorage.getItem("privateKey");
    const privateKeyJson = JSON.parse(privateKeyString);
    if (!privateKeyJson) {
        console.error("Private key not found in local storage");
        return;
    }

    const privateKeyJsonComplete = {
        crv: privateKeyJson.crv,
        d: privateKeyJson.d,
        ext: true,
        key_ops: ["sign"],
        kty: privateKeyJson.kty,
        x: privateKeyJson.x,
        y: privateKeyJson.y,
    };

    // Import private key from openssl format to WebCrypto format
    const importedKey = await window.crypto.subtle.importKey(
        "jwk",
        privateKeyJsonComplete,
        {
            name: "ECDSA",
            namedCurve: "P-256",
        },
        true,
        ["sign"],
    );

    console.log('Private key imported successfully:', importedKey);
    return importedKey;
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
    const jsonDataBytes = new TextEncoder().encode(
            JSON.stringify(jsonData)
        );
    // Import private key
    const privateKey = await importPrivateKey();
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
    console.log(jsonData);
    // $.ajax({
    //     type: "POST",
    //     url: "adv_prog_project-1.0-SNAPSHOT/AuthServlet",
    //     dataType: "json",
    //     data: JSON.stringify(jsonData),
    //     success: function (msg) {
    //         if (msg) {
    //             alert("Sent!");
    //             location.reload(true);
    //         } else {
    //             alert("Not sent!");
    //         }
    //     },
    // });

    const xhr = new XMLHttpRequest();
    const url = "AssignGradeServlet";
    xhr.open("POST", url, true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            console.log(xhr.status);
        }
    };
    const data = JSON.stringify(jsonData);
    xhr.send(data);
}