package it.unitn.disi.advprog.gennaro.adv_prog_project.servlet;


import it.unitn.disi.advprog.gennaro.adv_prog_project.dto.EnrollmentDto;
import it.unitn.disi.advprog.gennaro.adv_prog_project.dto.StudentDto;
import it.unitn.disi.advprog.gennaro.adv_prog_project.dto.UserAccountDto;
import it.unitn.disi.advprog.gennaro.adv_prog_project.managers.StudentManagerBean;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
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
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Set initial attributes for messages
        request.setAttribute("messageStudent", "");
        request.setAttribute("messageAdvisor", "");

        // Get the userAccountDto attribute from the session
        UserAccountDto userAccountDto = (UserAccountDto) request.getSession().getAttribute("userAccountDto");

        // Get student information using the StudentManagerBean
        StudentDto studentDto = studentManagerBean.getStudent(userAccountDto);

        // Check if the student exists
        if (studentDto == null) {
            // Set an error message and forward to index.jsp
            request.setAttribute("messageStudent", "Matriculation is not registered");
            request.getRequestDispatcher("restricted/index.jsp").forward(request, response);
            return;
        }

        // Set the studentDto attribute for the JSP page
        request.setAttribute("studentDto", studentDto);

        // Get a list of enrollments for the student
        List<EnrollmentDto> enrollmentDtoList = studentManagerBean.getStudentCourses(studentDto.getId());

        for(EnrollmentDto e : enrollmentDtoList) {
            System.out.println(e.toString());
        }

        // Set the enrollmentDtoList attribute for the JSP page
        request.setAttribute("enrollmentDtoList", enrollmentDtoList);

        // Forward the request to studentPage.jsp
        request.getRequestDispatcher("restricted/studentPage.jsp").forward(request, response);
    }
}