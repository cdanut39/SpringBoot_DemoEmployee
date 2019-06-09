package com.learning.spring.rest.employees.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.learning.spring.rest.employees.model.Employee;
import com.learning.spring.rest.employees.model.Role;
import com.learning.spring.rest.employees.model.User;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

public class UserDTO {

    private int userId;
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 3, max = 32, message = "First name has to be equal to or greater than 3 and less than 20 characters")
    private String firstName;
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 3, max = 32, message = "Last name has to be equal to or greater than 3 and less than 20 characters")
    private String lastName;
    @Enumerated(EnumType.STRING)
    private User.Gender sex;
    //    @Pattern(regexp = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}")
    private long phoneNumber;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @Email
    private String email;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Set<Role> roles;

    public UserDTO() {
    }

    public UserDTO(int userId, @NotBlank(message = "Name cannot be blank") @Size(min = 3, max = 32, message = "First name has to be equal to or greater than 3 and less than 20 characters") String firstName, @NotBlank(message = "Name cannot be blank") @Size(min = 3, max = 32, message = "Last name has to be equal to or greater than 3 and less than 20 characters") String lastName, User.Gender sex, long phoneNumber, String password, @Email String email, Set<Role> roles) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.email = email;
        this.roles = roles;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Employee.Gender getSex() {
        return sex;
    }

    public void setSex(Employee.Gender sex) {
        this.sex = sex;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
