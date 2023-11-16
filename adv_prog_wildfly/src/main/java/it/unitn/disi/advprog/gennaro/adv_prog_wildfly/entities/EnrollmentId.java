package it.unitn.disi.advprog.gennaro.adv_prog_wildfly.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EnrollmentId implements Serializable {
    private static final long serialVersionUID = -6010027856523797998L;
    @Column(name = "course_id", nullable = false)
    private Integer courseId;

    @Column(name = "st_matriculation", nullable = false)
    private Integer stMatriculation;

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getStMatriculation() {
        return stMatriculation;
    }

    public void setStMatriculation(Integer stMatriculation) {
        this.stMatriculation = stMatriculation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EnrollmentId entity = (EnrollmentId) o;
        return Objects.equals(this.stMatriculation, entity.stMatriculation) &&
                Objects.equals(this.courseId, entity.courseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stMatriculation, courseId);
    }

}