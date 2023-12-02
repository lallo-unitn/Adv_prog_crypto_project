package it.unitn.disi.advprog.gennaro.adv_prog_project.servlet;

import it.unitn.disi.advprog.gennaro.adv_prog_project.dto.TeacherDto;
import it.unitn.disi.advprog.gennaro.adv_prog_project.managers.TeacherManagerBean;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class AssignGradeServlet extends HttpServlet {

    @EJB
    private TeacherManagerBean teacherManagerBean;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get the studentId number from the request
        int studentId = Integer.parseInt(req.getParameter("studentId"));

        // Get the studentDto attribute from the session
        TeacherDto teacherDto = (TeacherDto) req.getSession().getAttribute("teacherDto");

        /*// Get the list of teachers associated with the student using the TeacherManagerBean
        List<TeacherDto> teacherList = teacherManagerBean.getTeacherByStudent(studentId);

        // Check if the student is enrolled in any course
        if (!teacherList.contains(teacherDto)) {
            // Set an error message and forward to teacher.jsp
            req.setAttribute("messageStudent", "Not enrolled in your course");
            req.getRequestDispatcher("restricted/teacher.jsp").forward(req, resp);
            return;
        }*/

        // Get the grade from the request
        int grade = Integer.parseInt(req.getParameter("grade"));

        // Set the grade for the student using the TeacherManagerBean
        teacherManagerBean.setStudentGrade(studentId, grade);

        // Forward the request to teacher.jsp
        req.getRequestDispatcher("restricted/teacher.jsp").forward(req, resp);
    }
}
