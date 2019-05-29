package com.learning.spring.rest.employees.controller;

import com.learning.spring.rest.employees.dao.EmployeeRepo;
import com.learning.spring.rest.employees.dto.BaseEmployeeDTO;
import com.learning.spring.rest.employees.dto.EmployeeWithDeptNameDTO;
import com.learning.spring.rest.employees.exceptions.employee.EmployeeNotFoundException;
import com.learning.spring.rest.employees.exceptions.employee.EmployeeNotValidException;
import com.learning.spring.rest.employees.exceptionsHandler.ValidationError;
import com.learning.spring.rest.employees.mappers.EmployeeMapper;
import com.learning.spring.rest.employees.model.Employee;
import com.learning.spring.rest.employees.services.EmployeeServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class EmployeeController {

    private static final Logger logger = LogManager.getLogger(EmployeeController.class);

    private EmployeeRepo repo;
    private EmployeeServiceImpl employeeServices;
    private EmployeeMapper employeeMapper;

    @Autowired
    public EmployeeController(EmployeeRepo repo, EmployeeServiceImpl employeeServices, EmployeeMapper employeeMapper) {
        this.repo = repo;
        this.employeeServices = employeeServices;
        this.employeeMapper = employeeMapper;
    }

    @GetMapping(value = "/employees/orderBy/salary/DESC", produces = {"application/json"})
    public List<EmployeeWithDeptNameDTO> getEmployeesOrderdByCriteriaDESC() {
        List<Employee> employees = repo.getEmployeesOrderBySalary();
        List<EmployeeWithDeptNameDTO> sortedEmployees = employees.stream().map(employeeMapper::convertFromEmpToEmpDto).collect(Collectors.toList());
        return sortedEmployees;
    }

    @GetMapping(value = "/employees/orderBy/{criteria}/ASC", produces = {"application/json"})
    public List<EmployeeWithDeptNameDTO> getEmployeesOrderdByCriteriaASC(@PathVariable("criteria") String criteria) {
        List<Employee> employees = repo.findAll(new Sort(Sort.Direction.ASC, criteria));
        List<EmployeeWithDeptNameDTO> sortedEmployees = employees.stream().map(employeeMapper::convertFromEmpToEmpDto).collect(Collectors.toList());
        return sortedEmployees;
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<BaseEmployeeDTO> getEmployeeById(@PathVariable("id") int id) throws EmployeeNotFoundException {

        BaseEmployeeDTO getEmployee = employeeServices.getEmployeeById(id);
        return new ResponseEntity<>(getEmployee, HttpStatus.OK);
    }

    @PostMapping("/employee")
    public BaseEmployeeDTO addEmployee(@Valid @RequestBody Employee employee, BindingResult result) throws EmployeeNotValidException {
        if (result.hasErrors()) {

            List<ValidationError> fieldErrors = result.getFieldErrors().stream()
                    .map(e -> new ValidationError(e.getField(), e.getRejectedValue().toString(), e.getDefaultMessage()))
                    .collect(Collectors.toList());
            logger.error("Invalid data for adding new employee");
            throw new EmployeeNotValidException("Employee data not valid", fieldErrors);

        }

        BaseEmployeeDTO savedEmp = employeeServices.save(employee);

        return savedEmp;
    }

    @PutMapping("/updateEmployee/{id}")
    public ResponseEntity<BaseEmployeeDTO> updateEmployee(@PathVariable("id") int id, @Valid @RequestBody Employee employee, BindingResult result) throws EmployeeNotValidException, EmployeeNotFoundException {
        if (result.hasErrors()) {

            List<ValidationError> validationErrors = result.getFieldErrors().stream()
                    .map(e -> new ValidationError(e.getField(), e.getRejectedValue().toString(), e.getDefaultMessage()))
                    .collect(Collectors.toList());
            logger.info("updateEmployee failed ---Start");
            for (ValidationError validationError : validationErrors) {
                logger.error("Invalid data for field: {}", validationError.getField());
            }
            logger.info("updateEmployee failed ---End");
            throw new EmployeeNotValidException("Employee data not valid", validationErrors);
        }
        BaseEmployeeDTO updatedEmp = employeeServices.updateEmployee(id, employee);
        return new ResponseEntity<>(updatedEmp, HttpStatus.OK);
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") int id) throws EmployeeNotFoundException {
        Employee employee = repo.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Couldn't delete. Employee with id=" + id + " doesn't exist", id));
        repo.delete(employee);
        logger.info("Successfully removed employee with id={},{}", employee.getId(), employee.getName());
        return new ResponseEntity<>("Employee " + id + " was successfully removed", HttpStatus.OK);
    }


}
