package com.learning.spring.rest.employees.controller;


import com.learning.spring.rest.employees.exceptions.custom.user.UserNotFoundException;
import com.learning.spring.rest.employees.services.UserServiceImpl;
import com.learning.spring.rest.employees.utils.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static com.learning.spring.rest.employees.utils.Constants.USER_REMOVED;

@RestController
public class UserController {

    private static final Logger logger = LogManager.getLogger(UserController.class);

    private UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }


    /**
     * DELETE
     */
    @DeleteMapping("/user/{id}")
    public ResponseEntity<Response> removeUser(@PathVariable("id") int id) throws UserNotFoundException {
        Response response = new Response();
        userService.removeUser(id);
        response.setMessage(USER_REMOVED);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
