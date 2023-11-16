package it.unitn.disi.advprog.gennaro.adv_prog_wildfly.managers;


import it.unitn.disi.advprog.gennaro.adv_prog_wildfly.DTO.StudentDto;
import it.unitn.disi.advprog.gennaro.adv_prog_wildfly.DTO.TeacherDto;
import it.unitn.disi.advprog.gennaro.adv_prog_wildfly.DTOAssembler.DTOAssembler;
import it.unitn.disi.advprog.gennaro.adv_prog_wildfly.beans.StudentBean;
import it.unitn.disi.advprog.gennaro.adv_prog_wildfly.beans.TeacherBean;
import it.unitn.disi.advprog.gennaro.adv_prog_wildfly.entities.Student;
import it.unitn.disi.advprog.gennaro.adv_prog_wildfly.entities.Teacher;
import jakarta.ejb.*;
import org.jboss.logging.Logger;


import java.util.LinkedList;
import java.util.List;


@Stateless
@Remote(AdvisorChoiceManagerFacade.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class AdvisorChoiceManagerBean implements AdvisorChoiceManagerFacade {

    private static final Logger logger = Logger.getLogger(AdvisorChoiceManagerBean.class);

    @EJB
    private StudentBean studentBean;

    @EJB
    private TeacherBean teacherBean;

    @Override
    public StudentDto getStudent(int matriculation) {
        logger.info("Retrieving student [ " + matriculation + " ] info");
        Student student = this.studentBean.getStudentByMatriculation(matriculation);
        return DTOAssembler.getStudentDTO(student);
    }

    @Override
    public List<TeacherDto> getTeacherByStudent(int matriculation) {
        Student student = this.getStudentAux(matriculation);
        List<Teacher> teacherList = null;
        if(student == null){
            return null;
        }
        teacherList = this.teacherBean.getTeacherByStudent(student);
        if(teacherList == null || teacherList.isEmpty()){
            return null;
        }
        List<TeacherDto> teacherDTOList = (List<TeacherDto>) new LinkedList();
        for (Teacher t:
                teacherList) {
            teacherDTOList.add(DTOAssembler.getTeacherDTO(t));
        }
        return teacherDTOList;
    }

    private Student getStudentAux(int matriculation){
        Student student = null;
        student = this.studentBean.getStudentByMatriculation(matriculation);
        return student;
    }
}