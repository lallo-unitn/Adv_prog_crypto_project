# Esse3-Bootleg Project

## Project Description

Esse3-Bootleg is a JavaEE web application developed to assist teachers in assigning grades to their students. The application includes functionalities for students and administrators, offering a secure, user-friendly platform for managing academic information. The project was developed as part of the Advanced Programming of Cryptographic Methods course and aims to provide secure data handling, utilizing both data-at-rest and data-in-transit security mechanisms. The entire system is dockerized for ease of deployment and testing.

## Functional Requirements

- **Login:** Users can log in with a username and password. Unauthorized users are only able to access the login page.
- **Student Dashboard:** Students can view their personal details, the courses they are enrolled in, and their grades.
- **Teacher Dashboard:** Teachers can assign grades to students using a secure key-based mechanism.
- **Admin Dashboard:** Admins can add new students to the system by inputting user details.

## Security Features

- **Transport Layer Security (TLS):** All communications between the client, the application server, and the database are protected using TLSv1.3.
- **Data Encryption:** Data-at-rest in the database is encrypted using Transparent Data Encryption (TDE) with AES-128 in CTR mode.
- **Password Security:** Passwords are hashed and salted using Bcrypt with a work factor of 13.
- **Digital Signatures:** Teachers must sign grade assignments using ECDSA to ensure data integrity.

## Software Architecture

Esse3-Bootleg follows the MVC (Model-View-Controller) design pattern:

- **Controller Layer:** Handles the business logic, implemented via servlets that manage interactions between the model and view layers.
- **Model Layer:** Manages data persistence using Java Persistence API (JPA) and interacts with the database through enterprise beans.
- **View Layer:** Uses Java Server Pages (JSPs) to render the web interface, providing user interactions.

## Technological Stack

- **Java 17.0.9 with JavaEE v.9.1** for backend development.
- **WildFly v.30.0.0.Final** as the application server.
- **MariaDB v.11.2.2** for database management.
- **Docker & Docker-Compose** for containerized deployment.
- **Apache Maven** for building the project and managing dependencies.

## Installation and Execution

1. **Prerequisites:** Ensure Docker and Docker Compose are installed. Make sure ports 3306, 8080, 8443, and 9990 are available.
2. **Deployment:**
   - Unzip the project archive.
   - Navigate to the project directory and run: `docker-compose up --remove-orphans`.
   - Access the web application at: [https://0.0.0.0:8443/adv_prog_project_war/](https://0.0.0.0:8443/adv_prog_project_war/).
3. **Testing Roles:**
   - **Student:** Use credentials (user1, password1) to view the student dashboard.
   - **Teacher:** Use credentials (jane_smith, pass456) to assign grades.
   - **Admin:** Use credentials (admin, admin) to add new students.

## Known Limitations

- **WildFly Admin Console Security:** The admin console does not have TLS enabled, leaving administrative credentials vulnerable.
- **Development Configuration:** Default credentials are used for MariaDB and WildFly, which is not secure for production environments.
- **No Vault Integration for Key Management:** Keys are managed through file permissions rather than a dedicated key management solution.
- **No Mutual TLS Between WildFly and MariaDB:** Mutual TLS was not implemented due to documentation limitations.
- **Replay Attack Mitigation:** Currently, the digital signature system lacks adequate protection against replay attacks.

## Contact and Support

For any issues, please contact the project maintainers at the email addresses provided above.

---

Feel free to open issues or contribute by submitting a pull request if you have improvements or bug fixes to suggest!

