package it.unitn.disi.advprog.gennaro.adv_prog_tomcat.servlet;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class HomeServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("messageStudent", "");
        request.setAttribute("messageAdvisor", "");
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
