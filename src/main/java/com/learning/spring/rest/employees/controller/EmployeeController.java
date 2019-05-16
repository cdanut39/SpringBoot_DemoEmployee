package com.learning.spring.rest.employees.controller;

import com.learning.spring.rest.employees.dao.DepartmentRepo;
import com.learning.spring.rest.employees.dao.EmployeeRepo;
import com.learning.spring.rest.employees.dto.EmployeeDTO;
import com.learning.spring.rest.employees.exceptions.EmployeeNotFoundException;
import com.learning.spring.rest.employees.model.Department;
import com.learning.spring.rest.employees.model.Employee;
import com.learning.spring.rest.employees.services.EmployeeServices;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    private static final Logger logger = LogManager.getLogger(EmployeeController.class);

    @Autowired
    EmployeeRepo repo;

    @Autowired
    EmployeeServices employeeServices;

    @GetMapping(value = "/employees/orderBy/salary/DESC", produces = {"application/json"})
    @ResponseBody
    public List<Employee> getEmployeesOrderdByCriteriaDESC() {
        return repo.getEmployeesOrderBySalary();
    }

    @GetMapping(value = "/employees/orderBy/{criteria}/ASC", produces = {"application/json"})
    @ResponseBody
    public List<Employee> getEmployeesOrderdByCriteriaASC(@PathVariable("criteria") String criteria) {
        return repo.findAll(new Sort(Sort.Direction.ASC, criteria));
    }


//    @GetMapping("/employee/{id}")
//    @ResponseBody
//    public Employee getEmployeeById(@PathVariable("id") int id) throws EmployeeNotFoundException {
//
//        return repo.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + id));
//    }

    @GetMapping("/employee/{id}")
    @ResponseBody
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") int id) throws EmployeeNotFoundException {

        Employee employee = repo.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id=" + id, id));
        logger.info("Information for employee with id=" + id + ": Name={}, Salary={}", employee.getName(), employee.getSalary());
        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }

    @PostMapping("/employee")
    public Employee addEmployee(@RequestBody Employee employee) {

        Employee savedEmp = employeeServices.save(employee);

        return savedEmp;
    }

//    @PutMapping("/employee/{id}")
//    public Employee updateEmployee(@RequestBody @PathVariable("id") int id) {
//        Employee updatedEmp = repo.getOne(id);
//        updatedEmp.setName();
//        repo.save(updatedEmp);
//        return updatedEmp;
//    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") int id) throws EmployeeNotFoundException {
        Employee employee = repo.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Couldn't delete. Employee with id=" + id + " doesn't exist", id));
        repo.delete(employee);
        logger.info("Successfully removed employee with id={},{}", employee.getId(), employee.getName());
        return new ResponseEntity<String>("Employee " + id + " was successfully removed", HttpStatus.OK);
    }


}
