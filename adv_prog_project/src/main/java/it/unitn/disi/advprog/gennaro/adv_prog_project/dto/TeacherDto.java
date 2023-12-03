package it.unitn.disi.advprog.gennaro.adv_prog_project.dto;

import it.unitn.disi.advprog.gennaro.adv_prog_project.entities.Teacher;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link Teacher}
 */
public class TeacherDto implements Serializable {
    private final Integer id;
    private final String taughtCourse;
    private final UserAccountDto userAccount;
    private final CourseDto course;

    public TeacherDto(Integer id, String taughtCourse, UserAccountDto userAccount, CourseDto course) {
        this.id = id;
        this.taughtCourse = taughtCourse;
        this.userAccount = userAccount;
        this.course = course;
    }

    public Integer getId() {
        return id;
    }

    public String getTaughtCourse() {
        return taughtCourse;
    }

    public UserAccountDto getUserAccount() {
        return userAccount;
    }

    public CourseDto getCourse() {
        return course;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeacherDto entity = (TeacherDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.taughtCourse, entity.taughtCourse) &&
                Objects.equals(this.userAccount, entity.userAccount) &&
                Objects.equals(this.course, entity.course);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, taughtCourse, userAccount, course);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "taughtCourse = " + taughtCourse + ", " +
                "userAccount = " + userAccount + ", " +
                "course = " + course + ")";
    }
}