package it.unitn.disi.advprog.gennaro.adv_prog_project.servlet;

import it.unitn.disi.advprog.gennaro.adv_prog_project.dto.StudentDto;
import it.unitn.disi.advprog.gennaro.adv_prog_project.dto.TeacherDto;
import it.unitn.disi.advprog.gennaro.adv_prog_project.managers.AdvisorChoiceManagerBean;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

/**
 * AdvisorChoiceServlet handles requests related to advisor choices.
 * It retrieves information about a student and the teachers associated with that student
 * and forwards the request to the advisorChoice.jsp page.
 */
public class AdvisorChoiceServlet extends HttpServlet {

    @EJB
    private AdvisorChoiceManagerBean advisorChoiceManagerBean;

    /**
     * Handles HTTP GET requests.
     *
     * @param request  the {@code HttpServletRequest} object containing the client's request
     * @param response the {@code HttpServletResponse} object containing the servlet's response
     * @throws ServletException if the request for the GET could not be handled
     * @throws IOException      if an input or output error is detected when the servlet handles the GET request
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve matriculation parameter from the request
        int matriculation = Integer.parseInt(request.getParameter("matriculation"));

        // Set initial attributes for messages
        request.setAttribute("messageStudent", "");
        request.setAttribute("messageAdvisor", "");

        // Get student information using the AdvisorChoiceManagerBean
        StudentDto studentDto = advisorChoiceManagerBean.getStudent(matriculation);

        // Check if the student exists
        if (studentDto == null) {
            // Set an error message and forward to index.jsp
            request.setAttribute("messageAdvisor", "Matriculation is not registered");
            request.getRequestDispatcher("private/index.jsp").forward(request, response);
            return;
        }

        // Set the studentDto attribute for the JSP page
        request.setAttribute("studentDto", studentDto);

        // Get a list of teachers associated with the student
        List<TeacherDto> teacherDtoList = advisorChoiceManagerBean.getTeacherByStudent(matriculation);

        // Set the teacherDtoList attribute for the JSP page
        request.setAttribute("teacherDtoList", teacherDtoList);

        // Forward the request to advisorChoice.jsp
        request.getRequestDispatcher("private/advisorChoice.jsp").forward(request, response);
    }
}