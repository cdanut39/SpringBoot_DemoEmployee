package com.learning.spring.rest.employees.exceptions.custom.employee;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EmployeeNotFoundException extends Exception {

    private static final Logger logger = LogManager.getLogger(EmployeeNotFoundException.class);


    public EmployeeNotFoundException(String message, int id) {
        super(message);
        logger.error("No employee found with id=" + id);
    }
}
