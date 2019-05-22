package com.learning.spring.rest.employees.controller;

import com.learning.spring.rest.employees.dto.DepartmentDTO;
import com.learning.spring.rest.employees.exceptions.DepartmentNotFoundException;
import com.learning.spring.rest.employees.exceptions.EmployeeNotFoundException;
import com.learning.spring.rest.employees.services.DepartmentService;
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
    DepartmentService departmentService;

    @DeleteMapping("/department/delete/{id}")
    public ResponseEntity<String> deleteDepartmentById(@PathVariable("id") int id) throws DepartmentNotFoundException {
        departmentService.deleteDepartmentById(id);
        logger.info("Successfully removed the department with id={}", id);
        return new ResponseEntity<>("Department with id:" + id + " was successfully removed", HttpStatus.OK);
    }

    @GetMapping("/department/{id}")
    @ResponseBody
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable("id") int id) throws DepartmentNotFoundException, EmployeeNotFoundException {
        DepartmentDTO department = departmentService.getDepartmentById(id);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }
}
