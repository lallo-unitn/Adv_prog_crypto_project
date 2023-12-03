package it.unitn.disi.advprog.gennaro.adv_prog_project.beans;

import it.unitn.disi.advprog.gennaro.adv_prog_project.entities.Course;
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
public class CourseBean {

    // Logger for logging messages
    private static final org.jboss.logging.Logger logger = Logger.getLogger(TeacherBean.class);

    // Entity manager for interacting with the database
    @PersistenceContext(unitName = "default")
    private EntityManager entityManager;

    public Course getCourseById(int id) {
        // Logging an informational message
        logger.info("Retrieving course [ " + id + " ] info");

        // Constructing a JPQL query to select a course based on the id
        TypedQuery<Course> query = this.entityManager.createQuery(
                "SELECT c FROM Course c WHERE c.id = :id", Course.class
        );

        // Setting the parameter for the id
        query.setParameter("id", id);

        Course course = null;
        try {
            // Execute the query and retrieve the single course entity
            course = query.getSingleResult();
        } catch (NoResultException e) {
            // Log a message if the course is not found
            logger.info("Course [ " + id + " ] is not registered");
            return null;
        }
        return course;
    }

}
