package it.unitn.disi.advprog.gennaro.adv_prog_wildfly.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "enrollment", schema = "public")
public class Enrollment {

    @EmbeddedId
    private EnrollmentId id;

    @MapsId
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @MapsId
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "st_matriculation", nullable = false)
    private Student stMatriculation;

    @Column(name = "grade")
    private Integer grade;

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Student getStMatriculation() {
        return stMatriculation;
    }

    public void setStMatriculation(Student stMatriculation) {
        this.stMatriculation = stMatriculation;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public EnrollmentId getId() {
        return id;
    }

    public void setId(EnrollmentId id) {
        this.id = id;
    }
}