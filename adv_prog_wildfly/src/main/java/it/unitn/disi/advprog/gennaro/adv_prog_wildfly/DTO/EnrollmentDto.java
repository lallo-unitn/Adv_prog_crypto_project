package it.unitn.disi.advprog.gennaro.adv_prog_wildfly.DTO;

import it.unitn.disi.advprog.gennaro.adv_prog_wildfly.entities.Enrollment;
import it.unitn.disi.advprog.gennaro.adv_prog_wildfly.entities.EnrollmentId;
import it.unitn.disi.advprog.gennaro.adv_prog_wildfly.entities.Student;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link Enrollment}
 */
public class EnrollmentDto implements Serializable {
    private final EnrollmentId id;
    private final CourseDto course;
    private final Student stMatriculation;
    private final Integer grade;

    public EnrollmentDto(EnrollmentId id, CourseDto course, Student stMatriculation, Integer grade) {
        this.id = id;
        this.course = course;
        this.stMatriculation = stMatriculation;
        this.grade = grade;
    }

    public EnrollmentId getId() {
        return id;
    }

    public CourseDto getCourse() {
        return course;
    }

    public Student getStMatriculation() {
        return stMatriculation;
    }

    public Integer getGrade() {
        return grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnrollmentDto entity = (EnrollmentDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.course, entity.course) &&
                Objects.equals(this.stMatriculation, entity.stMatriculation) &&
                Objects.equals(this.grade, entity.grade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, course, stMatriculation, grade);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "course = " + course + ", " +
                "stMatriculation = " + stMatriculation + ", " +
                "grade = " + grade + ")";
    }
}