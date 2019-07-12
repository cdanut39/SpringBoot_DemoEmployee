package com.learning.spring.rest.employees.exceptions.custom.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserAlreadyExistsException extends Exception {
    private static final Logger logger = LogManager.getLogger(UserAlreadyExistsException.class);

    public UserAlreadyExistsException(String message, String email) {
        super(message);
        logger.error("User already registered with email: " + email);
    }
}
