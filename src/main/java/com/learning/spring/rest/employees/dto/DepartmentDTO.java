package com.learning.spring.rest.employees.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

public class DepartmentDTO {

    @JsonProperty(access = READ_ONLY)
    private String companyName;
    private int deptId;
    private String deptName;
    @JsonProperty(access = READ_ONLY)
    private List<EmployeeDTO> employees;


    public int getDeptId() {
        return deptId;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
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

    public List<EmployeeDTO> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeDTO> employees) {
        this.employees = employees;
    }
}
