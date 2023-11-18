package it.unitn.disi.advprog.gennaro.adv_prog_project.DTO;

import java.io.Serializable;

/**
 * Data Transfer Object (DTO) representing a Teacher.
 * This class is Serializable to support data transfer between layers.
 */
public class TeacherDto implements Serializable {

    private String name;
    private String surname;

    /**
     * Get the name of the teacher.
     *
     * @return the name of the teacher
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the teacher.
     *
     * @param name the name of the teacher
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the surname of the teacher.
     *
     * @return the surname of the teacher
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Set the surname of the teacher.
     *
     * @param surname the surname of the teacher
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }
}