package com.learning.spring.rest.employees.dto;

public class EmployeeWithDeptNameDTO extends BaseEmployeeDTO {

    private String deptName;

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}
