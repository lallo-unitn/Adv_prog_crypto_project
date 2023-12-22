package it.unitn.disi.advprog.gennaro.adv_prog_project.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class AdminFilter implements Filter {

    //filter users that are not admin
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpSession session = httpRequest.getSession();
        if(session.getAttribute("userAccountDto") == null) {
            RequestDispatcher rd = httpRequest.getRequestDispatcher("LoginServlet");
            rd.forward(servletRequest, servletResponse);
        }
        else {
            String role = (String) session.getAttribute("role");
            //log role
            System.out.println("AdminFilter: " + role);
            if(role.equals("admin")) {
                filterChain.doFilter(servletRequest, servletResponse);
            }
            else {
                RequestDispatcher rd = httpRequest.getRequestDispatcher("LoginServlet");
                rd.forward(servletRequest, servletResponse);
            }
        }
    }
}
