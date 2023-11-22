package it.unitn.disi.advprog.gennaro.adv_prog_project.auxiliary;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JspFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletResponse httpResponse= (HttpServletResponse) response;
        httpResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
        httpResponse.sendError(404);
    }
}
