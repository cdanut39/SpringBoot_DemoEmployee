package com.learning.spring.rest.employees.utils;

import com.learning.spring.rest.employees.dao.DepartmentRepo;
import com.learning.spring.rest.employees.model.Department;
import com.learning.spring.rest.employees.model.Employee;

public class EmployeeUtils {

    public void setExistingDepartment(DepartmentRepo departmentRepo, Employee employee) {
        String deptName = employee.getDepartment().getDeptName();
        Department department = departmentRepo.findByDeptName(deptName);

        if (department != null) {
            employee.setDepartment(department);
        }
    }
}
