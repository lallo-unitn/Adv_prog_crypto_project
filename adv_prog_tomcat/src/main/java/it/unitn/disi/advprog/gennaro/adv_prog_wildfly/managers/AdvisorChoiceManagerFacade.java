package it.unitn.disi.advprog.gennaro.adv_prog_wildfly.managers;

import it.unitn.disi.advprog.gennaro.adv_prog_wildfly.DTO.StudentDto;
import it.unitn.disi.advprog.gennaro.adv_prog_wildfly.DTO.TeacherDto;

import java.util.List;

public interface AdvisorChoiceManagerFacade {
    StudentDto getStudent(int matriculation);
    List<TeacherDto> getTeacherByStudent(int matriculation);
}
