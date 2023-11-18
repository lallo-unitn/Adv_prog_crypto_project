package it.unitn.disi.advprog.gennaro.adv_prog_project.beans;

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
     * @param matriculation The matriculation number of the student to retrieve.
     * @return The student entity associated with the provided matriculation number.
     */
    public Student getStudentByMatriculation(int matriculation) {
        // Logging an informational message
        logger.info("Retrieving student [ " + matriculation + " ] info");

        // Constructing a JPQL query to select a student based on the matriculation number
        TypedQuery<Student> query = this.entityManager.createQuery(
                "SELECT s FROM Student s WHERE s.id = :matriculation", Student.class
        );

        // Setting the parameter for the matriculation number
        query.setParameter("matriculation", matriculation);

        Student student = null;
        try {
            // Execute the query and retrieve the single student entity
            student = query.getSingleResult();
        } catch (NoResultException e) {
            // Log a message if the student is not found
            logger.info("Student [ " + matriculation + " ] is not registered");
            return null;
        }
        return student;
    }
}
