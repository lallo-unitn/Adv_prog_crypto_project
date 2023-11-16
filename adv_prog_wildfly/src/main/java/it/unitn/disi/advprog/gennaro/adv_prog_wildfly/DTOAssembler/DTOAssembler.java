package it.unitn.disi.advprog.gennaro.adv_prog_wildfly.DTOAssembler;

import it.unitn.disi.advprog.gennaro.adv_prog_wildfly.DTO.*;
import it.unitn.disi.advprog.gennaro.adv_prog_wildfly.entities.*;

import java.util.Set;

public class DTOAssembler {
    public static EnrollmentDto getEnrollmentDTO(Enrollment enrollment) {
        return new EnrollmentDto(
                DTOAssembler.getEnrollmentIdDto(enrollment.getId()),
                DTOAssembler.getCourseDTO(enrollment.getCourse()),
                DTOAssembler.getStudentDTO(enrollment.getStMatriculation()),
                enrollment.getGrade()
        );
    }

    private static Set<EnrollmentDto> getEnrollmentDTO(Set<Enrollment> enrollmentSet) {
        Set<EnrollmentDto> enrollmentDtos = new java.util.HashSet<>(Set.of());
        for (Enrollment enrollment : enrollmentSet) {
            enrollmentDtos.add(DTOAssembler.getEnrollmentDTO(enrollment));
        }
        return enrollmentDtos;
    }

    public static StudentDto getStudentDTO(Student student) {
        return new StudentDto(
                student.getId(),
                student.getName(),
                student.getSurname(),
                DTOAssembler.getEnrollmentDTO(student.getEnrollments())
        );
    }

    public static TeacherDto getTeacherDTO(Teacher teacher) {
        return new TeacherDto(
                teacher.getId(),
                DTOAssembler.getCourseDTO(teacher.getTaughtCourse())
        );
    }

    public static CourseDto getCourseDTO(Course course) {
        return new CourseDto(
                course.getId(),
                course.getName(),
                course.getEnrollments(),
                course.getTeachers()
        );
    }

    public static EnrollmentIdDto getEnrollmentIdDto(EnrollmentId enrollmentId){
        return new EnrollmentIdDto(
                enrollmentId.getCourseId(),
                enrollmentId.getStMatriculation()
        );
    }
}
