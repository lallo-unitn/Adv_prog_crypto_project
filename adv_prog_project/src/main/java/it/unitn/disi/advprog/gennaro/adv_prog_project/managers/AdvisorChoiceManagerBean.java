package it.unitn.disi.advprog.gennaro.adv_prog_project.managers;


import it.unitn.disi.advprog.gennaro.adv_prog_project.dto.StudentDto;
import it.unitn.disi.advprog.gennaro.adv_prog_project.dto.TeacherDto;
import it.unitn.disi.advprog.gennaro.adv_prog_project.dtoAssembler.DtoAssembler;
import it.unitn.disi.advprog.gennaro.adv_prog_project.beans.StudentBean;
import it.unitn.disi.advprog.gennaro.adv_prog_project.beans.TeacherBean;
import it.unitn.disi.advprog.gennaro.adv_prog_project.entities.Student;
import it.unitn.disi.advprog.gennaro.adv_prog_project.entities.Teacher;
import jakarta.ejb.*;
import org.jboss.logging.Logger;

import java.util.LinkedList;
import java.util.List;


@Stateless
@Local
@TransactionManagement(TransactionManagementType.CONTAINER)
public class AdvisorChoiceManagerBean {

    private static final Logger logger = Logger.getLogger(AdvisorChoiceManagerBean.class);

    @EJB
    private StudentBean studentBean;

    @EJB
    private TeacherBean teacherBean;

    public StudentDto getStudent(int matriculation) {
        logger.info("Retrieving student [ " + matriculation + " ] info");
        Student student = this.studentBean.getStudentByMatriculation(matriculation);
        if (student == null) {
            return null;
        }else{
            return DtoAssembler.getStudentDto(student);
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

    private Student getStudentAux(int matriculation) {
        Student student = null;
        student = this.studentBean.getStudentByMatriculation(matriculation);
        return student;
    }
}