package com.learning.spring.rest.employees.exceptions.custom.manager;

import com.learning.spring.rest.employees.exceptions.custom.user.UserNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ManagerNotFoundException extends UserNotFoundException {

    private static final Logger logger = LogManager.getLogger(ManagerNotFoundException.class);


    public ManagerNotFoundException(String message) {
        super(message);
        logger.error("Manager not found");
    }
}
