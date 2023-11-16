package it.unitn.disi.advprog.gennaro.adv_prog_wildfly.DTO;

import it.unitn.disi.advprog.gennaro.adv_prog_wildfly.entities.Student;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link Student}
 */
public class StudentDto implements Serializable {
    private final Integer id;
    private final String name;
    private final String surname;
    private final Set<EnrollmentDto> enrollments;

    public StudentDto(Integer id, String name, String surname, Set<EnrollmentDto> enrollments) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.enrollments = enrollments;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Set<EnrollmentDto> getEnrollments() {
        return enrollments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentDto entity = (StudentDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.surname, entity.surname) &&
                Objects.equals(this.enrollments, entity.enrollments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, enrollments);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "surname = " + surname + ", " +
                "enrollments = " + enrollments + ")";
    }
}