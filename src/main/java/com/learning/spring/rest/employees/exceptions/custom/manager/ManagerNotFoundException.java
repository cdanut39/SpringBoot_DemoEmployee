package com.learning.spring.rest.employees.exceptions.custom.manager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ManagerNotFoundException extends Exception {

    private static final Logger logger = LogManager.getLogger(ManagerNotFoundException.class);


    public ManagerNotFoundException(String message) {
        super(message);
        logger.error("Manager not found");
    }
}
