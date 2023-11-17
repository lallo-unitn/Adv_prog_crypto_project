package it.unitn.disi.advprog.gennaro.adv_prog_wildfly.DTO;


import java.io.Serializable;
import java.util.Objects;

public class TeacherDto implements Serializable {
    private final Integer id;
    private final CourseDto taughtCourse;
    private final String name;
    private final String surname;

    public TeacherDto(Integer id, CourseDto taughtCourse, String name, String surname) {
        this.id = id;
        this.taughtCourse = taughtCourse;
        this.name = name;
        this.surname = surname;
    }

    public Integer getId() {
        return id;
    }

    public CourseDto getTaughtCourse() {
        return taughtCourse;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeacherDto entity = (TeacherDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.taughtCourse, entity.taughtCourse) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.surname, entity.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, taughtCourse, name, surname);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "taughtCourse = " + taughtCourse + ", " +
                "name = " + name + ", " +
                "surname = " + surname + ")";
    }

}