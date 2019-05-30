package com.learning.spring.rest.employees.exceptions.department;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DepartmentNotFoundByNameException extends Exception {

    private static final Logger logger = LogManager.getLogger(DepartmentNotFoundByNameException.class);


    public DepartmentNotFoundByNameException(String message, String deptName) {
        super(message);
        logger.error("No department found with name=" + deptName);
    }
}
