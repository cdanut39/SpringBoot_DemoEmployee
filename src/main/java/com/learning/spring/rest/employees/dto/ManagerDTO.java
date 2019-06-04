package com.learning.spring.rest.employees.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.learning.spring.rest.employees.model.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

public class ManagerDTO extends UserDTO {

    public ManagerDTO() {
    }

    public ManagerDTO(Set<EmployeeDTO> employees) {
        this.employees = employees;
    }

    public ManagerDTO(int userId, @NotBlank(message = "Name cannot be blank") @Size(min = 3, max = 32, message = "First name has to be equal to or greater than 3 and less than 20 characters") String firstName, @NotBlank(message = "Name cannot be blank") @Size(min = 3, max = 32, message = "Last name has to be equal to or greater than 3 and less than 20 characters") String lastName, User.Gender sex, long phoneNumber, String password, @Email String email, Set<EmployeeDTO> employees) {
        super(userId, firstName, lastName, sex, phoneNumber, password, email);
        this.employees = employees;
    }

    @JsonProperty(access = READ_ONLY)
    private Set<EmployeeDTO> employees;

    public Set<EmployeeDTO> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<EmployeeDTO> employees) {
        this.employees = employees;
    }

}
