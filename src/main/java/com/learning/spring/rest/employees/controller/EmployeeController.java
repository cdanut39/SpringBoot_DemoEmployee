package com.learning.spring.rest.employees.controller;

import com.learning.spring.rest.employees.dto.BaseCommunityDTO;
import com.learning.spring.rest.employees.dto.EmployeeDTO;
import com.learning.spring.rest.employees.dto.UserDTO;
import com.learning.spring.rest.employees.exceptions.community.CommunityNotFoundByNameException;
import com.learning.spring.rest.employees.exceptions.employee.EmployeeNotFoundException;
import com.learning.spring.rest.employees.exceptions.employee.EmployeeNotValidException;
import com.learning.spring.rest.employees.exceptions.user.UserAlreadyExistsException;
import com.learning.spring.rest.employees.exceptions_handler.ValidationError;
import com.learning.spring.rest.employees.services.EmployeeServiceImpl;
import com.learning.spring.rest.employees.utils.Response;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

import static com.learning.spring.rest.employees.utils.Constants.*;

@RestController
public class EmployeeController {

    private static final Logger logger = LogManager.getLogger(EmployeeController.class);

    private EmployeeServiceImpl employeeServices;
    private Response response;

    @Autowired
    public EmployeeController(EmployeeServiceImpl employeeServices, Response response) {
        this.employeeServices = employeeServices;
        this.response = response;
    }

//    @GetMapping(value = "/employees/orderBy/salary/DESC", produces = {"application/json"})
//    public List<EmployeeWithCommunityNameDTO> getEmployeesOrderdByCriteriaDESC() {
//        List<Employee> employees = repo.getEmployeesOrderBySalary();
//        List<EmployeeWithCommunityNameDTO> sortedEmployees = employees.stream().map(userMapper::convertFromEmpToEmpDto).collect(Collectors.toList());
//        return sortedEmployees;
//    }
//
//    @GetMapping(value = "/employees/orderBy/{criteria}/ASC", produces = {"application/json"})
//    public List<EmployeeWithCommunityNameDTO> getEmployeesOrderdByCriteriaASC(@PathVariable("criteria") String criteria) {
//        List<Employee> employees = repo.findAll(new Sort(Sort.Direction.ASC, criteria));
//        List<EmployeeWithCommunityNameDTO> sortedEmployees = employees.stream().map(userMapper::convertFromEmpToEmpDto).collect(Collectors.toList());
//        return sortedEmployees;
//    }

    @GetMapping(value = "/employees/orderBy/{criteria}/{direction}")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(@PathVariable("criteria") String criteria, @PathVariable("direction") String direction) {
        return new ResponseEntity<>(employeeServices.getEmployeesSortedByCriteria(criteria, direction), HttpStatus.OK);
    }

    @GetMapping(value = "/employees")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        return new ResponseEntity<>(employeeServices.getAllEmployees(), HttpStatus.OK);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<UserDTO> getEmployeeById(@PathVariable("id") int id) throws EmployeeNotFoundException {

        EmployeeDTO getEmployee = employeeServices.getEmployeeById(id);
        return new ResponseEntity<>(getEmployee, HttpStatus.OK);
    }

    @PostMapping("/register/employee")
    @ApiOperation(
            value = "Add a new employee",
            notes = "Can be called only by users with ADMIN or MANAGER roles."
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Employee added successfully"),
            @ApiResponse(code = 400, message = "Employee data not valid"),
            @ApiResponse(code = 404, message = "Employee not found")
    })
    public ResponseEntity<Response> addEmployee(@Valid @RequestBody EmployeeDTO employee, BindingResult result) throws EmployeeNotValidException, UserAlreadyExistsException {
        if (result.hasErrors()) {

            List<ValidationError> fieldErrors = result.getFieldErrors().stream()
                    .map(e -> new ValidationError(e.getField(), e.getRejectedValue().toString(), e.getDefaultMessage()))
                    .collect(Collectors.toList());
            logger.error("Invalid data for adding new employee");
            throw new EmployeeNotValidException("Employee data not valid", fieldErrors);
        }
        employeeServices.save(employee);
        response.setMessage(EMPLOYEE_ADDED);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //    @PutMapping("/updateEmployee/{id}")
//    public ResponseEntity<String> updateEmployee(@PathVariable("id") int id, @Valid @RequestBody EmployeePUTReq_DTO employee, BindingResult result) throws EmployeeNotValidException, EmployeeNotFoundException {
//        if (result.hasErrors()) {
//
//            List<ValidationError> validationErrors = result.getFieldErrors().stream()
//                    .map(e -> new ValidationError(e.getField(), e.getRejectedValue().toString(), e.getDefaultMessage()))
//                    .collect(Collectors.toList());
//            logger.info("updateEmployee failed ---Start");
//            for (ValidationError validationError : validationErrors) {
//                logger.error("Invalid data for field: {}", validationError.getField());
//            }
//            logger.info("updateEmployee failed ---End");
//            throw new EmployeeNotValidException("Employee data not valid", validationErrors);
//        }
//        EmployeePUTResponse_DTO updatedEmp = employeeServices.updateEmployee(id, employee);
//        return new ResponseEntity<>(EMPLOYEE_MODIFIED, HttpStatus.OK);
//    }
//
    @DeleteMapping("/employee/{id}")
    public ResponseEntity<Response> deleteEmployee(@PathVariable("id") int id) throws EmployeeNotFoundException {
        employeeServices.removeEmployee(id);
        response.setMessage(EMPLOYEE_REMOVED);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/employee/{empID}/setCommunity")
    public ResponseEntity<Response> assignCommunity(@PathVariable("empID") int empId, @Valid @RequestBody BaseCommunityDTO community) throws EmployeeNotFoundException, CommunityNotFoundByNameException {
        employeeServices.assignCommunity(empId, community);
        response.setMessage(COMMUNITY_ASSIGNED);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
