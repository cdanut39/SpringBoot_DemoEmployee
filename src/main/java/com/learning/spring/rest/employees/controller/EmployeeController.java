package com.learning.spring.rest.employees.controller;

import com.learning.spring.rest.employees.dao.EmployeeRepo;
import com.learning.spring.rest.employees.dto.EmployeeDTO;
import com.learning.spring.rest.employees.exceptions.EmployeeNotFoundException;
import com.learning.spring.rest.employees.model.Employee;
import com.learning.spring.rest.employees.services.EmployeeServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class EmployeeController {

    private static final Logger logger = LogManager.getLogger(EmployeeController.class);

    @Autowired
    EmployeeRepo repo;

    @Autowired
    EmployeeServiceImpl employeeServices;

    @GetMapping(value = "/employees/orderBy/salary/DESC", produces = {"application/json"})
    public List<Employee> getEmployeesOrderdByCriteriaDESC() {
        return repo.getEmployeesOrderBySalary();
    }

    @GetMapping(value = "/employees/orderBy/{criteria}/ASC", produces = {"application/json"})
    public List<Employee> getEmployeesOrderdByCriteriaASC(@PathVariable("criteria") String criteria) {
        return repo.findAll(new Sort(Sort.Direction.ASC, criteria));
    }


    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") int id) throws EmployeeNotFoundException {

        Employee getEmployee = employeeServices.getEmployeeById(id);
        return new ResponseEntity<>(getEmployee, HttpStatus.OK);
    }

    @PostMapping("/employee")
    public EmployeeDTO addEmployee(@Valid @RequestBody Employee employee) {

        EmployeeDTO savedEmp = employeeServices.save(employee);

        return savedEmp;
    }

    @PutMapping("/updateEmployee/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable("id") int id, @Valid @RequestBody Employee employee) {
        EmployeeDTO updatedEmp = employeeServices.updateEmployee(id, employee);
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
