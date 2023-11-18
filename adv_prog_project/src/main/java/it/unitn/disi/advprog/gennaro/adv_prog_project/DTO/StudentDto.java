package it.unitn.disi.advprog.gennaro.adv_prog_project.DTO;

import java.io.Serializable;

/**
 * Data Transfer Object (DTO) representing a Student.
 * This class is Serializable to support data transfer between layers.
 */
public class StudentDto implements Serializable {

    private long id;
    private String name;
    private String surname;

    /**
     * Get the ID of the student.
     *
     * @return the ID of the student
     */
    public long getId() {
        return id;
    }

    /**
     * Set the ID of the student.
     *
     * @param id the ID of the student
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Get the name of the student.
     *
     * @return the name of the student
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the student.
     *
     * @param name the name of the student
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the surname of the student.
     *
     * @return the surname of the student
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Set the surname of the student.
     *
     * @param surname the surname of the student
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }
}
