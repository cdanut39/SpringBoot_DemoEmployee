package com.learning.spring.rest.employees.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EmployeeNotFoundException extends Exception {

    private static final Logger logger = LogManager.getLogger(EmployeeNotFoundException.class);


    public EmployeeNotFoundException(String message, int id) {
        super(message);
        logger.error("No employee found with id=" + id);
    }
}
