package it.unitn.disi.advprog.gennaro.adv_prog_wildfly.managers;

import it.unitn.disi.advprog.gennaro.adv_prog_wildfly.DTO.EnrollmentDto;
import it.unitn.disi.advprog.gennaro.adv_prog_wildfly.DTO.StudentDto;

import java.util.List;

public interface StudentManagerFacade {
    StudentDto getStudent(int matriculation);
    List<EnrollmentDto> getStudentCourses(int matriculation);
}