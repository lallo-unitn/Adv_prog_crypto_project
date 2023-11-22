package it.unitn.disi.advprog.gennaro.adv_prog_project.dto;

import it.unitn.disi.advprog.gennaro.adv_prog_project.entities.Course;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link Course}
 */
public class CourseDto implements Serializable {
    private final Integer id;
    private final String name;
    private final Integer cfu;
    private final Set<EnrollmentDto> enrollments;

    public CourseDto(Integer id, String name, Integer cfu, Set<EnrollmentDto> enrollments) {
        this.id = id;
        this.name = name;
        this.cfu = cfu;
        this.enrollments = enrollments;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getCfu() {
        return cfu;
    }

    public Set<EnrollmentDto> getEnrollments() {
        return enrollments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseDto entity = (CourseDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.cfu, entity.cfu) &&
                Objects.equals(this.enrollments, entity.enrollments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cfu, enrollments);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "cfu = " + cfu + ", " +
                "enrollments = " + enrollments + ")";
    }
}