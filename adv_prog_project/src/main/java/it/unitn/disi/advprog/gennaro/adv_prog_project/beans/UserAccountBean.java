package it.unitn.disi.advprog.gennaro.adv_prog_project.beans;

import it.unitn.disi.advprog.gennaro.adv_prog_project.entities.Student;
import it.unitn.disi.advprog.gennaro.adv_prog_project.entities.UserAccount;
import jakarta.ejb.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.jboss.logging.Logger;
import org.springframework.security.crypto.bcrypt.BCrypt;
@Local
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class UserAccountBean {
    private static final org.jboss.logging.Logger logger = Logger.getLogger(TeacherBean.class);

    @PersistenceContext(unitName = "default")
    private EntityManager entityManager;

    @EJB
    private StudentBean studentBean;

    public boolean setUserAccount(UserAccount userAccount) {
        logger.info("Registering user [ " + userAccount.getUsername() + " ]");
        this.entityManager.persist(userAccount);
        logger.debug("User [ " + userAccount.getUsername() + " ] successfully registered");
        return true;
    }

    public UserAccount getUserAccountByCredentials(String username, String password) throws NoResultException{
        logger.info("Checking credentials for user [ " + username + " ]");
        TypedQuery<UserAccount> query = this.entityManager.createQuery(
                "SELECT u FROM UserAccount u WHERE u.username = :username", UserAccount.class
        );
        query.setParameter("username", username);
        UserAccount user = query.getSingleResult();
        // Check if the password is correct
        if (BCrypt.checkpw(password, user.getPassword())) {
            return user;
        }
        return null;
    }

    public boolean addUserAccount(UserAccount userAccount) {
        logger.info("Registering user [ " + userAccount.getUsername() + " ]");
        Student student = new Student();
        this.studentBean.addStudent(student);
        userAccount.setStudent(student);
        this.studentBean.updateStudent(student);
        student.setUserAccount(userAccount);
        this.entityManager.persist(userAccount);
        logger.debug("User [ " + userAccount.getUsername() + " ] successfully registered");
        return true;
    }
}
