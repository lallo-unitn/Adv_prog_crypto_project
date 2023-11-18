package it.unitn.disi.advprog.gennaro.adv_prog_project.entities;

import jakarta.persistence.*;

/**
 * Entity class representing a teacher in the system.
 * This class is mapped to the "teacher" table in the "public" schema of the database.
 */
@Entity
@Table(name = "teacher", schema = "public")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "taught_course_id")
    private Course taughtCourse;

    @Column(name = "name", length = 30)
    private String name;

    @Column(name = "surname", length = 30)
    private String surname;

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

    public Course getTaughtCourse() {
        return taughtCourse;
    }

    public void setTaughtCourse(Course taughtCourse) {
        this.taughtCourse = taughtCourse;
    }
}