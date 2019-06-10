package com.learning.spring.rest.employees.controller;

import com.learning.spring.rest.employees.dto.UserDTO;
import com.learning.spring.rest.employees.exceptions.user.UserAlreadyExistsException;
import com.learning.spring.rest.employees.exceptions.user.UserNotValidException;
import com.learning.spring.rest.employees.exceptions_handler.ValidationError;
import com.learning.spring.rest.employees.services.UserServiceImpl;
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

import static com.learning.spring.rest.employees.utils.Constants.USER_ADDED;

@RestController
public class UserController {

    private static final Logger logger = LogManager.getLogger(UserController.class);

    private UserServiceImpl userService;
    private Response response;

    @Autowired
    public UserController(UserServiceImpl userService, Response response) {
        this.userService = userService;
        this.response = response;
    }

    @PostMapping("/register/user")
    public ResponseEntity<Response> addUser(@Valid @RequestBody UserDTO userDTO, BindingResult result) throws UserNotValidException, UserAlreadyExistsException {
        if (result.hasErrors()) {

            List<ValidationError> fieldErrors = result.getFieldErrors().stream()
                    .map(e -> new ValidationError(e.getField(), e.getRejectedValue().toString(), e.getDefaultMessage()))
                    .collect(Collectors.toList());
            logger.error("Invalid data for adding new user");
            throw new UserNotValidException("User data not valid", fieldErrors);

        }

        userService.save(userDTO);
        response.setMessage(USER_ADDED);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
