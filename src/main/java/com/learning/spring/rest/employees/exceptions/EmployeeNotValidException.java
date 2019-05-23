package com.learning.spring.rest.employees.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EmployeeNotValidException extends Exception {


    private static final Logger logger = LogManager.getLogger(EmployeeNotValidException.class);


    public EmployeeNotValidException(String message) {
        super(message);
        logger.error("Data validation for adding a new employee failed");
    }

}
