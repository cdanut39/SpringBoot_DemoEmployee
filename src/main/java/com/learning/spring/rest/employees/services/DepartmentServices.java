package com.learning.spring.rest.employees.services;

import com.learning.spring.rest.employees.controller.EmployeeController;
import com.learning.spring.rest.employees.dao.DepartmentRepo;
import com.learning.spring.rest.employees.exceptions.DepartmentNotFoundException;
import com.learning.spring.rest.employees.exceptions.EmployeeNotFoundException;
import com.learning.spring.rest.employees.model.Department;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DepartmentServices {
    private static final Logger logger = LogManager.getLogger(EmployeeController.class);

    @Autowired
    DepartmentRepo departmentRepo;


    public void deleteDepartmentById(int id) throws DepartmentNotFoundException {
        Department department = departmentRepo.findById(id).orElseThrow(() -> new DepartmentNotFoundException("Department not found with id=" + id, id));
        departmentRepo.delete(department);
    }

    public Department getDepartmentById(int id) throws EmployeeNotFoundException, DepartmentNotFoundException {
        Department department = departmentRepo.findById(id).orElseThrow(() -> new DepartmentNotFoundException("Employee not found with id=" + id, id));
        logger.info("Information for department with id=" + id + ": Name={}", department.getDeptName());
        return department;
    }
}
