package it.unitn.disi.advprog.gennaro.adv_prog_project.servlet;

import it.unitn.disi.advprog.gennaro.adv_prog_project.dto.TeacherDto;
import it.unitn.disi.advprog.gennaro.adv_prog_project.dto.UserAccountDto;
import it.unitn.disi.advprog.gennaro.adv_prog_project.managers.TeacherManagerBean;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * AdvisorChoiceServlet handles requests related to advisor choices.
 * It retrieves information about a student and the teachers associated with that student
 * and forwards the request to the teacher.jsp page.
 */
public class TeacherServlet extends HttpServlet {

    @EJB
    private TeacherManagerBean teacherManagerBean;

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
        // Get the userAccountDto attribute from the session
        UserAccountDto userAccountDto = (UserAccountDto) request.getSession().getAttribute("userAccountDto");

        // Set initial attributes for messages
        request.setAttribute("messageStudent", "");
        request.setAttribute("messageAdvisor", "");

        // Get student information using the TeacherManagerBean
        TeacherDto teacherDto = teacherManagerBean.getTeacher(userAccountDto);

        // Check if the student exists
        if (teacherDto == null) {
            // Set an error message and forward to index.jsp
            request.setAttribute("messageAdvisor", "Not registered");
            request.getRequestDispatcher("LoginServlet").forward(request, response);
            return;
        }

        // Set the studentDto attribute for the JSP page
        request.setAttribute("teacherDto", teacherDto);

        // Forward the request to teacher.jsp
        request.getRequestDispatcher("restricted/teacher.jsp").forward(request, response);
    }
}