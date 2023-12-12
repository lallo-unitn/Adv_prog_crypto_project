package it.unitn.disi.advprog.gennaro.adv_prog_project.servlet.auth;

import it.unitn.disi.advprog.gennaro.adv_prog_project.dto.UserAccountDto;
import it.unitn.disi.advprog.gennaro.adv_prog_project.dtoAssembler.DtoAssembler;
import it.unitn.disi.advprog.gennaro.adv_prog_project.entities.UserAccount;
import it.unitn.disi.advprog.gennaro.adv_prog_project.managers.UserAccountManager;
import jakarta.ejb.EJB;
import jakarta.persistence.PersistenceException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;


public class AuthServlet extends HttpServlet {

    @EJB
    private UserAccountManager userAccountManager;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/LoginServlet");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        RequestDispatcher rd = request.getRequestDispatcher("/LoginServlet");
        UserAccount userAccount;
        request.setAttribute("message", "");
        try{
            userAccount = userAccountManager.getUserAccountByCredentials(username, password);
        } catch (Exception e) {
            request.setAttribute("message", "Wrong credentials");
            rd.forward(request, response);
            return;
        }
        UserAccountDto userAccountDto = DtoAssembler.getUserAccountDto(userAccount);
        session.setAttribute("userAccountDto", userAccountDto);
        session.setAttribute("role", userAccountDto.getRole());

        switch (userAccountDto.getRole()) {
            case "teacher":
                rd = request.getRequestDispatcher("/TeacherServlet");
                break;
            case "student":
                rd = request.getRequestDispatcher("/StudentServlet");
                break;
            case "admin":
                rd = request.getRequestDispatcher("/AdminServlet");
                break;
            default:
                rd = request.getRequestDispatcher("/LoginServlet");
                break;
        }

        rd.forward(request, response);
    }

}