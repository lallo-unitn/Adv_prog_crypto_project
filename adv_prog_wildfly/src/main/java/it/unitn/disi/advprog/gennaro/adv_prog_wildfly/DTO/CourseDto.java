package it.unitn.disi.advprog.gennaro.adv_prog_wildfly.DTO;

import it.unitn.disi.advprog.gennaro.adv_prog_wildfly.entities.Course;
import it.unitn.disi.advprog.gennaro.adv_prog_wildfly.entities.Enrollment;
import it.unitn.disi.advprog.gennaro.adv_prog_wildfly.entities.Teacher;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link Course}
 */
public class CourseDto implements Serializable {
    private final Integer id;
    private final String name;
    private final Set<Enrollment> enrollments;
    private final Set<Teacher> teachers;

    public CourseDto(Integer id, String name, Set<Enrollment> enrollments, Set<Teacher> teachers) {
        this.id = id;
        this.name = name;
        this.enrollments = enrollments;
        this.teachers = teachers;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Enrollment> getEnrollments() {
        return enrollments;
    }

    public Set<Teacher> getTeachers() {
        return teachers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseDto entity = (CourseDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.enrollments, entity.enrollments) &&
                Objects.equals(this.teachers, entity.teachers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, enrollments, teachers);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "enrollments = " + enrollments + ", " +
                "teachers = " + teachers + ")";
    }
}