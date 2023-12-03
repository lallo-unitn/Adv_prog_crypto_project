package it.unitn.disi.advprog.gennaro.adv_prog_project.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "teacher")
public class Teacher {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "taught_course", nullable = false)
    private String taughtCourse;

    @OneToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_account_id")
    private UserAccount userAccount;

    @OneToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "course_id")
    private Course course;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTaughtCourse() {
        return taughtCourse;
    }

    public void setTaughtCourse(String taughtCourse) {
        this.taughtCourse = taughtCourse;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

}