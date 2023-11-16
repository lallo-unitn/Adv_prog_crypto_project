package it.unitn.disi.advprog.gennaro.adv_prog_wildfly.beans;

import it.unitn.disi.advprog.gennaro.adv_prog_wildfly.entities.Enrollment;
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

import java.util.List;

@Local
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class EnrollmentBean {
    private static final org.jboss.logging.Logger logger = Logger.getLogger(EnrollmentBean.class);
    @PersistenceContext(unitName = "default")
    private EntityManager entityManager;

    public List<Enrollment> getEnrollmentByStudent(Student student) {
        TypedQuery<Enrollment> query = this.entityManager.createQuery("SELECT e FROM Enrollment e WHERE e.stMatriculation = :stMatriculation", Enrollment.class);
        query.setParameter("stMatriculation", student);
        List<Enrollment> enrollmentList = null;
        try {
            enrollmentList = query.getResultList();
        }catch(NoResultException e){
            logger.info("Student [ " + student.getId() + " ] is not enrolled in any course");
            return null;
        }
        return enrollmentList;
    }
}
