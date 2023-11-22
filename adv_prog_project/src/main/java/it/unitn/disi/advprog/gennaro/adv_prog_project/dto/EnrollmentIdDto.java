package it.unitn.disi.advprog.gennaro.adv_prog_project.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link it.unitn.disi.advprog.gennaro.adv_prog_project.entities.EnrollmentId}
 */
public class EnrollmentIdDto implements Serializable {
    private final Integer studentId;
    private final Integer courseId;

    public EnrollmentIdDto(Integer studentId, Integer courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnrollmentIdDto entity = (EnrollmentIdDto) o;
        return Objects.equals(this.studentId, entity.studentId) &&
                Objects.equals(this.courseId, entity.courseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, courseId);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "studentId = " + studentId + ", " +
                "courseId = " + courseId + ")";
    }
}