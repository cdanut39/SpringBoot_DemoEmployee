package com.learning.spring.rest.employees.dto;

import com.learning.spring.rest.employees.model.Employee;

import java.util.List;

public class DepartmentDTO {

    private int deptId;
    private String deptName;
    private List<Employee> employees;

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }


}
