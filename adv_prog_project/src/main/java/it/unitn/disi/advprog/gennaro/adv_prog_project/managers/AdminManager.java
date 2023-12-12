package it.unitn.disi.advprog.gennaro.adv_prog_project.managers;

import it.unitn.disi.advprog.gennaro.adv_prog_project.beans.StudentBean;
import it.unitn.disi.advprog.gennaro.adv_prog_project.beans.TeacherBean;
import it.unitn.disi.advprog.gennaro.adv_prog_project.beans.UserAccountBean;
import it.unitn.disi.advprog.gennaro.adv_prog_project.dto.StudentDto;
import it.unitn.disi.advprog.gennaro.adv_prog_project.entities.Student;
import it.unitn.disi.advprog.gennaro.adv_prog_project.entities.UserAccount;
import jakarta.ejb.*;
import org.jboss.logging.Logger;

@Stateless
@Local
@TransactionManagement(TransactionManagementType.CONTAINER)
public class AdminManager {
    private static final Logger logger = Logger.getLogger(StudentManager.class);

    @EJB
    private UserAccountBean userAccountBean;
    @EJB
    private StudentBean studentBean;
    @EJB
    private TeacherBean teacherBean;

    public boolean isAdmin(String username, String password) {
        UserAccount userAccount = this.userAccountBean.getUserAccountByCredentials(username, password);
        return userAccount.getRole().equals("admin");
    }

    public boolean addUserAccount(UserAccount userAccount) {
        return this.userAccountBean.addUserAccount(userAccount);

    }
}
