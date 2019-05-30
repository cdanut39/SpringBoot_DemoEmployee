package com.learning.spring.rest.employees.dto;

import com.learning.spring.rest.employees.model.Employee;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class EmployeePUTResponse_DTO {

    private int id;
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 3, max = 32, message = "Name has to be equal to or greater than 3 and less than 32 characters")
    private String name;
    private Employee.Gender sex;
    private String deptName;

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

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

}
