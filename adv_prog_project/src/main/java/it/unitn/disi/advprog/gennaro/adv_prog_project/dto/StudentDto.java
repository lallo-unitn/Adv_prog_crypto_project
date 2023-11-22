package it.unitn.disi.advprog.gennaro.adv_prog_project.dto;

import it.unitn.disi.advprog.gennaro.adv_prog_project.entities.Student;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link Student}
 */
public class StudentDto implements Serializable {
    private final Integer id;
    private final Float gpa;
    private final Set<EnrollmentDto> enrollments;
    private final UserAccountDto userAccount;

    public StudentDto(Integer id, Float gpa, Set<EnrollmentDto> enrollments, UserAccountDto userAccount) {
        this.id = id;
        this.gpa = gpa;
        this.enrollments = enrollments;
        this.userAccount = userAccount;
    }

    public Integer getId() {
        return id;
    }

    public Float getGpa() {
        return gpa;
    }

    public Set<EnrollmentDto> getEnrollments() {
        return enrollments;
    }

    public UserAccountDto getUserAccount() {
        return userAccount;
    }

    @Override
    public String toString() {
        return "StudentDto{" +
                "id=" + id +
                ", gpa=" + gpa +
                ", enrollments=" + enrollments +
                ", userAccount=" + userAccount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentDto that = (StudentDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(gpa, that.gpa) &&
                Objects.equals(enrollments, that.enrollments) &&
                Objects.equals(userAccount, that.userAccount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, gpa, enrollments, userAccount);
    }

}