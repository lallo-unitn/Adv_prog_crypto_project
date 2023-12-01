package it.unitn.disi.advprog.gennaro.adv_prog_project.beans;

import it.unitn.disi.advprog.gennaro.adv_prog_project.dto.UserAccountDto;
import it.unitn.disi.advprog.gennaro.adv_prog_project.entities.Student;
import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionManagement;
import jakarta.ejb.TransactionManagementType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.jboss.logging.Logger;

@Local
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class StudentBean {
    // Logger for logging messages
    private static final Logger logger = Logger.getLogger(StudentBean.class);

    // Entity manager for interacting with the database
    @PersistenceContext(unitName = "default")
    private EntityManager entityManager;

    /**
     * Retrieves a student entity based on the provided matriculation number.
     *
     * @param id The matriculation number of the student to retrieve.
     * @return The student entity associated with the provided matriculation number.
     */
    public Student getStudentByMatriculation(int id) {
        // Logging an informational message
        logger.info("Retrieving student [ " + id + " ] info");

        // Constructing a JPQL query to select a student based on the matriculation number
        TypedQuery<Student> query = this.entityManager.createQuery(
                "SELECT s FROM Student s WHERE s.id = :id", Student.class
        );

        // Setting the parameter for the matriculation number
        query.setParameter("id", id);

        Student student = null;
        try {
            // Execute the query and retrieve the single student entity
            student = query.getSingleResult();
        } catch (NoResultException e) {
            // Log a message if the student is not found
            logger.info("Student [ " + id + " ] is not registered");
            return null;
        }
        return student;
    }

    public Student getStudentByUserAccount(UserAccountDto userAccountDto) {
        // Logging an informational message
        logger.info("Retrieving student [ " + userAccountDto.toString() + " ]");

        // Constructing a JPQL query to select a student based on the matriculation number
        TypedQuery<Student> query = this.entityManager.createQuery(
                "SELECT s FROM Student s WHERE s.userAccount.username = :username", Student.class
        );

        // Setting the parameter for the matriculation number
        query.setParameter("username", userAccountDto.getUsername());

        Student student = null;
        try {
            // Execute the query and retrieve the single student entity
            student = query.getSingleResult();
        } catch (NoResultException e) {
            // Log a message if the student is not found
            logger.info("Student [ " + userAccountDto.toString() + " ] is not registered");
            return null;
        }
        return student;
    }
}
