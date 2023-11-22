package it.unitn.disi.advprog.gennaro.adv_prog_project.servlet;

import it.unitn.disi.advprog.gennaro.adv_prog_project.dtoAssembler.DtoAssembler;
import it.unitn.disi.advprog.gennaro.adv_prog_project.entities.UserAccount;
import it.unitn.disi.advprog.gennaro.adv_prog_project.managers.UserAccountManager;
import jakarta.ejb.EJB;
import jakarta.persistence.NoResultException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
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
        RequestDispatcher rd = request.getRequestDispatcher("LoginServlet");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ServletContext ctx = request.getServletContext();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String destination = (String) request.getAttribute("destination");
        if (destination == null) {
            destination = "HomeServlet";
        }
        RequestDispatcher rd = request.getRequestDispatcher("LoginServlet");
        //UserBean ub = new UserBean(username, password);
        UserAccount userAccount = null;
        try{
            userAccount = userAccountManager.getUserAccountByCredentials(username, password);
        } catch (NoResultException e) {
            rd.forward(request, response);
            return;
        }
        rd = request.getRequestDispatcher(destination);
        session.setAttribute("userAccountDto", DtoAssembler.getUserAccountDto(userAccount));
        // decide here rbac
        rd.forward(request, response);
    }

}