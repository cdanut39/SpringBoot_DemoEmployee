package com.learning.spring.rest.employees.dto;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class BaseDepartmentDTO {
    private int deptId;
    @Size(min = 3, max = 16, message = "Name has to be equal to or greater than 3 and less than 16 characters")
    @Pattern(regexp = "^[A-Z]*")
    private String deptName;

    public BaseDepartmentDTO() {
    }

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
}
