package com.learning.spring.rest.employees.controller;

import com.learning.spring.rest.employees.dao.DepartmentRepo;
import com.learning.spring.rest.employees.dto.BaseDepartmentDTO;
import com.learning.spring.rest.employees.dto.DepartmentDTO;
import com.learning.spring.rest.employees.exceptions.department.DefaultDepartmentCanNotBeRemovedException;
import com.learning.spring.rest.employees.exceptions.department.DepartmentAlreadyExistsException;
import com.learning.spring.rest.employees.exceptions.department.DepartmentNotFoundByIdException;
import com.learning.spring.rest.employees.exceptions.department.DepartmentNotValidException;
import com.learning.spring.rest.employees.exceptions.employee.EmployeeNotFoundException;
import com.learning.spring.rest.employees.exceptionsHandler.ValidationError;
import com.learning.spring.rest.employees.mappers.DepartmentMapper;
import com.learning.spring.rest.employees.model.Department;
import com.learning.spring.rest.employees.services.DepartmentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class DepartmentController {

    private static final Logger logger = LogManager.getLogger(DepartmentController.class);


    private DepartmentService departmentService;
    private DepartmentRepo departmentRepo;
    private DepartmentMapper departmentMapper;

    @Autowired
    public DepartmentController(DepartmentService departmentService, DepartmentRepo departmentRepo, DepartmentMapper departmentMapper) {
        this.departmentService = departmentService;
        this.departmentRepo = departmentRepo;
        this.departmentMapper = departmentMapper;
    }

    @PostMapping("/department")
    public ResponseEntity<BaseDepartmentDTO> addDepartment(@Valid @RequestBody BaseDepartmentDTO baseDepartmentDTO, BindingResult result) throws DepartmentNotValidException, DepartmentAlreadyExistsException {
        if (result.hasErrors()) {

            List<ValidationError> fieldErrors = result.getFieldErrors().stream()
                    .map(e -> new ValidationError(e.getField(), e.getRejectedValue().toString(), e.getDefaultMessage()))
                    .collect(Collectors.toList());
            logger.error("Invalid data for adding new department");
            throw new DepartmentNotValidException("Department data not valid", fieldErrors);

        }
        Department department = departmentMapper.convertFromBaseDeptDtoToDept(baseDepartmentDTO);
        BaseDepartmentDTO deptDTO = departmentService.addDepartment(department);
        return new ResponseEntity<>(deptDTO, HttpStatus.OK);

    }

    @DeleteMapping("/department/delete/{id}")
    public ResponseEntity<String> deleteDepartmentById(@PathVariable("id") int id) throws DepartmentNotFoundByIdException, DefaultDepartmentCanNotBeRemovedException {
        departmentService.deleteDepartmentById(id);
        logger.info("Successfully removed the department with id={}", id);
        return new ResponseEntity<>("Department with id:" + id + " was successfully removed", HttpStatus.OK);
    }

    @GetMapping("/department/{id}")
    @ResponseBody
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable("id") int id) throws DepartmentNotFoundByIdException, EmployeeNotFoundException {
        DepartmentDTO department = departmentService.getDepartmentById(id);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }
}
