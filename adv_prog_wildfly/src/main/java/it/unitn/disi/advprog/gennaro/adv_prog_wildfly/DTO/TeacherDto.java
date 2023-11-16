package it.unitn.disi.advprog.gennaro.adv_prog_wildfly.DTO;

import it.unitn.disi.advprog.gennaro.adv_prog_wildfly.entities.Teacher;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link Teacher}
 */
public class TeacherDto implements Serializable {
    private final Integer id;
    private final CourseDto taughtCourse;

    public TeacherDto(Integer id, CourseDto taughtCourse) {
        this.id = id;
        this.taughtCourse = taughtCourse;
    }

    public Integer getId() {
        return id;
    }

    public CourseDto getTaughtCourse() {
        return taughtCourse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeacherDto entity = (TeacherDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.taughtCourse, entity.taughtCourse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, taughtCourse);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "taughtCourse = " + taughtCourse + ")";
    }
}