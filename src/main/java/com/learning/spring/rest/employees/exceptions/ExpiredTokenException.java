package com.learning.spring.rest.employees.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ExpiredTokenException extends Exception {
    private static final Logger logger = LogManager.getLogger(ExpiredTokenException.class);

    public ExpiredTokenException(String message) {
        super(message);
        logger.error(message);
    }
}
