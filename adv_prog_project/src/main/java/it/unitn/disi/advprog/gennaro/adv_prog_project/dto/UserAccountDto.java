package it.unitn.disi.advprog.gennaro.adv_prog_project.dto;

import it.unitn.disi.advprog.gennaro.adv_prog_project.entities.UserAccount;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link UserAccount}
 */
public class UserAccountDto implements Serializable {
    private final Integer id;
    private final String username;
    private final String password;
    private final String name;
    private final String surname;
    private final String role;

    public UserAccountDto(Integer id, String username, String password, String name, String surname, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAccountDto entity = (UserAccountDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.username, entity.username) &&
                Objects.equals(this.password, entity.password) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.surname, entity.surname) &&
                Objects.equals(this.role, entity.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, name, surname, role);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "username = " + username + ", " +
                "password = " + password + ", " +
                "name = " + name + ", " +
                "surname = " + surname + ", " +
                "role = " + role + ")";
    }
}