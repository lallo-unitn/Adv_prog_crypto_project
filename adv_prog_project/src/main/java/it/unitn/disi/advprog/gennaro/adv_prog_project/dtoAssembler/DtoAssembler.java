package it.unitn.disi.advprog.gennaro.adv_prog_project.dtoAssembler;

import it.unitn.disi.advprog.gennaro.adv_prog_project.dto.*;
import it.unitn.disi.advprog.gennaro.adv_prog_project.entities.*;

import java.util.HashSet;
import java.util.Set;

/**
 * The dtoAssembler class is responsible for converting entity objects to their corresponding DTOs.
 * It provides static methods for creating DTOs from entities, handling null values gracefully.
 */
public class DtoAssembler {

    /**
     * Converts an Enrollment entity to an EnrollmentDto.
     *
     * @param enrollment the Enrollment entity
     * @return the corresponding EnrollmentDto, or null if there are null values
     */
    public static EnrollmentDto getEnrollmentDto(Enrollment enrollment) {
        // Creating and returning a new EnrollmentDto
        return new EnrollmentDto(
                DtoAssembler.getEnrollmentIdDto(enrollment.getId()),
                enrollment.getCourse().getId(),
                enrollment.getCourse().getName(),
                enrollment.getGrade()
        );
    }

    public static EnrollmentIdDto getEnrollmentIdDto(EnrollmentId id) {
        return new EnrollmentIdDto(
                id.getStudentId(),
                id.getCourseId()
        );
    }

    public static CourseDto getCourseDto(Course course) {
        return new CourseDto(
                course.getId(),
                course.getName(),
                course.getCfu(),
                DtoAssembler.getEnrollmentsDto(course.getEnrollments())
        );
    }

    /**
     * Converts a Student entity to a StudentDto.
     *
     * @param student the Student entity
     * @return the corresponding StudentDto, or null if there are null values
     */
    public static StudentDto getStudentDto(Student student) {
        return new StudentDto(
                student.getId(),
                student.getGpa(),
                DtoAssembler.getEnrollmentsDto(student.getEnrollments()),
                DtoAssembler.getUserAccountDto(student.getUserAccount())
        );
    }

    public static UserAccountDto getUserAccountDto(UserAccount userAccount) {
        return new UserAccountDto(
                userAccount.getId(),
                userAccount.getUsername(),
                userAccount.getPassword(),
                userAccount.getName(),
                userAccount.getSurname(),
                userAccount.getRole()
        );
    }

    public static Set<EnrollmentDto> getEnrollmentsDto(Set<Enrollment> enrollments) {
        // Creating a new set of EnrollmentDto
        Set<EnrollmentDto> enrollmentsDto = new HashSet<>();
        // For each Enrollment in enrollments, add the corresponding EnrollmentDto to enrollmentsDto
        for (Enrollment e :
                enrollments) {
            enrollmentsDto.add(DtoAssembler.getEnrollmentDto(e));
        }
        return enrollmentsDto;
    }

    /**
     * Converts a Teacher entity to a TeacherDto.
     *
     * @param teacher the Teacher entity
     * @return the corresponding TeacherDto, or null if there are null values
     */
    public static TeacherDto getTeacherDto(Teacher teacher) {
        return new TeacherDto(
                teacher.getId(),
                teacher.getTaughtCourse(),
                DtoAssembler.getUserAccountDto(teacher.getUserAccount())
        );
    }
}