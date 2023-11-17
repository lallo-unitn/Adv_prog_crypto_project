package it.unitn.disi.advprog.gennaro.adv_prog_tomcat.business_delegate;


import it.unitn.disi.advprog.gennaro.adv_prog_tomcat.auxiliary.JndiName;
import it.unitn.disi.advprog.gennaro.adv_prog_tomcat.serviceLocator.RemoteServiceInitializer;
import it.unitn.disi.advprog.gennaro.adv_prog_wildfly.DTO.EnrollmentDto;
import it.unitn.disi.advprog.gennaro.adv_prog_wildfly.DTO.StudentDto;
import it.unitn.disi.advprog.gennaro.adv_prog_wildfly.managers.StudentManagerFacade;

import java.util.List;

public class StudentManagerBD {
    private final StudentManagerFacade studentManagerFacade;
    private static StudentManagerBD instance;

    private StudentManagerBD(){
        this.studentManagerFacade = (StudentManagerFacade) RemoteServiceInitializer.getInstance().getService(JndiName.STUDENT);
    }

    public static StudentManagerBD getInstance() {
        if (instance == null) {
            instance = new StudentManagerBD();
        }
        return instance;
    }

    public StudentDto getStudent(int matriculation){
        return this.studentManagerFacade.getStudent(matriculation);
    }

    public List<EnrollmentDto> getStudentCourses(int matriculation){
        return this.studentManagerFacade.getStudentCourses(matriculation);
    }

}
