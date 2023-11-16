package it.unitn.disi.advprog.gennaro.adv_prog_wildfly.DTO;

import it.unitn.disi.advprog.gennaro.adv_prog_wildfly.entities.EnrollmentId;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link EnrollmentId}
 */
public class EnrollmentIdDto implements Serializable {
    private final Integer courseId;
    private final Integer stMatriculation;

    public EnrollmentIdDto(Integer courseId, Integer stMatriculation) {
        this.courseId = courseId;
        this.stMatriculation = stMatriculation;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public Integer getStMatriculation() {
        return stMatriculation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnrollmentIdDto entity = (EnrollmentIdDto) o;
        return Objects.equals(this.courseId, entity.courseId) &&
                Objects.equals(this.stMatriculation, entity.stMatriculation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, stMatriculation);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "courseId = " + courseId + ", " +
                "stMatriculation = " + stMatriculation + ")";
    }
}