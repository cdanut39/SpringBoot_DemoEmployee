package com.learning.spring.rest.employees.dto;

import com.learning.spring.rest.employees.model.Employee;

import java.time.LocalDate;

public class EmployeeDTO {

    private int id;
    private String name;
    private Employee.Gender sex;
    private String deptName;
    private LocalDate startDate;

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

    public Employee.Gender getSex() {
        return sex;
    }

    public void setSex(Employee.Gender sex) {
        this.sex = sex;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
}
