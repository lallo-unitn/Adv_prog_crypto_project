package it.unitn.disi.advprog.gennaro.adv_prog_wildfly.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "teacher", schema = "public")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "taught_course_id")
    private Course taughtCourse;

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

    //TODO [JPA Buddy] generate columns from DB
}