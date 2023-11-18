package it.unitn.disi.advprog.gennaro.adv_prog_project.DTOAssembler;

import it.unitn.disi.advprog.gennaro.adv_prog_project.DTO.EnrollmentDto;
import it.unitn.disi.advprog.gennaro.adv_prog_project.DTO.StudentDto;
import it.unitn.disi.advprog.gennaro.adv_prog_project.DTO.TeacherDto;
import it.unitn.disi.advprog.gennaro.adv_prog_project.entities.Enrollment;
import it.unitn.disi.advprog.gennaro.adv_prog_project.entities.Student;
import it.unitn.disi.advprog.gennaro.adv_prog_project.entities.Teacher;

/**
 * The DTOAssembler class is responsible for converting entity objects to their corresponding DTOs.
 * It provides static methods for creating DTOs from entities, handling null values gracefully.
 */
public class DTOAssembler {

    /**
     * Converts an Enrollment entity to an EnrollmentDto.
     *
     * @param enrollment the Enrollment entity
     * @return the corresponding EnrollmentDto, or null if there are null values
     */
    public static EnrollmentDto getEnrollmentDTO(Enrollment enrollment) {
        EnrollmentDto dto = new EnrollmentDto();
        try {
            // Set values in the DTO
            dto.setCourseName(enrollment.getCourse().getName());
            dto.setStudentMatriculation(enrollment.getStMatriculation().getId());
            dto.setGrade(enrollment.getGrade());
        } catch (NullPointerException n) {
            // Handle null values and return null
            return null;
        }
        return dto;
    }

    /**
     * Converts a Student entity to a StudentDto.
     *
     * @param student the Student entity
     * @return the corresponding StudentDto, or null if there are null values
     */
    public static StudentDto getStudentDTO(Student student) {
        StudentDto dto = new StudentDto();
        try {
            // Set values in the DTO
            dto.setId(student.getId());
            dto.setName(student.getName());
            dto.setSurname(student.getSurname());
        } catch (NullPointerException n) {
            // Handle null values and return null
            return null;
        }
        return dto;
    }

    /**
     * Converts a Teacher entity to a TeacherDto.
     *
     * @param teacher the Teacher entity
     * @return the corresponding TeacherDto, or null if there are null values
     */
    public static TeacherDto getTeacherDTO(Teacher teacher) {
        TeacherDto dto = new TeacherDto();
        try {
            // Set values in the DTO
            dto.setName(teacher.getName());
            dto.setSurname(teacher.getSurname());
        } catch (NullPointerException n) {
            // Handle null values and return null
            return null;
        }
        return dto;
    }
}