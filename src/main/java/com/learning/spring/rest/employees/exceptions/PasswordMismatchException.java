package com.learning.spring.rest.employees.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PasswordMismatchException extends Exception {
    private static final Logger logger = LogManager.getLogger(PasswordMismatchException.class);

    public PasswordMismatchException(String message) {
        super(message);
        logger.error(message);
    }
}
