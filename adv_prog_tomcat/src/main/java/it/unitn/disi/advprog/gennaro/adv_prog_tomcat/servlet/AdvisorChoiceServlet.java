package it.unitn.disi.advprog.gennaro.adv_prog_tomcat.servlet;


//An advisorChoice page, where given a matriculation number we can obtain the
//anagraphic data of the student, together with the names of the teachers of the
//courses the student is enrolled in

import it.unitn.disi.advprog.gennaro.adv_prog_tomcat.business_delegate.AdvisorChoiceManagerBD;
import it.unitn.disi.advprog.gennaro.adv_prog_wildfly.DTO.StudentDto;
import it.unitn.disi.advprog.gennaro.adv_prog_wildfly.DTO.TeacherDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

/*
An advisorChoice page, where given a matriculation number we can obtain the
anagraphic data of the student, together with the names of the teachers of the
courses the student is enrolled in.
*/
public class AdvisorChoiceServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int matriculation = Integer.parseInt(request.getParameter("matriculation"));
        request.setAttribute("messageStudent", "");
        request.setAttribute("messageAdvisor", "");
        AdvisorChoiceManagerBD acmbd = AdvisorChoiceManagerBD.getInstance();
        StudentDto studentDto = acmbd.getStudent(matriculation);
        if(studentDto == null){
            request.setAttribute("messageAdvisor", "Matriculation is not registered");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }
        request.setAttribute("studentDto", studentDto);
        List<TeacherDto> teacherDtoList = acmbd.getTeacherByStudent(matriculation);
        request.setAttribute("teacherDtoList", teacherDtoList);
        request.getRequestDispatcher("advisorChoice.jsp").forward(request, response);
    }
}
