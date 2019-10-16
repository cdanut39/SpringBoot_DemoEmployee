package com.learning.spring.rest.employees.exceptions.custom.employee;

import com.learning.spring.rest.employees.exceptions.custom.user.UserNotFoundException;
import com.learning.spring.rest.employees.utils.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.learning.spring.rest.employees.utils.Constants.EMPLOYEE_404;

public class EmployeeNotFoundException extends UserNotFoundException {

    private static final Logger logger = LogManager.getLogger(EmployeeNotFoundException.class);


    public EmployeeNotFoundException(String message, int id) {
        super(message);
        logger.error("No employee found with id=" + id);
    }

    public EmployeeNotFoundException(String message, String idType) {
        super(message);
        logger.error(EMPLOYEE_404+" by {}", idType);
    }
}
