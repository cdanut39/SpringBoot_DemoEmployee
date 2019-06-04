package com.learning.spring.rest.employees.exceptions.user;

import com.learning.spring.rest.employees.exceptions.employee.EmployeeNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserAlreadyExistsException extends Exception {
    private static final Logger logger = LogManager.getLogger(EmployeeNotFoundException.class);

    public UserAlreadyExistsException(String message, String email) {
        super(message);
        logger.error("User already registered with email: " + email);
    }
}
