package it.unitn.disi.advprog.gennaro.adv_prog_project.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * HomeServlet handles requests to the home page.
 * This servlet sets attributes for messages related to students and advisors
 * and forwards the request to the index.jsp page.
 */
public class HomeServlet extends HttpServlet {

    /**
     * Handles HTTP GET requests.
     *
     * @param request  the {@code HttpServletRequest} object containing the client's request
     * @param response the {@code HttpServletResponse} object containing the servlet's response
     * @throws ServletException if the request for the GET could not be handled
     * @throws IOException      if an input or output error is detected when the servlet handles the GET request
     */
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("messageStudent", "");
        request.setAttribute("messageAdvisor", "");
        request.getRequestDispatcher("private/index.jsp").forward(request, response);
    }
}
