package com.learning.spring.rest.employees.exceptions.department;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DepartmentAlreadyExistsException extends Exception {
    private static final Logger logger = LogManager.getLogger(DepartmentAlreadyExistsException.class);

    public DepartmentAlreadyExistsException(String message) {
        super(message);

    }

}
