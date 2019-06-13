package com.learning.spring.rest.employees.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.learning.spring.rest.employees.model.Employee;
import com.learning.spring.rest.employees.model.Role;
import com.learning.spring.rest.employees.model.User;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

public class UserDTO {

    @ApiModelProperty(readOnly = true)
    private int userId;
    @ApiModelProperty(required = true)
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 3, max = 32, message = "First name has to be equal to or greater than 3 and less than 20 characters")
    private String firstName;
    @ApiModelProperty(required = true)
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 3, max = 32, message = "Last name has to be equal to or greater than 3 and less than 20 characters")
    private String lastName;
    @ApiModelProperty(required = true)
    @Enumerated(EnumType.STRING)
    private User.Gender sex;
    @ApiModelProperty(required = true)
//    @Pattern(regexp = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}")
    private long phoneNumber;
    @ApiModelProperty(required = true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @ApiModelProperty(required = true)
    @Email
    private String email;

    @JsonIgnore
    private Set<Role> roles;

    public UserDTO() {
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
