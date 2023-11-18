package it.unitn.disi.advprog.gennaro.adv_prog_project.entities;

import jakarta.persistence.*;

/**
 * Entity class representing an enrollment in the system.
 * This class is mapped to the "enrollment" table in the database.
 */
@Entity
@Table(name = "enrollment")
public class Enrollment {
    @EmbeddedId
    private EnrollmentId id;

    @MapsId("courseId")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @MapsId("stMatriculation")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "st_matriculation", nullable = false)
    private Student stMatriculation;

    @Column(name = "grade")
    private Integer grade;

    public EnrollmentId getId() {
        return id;
    }

    public void setId(EnrollmentId id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Student getStMatriculation() {
        return stMatriculation;
    }

    public void setStMatriculation(Student stMatriculation) {
        this.stMatriculation = stMatriculation;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

}