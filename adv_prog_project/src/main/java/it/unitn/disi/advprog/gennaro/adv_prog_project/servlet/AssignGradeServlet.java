package it.unitn.disi.advprog.gennaro.adv_prog_project.servlet;

import com.google.gson.*;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.ec.CustomNamedCurves;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMException;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import it.unitn.disi.advprog.gennaro.adv_prog_project.dto.TeacherDto;
import it.unitn.disi.advprog.gennaro.adv_prog_project.managers.StudentManagerBean;
import it.unitn.disi.advprog.gennaro.adv_prog_project.managers.TeacherManagerBean;
import jakarta.ejb.EJB;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;

import java.io.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;

public class AssignGradeServlet extends HttpServlet {
    @EJB
    TeacherManagerBean teacherManagerBean;
    @EJB
    StudentManagerBean studentManagerBean;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Read JSON data from the request body
        StringBuilder requestBody = new StringBuilder();
        try (BufferedReader reader = request.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                requestBody.append(line);
            }
        }

        // Parse JSON data using Gson library
        Gson gson = new Gson();
        JsonObject jsonData = gson.fromJson(requestBody.toString(), JsonObject.class);

        // print json data
        System.out.println(jsonData);

        JsonObject jsonMessage = jsonData.get("message").getAsJsonObject();

        int studentId = Integer.parseInt(jsonMessage.get("studentId").getAsString());
        int grade = Integer.parseInt(jsonMessage.get("grade").getAsString());
        int courseId = Integer.parseInt(jsonMessage.get("courseId").getAsString());

        TeacherDto teacherDto = teacherManagerBean.getTeacherByCourse(courseId);

        boolean isValid = validateRequest(
                jsonData,
                teacherDto
        );

        // Set the grade for the student using the TeacherManagerBean
        teacherManagerBean.setStudentGrade(
                studentManagerBean.getStudent(studentId),
                courseId,
                grade
        );

        // Respond with a JSON message
        JsonObject jsonResponse = new JsonObject();
        jsonResponse.addProperty("message", "Grade assigned successfully");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print(jsonResponse);
        out.flush();
    }

    private boolean validateRequest(JsonObject jsonData, TeacherDto teacherDto){
        /*
        const jsonData = {
            message:{
                timestamp: Date.now(),
                studentId: studentId,
                courseId: courseId,
                grade: grade,
            }
            challenge:challenge,
            signature:signature
        };
        * */

        // private key is in pkcs8 format
        String pemPrivateKey = "-----BEGIN PRIVATE KEY-----\n" +
                "MIGHAgEAMBMGByqGSM49AgEGCCqGSM49AwEHBG0wawIBAQQgr3Mf4CKBPfodhfGQ\n" +
                "xdRUJzK2xbF1VzGYibwjh3DflSuhRANCAAQma8CLQRY+CJrIX4rH8B3nDl7A8YZc\n" +
                "4WCWfkwlXcM1egQeeuBVzvBNuR64M9TyzJc5YL5uWi0g9RJ2ItQ5i9a6\n" +
                "-----END PRIVATE KEY-----";

        String pemPublicKey = "-----BEGIN PUBLIC KEY-----\n" +
                "MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAEJmvAi0EWPgiayF+Kx/Ad5w5ewPGG\n" +
                "XOFgln5MJV3DNXoEHnrgVc7wTbkeuDPU8syXOWC+blotIPUSdiLUOYvWug==\n" +
                "-----END PUBLIC KEY-----";
        try {
            //String privateKeyPEMContent = pemPrivateKey.replace("-----BEGIN PRIVATE KEY-----", "")
                    //.replace("-----END PRIVATE KEY-----", "")
                    //.replaceAll("\\s", "")
                    //.replaceAll("\\n", "");

            String publicKeyPEMContent = pemPublicKey.replace("-----BEGIN PUBLIC KEY-----", "")
                    .replace("-----END PUBLIC KEY-----", "")
                    .replaceAll("\\s", "")
                    .replaceAll("\\n", "");

            //KeyFactory keyFactory = KeyFactory.getInstance("EC");

            //convert pkcs8 to private key
            //byte[] encodedPrivateKey = Base64.getDecoder().decode(privateKeyPEMContent);
            //PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(encodedPrivateKey);
            //PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);

            //load pem public key
            byte[] encodedPublicKey = Base64.getDecoder().decode(publicKeyPEMContent);
            X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(encodedPublicKey);
            KeyFactory keyFactory = KeyFactory.getInstance("EC");
            PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);


            // print private key
            //System.out.println(Arrays.toString(privateKey.getEncoded()));

            String challenge = jsonData.get("challenge").getAsString();
            byte[] challengeBytes = challenge.getBytes(StandardCharsets.UTF_8);

            System.out.println("challengeBytes " + Arrays.toString(challengeBytes));

            //signature
            byte[] p1363EncodedSignature = Base64.getDecoder().decode(jsonData.get("signature").getAsString());
            byte[] asn1EncodedSignature = toASN1(p1363EncodedSignature);

            //sign
            Signature ecdsasignature = Signature.getInstance("SHA256withECDSA");
            //ecdsasignature.initSign(privateKey);
            //ecdsasignature.update(challengeBytes);
            //byte[] signature = ecdsasignature.sign();
            //System.out.println("JSON " + jsonData.get("signature").getAsString());
            //System.out.println(Arrays.toString(signature));
            //verify
            ecdsasignature.initVerify(publicKey);
            ecdsasignature.update(challenge.getBytes());;
            if (ecdsasignature.verify(asn1EncodedSignature)){
                System.out.println("signature verified");
                return true;
            }else{
                System.out.println("signature verification failed");
                return false;
            }
        } catch (
                NoSuchAlgorithmException | InvalidKeyException | SignatureException | IOException |
                InvalidKeySpecException e
        ) {
            throw new RuntimeException(e);
        }
    }

    private static byte[] toASN1(byte[] p1363EncodedSignature) throws IOException {
        int n = p1363EncodedSignature.length / 2;
        BigInteger r = new BigInteger(+1, Arrays.copyOfRange(p1363EncodedSignature, 0, n));
        BigInteger s = new BigInteger(+1, Arrays.copyOfRange(p1363EncodedSignature, n, n * 2));
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(new ASN1Integer(r));
        v.add(new ASN1Integer(s));
        return new DERSequence(v).getEncoded();
    }

}



/*
* import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.ec.CustomNamedCurves;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.signers.ECDSASigner;
import org.bouncycastle.crypto.signers.HMacDSAKCalculator;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Security;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AssignGradeServlet")
public class AssignGradeServlet extends HttpServlet {
    static {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the JSON data from the request
        String jsonData = request.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);

        // Parse JSON data
        Gson gson = new Gson();
        GradeData gradeData = gson.fromJson(jsonData, GradeData.class);

        // Verify the ECDSA signature
        boolean isSignatureValid = verifyECDSASignature(gradeData.signature, gradeData.challenge, gradeData);

        if (isSignatureValid) {
            // Signature is valid, process the grade
            // ...
            // Send the response
            PrintWriter out = response.getWriter();
            out.println("{\"message\": \"Signature verification successful. Grade processed.\"}");
        } else {
            // Signature is not valid
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            PrintWriter out = response.getWriter();
            out.println("{\"message\": \"Signature verification failed.\"}");
        }
    }

    private boolean verifyECDSASignature(byte[] signatureBytes, String challenge, GradeData gradeData) {
        try {
            byte[] challengeDataBytes = challenge.getBytes();

            // Decode public key from JWK format
            ECDomainParameters ecParams = CustomNamedCurves.getByName("secp256r1");
            X9ECParameters x9 = new X9ECParameters(ecParams.getCurve(), ecParams.getG(), ecParams.getN(), ecParams.getH());
            ECPublicKeyParameters publicKeyParameters = new ECPublicKeyParameters(
                    x9.getCurve().decodePoint(Base64.getDecoder().decode(gradeData.publicKey.x + gradeData.publicKey.y)), ecParams);

            // Create ECDSA signer
            ECDSASigner signer = new ECDSASigner(new HMacDSAKCalculator(new SHA256Digest()));

            signer.init(false, publicKeyParameters);

            // Prepare signature
            byte[] rBytes = new byte[32];
            byte[] sBytes = new byte[32];

            System.arraycopy(signatureBytes, 0, rBytes, 0, 32);
            System.arraycopy(signatureBytes, 32, sBytes, 0, 32);

            BigInteger r = new BigInteger(1, rBytes);
            BigInteger s = new BigInteger(1, sBytes);

            // Verify the signature
            return signer.verifySignature(challengeDataBytes, r, s);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

* */
