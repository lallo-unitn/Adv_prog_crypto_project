package it.unitn.disi.advprog.gennaro.adv_prog_project.dto;

import it.unitn.disi.advprog.gennaro.adv_prog_project.entities.Enrollment;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link Enrollment}
 */
public class EnrollmentDto implements Serializable {
    private final EnrollmentIdDto id;
    private final Integer courseId;
    private final String courseName;
    private final Integer grade;

    public EnrollmentDto(EnrollmentIdDto id, Integer courseId, String courseName, Integer grade) {
        this.id = id;
        this.courseId = courseId;
        this.courseName = courseName;
        this.grade = grade;
    }

    public EnrollmentIdDto getId() {
        return id;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public Integer getGrade() {
        return grade;
    }

    public String getCourseName() {
        return courseName;
    }

    @Override
    public String toString() {
        return "EnrollmentDto{" +
                "id=" + id +
                ", courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", grade=" + grade +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnrollmentDto that = (EnrollmentDto) o;
        return Objects.equals(id, that.id) && Objects.equals(courseId, that.courseId) && Objects.equals(courseName, that.courseName) && Objects.equals(grade, that.grade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courseId, courseName, grade);
    }

}