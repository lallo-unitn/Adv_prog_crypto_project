package it.unitn.disi.advprog.gennaro.adv_prog_wildfly.beans;

import it.unitn.disi.advprog.gennaro.adv_prog_wildfly.entities.Student;
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

    private static final Logger logger = Logger.getLogger(StudentBean.class);
    @PersistenceContext(unitName = "default")
    private EntityManager entityManager;

    public Student getStudentByMatriculation(int matriculation) {
        TypedQuery<Student> query = this.entityManager.createQuery("SELECT s FROM Student s WHERE s.id = :id", Student.class);
        query.setParameter("id", matriculation);
        Student student = null;
        try {
            student = query.getSingleResult();
        }catch(NoResultException e){
            logger.info("Student [ " + matriculation + " ] is not registered");
            return null;
        }
        return student;
    }
}
