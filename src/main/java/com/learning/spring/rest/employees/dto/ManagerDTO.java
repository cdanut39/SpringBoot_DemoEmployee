package com.learning.spring.rest.employees.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

public class ManagerDTO extends UserDTO {

    public ManagerDTO() {
    }

    public ManagerDTO(Set<EmployeeDTO> employees) {
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
