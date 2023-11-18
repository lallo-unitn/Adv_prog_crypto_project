package it.unitn.disi.advprog.gennaro.adv_prog_project.DTO;

import java.io.Serializable;


/**
 * Data Transfer Object (DTO) representing a Course.
 * This class is Serializable to support data transfer between layers.
 */
public class CourseDto implements Serializable {

    private String name;

    /**
     * Get the name of the course.
     *
     * @return the name of the course
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the course.
     *
     * @param name the name of the course
     */
    public void setName(String name) {
        this.name = name;
    }
}