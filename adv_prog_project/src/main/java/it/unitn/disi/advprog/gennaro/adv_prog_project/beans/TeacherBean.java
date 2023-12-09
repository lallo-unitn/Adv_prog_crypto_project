package it.unitn.disi.advprog.gennaro.adv_prog_project.beans;

import it.unitn.disi.advprog.gennaro.adv_prog_project.dto.UserAccountDto;
import it.unitn.disi.advprog.gennaro.adv_prog_project.entities.Course;
import it.unitn.disi.advprog.gennaro.adv_prog_project.entities.Enrollment;
import it.unitn.disi.advprog.gennaro.adv_prog_project.entities.Student;
import it.unitn.disi.advprog.gennaro.adv_prog_project.entities.Teacher;
import jakarta.ejb.*;
import jakarta.persistence.*;
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
                        "(SELECT e.course.name FROM Enrollment e WHERE e.student = :student)",
                Teacher.class
        );

        // Setting the parameter for the student's matriculation number
        query.setParameter("student", student);

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

    public Teacher getTeacherByUserAccount(UserAccountDto userAccountDto) {
        // Constructing a JPQL query to select a teacher based on the user account
        TypedQuery<Teacher> query = this.entityManager.createQuery(
                "SELECT t FROM Teacher t WHERE t.userAccount.id = :userAccountId",
                Teacher.class
        );

        // Setting the parameter for the user account
        query.setParameter("userAccountId", userAccountDto.getId());

        Teacher teacher = null;
        try {
            // Execute the query and retrieve the teacher
            teacher = query.getSingleResult();
        } catch (NoResultException e) {
            // Log a message if the teacher is not found
            logger.info("Teacher [ " + userAccountDto + " ] not found");
            return null;
        }
        return teacher;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void setStudentGrade(Student student, Course course, int grade) {
        // Constructing a JPQL query to update the student's grade
        Query query = this.entityManager.createQuery(
                "UPDATE Enrollment e SET e.grade = :grade " +
                        "WHERE e.student = :student AND e.course = :courseId"
        );

        // Setting the parameters for the student, course, and grade
        query.setParameter("student", student);
        query.setParameter("courseId", course);
        query.setParameter("grade", grade);

        // Execute the query
        int updatedRows = query.executeUpdate();

        // Log a message or handle the result as needed
        logger.info("Updated rows: " + updatedRows);

        // Log a message
        logger.info("Student [ " + student.getId() + " ] grade updated");
    }

    public Teacher getTeacherByCourse(Course course) {
        // Constructing a JPQL query to select a teacher based on the course
        TypedQuery<Teacher> query = this.entityManager.createQuery(
                "SELECT t FROM Teacher t WHERE t.course = :course",
                Teacher.class
        );

        // Setting the parameter for the course
        query.setParameter("course", course);

        Teacher teacher = null;
        try {
            // Execute the query and retrieve the teacher
            teacher = query.getSingleResult();
        } catch (NoResultException e) {
            // Log a message if the teacher is not found
            logger.info("Teacher [ " + course.getName() + " ] not found");
            return null;
        }
        return teacher;
    }
}

