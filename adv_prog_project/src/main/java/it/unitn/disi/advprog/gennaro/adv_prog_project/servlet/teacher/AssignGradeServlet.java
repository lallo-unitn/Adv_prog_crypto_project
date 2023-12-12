package it.unitn.disi.advprog.gennaro.adv_prog_project.servlet.teacher;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import it.unitn.disi.advprog.gennaro.adv_prog_project.dto.TeacherDto;
import it.unitn.disi.advprog.gennaro.adv_prog_project.managers.StudentManager;
import it.unitn.disi.advprog.gennaro.adv_prog_project.managers.TeacherManager;
import jakarta.ejb.EJB;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.DERSequence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;

public class AssignGradeServlet extends HttpServlet {
    @EJB
    TeacherManager teacherManager;
    @EJB
    StudentManager studentManager;

    // Convert a javascript P1363 encoded signature to ASN.1 format
    private static byte[] toASN1(byte[] p1363EncodedSignature) throws IOException {
        int n = p1363EncodedSignature.length / 2;
        BigInteger r = new BigInteger(+1, Arrays.copyOfRange(p1363EncodedSignature, 0, n));
        BigInteger s = new BigInteger(+1, Arrays.copyOfRange(p1363EncodedSignature, n, n * 2));
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(new ASN1Integer(r));
        v.add(new ASN1Integer(s));
        return new DERSequence(v).getEncoded();
    }

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

        TeacherDto teacherDto = teacherManager.getTeacherByCourse(courseId);
        String jsonReturnMessage;
        if(validateRequest(jsonData, teacherDto)){
            jsonReturnMessage = "Grade assigned successfully";
            // Set the grade for the student using the TeacherManager
            teacherManager.setStudentGrade(studentManager.getStudent(studentId), courseId, grade);
        } else {
            jsonReturnMessage = "Grade was not assigned";
        }

        // Respond with a JSON message
        JsonObject jsonResponse = new JsonObject();
        jsonResponse.addProperty("message", jsonReturnMessage);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print(jsonResponse);
        out.flush();
    }

    private boolean validateRequest(JsonObject jsonData, TeacherDto teacherDto) {

        String pemPublicKey = teacherDto.getEccPublicKey();

        try {

            String publicKeyPEMContent = pemPublicKey.replace("-----BEGIN PUBLIC KEY-----", "").replace("-----END PUBLIC KEY-----", "").replaceAll("\\s", "").replaceAll("\\n", "");

            //load pem public key
            byte[] encodedPublicKey = Base64.getDecoder().decode(publicKeyPEMContent);
            X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(encodedPublicKey);
            KeyFactory keyFactory = KeyFactory.getInstance("EC");
            PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);

            String challenge = jsonData.get("challenge").getAsString();
            byte[] challengeBytes = challenge.getBytes(StandardCharsets.UTF_8);

            System.out.println("challengeBytes " + Arrays.toString(challengeBytes));

            //signature
            byte[] p1363EncodedSignature = Base64.getDecoder().decode(jsonData.get("signature").getAsString());
            byte[] asn1EncodedSignature = toASN1(p1363EncodedSignature);

            //sign
            Signature ecdsasignature = Signature.getInstance("SHA256withECDSA");

            //verify
            ecdsasignature.initVerify(publicKey);
            ecdsasignature.update(challenge.getBytes());
            if (ecdsasignature.verify(asn1EncodedSignature)
                    && checkJsonValues(jsonData)
                    && checkTimestamp(jsonData)
            ){
                System.out.println("signature verified");
                return true;
            } else {
                System.out.println("signature verification failed");
                return false;
            }
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException | IOException |
                 InvalidKeySpecException e) {
            return false;
        }
    }

/*
     *  check if timestamp is not older than 10 seconds
     */
    private boolean checkTimestamp(JsonObject jsonData) {

        JsonObject jsonMessage = jsonData.get("message").getAsJsonObject();
        String timestamp = jsonMessage.get("timestamp").getAsString();
        long timestampLong = Long.parseLong(timestamp);
        long currentTime = System.currentTimeMillis();
        long difference = currentTime - timestampLong;
        System.out.println("difference " + difference);
        return difference < 10000;
    }

    /*
     *  check if field challenge in json is equal to the concatenation of fileds
     *  jsonData.message.timestamp jsonData.message.studentId jsonData.message.courseId jsonData.message.grade;
     */
    private boolean checkJsonValues(JsonObject jsonData) {

        JsonObject jsonMessage = jsonData.get("message").getAsJsonObject();

        String timestamp = jsonMessage.get("timestamp").getAsString();
        String studentId = jsonMessage.get("studentId").getAsString();
        String courseId = jsonMessage.get("courseId").getAsString();
        String grade = jsonMessage.get("grade").getAsString();

        String challenge = jsonData.get("challenge").getAsString();
        String message = timestamp + studentId + courseId + grade;
        return challenge.equals(message);
    }

}