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
    private final String eccPublicKey;

    public TeacherDto(Integer id, String taughtCourse, UserAccountDto userAccount, CourseDto course, String eccPublicKey) {
        this.id = id;
        this.taughtCourse = taughtCourse;
        this.userAccount = userAccount;
        this.course = course;
        this.eccPublicKey = eccPublicKey;
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

    public String getEccPublicKey() {
        return eccPublicKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeacherDto entity = (TeacherDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.taughtCourse, entity.taughtCourse) &&
                Objects.equals(this.userAccount, entity.userAccount) &&
                Objects.equals(this.course, entity.course) &&
                Objects.equals(this.eccPublicKey, entity.eccPublicKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, taughtCourse, userAccount, course, eccPublicKey);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "taughtCourse = " + taughtCourse + ", " +
                "userAccount = " + userAccount + ", " +
                "course = " + course + ", " +
                "eccPublicKey = " + eccPublicKey + ")";
    }
}