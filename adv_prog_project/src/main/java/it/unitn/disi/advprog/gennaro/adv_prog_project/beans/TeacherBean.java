package it.unitn.disi.advprog.gennaro.adv_prog_project.beans;

import it.unitn.disi.advprog.gennaro.adv_prog_project.entities.Student;
import it.unitn.disi.advprog.gennaro.adv_prog_project.entities.Teacher;
import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionManagement;
import jakarta.ejb.TransactionManagementType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.jboss.logging.Logger;

import java.util.List;


@Local
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class TeacherBean {
    // Logger for logging messages
    private static final org.jboss.logging.Logger logger = Logger.getLogger(TeacherBean.class);

    // Entity manager for interacting with the database
    @PersistenceContext(unitName = "default")
    private EntityManager entityManager;

    /**
     * Retrieves a list of teachers who teach courses that the specified student is enrolled in.
     *
     * @param student The student for whom to find teachers.
     * @return A list of teachers associated with courses the student is enrolled in.
     */
    public List<Teacher> getTeacherByStudent(Student student) {
        // Constructing a JPQL query to select teachers based on the courses the student is enrolled in
        TypedQuery<Teacher> query = this.entityManager.createQuery(
                "SELECT t FROM Teacher t WHERE t.taughtCourse IN" +
                        "(SELECT e.course FROM Enrollment e WHERE e.stMatriculation = :stMatriculation)",
                Teacher.class
        );

        // Setting the parameter for the student's matriculation number
        query.setParameter("stMatriculation", student);

        List<Teacher> teacherList = null;
        try {
            // Execute the query and retrieve the list of teachers
            teacherList = query.getResultList();
        } catch (NoResultException e) {
            // Log a message if the student is not enrolled in any course
            logger.info("Student [ " + student.getId() + " ] is not enrolled in any course");
            return null;
        }
        return teacherList;
    }
}

