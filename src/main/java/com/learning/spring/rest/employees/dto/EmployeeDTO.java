package com.learning.spring.rest.employees.dto;

import com.learning.spring.rest.employees.model.Department;
import com.learning.spring.rest.employees.model.Employee;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class EmployeeDTO {

    private String name;

    private Department department;

    @Enumerated(EnumType.STRING)
    private Employee.Gender sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Employee.Gender getSex() {
        return sex;
    }

    public void setSex(Employee.Gender sex) {
        this.sex = sex;
    }


}
