package it.unitn.disi.advprog.gennaro.adv_prog_project.filters;

import it.unitn.disi.advprog.gennaro.adv_prog_project.dto.UserAccountDto;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;


public class AuthFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();
        String path = httpRequest.getServletPath();
        String query = httpRequest.getQueryString();
        String destination = path;
        if (query != null && !query.equals("null"))
            destination = path + "?" + query;
        httpRequest.setAttribute("destination", destination);
        UserAccountDto userAccountDto = (UserAccountDto) session.getAttribute("userAccountDto");
        RequestDispatcher rd;
        if (userAccountDto != null && userAccountDto.getUsername() != null && !userAccountDto.getUsername().equals("null")) {
            System.out.println("AUTHFILTER: " + userAccountDto.getUsername());
            System.out.println("AUTHFILTER: " + destination);
            rd = httpRequest.getRequestDispatcher(destination);
        } else {
            System.out.println("AUTHFILTER: " + "not auth");
            rd = httpRequest.getRequestDispatcher("LoginServlet");
        }
        rd.forward(request, response);
        chain.doFilter(request, response);
    }
}