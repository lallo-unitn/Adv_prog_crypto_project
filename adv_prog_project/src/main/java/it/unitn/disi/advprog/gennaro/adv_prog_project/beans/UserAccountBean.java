package it.unitn.disi.advprog.gennaro.adv_prog_project.beans;

import it.unitn.disi.advprog.gennaro.adv_prog_project.entities.Student;
import it.unitn.disi.advprog.gennaro.adv_prog_project.entities.UserAccount;
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
public class UserAccountBean {
    private static final org.jboss.logging.Logger logger = Logger.getLogger(TeacherBean.class);

    @PersistenceContext(unitName = "default")
    private EntityManager entityManager;

    public UserAccount getUserAccountByCredentials(String username, String password) throws NoResultException{
        logger.info("Checking credentials for user [ " + username + " ]");
        TypedQuery<UserAccount> query = this.entityManager.createQuery(
                "SELECT u FROM UserAccount u WHERE u.username = :username AND u.password = :password", UserAccount.class
        );
        query.setParameter("username", username);
        query.setParameter("password", password);
        return query.getSingleResult();
    }

}
