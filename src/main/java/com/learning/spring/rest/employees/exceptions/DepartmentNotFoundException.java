package com.learning.spring.rest.employees.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class DepartmentNotFoundException extends Exception {

    private static final Logger logger = LogManager.getLogger(DepartmentNotFoundException.class);


    public DepartmentNotFoundException(String message, int id) {
        super(message);
        logger.error("No department found with id=" + id);
    }
}
