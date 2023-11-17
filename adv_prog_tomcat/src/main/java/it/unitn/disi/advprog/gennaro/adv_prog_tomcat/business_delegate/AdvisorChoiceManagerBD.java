package it.unitn.disi.advprog.gennaro.adv_prog_tomcat.business_delegate;

import it.unitn.disi.advprog.gennaro.adv_prog_tomcat.auxiliary.JndiName;
import it.unitn.disi.advprog.gennaro.adv_prog_tomcat.serviceLocator.RemoteServiceInitializer;
import it.unitn.disi.advprog.gennaro.adv_prog_wildfly.DTO.StudentDto;
import it.unitn.disi.advprog.gennaro.adv_prog_wildfly.DTO.TeacherDto;
import it.unitn.disi.advprog.gennaro.adv_prog_wildfly.managers.AdvisorChoiceManagerFacade;

import java.util.List;

public class AdvisorChoiceManagerBD {
    private final AdvisorChoiceManagerFacade advisorChoiceManagerFacade;
    private static AdvisorChoiceManagerBD instance;

    private AdvisorChoiceManagerBD(){
        this.advisorChoiceManagerFacade = (AdvisorChoiceManagerFacade) RemoteServiceInitializer.getInstance().getService(JndiName.ADVISOR);
    }

    public static AdvisorChoiceManagerBD getInstance() {
        if (instance == null) {
            instance = new AdvisorChoiceManagerBD();
        }
        return instance;
    }

    public StudentDto getStudent(int matriculation){
        return this.advisorChoiceManagerFacade.getStudent(matriculation);
    }

    public List<TeacherDto> getTeacherByStudent(int matriculation){
        return this.advisorChoiceManagerFacade.getTeacherByStudent(matriculation);
    }
}
