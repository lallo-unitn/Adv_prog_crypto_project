package it.unitn.disi.advprog.gennaro.adv_prog_tomcat.servlet;


import it.unitn.disi.advprog.gennaro.adv_prog_tomcat.business_delegate.StudentManagerBD;
import it.unitn.disi.advprog.gennaro.adv_prog_wildfly.DTO.EnrollmentDto;
import it.unitn.disi.advprog.gennaro.adv_prog_wildfly.DTO.StudentDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

//a Student page, where given a matriculation number we can obtain all the
//anagraphic data of the student, together with the list of all the courses he is
//enrolled in and the votes.
public class StudentServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("messageStudent", "");
        request.setAttribute("messageAdvisor", "");
        int matriculation = Integer.parseInt(request.getParameter("matriculation"));
        StudentManagerBD stbd = StudentManagerBD.getInstance();
        StudentDto studentDto = stbd.getStudent(matriculation);
        if(studentDto == null){
            request.setAttribute("messageStudent", "Matriculation is not registered");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }
        request.setAttribute("studentDTO", studentDto);
        List<EnrollmentDto> enrollmentDtoList = stbd.getStudentCourses(matriculation);
        request.setAttribute("enrollmentDTOList", enrollmentDtoList);
        request.getRequestDispatcher("studentPage.jsp").forward(request, response);
    }
}