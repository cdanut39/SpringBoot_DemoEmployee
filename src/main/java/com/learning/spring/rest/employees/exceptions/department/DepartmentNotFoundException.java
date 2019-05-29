package com.learning.spring.rest.employees.exceptions.department;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DepartmentNotFoundException extends Exception {

    private static final Logger logger = LogManager.getLogger(DepartmentNotFoundException.class);


    public DepartmentNotFoundException(String message, int id) {
        super(message);
        logger.error("No department found with id=" + id);
    }
}
