package it.unitn.disi.advprog.gennaro.adv_prog_project.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

/**
 * Embeddable class representing the composite primary key for Enrollment.
 * This class is used as the composite key for the Enrollment entity.
 */
@Embeddable
public class EnrollmentId implements Serializable {
    private static final long serialVersionUID = -7389469962927954713L;
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