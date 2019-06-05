package com.learning.spring.rest.employees.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@DiscriminatorValue("Employee")
public class Employee extends User {

    private int salary;
    private Boolean bonus;
    @JsonProperty(value = "firstDay")
    private LocalDate startDate;
    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "department_ID")
    private Department department;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "project_ID")
    private Project project;

    @Transient
    private String deptName;

    @Transient
    private String projectName;

    public Employee() {
    }

    public Employee(int id, String name, int salary) {
        this.salary = salary;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setDeptName(Employee employee) {
        this.deptName = employee.getDepartment().getDeptName();
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptName() {
        return deptName;
    }

    public Boolean getBonus() {
        return bonus;
    }

    public void setBonus(Boolean bonus) {
        this.bonus = bonus;
    }

}
