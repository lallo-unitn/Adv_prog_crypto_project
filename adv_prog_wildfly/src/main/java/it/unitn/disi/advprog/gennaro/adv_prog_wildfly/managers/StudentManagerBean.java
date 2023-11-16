package it.unitn.disi.advprog.gennaro.adv_prog_wildfly.managers;


import it.unitn.disi.advprog.gennaro.adv_prog_wildfly.DTO.EnrollmentDto;
import it.unitn.disi.advprog.gennaro.adv_prog_wildfly.DTO.StudentDto;
import it.unitn.disi.advprog.gennaro.adv_prog_wildfly.DTOAssembler.DTOAssembler;
import it.unitn.disi.advprog.gennaro.adv_prog_wildfly.beans.EnrollmentBean;
import it.unitn.disi.advprog.gennaro.adv_prog_wildfly.beans.StudentBean;
import it.unitn.disi.advprog.gennaro.adv_prog_wildfly.entities.Enrollment;
import it.unitn.disi.advprog.gennaro.adv_prog_wildfly.entities.Student;
import jakarta.ejb.*;
import org.jboss.logging.Logger;

import java.util.LinkedList;
import java.util.List;


@Stateless
@Remote(StudentManagerFacade.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class StudentManagerBean implements StudentManagerFacade {

    private static final Logger logger = Logger.getLogger(StudentManagerBean.class);

    @EJB
    private StudentBean studentBean;

    @EJB
    private EnrollmentBean enrollmentBean;

    @Override
    public StudentDto getStudent(int matriculation) {
        logger.info("Retrieving student [ " + matriculation + " ] info");
        Student student = this.studentBean.getStudentByMatriculation(matriculation);
        return DTOAssembler.getStudentDTO(student);
    }

    @Override
    public List<EnrollmentDto> getStudentCourses(int matriculation) {
        logger.info("Retrieving student [ " + matriculation + " ] enrollments");
        Student student = this.studentBean.getStudentByMatriculation(matriculation);
        if(student == null){
            return null;
        }
        List<Enrollment> enrollmentList = this.enrollmentBean.getEnrollmentByStudent(student);
        if(enrollmentList == null || enrollmentList.isEmpty()){
            return null;
        }
        List<EnrollmentDto> enrollmentDTOList = (List<EnrollmentDto>) new LinkedList();
        for (Enrollment e:
                enrollmentList) {
            enrollmentDTOList.add(DTOAssembler.getEnrollmentDTO(e));
        }
        return enrollmentDTOList;
    }

}