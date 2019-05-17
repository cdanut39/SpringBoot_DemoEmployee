package com.learning.spring.rest.employees.controller;

import com.learning.spring.rest.employees.exceptions.DepartmentNotFoundException;
import com.learning.spring.rest.employees.exceptions.EmployeeNotFoundException;
import com.learning.spring.rest.employees.model.Department;
import com.learning.spring.rest.employees.services.DepartmentServices;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DepartmentController {

    private static final Logger logger = LogManager.getLogger(DepartmentController.class);

    @Autowired
    DepartmentServices departmentServices;

    @DeleteMapping("/department/delete/{id}")
    public ResponseEntity<String> deleteDepartmentById(@PathVariable("id") int id) throws DepartmentNotFoundException {
        departmentServices.deleteDepartmentById(id);
        logger.info("Successfully removed the department with id={}", id);
        return new ResponseEntity<String>("Department with id:" + id + " was successfully removed", HttpStatus.OK);
    }

    @GetMapping("/department/{id}")
    @ResponseBody
    public ResponseEntity<Department> getDepartmentById(@PathVariable("id") int id) throws DepartmentNotFoundException, EmployeeNotFoundException {

        Department department = departmentServices.getDepartmentById(id);
        return new ResponseEntity<Department>(department, HttpStatus.OK);
    }
}
