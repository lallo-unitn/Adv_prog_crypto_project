package it.unitn.disi.advprog.gennaro.adv_prog_project.servlet.admin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class AdminServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("message", "");
        RequestDispatcher rd = request.getRequestDispatcher("restricted/adminPage.jsp");
        rd.forward(request, response);
    }

}
