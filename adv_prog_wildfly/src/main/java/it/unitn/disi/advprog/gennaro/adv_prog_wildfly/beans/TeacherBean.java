package it.unitn.disi.advprog.gennaro.adv_prog_wildfly.beans;

import it.unitn.disi.advprog.gennaro.adv_prog_wildfly.entities.Student;
import it.unitn.disi.advprog.gennaro.adv_prog_wildfly.entities.Teacher;
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
    private static final org.jboss.logging.Logger logger = Logger.getLogger(TeacherBean.class);
    @PersistenceContext(unitName = "default")
    private EntityManager entityManager;

    /*
    public Teacher getTeacherBySurname(String surname) {
        TypedQuery<Teacher> query = this.entityManager.createQuery("SELECT t FROM Teacher t WHERE t.id = :surname", Teacher.class);
        query.setParameter("surname", surname);
        return query.getSingleResult();
    }
    */

    //SELECT * FROM TEACHER WHERE TEACHED_COURSE IN
    //(SELECT COURSE_NAME FROM ENROLLMENT WHERE ST_MATRICULATION = 111)
    public List<Teacher> getTeacherByStudent(Student student) {
        TypedQuery<Teacher> query = this.entityManager.createQuery("SELECT t FROM Teacher t WHERE t.taughtCourse IN" +
                "(SELECT e.course FROM Enrollment e WHERE e.stMatriculation = :stMatriculation)", Teacher.class);
        query.setParameter("stMatriculation", student);
        List<Teacher> teacherList = null;
        try {
            teacherList = query.getResultList();
        }catch(NoResultException e){
            logger.info("Student [ " + student.getId() + " ] is not enrolled in any course");
            return null;
        }
        return teacherList;
    }
}

