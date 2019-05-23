package com.learning.spring.rest.employees.dto;

import java.util.List;

public class DepartmentDTO {

    private String companyName;
    private int deptId;
    private String deptName;
    private List<BaseEmployeeDTO> employees;


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

    public List<BaseEmployeeDTO> getEmployees() {
        return employees;
    }

    public void setEmployees(List<BaseEmployeeDTO> employees) {
        this.employees = employees;
    }
}
