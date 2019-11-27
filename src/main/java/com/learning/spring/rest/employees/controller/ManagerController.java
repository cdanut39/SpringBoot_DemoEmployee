package com.learning.spring.rest.employees.controller;


import com.learning.spring.rest.employees.dto.ManagerDTO;
import com.learning.spring.rest.employees.dto.UserDTO;
import com.learning.spring.rest.employees.exceptions.custom.manager.ManagerNotFoundException;
import com.learning.spring.rest.employees.exceptions.custom.manager.ManagerNotValidException;
import com.learning.spring.rest.employees.exceptions.custom.user.UserAlreadyExistsException;
import com.learning.spring.rest.employees.exceptions.handler.ValidationError;
import com.learning.spring.rest.employees.services.ManagerServiceImpl;
import com.learning.spring.rest.employees.utils.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.learning.spring.rest.employees.utils.BindingResultErrors.getErrors;
import static com.learning.spring.rest.employees.utils.Constants.MANAGER_ADDED;

@RestController
public class ManagerController {

    private static final Logger logger = LogManager.getLogger(ManagerController.class);

    private ManagerServiceImpl managerService;

    @Autowired
    public ManagerController(ManagerServiceImpl managerService) {
        this.managerService = managerService;
    }

    /**
     * POST
     */
    @PostMapping("/register/manager")
    public ResponseEntity<Response> addManager(@Valid @RequestBody ManagerDTO employee, BindingResult result) throws ManagerNotValidException, UserAlreadyExistsException {
        Response response = new Response();
        if (result.hasErrors()) {
            List<ValidationError> errors = getErrors(result);
            logger.error("Invalid data for adding new manager");
            throw new ManagerNotValidException("Manager data not valid", errors);
        }
        managerService.save(employee);
        response.setMessage(MANAGER_ADDED);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * GET
     */
    @GetMapping("/manager/{id}")
    public ResponseEntity<UserDTO> getEmployeeById(@PathVariable("id") int id) throws ManagerNotFoundException {
        ManagerDTO getEmployee = managerService.getManagerById(id);
        return new ResponseEntity<>(getEmployee, HttpStatus.OK);
    }
}
