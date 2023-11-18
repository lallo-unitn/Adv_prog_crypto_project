package it.unitn.disi.advprog.gennaro.adv_prog_project.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Entity class representing a student in the system.
 * This class is mapped to the "student" table in the database.
 */
@Entity
@Table(name = "student")
public class Student {

    @Id
    @Column(name = "matriculation", nullable = false)
    private Integer id;

    @Size(max = 30)
    @Column(name = "name", length = 30)
    private String name;

    @Size(max = 30)
    @Column(name = "surname", length = 30)
    private String surname;

    @OneToMany(mappedBy = "stMatriculation")
    private Set<Enrollment> enrollments = new LinkedHashSet<>();

    public Set<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(Set<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}