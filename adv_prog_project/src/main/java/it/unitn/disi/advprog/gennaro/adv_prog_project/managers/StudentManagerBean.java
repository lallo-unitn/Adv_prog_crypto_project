package it.unitn.disi.advprog.gennaro.adv_prog_project.managers;

import it.unitn.disi.advprog.gennaro.adv_prog_project.dto.EnrollmentDto;
import it.unitn.disi.advprog.gennaro.adv_prog_project.dto.StudentDto;
import it.unitn.disi.advprog.gennaro.adv_prog_project.dto.UserAccountDto;
import it.unitn.disi.advprog.gennaro.adv_prog_project.dtoAssembler.DtoAssembler;
import it.unitn.disi.advprog.gennaro.adv_prog_project.beans.EnrollmentBean;
import it.unitn.disi.advprog.gennaro.adv_prog_project.beans.StudentBean;
import it.unitn.disi.advprog.gennaro.adv_prog_project.entities.Enrollment;
import it.unitn.disi.advprog.gennaro.adv_prog_project.entities.Student;
import jakarta.ejb.*;
import org.jboss.logging.Logger;

import java.util.LinkedList;
import java.util.List;


@Stateless
@Local
@TransactionManagement(TransactionManagementType.CONTAINER)
public class StudentManagerBean {

    private static final Logger logger = Logger.getLogger(StudentManagerBean.class);

    @EJB
    private StudentBean studentBean;

    @EJB
    private EnrollmentBean enrollmentBean;


    public StudentDto getStudent(int matriculation) {
        logger.info("Retrieving student [ " + matriculation + " ] info");
        Student student = this.studentBean.getStudentByMatriculation(matriculation);
        if(student == null){
            return null;
        }else{
            return DtoAssembler.getStudentDto(student);
        }
    }

    public StudentDto getStudent(UserAccountDto userAccountDto) {
        logger.info("Retrieving [ " + userAccountDto.toString() + " ]");
        Student student = this.studentBean.getStudentByUserAccount(userAccountDto);
        if(student == null){
            return null;
        }else{
            return DtoAssembler.getStudentDto(student);
        }
    }


    public List<EnrollmentDto> getStudentCourses(int matriculation) {
        logger.info("Retrieving student [ " + matriculation + " ] enrollments");
        Student student = this.studentBean.getStudentByMatriculation(matriculation);
        if (student == null) {
            return null;
        }
        logger.info("Student [ " + matriculation + " ] found");
        List<Enrollment> enrollmentList = this.enrollmentBean.getEnrollmentByStudent(student);
        if (enrollmentList == null || enrollmentList.isEmpty()) {
            return null;
        }
        logger.info("Enrollment list size: " + enrollmentList.size());
        List<EnrollmentDto> enrollmentDTOList = (List<EnrollmentDto>) new LinkedList();
        for (Enrollment e :
                enrollmentList) {
            enrollmentDTOList.add(DtoAssembler.getEnrollmentDto(e));
        }
        return enrollmentDTOList;
    }
}