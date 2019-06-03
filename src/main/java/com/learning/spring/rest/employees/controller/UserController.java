package com.learning.spring.rest.employees.controller;

import com.learning.spring.rest.employees.dto.UserDTO;
import com.learning.spring.rest.employees.exceptions.employee.EmployeeNotValidException;
import com.learning.spring.rest.employees.exceptionsHandler.ValidationError;
import com.learning.spring.rest.employees.services.DepartmentServiceImpl;
import com.learning.spring.rest.employees.services.UserServiceImpl;
import com.learning.spring.rest.employees.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

import static com.learning.spring.rest.employees.utils.Constants.EMPLOYEE_ADDED;
import static com.learning.spring.rest.employees.utils.Constants.USER_ADDED;

@RestController
public class UserController {

    private UserServiceImpl userService;
    private DepartmentServiceImpl departmentService;
    private Response response;

    @Autowired
    public UserController(UserServiceImpl userService, DepartmentServiceImpl departmentService, Response response) {
        this.userService = userService;
        this.departmentService = departmentService;
        this.response = response;
    }

    @PostMapping("/user")
    public<T extends UserDTO> ResponseEntity<Response> addEmployee(@Valid @RequestBody T employee, BindingResult result) throws EmployeeNotValidException {
        if (result.hasErrors()) {

            List<ValidationError> fieldErrors = result.getFieldErrors().stream()
                    .map(e -> new ValidationError(e.getField(), e.getRejectedValue().toString(), e.getDefaultMessage()))
                    .collect(Collectors.toList());
//            logger.error("Invalid data for adding new employee");
            throw new EmployeeNotValidException("Employee data not valid", fieldErrors);

        }

        UserDTO savedEmp = userService.save(employee);
        response.setMessage(EMPLOYEE_ADDED);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
