package it.unitn.disi.advprog.gennaro.adv_prog_project.DTO;

import java.io.Serializable;


/**
 * Data Transfer Object (DTO) representing an Enrollment.
 * This class is Serializable to support data transfer between layers.
 */
public class EnrollmentDto implements Serializable {

    private String courseName;
    private int studentMatriculation;
    private int grade;

    /**
     * Get the grade of the enrollment.
     *
     * @return the grade of the enrollment
     */
    public int getGrade() {
        return grade;
    }

    /**
     * Set the grade of the enrollment.
     *
     * @param grade the grade of the enrollment
     */
    public void setGrade(int grade) {
        this.grade = grade;
    }

    /**
     * Get the name of the course associated with the enrollment.
     *
     * @return the name of the course
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * Set the name of the course associated with the enrollment.
     *
     * @param courseName the name of the course
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * Get the matriculation number of the student associated with the enrollment.
     *
     * @return the matriculation number of the student
     */
    public int getStudentMatriculation() {
        return studentMatriculation;
    }

    /**
     * Set the matriculation number of the student associated with the enrollment.
     *
     * @param studentMatriculation the matriculation number of the student
     */
    public void setStudentMatriculation(int studentMatriculation) {
        this.studentMatriculation = studentMatriculation;
    }
}