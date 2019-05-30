package com.learning.spring.rest.employees.exceptions.department;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DefaultDepartmentCanNotBeRemovedException extends Exception {

    private static final Logger logger = LogManager.getLogger(DefaultDepartmentCanNotBeRemovedException.class);

    public DefaultDepartmentCanNotBeRemovedException(String message) {
        super(message);
        logger.error("Department can not be removed");
    }
    }


