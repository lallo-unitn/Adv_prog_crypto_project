import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class MariaDBSSLTest {

    public static void main(String[] args) {
        // JDBC connection parameters
        String jdbcUrl = "jdbc:mariadb://localhost:3306/universityDatabase";
        String username = "root";
        String password = "root";

        // SSL certificate paths (if needed)
        //String clientKeystorePath = "/path/to/client-keystore.jks";
        //String clientKeystorePassword = "your-keystore-password";
        String truststorePath = "truststore.jks";
        String truststorePassword = "password";

        // Connection properties
        Properties properties = new Properties();
        properties.setProperty("user", username);
        properties.setProperty("password", password);
        properties.setProperty("useSSL", "true");
        properties.setProperty("requireSSL", "true");
        properties.setProperty("verifyServerCertificate", "true");

        /*if (clientKeystorePath != null && !clientKeystorePath.isEmpty()) {
            properties.setProperty("clientCertificateKeyStoreUrl", "file:" + clientKeystorePath);
            properties.setProperty("clientCertificateKeyStorePassword", clientKeystorePassword);
            properties.setProperty("clientCertificateKeyStoreType", "JKS");
        }*/

        /*if (truststorePath != null && !truststorePath.isEmpty()) {
            properties.setProperty("trustCertificateKeyStoreUrl", "file:" + truststorePath);
            properties.setProperty("trustCertificateKeyStorePassword", truststorePassword);
            properties.setProperty("trustCertificateKeyStoreType", "JKS");
        }*/

        // Database operations
        try (Connection connection = DriverManager.getConnection(jdbcUrl, properties)) {
            // Execute a simple query
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery("SELECT 'Hello, World!'")) {
                while (resultSet.next()) {
                    String result = resultSet.getString(1);
                    System.out.println("Query Result: " + result);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
