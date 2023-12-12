package it.unitn.disi.advprog.gennaro.adv_prog_project.managers;

import it.unitn.disi.advprog.gennaro.adv_prog_project.beans.CourseBean;
import it.unitn.disi.advprog.gennaro.adv_prog_project.dto.StudentDto;
import it.unitn.disi.advprog.gennaro.adv_prog_project.dto.TeacherDto;
import it.unitn.disi.advprog.gennaro.adv_prog_project.dto.UserAccountDto;
import it.unitn.disi.advprog.gennaro.adv_prog_project.dtoAssembler.DtoAssembler;
import it.unitn.disi.advprog.gennaro.adv_prog_project.beans.StudentBean;
import it.unitn.disi.advprog.gennaro.adv_prog_project.beans.TeacherBean;
import it.unitn.disi.advprog.gennaro.adv_prog_project.entities.Course;
import it.unitn.disi.advprog.gennaro.adv_prog_project.entities.Student;
import it.unitn.disi.advprog.gennaro.adv_prog_project.entities.Teacher;
import jakarta.ejb.*;
import org.jboss.logging.Logger;

import java.util.LinkedList;
import java.util.List;


@Stateless
@Local
@TransactionManagement(TransactionManagementType.CONTAINER)
public class TeacherManager {

    private static final Logger logger = Logger.getLogger(TeacherManager.class);

    @EJB
    private StudentBean studentBean;

    @EJB
    private TeacherBean teacherBean;
    @EJB
    private CourseBean courseBean;

    public TeacherDto getTeacher(UserAccountDto userAccountDto) {
        logger.info("Retrieving [ " + userAccountDto.toString() + " ]");
        Teacher teacher = this.teacherBean.getTeacherByUserAccount(userAccountDto);
        if (teacher == null) {
            return null;
        } else {
            return DtoAssembler.getTeacherDto(teacher);
        }
    }

    public List<TeacherDto> getTeacherByStudent(int matriculation) {
        Student student = this.getStudentAux(matriculation);
        List<Teacher> teacherList = null;
        if (student == null) {
            return null;
        }
        teacherList = this.teacherBean.getTeacherByStudent(student);
        if (teacherList == null || teacherList.isEmpty()) {
            return null;
        }
        List<TeacherDto> teacherDTOList = (List<TeacherDto>) new LinkedList();
        for (Teacher t :
                teacherList) {
            teacherDTOList.add(DtoAssembler.getTeacherDto(t));
        }
        return teacherDTOList;
    }

    public void setStudentGrade(StudentDto studentDto, int courseId, int grade) {
        logger.info("Trying setting grade [ " + grade + " ] for student [ " + studentDto.getId() + " ] in course [ " + courseId + " ]");
        this.teacherBean.setStudentGrade(
                getStudentAux(studentDto.getId()),
                getCourseAux(courseId),
                grade
        );
    }

    private Student getStudentAux(int studentId) {
        Student student = null;
        student = this.studentBean.getStudentByMatriculation(studentId);
        return student;
    }

    private Course getCourseAux(int courseId) {
        Course course = null;
        course = this.courseBean.getCourseById(courseId);
        return course;
    }

    public TeacherDto getTeacherByCourse(int courseId) {
        Course course = this.getCourseAux(courseId);
        Teacher teacher = null;
        teacher = this.teacherBean.getTeacherByCourse(course);
        if (teacher == null) {
            return null;
        } else {
            return DtoAssembler.getTeacherDto(teacher);
        }
    }
}