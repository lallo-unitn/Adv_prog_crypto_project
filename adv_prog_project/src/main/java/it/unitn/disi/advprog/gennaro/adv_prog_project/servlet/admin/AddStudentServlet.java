package it.unitn.disi.advprog.gennaro.adv_prog_project.servlet.admin;

import com.google.gson.JsonObject;
import it.unitn.disi.advprog.gennaro.adv_prog_project.entities.Student;
import it.unitn.disi.advprog.gennaro.adv_prog_project.entities.UserAccount;
import it.unitn.disi.advprog.gennaro.adv_prog_project.managers.StudentManager;
import it.unitn.disi.advprog.gennaro.adv_prog_project.managers.UserAccountManager;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class AddStudentServlet extends HttpServlet {
    @EJB
    UserAccountManager userAccountManager;
    @EJB
    StudentManager studentManager;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("newUsername");
        String password = request.getParameter("newPassword");
        String name = request.getParameter("newName");
        String surname = request.getParameter("newSurname");
        String role = "student";

        UserAccount userAccount = new UserAccount(username, password, name, surname, role);
        this.userAccountManager.addUserAccount(userAccount);

        // send confirmation json
        JsonObject jsonResponse = new JsonObject();
        jsonResponse.addProperty("message", "Student added successfully");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print(jsonResponse);
        out.flush();
    }
}
