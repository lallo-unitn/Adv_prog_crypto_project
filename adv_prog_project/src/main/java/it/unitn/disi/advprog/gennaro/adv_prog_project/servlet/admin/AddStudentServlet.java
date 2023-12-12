package it.unitn.disi.advprog.gennaro.adv_prog_project.servlet.admin;

import com.google.gson.JsonObject;
import it.unitn.disi.advprog.gennaro.adv_prog_project.entities.Student;
import it.unitn.disi.advprog.gennaro.adv_prog_project.entities.UserAccount;
import it.unitn.disi.advprog.gennaro.adv_prog_project.managers.StudentManager;
import it.unitn.disi.advprog.gennaro.adv_prog_project.managers.UserAccountManager;
import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.io.IOException;
import java.io.PrintWriter;

public class AddStudentServlet extends HttpServlet {
    @EJB
    UserAccountManager userAccountManager;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("newUsername");
        String password = request.getParameter("newPassword");
        String name = request.getParameter("newName");
        String surname = request.getParameter("newSurname");
        String role = "student";

        UserAccount userAccount = new UserAccount(
                username,
                BCrypt.hashpw(password, BCrypt.gensalt(12)),
                name,
                surname,
                role
        );
        try{
            this.userAccountManager.addUserAccount(userAccount);
            request.setAttribute("message", "Student added successfully");
        } catch (Exception e) {
            request.setAttribute("message", "Error adding student");
        }
        RequestDispatcher rd = request.getRequestDispatcher("restricted/adminPage.jsp");
        rd.forward(request, response);
    }
}
