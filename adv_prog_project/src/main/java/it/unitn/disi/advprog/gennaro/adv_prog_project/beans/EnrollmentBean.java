package it.unitn.disi.advprog.gennaro.adv_prog_project.beans;

import it.unitn.disi.advprog.gennaro.adv_prog_project.entities.Enrollment;
import it.unitn.disi.advprog.gennaro.adv_prog_project.entities.Student;
import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionManagement;
import jakarta.ejb.TransactionManagementType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

@Local
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class EnrollmentBean {
    // Logger for logging messages
    private static final org.jboss.logging.Logger logger = org.jboss.logging.Logger.getLogger(EnrollmentBean.class);

    // Entity manager for interacting with the database
    @PersistenceContext(unitName = "default")
    private EntityManager entityManager;

    /**
     * Retrieves a list of enrollments for the specified student.
     *
     * @param student The student for whom to retrieve enrollments.
     * @return A list of enrollments associated with the specified student.
     */
    public List<Enrollment> getEnrollmentByStudent(Student student) {
        // Constructing a JPQL query to select enrollments based on the student
        TypedQuery<Enrollment> query = entityManager.createQuery(
                "SELECT e FROM Enrollment e WHERE e.stMatriculation = :student", Enrollment.class
        );

        // Setting the parameter for the student
        query.setParameter("student", student);

        List<Enrollment> enrollmentList;
        try {
            // Execute the query and retrieve the list of enrollments
            enrollmentList = query.getResultList();
        } catch (NoResultException e) {
            // Log an error message and an info message if no enrollments are found
            logger.error("Error retrieving enrollments for student [ " + student.getId() + " ]", e);
            logger.info("Student [ " + student.getId() + " ] is not enrolled in any course");
            return null;
        }

        return enrollmentList;
    }
}
