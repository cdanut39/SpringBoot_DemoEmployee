package com.learning.spring.rest.employees.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class EmployeePUTReq_DTO {
    private int id;
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 3, max = 32, message = "Name has to be equal to or greater than 3 and less than 32 characters")
    private String name;
    @Min(value = 2000, message = "Minimum salary is 2000 EURO")
    private int salary;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean bonus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isBonus() {
        return bonus;
    }

    public void setBonus(boolean bonus) {
        this.bonus = bonus;
    }

    public EmployeePUTReq_DTO() {
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
