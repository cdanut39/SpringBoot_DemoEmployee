package com.learning.spring.rest.employees.controller;


import com.learning.spring.rest.employees.dto.ManagerDTO;
import com.learning.spring.rest.employees.exceptions.manager.ManagerNotValidException;
import com.learning.spring.rest.employees.exceptions.user.UserAlreadyExistsException;
import com.learning.spring.rest.employees.exceptions_handler.ValidationError;
import com.learning.spring.rest.employees.services.ManagerServiceImpl;
import com.learning.spring.rest.employees.utils.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

import static com.learning.spring.rest.employees.utils.Constants.MANAGER_ADDED;

@RestController
public class ManagerController {

    private static final Logger logger = LogManager.getLogger(EmployeeController.class);

    private ManagerServiceImpl managerService;
    private Response response;

    @Autowired
    public ManagerController(ManagerServiceImpl managerService, Response response) {
        this.managerService = managerService;
        this.response = response;
    }


    @PostMapping("/register/manager")
    public ResponseEntity<Response> addManager(@Valid @RequestBody ManagerDTO employee, BindingResult result) throws ManagerNotValidException, UserAlreadyExistsException {
        if (result.hasErrors()) {

            List<ValidationError> fieldErrors = result.getFieldErrors().stream()
                    .map(e -> new ValidationError(e.getField(), e.getRejectedValue().toString(), e.getDefaultMessage()))
                    .collect(Collectors.toList());
            logger.error("Invalid data for adding new manager");
            throw new ManagerNotValidException("Manager data not valid", fieldErrors);
        }
        managerService.save(employee);
        response.setMessage(MANAGER_ADDED);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
