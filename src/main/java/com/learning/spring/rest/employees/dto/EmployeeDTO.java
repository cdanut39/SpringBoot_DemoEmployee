package com.learning.spring.rest.employees.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class EmployeeDTO extends UserDTO {

    private int salary;
    private Boolean bonus;
    @JsonProperty(value = "firstDay")
    private LocalDate startDate;
    private String communityName;


    public EmployeeDTO() {
    }


    public EmployeeDTO(int salary, Boolean bonus, LocalDate startDate, String communityName) {
        this.salary = salary;
        this.bonus = bonus;
        this.startDate = startDate;
        this.communityName = communityName;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Boolean getBonus() {
        return bonus;
    }

    public void setBonus(Boolean bonus) {
        this.bonus = bonus;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }
}
