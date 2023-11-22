package it.unitn.disi.advprog.gennaro.adv_prog_project.servlet;


import it.unitn.disi.advprog.gennaro.adv_prog_project.dto.EnrollmentDto;
import it.unitn.disi.advprog.gennaro.adv_prog_project.dto.StudentDto;
import it.unitn.disi.advprog.gennaro.adv_prog_project.managers.StudentManagerBean;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

/**
 * StudentServlet handles requests related to student information.
 * It retrieves information about a student and the courses in which the student is enrolled
 * and forwards the request to the studentPage.jsp page.
 */
public class StudentServlet extends HttpServlet {

    @EJB
    private StudentManagerBean studentManagerBean;

    /**
     * Handles HTTP GET requests.
     *
     * @param request  the {@code HttpServletRequest} object containing the client's request
     * @param response the {@code HttpServletResponse} object containing the servlet's response
     * @throws IOException      if an input or output error is detected when the servlet handles the GET request
     * @throws ServletException if the request for the GET could not be handled
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Set initial attributes for messages
        request.setAttribute("messageStudent", "");
        request.setAttribute("messageAdvisor", "");

        // Retrieve matriculation parameter from the request
        int matriculation = Integer.parseInt(request.getParameter("matriculation"));

        // Get student information using the StudentManagerBean
        StudentDto studentDto = studentManagerBean.getStudent(matriculation);

        // Check if the student exists
        if (studentDto == null) {
            // Set an error message and forward to index.jsp
            request.setAttribute("messageStudent", "Matriculation is not registered");
            request.getRequestDispatcher("private/index.jsp").forward(request, response);
            return;
        }

        // Set the studentDto attribute for the JSP page
        request.setAttribute("studentDto", studentDto);

        // Get a list of enrollments for the student
        List<EnrollmentDto> enrollmentDtoList = studentManagerBean.getStudentCourses(matriculation);

        for(EnrollmentDto e : enrollmentDtoList) {
            System.out.println(e.toString());
        }

        // Set the enrollmentDtoList attribute for the JSP page
        request.setAttribute("enrollmentDtoList", enrollmentDtoList);

        // Forward the request to studentPage.jsp
        request.getRequestDispatcher("private/studentPage.jsp").forward(request, response);
    }
}