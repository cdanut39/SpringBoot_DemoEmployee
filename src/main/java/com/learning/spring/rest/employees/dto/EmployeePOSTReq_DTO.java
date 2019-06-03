package com.learning.spring.rest.employees.dto;

import javax.validation.constraints.Min;

public class EmployeePOSTReq_DTO extends UserDTO {
    @Min(value = 2000, message = "Minimum salary is 2000 EURO")
    private int salary;

    public EmployeePOSTReq_DTO() {
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
