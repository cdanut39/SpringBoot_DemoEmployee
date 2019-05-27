package com.learning.spring.rest.employees.exceptions;

import com.learning.spring.rest.employees.exceptionsHandler.ValidationError;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class EmployeeNotValidException extends Exception {

    private static final Logger logger = LogManager.getLogger(EmployeeNotValidException.class);

    private List<ValidationError> fieldErrors;

    public List<ValidationError> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(List<ValidationError> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

    public EmployeeNotValidException(String message, List<ValidationError> fieldErrors) {
        super(message);
        this.fieldErrors = fieldErrors;
    }


}
